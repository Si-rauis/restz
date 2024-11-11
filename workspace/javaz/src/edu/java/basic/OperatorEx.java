package edu.java.basic;

public class OperatorEx {
    public static void main (String[] args){
        System.out.println("----- PIZZA MENU -----");
        System.out.println(" medium size : 10000원");
        System.out.println("  large size : 20000원");
        System.out.println("----------------------");
        //가성비 피자 주문 ---
        //- 미디엄 피자 반지름 : 20cm
        //- 라지 피자 반지름 : 40cm
        //- 예산 20000원으로 어느것을 주문해야 가성비??

        double mimRadius = 20;
        double larRadius = 40;
        double mediumArea = area(mimRadius);
        double largeArea = area(larRadius);
        String costLow = mediumArea * 2 > largeArea ? "미디움" : "라지";
        System.out.println("예산 20000원");
        System.out.println("미디엄 피자 2판 면적 : " + (mediumArea * 2));
        System.out.println("라지 피자 1판 면적   : " + largeArea);
        System.out.println("가성비 피자 : "+ costLow);

    }

    public static double area (double radius){
        double PI = 3.14;
        return (radius * radius) * PI;
    }
}
