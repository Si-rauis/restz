package edu.java.oop;

import java.util.Scanner;

public class DeskLampTest {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        DeskLamp lamp = new DeskLamp(true, 1);

        int bright;

        lamp.powerOn();
        lamp.powerOff();

        System.out.println("현재 밝기는 " + lamp.getBrightness());

        while (true) {
            bright = scan.nextInt();
            if (bright >= 1 && bright <= 3) {
                lamp.setBrightness(bright);
                break;
            } else {
                System.out.println("값을 다시 입력하세요");
            }

        }

        System.out.println(lamp.toString());

    }
}
