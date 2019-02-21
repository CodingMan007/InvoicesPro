package com.ydc.excel_to_db.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import lombok.Data;

/**
 * @Description: 值对象
 * @Author: Joss xu
 * @Date: Created in 2018-2-6
 */
@Data
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ExcelModelVo {
	// 格式校验成功的数据大小
	private long succSize;

	// 导入数据库成功的数据大小；
	private long succToDBSize;

	// 格式校验失败的数据大小
	private long failSize;

	// 导入数据库失败的数据大小
	private long failToDBSize;

	
	
	public long getSuccSize() {
		return succSize;
	}



	public void setSuccSize(long succSize) {
		this.succSize = succSize;
	}



	public long getSuccToDBSize() {
		return succToDBSize;
	}



	public void setSuccToDBSize(long succToDBSize) {
		this.succToDBSize = succToDBSize;
	}



	public long getFailSize() {
		return failSize;
	}



	public void setFailSize(long failSize) {
		this.failSize = failSize;
	}



	public long getFailToDBSize() {
		return failToDBSize;
	}



	public void setFailToDBSize(long failToDBSize) {
		this.failToDBSize = failToDBSize;
	}



	public ExcelModelVo(long succSize, long failSize, long failToDBSize) {
		this.succSize = succSize;
		this.failSize = failSize;
		this.failToDBSize = failToDBSize;
		// 校验成功的数据大小 - 导入数据库失败的数据大小 =成功导入的数据大小
		this.succToDBSize = succSize - failToDBSize;
	}
}
