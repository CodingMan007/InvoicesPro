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

import com.ydc.excel_to_db.service.CustomerService;
import com.ydc.excel_to_db.service.InvoicesService;
import com.ydc.excel_to_db.service.PrintService;
import com.ydc.excel_to_db.util.common.RestApiEncapsulation;
import com.ydc.excel_to_db.vo.CustomerInfoModelVo;
import com.ydc.excel_to_db.vo.IndentModelVo;
import com.ydc.excel_to_db.vo.SpecificationModelVo;

/**
 * @Description: 控制层
 * @Author: Joss xu
 * @Date: Created in  2018-10-23
 */
@RestController
@RequestMapping("/invoicesinfo")
public class InvoiceController {
    @Autowired
    InvoicesService invoicesService;
    
    @Autowired
    CustomerService customerService;

    @Autowired
    PrintService printService;
    
    private static final Logger log = LoggerFactory.getLogger(InvoiceController.class);

    
    //查询按规格的所有数据
    @RequestMapping(value="/getInvoicesSpecificationAll/{isgitype}",method=RequestMethod.GET)
    @ResponseBody
    public List<SpecificationModelVo> getSpecificationByAll(HttpServletRequest request ,HttpServletResponse response, 
    														@PathVariable("isgitype") String isgitype) {
    	List<SpecificationModelVo> returnList = null;
    	if (isgitype.equals("NotG")) {
    		//未生成发票
    		returnList =  invoicesService.getResultSpecificationAllNotGData();
		}else{
			//已生成发票
			returnList =  invoicesService.getResultSpecificationAllIsGData();
		}
			
    	return returnList;
    }
    
    
    /**
     * 更新生成发票信息。返回时查询更新已生成发票之后的未生成发票数据
     * @param request
     * @param response
     * @param idvalues
     * @param operationtype 表示操作类型；IsG表示已生成操作  NotG表示未生成操作
     * @return
     */
    @RequestMapping(value="/putInvoicesSpecificationID/{idvalues}/{operationtype}/{invoicetype}",method=RequestMethod.POST)
    @ResponseBody
    public List<SpecificationModelVo> putSpecificationByID(HttpServletRequest request ,HttpServletResponse response,
    													  @PathVariable("idvalues") String idvalues,
    													  @PathVariable("operationtype") String operationtype,
    													  @PathVariable("invoicetype") String invoicetype) {
    	List<SpecificationModelVo> returnList = null;
    	boolean returnboolean = invoicesService.putResultSpecificationIdData(idvalues , invoicetype);
    	if (returnboolean && operationtype.equals("IsG")) {
    		returnList = invoicesService.getResultSpecificationAllIsGData();
		}else {
			returnList = invoicesService.getResultSpecificationAllNotGData();
		}
    	return returnList;
    }
    
    
    /**
     * 查询按规格的所有数据
     * @param request
     * @param response
     * @param custamercodev
     * @param dateselectv
     * @param operationtype 表示操作类型；IsG表示已生成操作  NotG表示未生成操作
     * @return
     */
    @RequestMapping(value="/getcustamercodeAll/{customercodev}/{dateselectv}/{operationtype}",method=RequestMethod.POST)
    @ResponseBody
    public List<SpecificationModelVo> getSpecificationByCondition(HttpServletRequest request ,HttpServletResponse response,
    		@PathVariable("customercodev") String customername,
		    @PathVariable("dateselectv") String dateselectv,
    		@PathVariable("operationtype") String operationtype) {
    	response.setCharacterEncoding("UTF-8");

    	log.info(customername);
    	log.info(dateselectv);
    	
		String startTime = null ;
		String endTime = null;
		String[] isarry = null;
		int isgenerateinvoice;
		List<SpecificationModelVo> returnList = null;
		
		if (!dateselectv.isEmpty() && !dateselectv.equals("0")) {
			isarry= dateselectv.split(" - ");
			startTime = isarry[0];
			endTime = isarry[1];
			if (operationtype.equals("NotG")) {
				//未生成发票
				isgenerateinvoice = 0;
				if (customername.equals("0") || customername.isEmpty()) {
					returnList =  invoicesService.getResultSpecificationDateData(startTime, endTime, isgenerateinvoice);
				}else {
					returnList =  invoicesService.getResultSpecificationDateAndNameData(customername, startTime, endTime, isgenerateinvoice);
				}
			}else if (operationtype.equals("IsG")) {
				//已生成发票
				isgenerateinvoice = 1;
				if (customername.equals("0") || customername.isEmpty()) {
					returnList =  invoicesService.getResultSpecificationDateData(startTime, endTime, isgenerateinvoice);
				}else {
					returnList =  invoicesService.getResultSpecificationDateAndNameData(customername, startTime, endTime, isgenerateinvoice);
				}
			}
		}else {
			if (operationtype.equals("NotG")) {
				//未生成发票
				isgenerateinvoice = 0;
				returnList =  invoicesService.getResultSpecificationNameData(customername ,isgenerateinvoice);
			}else if (operationtype.equals("IsG")) {
				//已生成发票
				isgenerateinvoice = 1;
				returnList =  invoicesService.getResultSpecificationNameData(customername ,isgenerateinvoice);
			}
		}
		
    	return returnList;
    }
    
