package com.example.ett15084.harkkatyo;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DateHandler {

    private static DateHandler dateHandler = new DateHandler();

    // This class gets the dates for the date spinner


    // This arrayist holds the dates and from this the spinner is populated
    ArrayList dates = new ArrayList();

    public ArrayList setDatesArray(){
        Calendar cal = Calendar.getInstance();
        dates.clear();


        System.out.println("##########");
        System.out.println("Tämä päivä: " + cal.get(Calendar.DAY_OF_MONTH)+ "." + (cal.get(Calendar.MONTH)+1)+ ".");
        dates.add(cal.get(Calendar.DAY_OF_MONTH)+ "." + (cal.get(Calendar.MONTH)+1)+ ".");
        cal.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Huominen: " + cal.get(Calendar.DAY_OF_MONTH)+ "." + (cal.get(Calendar.MONTH)+1)+ ".");
        dates.add(cal.get(Calendar.DAY_OF_MONTH)+ "." + (cal.get(Calendar.MONTH)+1)+ ".");
        cal.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Sitten " + cal.get(Calendar.DAY_OF_MONTH)+ "." + (cal.get(Calendar.MONTH)+1)+ ".");
        dates.add(cal.get(Calendar.DAY_OF_MONTH)+ "." + (cal.get(Calendar.MONTH)+1)+ ".");
        cal.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Sitten " + cal.get(Calendar.DAY_OF_MONTH)+ "." + (cal.get(Calendar.MONTH)+1)+ ".");
        dates.add(cal.get(Calendar.DAY_OF_MONTH)+ "." + (cal.get(Calendar.MONTH)+1)+ ".");
        cal.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Sitten " + cal.get(Calendar.DAY_OF_MONTH)+ "." + (cal.get(Calendar.MONTH)+1)+ ".");
        dates.add(cal.get(Calendar.DAY_OF_MONTH)+ "." + (cal.get(Calendar.MONTH)+1)+ ".");
        System.out.println("##########");

        System.out.println(dates);
        return dates;

    }

    public static DateHandler getInstance(){
        return dateHandler;
    }
}
