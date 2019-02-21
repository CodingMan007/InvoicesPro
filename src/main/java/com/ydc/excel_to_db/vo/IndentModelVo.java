package com.ydc.excel_to_db.vo;

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
public class IndentModelVo {
	
	//"id"
	private int id;
	
	//"销售日期", format = "yyyy-MM-dd hh:mm:ss")
	private String saledate;
	
	//"单号" )
	@NotBlank(message = "该字段不能为空")
	private String indentnumber;
	
	//"客户代码")
	private String customercode;
	
	//"客户名称")
	private String customername;
	
	//"购货数量")
	private BigDecimal purchasequantity;
	
	//"含税金额")
	private BigDecimal taxincludedamount;
	
	//"仍欠货款")
	private String arrearagesgoods;
	
	//"开票人")
	private String zieher;
	
	//"是否已生成发票 0：未生成  1：已生成
	private int isgenerateinvoice;

	
	

	public int getIsgenerateinvoice() {
		return isgenerateinvoice;
	}

	public void setIsgenerateinvoice(int isgenerateinvoice) {
		this.isgenerateinvoice = isgenerateinvoice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSaledate() {
		return saledate;
	}

	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}

	public String getIndentnumber() {
		return indentnumber;
	}

	public void setIndentnumber(String indentnumber) {
		this.indentnumber = indentnumber;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public BigDecimal getPurchasequantity() {
		return purchasequantity;
	}

	public void setPurchasequantity(BigDecimal purchasequantity) {
		this.purchasequantity = purchasequantity;
	}

	public BigDecimal getTaxincludedamount() {
		return taxincludedamount;
	}

	public void setTaxincludedamount(BigDecimal taxincludedamount) {
		this.taxincludedamount = taxincludedamount;
	}

	public String getArrearagesgoods() {
		return arrearagesgoods;
	}

	public void setArrearagesgoods(String arrearagesgoods) {
		this.arrearagesgoods = arrearagesgoods;
	}

	public String getZieher() {
		return zieher;
	}

	public void setZieher(String zieher) {
		this.zieher = zieher;
	}
}
