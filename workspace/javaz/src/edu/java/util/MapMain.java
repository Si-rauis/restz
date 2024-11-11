package edu.java.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MapMain {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(21, "부산");
        map.put(31, "경기");
        map.put(42, "대전");
        map.put(64, "제주");
        map.put(64, "제제주");
        System.out.println(map);
        System.out.println(map.get(31));
        System.out.println();

        System.out.println("keySet() 을 이용하여 map의 값 출력");
        //1.keySet()을 호출하여 반환되는 값을 임의의 변수에 저장
        Set<Integer> key = map.keySet();
        for (Integer i : key) {
            System.out.println(i + " : " + map.get(i));
        }

        System.out.println();
        System.out.println("Iterator를 이용하여 map의 값 출력 - while");
        Iterator<Integer> ite = map.keySet().iterator();
        while(ite.hasNext()){
            int key1 = ite.next();
            System.out.print(map.get(key1) + " ");
        }
        System.out.println();
        System.out.println();

        //2.foreach문을 이용하여 map의 값 출력
        System.out.println("5. BiConsumer 인터페이스를 이용하여 Map의 값 출력 - 람다식으로");
       map.forEach((key1, value) -> {
           System.out.println(key1 + " : " + value);
       });
    }
}
