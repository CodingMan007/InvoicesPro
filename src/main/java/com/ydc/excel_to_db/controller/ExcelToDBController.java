package com.ydc.excel_to_db.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ydc.excel_to_db.domain.SpecificationModel;
import com.ydc.excel_to_db.result.CodeMsg;
import com.ydc.excel_to_db.result.Result;
import com.ydc.excel_to_db.service.ImportService;
import com.ydc.excel_to_db.util.Constant;
import com.ydc.excel_to_db.util.ExcelUtil;
import com.ydc.excel_to_db.vo.ExcelModelVo;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 * @Description: 控制层
 * @Author: Joss xu
 * @Date: Created in  2018-2-6
 */
@Controller
public class ExcelToDBController {
    @Autowired
    ImportService importService;

    private static final Logger log = LoggerFactory.getLogger(ExcelToDBController.class);

    /**
     * @Description: 异步接收上传的Excel文件并传递至Service层
     * @Param: [file]
     * @Retrun: com.ydc.excel_to_db.result.Result<com.ydc.excel_to_db.result.CodeMsg>
     */
    @PostMapping("/doImport")
    @ResponseBody
    public Result<CodeMsg> doImport(@RequestParam("file") MultipartFile file) {
        CodeMsg codeMsg = importService.verfiyExcel(file);
        // 返回封装好的结果集
        return Result.success(codeMsg);
    }

    /**
     * @Description: 导出Excel文件
     * @Param: response
     * @Param: failTypeName 失败类型的名称，例如:格式错误(format)/导入数据库失败(db)/excel模板下载(excelDemo)
     * @Retrun: void
     */
    @GetMapping("/doExport")
    public void doExport(HttpServletResponse response, @RequestParam("failTypeName") String failTypeName) {
        try {
            // 设置响应输出的头类型
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 导出文件名称
            String exportExcelName;
            if ("format".equals(failTypeName)) {
                exportExcelName = "数据格式校验失败的数据";
            } else if ("db".equals(failTypeName)) {
                exportExcelName = "导出未开票数据";
            } else {
                exportExcelName = "Excel数据格式模板";
            }
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(exportExcelName, "UTF-8") + ".xls");
            ExportParams exportParams = new ExportParams();
            /* exportParams.setDataHanlder(null); 设置handler来处理特殊数据 */
            // 根据失败类型的名称(failTypeName),获取对应的List
            
        	OutputStream out = response.getOutputStream();//拿到用户选择的路径
            List<Map<String, Object>> failList = importService.getFailList(failTypeName,exportExcelName, out);
            log.info("导出成功的数据： {}",failList);
            // 导出Excel
//            HSSFWorkbook workbook = (HSSFWorkbook) ExcelExportUtil.exportExcel(exportParams, SpecificationModel.class,failList);
//            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @Description: 异步获取当前消息队列中未被消费的队列大小
     * @Param: []
     * @Retrun: com.ydc.excel_to_db.result.Result<java.lang.Long>
     */
    @GetMapping("/getUndoSize")
    @ResponseBody
    public Result<Long> getUndoSize() {
        return Result.success(importService.getTempSize(Constant.succSizeTempKey));
    }

    /**
     * @Description: 跳转至同步结果页面
     * @Param: []
     * @Retrun: java.lang.String
     */
    @GetMapping("/toResult")
    public String toResult() {
        return "importResult";
    }

    /**
     * @Description: 异步获取同步结果页面中饼状图所需的数据
     * @Param: []
     * @Retrun: com.ydc.excel_to_db.result.Result<com.ydc.excel_to_db.vo.ExcelModelVo>
     */
    @RequestMapping("/getResultData")
    @ResponseBody
    public Result<ExcelModelVo> getResultData() {
        ExcelModelVo resultData = importService.getResultData();
        return Result.success(resultData);
    }
}
