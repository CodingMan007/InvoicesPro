package com.ydc.excel_to_db.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ydc.excel_to_db.service.InvoicesService;
import com.ydc.excel_to_db.util.common.RestApiEncapsulation;
import com.ydc.excel_to_db.vo.IndentModelVo;
import com.ydc.excel_to_db.vo.SpecificationModelVo;

/**
 * @Description: 控制层
 * @Author: Joss xu
 * @Date: Created in  2018-10-23
 */
@RestController
@RequestMapping("/restinfo")
public class RestInvoicesController {
    @Autowired
    InvoicesService invoicesService;

    private static final Logger log = LoggerFactory.getLogger(RestInvoicesController.class);

    
    //查询按规格的所有数据
    @RequestMapping(value="/getInvoicesSpecificationAll",method=RequestMethod.GET)
    @ResponseBody
    public Object getSpecificationByAll(HttpServletRequest request ,HttpServletResponse response) {
    	List<SpecificationModelVo> returnList = invoicesService.getResultSpecificationAllNotGData();
		return new RestApiEncapsulation().EncapCorrectResult(returnList);
    }
    
    //查询某一时间段按规格的数据
	@RequestMapping(value="/getInvoicesSpecificationDate/{startTime}/{endTime}",method=RequestMethod.GET)
	@ResponseBody
    public Object getSpecificationByDate(HttpServletResponse response, @PathVariable("startTime") String startTime,
    												   @PathVariable("endTime") String endTime) {
		int isgenerateinvoice = 1;
        List<SpecificationModelVo> returnList = invoicesService.getResultSpecificationDateData(startTime, endTime,isgenerateinvoice);
		return new RestApiEncapsulation().EncapCorrectResult(returnList);
    }
	
	//查询按订单的所有数据
	@RequestMapping(value="/getInvoicesIndentAll",method=RequestMethod.GET)
	@ResponseBody
	public Object getIndentByAll(HttpServletRequest request, HttpServletResponse response) {
		List<IndentModelVo> returnList = invoicesService.getResultIndentAllData();
		return new RestApiEncapsulation().EncapCorrectResult(returnList);
	}
	
    //查询某一时间段按订单的数据
	@RequestMapping(value="/getInvoicesIndentAllDate/{startTime}/{endTime}",method=RequestMethod.GET)
	@ResponseBody
	public Object getIndentByDate(HttpServletRequest request, HttpServletResponse response, @PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime) {
		List<IndentModelVo> returnList = invoicesService.getResultIndentModelDateData(startTime, endTime);
		return new RestApiEncapsulation().EncapCorrectResult(returnList);
	}
	//查询某一时间段按订单的数据
	@RequestMapping(value="/getInvoicesIndentAllCustomerCode/{customerCode}",method=RequestMethod.GET)
	@ResponseBody
	public Object getIndentByCustomerCode(HttpServletRequest request, HttpServletResponse response, @PathVariable("customerCode") String customerCode) {
		List<IndentModelVo> returnList = invoicesService.getResultIndentModelCustomerCodeData(customerCode);
		return new RestApiEncapsulation().EncapCorrectResult(returnList);
	}
	//用户提交数据生成XML文件
	@RequestMapping(value="/getPuttInvoicesIndentToXml",method=RequestMethod.GET)
	@ResponseBody
	public Object putIndentByXml(HttpServletRequest request, HttpServletResponse response) {
		String buildtype = null;
		boolean returnList = true;//invoicesService.putIndentModelToXml(buildtype);
		return new RestApiEncapsulation().EncapCorrectResult(returnList);
	}
   
}
