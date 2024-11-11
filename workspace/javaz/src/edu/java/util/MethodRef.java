package edu.java.util;

import java.util.function.IntBinaryOperator;
import java.util.function.ToIntFunction;

public class MethodRef {
    public static void main(String[] args) {
        System.out.println(Math.max(1,2));

        System.out.println("1. 정적 메서드 참조");
        IntBinaryOperator ibo = (x,y) -> Math.max(x,y);
                          ibo = Math ::  max;
        System.out.println("3 vs 4 : " + ibo.applyAsInt(3,4) );



        ToIntFunction<String> tif = (x) -> Integer.parseInt(x);
                              tif = Integer :: parseInt;
        System.out.println("123 : " + tif.applyAsInt("123"));

    }
}

class Calculator {
    public Calculator(){
        System.out.println();
    }
}