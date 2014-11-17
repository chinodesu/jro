package ro;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by roroco on 11/17/14.
 */
public class Time {
    public static String now() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime().toString();
    }
}
