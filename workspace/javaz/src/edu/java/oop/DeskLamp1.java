package edu.java.oop;

public class DeskLamp1 {
    final int VOLT; //생성자가 없으면 초기화 필수
    private boolean power;
    private int brightness;

    DeskLamp1(int VOLT){
        this.VOLT = VOLT;
    }
}
