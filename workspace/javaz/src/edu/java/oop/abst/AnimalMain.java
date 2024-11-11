package edu.java.oop.abst;

public class AnimalMain {
    public static void main(String[] args) {

        //Animal ani = new Animal();  추상 클래스는 객체 생성 불가능

        Dolphin dolphin = new Dolphin("남방돌고래", "제주 앞바다" , 10);
        Person person = new Person("소녀", "도시", 15);

        dolphin.move();
        dolphin.fin();
        dolphin.sleep();

        person.tool();
        person.sleep();
        person.move();
    }
}
