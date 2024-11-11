package edu.java.oop.abst;

public class PolyMain {
    public static void main(String[] args) {

        Dolphin dolphin = new Dolphin("남방돌고래", "제주 앞바다" , 10);
        Person person = new Person("소녀", "도시", 15);

        Animal ani = new Dolphin("남방돌고래", "제주 앞바다" , 10);
               ani.move();

               ani = new Person("소녀", "도시", 15);
               ani.move();


    }
}
