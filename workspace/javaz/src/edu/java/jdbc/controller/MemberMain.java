package edu.java.jdbc.controller;

import edu.java.jdbc.dao.MemberDAO;
import edu.java.jdbc.util.DBCon;
import edu.java.jdbc.vo.MemberVO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MemberMain {

    private static Scanner scan;
    private static MemberDAO mdao;

    public void menu() {
        while(true) {
            System.out.println("------------------------------------------");
            System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ MAIN MENU");
            System.out.println("------------------------------------------");
            System.out.println(" 1.가입");
            System.out.println(" 2.회원정보 수정");
            System.out.println(" 3.회원 탈퇴");
            System.out.println(" 4.회원 정보 조회");
            System.out.println(" 5.회원 목록 보기");
            System.out.println(" 6.시스템 종료");
            System.out.print("선택 : ");
            switch (scan.nextLine()) {
                case "1":
                    join();     break;
                case "2":
                    modify();   break;
                case "3":
                    remove();   break;
                case "4":
                    view();     break;
                case "5":
                    list();     break;
                case "6":
                    System.out.println("시스템을 종료합니다.");
                    scan.close();               // SCAN 객체 닫기
                    DBCon.Close();              // DB 연결 객체 닫기
                    System.exit(0);     // 시스템 정상 종료
                default:
                    System.out.println(">1 ~ 2를 선책해 주세요");
            }
        }
    }

    public void join() {
        // 사용자의 데이터를 입력받아
        // t_member 테이블에 레코드 추가하는 메서드 호출 및 // 반환되는 값을 화면에 표시
        //회원 아이디, 회원 이름, 비밀번호, 이메일, 성별, 사진, 생년월일, 가입일자
        //aaa           김이박     1111    a@a.com    F  a.png  1999-09-09  지금

        MemberVO mvo = new MemberVO();
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ JOIN");
        System.out.println("------------------------------------------");
        System.out.print("아이디 : ");                mvo.setMid(scan.nextLine());
        System.out.print("이름 : ");                  mvo.setMname(scan.nextLine());
        System.out.print("비밀번호 : ");              mvo.setMpw(scan.nextLine());
        System.out.print("이메일 : ");                mvo.setEmail(scan.nextLine());
        System.out.print("성별(F / M) : ");           mvo.setGender(scan.nextLine());
        System.out.print("사진 : ");                  mvo.setPhoto(scan.nextLine());
        System.out.print("생년월일(yyyy-MM-dd) : ");  mvo.setBirthDate(scan.nextLine());

        if(mdao.insert(mvo)) {
            System.out.println("> 회원 가입이 완료되었습니다.");
            System.out.println("> 로그인 후 이용해주세요.");
        } else {
            System.out.println("> 회원 가입이 실패했습니다");
            System.out.println("> 다시 시도해주세요.");
        }
    }

    public void modify(){
        // 수정하려는 정보를 입력받아
        // t_member 테이블에 레코드를 추가하는 메서드 호출 및 // 반환값 화면 표시
        //이메일 : aaa@aaa.com, 사진: aaa.png
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ MODIFY");
        System.out.println("------------------------------------------");
        System.out.print("ID : ");
        String mid = scan.nextLine();
        //수정하려는 레코드의 아이디를 입력받아
        //t_member 테이블에서 mvo객체를 받아오고
        MemberVO mvo = mdao.select(mid);


        System.out.print("수정하려는 비밀번호 : ");
        String input = scan.nextLine();
                if(!input.isEmpty()){
            mvo.setMpw(input);
        }
        System.out.print("수정하려는 email : ");
        input = scan.nextLine();
        if(!input.isEmpty()){
            mvo.setEmail(input);
        }
        System.out.print("수정하려는 photo : ");
        input = scan.nextLine();
        if(!input.isEmpty()){
            mvo.setPhoto(input);
        }

        if(mdao.update(mvo)){
            System.out.println("정보 수정에 성공하였습니다.");
        } else {
            System.out.println("정보 수정에 실패하셨습니다.");
        }
    }

    public void remove(){
        // 삭제하려는 회원 아이디를 입력받아
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ REMOVE");
        System.out.println("------------------------------------------");
        System.out.print("ID : ");
        String mid = scan.nextLine();
        //탈퇴하려는 레코드의 아이디를 입력받아
        //t_member 테이블에서 mvo객체를 받아오고

        if(mdao.delete(mid)){
            System.out.println("회원 탈퇴에 성공하였습니다.");
        } else {
            System.out.println("회원 탈퇴에 실패하셨습니다.");
        }                     // t_member 테이블의 레코드를 삭제하는 메서드 호출 및 // 반환되는 값 화면 표시
    }

    public void view(){
        // 조회하려는 회원아이디를 입력받아
        // t_member 테이블의 레코드를 조회하는 메서드 호출 및 // 반환되는 값 화면 표시
        //회원 아이디가 aaa인 레코드
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ VIEW");
        System.out.println("------------------------------------------");
        System.out.print("ID : ");
        String mid = scan.nextLine();
        //조회하려는 레코드의 아이디를 입력받아
        //t_member 테이블에서 mvo객체를 받아오고
        MemberVO mvo = mdao.select(mid);
        if(mvo != null) {
            System.out.println("------- 회원 정보 -------");
            System.out.println("회원 아이디 : " + mvo.getMid());
            System.out.println("회원 이름 : " + mvo.getMname());
            System.out.println("이메일 : " + mvo.getEmail());
            System.out.println("성별 : " + mvo.getGender());
            System.out.println("사진 : " + mvo.getPhoto());
            System.out.println("생년월일 : " + mvo.getBirthDate());
            System.out.println("가입일자 : " + mvo.getJoinDate());
        } else {
            System.out.println("- 일치하는 회원 정보가 없습니다");
        }
    }


    public void list() {
        // t_member 테이블의 모든 레코드 조회하는 메서드 호출 및 반환값 화면 표시
        // 단, 등록된 회원이 없는 경우 적절한 메시지 출력
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ List");
        System.out.println("------------------------------------------");
        List<MemberVO> members = mdao.selectAll();
        //조회하려는 레코드의 아이디를 입력받아
        //t_member 테이블에서 mvo객체를 받아오고
        for(int i = 0; i < members.size();i++) {
            MemberVO mvo = members.get(i);
            System.out.println("------- 회원 정보 -------");
            System.out.println("회원 아이디 : " + mvo.getMid());
            System.out.println("회원 이름 : " + mvo.getMname());
            System.out.println("이메일 : " + mvo.getEmail());
            System.out.println("가입일자 : " + mvo.getJoinDate());
        }

//        foreach
//        for(MemberVO mvolist : members){
//            System.out.println(mvolist.getMid() + " | " + mvolist.getMname() + " | " +
//                                mvolist.getEmail() + " | " + mvolist.getJoinDate() );
//        }

//        foreach
//        members.forEach(mvo -> System.out.println(mvo.getMid() + " | " + mvo.getMname() + " | " +
//                                                  mvo.getEmail() + " | " + mvo.getJoinDate() ));
   }




    public static void main(String[] args) {
        scan = new Scanner(System.in);
        MemberMain main = new MemberMain();
        mdao = new MemberDAO();
        main.menu();
    }
}
