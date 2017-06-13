package com.kelee.frame.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by kelee on 2017-06-01.
 * 日历工具类
 */
public class CalendarUtils {
    /**
     * 完整的日期时间格式
     */
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 只有日期的格式
     */
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 只有时间的格式
     */
    public final static String TIME_FORMAT = "HH:mm:ss";
    /**
     * 带中文的日期格式(2000年01月01日)
     */
    public final static String DATE_FORMAT_WITH_CHINESE = "yyyy年MM月dd日";

    /**
     * 短时间格式(HH:mm)
     */
    public final static String SHORT_TIME_FORMAT = "HH:mm";
    private static final String TAG = "CalendarUtils";
    /**
     * 一天秒钟数
     */
    private static final long MILLS = 86400;

    /**
     * 私有构造
     */
    private CalendarUtils() {
    }

    /**
     * 20:30
     */
    public static String getShortTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_TIME_FORMAT);
        return sdf.format(c.getTime());
    }

    /**
     * 获取指定字符串中的时间
     *
     * @param data 指定字符串
     * @return
     */
    public static String getTime(String data) {
        Calendar calendar = transformStringToCalendar(data, DATE_TIME_FORMAT);
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_TIME_FORMAT);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取两个时间段相差的分钟数
     * @param data1
     * @param data2
     * @return
     */
    public static long computeTimeLag(String data1, String data2) {
        long mintus = 0;
        SimpleDateFormat format = new SimpleDateFormat(SHORT_TIME_FORMAT);
        try {
            long time1 = format.parse(data1).getTime();
            long time2 = format.parse(data2).getTime();
            mintus = (time2 - time1) / 60000;
            return mintus;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mintus;
    }

    /**
     * 把String类型的日期转换成Calendar对象
     *
     * @param string （日期时间:2000-00-00 00:00:00)
     */
    public static Calendar transformStringToCalendar(String string) {
        return transformStringToCalendar(string, DATE_TIME_FORMAT);
    }

    /**
     * 通过SimpleDataFormat格式把string转换成Calendar
     *
     * @param string 日期字符串
     * @param format 目标日期格式
     */
    public static Calendar transformStringToCalendar(String string, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 把日期字符串转换成TimeMillis
     */
    public static long transformStringToMillis(String string) {
        return transformStringToCalendar(string).getTimeInMillis();
    }

    /**
     * 通过TimeMillis转换成秒钟
     *
     * @param millis TimeMillis
     */
    public static long getSecondWithTimeMillis(long millis) {
        return millis / 1000;
    }

    /**
     * 返回两个日期相差的秒
     */
    public static long getIntervalInSeconds(Calendar calendar, Calendar targetCalendar) {
        return (calendar.getTimeInMillis() - targetCalendar.getTimeInMillis()) / 1000;
    }

    /**
     * 格式化日期
     *
     * @param string 有效的日期字符
     * @param format 格式化的格式
     */
    public static String formatWithString(String string, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(transformStringToCalendar(string).getTime());
    }

    /**
     * 格式化日期
     *
     * @param src          源日期字符
     * @param srcFormat    源日期格式
     * @param targetFormat 目标日期格式
     */
    public static String formatWithString(String src, String srcFormat, String targetFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(srcFormat);
        try {
            Date date = sdf.parse(src);
            SimpleDateFormat targetSdf = new SimpleDateFormat(targetFormat);
            return targetSdf.format(date);
        } catch (Exception e) {
            FL.e(TAG, "src=" + src + "  " + srcFormat + "  " + targetFormat);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化成日期时间
     *
     * @param string 将要被格式化的日期字符串
     * @return 格式:(2000-00-00 00:00:00)
     */
    public static String formatDateTimeWithString(String string) {
        return formatWithString(string, DATE_TIME_FORMAT);
    }

    /**
     * 格式化日期
     *
     * @param srcDate       源日期字符
     * @param srcDateFormat 源日期格式
     * @param targetFormat  目标格式
     */
    public static String formatDateTimeWithString(String srcDate, String srcDateFormat,
                                                  String targetFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(srcDateFormat);
        try {
            Date date = sdf.parse(srcDate);
            SimpleDateFormat parseSdf = new SimpleDateFormat(targetFormat);
            return parseSdf.format(date);
        } catch (ParseException e) {
            FL.e(TAG, "srcDate:"
                    + srcDate
                    + "  srcDateFormat:"
                    + srcDateFormat
                    + "   targetFormat"
                    + targetFormat);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期
     *
     * @param string 将要被格式化的日期字符串
     * @return 格式:(2000-00-00)
     */
    public static String formatDateWithString(String string) {
        return formatWithString(string, DATE_FORMAT);
    }

    /**
     * 格式化日期
     *
     * @param string 将要被格式化的日期字符串
     * @return 格式:(00:00:00)
     */
    public static String formatTimeWithString(String string) {
        return formatWithString(string, TIME_FORMAT);
    }

    /**
     * 格式化日期
     *
     * @param string 将要被格式化的日期字符串
     * @return 格式:(2000年01月01日)
     */
    public static String formatStringToChinese(String string) {
        return formatWithString(string, DATE_FORMAT_WITH_CHINESE);
    }

    /**
     * Data日期格式化成String
     *
     * @param date 将要被格式化的data
     * @return 格式:(2000-00-00 00:00:00)
     */
    public static String formatDateTimeWithDate(Date date) {
        return formatStringWithDate(date, DATE_TIME_FORMAT);
    }

    /**
     * 时间戳格式的数据格式化成需要的格式
     *
     * @param string 将要被格式化的时间戳
     * @return 格式:(2000-00-00 00:00:00)
     */
    public static String formatDateTimeWithTime(String string) {
        return formatStringWithDate(timeToData(string), DATE_TIME_FORMAT);
    }

    /**
     * 把中文日期（2000年01月01日)格式化成标准日期(2000-01-01)
     *
     * @param data 将要格式化的日期字符串
     * @return 格式:(2000-00-00);
     */
    public static String formatChineseDataToData(String data) {
        data = data.replace("年", "-");
        data = data.replace("月", "-");
        data = data.replace("日", "");
        return data;
    }

    /**
     * 日期格式化成String
     *
     * @param date 将要格式化的日期字符串
     * @return 格式：（format格式）
     */
    public static String formatStringWithDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 把String类型的时间转换成Calendar
     *
     * @param time 时间格式：00:00:00
     */
    public static Calendar transformStringTimeToCalendar(String time) {
        Calendar calendar = Calendar.getInstance();
        String[] split = time.split(":");
        if (split.length > 0) {
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(split[0]));
        }
        if (split.length > 1) {
            calendar.set(Calendar.MINUTE, Integer.parseInt(split[1]));
        }
        if (split.length > 2) {
            calendar.set(Calendar.SECOND, Integer.parseInt(split[2]));
        }
        return calendar;
    }

    /**
     * 把时间戳:1234567890123,转换成Date对象
     */
    public static Date timeToData(String time) {
        return new Date(Long.parseLong(time));
    }

    /**
     * 比较两个字符串日期的大小
     *
     * @return 小于0：srcData 小于 tagData;
     * 等于0：则srcData = tagData;
     * 大于0：则srcData 大余 tagData
     */
    public static int compare(String srcDate, String tagDate) {
        return srcDate.compareTo(tagDate);
    }

    /**
     * 返回现在的日期和时间
     *
     * @return 格式:2000-00-00 00:00:00
     */
    public static String getNowDataTime() {
        Calendar calendar = Calendar.getInstance();
        return formatDateTimeWithDate(calendar.getTime());
    }

    /**
     * 返回当前的日期
     *
     * @return 格式：2000-00-00
     */
    public static String getData() {
        return getNowDataTime().split(" ")[0];
    }

    /**
     * 获取当前日期
     *
     * @return 格式20170607
     */
    public static String getNowData() {
        return getData().replace("-", "");
    }


    /**
     * 返回当前时间
     *
     * @return 格式：00:00:00
     */
    public static String getTime() {
        return getNowDataTime().split(" ")[1];
    }

    /**
     * 获取今天往后指定天数的日期（年-月-号）
     *
     * @param day 指定天数
     * @return
     */
    public static List<String> getDatas(int day) {
        List<String> dates = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < day; i++) {
            Date date = new Date(currentTime + MILLS * i * 1000);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            String dateStr = simpleDateFormat.format(date);
            dates.add(dateStr);
        }
        return dates;
    }

    /**
     * 获取当前星期后指定天数的星期
     *
     * @param day 指定天数
     * @return
     */
    public static List<String> getWeeks(int day) {
        List<String> weeks = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < day; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(currentTime + MILLS * i * 1000);
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                weeks.add("星期天");
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                weeks.add("星期一");
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                weeks.add("星期二");
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                weeks.add("星期三");
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                weeks.add("星期四");
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                weeks.add("星期五");
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                weeks.add("星期六");
            }
        }
        return weeks;
    }

    /**
     * 得到当年当月的最大日期
     **/
    public static int MaxDayFromDay_OF_MONTH(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);//注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }


}
