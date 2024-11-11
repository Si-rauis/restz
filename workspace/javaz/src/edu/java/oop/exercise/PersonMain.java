package edu.java.oop.exercise;

public class PersonMain {
    //Person 객체를 매개변수로 받아서 이름과 전화번호를 화면에 표시하는
    //인스턴스 메서드 PersonInfo()
    //반환값 : X
    //기능 : ----- 연락처 정보 -----
    // 이름 :
    // 전화번호 :
    public void PersonInfo(Person person){
        System.out.println("----- 연락처 정보 -----");
        System.out.println("이름 \t: " + person.getName());
        System.out.println("전화번호 \t: " + person.getTelNum());
    }

    public static void main(String[] args) {
        //명령행 매개변수를 이용하여
        //프로그램 실행 시 이름과 전화번호를 전달 받고
        //전달 받은 값으로 Person 객체를 생성한 후
        //정보를 화면에 출력하는 메서드를 호출
        Person person1 = new Person(args[0],args[1]);
        PersonMain per = new PersonMain();
        per.PersonInfo(person1);

        //Person 객체 생성 시 매개변수를 전달 X
        Person person2 = new Person();
        person2.changPara("전우치","000-0000-0000");
        per.PersonInfo(person2);



    }//메인 메서드
}//클래스
