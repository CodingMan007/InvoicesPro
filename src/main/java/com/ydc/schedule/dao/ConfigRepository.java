package com.ydc.schedule.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ydc.excel_to_db.domain.PrintModel;
import com.ydc.excel_to_db.service.PrintService;
import com.ydc.schedule.entity.Config;

@Component  
public class ConfigRepository {
	private static final Logger log = LoggerFactory.getLogger(ConfigRepository.class);
	
	@Autowired
	PrintService printService;
	public Config findOne(Long id) {
		Config config = new Config();
		config.setId(1L);
		config.setCron("0 0/2 * * * ?");
		return config;
	}
	
	
	public List<PrintModel> findPrintAll() {
		log.info("scan method .... ");
		return printService.getPrintAllData();// 从数据库查询出来的  
	}
	
	
	
}
