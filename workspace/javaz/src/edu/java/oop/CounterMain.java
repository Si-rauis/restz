package edu.java.oop;

import java.util.Scanner;

class Counter{

    private final String instanceName;
    private static int instanceNum = 0;

    Counter(String name){
        this.instanceName = name;
        Counter.instanceNum++;
        System.out.println("생성된 인스턴스의 이름 : " + instanceName + ", 누적 인스턴스의 개수: " + Counter.instanceNum);
    }
}

//////////////////////////////////////////////////////////

public class CounterMain {
    public static void main(String[] args) {
        Counter c1 = new Counter("1");
        Counter c2 = new Counter("2");
        new Counter("3");
    }
}


