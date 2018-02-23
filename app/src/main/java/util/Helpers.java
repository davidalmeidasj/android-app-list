package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by davidalmeida on 23/02/18.
 */

public final class Helpers {

    public static String format(Calendar calendar, String format){

        SimpleDateFormat date = new SimpleDateFormat(format, Locale.getDefault());

        return date.format(calendar.getTime());
    }

    public static Calendar buildCalendar(int year, int month, int day) {

        final Calendar calendar = Calendar.getInstance();

        calendar.set(year, month, day);

        return calendar;
    }
}
