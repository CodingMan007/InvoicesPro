package com.ydc.excel_to_db.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @Description: 为了方便扩展，这里的字段命名规则为col[列]+1[列数]
 *               例如：col1代表的就是第一列的数据，通过@Excel(name=?)注解进行区分
 * @Author: joss xu 
 * @Date: Created in 2018-2-6
 * 按单号
 */
@Data
@JsonSerialize(include=Inclusion.NON_EMPTY) 
public class CustomerInfoModel {
	
	@Excel(name = "纳税人识别号")
	@NotBlank(message = "该字段不能为空")
	private String col1;
	
	@Excel(name = "客户名称" )
	@NotBlank(message = "该字段不能为空")
	private String col2;
	
	@Excel(name = "客户代码")
	@NotBlank(message = "该字段不能为空")
	private String col3;
	
	@Excel(name = "客户地址")
	@NotBlank(message = "该字段不能为空")
	private String col4;
	
	@Excel(name = "客户电话")
	@NotBlank(message = "该字段不能为空")
	private String col5;
	
	@Excel(name = "客户开户行")
	@NotBlank(message = "该字段不能为空")
	private String col6;
	
	@Excel(name = "客户开户行账号")
	@NotBlank(message = "该字段不能为空")
	private String col7;

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	public String getCol4() {
		return col4;
	}

	public void setCol4(String col4) {
		this.col4 = col4;
	}

	public String getCol5() {
		return col5;
	}

	public void setCol5(String col5) {
		this.col5 = col5;
	}

	public String getCol6() {
		return col6;
	}

	public void setCol6(String col6) {
		this.col6 = col6;
	}

	public String getCol7() {
		return col7;
	}

	public void setCol7(String col7) {
		this.col7 = col7;
	}

}
