package edu.java.oop.exercise;


//이름과 전화번호를 저장하는 클래스

public class Person {
    //필드들은 객체 내부에서만 접근 가능
    private String name;
    private String telNum;

    public Person() { }

    //필드들의 값을 매개변수로 받아서 초기화하는 생성자
    public Person(String name, String telNum) {
        this.name = name;
        this.telNum = telNum;
    }
    public Person(String name) {
        this.name = name;
    }
    //객체 외부에서 필드에 접근 가능하도록 메서드들 정의
    public void changPara(String name, String telNum){
        this.name = name;
        this.telNum = telNum;
    }

    //객체의 필드값들을 문자열로 반환하는 메서드 정의

    public String getName() {
        return name;
    }

    public String getTelNum() {
        return telNum;
    }

    public String toString(){
        return "Person name : " + name + ", 전화번호: " + telNum;
    }
}

