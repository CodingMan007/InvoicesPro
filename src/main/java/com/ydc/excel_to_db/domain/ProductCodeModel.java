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
public class ProductCodeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String specificationtype;
	
	@Column
	private String spbmcode;
	
	@Column
	private String spbmversion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpecificationtype() {
		return specificationtype;
	}

	public void setSpecificationtype(String specificationtype) {
		this.specificationtype = specificationtype;
	}

	public String getSpbmcode() {
		return spbmcode;
	}

	public void setSpbmcode(String spbmcode) {
		this.spbmcode = spbmcode;
	}

	public String getSpbmversion() {
		return spbmversion;
	}

	public void setSpbmversion(String spbmversion) {
		this.spbmversion = spbmversion;
	}
	

	
}