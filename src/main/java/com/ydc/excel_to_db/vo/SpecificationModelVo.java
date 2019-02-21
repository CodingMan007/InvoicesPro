package com.ydc.excel_to_db.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * @Description: 为了方便扩展，这里的字段命名规则为col[列]+1[列数]
 *               例如：col1代表的就是第一列的数据，通过@Excel(name=?)注解进行区分
 * @Author: joss xu
 * @Date: Created in 2018-2-6
 * 按规格
 */
@SuppressWarnings("deprecation")
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SpecificationModelVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// "ID号")
	private String id;
	
	// "单号")
	private String indentnumber;

	// "名称")
	private String name;
	
	// "规格")
	private String specification;
	
	// "销售日期", format = "yyyy-MM-dd")
	private String saledate;
		
	// "客户名称")
	private String customername;
	
	// "数量")
	private BigDecimal quantity;
	
	// "计量单位")
	private String measurementunit;
	
	// "含税单价")
	private BigDecimal taxincludedprice;
	
	// "含税金额")
	private BigDecimal taxincludedamount;
	
	// "税前货款")
	private BigDecimal pretax;
	
	// "税金")
	private BigDecimal taxes;

	// "是否已生成发票")
	private int isgenerateinvoice;

	// "是否已打印发票")
	private int isprint;
	
	// "商品名称编号")
	private String spbmcode;
	

	public String getSpbmcode() {
		return spbmcode;
	}

	public void setSpbmcode(String spbmcode) {
		this.spbmcode = spbmcode;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndentnumber() {
		return indentnumber;
	}

	public void setIndentnumber(String indentnumber) {
		this.indentnumber = indentnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getSaledate() {
		return saledate;
	}

	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getMeasurementunit() {
		return measurementunit;
	}

	public void setMeasurementunit(String measurementunit) {
		this.measurementunit = measurementunit;
	}

	public BigDecimal getTaxincludedprice() {
		return taxincludedprice;
	}

	public void setTaxincludedprice(BigDecimal taxincludedprice) {
		this.taxincludedprice = taxincludedprice;
	}

	public BigDecimal getTaxincludedamount() {
		return taxincludedamount;
	}

	public void setTaxincludedamount(BigDecimal taxincludedamount) {
		this.taxincludedamount = taxincludedamount;
	}

	public BigDecimal getPretax() {
		return pretax;
	}

	public void setPretax(BigDecimal pretax) {
		this.pretax = pretax;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}

	public int getIsgenerateinvoice() {
		return isgenerateinvoice;
	}

	public void setIsgenerateinvoice(int isgenerateinvoice) {
		this.isgenerateinvoice = isgenerateinvoice;
	}

	public int getIsprint() {
		return isprint;
	}

	public void setIsprint(int isprint) {
		this.isprint = isprint;
	}

}