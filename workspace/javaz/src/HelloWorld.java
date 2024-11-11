/*
            COMMENT
            여러 줄 주석 처리
            컴파일러가 무시
         */
//한줄 주석
public class HelloWorld {
    public static void main(String[] args){
        System.out.print("HelloWorld!\n");
        System.out.print("HelloWorld!\n");
    }
}

class Aclass { }

//identifiers 식별자
//클래스, 매서드, 변수, 상수 등의 이름
//대소문자 구분
//영문자, 숫자, _, $, 한글(권장X)
//숫자 시작 X
//키워드 사용 X

//Class
//-자바 프로그램 실행 단위
//-하나의 소스 파일에 여러 개의 클래스 작성 가능
//-public class는 1개만, 소스 파일명과 같아야함
//-명명규칙
//주로 명사로 작성
// 각 단어의 첫글자 대문자, 나머지는 소문자로 표시 -> 파스칼 표기법

//method
//-특정 기능을 수행하는 코드들
//-중괄호 블럭 { } 내부에 작성
//-각 실행문의 마지막에 세미콜론 ; 명시
//-자바 프로그램의 실행 시에는 main() 필요
//명명규칙
//주로 동사로 작성
//첫 단어는 모두 소문자, 이후 단어들의 첫 글자는 대문자 표기(camel case)


//statement 실행문 수행문
//-특정 기능을 수행하는 문장
//-기본적으로 위에서 아래로 순차 실행

//variable 변수
//변하는 값을 저장하는 메모리 상의 공간
//명명규칙
//주로 명사로 작성
//첫 단어는 모두 소문자, 이후 단어들의 첫 글자는 대문자 표기(camel case)

//constant 상수
//변하지 않는 값을 저장하는 메모리 상의 공간
//명명 규칙
//주로 명사로 작성
//모두 대문자로 표시, 각 단어는 _로 구분(Snacke case)
//예시) MAX_SIZE