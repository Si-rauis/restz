package edu.java.oop;

class Outer{
    int i;
    class Member{
        public void method(){
            System.out.println("Member's method()");
        }
    }

    static class Static{
        public static void method(){
            System.out.println("Static's method()");
        }
    }
}

//추상 메서드 method를 갖는 추상 클래스 Abstract
abstract class Abstract{
    public abstract void method();
}

//추상 메서드 method를 갖는 인터페이스 Interface
interface Interface{
    public abstract void method();
}

//Abstarct를 상속받는 AbstractSub 클래스
class AbstractSub extends Abstract{
    @Override
    public void method() {

    }
}

//Interface를 구현하는 InterfaceImpl 클래스
class InterfaceImpl implements Interface{
    @Override
    public void method() {

    }
}

public class InnerClassMain {
    public static void main(String[] args) {
        //추상 클래스 Abstract를 구현하는 익명의 내부 클래스 -참조 변수 O
        // Abstract abs = new Abstract();        //추상 클래스는 객체 생성 불가
        Abstract abs =  new Abstract() {
            @Override
            public void method() {
                System.out.println("추상 클래스를 구현하는 익명의 내부 클래스로 구현");
            }
        };
        abs.method();

        //추상 클래스 Abstract를 구현하는 익명의 내부 클래스 -참조 변수 X
        new Abstract() {
            @Override
            public void method() {
                System.out.println("추상 클래스를 구현하는 익명의 내부 클래스로 구현");
            }
        }.method();

        //인터페이스 Interface를 구현하는 익명의 내부 클래스 - 참조 변수 X
        new Interface() {
            @Override
            public void method() {
                System.out.println("인터페이스를 구현하는 익명의 내부 클래스로 구현");
            }
        }.method();

        //인터페이스 Interface를 람다식으로 구현
        Interface interLambda = () -> {
            System.out.println("Interface를 람다식으로 구현");
        };
        interLambda.method();


        // Member m = new Member();
        Outer o = new Outer();
        Outer.Member m = o.new Member();

        m.method();
        Outer.Static.method();
    }
}
