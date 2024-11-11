package edu.java.oop.abst;


interface InterfaceA {
    int POWER = 5;   //final 키워드 생략된 상수
    // int ABC;     // 인터페이스에는 상수만 존재 -> 값 초기화 필요
    public static final int ABC = 123;
}

interface InterfaceB {

    /*
     public void method1(){System.out.println();}  //인터페이스에는 추상 메서드만 존재
     */
    public void method1();

    public abstract void method2();
}
interface InterfaceC extends InterfaceA, InterfaceB{  //인터페이스는 다중 상속 가능
    public default void method3(){
        System.out.println("인터페이스의 디폴트 메서드");
    };

    public static void method4(){
        System.out.println("인터페이스의 공유 메서드");
    }
}

class InterfaceAImpl implements InterfaceA{  }
class InterfaceBImpl implements InterfaceA, InterfaceB{
    @Override
    public void method1() {
        System.out.println("method1 오버라이딩");
    }

    @Override
    public void method2() {
        System.out.println("method2 오버라이딩");
    }
}
class InterfaceCImpl implements InterfaceC{
    @Override
    public void method1() {
        System.out.println("method1 오버라이딩");
    }

    @Override
    public void method2() {
        System.out.println("method2 오버라이딩");
    }

    @Override
    public void method3() {
        System.out.println("method3 오버라이딩");
    }
}

public class InterfaceMain {
    public static void main(String[] args) {

        InterfaceCImpl c = new InterfaceCImpl();
        c.method1();
        c.method2();
        c.method3();
        InterfaceC.method4();

    }
}
