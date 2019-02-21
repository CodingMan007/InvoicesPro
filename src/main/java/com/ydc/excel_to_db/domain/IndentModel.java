package com.ydc.excel_to_db.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.ydc.excel_to_db.util.common.Tools;

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
public class IndentModel {
	
	@Excel(name = "销售日期", format = "yyyy-MM-dd")
	private Date col1;
	
	@Excel(name = "单号" )
	@NotBlank(message = "该字段不能为空")
	private String col2;
	
	@Excel(name = "客户代码")
	private String col3;
	
	@Excel(name = "客户名称")
	private String col4;
	
	@Excel(name = "购货数量")
	private BigDecimal col5;
	
	@Excel(name = "含税金额")
	private BigDecimal col6;
	
	@Excel(name = "仍欠货款")
	private String col7;
	
	@Excel(name = "开票人")
	private String col8;

	public Date getCol1() {
		return col1;
	}

	public void setCol1(Date col1) {
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
		return Tools.filterAll(col4);
	}

	public void setCol4(String col4) {
		this.col4 = Tools.filterAll(col4);
	}

	public BigDecimal getCol5() {
		return col5;
	}

	public void setCol5(BigDecimal col5) {
		this.col5 = col5;
	}

	public BigDecimal getCol6() {
		return col6;
	}

	public void setCol6(BigDecimal col6) {
		this.col6 = col6;
	}

	public String getCol7() {
		return col7;
	}

	public void setCol7(String col7) {
		this.col7 = col7;
	}

	public String getCol8() {
		return col8;
	}

	public void setCol8(String col8) {
		this.col8 = col8;
	}


}
