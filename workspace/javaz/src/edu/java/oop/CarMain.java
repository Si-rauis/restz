package edu.java.oop;

public class CarMain {
    public static void main(String[] args) {
        int x;              //정수형 변수 x 선언
        int[] i;            //정수형 배열 i 선언
        Car redCar = new Car("red", true, 10000 , "KIA");//클래스 참조 변수 선언

        // redCar.maker = "KIA"; //private 엑세스 불가능
        System.out.println(redCar.getMaker());
        redCar.printCar();

        //Car.printCar(redCar);

        //클래스 참조 변수 선언 및 객체 생성
        Car blueCar = new Car("blue", false, 5000,"maker");

        /////////////////////////////////////
        //1. 모든 필드의 값을 매개변수로 받아서 초기화하는 생성자를 이용하여
        // Car 클래스의 객체 greenCar 생성
        // 색상은 GREEN, 썬루프는 없음, 가격은 1000, 제조사는 르노
        //2. 생성된 객체를 화면으로 출력

        Car greenCar = new Car("green", false, 1000, "르노");
        greenCar.printCar();
    }
}
