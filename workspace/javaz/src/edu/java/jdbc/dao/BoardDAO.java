package edu.java.jdbc.dao;

import edu.java.jdbc.util.DBCon;
import edu.java.jdbc.vo.BoardVO;
import edu.java.jdbc.vo.MemberVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private Connection con;             // DB 연결 객체
    private Statement stmt;             // 쿼리(변수 포함 X) 실행 객체
    private PreparedStatement pstmt;    // 쿼리(변수를 ? 로 바인딩하는 경우) 실행 객체
    private ResultSet rs;               // 쿼리 실행 결과 레코드 집합 저장

    public BoardDAO() {
        this.con = DBCon.getConnection();
    }

    public boolean insert(BoardVO bvo) {
        String query = " INSERT INTO t_board (title, content, writer) VALUE (?, ?, ?)";

        try {
            pstmt = con.prepareStatement(query);          //쿼리문을 미리 준비
            pstmt.setString(1, bvo.getTitle());       //쿼리의 물음표에 해당하는 값 바인딩
            pstmt.setString(2, bvo.getContent());
            pstmt.setString(3, bvo.getWriter());

            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("게시판 작성에 실패했습니다");
        } finally { //예외 발생 여부 관계없이 항상 실행
            DBCon.Close(pstmt);
        } return false;
    }


    public boolean update(BoardVO bvo){
        String query = " UPDATE t_board SET title = ?,content = ?, writer = ? WHERE bno = ? ";

        try {
            pstmt = con.prepareStatement(query);          //쿼리문을 미리 준비
            pstmt.setString(1, bvo.getTitle());
            pstmt.setString(2, bvo.getContent());       //쿼리의 물음표에 해당하는 값 바인딩
            pstmt.setString(3, bvo.getWriter());
            pstmt.setInt(4, bvo.getBno());

            int result = pstmt.executeUpdate();         //DML 쿼리 실행
            if(result == 1){
                DBCon.Close(pstmt);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("게시물 수정에 실패하였습니다.");
        }
        DBCon.Close(pstmt);
        return false;
    }


    public boolean delete(String bno){
        //t_member 테이블의 회원아이디를 매개변수로 받아 레코드 삭제 --------
        String query = (" DELETE FROM t_board WHERE bno = '" + bno + "'");
        try (Statement stmt = con.createStatement()){      //쿼리 실행 객체
            int result = stmt.executeUpdate(query);         //DML 쿼리 실행
            if(result == 1){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("게시판 삭제에 실패하였습니다.");
        }
        return false;
    }


    public BoardVO select(String bno){
        //t_member 테이블의 레코드 조회 ---------------------------
        //회원 아이디가 aaa인 레코드
        ResultSet rs = null;
        String query = " SELECT * FROM t_board WHERE bno = ? ";
        String updateQuery = " UPDATE t_board SET hit = hit + 1 WHERE bno = ? ";
        BoardVO bvo = null;
        try {    //쿼리문을 미리 준비
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,bno);      //쿼리의 물음표에 해당하는 값 바인딩

            PreparedStatement pstmt1 = con.prepareStatement(updateQuery);
            pstmt1.setString(1,bno);      //쿼리의 물음표에 해당하는 값 바인딩
              //쿼리 실행하고 결과 레코드 받기

            int result = pstmt1.executeUpdate();         //DML 쿼리 실행
            if(result == 1){
                DBCon.Close(pstmt1);
            }

            rs = pstmt.executeQuery();                   //쿼리 실행하고 결과 레코드 받기
            if( rs.next()){ //읽어올 값이 있는지 확인  커서가 넘어가면서 값을 읽는 것
                //mvo 객체를 생성하여
                //읽어온 값들을 저장
                bvo = new BoardVO();
                bvo.setBno(rs.getInt("bno"));
                bvo.setTitle(rs.getString("title"));
                bvo.setContent(rs.getString("content"));
                bvo.setWriter(rs.getString("writer"));
                bvo.setHit(rs.getInt("hit"));
                bvo.setWrite_date(rs.getDate("write_date"));

            }


        } catch (SQLException e) {
            System.out.println("일치하는 게시판이 없습니다");
        } finally {
            DBCon.Close(rs,pstmt);
        }
        return bvo;
    }


    public List<BoardVO> selectAll() {
        //t_member 테이블의 모든 레코드 조회 --------------------
        String query = " SELECT * FROM t_board ";

        List<BoardVO> list = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();                  //쿼리 실행하고 결과 레코드 받기

            while (rs.next()) {                 //더이상 읽을 값이 없을 때까지 반복
                BoardVO bvo = new BoardVO();
                bvo.setBno(rs.getInt("bno"));
                bvo.setTitle(rs.getString("title"));
                bvo.setContent(rs.getString("content"));
                bvo.setWriter(rs.getString("writer"));
                bvo.setHit(rs.getInt("hit"));
                bvo.setWrite_date(rs.getDate("writer_date"));
                //생성된 bvo 객체를 list에 저장
                list.add(bvo);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBCon.Close(rs,pstmt);
        }
        return list;
    }
}
