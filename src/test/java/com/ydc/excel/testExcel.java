package com.ydc.excel;
 
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ydc.excel_to_db.util.DbToExcelTool;
 
/**
 *测试导出数据
 */
@SuppressWarnings("serial")
public class testExcel extends HttpServlet{
 
 
    public static void main(String[] args) throws UnsupportedEncodingException {
    	 //System.getProperty("user.dir") 获取当前项目的路径
        String fileName=System.getProperty("user.dir")+"\\导出数据Excel.xls";//定义到处路径
        System.out.println(fileName);
         
    	List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
        for (int i = 1; i < 6; i++) {
        	Map<String,Object> map=new HashMap<String,Object>();
        	 map.put("序列", i);
             map.put("姓名", "张三");
             map.put("年龄", 20);
             map.put("户籍", "陕西");
             listmap.add(map);
		}
         
        //String worksheetTitle = "Excel导出信息";
        //sheet名
        String sheetName="发票清单";
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);//定义sheet名
        DbToExcelTool exportExcel = new DbToExcelTool(wb,sheet);//调用Excel工具类
        fileName = new String(fileName.getBytes("GBK"), "GBK");//修改编码格式
        //定义第一行标题
        List<String> list=new ArrayList<String>();
        list.add("序号");
        list.add("货物或应税劳务、服务名称");
        list.add("计量单位");
        list.add("规格型号");
        list.add("数量");
        list.add("金额");
        list.add("税率");
        list.add("商品税目");
        list.add("折扣金额");
        list.add("税额");
        list.add("折扣税额");
        list.add("折扣率");
        list.add("单价");
        list.add("价格方式");
        list.add("税收分类编码版本号");
        list.add("税收分类编码");
        list.add("企业商品编码");
        list.add("使用优惠政策标识");
        list.add("零税率标识");
        list.add("优惠政策说明");
        list.add("中外合作油气田标识");
        
        
             
        // 创建报表头部
//        exportExcel.createNormalHead(worksheetTitle, 3);
        //定义第一行
        exportExcel.createNormalTwoRow(list, 0);
        //导入数据
        //exportExcel.createColumHeader(listmap);
        //输出文件流，把相应的Excel工作簿 输出到本地
        exportExcel.outputExcel(fileName);
    }
}