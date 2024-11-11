package edu.java.oop;

public class CarExtendsMain {
    public static void main(String[] args) {

        ElectricCar ec = new ElectricCar();
        ec.color = "black";
        ec.price = 10000;
        ec.setMaker("KIA");
        System.out.println(ec);



    }
}
