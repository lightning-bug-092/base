package com.hius.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


public final class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static final String MMddHHmm = "MMddHHmm";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final String ddMMyyyyHHmm = "dd/MM/yyyy HH:mm:ss";
    public static final String TIME_FORMAT_EXPORT = "HH:mm:ss dd/MM/yyyy";
    public static final String UI_FORMAT = "dd/MM HH:mm";
    public static final String DDMM = "dd-MM";
    public static final String HHmm = "HH:mm";
    public static final String yyMMdd = "yyMMdd";
    public static final String TID = "yyMMddHHmm";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYMMDDHHmm = "yyMMddHHmm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String MM_YYYY = "MM-yyyy";
    public static final String HHmmYYYYMMDD = "HH:mm yyyy-MM-dd";
    public static final String yyMMddHHmm = "yyMMddHHmm";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String HHmmssSSSddMMyyyy = "HHmmssSSSddMMyyyy";
    public static final String MMddHHmmss = "MMddHHmmss";
    public static final String FormateerddMMYYYY_FileName = "ddMMyyyyHHmm";
    public static int HOUR_LOCAL = 7;// GMT-06:00 to GMT+07:00
    public static int hours = 7;
    private static final Object LOCK = new Object();
    private static final Map<String, ThreadLocal<SimpleDateFormat>> POOL = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    public static final String DATE_FMT = "dd/MM/yyyy";

    public DateUtil() {
    }

    public static String toDateString(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(new Date());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String toString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String toHHmm(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(HHmm);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String toddmmY(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ddMMyyyyHHmm);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String toTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String toTimeFormat(Date date) {
        if(date != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT_EXPORT);
            try {
                return dateFormat.format(date);
            } catch (Exception e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
        return "";
    }

    /**
     * date to String By Format
     *
     * @param date
     * @param format
     * @return
     */
    public static String toString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String getDay() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(day);
    }

    public static String getMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return String.valueOf(month);
    }

    public static String getYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    public static String toDateHHMI(Date date) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(UI_FORMAT);
            return dateFormat.format(toLocalDate(date));
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String toDateDDMM(Date date) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DDMM);
            return dateFormat.format(toLocalDate(date));
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static Date toLocalDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, HOUR_LOCAL);
        return calendar.getTime();
    }

    public static String toDateYYMMDD(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyMMdd);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String getTodayString(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYYMMDD);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getYesterdayString(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYYMMDD);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String toDateYYYYMMDD(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }



    public static String toDateYYYYMMDD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);
        try {
            return dateFormat.format(new Date());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String toDateYYYYMMDDWithDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String toLocalDateYYYYMMDD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);
        try {
            return dateFormat.format(toLocalDate(new Date()));
        } catch (Exception e) {
            return "";
        }
    }

    public static String toDateYYYY_MM(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String toDateMM_YYYY(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(MM_YYYY);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String toDateYYYYMMDDSSS(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(HHmmssSSSddMMyyyy);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String toDateYYYY_MM_DD(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static Date toDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
        Date date;
        try {
            dateString = dateString.replaceAll("/", "-");
            date = dateFormat.parse(dateString);
            return date;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public static Date toDateAddDay(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
        Date date;
        try {
            dateString = dateString.replaceAll("/", "-");
            date = dateFormat.parse(dateString);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
            return date;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public static Date toDateYYYYMMDD(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        Date date;
        try {
            date = dateFormat.parse(dateString);
            return date;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public static Date toDateYYYYMMDD2(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);
        Date date;
        try {
            date = dateFormat.parse(dateString);
            return date;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * Convert DateString by format 20210120T092414Z
     *
     * @param dateString
     * @param format
     * @return Date
     */
    public static Date toDate(String dateString, String format) {
        // Remove offset
        if (dateString.contains(".") && dateString.endsWith("Z")) {
            dateString = dateString.substring(0, dateString.indexOf(".")) + "Z";
        }
        //2013-11-23T13:59
        if (dateString.length() == 15) {
            dateString = dateString + ":00Z";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = dateFormat.parse(dateString);
            return date;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public static Date toDateGMT(String dateString, String format) {
        if (dateString.contains(".") && dateString.endsWith("Z")) {
            dateString = dateString.substring(0, dateString.indexOf(".")) + "Z";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date;
        try {
            date = dateFormat.parse(dateString);
            return date;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public static String livingTime(Date fromDate, Date toDate) {
        long diff = Math.abs(toDate.getTime() - fromDate.getTime());

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        if (diffHours == 0) {
            return diffMinutes + ":" + diffSeconds;
        }

        return diffHours + ":" + diffMinutes + ":" + diffSeconds;

    }

    public static String duringTime(Date fromDate, Date toDate) {
        long diff = Math.abs(toDate.getTime() - fromDate.getTime());

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        if (diffHours == 0) {
            return diffMinutes + " minutes " + diffSeconds + " second";
        }
        return diffHours + " hours " + diffMinutes + " minutes ";
    }

    public static int yearAgo(Date birthDate) {
        if ((birthDate != null)) {
            long currentTime = System.currentTimeMillis();
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(currentTime);

            Calendar birthDay = Calendar.getInstance();
            birthDay.setTimeInMillis(birthDate.getTime());

            //Get difference between years
            return now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        }
        return 0;
    }

    public static String getTimeAgo(Date fromDate) {
        long diff = Math.abs(new Date().getTime() - fromDate.getTime());

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        // long diffDays = diff / (24 * 60 * 60 * 1000);
        if (diffHours == 0) {
            if (diffMinutes == 0) {
                return diffSeconds + " seconds ago";
            } else {
                return diffMinutes + " minutes ago";
            }
        }
        return diffHours + " hours ago";
    }

    //yyyy-mm-dd
    public static String addStartTime(String from) {
        if (from != null && from.trim().length() >= 10) {
            from = from.substring(0, 10) + " 00:00:00";
        }
        return from;
    }

    //yyyy-mm-dd plus 1 day
    public static String addEndTime(String to) {
        if (to != null && to.trim().length() >= 10) {
            to = to.substring(0, 10) + " 23:59:59";
        }
        return to;
    }


    /**
     * GMT format ago day
     *
     * @param ago
     * @return Date
     */
    public static Date getDateGMT(int ago) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, ago);
        return calendar.getTime();

    }


    /**
     * Get Local system now
     *
     * @return
     */
    public static Date getNow() {
        try {
            Calendar calendar = Calendar.getInstance();
            return calendar.getTime();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }


    /**
     * addDate to now
     *
     * @param day
     * @return
     */
    public static Date addDate(int day) {
        return addDate(new Date(), day);
    }


    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static String toMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return toString(calendar.getTime());
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    public static Date addYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static Date addDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    public static Date createDate(int hour, int minute) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        calendar.set(year, month, date, hour, minute, 0);
        return calendar.getTime();
    }

    public static Date subMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static Date addLocalDate(int day) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();

    }

    /**
     * Get add minutes from specified date
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static Date addMinutes(int minutes) {
        return addMinutes(new Date(), minutes);
    }

    public static Date addSeconds(int seconds) {
        return addSeconds(new Date(), seconds);
    }

    public static Date addSeconds(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static String toNow() {
        return toString(new Date(), yyyyMMddHHmmss);
    }

    public static Long getTimeNow() {
        return new Date().getTime();
    }

    public static List<String> getListDate(String fromDate, String toDate) {

        List<String> lst = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(fromDate);
            calendar.setTime(date);
            while (df.format(calendar.getTime()).compareTo(toDate) <= 0) {
                lst.add(df.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);

            }
        } catch (ParseException e) {
            logger.error("error get list date from_date =" + fromDate + " to_date=" + toDate, e);
        }
        return lst;
    }

    public static List<String> getListDateByHourRange(String fromDate, String toDate, Integer timeDelta) {

        List<String> lst = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
        try {
            Date date = df.parse(fromDate);
            calendar.setTime(date);
            while (df.format(calendar.getTime()).compareTo(toDate) <= 0) {
                lst.add(df.format(calendar.getTime()));
                calendar.add(Calendar.HOUR_OF_DAY, timeDelta);
            }
        } catch (ParseException e) {
            logger.error("error get list date from_date =" + fromDate + " to_date=" + toDate, e);
        }
        return lst;
    }

    public static String convertTimeLong(Long time, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(time));
    }

    public static List<String> getListHourly(Long startTime, int range) {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat df = new SimpleDateFormat(format);
        String hourString = convertTimeLong(startTime, format);
        List<String> lst = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(df.parse(hourString));
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            for (int i = 0; i < range; i++) {
                lst.add(df.format(calendar.getTime()));
                calendar.add(Calendar.HOUR_OF_DAY, 1);
            }
        } catch (ParseException e) {
            logger.error("error get list hour hour =" + hourString + " delta=" + range, e);
        }
        return lst;
    }

    public static Long timeLongToHour(Long timeLong) {
        long tmp = timeLong % (60 * 60 * 1000);
        return timeLong - tmp;
    }

    public static String convertHourLong(Long time) {
        Date date = new Date(time);
        DateFormat formatter = new SimpleDateFormat("HH");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

    public static Date getStartTime(String fromDate) throws ParseException {
        SimpleDateFormat reconcileFormat = new SimpleDateFormat(YYYYMMDD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reconcileFormat.parse(fromDate));
        return calendar.getTime();
    }

    public static Date getEndTime(String toDate) {
        SimpleDateFormat reconcileFormat = new SimpleDateFormat(YYYYMMDD);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(reconcileFormat.parse(toDate));
            calendar.add(Calendar.HOUR, 23);
        } catch (ParseException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return calendar.getTime();
    }

    public static String getDateYesterday() {
        Instant now = Instant.now();
        Instant yesterday = now.minus(1, ChronoUnit.DAYS);
        Date date = Date.from(yesterday);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static String get() {
        Instant now = Instant.now();
        Instant yesterday = now.minus(1, ChronoUnit.DAYS);
        Date date = Date.from(yesterday);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }


    public static String getDateYesterday(String format) {
        Instant now = Instant.now();
        Instant yesterday = now.minus(1, ChronoUnit.DAYS);
        Date date = Date.from(yesterday);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static String getDatePlusDay(int i, String format) {
        Instant now = Instant.now();
        Instant yesterday = now.plus(i, ChronoUnit.DAYS);
        Date date = Date.from(yesterday);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static String formatDate(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date getFirstDayOfLastMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date getLastDayOfLastMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static String TimestampToString_ddMMyyyyHHmmFileName(Timestamp ts) {
        Date date = new Date();
        date.setTime(ts.getTime());
        return new SimpleDateFormat(FormateerddMMYYYY_FileName).format(date);
    }

    public static boolean checkTimeout(int timeEnd) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        int hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        int time = hour * 3600 + minute * 60 + seconds;
        return time > timeEnd;
    }

    public static SimpleDateFormat getDFormat(String pattern) {
        ThreadLocal<SimpleDateFormat> tl = POOL.get(pattern);
        if (tl == null) {
            synchronized (LOCK) {
                tl = POOL.get(pattern);
                if (tl == null) {
                    final String p = pattern;
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected synchronized SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(p);
                        }
                    };
                    POOL.put(p, tl);
                }
            }
        }
        return tl.get();
    }
    public static Date parse(String date, String pattern) {
        if (date != null) {
            if (pattern == null || "".equals(pattern)) {
                return null;
            }
            DateFormat format = getDFormat(pattern);
            try {
                return format.parse(date);
            } catch (ParseException e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
        return null;
    }
    public static Date parseDate(String date) {
        return parse(date, DATE_FMT);
    }

    public static String toTime(String dateStr) { //dd/MM/yyyy
        if(!dateStr.isEmpty()){
            Date date = parseDate(dateStr);
            return toTime(date);
        }
        return "";
    }

    public static String toTimeAddDay(String dateStr) { //dd/MM/yyyy
        if(!dateStr.isEmpty()){
            Date date = toDateAddDay(dateStr);
            return toTime(date);
        }
        return "";
    }

    public static Date string2Date(String strDate, String pattern) {
        if (StringUtil.isEmpty(strDate) || StringUtil.isEmpty(pattern)) return null;

        try {
            return getDFormat(yyyyMMddHHmmss).parse(strDate);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public static Date toDateYYYYMMDDHHMMSS(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date;
        try {
            date = dateFormat.parse(dateString);
            return date;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public static Date toDateYYYYMMDDHHMMSSAddss(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date;
        try {
            date = dateFormat.parse(dateString);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.SECOND, 1);
            date = c.getTime();
            return date;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public static Date toDateYYYYMMDDHHMMSSAddDay(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date;
        try {
            date = dateFormat.parse(dateString);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
            return date;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException e) {
        logger.error(ExceptionUtils.getStackTrace(e));
        }

        return date != null;
    }

    public static Date toDateYYYYMMDDHHMMSSApartMm(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date;
        try {
            date = dateFormat.parse(dateString);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MINUTE, -15);
            date = c.getTime();
            return date;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public static String toTimeFormatByGMT(Date date) {
        if(date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY, hours);

            date = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT_EXPORT);
            try {
                return dateFormat.format(date);
            } catch (Exception e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
        return "";

    }

    public static String TimestampToString_ddMMyyyyHHmmFileName() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormateerddMMYYYY_FileName);
        return simpleDateFormat.format(calendar.getTime());
    }
}
