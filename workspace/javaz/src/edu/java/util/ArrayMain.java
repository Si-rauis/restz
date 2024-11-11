package edu.java.util;

import java.util.Arrays;
import java.util.Collections;

public class ArrayMain {
    public static void main(String[] args) {

        Integer[] lottoWin =  {20, 3, 9, 6, 33, 7};

        //배열의 내용 출력
        System.out.println(Arrays.toString(lottoWin));

        //배열의 정렬
        Arrays.sort(lottoWin);
        System.out.println(Arrays.toString(lottoWin));

        Arrays.sort(lottoWin, Collections.reverseOrder());
        System.out.println(Arrays.toString(lottoWin));

    }
}

