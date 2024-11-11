package edu.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class DateMain {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        Date current = new Date(System.currentTimeMillis());

        System.out.println(date);
        System.out.println(current.toLocaleString());

        SimpleDateFormat fmt
                = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //Date -----> String
        String str = fmt.format(current);
        System.out.println(str);

        //String ---> Date
        //Date data = fmt.parse("2024/07/23 11:22:33");

        ////////////////////////////////////////
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR) );

        System.out.println();

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.now();

        System.out.println(ld);
        System.out.println(lt);
        System.out.println(ldt);

        System.out.println(ld.getYear());
        System.out.println("윤년? " + ld.isLeapYear());
        System.out.println(lt.getHour());
        System.out.println("이 달의 마지막은 " + ld.getDayOfMonth());

    }
}
