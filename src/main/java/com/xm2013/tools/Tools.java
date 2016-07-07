package com.xm2013.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author tuxming
 * @created: 2016/04/19
 */
public class Tools {
	
	/**
     * 检测字符串是否绝对为空
     *	null   ==> true
     *	"null" ==> true
     *	""	   ==> true
     *  "   "  ==> true
     * @param s --> java.lang.String
     * @return 为空则返回true，否则返回false
     */
    public static boolean isAbsEmpty(String s) {
    	if(s==null)
    		return true;
    	if("".equals(s))
    		return true;
    	if("null".equals(s))
    		return true;
    	if(s.trim().length()==0)
    		return true;
    	return false;
    }
    
    /**
     * 检测字符串是否绝对为空
     *	null   ==> false
     *	"null" ==> false
     *	""	   ==> false
     *  "   "  ==> false
     * @param s java.lang.String
     * @return 不为空则返回true，否则返回false
     */
    public static boolean isNotAbsEmpty(String s){
    	return !isAbsEmpty(s);
    }
    
    /**
     * 判断字符串是否为空
     * null   ==> false
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean isEmpty(String s){
    	if(null==s)
    		return true;
    	if("".equals(s.trim()))
    		return true;
    	return false;
    }
    
    /**
     * 判断字符串是否不为空
     * null   ==> false
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean isNotEmpty(String s){
    	return !isEmpty(s);
    }
    
    /**
     * 把时间根据时、分、秒转换为时间段
     *
     * @param StrDate
     */
    public static String getTimes(String StrDate) {
        String resultTimes = "";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now;

        try {
            now = new Date();
            Date date = df.parse(StrDate);
            long times = now.getTime() - date.getTime();
            long day = times / (24 * 60 * 60 * 1000);
            long hour = (times / (60 * 60 * 1000) - day * 24);
            long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

            StringBuffer sb = new StringBuffer();
            // sb.append("发表于：");
            if (hour > 0) {
                sb.append(hour + "小时前");
            } else if (min > 0) {
                sb.append(min + "分钟前");
            } else {
                sb.append(sec + "秒前");
            }

            resultTimes = sb.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultTimes;
    }
    
    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern
                    .compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

	/**
	 * 获取两个日历的月份之差
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int monthsDiff(Date d1,Date d2) {
		Calendar c1=Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2=Calendar.getInstance();
		c2.setTime(d2);
		return (c2.get(Calendar.YEAR) - c1
				.get(Calendar.YEAR))
				* 12
				+ c2.get(Calendar.MONTH)
				- c1.get(Calendar.MONTH);
	}

    /**
     * 获取两个日历的天数之差
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int daysDiff(Date beginDate, Date endDate) {
    	
    	Long startTime = beginDate.getTime();
    	Long endTime = endDate.getTime();
    	
    	return (int)(startTime - endTime)/(24*1000*3600);
    	
    	/*
        Calendar s = Calendar.getInstance();
        s.setTime(strToDate(dateToStr(beginDate, "yyyy-MM-dd"), "yyyy-MM-dd"));
        Calendar e = Calendar.getInstance();
        e.setTime(strToDate(dateToStr(endDate, "yyyy-MM-dd"), "yyyy-MM-dd"));
        int days = 0;

        if (e.after(s)) {
            while (e.after(s)) {
                days++;
                s.add(Calendar.DATE, 1);
            }
            return days;
        } else if (s.after(e)) {
            return -1;
        } else {
            return 0;
        }*/
    }
    
    /**
     * 按照参数format的格式，日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }
    
    /**
     * 按照格式，字符串转日期(如yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @param format
     * @return
     */
    public static Date strToDate(String date, String format) {
        if (isNotAbsEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        } else {
            return null;
        }
    }
    
    /*
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    */
}
