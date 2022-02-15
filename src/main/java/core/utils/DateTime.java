package core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper-class for simple use of actual time in format: 'HH:mm:ss.SSS' and milliseconds.
 *
 * @author StellaB
 */
public class DateTime {

    /**
     * Default Time format 'HH:mm:ss.SSS'
     */
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");


    private DateTime() {
    }


    /**
     * Get the actual time out of system-context
     *
     * @param sec Int - minus seconds
     * @return current time as String
     */
    public static String now(int sec) {
        return LocalDateTime.now().minusSeconds(sec).format(dateTimeFormatter);
    }


    /**
     * Getting formatted Time as String
     *
     * @return LocalDateTime, formatted as dateTimeFormatter is set
     */
    public static String getTimeString() {
        LocalDateTime current = LocalDateTime.now();
        return current.format(dateTimeFormatter);
    }


    public static void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        DateTime.dateTimeFormatter = dateTimeFormatter;
    }
}