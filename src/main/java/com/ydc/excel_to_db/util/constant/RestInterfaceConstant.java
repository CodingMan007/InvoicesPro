package com.ydc.excel_to_db.util.constant;

/**
 *
 * @Author: Joss xu
 * @version
 * @date 2016年10月13日
 */
public interface RestInterfaceConstant {

	int Code_200 = 200;
	String STATUS_200 = "success";
	
	int Code_405 = 405;
	
	int Code_500 = 500;
	String STATUS_500 = "erro";
	
	
	String STATUS_500_CODE = "HTTP_STATUS_500";
	String STATUS_500_MESSAGE = "内部服务器错误";
	String STATUS_450_CODE = "HTTP_STATUS_450";
	String STATUS_450_MESSAGE = "时间范围不能超过1年！";

}
