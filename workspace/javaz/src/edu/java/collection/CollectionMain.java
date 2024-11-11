package edu.java.collection;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Consumer;

public class CollectionMain {
    public static void main(String[] args) {
        new MyCollection<>(Arrays.asList(1, 2, 3, 4, 5))
                .foreach(integer -> System.out.println(integer));
    }
}
