package com.ydc.excel_to_db.util;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.ydc.excel_to_db.util.common.Tools;
import com.ydc.excel_to_db.vo.SpecificationModelVo;
 
/**
 * EXCEL报表工具类.
 *
 */
public class DbToExcelTool {
	static DecimalFormat df1 = new DecimalFormat("#.00");  
	static DecimalFormat dj = new DecimalFormat("#.0000000");  
	static DecimalFormat sl = new DecimalFormat("#.000");  
	
    private HSSFWorkbook wb = null;//得到Excel工作簿对象  
    private HSSFSheet sheet = null;//得到Excel工作表对象 
     
    public DbToExcelTool(HSSFWorkbook wb,HSSFSheet sheet){
        this.wb=wb;
        this.sheet = sheet;
    }
     
    /**
     * 创建通用的Excel头
     *
     * @param  headString  头部显示的字符
     *  @param colSum  该报表的列数
     */
    public void createNormalHead(String headString, int colSum){
        // 设置第一行
        HSSFRow row = sheet.createRow(0);//创建Excel工作表的行
        HSSFCell cell = row.createCell(0);//创建Excel工作表指定行的单元格  
        row.setHeight((short) 1000);//设置高度
        // 定义单元格为字符串类型
        cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理
        cell.setCellValue(new HSSFRichTextString(headString));
         
        // 指定合并区域
        //sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colSum));
         
        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格水平对齐类型
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行
        // 设置单元格字体
        HSSFFont font = wb.createFont();
         font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         font.setFontName("微软雅黑");//字体
         font.setFontHeightInPoints((short)16);//设置字体
         cellStyle.setFont(font);
         cell.setCellStyle(cellStyle);
    }
     
