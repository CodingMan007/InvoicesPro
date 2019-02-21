package com.ydc.excel_to_db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ydc.excel_to_db.domain.SpecificationModel;
import com.ydc.excel_to_db.vo.IndentModelVo;
import com.ydc.excel_to_db.vo.SpecificationModelVo;




public interface InvoicesService {
    
	/**
	 * @Description: 获取规格表的所有未生成发票数据
	 */
	List<SpecificationModelVo> getResultSpecificationAllNotGData();
	
	/**
	 * @Description: 获取规格表的所有已生成发票数据
	 */
	List<SpecificationModelVo> getResultSpecificationAllIsGData();

	/**
	 * @Description: 更新生成发票状态
	 * 0：未生成发票
	 * 1：已生成发票
	 */
	 boolean putResultSpecificationIdData(String id,String invoicetype);
	
    /**
     * @Description: 获取规格表内指定时间范围内的数据
     */
	List<SpecificationModelVo> getResultSpecificationDateAndNameData(
			@Param("customername")String customername,
			@Param("startTime")String startTime,
			@Param("endTime")String endTime ,
			@Param("isgenerateinvoice")int isgenerateinvoice);
	
	/**
	 * @Description: 获取规格表内指定时间\客户名称范围内的数据
	 */
	List<SpecificationModelVo> getResultSpecificationDateData(
			@Param("startTime")String startTime,
			@Param("endTime")String endTime ,
			@Param("isgenerateinvoice")int isgenerateinvoice);
	
	/**
	 * @Description: 获取规格表内指定用户名的数据
	 */
	List<SpecificationModelVo> getResultSpecificationNameData(@Param("customername")String customername,@Param("isgenerateinvoice")int isgenerateinvoice);
	
	/**
	 * @Description: 获取以订单的所有数据
	 */
	List<SpecificationModelVo> getResultSpecificationNumberAllData(@Param("indentNumber")String indentNumber);
	
	/**
	 * @Description: 获取订单表的所有数据
	 */
	List<IndentModelVo> getResultIndentAllData();
	
	/**
	 * @Description: 获取指定时间范围内的订单数据
	 */
	List<IndentModelVo> getResultIndentModelDateData(@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	/**
	 * @Description: 获取指定客户代码的订单数据
	 */
	List<IndentModelVo> getResultIndentModelCustomerCodeData(@Param("customerCode")String customerCode);
	
	/**
	 * @Description: 根据用户提交数据生成XML数据
	 */
	//boolean putIndentModelToXml(String buildtype);
	
	
	SpecificationModelVo  getResultSpecificationId(SpecificationModelVo specificationModelVo);
	
	/**
	 * @Description: 根据用户提交ID数据重置为未开数据
	 */
	int  putResultSpecificationId(SpecificationModelVo specificationModelVo);
	
	/**
	 * @Description: 清除所有的原始数据
	 */
	boolean  delInvoicesDeleteAll();
	
	/**
	 * @Description: 清除所有的原始里已生成的数据
	 */
	boolean  delInvoicesDeleteGenerate();
	
	
	
}
