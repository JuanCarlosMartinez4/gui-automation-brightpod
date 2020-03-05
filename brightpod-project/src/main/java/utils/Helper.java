package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Helper {
    private SimpleDateFormat formatter= new SimpleDateFormat("MMM dd, yyyy");
    private Calendar calendar = Calendar.getInstance();
    private final int DAYS = 0;
    private final int MONTHS = 1;
    private final int YEARS = 2;
    private final String TODAY = "TODAY";
    private final String BEFORE = "BEFORE";
    private final String AFTER = "AFTER";
    private String [] date;
    private Date today = new Date(System.currentTimeMillis());

    private String formatToday() {
        return formatter.format(today);
    }

    private String[] spliter(String dateString) {
        return dateString.split("-");
    }

    private int[] replaceStringsAfter(String[] date) {
        int [] result = new int[3];
        result[DAYS] = Integer.parseInt(date[DAYS].replaceAll("[^0-9]+", ""));
        result[MONTHS] = Integer.parseInt(date[MONTHS].replaceAll("[^0-9]+", ""));
        result[YEARS] = Integer.parseInt(date[YEARS].replaceAll("[^0-9]+", ""));
        return result;
    }

    private int[] replaceStringsBefore(String[] date) {
        int [] result = new int[3];
        result[DAYS] = -Integer.parseInt(date[DAYS].replaceAll("[^0-9]+", ""));
        result[MONTHS] = -Integer.parseInt(date[MONTHS].replaceAll("[^0-9]+", ""));
        result[YEARS] = -Integer.parseInt(date[YEARS].replaceAll("[^0-9]+", ""));
        return result;
    }

    private Date setValuesToCalendar(int[] dateNumber) {
        calendar.setTime(today);
        calendar.add(Calendar.DATE, dateNumber[DAYS]);
        calendar.add(Calendar.MONTH, dateNumber[MONTHS]);
        calendar.add(Calendar.YEAR, dateNumber[YEARS]);
        return calendar.getTime();
    }

    private String before(String dateString) {
        date = spliter(dateString);
        String [] dates = new String[3];
        dates[DAYS] = date[DAYS];
        dates[MONTHS] = date[MONTHS];
        dates[YEARS] = date[YEARS];
        int [] dateNumber = replaceStringsBefore(dates);
        Date dateValue = setValuesToCalendar(dateNumber);
        return formatter.format(dateValue);
    }

    private String after(String dateString) {
        date = spliter(dateString);
        String [] dates = new String[3];
        dates[DAYS] = date[DAYS];
        dates[MONTHS] = date[MONTHS];
        dates[YEARS] = date[YEARS];
        int [] dateNumber = replaceStringsAfter(dates);
        Date dateValue = setValuesToCalendar(dateNumber);
        return formatter.format(dateValue);
    }

    public String formatDate(String date) {
        String value = null;
        if (date == TODAY) {
            value = formatToday();
        }
        else if (date.contains(BEFORE)) {
            value = before(date);
        }
        else if (date.contains(AFTER)) {
            value = after(date);
        }
        return value;
    }

    public static void main(String[] args) {
        Helper helper = new Helper();
        System.out.println(helper.formatDate("3 DAYS-0 MONTH-0 YEAR-AFTER-FROM TODAY"));
    }
}
