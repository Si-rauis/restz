package edu.java.jdbc;

import java.sql.*;
import java.util.Scanner;

public class JDBCTest {

    private Connection con; // DB 연결 객체
    private Statement stmt; // 쿼리 실행 객체


    public JDBCTest() {
        String url = "jdbc:mysql://localhost:3306/modeldb"; //mysql 접속 정보 및 스키마 선택
        String username = "root";  //mysql 접속 계정
        String password = "7189";  //     "         의 비밀번호

        //mysql Database 접속 - Connection 객체 필요
//       Connection con = null;
        try {
            // - Connection 객체 생성 - DriveManager을 이용
            con = DriverManager.getConnection(url, username, password);
            System.out.println("con ok!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void insert(){ //t_member 테이블에 레코드 추가
            //회원 아이디, 회원 이름, 비밀번호, 이메일, 성별, 사진, 생년월일, 가입일자
            //aaa           김이박     1111    a@a.com    F  a.png  1999-09-09  지금
            //bbb           김비비     1111    b@b.com    M  b.png  1999-11-22  지금
        String query  = " INSERT INTO t_member VALUE ('aaa', '김이박', '1111', 'a@a.com', 'F', 'a.png', '1999-09-09', NOW()) ";


        /*
               query  = """
                            INSERT INTO t_member(mid, mname, mpw, email, gender, birth_date)
                            VALUE ('bbb', '김비비', '1111', 'b@b.com', 'M', '1999-11-22')
                        """;

         */



        try {
            stmt = con.createStatement();               //쿼리 실행 객체 생성
            int result = stmt.executeUpdate(query);     //DML 쿼리 실행
            if(result == 1){ //추가 성공인 경우
                System.out.println("insert ok");
            } else {
                System.out.println("insert not ok");
            }
        } catch (SQLException e) {
            System.err.println("INSERT 쿼리 실행 실패");
            System.err.println(e.getSQLState() + " | " + e.getErrorCode() + " | " + e.getMessage());
        } finally { //예외 발생 여부 관계없이 항상 실행
            try {
                if( stmt != null )  stmt.close();               //쿼리 실행 객체 닫기
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void select(String id){ //t_member 테이블의 레코드 조회 ---------------------------
        //회원 아이디가 aaa인 레코드
        ResultSet rs = null;
        String query = " SELECT * FROM t_member WHERE mid = ? ";
        try (PreparedStatement pstmt                    //쿼리 실행 객체
                     = con.prepareStatement(query)){    //쿼리문을 미리 준비
            pstmt.setString(1,id);      //쿼리의 물음표에 해당하는 값 바인딩
            rs = pstmt.executeQuery();                  //쿼리 실행하고 결과 레코드 받기

            if( rs.next()){ //읽어올 값이 있는지 확인
                System.out.println("------- 회원 정보 -------");
                System.out.println("회원 아이디 : " + rs.getString("mid"));
                System.out.println("회원 이름 : " + rs.getString("mname"));
                System.out.println("이메일 : " + rs.getString("email"));
                System.out.println("성별 : " + rs.getString("gender"));
                System.out.println("사진 : " + rs.getString("photo"));
                System.out.println("생년월일 : " + rs.getString("birth_date"));
                System.out.println("가입일자 : " + rs.getString("join_date"));
            } else {
                System.out.println("- 일치하는 회원 정보가 없습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public void selectAll() {//t_member 테이블의 모든 레코드 조회 --------------------
        String query = " SELECT * FROM t_member ";
        ResultSet rs = null;
        try (PreparedStatement pstmt                    //쿼리 실행 객체
                     = con.prepareStatement(query)) {   //쿼리문을 미리 준비
            rs = pstmt.executeQuery();                  //쿼리 실행하고 결과 레코드 받기
            System.out.println("======== 전체 회원 목록 ========");
            while (rs.next()) {                 //더이상 읽을 값이 없을 때까지 반복
                System.out.println(rs.getString("mid") + " | " + rs.getString("mname") +
                        " | " + rs.getString("email") + " | " + rs.getString("join_date"));
            }
            System.out.println("--------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



        public void update(){ //t_member 테이블의 회원아이디가 aaa인 레코드 변경 --------
        //이메일 : aaa@aaa.com, 사진: aaa.png
        String query = " UPDATE t_member SET email = 'aaa@aaa.com', photo = 'aaa.png' WHERE mid = 'aaa' ";
        try (Statement stmt = con.createStatement();){      //쿼리 실행 객체
            int result = stmt.executeUpdate(query);         //DML 쿼리 실행
            if(result == 1)System.out.println("UPDATE ok"); //변경 성공인 경우
            else System.out.println("UPDATE not ok");                           //try with resours 사용
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(){ //t_member 테이블의 회원아이디를 매개변수로 받아 레코드 삭제 --------
        Scanner scan = new Scanner(System.in);
        System.out.print("회원 아이디: ");
        String mid = scan.nextLine();
        String query = (" DELETE FROM t_member WHERE mid = '" + mid + "'");
        try (Statement stmt = con.createStatement();){      //쿼리 실행 객체
            int result = stmt.executeUpdate(query);         //DML 쿼리 실행
            if(result == 1)System.out.println("DELETE ok"); //삭제 성공인 경우
            else System.out.println("DELETE not ok");                           //try with resours 사용
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







    public static void main(String[] args) {

        JDBCTest test = new JDBCTest();
        Scanner scan = new Scanner(System.in);

        test.selectAll();

        //id aaa를 매개변수로 전달하여 select 메서드 호출
        //test.select("aaa");

        //id bbb를 매개변수로 전달하여 delete 메서드 호출
        //test.delete();

        //test.insert();
        //test.update();

    } // Main
} // Class
