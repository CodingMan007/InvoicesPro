package com.ydc.excel_to_db.controller;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.ydc.excel_to_db.domain.PrintModel;
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
@RequestMapping("/printinfo")
public class PrintController {
	@Autowired
    InvoicesService invoicesService;
    
    @Autowired
    CustomerService customerService;

    @Autowired
    PrintService printService;
    
    private static final Logger log = LoggerFactory.getLogger(PrintController.class);

	
	//查询按订单的所有数据
	@RequestMapping(value="/getPrintInvoiceAll",method=RequestMethod.GET)
	@ResponseBody
	public List<PrintModel> getIndentByAll(HttpServletRequest request, HttpServletResponse response) {
		List<PrintModel> returnList = printService.getPrintAllData();
		return returnList;
	}
   
	
	 /**
     * 打印发票重新生成发票信息。返回时查询更新已生成发票之后的未生成发票数据
     * @param request
     * @param response
     * @param idvalues
     * @param operationtype 表示操作类型；IsG表示已生成操作  NotG表示未生成操作
     * @return
     */
    @RequestMapping(value="/putPritInvoicesSpecificationID/{idvalues}/{operationtype}/{invoicetype}",method=RequestMethod.POST)
    @ResponseBody
    public List<SpecificationModelVo> putPritInvoicesSpecificationID(HttpServletRequest request ,HttpServletResponse response,
    		@PathVariable("idvalues") String idvalues,
    		@PathVariable("operationtype") String operationtype,
    		@PathVariable("invoicetype") String invoicetype) {
    	List<SpecificationModelVo> returnList = null;
    	PrintModel printModel = new PrintModel();
		String[] vsplit = idvalues.split(",");
    	printModel.setId(Long.parseLong(vsplit[0]));
    	printModel = printService.getPrintByIdAllData(printModel).get(0);
    	
    	boolean returnboolean = invoicesService.putResultSpecificationIdData(printModel.getGenerateId() , invoicetype);
    	if (returnboolean && operationtype.equals("IsG")) {
    		returnList = invoicesService.getResultSpecificationAllIsGData();
    	}else {
    		returnList = invoicesService.getResultSpecificationAllNotGData();
    	}
    	return returnList;
    }
    
  //删除指定的打印单据所有数据
  	@RequestMapping(value="/delPrintInvoice/{id}",method=RequestMethod.GET)
  	@ResponseBody
  	public List<PrintModel> DelIndentByAll(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) {
  		PrintModel printModel = new PrintModel();
  		printModel.setId(Long.parseLong(id));
  		//删除指定的文件
  		printService.delPrintIdData(printModel);
  		List<PrintModel> returnList = printService.getPrintAllData();
  		return returnList;
  	}
  	//查询按订单的所有数据
  	@RequestMapping(value="/fidPrintInvoice/{id}",method=RequestMethod.GET)
  	@ResponseBody
  	public List<SpecificationModelVo> FindIndentByAll(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) {
  		PrintModel printModel = new PrintModel();
  		printModel.setId(Long.parseLong(id));
  		//查看生成发票的明细
  		printModel= printService.getPrintByIdAllData(printModel).get(0);
  		List<SpecificationModelVo> returnList = new ArrayList<SpecificationModelVo>();
  		String[] generateId =  printModel.getGenerateId().split(",");
  		for (int i = 0; i < generateId.length; i++) {
  			log.info(id,"{}<<<  生成发票的id    =====     规格表中的id", generateId[i]);
  			SpecificationModelVo spmvo = new SpecificationModelVo();
  			spmvo.setId(generateId[i]);
  			returnList.add(invoicesService.getResultSpecificationId(spmvo));
		}
  		return returnList;
  	}
  	//查询按订单的所有数据
  	@RequestMapping(value="/resetPrintInvoice/{id}",method=RequestMethod.POST)
  	@ResponseBody
  	public List<PrintModel> ResetIndentByAll(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) {
  		String[] idvalue = id.split(",");
  		if (idvalue.length >0) {
			for (int i = 0; i < idvalue.length; i++) {
				PrintModel printModel = new PrintModel();
				printModel.setId(Long.parseLong(idvalue[i]));
			  		//查看生成发票的明细
			  		printModel= printService.getPrintByIdAllData(printModel).get(0);
			  		String[] generateId =  printModel.getGenerateId().split(",");
			  		for (int j = 0; j < generateId.length; j++) {
			  			log.info("{}<<<  生成发票的id    =====     规格表中的id {} ",id, generateId[j]);
			  			SpecificationModelVo spmvo = new SpecificationModelVo();
			  			spmvo.setId(generateId[j]);
			  			invoicesService.putResultSpecificationId(spmvo);
				}
			  	//重置数据后要删除原来在打印表中数据
			  	printService.delPrintIdData(printModel);
			}
  		}
  		List<PrintModel> returnList = printService.getPrintAllData();
  		return  returnList ;
  	}
    
}
