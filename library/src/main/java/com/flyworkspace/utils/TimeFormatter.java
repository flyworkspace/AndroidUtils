package com.flyworkspace.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFormatter {

    /**
     * Get [YEAR] of date
     *
     * @param date
     * @return ep:2015
     */
    public static String getYearFormat(Date date) {
        String str = String.format("%tY", date);
        return str;
    }

    /**
     * Get [MONTH] of date
     *
     * @param date
     * @return ep: 11
     */
    public static String getMonthFormat(Date date) {
        String str = String.format("%tb", date);
        return str;
    }

    /**
     * Get [DAY] of date
     *
     * @param date
     * @return ep:24
     */
    public static String getDayFormat(Date date) {
        String str = String.format("%td", date);
        return str;
    }

    /**
     * Get [WEEk] of date
     *
     * @param date
     * @return ep MONDAY
     */
    public static String getWeekFormat(Date date) {
        String str = String.format("%ta", date);
        return str;
    }

    /**
     * Get [MONTH]-[DAY] of date
     *
     * @param context
     * @param date
     * @return
     */
    public static String getMonthDay(Context context, Date date) {
        return getMonthFormat(date) + "-" + getDayFormat(date);
    }

    /**
     * Get [YEAR]-[MONTH]-[DAY]
     * @param date
     * @return ep: 2015-05-14
     */
    public static String getYearMonthDay(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }

    /**
     * Get [YEAR]-[MONTH]-[DAY]
     * @param millisecond
     * @return ep: 2015-05-14
     */
    public static String formatDateYYYYMMDD(long millisecond) {
        return (String) android.text.format.DateFormat.format("yyyy-MM-dd",
                millisecond);
    }

    /**
     * Get [YEAR]  [MONTH]  [DAY]
     * @param millisecond
     * @return
     */
    public static String formatDateYYYYMMMDD(long millisecond) {
        return (String) android.text.format.DateFormat.format("yyyy  MMM  dd",
                millisecond);
    }


    /**
     * Get [YEAR]-[MONTH]
     * @param millisecond
     * @return
     */
    public static String formatDateYYYYMM(long millisecond) {
        return (String) android.text.format.DateFormat.format("yyyy-MMM",
                millisecond);
    }

    /**
     * Get [hour]:[minute][a]
     * @param millisecond
     * @return
     */
    public static String formatDateHHMM(long millisecond) {
        return (String) android.text.format.DateFormat.format("hh:mm a",
                millisecond);
    }

    /**
     * Get [YEAR]
     * @param millisecond
     * @return
     */
    public static String formatDateYYYY(long millisecond) {
        return (String) android.text.format.DateFormat.format("yyyy",
                millisecond);
    }

    /**
     * Get [MONTH]
     * @param millisecond
     * @return
     */
    public static String formatDateMMM(long millisecond) {
        return (String) android.text.format.DateFormat.format("MMM",
                millisecond);
    }

    /**
     * Get [DAY]
     * @param millisecond
     * @return
     */
    public static String formatDateDD(long millisecond) {
        return (String) android.text.format.DateFormat
                .format("dd", millisecond);
    }

    /**
     * Get [WEEK]
     * @param millisecond
     * @return
     */
    public static String formatDateWeek(long millisecond) {
        return (String) android.text.format.DateFormat.format("EEE",
                millisecond);
    }

    /**
     * Get [YEAR]  [MONTH]  [DAY]  [WEEK]  [hour]:[minute][a]
     * @param millisecond
     * @return
     */
    public static String formatDateYYYYMMMDDEEEHHMM(long millisecond) {
        return formatDateYYYYMMMDD(millisecond) + "  "
                + formatDateWeek(millisecond) + "  "
                + formatDateHHMM(millisecond);
    }

    /**
     * Get min time in a month .ep: 2004-10-04  19:29:30  ----  2004-10-01  00:00:00
     * @param time
     * @return
     */
    public static long getMinTimeOfMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                1, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Get max time in a month .ep: 2004-10-04  19:29:30  ----  2004-10-31  23:59:59
     * @param time
     * @return
     */
    public static long getMaxTimeOfMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTimeInMillis();
    }
}
