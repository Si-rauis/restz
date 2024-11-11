package edu.java.basic;

public class While {
    public static void main(String[] args) {

        char yesNo = 'n';

        do{
            System.out.println(">> 게임을 시작합니다");
            System.out.println(">> 게임이 끝났습니다.");
            System.out.println(">> 계속하시겠습니까? " + yesNo);
        } while( yesNo == 'y' );


        System.out.println("COUNT DOWN !!!");

        //10
        //9
        // ...
        //fire!!!
        int i = 10;
        while(true){
            System.out.println(i);
            i -= 1;
            if(i == 1){
                System.out.println("fire!!!");
                break;
            }
        }

    }
}
