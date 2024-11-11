package edu.java.oop;

public class DeskLamp {

    private boolean power;
    private int brightness;

    final int VOLT = 110;  //필드에 상수를 쓸때 항상 초기화

    public DeskLamp (boolean power, int brightness){
        this.power = power;
        this.brightness = brightness;
    }

    void powerOn(){
        this.power = true;
        System.out.println("램프 ON");
    }
    void powerOff(){
        this.power = false;
        System.out.println("램프 Off");
        this.brightness = 0;
    }

    void setBrightness(int brightness){
        this.brightness = brightness;
        System.out.println("밝기가 " + brightness + "로 바꼇습니다.");
    }

    int getBrightness(){
        return this.brightness;
    }

    public String toString(){
        return "DeskLamp 파워 :\t" + power +
                "\n밝기 :\t" + brightness;
    }

}
