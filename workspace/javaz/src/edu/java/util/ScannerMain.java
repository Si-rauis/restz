package edu.java.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerMain {
    public static void main(String[] args) throws FileNotFoundException {

        String str = "1 and 2 and bee and ant and fly";

        Scanner scan = new Scanner(str);
        scan.useDelimiter("\\s*and\\s*");

        System.out.println(scan.nextInt());
        System.out.println(scan.nextInt());
        System.out.println(scan.next());
        System.out.println(scan.next());
        System.out.println(scan.next());

        scan.close();

        /////////////////////////////////////////
        scan = new Scanner(new File("src/user.txt"));
        while(scan.hasNextLine()){
            System.out.println(scan.nextLine());
        }
        scan.close();

        System.out.println();
        System.out.println("3. 키보드 입력 받기");

        scan = new Scanner(System.in);

        System.out.print("문자열 입력 : ");
        String input = scan.nextLine();
        System.out.println(input);


        System.out.print("정수 입력 : ");
        System.out.println(scan.nextInt());
        System.out.println();

        System.out.print("실수 입력 : ");
        System.out.println(scan.nextDouble());
        System.out.println();
    }
}
