package edu.java.lang;

import java.util.Random;

public class MathLotto {
    public static void main(String[] args) {

        //1. 1 ~ 45 사이의 중복되지 않는 정수형 난수 6개를 추출하여
        //   오름차순 정렬하여 lottoNum 배열에 저장
        int[] lotto = new int[6];

        for (int i = 0; i < lotto.length;i++) {
            Random random = new Random();
            lotto[i] = random.nextInt(46);
        }

        for (int i = 0; i < lotto.length; i++) {
            for (int j = lotto.length - 1; j > i; j--) {
                if (lotto[i] > lotto[j]) {
                    int change = lotto[i];
                    lotto[i] = lotto[j];
                    lotto[j] = change;
                }
            }
        }

            //2. 로또 당첨 번호 저장
            //   lottoWin 배열에 임의의 다첨 번호 6개를 오름차순으로 저장
            int[] lottoWin = {5, 10, 11, 17, 28, 34};

            //3. 보너스 번호는 bounsNum 변수에 임의의 값으로 저장
            int bounsNum = 22;

            //4. 당첨 결과 출력
            System.out.println("당첨 번호: 5 10 11 17 28 34 + 22");
            System.out.print("나의 번호: ");
            lottoNum(lotto);
            System.out.println("---------------------------------");
            System.out.println("당첨 결과 : ");


        }//메인
    public static void lottoNum(int[] lotto) {
        for (int lotto1 : lotto) {
            System.out.print(lotto1 + " ");
        }
        ;
        System.out.println();
    }

    public static void lotto(int[] lottowin, int[] lotto, int bounsNum) {
        int count = 0;
        for(int i = 0;i < lotto.length;i++){
            for(int j = 0; j < lottowin.length;j++){
                if(lotto[i] == lottowin[j]){
                    count++;
                }
            }
        }
        for(int i = 0; i < lotto.length; i++){
            //if()
        }
        System.out.println();
    }


    } //클래스

