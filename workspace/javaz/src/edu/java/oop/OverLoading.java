package edu.java.oop;

public class OverLoading {
    //원주율 값을 3.14로 초기화하는 상수 PI - 접근 제한X
    //문자열을 저장하는 변수 type - 같은 클래스 내에서만 접근 가능
    static final double PI = 3.14;
    private String type;

    //type의 값을 매개변수로 받아서 초기화하는 생성자
    OverLoading (String type){
        this.type = type;
    }

    public void area(){
        System.out.println("--- 넓이 구하기 ---");
    }

    //메서드 오버로딩
    //정사작형의 길이를 매개변수로 받아서 넓이를 계산후 출력
    public void area(int length){
        int area =  0;
        System.out.println("--- 넓이 구하기 ---");
        area = length * length;
        System.out.println("넓이 :" + area);
    }

    //원의 넓이를 계산하여 출력
    public double area(double radius){
        return radius * radius * PI;
    }
    public double area(int length , int height){
        return length * height * 0.5;
    }

    ///////////////////////////////////////////////////
    //var args
    public static void sumAll(){
        System.out.println("-- 모두 더하기 var args --");

    }

    //정수 2개를 매개변수로 받아서 더한 후 반환하는 공유 메서드 sumAll( )
    public static void sumAll(int x, int y){
        System.out.println(x + " + " + y + " = " + (x + y));
    }

    //정수 3개를 매개변수로 받아서 더한 후 반환하는 공유 메서드 sumAll( )
    public static void sumAll(int x, int y, int z){
        System.out.println(x + " + " + y + " + " + z + " = " + (x + y + z));
    }

    //정수 배열을 매개변수로 받아서 더한 후 반환하는 공유 메서드 sumAll( )
    public static int sumAll(int[] x){
        int total = 0;
        for(int i = 0; i < x.length; i++){
            total += x[i];
        }
        return total;
    }



    //현재 객체를 "종류 : ~~~, 원주율 : ~~~"의 문자열 형태로 반환하는 메서드
    public String toString (){
        return "종류 : " + type + ", 원주율 : " + PI;
    }
}
