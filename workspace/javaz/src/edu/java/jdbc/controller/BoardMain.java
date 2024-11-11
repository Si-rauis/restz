package edu.java.jdbc.controller;

import edu.java.jdbc.dao.BoardDAO;
import edu.java.jdbc.dao.ReplyDAO;
import edu.java.jdbc.util.DBCon;
import edu.java.jdbc.vo.BoardVO;
import edu.java.jdbc.vo.ReplyVO;


import java.util.List;
import java.util.Scanner;

public class BoardMain {
    private static Scanner scan;
    private static BoardDAO bdao;
    private static ReplyDAO rdao;
    public void menu() {
        while(true) {
            System.out.println("------------------------------------------");
            System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ BOARD MENU");
            System.out.println("------------------------------------------");
            System.out.println(" 1.게시판 작성");
            System.out.println(" 2.게시물 수정");
            System.out.println(" 3.게시물 삭제");
            System.out.println(" 4.게시물 조회");
            System.out.println(" 5.게시물 목록");
            System.out.println(" 6.시스템 종료");
            System.out.print("선택 : ");
            switch (scan.nextLine()) {
                case "1":
                    create();     break;
                case "2":
                    modify();     break;
                case "3":
                    remove();     break;
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
                    System.out.println(">1 ~ 6를 선책해 주세요");
            }
        }
    }

    public void create(){
        BoardVO bvo = new BoardVO();
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ CREATE");
        System.out.println("------------------------------------------");
        System.out.print("제목 : ");                  bvo.setTitle(scan.nextLine());
        System.out.print("내용 : ");                  bvo.setContent(scan.nextLine());
        System.out.print("작성자 : ");                bvo.setWriter(scan.nextLine());

        if(bdao.insert(bvo)) {
            System.out.println("> 게시판 작성이 완료되었습니다.");
        } else {
            System.out.println("> 게시판 작성에 실패하였습니다.");
        }
    }


    public void modify(){
        // 수정하려는 정보를 입력받아
        // t_member 테이블에 레코드를 추가하는 메서드 호출 및 // 반환값 화면 표시
        //이메일 : aaa@aaa.com, 사진: aaa.png
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ MODIFY");
        System.out.println("------------------------------------------");
        System.out.print("No : ");
        String bno = scan.nextLine();
        //수정하려는 레코드의 아이디를 입력받아
        //t_member 테이블에서 mvo객체를 받아오고
        BoardVO bvo = bdao.select(bno);


        System.out.print("제목 : ");
        String input = scan.nextLine();
        if(!input.isEmpty()){
            bvo.setTitle(input);
        }
        System.out.print("내용 : ");
        input = scan.nextLine();
        if(!input.isEmpty()){
            bvo.setContent(input);
        }
        System.out.print("작성자 : ");
        input = scan.nextLine();
        if(!input.isEmpty()){
            bvo.setWriter(input);
        }

        if(bdao.update(bvo)){
            System.out.println("게시판 수정에 성공하였습니다.");
        }
    }


    public void remove(){
        // 삭제하려는 회원 아이디를 입력받아
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ REMOVE");
        System.out.println("------------------------------------------");
        System.out.print("No : ");
        String bno = scan.nextLine();
        //탈퇴하려는 레코드의 아이디를 입력받아
        //t_board 테이블에서 bvo객체를 받아오고

        if(bdao.delete(bno)){
            System.out.println("게시판 삭제에 성공하였습니다.");
        }                   // t_member 테이블의 레코드를 삭제하는 메서드 호출 및 // 반환되는 값 화면 표시
    }


    public void view(){
        // 조회하려는 회원아이디를 입력받아
        // t_member 테이블의 레코드를 조회하는 메서드 호출 및 // 반환되는 값 화면 표시
        //회원 아이디가 aaa인 레코드
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ VIEW");
        System.out.println("------------------------------------------");
        System.out.print("No : ");
        String bno = scan.nextLine();
        //조회하려는 레코드의 아이디를 입력받아
        //t_member 테이블에서 mvo객체를 받아오고
        BoardVO bvo = bdao.select(bno);
        if(bvo != null) {
            System.out.println("--------- 게시판 ---------");
            System.out.println("No : " + bvo.getBno());
            System.out.println("제목 : " + bvo.getTitle());
            System.out.println("내용 : " + bvo.getContent());
            System.out.println("작성자 : " + bvo.getWriter());
            System.out.println("조회수 : " + bvo.getHit());
            System.out.println("작성일자 : " + bvo.getWrite_date());
            replyList(bno);
        } else {
            System.out.println("- 일치하는 게시판이 없습니다");
        }
    }


