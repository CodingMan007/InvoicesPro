package com.ydc.excel_to_db.util.common;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 默认分页工具
 * @author Administrator
 *
 */
public class PageUtils {

	@Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

    @Transient
    private String orderBy;

    @JsonIgnore
    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @JsonIgnore
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonIgnore
    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
