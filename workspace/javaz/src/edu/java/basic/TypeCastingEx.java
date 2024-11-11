package edu.java.basic;

import java.util.Scanner;

public class TypeCastingEx {
    //변수 ch를 선언하며 임의의 알파벳 문자 하나로 초기화
    //해당 문자의 아스키코드를 다음과 같은 형태로 출력
    //
    //  문자 ~의 아스키코드 : ~~
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        char ch = scan.next().charAt(1);

        System.out.println("문자 " + ch + "의 아스키코드 : " + (int)ch);

        //변수 code를 선언하고
        //선언된 변수를 33 ~ 90 사이의 정수 중 하나로 초기화
        //해당 문자의 아스키코드를 출력

        int code;
        code = 66;
        System.out.println("아스키코드 " + code + " 의 문자는 " + (char)code);

        /*
        char 타입 Scanner 입력받기
        String str = scan.next();
        char ch = str.charAt(0); -> 0은 문자열의 0번째에 있는 문자를 ch에 초기화

        //아스키코드 입력방법
        char타입을 int로 형변환을 시켜서 출력
         = (int)ch

         범위를 초과하는 것 -> Overflow
         */
    }
}
