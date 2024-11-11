package edu.java.basic;

public class If {
    public static void main(String[] args){
        int jisu = 30;              //미세먼지 지수
        String status = "알수 없음"; //미세먼지 상태

        //0 ~ 50이면 좋음
        if( jisu <= 50 ){
            status = "좋음";
        }

        System.out.println("-- 통합대기환경지수 --");
        System.out.println("현재 : " + jisu);
        System.out.println("상태 : " + status);


        System.out.println("-- BMI와 비만 정도 출력 --");
        //키, 몸무게, BMI, 비만 정도
        //BMI 18.5 미만이면 저체중
        //BMI 18.5 ~ 24.9 정상
        //BMI 25 ~ 29.9 과체중
        //BMI 30 ~ 34.9 비만
        //BMI 35 ~ 고도비만
        //BMI = 몸무게 / (키 * 키)

        double height = 1.73;
        double weight = 69.5;
        double bmi = weight / (height * height);
        String result = "";
        if( bmi < 18.5 ){
            result = "저체중";
        } else if ( bmi < 24.5){
            result = "정상";
        }else if ( bmi < 30.0){
            result = "과체중";
        }else if ( bmi < 35.0){
            result = "비만";
        } else {
            result = "고도비만";
        }


        System.out.println("키 \t: " + height);
        System.out.println("몸무게 \t: " + weight);
        System.out.println("BMI \t: " + bmi);
        System.out.println("결과 \t: " + result);
    }
}
