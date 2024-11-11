package edu.java.util;

import java.util.*;

public class CollectionsMain {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("bee", "air", "sky", "Sea", "Ace");
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);

        Comparator<String> ascComp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        };
        Collections.sort(list,ascComp);
        System.out.println(list);

        //람다식으로 Comparator 인터페이스를 구현하여
        //대소문자 무시하고 정렬하도록 정렬기준 재정의
        Collections.sort(list, ((o1, o2) -> o1.compareToIgnoreCase(o2)));
        System.out.println(list);

        //람다식으로 Comparator 인터페이스를 구현하여
        //대소문자를 무시하고 내림차순 정렬하도록 정렬 기준 재정의
        Collections.sort(list, (Collections.reverseOrder((o1, o2) -> o1.compareToIgnoreCase(o2))));
        System.out.println(list);
    }
}
