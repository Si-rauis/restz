package edu.java.basic;

public class BreakContinue {
    public static void main(String[] args) {
        int maxSeven = 0;

        System.out.println("1. 1 ~ 100 사이의 모든 7의 배수 ");

        for(int i = 1; i <= 100; i++){
            if(i % 7 == 0) {
                System.out.print(i + "\t");
            }
        }
        System.out.println("\n");

        System.out.println("2. 1 ~ 100 사이의 모든 7의 배수");
        System.out.println("   단, 3의 공배수는 제외");
        for(int i = 1; i <= 100; i++){
            if(i % 7 == 0) {
                if (i % 3 != 0) {
                    System.out.print(i + "\t");
                }
            }
        }
        System.out.println("\n");


        System.out.println("1. 1 ~ 100 사이에서 가장 큰 7의 배수");
        for(int i = 1; i <= 100; i++){
            if(i % 7 == 0) {
                maxSeven = i;
            }
        }
        System.out.println(maxSeven);
        System.out.println("\n");


        System.out.println();
        System.out.println("중첩 반복문 중단");
        for(int i = 5; i >= 1; i--){
            //i가 3이 되면 반복 중단
            if(i == 3){
                break;
            }
            for(int j = 1; j <= i; j++){
                System.out.print("ㅁ");
            }
            System.out.println();
        }

    } //main
} //class
