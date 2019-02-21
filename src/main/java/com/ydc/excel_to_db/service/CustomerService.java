package com.ydc.excel_to_db.service;

import java.util.List;

import com.ydc.excel_to_db.domain.CustomerInfoModel;
import com.ydc.excel_to_db.vo.CustomerInfoModelVo;




public interface CustomerService {
    
	
	/**
	 * @Description: 获取客户信息表的所有数据
	 */
	List<CustomerInfoModelVo> getCusotmerInfoAllData();
	
	
	 /**
	  * @Description: 插入/更新 客户信息表
	  */
	 Long putResultCustomerInfoReplaceData(CustomerInfoModel customerInfoModel);
	 
}
