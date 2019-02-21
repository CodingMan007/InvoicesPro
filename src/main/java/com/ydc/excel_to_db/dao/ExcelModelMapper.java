package com.ydc.excel_to_db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ydc.excel_to_db.domain.ExcelModel;
import com.ydc.excel_to_db.domain.IndentModel;
import com.ydc.excel_to_db.domain.SpecificationModel;
import com.ydc.excel_to_db.util.Constant;
import com.ydc.excel_to_db.vo.IndentModelVo;
import com.ydc.excel_to_db.vo.SpecificationModelVo;

/**
 * @Description: 为了方便扩展, 这里直接使用注解的形式进行绑定sql语句,对应的实体类：com.ydc.excel_to_db.domain.ExcelModel
 * @Author: Joss xu
 * @Date: Created in  2018-2-6
 */
@Mapper
public interface ExcelModelMapper {
    /**
     * @Description: 通过“IGNORE”关键字，使插入数据的主键“已存在”时也不会报异常
     * @Param: [excelModel]
     * @Retrun: long 插入成功，返回 1，插入失败，返回 0；
     */
	
    // mysql数据库sql
    @Insert("insert  ignore into excelmodel(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11,col12, col13, col14, col15, col16, col17,col18)values("
            + "#{col1}, #{col2}, #{col3}, #{col4}, #{col5},#{col6},#{col7},#{col8} ,#{col9} ,#{col10} ,#{col11} ,#{col12} ,#{col13} ,#{col14} ,#{col15},#{col16},#{col17},#{col18})")
    long insert(ExcelModel excelModel);
    
    /*
     * 按单号写入
     */
    @Insert("insert  ignore into indentModel(sale_date, indent_number, customer_code, customer_name, purchase_quantity, taxincluded_amount, arrearages_goods, zieher,is_generate_invoice, is_merge)values("
    		+ "#{col1}, #{col2}, #{col3}, #{col4}, #{col5},#{col6},#{col7},#{col8}, 0, 0)")
    long insertIndent(IndentModel indentModel);
   
    /*
     * 写入规格表
     */
    @Insert("insert  ignore into specificationModel(name, specification, sale_date,indent_number, customer_name, quantity, measurement_unit, taxincluded_price, taxincluded_amount, pre_tax, taxes,is_generate_invoice,is_print)values("
    		+ "#{col1}, #{col2}, #{col3}, #{col4}, #{col5},#{col6},#{col7},#{col8} ,#{col9} ,#{col10} ,#{col11} ,0 ,0)")
    boolean insertSpecification(SpecificationModel specificationModel);
    
    /*
     * 写入临时规格表数据
     */
    @Insert("insert  ignore into specificationModel_tmp("
    		+ "name, specification, sale_date,indent_number, customer_name, "
    		+ "quantity, measurement_unit, taxincluded_price, taxincluded_amount, pre_tax, taxes,is_generate_invoice,is_print)values("
    		+ "#{name}, #{specification}, #{saledate}, #{indentnumber}, #{customername},"
    		+ "#{quantity},#{measurementunit},#{taxincludedprice} ,#{taxincludedamount} ,#{taxes} ,#{taxes} ,#{isgenerateinvoice} ,0)")
    boolean insertSpecificationTmp(SpecificationModelVo specificationModelVo);
   
    /*
     * 清除规格临时表数据
     */
    @Delete("DELETE FROM specificationModel_tmp")
    void deleteSpecificationModeltmp();
    
    
    /*
     * 写入合并表
     */
    @Insert("insert  ignore into specification_merge("
    		+ "id, name, specification, sale_date,indent_number, customer_name, "
    		+ "quantity, measurement_unit, taxincluded_price, taxincluded_amount, pre_tax, taxes,is_generate_invoice,is_print,spbm_code)values("
    		+ "#{id}, #{name}, #{specification}, #{saledate}, #{indentnumber}, #{customername},"
    		+ "#{quantity},#{measurementunit},#{taxincludedprice} ,#{taxincludedamount} ,#{taxes} ,#{taxes} ,#{isgenerateinvoice} ,0,#{spbmcode})")
    long insertSpecificationMerge(SpecificationModelVo specificationModelVo);
   
    /*
     * 清除规格临时表数据
     */
    @Delete("DELETE FROM specification_merge WHERE id = ${id} ")
    void deleteSpecificationMerge(@Param("id")String id);
    