    public void list() {
        // t_member 테이블의 모든 레코드 조회하는 메서드 호출 및 반환값 화면 표시
        // 단, 등록된 회원이 없는 경우 적절한 메시지 출력
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ List");
        System.out.println("------------------------------------------");
        List<BoardVO> members = bdao.selectAll();
        //조회하려는 레코드의 아이디를 입력받아
        //t_member 테이블에서 mvo객체를 받아오고
        for (BoardVO bvo : members) {
            System.out.println("------- 게시판 정보 -------");
            System.out.println("No : " + bvo.getBno());
            System.out.println("제목 : " + bvo.getTitle());
            System.out.println("내용 : " + bvo.getContent());
            System.out.println("작성자 : " + bvo.getWriter());
            System.out.println("조회수 : " + bvo.getHit());
            System.out.println("작성일자 : " + bvo.getWrite_date());
        }
    }

    public void replyList(String bno) {
        List<ReplyVO> members = rdao.selectALL(bno);
        System.out.println("------------------------------------------");
        if(members == null){
            System.out.println("댓글이 없습니다");
        }else {
            System.out.println(bno + "번 게시글의 댓글");
            for (ReplyVO rvo : members) {
                System.out.print(" 댓글 : " + rvo.getReply());
                System.out.print("\t작성자  : " + rvo.getReplier());
                System.out.print("\t작성 일자 : " + rvo.getReply_date());
                System.out.print("\t댓글 번호 : " + rvo.getRno());
                System.out.println();
            }
        }
        replyMenu(bno);
    }

    public void replyWrite(String bno){
        ReplyVO rvo = new ReplyVO();
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ REPLY_WRITER");
        System.out.println("------------------------------------------");
        System.out.print("댓글 : ");
        rvo.setReply(scan.nextLine());
        int Bno = Integer.parseInt(bno);
        rvo.setBno(Bno);

        if(rdao.insert(rvo)) {
            System.out.println("> 댓글 작성이 완료되었습니다.");
        }
    }

    public void replyRemove(String bno){
        System.out.println("\n------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ REPLY_REMOVE");
        System.out.println("------------------------------------------");
        System.out.print("Rno : ");
        String rno = scan.nextLine();
        //탈퇴하려는 레코드의 아이디를 입력받아
        //t_board 테이블에서 bvo객체를 받아오고

        if(rdao.delete(rno , bno)){
            System.out.println("게시판 삭제에 성공하였습니다.");
        }                   // t_member 테이블의 레코드를 삭제하는 메서드 호출 및 // 반환되는 값 화면 표시
    }

    public void replyUpdate(){
        System.out.println("------------------------------------------");
        System.out.println("ㅁ MEMBER only SYSTEM ㅁㅁㅁㅁㅁ REPLY_UPDATE");
        System.out.println("------------------------------------------");
        System.out.print("Rno : ");
        String rno = scan.nextLine();
        //수정하려는 레코드의 아이디를 입력받아
        //t_member 테이블에서 mvo객체를 받아오고
        ReplyVO rvo = rdao.select(rno);


        System.out.print("댓글 : ");
        String input = scan.nextLine();
        if(!input.isEmpty()){
            rvo.setReply(input);
        }

        if(rdao.update(rvo)){
            System.out.println("댓글 수정에 성공하였습니다.");
        }
    }
    public void replyMenu(String bno){
        while(true) {
            System.out.println("------------------------------------------");
            System.out.println(" 1.댓글 작성");
            System.out.println(" 2.댓글 목록");
            System.out.println(" 3.댓글 삭제");
            System.out.println(" 4.댓글 수정");
            System.out.println(" 5.메뉴 보기");
            System.out.print("선택 : ");
            switch (scan.nextLine()) {
                case "1":
                    replyWrite(bno);
                    break;
                case "2":
                    replyList(bno);
                    break;
                case "3":
                    replyRemove(bno);
                    break;
                case "4":
                    replyUpdate();
                    break;
                case "5":
                    menu();
                    break;
                default:
                    System.out.println(">1 ~ 5를 선책해 주세요");
            }
        }
    }




    public static void main(String[] args) {
        scan = new Scanner(System.in);
        bdao = new BoardDAO();
        rdao = new ReplyDAO();
        BoardMain main = new BoardMain();
        main.menu();
    }
}
