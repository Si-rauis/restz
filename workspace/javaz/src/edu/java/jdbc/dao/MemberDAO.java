package edu.java.jdbc.dao;

import edu.java.jdbc.util.DBCon;
import edu.java.jdbc.vo.MemberVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//DAO ; Date Access Object
public class MemberDAO {

    private Connection con;             // DB 연결 객체
    private Statement stmt;             // 쿼리(변수 포함 X) 실행 객체
    private PreparedStatement pstmt;    // 쿼리(변수를 ? 로 바인딩하는 경우) 실행 객체
    private ResultSet rs;               // 쿼리 실행 결과 레코드 집합 저장

    //기본 생성자에서
    //DBCon의 Connection 객체를 반환하는 메서드를 이용하여 con 객체 받아오기


    public MemberDAO() {
        this.con = DBCon.getConnection();
    }

    public boolean insert(MemberVO mvo){
        //t_member 테이블에 레코드 추가
        //레코드 추가에 성공하면 true, 그렇지 않으면 false 반환

        //회원 아이디, 회원 이름, 비밀번호, 이메일, 성별, 사진, 생년월일, 가입일자
        //매개변수로 념겨받은 mvo에서 꺼내서 쿼리문 작성
        String query  = " INSERT INTO t_member VALUE (?, ?, ?, ?, ?, ?, ?, NOW()) ";

        try {
            pstmt = con.prepareStatement(query);          //쿼리문을 미리 준비
            pstmt.setString(1, mvo.getMid());       //쿼리의 물음표에 해당하는 값 바인딩
            pstmt.setString(2, mvo.getMname());
            pstmt.setString(3, mvo.getMpw());
            pstmt.setString(4, mvo.getEmail());
            pstmt.setString(5, mvo.getGender());
            pstmt.setString(6, mvo.getPhoto());
            pstmt.setString(7, mvo.getBirthDate());

            return pstmt.executeUpdate() == 1;

//            int result = pstmt.executeUpdate();        //DML 쿼리 실행 결과 행 수 저장
//            if(result == 1){ //추가 성공인 경우
//                System.out.println("insert ok");
//            } else {
//                System.out.println("insert not ok");
//            }
        } catch (SQLException e) {
            System.err.println("INSERT 쿼리 실행 실패");
            System.err.println(e.getSQLState() + " | " + e.getErrorCode() + " | " + e.getMessage());
        } finally { //예외 발생 여부 관계없이 항상 실행
            DBCon.Close(pstmt);
        }
        return false;
    }

    public MemberVO select(String id){
        //t_member 테이블의 레코드 조회 ---------------------------
        //회원 아이디가 aaa인 레코드
        ResultSet rs = null;
        String query = " SELECT * FROM t_member WHERE mid = ? ";
        MemberVO mvo = null;
        try {    //쿼리문을 미리 준비
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,id);      //쿼리의 물음표에 해당하는 값 바인딩
            rs = pstmt.executeQuery();                  //쿼리 실행하고 결과 레코드 받기

            if( rs.next()){ //읽어올 값이 있는지 확인  커서가 넘어가면서 값을 읽는 것
                //mvo 객체를 생성하여
                //읽어온 값들을 저장
                mvo = new MemberVO();
                mvo.setMid(rs.getString("mid"));
                mvo.setMname(rs.getString("mname"));
                mvo.setMpw(rs.getString("mpw"));
                mvo.setEmail(rs.getString("email"));
                mvo.setGender(rs.getString("gender"));
                mvo.setPhoto(rs.getString("photo"));
                mvo.setBirthDate(rs.getString("birth_date"));
                mvo.setJoinDate(rs.getDate("join_date"));

            } else {
                System.out.println("- 일치하는 회원 정보가 없습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBCon.Close(rs,pstmt);
        }
        return mvo;
    }

    public List<MemberVO> selectAll() {
        //t_member 테이블의 모든 레코드 조회 --------------------
        String query = " SELECT * FROM t_member ";

        List<MemberVO> list = new ArrayList<>();

        try {
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();                  //쿼리 실행하고 결과 레코드 받기

                while (rs.next()) {                 //더이상 읽을 값이 없을 때까지 반복
                    MemberVO mvo = new MemberVO();
                    mvo.setMid(rs.getString("mid"));
                    mvo.setMname(rs.getString("mname"));
                    mvo.setEmail(rs.getString("email"));
                    mvo.setJoinDate(rs.getDate("join_date"));
                    //생성된 mvo 객체를 list에 저장
                    list.add(mvo);
                }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBCon.Close(rs,pstmt);
        }
        return list;
    }

    public boolean update(MemberVO mvo){
        //t_member 테이블의 회원아이디가 aaa인 레코드 변경 --------
        //이메일 : aaa@aaa.com, 사진: aaa.png
        String query = " UPDATE t_member SET mpw = ?,email = ?, photo = ? WHERE mid = ? ";

        try {
            pstmt = con.prepareStatement(query);          //쿼리문을 미리 준비
            pstmt.setString(1, mvo.getMpw());
            pstmt.setString(2, mvo.getEmail());       //쿼리의 물음표에 해당하는 값 바인딩
            pstmt.setString(3, mvo.getPhoto());
            pstmt.setString(4, mvo.getMid());

            int result = pstmt.executeUpdate();         //DML 쿼리 실행
            if(result == 1){
                DBCon.Close(pstmt);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean delete(String id){
        //t_member 테이블의 회원아이디를 매개변수로 받아 레코드 삭제 --------
        String query = (" DELETE FROM t_member WHERE mid = '" + id + "'");
        try (Statement stmt = con.createStatement()){      //쿼리 실행 객체
            int result = stmt.executeUpdate(query);         //DML 쿼리 실행
            if(result == 1){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


}
