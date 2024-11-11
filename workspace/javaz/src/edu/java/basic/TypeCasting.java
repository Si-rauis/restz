package edu.java.basic;

public class TypeCasting {
    public static void main(String[] args) {
        byte one = 1;
        byte two = 2;
        byte sam = 1 + 2;       // 값 연산은 0
            //sam = one + two; // 자료형끼리의 연산에 주의
        sam = (byte)(one + two); //강제 형변환

        int hanaDul = one + two; //자동 형변환

        int oneMil = 1000000;
        int twoMil = 2000000;
        int intTril = (int)(oneMil * twoMil); //overflow
            intTril = 1000000 * 2000000;
        //백만 * 이백만 연산의 결과를 저장할 수 있는 변수를 선언하고
        //값을 저장한 후
        //화면에 표시


        long logTril = 1000000L * 2000000L;
             logTril = oneMil * twoMil; //int * int 연산으로 값 손실 상태의 결과를 대입
             logTril = (long)oneMil * twoMil; //강제형변환을 시켜서 자동 형변환을 유도 longTril = long * int >>> long * long
             logTril = oneMil * 2000000L; //자동형변환 longTril = int * long >>> long * long
            
        System.out.println("intTril = " + intTril);
        System.out.println("logTril = " + logTril);
    }
}

//변수에 값을 대입할 때
//피연산자의 타입이 같아야 연산 가능
//- 연산 전에 피연산자들의 타입을 일치시킴
//- 자료형끼리의 연산에 주의

//자동 형변환 up - casting / promotion
//- JVM 내부에서 자동 수행
//- 4byte 이하는 int형으로 변환
// byte, short, char >>> int >>> long >>> float >>> double
//- 데이터 타입이 다른 경우
//  표현 범위가 넓은 쪽에 맞춰 변환된 후 연산 수행
// int + float >>> float + float >>> float
// int + long >>> long + long >>> long


//강제 형변환 down-casting / demotion
//- 범위가 큰 자료형을 작은 자료형으로 변환
//- 값 손실 발생 가능 : 소수점 이하 버려짐
//
//(변환하려는 데이터타입) 변수이름 | 리터럴