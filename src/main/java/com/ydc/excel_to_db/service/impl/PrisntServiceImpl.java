package com.ydc.excel_to_db.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydc.excel_to_db.dao.PrintModelMapper;
import com.ydc.excel_to_db.dao.ProductCodeModelMapper;
import com.ydc.excel_to_db.domain.PrintModel;
import com.ydc.excel_to_db.domain.ProductCodeModel;
import com.ydc.excel_to_db.redis.RedisDao;
import com.ydc.excel_to_db.service.CustomerService;
import com.ydc.excel_to_db.service.PrintService;




@Service
public class PrisntServiceImpl implements PrintService {
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

	
	@Autowired
	RedisDao redisDao;
	
	@Autowired
	 PrintModelMapper printModelMapper;
	
	@Autowired
	ProductCodeModelMapper productCodeModelMapper;
	
	@Override
	public List<PrintModel> getPrintAllData() {
		return printModelMapper.selectPrintInfoModelByAll();
	}
 
	@Override
	public long petPrintData(PrintModel printModel) {
		log.info("printModel  :  {}",printModel);
		return printModelMapper.insertIndent(printModel);
	}

	@Override
	public ProductCodeModel getProductCodAllNameData(ProductCodeModel productCodeModel) {
		return productCodeModelMapper.selectProductCodeModelByName(productCodeModel);
	}

	@Override
	public List<PrintModel> getPrintByIdAllData(PrintModel printModel) {
		
		return printModelMapper.selectPrintInfoModelById(printModel);
	}

	@Override
	public long delPrintIdData(PrintModel printModel) {
		return printModelMapper.deletePrintInfoModelById(printModel);
	}
	

}