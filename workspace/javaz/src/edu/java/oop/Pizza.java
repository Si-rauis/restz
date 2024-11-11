package edu.java.oop;

public class Pizza {
    private int radius;
    String name;

    //모든 필드를 매개변수로 받아서 초기화하는 생성자 //Alt + Insert 단축 메뉴 -> constructor
    Pizza(int radius, String name){
        this.radius = radius;
        this.name = name;
    }

    //Pizza 객체 두 개를 매개변수로 받아서 둘 중 radius가 더 큰 개체를 반환
    public static Pizza getLargePizza(Pizza num1, Pizza num2){
        if( num1.radius > num2.radius ){
            return num1;
        } else {
            return num2;
        }
    }

   public static void makeLargePizza(Pizza num1,int radius){
        if(num1.radius < 20){
            num1.radius = radius;
        }
    }

    public void changeRadius(int radius){
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
