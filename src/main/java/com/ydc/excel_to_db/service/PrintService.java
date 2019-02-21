package com.ydc.excel_to_db.service;

import java.util.List;

import com.ydc.excel_to_db.domain.PrintModel;
import com.ydc.excel_to_db.domain.ProductCodeModel;




public interface PrintService {
    
	
	/**
	 * @Description: 生成的发票数据文件写入
	 */
	long  petPrintData(PrintModel printModel);
	
	
	/**
	 * @Description: 删除指定的ID数据
	 */
	long delPrintIdData(PrintModel printModel);
	
	/**
	 * @Description: 获取打印表的所有数据
	 */
	List<PrintModel> getPrintAllData();
	
	/**
	 * @Description: 根据ID获取打印表的所有数据
	 */
	List<PrintModel> getPrintByIdAllData(PrintModel printModel);
	
	
	/**
	 * @Description: 获取商品编码表的以名称为条件的所有数据
	 */
	ProductCodeModel getProductCodAllNameData(ProductCodeModel productCodeModel);
	
}