    /*
     * 清除单号表数据
     */
    @Delete("DELETE FROM indentmodel")
    boolean deleteAllIndentmodell();
    /*
     * 清除打印表数据
     */
    @Delete("DELETE FROM printmodel")
    boolean deleteAllPrintmodel();
    /*
     * 清除规格合并表数据
     */
    @Delete("DELETE FROM specification_merge")
    boolean deleteAllSpecificationMerge();
    /*
     * 清除规格表数据
     */
    @Delete("DELETE FROM specificationmodel")
    boolean deleteAllSpecificationmodel();
    
    /*
     * 清除规格表数据
     */
    @Delete("DELETE FROM specificationmodel WHERE is_generate_invoice =1")
    boolean deleteAllSpecificationmodelByGenerate();
    
    /*
     * 清除规格临时表数据
     */
    @Delete("DELETE FROM specificationmodel_tmp")
    boolean deleteAllSpecificationmodelTmp();
    
    /*
     * 查询合并表所有数据
     */
    @Select("SELECT id, indent_number, `name`, specification, sale_date, customer_name,"
    		+ "SUM(quantity) as quantity,"
    		+ "measurement_unit,"
    		+ "AVG(taxincluded_price) as taxincluded_price,"
    		+ "SUM(taxincluded_amount) as taxincluded_amount,"
    		+ "SUM(pre_tax) as pre_tax,"
    		+ "SUM(taxes) as taxes,"
			+ "is_generate_invoice,is_print ,"
			+ "is_generate_invoice,is_print,spbm_code " + 
    		"FROM  specification_merge " + 
    		"GROUP BY `name` , left(specification, 3)")
    List<SpecificationModelVo> selectSpecificationMerge();
    
    
    /*
	 * 合并按单号与规格前三位的数据
	 */
	@Select("SELECT id, indent_number, `name`, specification, sale_date, customer_name,SUM(quantity) as quantity," + 
			"measurement_unit,taxincluded_price, SUM(taxincluded_amount)  as taxincluded_amount,SUM(pre_tax) as pre_tax," + 
			"SUM(taxes) as taxes,is_generate_invoice,is_print ,is_generate_invoice " + 
			"FROM  specificationModel_tmp "+
			"WHERE indent_number = #{col4} ")
			//+ " AND LEFT(specification,3) = #{col2}")
	SpecificationModelVo selectSpecificationByindentNumberAndSpecificationAll(SpecificationModel excelModel);
	
	
	/*
	 * 规格表未生成发票状态的所有数据
	 */
	@Select("SELECT * FROM  specificationModel  WHERE is_generate_invoice <> 1 ORDER BY indent_number,sale_date LIMIT 0 ,2000")
	List<SpecificationModelVo> selectSpecificationByNotGAll();
	
	/*
	 * 导出还未开票的数据
	 */
	@Select("SELECT "
			+ "name as col1, "
			+ "specification as col2, "
			+ "sale_date as col3,"
			+ "indent_number as col4, "
			+ "customer_name as col5, "
			+ "quantity as col6, "
			+ "measurement_unit as col7, "
			+ "taxincluded_price as col8, "
			+ "taxincluded_amount as col9, "
			+ "pre_tax as col10, "
			+ "taxes as col11 "
			+ "FROM  specificationModel  WHERE is_generate_invoice <> 1 ORDER BY indent_number,sale_date")
	List<Map<String, Object>> exportSpecificationByNotGAll();
	
	
	/*
	 * 规格表已生成发票状态的所有数据
	 */
	@Select("SELECT * FROM  specificationModel  WHERE is_generate_invoice <> 0 ORDER BY indent_number, sale_date")
	List<SpecificationModelVo> selectSpecificationByIsGAll();

	/*
	 * 按时间查规格所有数据
	 */
	@Select("SELECT * FROM  specificationModel WHERE is_generate_invoice = ${isgenerateinvoice} AND sale_date between '${startTime}' and '${endTime}' ORDER BY indent_number,sale_date")
	//@ResultMap("com.ydc.excel_to_db.vo.SpecificationModelVo")  
	List<SpecificationModelVo> selectSpecificationByDate(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("isgenerateinvoice")int isgenerateinvoice);
	
