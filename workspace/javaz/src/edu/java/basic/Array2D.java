package edu.java.basic;

public class Array2D {
    public static void main(String[] args) {
        //2차원 배열 선언
        int[][] score;
        char grade [][];

        //선언된 배열의 생성 - 길이 지정
        score = new int[3][3];  //[행의 길이] [열의 길이]
        grade = new char[2][];  //[고정 길이] [가변 길이]

        //배열의 초기화
        //첫번째 행
        score[0][0] = 99;
        score[0][1] = 88;
        score[0][2] = 77;

        //두번째 행
        score[1][0] = 66;
        score[1][1] = 55;
        score[1][2] = 44;

        //가변길이 배열 초기화
        grade[0] = new char[2]; //첫번째 행의 길이
        grade[1] = new char[4]; //두번째 행의 길이

        grade[0][0] = 'H';
        grade[0][1] = 'i';
        grade[1][0] = 'J';
        grade[1][1] = 'A';
        grade[1][2] = 'V';
        grade[1][3] = 'A';

        System.out.println(grade[0]);
        System.out.println(grade[1]);

        //배열의 선언 및 생성
        boolean[][] pass = new boolean[3][2];
        double[][] incentive = new double[][] {
                { 0.1, 0.2, 0.3 },
                { 0.4, 0.5, 0.6 }
        };

        String[][] user = {
                {"Kim", "Lee", "Han"},
                {"Ann", "Ben", "Gee"}
        };

        System.out.println(user[0]);
        System.out.println(user[1]);

        System.out.println(user[0].length);

        System.out.println("users 배열 값 - for");
        for(int i = 0; i < user.length; i++){
            for(int j = 0; j < user[i].length; j++){
                System.out.print(user[i][j] + "\t");
            }
            System.out.println();
        }


        System.out.println("users 배열 값 - foreach");
            for (String[] users : user) {
                for(String u : users){
                    System.out.print(u + "\t");
                }
                System.out.println();
            }
    }
}