    //查询某一时间段按规格的数据
	@RequestMapping(value="/getInvoicesSpecificationDate/{startTime}/{endTime}",method=RequestMethod.GET)
	@ResponseBody
    public Object getSpecificationByDate(HttpServletResponse response, @PathVariable("startTime") String startTime,
    												   @PathVariable("endTime") String endTime) {
		int isgenerateinvoice = 1;
        List<SpecificationModelVo> returnList = invoicesService.getResultSpecificationDateData(startTime, endTime ,isgenerateinvoice);
		return new RestApiEncapsulation().EncapCorrectResult(returnList);
    }
	
	//查询按订单号中还未生成发票的所有数据
	@RequestMapping(value="/getInvoicesIndentAll",method=RequestMethod.GET)
	@ResponseBody
	public List<IndentModelVo> getIndentByAll(HttpServletRequest request, HttpServletResponse response) {
		List<IndentModelVo> returnList = invoicesService.getResultIndentAllData();
		return returnList;
	}
	//按按订单号查询所有数据
	@RequestMapping(value="/getInvoicesIndentNumber/{indentNumber}",method=RequestMethod.GET)
	@ResponseBody
	public List<SpecificationModelVo> getIndentByNO(HttpServletRequest request, HttpServletResponse response,@PathVariable("indentNumber") String indentNumber) {
		List<SpecificationModelVo> returnList = invoicesService.getResultSpecificationNumberAllData(indentNumber);
		return returnList;
	}
	//查询客户信息表
	@RequestMapping(value="/getCusotmerInfoAll",method=RequestMethod.GET)
	@ResponseBody
	public List<CustomerInfoModelVo> getCusotmerInfoByAll(HttpServletRequest request, HttpServletResponse response) {
		List<CustomerInfoModelVo> returnList = customerService.getCusotmerInfoAllData();
		return returnList;
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
	@RequestMapping(value="/puttInvoicesIndentToXml",method=RequestMethod.GET)
	@ResponseBody
	public Object putIndentByXml(HttpServletRequest request, HttpServletResponse response) {
		String buildtype = null;
		boolean returnList = true;//invoicesService.putIndentModelToXml(buildtype);
		return new RestApiEncapsulation().EncapCorrectResult(returnList);
	}
	//清除所有原始数据
	@RequestMapping(value="/delInvoicesDeleteAll",method=RequestMethod.GET)
	@ResponseBody
	public Object delInvoicesDeleteAll(HttpServletRequest request, HttpServletResponse response) {
		boolean returnList = invoicesService.delInvoicesDeleteAll();
		return new RestApiEncapsulation().EncapCorrectResult(returnList);
	}
	
	//清除所有已生成票的原始数据
	@RequestMapping(value="/delInvoicesDeleteGenerate",method=RequestMethod.GET)
	@ResponseBody
	public Object delInvoicesDeleteGenerate(HttpServletRequest request, HttpServletResponse response) {
		boolean returnList = invoicesService.delInvoicesDeleteGenerate();
		return new RestApiEncapsulation().EncapCorrectResult(returnList);
	}
   
}
