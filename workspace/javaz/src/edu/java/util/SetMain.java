package edu.java.util;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetMain {
    public static void main(String[] args) {
//        Set<String> set = new Set<String>();  //Set은 인터페이스 객체 생성 불가
        Set<String> set = new HashSet<>(); //String만 저장 가능하게 생성
        set.add("1");
        // set.add(1);   //문자열이 아닌 것은 X
        set.add("sam"); set.add("e"); set.add("N");
        set.add("2");   set.add("A");
        System.out.println(set);

        Set<String> tSet = new TreeSet<>();
        tSet.add("1");  tSet.add("sam"); tSet.add("e");
        tSet.add("N");   tSet.add("2");   tSet.add("A");
        System.out.println(tSet);

        System.out.println(tSet.size());
        if(tSet.contains("A")){ //A가 포함되어 있으면
            tSet.remove("A");
        }
        System.out.println(tSet);
        System.out.println(tSet.size());
    }
}
