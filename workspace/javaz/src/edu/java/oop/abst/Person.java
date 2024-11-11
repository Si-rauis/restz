package edu.java.oop.abst;

public class Person extends Animal{
    public Person(String name, String place, int age) {
        this.name = name;
        this.place = place;
        this.age = age;
    }
    //사람 전용 기능 추가

    public void tool(){
        System.out.println("사람은 도구를 사용할 수 있다.");
    }
    @Override
    public void move(){
        System.out.println("동물은 움직일 수 있다.");
    }

}
