package com.ydc.schedule.quartz;

import java.beans.Transient;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.ydc.excel_to_db.dao.PrintModelMapper;
import com.ydc.excel_to_db.domain.PrintModel;
import com.ydc.excel_to_db.util.Constant;
import com.ydc.excel_to_db.util.common.ScanIsFileExist;
import com.ydc.schedule.dao.ConfigRepository;  
  
@Configuration  
@Component // 此注解必加  
@EnableScheduling // 此注解必加  
public class ScheduleTask {  
    private static final Logger log =  LoggerFactory.getLogger(ScheduleTask.class);  

    @Autowired
	PrintModelMapper printModelMapper;
    
    @Autowired  
    private ConfigRepository repository;  
    
    
    @Transient
    /*
     * IsPrint是否已打印生成的发票，
     *   0：未打印发票 1：（表示目录下有此文件）已打印发票（表示目录下没有此文件）
     */
    public void goScanfileIsExist(){ 
		 Date now = new Date();
    	log.info("Hello machine, You will go scan print file been not exist......."); 
    	 List<PrintModel> searchName = repository.findPrintAll();// 从数据库查询出来的  
         for (int i = 0; i < searchName.size(); i++) {
         	String filePath  = Constant.EXPORTPATH + searchName.get(i).getGenerateName();
         	boolean scansucc = ScanIsFileExist.judeDirExists(new File(filePath));
         	//目录下有此文件就代表还没有打印
         	if (!scansucc) {
				PrintModel printModel =new PrintModel();
         		printModel.setGenerateName(searchName.get(i).getGenerateName());
         		printModel.setIsPrint("已打印发票");
         		printModel.setPrintDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now));
         		printModelMapper.updatePrintInfoModelByName(printModel);
			}
 		}
    }  
} 