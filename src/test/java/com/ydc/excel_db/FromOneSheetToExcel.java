package com.ydc.excel_db;

import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class FromOneSheetToExcel {
    //TODO 需要将url\用户名\密码修改为自己的
    public final static String url = "jdbc:mysql://127.0.0.1:3306"; // 数据库URL
    public final static String user = "root"; // 数据库用户名
    public final static String password = "root_998"; // 数据库密码

    // 把数据库单张表信息导入到Excel表中
    public static void main(String[] args) throws Exception {
        // 架子啊数据库驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 连接数据库
        Connection con = (Connection) DriverManager.getConnection(url, user, password);
        // 创建Excel表。
        Workbook book = new HSSFWorkbook();
        // DatabaseMetaData dbmt = con.getMetaData();
        // 获得blog数据库
        //TODO 需要将blog修改为你指定的数据库
        // ResultSet rs = dbmt.getTables("blog", "blog", null, new String[] {
        // "TABLE", "VIEW" });
        // 设置要转化为Excel的表
        //TODO 需要将Table_Name修改为当前数据库中你想导出的数据表
        String Table_Name = "productcodeModel";
        // while (rs.next()) {
        // if ("team".equals(rs.getString("TABLE_NAME"))) {
        // Table_Name = "team";
        // break;
        // }
        // }
        // 在当前Excel创建一个子表
        Sheet sheet = book.createSheet(Table_Name);

        Statement st = (Statement) con.createStatement();
        // 创建sql语句，对team进行查询所有数据
        String sql = "select * from invoicedb." + Table_Name;
        ResultSet rs = st.executeQuery(sql);
        // 设置表头信息（写入Excel左上角是从(0,0)开始的）
        Row row1 = sheet.createRow(0);
        ResultSetMetaData rsmd = rs.getMetaData();
        int colnum = rsmd.getColumnCount();
//        for (int i = 1; i <= colnum; i++) {
//            String name = rsmd.getColumnName(i);
//            // 单元格
//            Cell cell = row1.createCell(i - 1);
//            // 写入数据
//            cell.setCellValue(name+i);
//        }
        Cell cellrw = row1.createCell(8);
        cellrw.setCellValue("序号");
        cellrw.setCellValue("货物或应税劳务、服务名称");
        cellrw.setCellValue("计量单位");
        cellrw.setCellValue("规格型号");
        cellrw.setCellValue("数量");
        cellrw.setCellValue("金额");
        cellrw.setCellValue("税率");
        cellrw.setCellValue("商品税目");
        cellrw.setCellValue("折扣金额");
        cellrw.setCellValue("税额");
        cellrw.setCellValue("折扣税额");
        cellrw.setCellValue("折扣率");
        cellrw.setCellValue("单价");
        cellrw.setCellValue("价格方式");
        cellrw.setCellValue("税收分类编号版本号");
        cellrw.setCellValue("税收分类编码");
        cellrw.setCellValue("企业商品编码");
        cellrw.setCellValue("使用优惠政策标识");
        cellrw.setCellValue("零税率标识");
        cellrw.setCellValue("优惠政策yij");

        cellrw.setCellValue("金额");
        
        
        
        // 设置表格信息
        int idx = 1;
        while (rs.next()) {
            // 行
            Row row = sheet.createRow(idx++);
            for (int i = 1; i <= colnum; i++) {
                String str = rs.getString(i);
                // 单元格
                Cell cell = row.createCell(i - 1);
                // 写入数据
                cell.setCellValue(str);
            }
        }
        // 保存
        String fileName=System.getProperty("user.dir");//定义到处路径

        book.write(new FileOutputStream(fileName + Table_Name + ".xls"));

    }
}