package brightpod.components;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Defines opportunities products pages.
 *
 * @author Juan Martinez.
 * @version 1.0 04 april 2020.
 */
public final class DateFormat {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static Calendar calendar = Calendar.getInstance();
    private static final int DAYS = 0;
    private static final int MONTHS = 1;
    private static final int YEARS = 2;
    private static final String TODAY = "TODAY";
    private static final String BEFORE = "BEFORE";
    private static final String AFTER = "AFTER";
    private static String[] date;
    private static final int LIST_SIZE = 3;
    private static Date today = new Date(System.currentTimeMillis());

    /**
     * Date format constructor
     */
    private DateFormat() {

    }

    /**
     * Formats date.
     * @return formated date.
     */
    private static String formatToday() {
        return formatter.format(today);
    }

    /**
     * Splits date format.
     * @param dateString value.
     * @return split date.
     */
    private static String[] spliter(String dateString) {
        return dateString.split("-");
    }

    /**
     * Replaces String after.
     * @param date value.
     * @return string value.
     */
    private static int[] replaceStringsAfter(String[] date) {
        int[] result = new int[LIST_SIZE];
        result[DAYS] = Integer.parseInt(date[DAYS].replaceAll("[^0-9]+", ""));
        result[MONTHS] = Integer.parseInt(date[MONTHS].replaceAll("[^0-9]+", ""));
        result[YEARS] = Integer.parseInt(date[YEARS].replaceAll("[^0-9]+", ""));
        return result;
    }

    /**
     * Replaces string before.
     * @param date value.
     * @return string value.
     */
    private static int[] replaceStringsBefore(String[] date) {
        int[] result = new int[LIST_SIZE];
        result[DAYS] = -Integer.parseInt(date[DAYS].replaceAll("[^0-9]+", ""));
        result[MONTHS] = -Integer.parseInt(date[MONTHS].replaceAll("[^0-9]+", ""));
        result[YEARS] = -Integer.parseInt(date[YEARS].replaceAll("[^0-9]+", ""));
        return result;
    }

    /**
     * Sets values to calendar
     * @param dateNumber value.
     * @return calendar values
     */
    private static Date setValuesToCalendar(int[] dateNumber) {
        calendar.setTime(today);
        calendar.add(Calendar.DATE, dateNumber[DAYS]);
        calendar.add(Calendar.MONTH, dateNumber[MONTHS]);
        calendar.add(Calendar.YEAR, dateNumber[YEARS]);
        return calendar.getTime();
    }

    /**
     * Formats before date.
     * @param dateString value
     * @return before value.
     */
    private static String before(String dateString) {
        date = spliter(dateString);
        String[] dates = new String[LIST_SIZE];
        dates[DAYS] = date[DAYS];
        dates[MONTHS] = date[MONTHS];
        dates[YEARS] = date[YEARS];
        int[] dateNumber = replaceStringsBefore(dates);
        Date dateValue = setValuesToCalendar(dateNumber);
        return formatter.format(dateValue);
    }

    /**
     * Formats after date.
     * @param dateString value.
     * @return after format date.
     */
    private static String after(String dateString) {
        date = spliter(dateString);
        String[] dates = new String[LIST_SIZE];
        dates[DAYS] = date[DAYS];
        dates[MONTHS] = date[MONTHS];
        dates[YEARS] = date[YEARS];
        int[] dateNumber = replaceStringsAfter(dates);
        Date dateValue = setValuesToCalendar(dateNumber);
        return formatter.format(dateValue);
    }

    /**
     * Formats date.
     * @param date value.
     * @return formated date.
     */
    public static String formatDate(String date) {
        String value = null;
        if (date == null)
            return date;
        if (date.equals(TODAY)) {
            value = formatToday();
        } else if (date.contains(BEFORE)) {
            value = before(date);
        } else if (date.contains(AFTER)) {
            value = after(date);
        }
        assert value != null;
        String replace = value.replaceAll("[.]", "");
        return replace.substring(0, 1).toUpperCase() + replace.substring(1);
    }
}
