package com.ydc.excel_to_db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ydc.excel_to_db.domain.CustomerInfoModel;
import com.ydc.excel_to_db.domain.ProductCodeModel;
import com.ydc.excel_to_db.vo.CustomerInfoModelVo;

/**
 * @Description: 为了方便扩展, 这里直接使用注解的形式进行绑定sql语句,对应的实体类：com.ydc.excel_to_db.domain.ExcelModel
 * @Author: Joss xu
 * @Date: Created in  2018-2-6
 */
@Mapper
public interface ProductCodeModelMapper {
    /**
     * @Description: 通过“IGNORE”关键字，使插入数据的主键“已存在”时也不会报异常
     * @Param: [excelModel]
     * @Retrun: long 插入成功，返回 1，插入失败，返回 0；
     */

    /*
     * 商品编号数据
     */
    @Insert("insert  ignore into productcodeModel(customer_identification, customer_name, customer_code, customer_address, customer_telephone, customer_bank, customer_account)values("
    		+ "#{col1}, #{col2}, #{col3}, #{col4}, #{col5},#{col6},#{col7}")
    long insertProductCode(ProductCodeModel productCodeModel);
    
	
    /*
	 * 商品编号所有数据
	 */
	@Select("SELECT * FROM  productcodeModel")
	List<ProductCodeModel> selectProductCodeModelByAll();
	
	
	/*
	 * 商品编号的所有数据
	 */
	@Select("SELECT * FROM  productcodeModel WHERE id = #{id}")
	ProductCodeModel selectProductCodeModelByID(ProductCodeModel productCodeModel);
	
	/*
	 * 商品编号的名称所有数据
	 */
	@Select("SELECT * FROM  productcodeModel WHERE specification_type = #{specificationtype}")
	ProductCodeModel selectProductCodeModelByName(ProductCodeModel productCodeModel);
	
	
	
	
}
