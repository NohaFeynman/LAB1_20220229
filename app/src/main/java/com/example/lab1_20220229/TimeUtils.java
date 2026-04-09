package com.example.lab1_20220229;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm a", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String getCurrentHourMinute() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String getElapsedSeconds(long startMillis) {
        long elapsedMillis = System.currentTimeMillis() - startMillis;
        long seconds = elapsedMillis / 1000;

        if (seconds < 0) {
            seconds = 0;
        }

        return seconds + "s";
    }
}