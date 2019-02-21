package com.ydc.excel_to_db.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import lombok.Data;
@Data
@JsonSerialize(include=Inclusion.NON_EMPTY) 
@Entity
public class PrintModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String generateName;
	
	@Column
	private String customerName;
	
	@Column
	private String generateId;
	
	@Column
	private String isGenerateInvoice;
	
	@Column
	private String isPrint;
	
	@Column
	private String generateDate;
	
	@Column
	private String printDate;
	
	@Column
	private String printContent;
	
	@Column
	private String invoiceamount;

	public String getInvoiceamount() {
		return invoiceamount;
	}

	public void setInvoiceamount(String invoiceamount) {
		this.invoiceamount = invoiceamount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenerateName() {
		return generateName;
	}

	public void setGenerateName(String generateName) {
		this.generateName = generateName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGenerateId() {
		return generateId;
	}

	public void setGenerateId(String generateId) {
		this.generateId = generateId;
	}

	public String getIsGenerateInvoice() {
		return isGenerateInvoice;
	}

	public void setIsGenerateInvoice(String isGenerateInvoice) {
		this.isGenerateInvoice = isGenerateInvoice;
	}

	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	public String getGenerateDate() {
		return generateDate;
	}

	public void setGenerateDate(String generateDate) {
		this.generateDate = generateDate;
	}

	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public String getPrintContent() {
		return printContent;
	}

	public void setPrintContent(String printContent) {
		this.printContent = printContent;
	}

	
}