package com.machine.fcmpusherlib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by admin on 30/10/2017.
 */

public final class ISO8601 {

    /** Transform Calendar to ISO 8601 string. */
    public static String fromCalendar(final Calendar calendar) {
        Date date = calendar.getTime();
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

    public static String fromDate(final Date date) {
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

    public static String fromUTCDate(final Date date) {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formatted = sourceFormat.format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

    public static String toUTCDate(final Date date) {
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        destFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return destFormat.format(date);
    }

    public static String getUTCFormatDate(final Date date) {
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        destFormat.setTimeZone(TimeZone.getDefault());
        return destFormat.format(date);
    }

    /** Get current date and time formatted as ISO 8601 string. */
    public static String now() {
        return fromCalendar(GregorianCalendar.getInstance());
    }

    /** Transform ISO 8601 string to Calendar. */
    public static String formatDate(final String iso8601string, String timeZone, String format) throws ParseException {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parseDate = sourceFormat.parse(iso8601string);
        SimpleDateFormat destFormat = new SimpleDateFormat(format);
        destFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return destFormat.format(parseDate);
    }

    public static Date getDate(final String iso8601string, String timeZone) throws ParseException {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parseDate = sourceFormat.parse(iso8601string);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
        calendar.setTime(parseDate);
        return calendar.getTime();
    }

    public static Date getDateInUTC(final String iso8601string) throws ParseException {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sourceFormat.parse(iso8601string);
    }

    public static Date getDate(final String iso8601string) throws ParseException {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sourceFormat.parse(iso8601string);
    }

    public static String getCurrentDateInUTC() {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return sourceFormat.format(calendar.getTime());
    }

    public static Date getFromDate(String function)
    {
        GregorianCalendar c = new GregorianCalendar();
        c.set(Calendar.HOUR,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,1); //This is so that the timezone conversion takes place in the conversion methods
        c.set(Calendar.AM_PM,0);
        int current_month = c.get(Calendar.MONTH);
        int current_year = c.get(Calendar.YEAR);
        int current_day = c.get(Calendar.DAY_OF_MONTH);
        int current_day_of_week = c.get(Calendar.DAY_OF_WEEK);
        int current_day_of_yr = c.get(Calendar.DAY_OF_YEAR);

        c.set(Calendar.DAY_OF_MONTH,1);
        int start_quarter = 0;
        int first_quarter_start = start_quarter;
        int second_quarter_start = start_quarter + 3;
        int third_quarter_start = start_quarter + 6;
        int fourth_quarter_start = start_quarter + 9;

        int start_f_quarter = 0; // financial

        int second_f_quarter_start = start_f_quarter + 3;
        if (second_f_quarter_start >= 12)
            second_f_quarter_start = second_f_quarter_start - 12;

        int third_f_quarter_start = start_f_quarter + 6;
        if (third_f_quarter_start >= 12)
            third_f_quarter_start = third_f_quarter_start - 12;

        int fourth_f_quarter_start = start_f_quarter + 9;
        if (fourth_f_quarter_start >= 12)
            fourth_f_quarter_start = fourth_f_quarter_start - 12;

        if (function.equals("currentQ"))
        {
            if (current_month >= first_quarter_start && current_month < second_quarter_start)
                c.set(Calendar.MONTH,first_quarter_start);
            else if (current_month >= second_quarter_start && current_month < third_quarter_start)
                c.set(Calendar.MONTH,second_quarter_start);
            else if (current_month >= third_quarter_start && current_month < fourth_quarter_start)
                c.set(Calendar.MONTH,third_quarter_start);
            else if (current_month >= fourth_quarter_start)
                c.set(Calendar.MONTH,fourth_quarter_start);
        }
        else if (function.equals("currentQ_NEXT"))
        {
            if (current_month >= first_quarter_start && current_month < second_quarter_start)
                c.set(Calendar.MONTH,first_quarter_start);
            else if (current_month >= second_quarter_start && current_month < third_quarter_start)
                c.set(Calendar.MONTH,second_quarter_start);
            else if (current_month >= third_quarter_start && current_month < fourth_quarter_start)
                c.set(Calendar.MONTH,third_quarter_start);
            else if (current_month >= fourth_quarter_start)
                c.set(Calendar.MONTH,fourth_quarter_start);
        }
        else if (function.equals("currentQ_PREVIOUS"))
        {
            if (current_month >= first_quarter_start && current_month < second_quarter_start)
            {
                c.set(Calendar.MONTH,fourth_quarter_start);
                c.set(Calendar.YEAR,current_year-1);
            }
            else if (current_month >= second_quarter_start && current_month < third_quarter_start)
                c.set(Calendar.MONTH,first_quarter_start);
            else if (current_month >= third_quarter_start && current_month < fourth_quarter_start)
                c.set(Calendar.MONTH,second_quarter_start);
            else if (current_month >= fourth_quarter_start)
                c.set(Calendar.MONTH,third_quarter_start);
        }
        else if (function.equals("Q_NEXT"))
        {
            if (current_month >= first_quarter_start && current_month < second_quarter_start)
                c.set(Calendar.MONTH,second_quarter_start);
            else if (current_month >= second_quarter_start && current_month < third_quarter_start)
                c.set(Calendar.MONTH,third_quarter_start);
            else if (current_month >= third_quarter_start && current_month < fourth_quarter_start)
                c.set(Calendar.MONTH,fourth_quarter_start);
            else if (current_month >= fourth_quarter_start)
            {
                c.set(Calendar.MONTH,first_quarter_start);
                c.set(Calendar.YEAR,current_year+1);
            }
        }
        else if (function.equals("Q_PREVIOUS"))
        {
            if (current_month >= first_quarter_start && current_month < second_quarter_start)
            {
                c.set(Calendar.MONTH,fourth_quarter_start);
                c.set(Calendar.YEAR,current_year-1);
            }
            else if (current_month >= second_quarter_start && current_month < third_quarter_start)
                c.set(Calendar.MONTH,first_quarter_start);
            else if (current_month >= third_quarter_start && current_month < fourth_quarter_start)
                c.set(Calendar.MONTH,second_quarter_start);
            else if (current_month >= fourth_quarter_start)
                c.set(Calendar.MONTH,third_quarter_start);
        }
        else if (function.equals("currentY"))
        {
            c.set(Calendar.MONTH,0);
        }
        else if (function.equals("currentY_NEXT"))
        {
            c.set(Calendar.MONTH,0);
        }
        else if (function.equals("currentY_PREVIOUS"))
        {
            c.set(Calendar.MONTH,0);
            c.set(Calendar.YEAR,current_year-1);
        }
        else if (function.equals("Y_NEXT"))
        {
            c.set(Calendar.MONTH,0);
            c.set(Calendar.YEAR,current_year+1);
        }
        else if (function.equals("Y_PREVIOUS"))
        {
            c.set(Calendar.MONTH,0);
            c.set(Calendar.YEAR,current_year-1);
        }
        else if (function.equals("yesterday"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day-1);
        }
        else if (function.equals("today"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
        }
        else if (function.equals("tomorrow"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+1);
        }
        else if (function.equals("3daysFromNow"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+3);
        }
        else if (function.equals("15daysFromNow"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+15);
        }
        else if (function.equals("7daysFromNow"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+7);
        }
        else if (function.equals("monthFromNow"))
        {
            c.set(Calendar.MONTH,current_month+1);
        }
        else if (function.equals("lastweek"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
            c.add(Calendar.DAY_OF_MONTH,-1*(current_day_of_week)); // goto end of last of week
            c.add(Calendar.DAY_OF_MONTH,-6); // goto start of last of week
        }
        else if (function.equals("beforethisweek"))
        {
            c.set(Calendar.YEAR,1950);
        }
        else if (function.equals("beforetoday"))
        {
            c.set(Calendar.YEAR,1950);
        }
        else if (function.equals("aftertoday"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+1);
        }
        else if (function.equals("thisweek"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
            c.add(Calendar.DAY_OF_MONTH,-1*(current_day_of_week-1)); // goto start of week
        }
        else if (function.equals("nextweek"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
            c.add(Calendar.DAY_OF_MONTH,1*(7-current_day_of_week+1)); // goto start of next week
        }
        else if (function.equals("lastmonth"))
        {
            c.set(Calendar.MONTH,current_month-1);
        }
        else if (function.equals("last3months"))
        {
            c.set(Calendar.MONTH,current_month-3);
        }
        else if (function.equals("thismonth"))
        {
            c.set(Calendar.MONTH,current_month);
        }
        else if (function.equals("nextmonth"))
        {
            c.set(Calendar.MONTH,current_month+1);
        }
        else if (function.equals("thisyear"))
        {
            c.set(Calendar.YEAR,current_year);
            c.set(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.MONTH, 0);
        }
        else if (function.equals("ago3years"))
        {
            c.set(Calendar.YEAR,current_year-3);
        }
        else if (function.equals("ago2years"))
        {
            c.set(Calendar.YEAR,current_year-2);
        }
        else if (function.equals("ago365days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-365);
        }

        else if (function.equals("ago180days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-180);
        }

        else if (function.equals("ago120days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-120);
        }
        else if (function.equals("ago90days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-90);
        }
        else if (function.equals("ago60days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-60);
        }
        else if (function.equals("ago30days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-30);
        }
        else if (function.equals("ago14days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-14);
        }
        else if (function.equals("ago7days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-7);
        }
        else if (function.equals("last2years"))
        {
            c.set(Calendar.YEAR,current_year-2);
        }
        else if (function.equals("last3years"))
        {
            c.set(Calendar.YEAR,current_year-3);
        }
        else if (function.equals("last365days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-365);
        }
        else if (function.equals("last180days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-180);
        }
        else if (function.equals("last120days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-120);
        }
        else if (function.equals("last90days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-90);
        }
        else if (function.equals("last60days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-60);
        }
        else if (function.equals("last30days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-30);
        }
        else if (function.equals("last14days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-14);
        }
        else if (function.equals("last7days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-7);
        }
        else if (function.equals("next7days"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
        }
        else if (function.equals("next14days"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
        }
        else if (function.equals("next30days"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
        }
        else if (function.equals("next60days"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
        }
        else if (function.equals("next90days"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
        }
        else if (function.equals("next120days"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
        }
        else if (function.equals("later0days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
        }
        else if (function.equals("later1days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+1);
        }
        else if (function.equals("later7days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+7);
        }
        else if (function.equals("later30days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+30);
        }
        else if (function.equals("later60days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+60);
        }
        else if (function.equals("later90days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+90);
        }
        else if (function.equals("later120days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+120);
        }
        else if (function.equals("tomorrow_date_range"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+2);
            c.set(Calendar.HOUR,9);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            c.set(Calendar.AM_PM, Calendar.AM);
        }
        else if (function.equals("2daysFromNow_date_range"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+2);
            c.set(Calendar.HOUR,9);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            c.set(Calendar.AM_PM, Calendar.AM);
        }
        else if (function.equals("nextweek_date_range"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
            c.add(Calendar.DAY_OF_MONTH,1*(7-current_day_of_week+1)); // goto start of next week
            c.set(Calendar.HOUR,9);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            c.set(Calendar.AM_PM, Calendar.AM);
        }

        return new Date(c.getTimeInMillis());
    }

    public static Date getToDate(String function)
    {
        GregorianCalendar c = new GregorianCalendar();
        c.set(Calendar.HOUR,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.AM_PM,0);
        c.set(Calendar.MILLISECOND,0);
        int current_month = c.get(Calendar.MONTH);
        int current_year = c.get(Calendar.YEAR);
        int current_day = c.get(Calendar.DAY_OF_MONTH);
        int current_day_of_week = c.get(Calendar.DAY_OF_WEEK);
        int current_day_of_yr = c.get(Calendar.DAY_OF_YEAR);

        c.set(Calendar.DAY_OF_MONTH,1);
        int start_quarter = 0;
        int first_quarter_start = start_quarter;
        int second_quarter_start = start_quarter + 3;
        int third_quarter_start = start_quarter + 6;
        int fourth_quarter_start = start_quarter + 9;

        int start_f_quarter = 0; // financial

        int second_f_quarter_start = start_f_quarter + 3;
        if (second_f_quarter_start >= 12)
            second_f_quarter_start = second_f_quarter_start - 12;

        int third_f_quarter_start = start_f_quarter + 6;
        if (third_f_quarter_start >= 12)
            third_f_quarter_start = third_f_quarter_start - 12;

        int fourth_f_quarter_start = start_f_quarter + 9;
        if (fourth_f_quarter_start >= 12)
            fourth_f_quarter_start = fourth_f_quarter_start - 12;

        if (function.equals("currentQ"))
        {
            if (current_month >= first_quarter_start && current_month < second_quarter_start)
                c.set(Calendar.MONTH,second_quarter_start);
            else if (current_month >= second_quarter_start && current_month < third_quarter_start)
                c.set(Calendar.MONTH,third_quarter_start);
            else if (current_month >= third_quarter_start && current_month < fourth_quarter_start)
                c.set(Calendar.MONTH,fourth_quarter_start);
            else if (current_month >= fourth_quarter_start)
            {
                c.set(Calendar.MONTH,first_quarter_start);
                c.set(Calendar.YEAR,current_year+1);
            }

        }
        else if (function.equals("currentQ_NEXT"))
        {
            if (current_month >= first_quarter_start && current_month < second_quarter_start)
                c.set(Calendar.MONTH,third_quarter_start);
            else if (current_month >= second_quarter_start && current_month < third_quarter_start)
                c.set(Calendar.MONTH,fourth_quarter_start);
            else if (current_month >= third_quarter_start && current_month < fourth_quarter_start)
            {
                c.set(Calendar.MONTH,first_quarter_start);
                c.set(Calendar.YEAR,current_year+1);
            }
            else if (current_month >= fourth_quarter_start)
            {
                c.set(Calendar.MONTH,second_quarter_start);
                c.set(Calendar.YEAR,current_year+1);
            }
        }
        else if (function.equals("currentQ_PREVIOUS"))
        {
            if (current_month >= first_quarter_start && current_month < second_quarter_start)
                c.set(Calendar.MONTH,second_quarter_start);
            else if (current_month >= second_quarter_start && current_month < third_quarter_start)
                c.set(Calendar.MONTH,third_quarter_start);
            else if (current_month >= third_quarter_start && current_month < fourth_quarter_start)
                c.set(Calendar.MONTH,fourth_quarter_start);
            else if (current_month >= fourth_quarter_start)
            {
                c.set(Calendar.MONTH,first_quarter_start);
                c.set(Calendar.YEAR,current_year+1);
            }
        }
        else if (function.equals("Q_NEXT"))
        {
            if (current_month >= first_quarter_start && current_month < second_quarter_start)
                c.set(Calendar.MONTH,third_quarter_start);
            else if (current_month >= second_quarter_start && current_month < third_quarter_start)
                c.set(Calendar.MONTH,fourth_quarter_start);
            else if (current_month >= third_quarter_start && current_month < fourth_quarter_start)
            {
                c.set(Calendar.MONTH,first_quarter_start);
                c.set(Calendar.YEAR,current_year+1);
            }
            else if (current_month >= fourth_quarter_start)
            {
                c.set(Calendar.MONTH,second_quarter_start);
                c.set(Calendar.YEAR,current_year+1);
            }
        }
        else if (function.equals("Q_PREVIOUS"))
        {
            if (current_month >= first_quarter_start && current_month < second_quarter_start)
                c.set(Calendar.MONTH,first_quarter_start);
            else if (current_month >= second_quarter_start && current_month < third_quarter_start)
                c.set(Calendar.MONTH,second_quarter_start);
            else if (current_month >= third_quarter_start && current_month < fourth_quarter_start)
                c.set(Calendar.MONTH,third_quarter_start);
            else if (current_month >= fourth_quarter_start)
                c.set(Calendar.MONTH,fourth_quarter_start);
        }
        else if (function.equals("currentY"))
        {
            c.add(Calendar.YEAR,1);
            c.set(Calendar.MONTH,0);
        }
        else if (function.equals("currentY_NEXT"))
        {
            c.add(Calendar.YEAR,2);
            c.set(Calendar.MONTH,0);
        }
        else if (function.equals("currentY_PREVIOUS"))
        {
            c.set(Calendar.MONTH,0);
            c.add(Calendar.YEAR,1);
        }
        else if (function.equals("Y_NEXT"))
        {
            c.set(Calendar.MONTH,0);
            c.set(Calendar.YEAR,current_year+2);
        }
        else if (function.equals("Y_PREVIOUS"))
        {
            c.set(Calendar.MONTH,0);
            c.set(Calendar.YEAR,current_year);
        }
        else if (function.equals("yesterday"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
        }
        else if (function.equals("today"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+1);
        }
        else if (function.equals("aftertoday"))
        {
            c.set(Calendar.YEAR,2050);
        }
        else if (function.equals("beforetoday"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
        }
        else if (function.equals("tomorrow"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+2);
        }
        else if (function.equals("lastweek"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
            c.add(Calendar.DAY_OF_MONTH,-1*(current_day_of_week-1)); // goto end of last of week
        }
        else if (function.equals("thisweek"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
            c.add(Calendar.DAY_OF_MONTH,1*(7-current_day_of_week+1)); // goto end of this week
        }
        else if (function.equals("beforethisweek"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
            c.add(Calendar.DAY_OF_MONTH,-1*(current_day_of_week-1)); // goto end of last of week
        }
        else if (function.equals("nextweek"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
            c.add(Calendar.DAY_OF_MONTH,1*(7-current_day_of_week+8)); // goto end of next week
        }
        else if (function.equals("lastmonth"))
        {
            c.set(Calendar.MONTH,current_month);
        }
        else if (function.equals("thismonth"))
        {
            c.set(Calendar.MONTH,current_month+1);
        }
        else if (function.equals("nextmonth"))
        {
            c.set(Calendar.MONTH,current_month+2);
        }
        else if (function.equals("thisyear"))
        {
            c.set(Calendar.YEAR,current_year+1);
            c.set(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.MONTH, 0);
        }
        else if (function.equals("ago3years"))
        {
            c.set(Calendar.YEAR,current_year-3);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("ago2years"))
        {
            c.set(Calendar.YEAR,current_year-2);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("ago365days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-365);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }

        else if (function.equals("ago180days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-180);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }

        else if (function.equals("ago120days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-120);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("ago90days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-90);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("ago60days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-60);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("ago30days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-30);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("ago14days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-14);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("ago7days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr-7);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("last2years"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("last3years"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("last365days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("last180days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("last120days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("last90days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("last60days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("last30days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("last14days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("last7days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("15daysFromNow"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+15);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("7daysFromNow"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+7);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("3daysFromNow"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+3);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("monthFromNow"))
        {
            c.set(Calendar.MONTH,current_month+1);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("next7days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+7);
        }
        else if (function.equals("next14days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+14);
        }
        else if (function.equals("next30days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+30);
        }
        else if (function.equals("next60days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+60);
        }
        else if (function.equals("next90days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+90);
        }
        else if (function.equals("next120days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+120);
        }
        else if (function.equals("later0days"))
        {
            c.set(Calendar.YEAR,2099);
        }
        else if (function.equals("later1days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+1);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("later7days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+7);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("later30days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+30);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("later60days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+60);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("later90days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+90);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("later120days"))
        {
            c.set(Calendar.DAY_OF_YEAR,current_day_of_yr+120);
            c.set(Calendar.HOUR,11);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            c.set(Calendar.AM_PM,1);
        }
        else if (function.equals("tomorrow_date_range"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+2);
            c.set(Calendar.HOUR,9);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            c.set(Calendar.AM_PM, Calendar.AM);
        }
        else if (function.equals("2daysFromNow_date_range"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day+2);
            c.set(Calendar.HOUR,9);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            c.set(Calendar.AM_PM, Calendar.AM);
        }
        else if (function.equals("nextweek_date_range"))
        {
            c.set(Calendar.DAY_OF_MONTH,current_day);
            c.add(Calendar.DAY_OF_MONTH,1*(7-current_day_of_week+1)); // goto start of next week
            c.set(Calendar.HOUR,9);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            c.set(Calendar.AM_PM, Calendar.AM);
        }

        return new Date(c.getTimeInMillis());
    }

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        return cal.getTime();
    }

    public static Date add(int hour) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR,hour);
        System.out.println("Time here "+now.getTime());
        return now.getTime();
    }

}
