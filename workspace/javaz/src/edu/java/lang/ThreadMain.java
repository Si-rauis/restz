package edu.java.lang;

//1. Thread 클래스를 상속받는 ThreadExtends 클래스
    //1.1 기본 생성자 오버로딩
    //1.2 문자열을 매개변수로 받는 생성자를 오버로딩하여
    //    넘겨받은 값을 부모 생성자에게 전달
    //1.3 부모 메서드를 오버라이딩하여 다음을 스레드로 처리
    //1초마다 스레드의 이름과 1 ~ 3 출력

class ThreadExtends extends Thread{
    public ThreadExtends() {
        super();
    }

    public ThreadExtends(String name) {
        super(name);
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i++){
            System.out.println(getName() + " " + (i + 1));
        }
    }
}

//2. Runnable 인터페이스를 구현하는 ThreadImple 클래스 ----
    //1.1부모 메서드를 오버라이딩하여 다음을 스레드로 처리
        //스레드의 이름과 4 ~ 6 출력
class ThreadImple implements Runnable {
    @Override
    public void run() {
        for(int i = 4; i <= 6; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}


public class ThreadMain {
    public static void main(String[] args) {

        ThreadImple t3 = new ThreadImple();
        //t3.start();     //Thread 상속 받지 않았으므로 X

        Runnable r = new ThreadImple();
        Thread t4 = new Thread(r);
        t4.setName("티4");
        t4.start();

        //////////////////////////////////////////////
        ThreadExtends t1 = new ThreadExtends();
        t1.setName("티1");

        ThreadExtends t2 = new ThreadExtends("티2");

        t1.start();
        t2.start();


        System.out.println("----- main() begin -----");
        Thread t = Thread.currentThread();

        System.out.println("실행 중인 쓰레드 이름 : " + t.getName());

        System.out.println("------ main() end ------");

    }
}

