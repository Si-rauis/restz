package edu.java.util;

import java.util.*;
import java.util.function.Consumer;

public class ListMain {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        ArrayList<Object> arrList = new ArrayList<>();

        System.out.println(list.size());

        list.add('1');  list.add(2);    list.add(true);
        list.add("넷"); list.add(3.0); list.add(true);
        System.out.println(list.size());
        System.out.println(list);

        System.out.println("List의 1번 인덱스의 데이터 : " + list.get(1));
        System.out.println("List의 1번 인덱스의 데이터를 \"삼쩜영\"으로 변경");
        list.set(1,"삼쩜영");
        System.out.println(list);

        System.out.println();
        System.out.println("1. for문을 이용하여 list의 값 출력");
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("2. foreach문을 이용하여 list의 값 출력");
        for (Object o : list) {
            System.out.print(o + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("3. Iterator를 이용하여 list의 값 출력");
        Iterator<Object> itr = list.iterator();
        while(itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("4. Consumer 인터페이스를 이용하여 list의 값 출력");
        Consumer<Object> consumer = new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.print(o + " ");
            }
        };
        list.forEach(consumer);

        System.out.println();
        System.out.println();

        System.out.println("5. Consumer 인터페이스를 이용하여 list의 값 출력 - 람다식으로");
        list.forEach( o -> System.out.print(o + " "));
        System.out.println();

        //////////////////////////////////////////
        System.out.println("배열 ----> ArrayList로 ");
        String[] animals = {"dog" , "cat", "ant"};
        List<String> animalList = Arrays.asList(animals);

        System.out.println(Arrays.toString(animals));
        System.out.println(animalList);
        System.out.println();

        animalList.set(1,"bee");
        System.out.println("List ---> 배열로 ");
        animals = animalList.toArray( new String[0]);
        //animals = animalList; X
        animals = animalList.toArray(String[]::new);

        System.out.println(Arrays.toString(animals));
        System.out.println(animalList);
        System.out.println();

//      animalList.add("cow");    //배열을 List로 담은 경우 예외 발생

    }
}
