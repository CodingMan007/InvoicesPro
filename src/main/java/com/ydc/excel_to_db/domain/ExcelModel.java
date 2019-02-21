package com.ydc.excel_to_db.domain;

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
 * @Author: Joss xu
 * @Date: Created in 2018-2-6
 */
@Data
@JsonSerialize(include=Inclusion.NON_EMPTY) 
public class ExcelModel {
	// 官方文档 http://easypoi.mydoc.io/
	@Excel(name = "序号")
	@NotBlank(message = "该字段不能为空")
	@Max(value = 1000)
	private String col1;
	
	@Excel(name = "姓名")
	@Pattern(regexp = "[\\u4E00-\\u9FA5]{2,5}", message = "姓名中文2-5位")
	private String col2;
	
	@Excel(name = "性别")
	private String col3;
	
	@Excel(name = "出生年月", format = "yyyy.MM.dd")
	private Date col4;
	
	@Excel(name = "民族")
	private String col5;
	
	@Excel(name = "籍贯")
	private String col6;
	
	@Excel(name = "文化程度")
	private String col7;
	
	@Excel(name = "参工时间", format = "yyyy.MM")
	private Date col8;
	
	@Excel(name = "政治面貌")
	private String col9;
	
	@Excel(name = "职务")
	private String col10;
	
	@Excel(name = "现处室时间", format = "yyyy.MM")
	private Date col11;
	
	@Excel(name = "任现职位时间", format = "yyyy.MM")
	private Date col12;
	
	@Excel(name = "任现职级时间", format = "yyyy.MM")
	private Date col13;
	
	@Excel(name = "职称")
	private String col14;
	
	@Excel(name = "执业资格")
	private String col15;
	
	@Excel(name = "进局时间", format = "yyyy.MM")
	private Date col16;
	
	@Excel(name = "实务导师")
	private String col17;
	
	@Excel(name = "备注")
	private String col18;

	
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

	public Date getCol4() {
		return col4;
	}

	public void setCol4(Date col4) {
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

	public Date getCol8() {
		return col8;
	}

	public void setCol8(Date col8) {
		this.col8 = col8;
	}

	public String getCol9() {
		return col9;
	}

	public void setCol9(String col9) {
		this.col9 = col9;
	}

	public String getCol10() {
		return col10;
	}

	public void setCol10(String col10) {
		this.col10 = col10;
	}

	public Date getCol11() {
		return col11;
	}

	public void setCol11(Date col11) {
		this.col11 = col11;
	}

	public Date getCol12() {
		return col12;
	}

	public void setCol12(Date col12) {
		this.col12 = col12;
	}

	public Date getCol13() {
		return col13;
	}

	public void setCol13(Date col13) {
		this.col13 = col13;
	}

	public String getCol14() {
		return col14;
	}

	public void setCol14(String col14) {
		this.col14 = col14;
	}

	public String getCol15() {
		return col15;
	}

	public void setCol15(String col15) {
		this.col15 = col15;
	}

	public Date getCol16() {
		return col16;
	}

	public void setCol16(Date col16) {
		this.col16 = col16;
	}

	public String getCol17() {
		return col17;
	}

	public void setCol17(String col17) {
		this.col17 = col17;
	}

	public String getCol18() {
		return col18;
	}

	public void setCol18(String col18) {
		this.col18 = col18;
	}
	
	
	
	
}
