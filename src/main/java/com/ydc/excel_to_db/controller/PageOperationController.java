package com.ydc.excel_to_db.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydc.excel_to_db.service.ImportService;

/**
 * @Description: 控制层
 * @Author: Joss xu
 * @Date: Created in  2018-2-6
 */
@Controller
@RequestMapping("/pages")

public class PageOperationController {
    @Autowired
    ImportService importService;

    private static final Logger log = LoggerFactory.getLogger(PageOperationController.class);

    /**
     * @Description: 跳转至首页
     * @Param: []
     * @Retrun: java.lang.String
     */
    @GetMapping("/toIndexOperation")
    public String toIndexOperation() {
    	log.info("index pages ...........");
        return "index";
    }
    
    /**
     * @Description: 跳转导入数据页面(也就是导入Excel的页面)
     * @Param: []
     * @Retrun: java.lang.String
     */
    @GetMapping("/toImport")
    public String toImport() {
    	log.info("importExcel pages ...........");
    	return "importExcel";
    }
    /**
     * @Description: 跳转已生成发票数据页面
     * @Param: []
     * @Retrun: java.lang.String
     */
    @GetMapping("/toGenerate")
    public String toGenerate() {
    	log.info("to generate invoice yet pages ...........");
    	return "generateInvoice";
    }
    
    @GetMapping("/toTest")
        public String totest(ModelMap modelMap) {
    	log.info("tet pages ...........");
    	return "test";
    }
    
    /**
     * @Description: 跳转至生成发票页
     * @Param: []
     * @Retrun: java.lang.String
     */
    @GetMapping("/toOperation")
    public String toOperation(ModelMap modelMap) {
    	log.info("invoice date page ...........");
    	modelMap.put("msg", "生成发票");
        return "invoicedate";
    }
    /**
     * @Description: 跳转至发票打印页
     * @Param: []
     * @Retrun: java.lang.String
     */
    @GetMapping("/toPrintOperation")
    public String toPrintOperation(ModelMap modelMap) {
    	log.info("invoice print page ...........");
    	modelMap.put("msg", "发票打印");
    	return "print";
    }
}
