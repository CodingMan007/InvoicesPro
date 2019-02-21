package com.ydc.excel_to_db.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydc.excel_to_db.dao.CustomerModelMapper;
import com.ydc.excel_to_db.domain.CustomerInfoModel;
import com.ydc.excel_to_db.redis.RedisDao;
import com.ydc.excel_to_db.service.CustomerService;
import com.ydc.excel_to_db.vo.CustomerInfoModelVo;




@Service
public class CustomerServiceImpl implements CustomerService {
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

	
	@Autowired
	RedisDao redisDao;
	
	@Autowired
	 CustomerModelMapper customerModelMapper;
	

	@Override
	public List<CustomerInfoModelVo> getCusotmerInfoAllData() {
		customerModelMapper.selectCustomerInfoModelByAll();
		return customerModelMapper.selectCustomerInfoModelByAll() ;
	}


	@Override
	public Long putResultCustomerInfoReplaceData(CustomerInfoModel customerInfoModel) {
		return customerModelMapper.insertOrReplaceIndent(customerInfoModel);
	}
	

}