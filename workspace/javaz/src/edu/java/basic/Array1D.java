package edu.java.basic;

public class Array1D {
    public static void main(String[] args) {

        //1차원 배열의 선언
        int[] scores;
        char grades[];

        //선언된 배열의 생성 - 크기를 지정 - 기본 값으로 자동 초기화
        scores = new int[3];    //길이가 3인 정수 배열
        grades = new char[3];   //길이가 3인 문자 배열

        System.out.println("socres 배열의 길이 : " + scores.length);
        System.out.println("scores 배열의 값");
        System.out.println(scores[0]);
        System.out.println(scores[1]);
        System.out.println(scores[2]);
        System.out.println("scores 배열");
        System.out.println(scores);  //배열의 주소값

        System.out.println("----------------------------------------");

        //배열의 선언 및 생성 - 크기 지정
        boolean[] pass = new boolean[3];
        System.out.println("pass 배열의 값");
        System.out.println(pass[0]);
        System.out.println(pass[1]);
        System.out.println(pass[2]);

        //배열의 선언 , 생성 및 초기화
        double[] incentive = new double[] {0.1, 0.2, 0.3};

        //배열의 선언 , 생성 및 초기화
        String[] users = {"Kim" , "Lee", "Han"};

        System.out.println("incentive 배열의 길이 : \t" + incentive.length);
        System.out.println("users 배열의 길이 \t: \t" + users.length);

        //기존 배열을 재초기화
        // users = {"Hoo", "Moo", "Soo"};               //X
        users = new String[] {"Hoo", "Moo", "Soo"};     //O

        System.out.println("Users 배열의 값 ");
        System.out.println(users[0]);
        System.out.println(users[1]);
        System.out.println(users[2]);

        System.out.println("users 배열의 값 - for");
        for(int i = 0; i <= 2; i++){
            System.out.println("users [" + i + "]의 값 " + users[i]);
        }

        System.out.println("----------------------------------------");

        System.out.println("users 배열의 값 - foreach");
        for(String user : users){
            System.out.println(user);
        }



    }
}
