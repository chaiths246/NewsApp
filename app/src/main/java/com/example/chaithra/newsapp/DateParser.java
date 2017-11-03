package com.example.chaithra.newsapp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chaithra on 10/19/17.
 */

public class DateParser {
    public static Date parse(String input) throws java.text.ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        if (input.endsWith("Z")) {
            input = input.substring(0, input.length() - 1) + "GMT-00:00";
        } else {
            int inset = 6;
            String s0 = input.substring(0, input.length() - inset);
            String s1 = input.substring(input.length() - inset, input.length());
            input = s0 + "GMT" + s1;
        }
        return df.parse(input);
    }

    public static String toString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        String output = df.format(date);
        return output;
    }

}



