package edu.java.util;

//1.Thtread 클래스를 상속받아 부모의 메서드를 재정의하는 ClassA
class ClassA extends Thread {
    @Override
    public void run() {
        System.out.println("Thread 상속");
    }
}

//2.Runnable 인터페이스를 명시적으로 구현하여 부모의 메서드를 재정의하는 ClassB
class ClassB implements Runnable{
    @Override
    public void run() {
        System.out.println("Runnable 구현");
    }
}

public class LambdaMain {
    public static void main(String[] args) {
        //3.    ClassA의 객체 a를 생성하고
        ClassA a = new ClassA();
        //3.1   ClassA에 오버라이딩된 메서드를 스레드로 시작 시키기
        a.start();

        //4.ClassB에 오버라이딩된 메서드를 스레드로 시작 시키기
        Runnable rab = new ClassB();
        Thread thr = new Thread(rab);
        thr.start();

        System.out.println("5. 참조 변수가 있는 익명의 객체로 Thread 구현");
        Thread thr2 = new Thread(){
            @Override
            public void run() {
                System.out.println("Thread 익명의 객체로 구현");
            }
        };
        thr2.start();

        System.out.println("6. 참조 변수가 없는 익명의 객체로 Runnable 구현");
        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("Runnable 익명의 객체로 구현");
            }
        }).start();


        System.out.println("7. 람다식으로 Runnable 구현");
        new Thread( () -> System.out.println("Thread lambda") ).start();

    }
}
