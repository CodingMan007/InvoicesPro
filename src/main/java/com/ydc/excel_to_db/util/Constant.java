package com.ydc.excel_to_db.util;

import java.util.Date;

/**
 * @Description: 常量
 * @Author: Joss xu
 * @Date: Created in  2018-2-6
 */
public class Constant {
    // 未通过格式校验的数据
    public static final String failListKey = "excelToDB:failListKey";
    // 已通过格式校验的数据
    public static final String successListKey = "excelToDB:successListKey";
    // 未通过格式校验的数据大小
    public static final String failSizeKey = "excelToDB:failSizeKey";
    // 已通过格式校验的数据大小
    public static final String succSizeKey = "excelToDB:succSizeKey";
    // 消息队列中未被消费的数据大小
    public static final String succSizeTempKey = "excelToDB:succSizeTempKey";
    // 导入数据库失败的数据大小
    public static final String failToDBKey = "excelToDB:failToDBKey";


    // redis中，发布者中所使用到的频道名称
    public static final String receiveSingle = "excelToDB:receiveSingle";
    public static final String receiveList = "excelToDB:receiveList";
    
    public static final String receiveIndentSingle = "excelToDB:receiveIndentSingle";
    public static final String receiveIndentList = "excelToDB:receiveIndentList";
    
    public static final String receiveSpecificationSingle = "excelToDB:receiveSpecificationSingle";
    public static final String receiveSpecificationList = "excelToDB:receiveSpecificationList";
    

    // redis中，不同消费者的方法名
    public static final String singleMethodName = "receiveSingle";
    public static final String listMethodName = "receiveList";
    
    public static final String indentMethodName = "receiveIndentSingle";
    public static final String listMethodIName = "receiveIndentList";
    
    public static final String specificationMethodName = "receiveSpecificationSingle";
    public static final String listMethodSName = "receiveSpecificationList";
    
    
    // 文件输出路径
    public static final String EXPORTPATH = "H:\\开票销售发票\\导入\\";
    public static final String PTFP = "PTFP_";
    public static final String ZYFP = "ZYFP_";
    
    //发票信息
    public static final String FPSLV = "0.16";
    //发票信息 标准备税率
    public static final String HSLV = "1.16";
    
    
    public static final String SELLVALUE = "热镀";
    public static final String SELLQFVALUE = "QF热镀管";
    public static final String INVOICEVALUE = "热镀锌管";
     
    
    

}
