package edu.java.basic;

public class Variable {
//   Ctrl + / -> 주석 토글 단축기
    public static void main(String[] args){
        //3.14를 저장하는 상수 PI
        final double PI = 3.14;
       // PI = 3.1444444444444444444444444444444; 상수는 변경 불가

        var guess = 1;
            /* guess ="일"; 이렇게는 불가능
               var str; 값 미지정 X       */

        //동일 타입의 변수 선언 및 초기화
        int aa = 1, bb = 2, cc = 3;
        boolean tf;
        char c;
        //정수형 변수 선언 및 초기화
        byte b = 127;
        short s = -32768;
        int i = 2100000000;
        long l = 21000000000L;

        //실수형 변수 선언 및 초기화
        float f = 0.123123123F;
        double d = 0.12312312213;


        System.out.println("----------------------------");
        //선언된 변수 초기화
        tf = true;
        System.out.println(tf);
        tf = false;
        System.out.println(tf);
        tf = 1 > 2;
        System.out.println(tf);

        System.out.println("----------------------------");

        c = 'A';
        System.out.println(c);
        c = '\u0041';
        System.out.println(c);
        c = 65;
        System.out.println(c);

        System.out.println("----------------------------");

        System.out.println("b = " + b);
        System.out.println("s = " + s);
        System.out.println("i = " + i);
        System.out.println("l = " + l);

        System.out.println("----------------------------");
        System.out.println("f = " + f);
        System.out.println("d = " + d);
        
        //문자열 변수 선언 및 초기화
        System.out.println("----------------------------");
        String name = "Lee";
        //국어 점수 90을 변수  kor에 저장하고,
        //영어 점수 80을 변수 emg에 저장하고,
        //컴퓨터 점수 점수 75를 변수 com에 저장한 후
        //세 과목의 점수를 모두 합산하여 sum에 저장하고
        //다음처럼 출력
        //단, 이름은 김리로 출력되도록
        //변수  name의 값을 변경하여 처리

        name = "김리";
        int kor = 90;
        int eng = 80;
        int com = 75;
        int sum = total(kor,eng,com);
        int avg = sum / 3;
        double avgDou = sum / 3.0;

        System.out.println("이름 \t" + name);
        System.out.println("과목 \t" + "점수");
        System.out.println("국어 \t" + kor);
        System.out.println("영어 \t" + eng);
        System.out.println("컴퓨터 \t" + com);
        System.out.println("합계 \t" + sum);
        System.out.println("평균 \t" + avg);
        System.out.println("평균 \t" + avgDou);
    }
    public static int total(int num1, int num2, int num3){
        int total = 0;
        total = num1 + num2 + num3;
        return total;
    }
}
//variable 변수
//- 변하는 값을 저장하는 메모리 공간
//- 가장 마지막에 저장된 값을 기억
//- 변수 이름 : 메모리 공간(주소)에 붙인 이름
//
//- 중괄호 블럭{ } 내부에 선언하여 사용
//- 선언 : 변수의 종류에 따라 각기 다른 크기의 메모리 공간 확보
//- 선언된 블럭 내에서만 유효
//- 선언 후 초기화 또는 선언과 동시에 초기화하거나 초기화되어 사용
//- 초기화 : 변수의 값을 최초 저장하는 것

//변수의 종류
//- 인스턴스 변수 : 특정 객체가 소유하는 변수
//- 클래스 변수 : 모든 객체가 공유 static 하는 변수

//- 지역 변수 : 메소드 내부에 선언된 변수
//- 전역 변수 : 클래스의 멤버로 선언된 변수
//- 매개 변수 : 메소드 호출 시에 전달되는 값을 저장하는 변수
//             parameter, argument

//변수 선언                         상수 선언
//- 데이터타입 변수이름;              final 데이터타입 변수이름 = 초기화;
//                                 final 데이터타입 변수이름; -> 이후 생성자에서 초기화 가능

//변수 초기화
//- 선언된변수이름 = 리터럴 | 변수 | 연산 | 특정메서드의반환값;

//변수 선언과 초기화 동시
//- 데이터타입 변수이름 = 리터럴 | 변수 | 연산 | 특정메서드의반환값;

////////////////////////////////////////////////
//var 키워드
//- 저장되는 데이터에 따라 타입이 자동 결정

//데이터 타입 data type
//- 숫자, 문자, 논리값, 객체
//- 변수의 메모리 크기
//
//구분
//primitive type : 기본 자료형 8가지 - 숫자, 문자, 논리값
//                  - 숫자 - 정수 :  byte  1byte
//                                 short 2byte
//                                 int   4byte(기본)
//                                 long  8byte
//                        - 실수 : float  4byte
//                                double 8byte(기본)
//                  - 문자       : char   2byte
//                  - 논리값     : boolean 1byte
//
// ** long 초과 정수는 BigInteger 클래스
// ** 실수의 경우 부동소수점 방식 ->  정확한 계산 BigDecimal 클래스

//char
//- 2byte, 유니코드 -16진수 UTF-16규격
//- 한글, 영문
//- 내부적으로 부호없는 정수 형태로 저장
//- 0 ~ 127 ASCII CODE와 호환

//boolean
//- 1byte
//- true, false 값 저장


//reference type : 객체의 주소
//               - 문자열 String

//- \t을 사용하면 키보드로 Tab키를 누른것으로 인식