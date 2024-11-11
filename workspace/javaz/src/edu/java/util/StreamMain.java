package edu.java.util;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamMain {
    public static void main(String[] args) {
        int[] arr0to4 = {0, 1, 2, 3, 4};
        System.out.println(Arrays.toString(arr0to4));

        //IntStream is = IntStream.range(0, 4);
        IntStream is = IntStream.rangeClosed(0, 4);

        is.forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
