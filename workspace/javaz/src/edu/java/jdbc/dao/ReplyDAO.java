package edu.java.jdbc.dao;

import edu.java.jdbc.util.DBCon;
import edu.java.jdbc.vo.BoardVO;
import edu.java.jdbc.vo.ReplyVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReplyDAO {
    private Connection con;             // DB 연결 객체
    private Statement stmt;             // 쿼리(변수 포함 X) 실행 객체
    private PreparedStatement pstmt;    // 쿼리(변수를 ? 로 바인딩하는 경우) 실행 객체
    private ResultSet rs;               // 쿼리 실행 결과 레코드 집합 저장

    public ReplyDAO() {
        this.con = DBCon.getConnection();
    }

    public boolean insert(ReplyVO rvo) {
        String query = " INSERT INTO t_reply (reply, bno, replier) VALUE (?, ?, 'aaa');";

        try {
            pstmt = con.prepareStatement(query);          //쿼리문을 미리 준비
            pstmt.setString(1, rvo.getReply());       //쿼리의 물음표에 해당하는 값 바인딩
            pstmt.setInt(2, rvo.getBno());       //쿼리의 물음표에 해당하는 값 바인딩

            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("댓글 작성에 실패했습니다");
        } finally { //예외 발생 여부 관계없이 항상 실행
            DBCon.Close(pstmt);
        } return false;
    }


    public boolean update(ReplyVO rvo){
        String query = " UPDATE t_reply SET reply = ? WHERE rno = ? ";

        try {
            pstmt = con.prepareStatement(query);          //쿼리문을 미리 준비
            pstmt.setString(1, rvo.getReply());
            pstmt.setInt(2, rvo.getRno());

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

    public boolean delete(String rno, String bno){
        //t_member 테이블의 회원아이디를 매개변수로 받아 레코드 삭제 --------
        String query = (" DELETE FROM t_reply WHERE rno = '" + rno + "'");
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

    public List<ReplyVO> selectALL(String bno){
        //t_member 테이블의 레코드 조회 ---------------------------
        //회원 아이디가 aaa인 레코드
        List<ReplyVO> list = new ArrayList<>();
        String query = " SELECT * FROM t_reply WHERE bno = ? ";
        ReplyVO rvo = null;
        try {    //쿼리문을 미리 준비
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,bno);      //쿼리의 물음표에 해당하는 값 바인딩

            rs = pstmt.executeQuery();                   //쿼리 실행하고 결과 레코드 받기
            while( rs.next()){ //읽어올 값이 있는지 확인  커서가 넘어가면서 값을 읽는 것
                //mvo 객체를 생성하여
                //읽어온 값들을 저장
                rvo = new ReplyVO();
                rvo.setBno(rs.getInt("bno"));
                rvo.setRno(rs.getInt("rno"));
                rvo.setReply(rs.getString("reply"));
                rvo.setReplier(rs.getString("replier"));
                rvo.setReply_date(rs.getDate("reply_date"));
                list.add(rvo);
            }
        } catch (SQLException e) {
            System.out.println("댓글이 없습니다.");
            throw new RuntimeException(e);
        } finally {
            DBCon.Close(rs,pstmt);
        }
        return list;
    }

    public ReplyVO select(String rno){
        ResultSet rs = null;
        String query = " SELECT * FROM t_reply WHERE rno = ? ";
        ReplyVO rvo = null;
        try {    //쿼리문을 미리 준비
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,rno);      //쿼리의 물음표에 해당하는 값 바인딩

            rs = pstmt.executeQuery();                   //쿼리 실행하고 결과 레코드 받기
            if( rs.next()){ //읽어올 값이 있는지 확인  커서가 넘어가면서 값을 읽는 것
                //mvo 객체를 생성하여
                //읽어온 값들을 저장
                rvo = new ReplyVO();
                rvo.setRno(rs.getInt("rno"));
                rvo.setBno(rs.getInt("bno"));
                rvo.setReply(rs.getString("reply"));
                rvo.setReplier(rs.getString("replier"));
                rvo.setReply_date(rs.getDate("reply_date"));
            }
        } catch (SQLException e) {
            System.out.println("일치하는 댓글이 없습니다");
        } finally {
            DBCon.Close(rs, pstmt);
        }
        return rvo;
    }
}

