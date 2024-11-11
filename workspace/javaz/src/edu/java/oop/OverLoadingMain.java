package edu.java.oop;

public class OverLoadingMain {
    public static void main(String[] args) {
        //1.OverLoading 클래스의 객체 saa를 생성
        //   단 타입은 "정사각형"으로 지정
        //2.saa를 이용하여 넓이 구하기를 출력하는 메서드 호출

        double result = 0;
        int[] ary = {1, 4, 6, 8 ,5};

        OverLoading saa = new OverLoading("정사각형");
        OverLoading won = new OverLoading("원");
        OverLoading sam = new OverLoading("삼각형");
        saa.area();
        saa.area(5);
        System.out.println("반지름이 5인 원의 넓이");
        result = won.area(5.0);
        System.out.println(result);
        System.out.println("밑변 5, 높이 5인 삼각형의 넓이");
        result = sam.area(5,5);
        System.out.println(result);

        System.out.println();
        //sam.sumAll();         //static으로 선언된 메서드는
        OverLoading.sumAll();   //객체를 이용하지 않고 사용 가능
        OverLoading.sumAll(1,5);
        OverLoading.sumAll(1,5,7);
        System.out.println(OverLoading.sumAll(ary));

    }
}
