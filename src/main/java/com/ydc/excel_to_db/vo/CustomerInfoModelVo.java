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
public class CustomerInfoModelVo implements Cloneable{
	
	//"id"
	private int id;
	
	//"纳税人识别号")
	private String customeridentification;
	
	//"客户名称" )
	private String customername;
	
	//"客户代码")
	private String customercode;
	
	//"客户地址")
	private String customeraddress;
	
	//"客户电话")
	private String customertelephone;
	
	//"客户开户行")
	private String customerbank;

	//"客户开户行账号")
	private String customeraccount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomeridentification() {
		return customeridentification;
	}

	public void setCustomeridentification(String customeridentification) {
		this.customeridentification = customeridentification;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getCustomeraddress() {
		return customeraddress;
	}

	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}

	public String getCustomertelephone() {
		return customertelephone;
	}

	public void setCustomertelephone(String customertelephone) {
		this.customertelephone = customertelephone;
	}

	public String getCustomerbank() {
		return customerbank;
	}

	public void setCustomerbank(String customerbank) {
		this.customerbank = customerbank;
	}

	public String getCustomeraccount() {
		return customeraccount;
	}

	public void setCustomeraccount(String customeraccount) {
		this.customeraccount = customeraccount;
	}
	
	@Override  
    public Object clone() {  
		CustomerInfoModelVo customerInfoModelVo = null;  
        try{  
        	customerInfoModelVo = (CustomerInfoModelVo)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return customerInfoModelVo;  
    }  
	
}