	/*
	 * 按时间\客户名称查规格所有数据
	 */
	@Select("SELECT * FROM  specificationModel WHERE customer_name ='${customername}' AND is_generate_invoice = ${isgenerateinvoice} AND sale_date between '${startTime}' and '${endTime}' ORDER BY indent_number,sale_date")
	//@ResultMap("com.ydc.excel_to_db.vo.SpecificationModelVo")  
	List<SpecificationModelVo> getResultSpecificationDateAndNameData(@Param("customername")String customername,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("isgenerateinvoice")int isgenerateinvoice);
	
	/*
	 * 按客户名查规格所有数据
	 */
	@Select("SELECT * FROM  specificationModel WHERE is_generate_invoice = ${isgenerateinvoice} AND customer_name ='${customername}' ORDER BY indent_number,sale_date")
	//@ResultMap("com.ydc.excel_to_db.vo.SpecificationModelVo")  
	List<SpecificationModelVo> selectSpecificationByName(@Param("customername")String customername,@Param("isgenerateinvoice")int isgenerateinvoice);
	
	/*
	 * 按客户ID查规格所有数据
	 */
	@Select("SELECT * FROM  specificationModel WHERE id = ${id}")
	//@ResultMap("com.ydc.excel_to_db.vo.SpecificationModelVo")  
	SpecificationModelVo selectSpecificationByID(@Param("id")String id);
	
	/*
	 * 按客户ID统计规格所有发票金额
	 */
	@Select("SELECT (SUM(taxincluded_amount)/"+Constant.HSLV+")  as taxincluded_amount FROM  specificationModel WHERE id = ${id} and is_generate_invoice<>0 ORDER BY indent_number,sale_date")
	//@ResultMap("com.ydc.excel_to_db.vo.SpecificationModelVo")  
	SpecificationModelVo sumSpecificationByID(@Param("id")String id);
	
	
	/*
	 * 根据订单号查询所有数据
	 */
	@Select("SELECT * FROM  specificationModel specificationModel WHERE indent_number = '${indentNumber}' and is_generate_invoice<>1 ORDER BY indent_number,sale_date")
	//    @ResultMap("com.ydc.excel_to_db.vo.SpecificationModelVo")  
	List<SpecificationModelVo> selectSpecificationByIndentNumber(@Param("indentNumber")String indentNumber);
	
	
	/*
	 * 订单中还未生成发票的所有单号数据
	 */
	@Select("SELECT * FROM  indentModel WHERE is_generate_invoice <> 1 ORDER BY sale_date")
	//    @ResultMap("com.ydc.excel_to_db.vo.SpecificationModelVo")  
	List<IndentModelVo> selectIndentByAll();
	
	/*
	 * 订单所有数据 内部调用
	 */
	@Select("SELECT * FROM  indentModel WHERE is_generate_invoice = 0 and is_merge=0")
	//    @ResultMap("com.ydc.excel_to_db.vo.SpecificationModelVo")  
	List<IndentModelVo> selectIndentByIsAll();
	
	
	/*
	 * 按时间查订单所有数据
	 */
	@Select("SELECT * FROM  indentModel WHERE sale_date between '${startTime}' and '${endTime}'")
	//@ResultMap("com.ydc.excel_to_db.vo.SpecificationModelVo")  
	List<IndentModelVo> selectIndentByDate(@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	/*
	 * 按客户代码查询订单所有数据
	 */
	@Select("SELECT * FROM  indentModel WHERE customer_code like '%${customerCode}%'")
	//@ResultMap("com.ydc.excel_to_db.vo.SpecificationModelVo")  
	List<IndentModelVo> selectIndentByCustomerCode(@Param("customerCode")String customerCode);
	
	/*
	 * 按ID更新单表是否已生成发票数据
	 */
	@Update("UPDATE indentModel SET is_merge = 1 WHERE id=#{id}")
	int updateByIsGenerateInvoice(IndentModelVo indentModelVo);
	
	/*
	 * 按ID更新是否已生成发票数据
	 */
	@Update("UPDATE specificationModel SET is_generate_invoice = 1 WHERE id=${id}")
	int updatespecificationByIsGenerateInvoice(@Param("id")String id);
	
	/*
	 * 按ID更新已生成发票的数据为未生成
	 */
	@Update("UPDATE specificationModel SET is_generate_invoice = 0 WHERE id=${id}")
	int updatespecificationByNotGenerateInvoice(@Param("id")String id);
	
	
	
	
	
	
}
