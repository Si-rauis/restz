package edu.java.basic;

public class Operator {
    public static void main(String[] args) {
        //부호 연산자
        //+ 피연산자에 1 곱하기
        //- 피연산자에 -1  "

        System.out.println("부호 연산자----------------------------");

        int buho = 3;
        System.out.println(buho);
        buho = -buho;
        System.out.println(buho);
        buho = +buho;
        System.out.println(buho);
        buho = -buho;
        System.out.println(buho);

        System.out.println("산술 연산자----------------------------");
        int divide = 7 + 6 - 5 * 4 / 3;
        int modulo = 7 + 6 - 5 * 4 % 3;
        System.out.println(divide);
        System.out.println(modulo);

    //    System.out.println(divide / 0); //ArithmeticException 예외 발생
        System.out.println(divide / 0.0); //Infinity 출력
        System.out.println(modulo % 0.0); //NaN; Not a Number 출력

        int time = 3695; //초
        //time 변수에 저장된 초
        int sec = time % 60;
        int min = (time / 60) % 60;
        int hour  = (time / 60) / 60;
        System.out.println(time + "초는 "
                + hour + "시간 "
                + min + "분 "
                + sec + "초 ");


        //증감 연산자
        int year = 2024;
        System.out.println("last year : " + --year);
        System.out.println("this year : " + ++year);
        System.out.println("still year : " + year--);
        System.out.println("year : " + year);
        System.out.println("back year : " + year++);
        System.out.println("year : " + year);

        //대입 연산자
        int i = 1;
        System.out.println(i);
        i = i + 1; // -> i += 1;
        System.out.println(i);
        i += 1;
        System.out.println(i);
        i++;
        System.out.println(i);

        i *= 1;
        System.out.println(i);
        i -= 1;
        System.out.println(i);
        i /= 1;
        System.out.println(i);
        i %= 1;
        System.out.println(i);

        //문자 x를 임의의 변수에 저장하고,
        //x가 소문자이면 lower 변수에 true, 그렇지 않으면 false를 저장
        //x가 대문자이면 upper 변수에 true, 그렇지 않으면 false를 저장
        //x가 알파벳이면 alpha 변수에 true, 그렇지 않으면 false를 저장

        char text = 'x';
        boolean upper = (int)text >= 65 && (int)text <=90; //ASCII 값을 직접 사용하지 않아도 O
                                                           // text >= 'a' && text <= 'z';
        boolean lower = (int)text >= 97 && (int)text <=122;
        boolean alpha = upper || lower ;


        System.out.println("입력 문자 : " + text);
        System.out.println("소문자 ? : " + lower);
        System.out.println("대문자 ? : " + upper);
        System.out.println("알파벳 ? : " + alpha);

        //x의 값이 홀수인지 짝수인지 비교한 결과를
        //result 변수에 저장하고 화면에 표시
        int x = 5;
        String result = x % 2 == 0 ? "짝수" : "홀수";
        System.out.println(result);


        year = 2023;

        //result 변수에 윤년이면 true, 아니면 false를 저장
        //임의의 문자열 변수에 윤년이면 "윤년 0"를
        //아니면 "윤년 X"를 저장하고 화면에 표시

        result = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ? "윤년 O" : "윤년 X";
        System.out.println(result);



    }
}

//Operator 연산자
//- 특정 연산을 나타내는 기호
//- 동일 우선순위에서는 왼쪽에서 오른쪽으로 연산 진행
//- 괄호 ( )를 이용하여 우선순위 조절 가능 \

//증감 연산자 ++, --
//산술 연산자 +, -, *, /, %(나머지)
//시프트 연산자 >>, <<, >>>
//비교 연산자 >, <, >=, <=, ==, !=
//비트 연산자 &, |, ^, ~
//논리 연산자 &&, ||, ~
//조건(삼항) 연산자  (조건) ? 조건이참인경우 : 조건이거짓인경우
//대입 연산자 +, *=, /=, %=, +=, -=
