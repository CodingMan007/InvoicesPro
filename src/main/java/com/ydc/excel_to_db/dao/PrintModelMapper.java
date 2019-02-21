package com.ydc.excel_to_db.dao;

import java.util.List;

import javax.persistence.Column;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ydc.excel_to_db.domain.PrintModel;
import com.ydc.excel_to_db.vo.CustomerInfoModelVo;

/**
 * @Description: 为了方便扩展, 这里直接使用注解的形式进行绑定sql语句,对应的实体类：com.ydc.excel_to_db.domain.ExcelModel
 * @Author: Joss xu
 * @Date: Created in  2018-2-6
 */
@Mapper
public interface PrintModelMapper {
    /**
     * @Description: 通过“IGNORE”关键字，使插入数据的主键“已存在”时也不会报异常
     * @Param: [excelModel]
     * @Retrun: long 插入成功，返回 1，插入失败，返回 0；
     */

    /*
     * 导入客户信息数据
     */
    @Insert("insert  ignore into printModel(generate_name, customer_name, generate_Id, is_generate_invoice, is_print, generate_date, print_date,print_content,invoice_amount)"
    		+ "values("
    		+ "#{generateName}, #{customerName}, #{generateId}, #{isGenerateInvoice}, #{isPrint}, #{generateDate},#{printDate},#{printContent},#{invoiceamount})")
    long insertIndent(PrintModel PrintModel);
    
    /*
	 * 生成的打印信息的所有数据
	 */
	@Select("SELECT * FROM  printModel")
	List<PrintModel> selectPrintInfoModelByAll();
	
	/*
	 * 按名称查询所有数据
	 */
	@Select("SELECT * FROM  printModel WHERE generate_name = #{generateName}")
	PrintModel selectPrintInfoModelByName();
	
	/*
	 * 按名称查询所有数据
	 */
	@Select("SELECT * FROM  printModel WHERE id = #{id}")
	List<PrintModel> selectPrintInfoModelById(PrintModel printModel);
	
	/*
	 * 按ID更新打印状态
	 */
	@Update("update printModel set is_print =#{isPrint} ,print_date=#{printDate} WHERE generate_name = #{generateName}")
	long updatePrintInfoModelByName(PrintModel PrintModel);
	
	/*
	 * 按ID删除生成发票的记录
	 */
	@Delete("DELETE FROM printModel WHERE id = #{id}")
	long deletePrintInfoModelById(PrintModel PrintModel);
	
	
	
	
}
