package edu.java.oop.abst;

public abstract class Animal {
   String name, place;
   int age;

    public abstract void move();

    public  void sleep() {
        System.out.println(name + "가 잠을 잘 수 있습니다.");
    };
}
