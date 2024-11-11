package edu.java.basic;

public class Switch {
    public static void main(String[] args) {
        System.out.println("-- PIZZA MENU --");
        String size = "Large"; //small | medium | larage
        int price = 0;          //만원   | 이만원  | 삼만원

        //size에 따른 가격을 price에 저장
        switch (size){
            case "small"  : price = 10000;
                break;
            case "medium" : price = 20000;
                break;
            case "larage" : price = 30000;
                break;
            default :
                System.out.println("피자 사이즈 선택 오류!!");
                System.out.println("small, medium, large 중 하나를 선택해주세요.");
                // return; //실행을 중단하고 호출한 쪽으로 제어를 넘김
        }

        System.out.println("size \t: " + size);
        System.out.println("price \t: " + price);

        System.out.println("-- 특정 연월의 마지막 날 알아보기 - 윤년 반영");
        int year = 2024;
        int month = 2;
        int lastData = 0;
        switch (month) {
            case 1 , 3 , 5, 7, 9, 11 : lastData = 31;
                break;
            case 2 :
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    lastData = 28;
            } else {
                lastData = 29;
            } break;
            case 4 , 6, 8, 10, 12: lastData = 31;
                break;
        };

        System.out.println(lastData);



    }
}
