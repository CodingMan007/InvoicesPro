package com.ydc.excel_to_db.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydc.excel_to_db.domain.CustomerInfoModel;
import com.ydc.excel_to_db.domain.ExcelModel;
import com.ydc.excel_to_db.domain.IndentModel;
import com.ydc.excel_to_db.domain.SpecificationModel;
import com.ydc.excel_to_db.service.CustomerService;
import com.ydc.excel_to_db.service.ImportService;
import com.ydc.excel_to_db.util.Constant;
import com.ydc.excel_to_db.util.JsonUtil;

/**
 * @Description: 消息接收者，将其在ExcelToDbApplication.java中注入消息监听容器(MessageListenerAdapter)中
 * @Author: joss xu
 * @Date: Created in  2018-2-6
 */
@Service
public class Receiver {
    @Autowired
    ImportService importService;
    
    @Autowired
    CustomerService customerService;
    
    
    @Autowired
    RedisDao redisDao;
    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    /**
     * @Description: 用于接收单个对象，将对象同步至数据库，如果同步失败，则存入redis中
     * @Param: [message] “fastjson”转换后的json字符串
     * @Retrun: void
     */
    public void receiveSingle(String message) throws InterruptedException {
        // 将json字符串转换成实体对象
    	ExcelModel excelModel = JsonUtil.stringToBean(message, ExcelModel.class);        
        // 尝试同步数据库并返回同步结果
        boolean result = importService.save(excelModel);
        if (!result)
            // 同步失败，将其存入redis中
			redisDao.leftPushKey(Constant.failToDBKey, excelModel);
        else
            // 同步成功，输出至日志中
            log.info("成功插入数据库的数据：" + excelModel.getCol2());
        // 加上-1，其实也就是做减1操作
        redisDao.incrOrDecr(Constant.succSizeTempKey, -1);
    }
    //单号写入
    public void receiveIndentSingle(String message) throws InterruptedException {
    	
    	// 将json字符串转换成实体对象
    	IndentModel excelModel = JsonUtil.stringToBean(message, IndentModel.class);        
    	// 尝试同步数据库并返回同步结果
    	boolean result = importService.save(excelModel);
    	
    	if (!result)
    		// 同步失败，将其存入redis中
    		redisDao.leftPushKey(Constant.failToDBKey, excelModel);
    	else
		// 同步成功，输出至日志中
		log.info("成功插入数据库的数据：" + excelModel.getCol4());
    	// 加上-1，其实也就是做减1操作
    	redisDao.incrOrDecr(Constant.succSizeTempKey, -1);
    	CustomerInfoModel customerInfoModel = new CustomerInfoModel();
    	customerInfoModel.setCol1("");
    	//"客户名称"
    	customerInfoModel.setCol2(excelModel.getCol4());
    	//"客户代码"
    	customerInfoModel.setCol3(excelModel.getCol3());
    	
    	customerInfoModel.setCol4("");
    	customerInfoModel.setCol5("");
    	customerInfoModel.setCol6("");
    	customerInfoModel.setCol7("");
    	
    	customerService.putResultCustomerInfoReplaceData(customerInfoModel);
    	
    }
    public void receiveSpecificationSingle(String message) throws InterruptedException {
    	// 将json字符串转换成实体对象
    	SpecificationModel excelModel = JsonUtil.stringToBean(message, SpecificationModel.class);        
    	// 尝试同步数据库并返回同步结果
    	boolean result = importService.save(excelModel);
    	
    	//boolean result = importService.saveToTemp(excelModel);
    	if (!result)
    		// 同步失败，将其存入redis中
    		redisDao.leftPushKey(Constant.failToDBKey, excelModel);
    	else
    		// 同步成功，输出至日志中
    		log.info("成功插入数据库的数据：" + excelModel.getCol2());
    	// 加上-1，其实也就是做减1操作
    	redisDao.incrOrDecr(Constant.succSizeTempKey, -1);
    }

    /**
     * @Description: 用于接收对象集合，将集合遍历拆分成单个对象并进行发布
     * @Param: [message] “fastjson”转换后的json字符串
     * @Retrun: void
     */
    public void receiveList(String message) throws InterruptedException {
        // 将json字符串转换成对象集合
        List<ExcelModel> list = JsonUtil.stringToList(message, ExcelModel.class);
        // 遍历集合,并依次将其发布
        for (ExcelModel excelModel : list) {
            redisDao.publish(Constant.receiveSingle, excelModel);
        }
    }
    public void receiveIndentList(String message) throws InterruptedException {
    	// 将json字符串转换成对象集合
    	List<IndentModel> list = JsonUtil.stringToList(message, IndentModel.class);
    	// 遍历集合,并依次将其发布
    	for (IndentModel excelModel : list) {
    		redisDao.publish(Constant.receiveIndentSingle, excelModel);
    	}
    }
    public void receiveSpecificationList(String message) throws InterruptedException {
    	// 将json字符串转换成对象集合
    	List<SpecificationModel> list = JsonUtil.stringToList(message, SpecificationModel.class);
    	// 遍历集合,并依次将其发布
    	for (SpecificationModel excelModel : list) {
    		redisDao.publish(Constant.receiveSpecificationSingle, excelModel);
    	}
    }
}
