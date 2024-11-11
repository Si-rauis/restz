package edu.java.basic;

public class For {
    public static void main(String[] args) {

        System.out.println("2 ~ 10 사이의 2의 배수 - if문을 이용 O");
        for(int i = 2 ; i <= 10; i++){
            if( i % 2 == 0) {
                System.out.println(i);
            }
        }

        System.out.println("2 ~ 10 사이의 2의 배수 - if문을 이용 X");
        for(int i = 2 ; i <= 10; i = i + 2){
            System.out.println(i);
        }

        System.out.println("1 ~ 10 사이의 모든 정수의 합계");
        int sum = 0;
        for(int i = 1 ; i <= 10; i++){
            sum += i;
        }
        System.out.println(sum);

        System.out.println("-----------------------------");
        System.out.println("구구단 2단 출력");
        for(int i = 1; i <= 9; i++){
            System.out.println(" 2 * " + i + " = " + 2 * i );
        }

        System.out.println("-----------------------------");
        System.out.println("중첩 for문");
        System.out.println("1 : *");
        System.out.println("2 : **");
        System.out.println("3 : ***");
        System.out.println("4 : ****");
        System.out.println("5 : *****");
        System.out.println();
        System.out.println("1 : 1");
        System.out.println("2 : 12");
        System.out.println("3 : 123");
        System.out.println("4 : 1234");
        System.out.println("5 : 12345");

        //직각삼각형
        for(int i = 1; i <= 5; i++){
            System.out.print( i + " : ");
            for(int j = 1; j <= i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
        //삼각형
        for(int i = 1; i <= 5; i++){
            System.out.print( i + " : ");
            for(int a = 5; a > i; a--){
                System.out.print(" ");
            }
            for(int j = 2; j <= 2 * i; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        //A B C D E F
        //G H I J K L
        //M N O P Q R
        //S T U V W X
        //Y Z [ \ ] ^

        int count = 0;
        for(int i = 'A'; i <= 'Z'; i++){
            char alpha = (char)i;
            System.out.print(alpha);
            count++;
            if(count % 6 == 0){
                System.out.println();
            }
        }
    }//main
}//Class
