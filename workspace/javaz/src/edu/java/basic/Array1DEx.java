package edu.java.basic;

public class Array1DEx {
    public static void main(String[] args) {

        System.out.println(" ===== TEST SCORE ===== ");
        System.out.println("\tNo.\t name\tscore");
        System.out.println("----------------------------------------");

        String[] name = {"Kim" , "Lee", "Han", "Ann", "Ben"};
        int[] score = { 99, 88, 77, 66, 55};
        double avg;
        int sum = 0;
        for (int i = 0; i < 5; i++){
            System.out.println( i + "\t" + name[i] + "\t" + score[i]);
            sum += score[i];
        }
        avg = sum / 5.0;
        System.out.println("----------------------------------------");
        System.out.println("총점 : " + sum);
        System.out.println("평균 : " + avg);

    }
}
