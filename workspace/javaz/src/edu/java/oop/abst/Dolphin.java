package edu.java.oop.abst;

public class Dolphin extends Animal{

    public Dolphin(String name, String place, int age) {
        this.name = name;
        this.place = place;
        this.age = age;
    }

    @Override
    public void move(){
        System.out.println("동물은 움직일 수 있다.");
    }

   public void fin(){
       System.out.println("지느러미를 사용해 헤엄친다.");
   }
}
