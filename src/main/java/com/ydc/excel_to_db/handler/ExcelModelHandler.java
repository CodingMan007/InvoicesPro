package com.ydc.excel_to_db.handler;


import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;



/**
 * @Description: easypoi的handler是用来处理一些特殊事情的。
 * 例如：导入的数据某些信息不能与现有数据库中的数据冲突，需要提前查询数据库...
 * @Author: Joss xu
 * @Date: Created in  2018-2-6
 */
public class ExcelModelHandler extends ExcelDataHandlerDefaultImpl<T> {

    private static final Logger log = LoggerFactory.getLogger(ExcelModelHandler.class);


    @Override
    public Object importHandler(T obj, String name, Object value) {
        log.info(name+":"+value);
        return super.importHandler(obj, name, value);
    }

}
