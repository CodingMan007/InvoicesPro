package com.ydc.excel_to_db.util.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.UUID;

/**
 * @author LX
 * @createDate 2017年9月19日 下午3:19:02
 * @updateDate 2017年9月19日 下午3:19:02
 * @description 常用工具
 */
public class Tools {

	public static Date getCurrentTime() {
		return new Date();

	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获取新的字符串拼接
	 * @param str "a,b,c"
	 * @return newStr "'a','b','c'"
	 */
	public static String getInStr(String str){
		if(str != null && str != ""){
			String newStr = "";
			String[] arr = str.split(",");
			for(int i=0; i < arr.length; i++){
				String temp = arr[i];
				newStr += "'" + temp + "',";
			}
			return newStr.substring(0,newStr.length() - 1);//去掉最后的,号
		}else{
			return "";
		}
	}
	
	/**
     * 四舍五入
     * @param dou 要四舍五入的数
     * @param num 要保留的位数
     * @return
     */
    public static double formatDouble(double d,int num) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(num, RoundingMode.UP);
        return bg.doubleValue();
    }
    
    /**
     * 不需要四舍五入
     * @param dou 要四舍五入的数
     * @param num 要保留的位数
     * @return
     */
    public static double formatStirng(double d,int num) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(num, RoundingMode.DOWN);
        return bg.doubleValue();
    }
    
    /**
     * 除法运算
     * @param d1 分子 
     * @param d2 分母
     * @param len 四舍五入，保留的位数
     * @return
     */
    public static double division(double d1, double d2, int len) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
    /**
     *  位数长度补位
     * @param str 原字符
     * @param strLength  补位长度
     * @return
     */
    public static String addZeroForNum(String str, int strLength) {
	    int strLen = str.length();
	    if (strLen < strLength) {
	        while (strLen < strLength) {
	            StringBuffer sb = new StringBuffer();
//	            sb.append("0").append(str);// 左补0
	            sb.append(str).append("0");//右补0
	            str = sb.toString();
	            strLen = str.length();
	        }
	    }

	    return str;
	}
    
    
    /**
     * 过滤汉字
     * @param chin
     * @return
     */
	public static String filterChinese(String chin) {
		return chin.replaceAll("[\\u4e00-\\u9fa5]", "");
	}
     
     /**
     * 过滤 字母、数字、汉字
     * @param character
     * @return
     */
    public static String filterAll(String character) {
		character = character.replaceAll("[a-zA-Z0-9]", "");
		return character;
	}
    
}
