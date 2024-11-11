package edu.java.oop;

//클래스
public class Car {
    //속성, 상태, property, field, 멤버 변수
    public String color;        //어디서든 사용가능
    protected  boolean sunroof; //package가 같을 때 사용가능
    int price;                  //package가 같을 때 사용가능
    private String maker;       //클래스 내부에서만 사용가능


    public Car() {
    }

    //생성자
    public Car(String color, boolean sunroof, int price, String maker){
        this.color = color;
        this.sunroof = sunroof;
        this.price = price;
        this.maker = maker;
        System.out.println("자동차가 생성되었습니다.");
    }

    //기능, 동작, behavior, method

    public void start(){
        System.out.println(">자동차가 출발합니다.");
    }

    public void stop(){
        System.out.println(">자동차가 멈춥니다.");
    }

    public void dirve(){
        System.out.println(">자동차를 운전 중입니다.");
    }

     public void printCar(){
        System.out.println(this.color);
        System.out.println(this.sunroof);
        System.out.println(this.price);
    }

    public void setMaker(String maker){
        this.maker = maker;
    }
    public String getMaker(){
        return maker;
    }






}
