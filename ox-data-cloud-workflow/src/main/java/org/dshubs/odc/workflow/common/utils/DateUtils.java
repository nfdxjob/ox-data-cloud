package org.dshubs.odc.workflow.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.dshubs.odc.workflow.common.exception.BaseException;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author hnrc
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyyMMdd", "yyyyMM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date getDateFromMonthStr(String str) throws BaseException {
        if (StringUtils.isEmpty(str)){
            throw new BaseException("日期格式错误");
        }
        str = str.replace("-","");
        String format = null;
        if (str.length() == 8){
            format = "yyyyMMdd";
        }else if(str.length() == 6){
            format = "yyyyMM";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            throw new BaseException("日期格式转换错误错误");
        }
        return parse;
    }


    /**
     * getMonthsBetween(查询两个日期相隔的月份)
     *
     * @param startDate 开始日期1 (格式yyyy-MM)
     * @param endDate   截止日期2 (格式yyyy-MM)
     * @return
     */
    public static int getMonthsDiff(String startDate, String endDate) throws ParseException {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(DateUtils.getDateFromMonthStr(startDate));
        c2.setTime(DateUtils.getDateFromMonthStr(endDate));
        int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        int month = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        return Math.abs(year * 12 + month);
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            DateFormat dFormat3 = new SimpleDateFormat("yyyyMMdd");
            return dFormat3.parse(str.toString());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date stringToDate(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }

        String patt = "(\\d{4})[/-]?(\\d{1,2})[/-]?(\\d{1,2})";
        boolean matches = dateString.matches(patt);
        if (matches) {
            Date date = null;
            try {
                date = (new SimpleDateFormat()).parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        } else {
            throw new BaseException("日期格式错误");
        }

    }

    public static Date preMonth(int num) {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) - num);
        return curr.getTime();
    }


    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }


    public static Date getPreMonth(String startDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(startDate));
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();

    }


    /**
     * 比较日期大小  startDate：开始日期 endDate：结束日期
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static boolean compareDate(String startDate, String endDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date start = simpleDateFormat.parse(startDate);
        Date end = simpleDateFormat.parse(endDate);

        if (start.compareTo(end) > 0) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * 字符串转换成Date格式
     *
     * @param dateStr 日期型字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date strToDate(String dateStr, String pattern) {
        try {
            if ((dateStr == null) || (dateStr.length() == 0)) {
                return null;
            }

            if (pattern == null) {
                pattern = YYYY_MM_DD;
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串转换的带time的Date格式
     *
     * @param dateStr
     * @return
     */
    public static Date strToDateTime(String dateStr) {
        try {
            return strToDate(dateStr, YYYY_MM_DD_HH_MM_SS);
        } catch (Exception e) {
            return null;
        }
    }


    public static void main(String[] args) throws ParseException {
        System.out.println(getMonthsDiff("2022-04", "2022-03"));
    }
}
