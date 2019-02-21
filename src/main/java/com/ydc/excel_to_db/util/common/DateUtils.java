package com.ydc.excel_to_db.util.common;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日期转换等处理工具
 * @author xu
 *         Created on 2016-10-15
 */
public class DateUtils {

    public static final String FORMAT_DATE       = "yyyy-MM-dd";
    public static final String FORMAT_SHORT_DATE       = "yyyyMMdd";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 给Date增加second秒
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 给Date增加hourz小时
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 将UTC时间转成中国本地时间
     * @param date
     * @return
     */
    public static Date UTC2LocalTime(Date date) {
        return addHour(date, 8);
    }

    /**
     * 判断日期是否在有效期内
     * 返回true时已过期，false未过期
     * @param expire
     * @return
     */
    public static boolean isUserSessionExpire(Date expire) {
        boolean isExpire = true;
        Calendar now = Calendar.getInstance();
        Calendar expireCalendar = Calendar.getInstance();
        expireCalendar.setTime(expire);

        isExpire = now.after(expireCalendar);
        return isExpire;
    }

    /**
     * 格式化时间
     * @param date
     * @param formatPattern
     * @return
     */
    public static String date2String (Date date, String formatPattern)
    {
        String dateStr = null;
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
            dateStr = sdf.format(date);
        }
        return dateStr;
    }
    
    
    /**
     * 格式化时间
     * @param date
     * @param formatPattern
     * @return
     */
    public static Date getDate(Date date, String formatPattern){
    	
    	Date result = null;
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
            String dateStr = sdf.format(date);
            try {
				result = sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        return result;
    }

    /**
     * 字符串转日期
     * @param date
     * @param formatPattern
     * @return
     */
    public static Date string2Date (String date, String formatPattern)
    {
        Date result = null;
        if(StringUtils.isBlank(date)){
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatPattern);
            result = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 格式化日期
     * yyyy-MM-dd HH:mm:ss
     * @param datetime
     * @return
     */
    public static Date string2Datetime(String datetime) {
        if (StringUtils.isBlank(datetime)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE_TIME);

        Date date = null;
        try {
            date = format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 将时间戳转化成String时间
     * @param time
     * @return
     */
    public static String convertDateTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE_TIME);
        String outDate = format.format(time);
        return outDate;
    }

    /**
     * 取当前日期，无时分秒
     * @return
     */
    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 日期比较
     * @param date1
     * @param date2
     * @return date1 < date2 return -1
     *         date1 > date2 return  1
     *         date1 = date2 return  0
     */
    public static int comparator(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        if (calendar1.before(calendar2)) {
            return -1;
        } else if (calendar1.after(calendar2)) {
            return 1;
        } else {
            return 0;
        }
    }
    
    /**
     * 获取当前日期前N天日期
     * 正数： 当前日期之后N天日期
     * 负数： 当前日期之前N天日期
     * 日期格式： YYYY-MM-DD
     */
     @SuppressWarnings("static-access")
 	public static String getChooseDateStr(int num,String formatPattern){
     	SimpleDateFormat sf=new SimpleDateFormat(formatPattern);
 	    Calendar theCa = Calendar.getInstance();
 	    theCa.setTime(new Date());
 	    theCa.add(theCa.DATE, num);
 		return  sf.format(theCa.getTime());
     }
     
     /**
      * 得到给定日期N天前/后的日期
      * @param num  正数:获取给定日期之后的日期<br/>
      * @param num  负数:获取给定日期之前的日期
      * @param datestr 给定日期
      * @return
      */
     public static String GetDateArithmetic(String datestr,int num) {
         SimpleDateFormat sf = new SimpleDateFormat(FORMAT_DATE);
         Date date1;
 		try {
 			date1 = sf.parse(datestr);
 			  long time = date1.getTime()+(1000L * 60 * 60 * 24 * num);           
 		        Date date = new Date();
 		        if (time > 0) {
 		            date.setTime(time);
 		        }
 		        return sf.format(date);
 		} catch (ParseException e) {
 		}
         return null;

     }
     
     /**
      * 获取当前年份
      * @return
      */
     public static int getYear(){
     	Calendar calendar = Calendar.getInstance();
     	return calendar.get(Calendar.YEAR);
     }
     
 	/**
 	 * 获取当前月份
 	 * @return 
 	 */
 	public static String getMonth() {
 		Calendar calendar = Calendar.getInstance();
 		 int month = calendar.get(Calendar.MONTH) + 1;
 		 String moth1 = "";
 		if(month < 10){
 			moth1 = "0" + month;
 		}else{
 			moth1 = String.valueOf(month);
 		}
 		return moth1;
 	}
 	
    /**
     * 获取当前月第一天
     */
    public static String getFirstDate(){
		SimpleDateFormat sf=new SimpleDateFormat(FORMAT_DATE);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return sf.format(calendar.getTime());
	}
    
	/**
	 * @desc 获取某个时间段内所有日期
	 * @param beginDate
	 * @param endDate
	 * @return List<Date>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Date> getListDates(Date beginDate, Date endDate)  {  
		  List lDate = new ArrayList();  
		  lDate.add(beginDate);  
		  Calendar calBegin = Calendar.getInstance();  
		  // 使用给定的 Date 设置此 Calendar 的时间  
		  calBegin.setTime(beginDate);  
		  Calendar calEnd = Calendar.getInstance();  
		  // 使用给定的 Date 设置此 Calendar 的时间  
		  calEnd.setTime(endDate);  
		  // 测试此日期是否在指定日期之后  
		  while (endDate.after(calBegin.getTime()))  
		  {  
		   // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
		   calBegin.add(Calendar.DAY_OF_MONTH, 1);  
		   lDate.add(calBegin.getTime());  
		  }  
		  return lDate;  
	 }
	
    /**
	 * @author Qizj
	 * 判断传入年份是否是闰年
	 * @param year
	 * @return true/false
	 */
	public static boolean isLeapYear(int year){
		GregorianCalendar c = new GregorianCalendar();
		boolean flag = false;
		if (c.isLeapYear(year)){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @author Qizj
	 * 获取上一个月份
	 * @return
	 */
	public static String getLastMonth(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format =  new SimpleDateFormat("MM");
		String time = format.format(c.getTime());
		return time;
	}
	
	/**
	 * 获取下个月第一天
	 * @return ex: 2017-07-01
	 */
	public static String getNextMonthFirstDay(){
		
		String baseYearMonth = getYear()+getMonth(); 
		 try{
           SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");
           SimpleDateFormat sdf2 = new SimpleDateFormat ("yyyy-MM-dd");
           Date date = sdf.parse (baseYearMonth);
           Calendar calendar = Calendar.getInstance ();
           calendar.setTime (date);
           calendar.add (Calendar.MONTH, 1);
           return sdf2.format(calendar.getTime ());
       }catch (ParseException e){
           e.printStackTrace ();
       }
		 return null;
	}
	
	/**
	 * 根据周获取日期
	 * year:年份
	 * week:第几周
	 * day:星期几：1: 1表示周日，2表示周一...7表示周六
	 * @return ex: 2017-07-01
	 */
	public static String getDateByWeek(int year,int week,int day){
		
         SimpleDateFormat sdf2 = new SimpleDateFormat ("yyyy-MM-dd");
         Calendar calendar = Calendar.getInstance ();
         calendar.set(Calendar.YEAR, year);
         calendar.set(Calendar.WEEK_OF_YEAR,week);
         calendar.set(Calendar.DAY_OF_WEEK, day);
         return sdf2.format(calendar.getTime ());
       
	}
	
	 /**
     * 获取当前月所在季度所有月份数组
     * @author Joss xu
     * @return
     */
    public static String[] getQuarterMonths() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        
        String [] months = new String[3];
        try {
            if (currentMonth >= 1 && currentMonth <= 3){
                months[0]="01";
            	months[1]="02";
            	months[2]="03";
            }else if (currentMonth >= 4 && currentMonth <= 6){
            	months[0]="04";
	        	months[1]="05";
	        	months[2]="06";

            }else if (currentMonth >= 7 && currentMonth <= 9){
            	months[0]="07";
	        	months[1]="08";
	        	months[2]="09";
            }else if (currentMonth >= 10 && currentMonth <= 12){
            	months[0]="10";
	        	months[1]="11";
	        	months[2]="12";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return months;
    }
	
}