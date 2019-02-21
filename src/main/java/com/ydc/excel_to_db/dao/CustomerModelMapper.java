package com.ydc.excel_to_db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ydc.excel_to_db.domain.CustomerInfoModel;
import com.ydc.excel_to_db.vo.CustomerInfoModelVo;

/**
 * @Description: 为了方便扩展, 这里直接使用注解的形式进行绑定sql语句,对应的实体类：com.ydc.excel_to_db.domain.ExcelModel
 * @Author: Joss xu
 * @Date: Created in  2018-2-6
 */
@Mapper
public interface CustomerModelMapper {
    /**
     * @Description: 通过“IGNORE”关键字，使插入数据的主键“已存在”时也不会报异常
     * @Param: [excelModel]
     * @Retrun: long 插入成功，返回 1，插入失败，返回 0；
     */

    /*
     * 导入客户信息数据
     */
    @Insert("insert ignore into customerinfoModel(customer_identification, customer_name, customer_code, customer_address, customer_telephone, customer_bank, customer_account)values("
    		+ "#{col1}, #{col2}, #{col3}, #{col4}, #{col5},#{col6},#{col7})")
    long insertIndent(CustomerInfoModel customerInfoModel);
    
	
    /*
     * 导入客户信息数据
     */
    @Insert("INSERT INTO customerinfoModel (customer_name, customer_code ) "
    		+ "SELECT #{col2}, #{col3} FROM DUAL WHERE NOT EXISTS(SELECT customer_name  FROM customerinfoModel WHERE customer_name =  #{col2})")
    long insertOrReplaceIndent(CustomerInfoModel customerInfoModel);
    
    
    /*
	 * 客户信息的所有数据
	 */
	@Select("SELECT * FROM  customerinfoModel group by customer_name")
	List<CustomerInfoModelVo> selectCustomerInfoModelByAll();
	
	
	/*
	 * 客户信息的所有数据
	 */
	@Select("SELECT * FROM  customerinfoModel WHERE id = #{id} or customer_name = #{customername}")
	CustomerInfoModelVo selectCustomerInfoModelByID(CustomerInfoModelVo customerInfoModelVo);
	
	
	
	
}
