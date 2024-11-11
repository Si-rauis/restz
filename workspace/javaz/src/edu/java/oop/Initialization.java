package edu.java.oop;

public class Initialization {
    int instanceField;
    static int staticField;

    {System.out.println(" instance init block ");}

    static {System.out.println(" static init block ");}

    public Initialization() {System.out.println(" constructor block ");}


    public static void main(String[] args) {
        //staticField 값을 화면에 출력
        System.out.println(staticField);

        //instanceField 값을 화면에 출력
        Initialization init = new Initialization();
        System.out.println(init.instanceField);



        System.out.println(" main() start ");

        new Initialization(); //기본 생성자 호출              - 참조변수 X
//      Initialization init = new Initialization();         - 참조 변수 O

        System.out.println(" main() end ");
    }
}
