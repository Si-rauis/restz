package edu.java.lang;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class SystemMain {
    public static void main(String[] args) throws IOException {
        long nowMill = System.currentTimeMillis();

        System.out.println( new Date(nowMill) );
/*
        System.out.println("시스템 프로퍼티 -----");
        System.out.println("운영체제 : " + System.getProperty("os.name"));
        System.out.println("파일 구분자 : " + System.getProperty("file.separator"));
        System.out.println("경로 구분자 : " + System.getProperty("path.separator"));
        Properties props = System.getProperties();
        props.list(System.out);

        System.out.println();
        System.out.println(System.getenv("JAVA_HOME"));

        System.out.println("-----------------------------------------------");
        int input = System.in.read();
        System.out.println("input = " + input);

 */
        System.out.print("Please input a word : ");
        byte[] inputs = new byte[10];

        int input = System.in.read(inputs);
        System.out.println("input : " + input);

    }//main
}//class