    /**
     * 创建报表第二行
     * @param params
     *            统计条件数组
     * @param colSum
     *            需要合并到的列索引
     *
     */
    @SuppressWarnings("deprecation")
    public void createNormalTwoRow(List<String> list, int colSum){
        // 创建第一行
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short) 800);//设置高度
        HSSFCell cell2 = row1.createCell(0);//创建Excel工作表指定行的单元格
        cell2.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理
        // 指定合并区域
        //sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) colSum));
        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行
 
        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");//字体
        font.setFontHeightInPoints((short)12);//设置字体
        cellStyle.setFont(font);
        //HSSFCellStyle.ALIGN_CENTER  设定居中
        for(int i=0;i<list.size();i++){
            cteateCell(wb,row1,i,HSSFCellStyle.ALIGN_CENTER,list.get(i),cellStyle);
        }      
    }
     
    /**
     * 设置报表标题
     *
     * @param columHeader
     *            标题字符串数组
     */
    public void createColumHeader(List<SpecificationModelVo> list) {
        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行
 
        // 单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");//字体
        font.setFontHeightInPoints((short)12);//设置字体
        cellStyle.setFont(font);
 
        // 设置单元格背景色
        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
 
        HSSFCell cell3 = null;
 
        for (int i = 0; i < list.size(); i++) {
        	//原数为：含税金额 处理成不税金额 
			BigDecimal BZSL=new BigDecimal(Constant.HSLV);
			BigDecimal Je= list.get(i).getTaxincludedamount().divide(BZSL,6,RoundingMode.HALF_UP);
			BigDecimal Quantity=list.get(i).getQuantity();
			BigDecimal taxincludedprice = Je.divide(Quantity,6,RoundingMode.HALF_UP);
        	
        	
            //循环插入数据
            HSSFRow row2 = sheet.createRow(i+1);   
            row2.setHeight((short) 400);// 指定行高
            cell3 = row2.createCell(0);
            cell3.setCellStyle(cellStyle);
            int indenx = 1;
            cell3.setCellValue(new HSSFRichTextString(String.valueOf(indenx+i)));
             
            /*
             * 商品名称：
             *  	货物或应税劳务、服务名称
             */
            cell3 = row2.createCell(1);
            cell3.setCellStyle(cellStyle);
            String invoicename = list.get(i).getName().equals(Constant.SELLVALUE)?Constant.INVOICEVALUE:list.get(i).getName();
                   invoicename = invoicename.equals(Constant.SELLQFVALUE)?Constant.INVOICEVALUE:invoicename;
            		
            cell3.setCellValue(new HSSFRichTextString(invoicename));
             
            //计量单位
            cell3 = row2.createCell(2);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(list.get(i).getMeasurementunit()));
             
            //规格型号
            cell3 = row2.createCell(3);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString((String) list.get(i).getSpecification().substring(0, 3)));
            
            //数量
            cell3 = row2.createCell(4);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(String.valueOf(sl.format(list.get(i).getQuantity()))));
            
            //金额
            cell3 = row2.createCell(5);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(String.valueOf(df1.format(Je))));
            
            //税率
            cell3 = row2.createCell(6);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(Constant.FPSLV));
            
            //商品税目
            cell3 = row2.createCell(7);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(""));
            
            //折扣金额
            cell3 = row2.createCell(8);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(""));
            
            //税额
            BigDecimal FPSLV=new BigDecimal(Constant.FPSLV);
			BigDecimal  se = Je.multiply(FPSLV);
            cell3 = row2.createCell(9);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(String.valueOf(dj.format(se))));
            
            //折扣税额
            cell3 = row2.createCell(10);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(""));
            
            //折扣率
            cell3 = row2.createCell(11);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(""));
            
            /*
             * 单价
             * 原数为：含税金额 处理成不税金额 
             */
            cell3 = row2.createCell(12);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(String.valueOf(dj.format(taxincludedprice))));
            
            /**
             * 价格方式
             * 含税标志 0：不含税税率，1：含税税率，2：差额税;中外合作油气田（原海洋石油）5%税率、1.5%税率为1，差额税为2，其他为0；
             */
            cell3 = row2.createCell(13);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString("0"));
            
            //税收分类编码版本号
            cell3 = row2.createCell(14);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString("28.0"));
            
            //税收分类编码
            cell3 = row2.createCell(15);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(Tools.addZeroForNum(list.get(i).getSpbmcode(), 19)));
            
            //企业商品编码
            cell3 = row2.createCell(16);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(""));
            
            //使用优惠政策标识
            cell3 = row2.createCell(17);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString("0"));
            
            //零税率标识
            cell3 = row2.createCell(18);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString("3"));
            
            //优惠政策说明	
            cell3 = row2.createCell(19);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(""));
            
            //中外合作油气田标识
            cell3 = row2.createCell(20);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString("0"));
            
        }
    }
     
    /**
     * 创建内容单元格
     *
     * @param wb
     *            HSSFWorkbook
     * @param row
     *            HSSFRow
     * @param col
     *            short型的列索引
     * @param align
     *            对齐方式
     * @param val
     *            列值
     */
    public void cteateCell(HSSFWorkbook wb, HSSFRow row, int col, short align,
            String val,HSSFCellStyle cellstyle ) {
        HSSFCell cell = row.createCell(col);
        cell.setCellType(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(new HSSFRichTextString(val));
        cell.setCellStyle(cellstyle);
    }
    /**
     * 创建合计行
     *
     * @param colSum
     *            需要合并到的列索引
     * @param cellValue
     */
    public void createLastSumRow(int colSum, List<String> list) {
        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行
 
        // 单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 250);
        cellStyle.setFont(font);
        // 获取工作表最后一行
        HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));
        HSSFCell sumCell = lastRow.createCell(0);//创建Excel工作表指定行的单元格
 
        sumCell.setCellValue(new HSSFRichTextString("合计"));
        sumCell.setCellStyle(cellStyle);
        // 合并 最后一行的第零列-最后一行的第一列
//        sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0,
//        sheet.getLastRowNum(), (short) colSum));// 指定合并区域
 
        for (int i = 2; i < (list.size() + 2); i++) {
            // 定义最后一行的第三列
            sumCell = lastRow.createCell(i);
            sumCell.setCellStyle(cellStyle);
            // 定义数组 从0开始。
            sumCell.setCellValue(new HSSFRichTextString(list.get(i-2)));
        }
    }
    /**
     * 输入EXCEL文件
     *
     * @param fileName
     *            文件名
     */
    public void outputExcel(String fileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(fileName));
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HSSFWorkbook getWb() {
        return wb;
    }
    public void setWb(HSSFWorkbook wb) {
        this.wb = wb;
    }
    public HSSFSheet getSheet() {
        return sheet;
    }
    public void setSheet(HSSFSheet sheet) {
        this.sheet = sheet;
    }
}