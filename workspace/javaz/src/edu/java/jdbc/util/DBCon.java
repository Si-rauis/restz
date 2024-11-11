package edu.java.jdbc.util;

import java.sql.*;

public class DBCon {
    //1.데이터베이스 연결 공유 객체를 클래스 외부에서 접근 할 수  없도록 선언
   private static Connection con;


    //2.기본 생성자를 선언하여 클래스 외부에서 접근할 수 없도록 처리
    private DBCon() {  }

    //3.필드로 선언된 객체를 반환하는 클래스 메서드 getConnection 작성   - 클래스 메서드 -> 공유 메서드
    //단, 필드가 null인 경우에는 객체를 생성하여 반환하도록 처리         - 인스턴스 메서드
    public static Connection getConnection(){
        if(con == null){
            String url = "jdbc:mysql://localhost:3306/modeldb"; //mysql 접속 정보 및 스키마 선택
            String username = "root";  //mysql 접속 계정
            String password = "7189";  //     "         의 비밀번호

            try {
                con = DriverManager.getConnection(url, username, password);
                System.out.println("con ok");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

    //4. ResultSet 객체와 PreparedStatement 객체를 매개변수로 받아서
    // 닫는 Close 메서드 작성
    // 4 ~ 7은 모두 공유 메서드로 작성
    public static void Close(ResultSet rs, PreparedStatement pstmt){
                try { // 객체 생성 역순으로 닫음
                    if(rs != null) rs.close();
                    if(pstmt != null) pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
    }

    //5. ResultSet 객체와 Statement 객체를 매개변수로 받아서 닫는 close 메서드 작성
    public static void Close(ResultSet rs, Statement stat){
        try { // 객체 생성 역순으로 닫음
            if(rs != null) rs.close();
            if(stat != null) stat.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //6. PreparedStatement 객체를 매개변수로 받아서 닫는 close 메서드 오버로딩
    public static void Close(PreparedStatement pstmp){
        try { // 객체 생성 역순으로 닫음
            if(pstmp != null) pstmp.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //7. 필드로 선언된 객체를 닫는 close 메서드 오버로딩
    public static void Close(){
        try { // 객체 생성 역순으로 닫음
            if(con != null) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
