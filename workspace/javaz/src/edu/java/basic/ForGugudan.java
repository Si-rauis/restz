package edu.java.basic;

public class ForGugudan {
    public static void main(String[] args) {

        //1. 구구단 2단 ~ 9단 세로 출력
        // 2 * 1 = 2
        // 2 * 2 = 4
        //    *

        for (int i = 2; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(i + " * " + j + " = " + i * j);
                System.out.println();
            }
            System.out.println();
        }

        //2. 구구단 2단 ~ 9단 가로 2블럭으로 출력
        // 2 * 1 = 2             3 * 1 = 3              4 * 1 = 3          5 * 1 = 5
        // 2 * 2 = 4             3 * 2 = 6              4 * 2 = 8          5 * 2 = 10
        //   *                     *                      *                  *

        for (int a = 1; a <= 2; a++) {
            if (a == 1) {
                for (int i = 1; i <= 9; i++) {
                    for (int j = 2; j <= 5; j++) {
                        System.out.print(j + " * " + i + " = " + i * j + "\t");
                    }
                    System.out.println();
                }
            } else {
                for (int i = 1; i <= 9; i++) {
                    for (int j = 6; j <= 9; j++) {
                        System.out.print(j + " * " + i + " = " + i * j + "\t");
                    }
                    System.out.println();
                }
            }
        }
    }
}
