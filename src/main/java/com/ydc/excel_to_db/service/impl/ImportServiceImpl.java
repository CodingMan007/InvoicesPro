package com.ydc.excel_to_db.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ydc.excel_to_db.dao.ExcelModelMapper;
import com.ydc.excel_to_db.domain.CustomerInfoModel;
import com.ydc.excel_to_db.domain.ExcelModel;
import com.ydc.excel_to_db.domain.IndentModel;
import com.ydc.excel_to_db.domain.SpecificationModel;
import com.ydc.excel_to_db.redis.RedisDao;
import com.ydc.excel_to_db.result.CodeMsg;
import com.ydc.excel_to_db.service.ImportService;
import com.ydc.excel_to_db.util.Constant;
import com.ydc.excel_to_db.util.ExcelUtil;
import com.ydc.excel_to_db.vo.ExcelModelVo;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;




@Service
public class ImportServiceImpl implements ImportService {
	@Autowired
	RedisDao redisDao;
	@Autowired
	ExcelModelMapper excelModelMapper;

	private static final Logger log = LoggerFactory.getLogger(ImportServiceImpl.class);

	/**
	 * @Description: 1.校验上传的文件类型及其对应的数据 2.将通过（1）的数据转换为实体对象集合
	 *               3.清空redis中的部分旧数据cleanCache() 4.将对象集合传入cacheAndPublish()中
	 *               5.封装本次数据校验结果并返回
	 * @Param: [file]
	 * @Retrun: com.ydc.excel_to_db.result.CodeMsg
	 */
	@Override
	public CodeMsg verfiyExcel(MultipartFile file) {
		// 截取原始文件名里的类型名(最后一个“.”后的数据)
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		// 判断是否属于Excel表格的类型，不属于则直接返回“上传文件类型异常”(CodeMsg.FilE_ERROR)
		if (!".xls".equalsIgnoreCase(suffix) && !".xlsx".equalsIgnoreCase(suffix)) {
			return CodeMsg.FilE_ERROR;
		}
		// 对上传的数据进行处理，详情信息请看-com.ydc.excel_to_db.handler.ExcelModelHandler;
		ImportParams importParams = new ImportParams();
		ExcelImportResult<T> result = null;
		// 是否需要验证
		importParams.setNeedVerfiy(true);
		try {
			if (fileName.contains("按单号.xls") || fileName.contains("单号.xls") || fileName.contains("单号") || fileName.contains("按单号")) {
				result = ExcelImportUtil.importExcelMore(file.getInputStream(), IndentModel.class, importParams);
				// 当结果中通过校验的数据(result.getList())为空时
				// 直接返回“上传Excel表格格式有误<br>或者<br> 上传Excel数据为空”(CodeMsg.Excel_FORMAT_ERROR)
				if (result.getList().size() == 0 || result.getList().get(0) == null) {
					return CodeMsg.Excel_FORMAT_ERROR;
				}
				// 清空redis中的部分旧数据
				cleanCache();
				// 将参数result中的部分数据存入redis中，并把格式校验成功的数据发布至对应频道中
				cacheAndPublish(result, new IndentModel());
			} else if (fileName.contains("按规格.xls") || fileName.contains("规格.xls") || fileName.contains("规格") || fileName.contains("按规格")) {
				result = ExcelImportUtil.importExcelMore(file.getInputStream(), SpecificationModel.class, importParams);
				// 当结果中通过校验的数据(result.getList())为空时
				// 直接返回“上传Excel表格格式有误<br>或者<br> 上传Excel数据为空”(CodeMsg.Excel_FORMAT_ERROR)
				if (result.getList().size() == 0 || result.getList().get(0) == null) {
					return CodeMsg.Excel_FORMAT_ERROR;
				}
				// 清空redis中的部分旧数据
				cleanCache();
				// 将参数result中的部分数据存入redis中，并把格式校验成功的数据发布至对应频道中
				cacheAndPublish(result, new SpecificationModel());
			} else if (fileName.contains("客户信息.xls") || fileName.contains("客户信息.xls") || fileName.contains("客户信息")) {
				result = ExcelImportUtil.importExcelMore(file.getInputStream(), CustomerInfoModel.class, importParams);
				// 当结果中通过校验的数据(result.getList())为空时
				// 直接返回“上传Excel表格格式有误<br>或者<br> 上传Excel数据为空”(CodeMsg.Excel_FORMAT_ERROR)
				if (result.getList().size() == 0 || result.getList().get(0) == null) {
					return CodeMsg.Excel_FORMAT_ERROR;
				}
				// 清空redis中的部分旧数据
				cleanCache();
				// 将参数result中的部分数据存入redis中，并把格式校验成功的数据发布至对应频道中
				cacheAndPublish(result, new SpecificationModel());
			} else {
				result = ExcelImportUtil.importExcelMore(file.getInputStream(), ExcelModel.class, importParams);
				// 当结果中通过校验的数据(result.getList())为空时
				// 直接返回“上传Excel表格格式有误<br>或者<br> 上传Excel数据为空”(CodeMsg.Excel_FORMAT_ERROR)
				if (result.getList().size() == 0 || result.getList().get(0) == null) {
					return CodeMsg.Excel_FORMAT_ERROR;
				}
				// 清空redis中的部分旧数据
				cleanCache();
				// 将参数result中的部分数据存入redis中，并把格式校验成功的数据发布至对应频道中
				cacheAndPublish(result, new ExcelModel());
			}
			int succSize = result.getList().size();
			int failSize = result.getFailList().size();
			String msg = "在Excel数据格式校验环节中，共获得有效数据" + (succSize + failSize) + "条</br>其中," + succSize + "条数据通过格式校验,"
					+ failSize + "条数据未通过格式校验 </br> 是否需要查看完整数据同步结果信息？";
			return CodeMsg.userDefined(msg);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		// 当以上过程中抛出异常时，返回"服务端异常，请您联系管理员查看服务器日志"(CodeMsg.SERVER_ERROR)
		return CodeMsg.SERVER_ERROR;
	}

	/**
	 * @Description: 清空redis中的部分旧数据
	 * @Param: []
	 * @Retrun: void
	 */
	@Override
	@Transactional
	public void cleanCache() {
		List<String> keyList = new ArrayList<>(10);
		keyList.add(Constant.failToDBKey);
		keyList.add(Constant.failListKey);
		keyList.add(Constant.failSizeKey);

		keyList.add(Constant.succSizeKey);
		keyList.add(Constant.successListKey);
		keyList.add(Constant.succSizeTempKey);
		redisDao.cleanCache(keyList);
	}

	/**
	 * @Description: 将参数result中的部分数据存入redis中，并把格式校验成功的数据发布至对应频道中
	 * @Param: [result]
	 * @Retrun: void
	 */
	@Transactional
	public void cacheAndPublish(ExcelImportResult<T> result, Object objct) {

		// 通过校验的数据
		List<T> successList = result.getList();
		// 未通过校验的数据
		List<T> failList = result.getFailList();
		int succSize = successList.size();
		int failSize = failList.size();

		// 将未通过校验的数据存入redis中
		redisDao.setStringKey(Constant.failListKey, failList);
		redisDao.setStringKey(Constant.failSizeKey, failSize);
		// 将通过校验的数据存入redis中
		redisDao.setStringKey(Constant.succSizeKey, succSize);
		// succSizeTempKey 用于判断消息队列中未消费数据的大小
		redisDao.setStringKey(Constant.succSizeTempKey, succSize);
		redisDao.setStringKey(Constant.successListKey, successList);
		if (objct instanceof IndentModel) {
			// 发布到消息通道
			redisDao.publish(Constant.receiveIndentList, successList);
		} else if (objct instanceof SpecificationModel) {
			// 发布到消息通道
			redisDao.publish(Constant.receiveSpecificationList, successList);
		} else {
			// 发布到消息通道
			redisDao.publish(Constant.receiveList, successList);
		}

	}

	/**
	 * @Description: 将实体对象存入数据库中
	 * @Param: [excelModel]
	 * @Retrun: boolean 保存成功，返回true；保存失败，返回false；
	 */
	@Override
	public boolean save(ExcelModel excelModel) {
		// INSERT IGNORE 存在重复主键时，返回0
		return excelModelMapper.insert(excelModel) > 0;
	}

	@Override
	public boolean save(IndentModel excelModel) {
		// INSERT IGNORE 存在重复主键时，返回0
		return excelModelMapper.insertIndent(excelModel) > 0;
	}

	
	/**
	 * 第一次写入到临时表中
	 */
	@Override
	public boolean saveToTemp(SpecificationModel excelModel) {
		log.info("specificationModel  date to temp table:  {}",excelModel.getCol2());
		// INSERT IGNORE 存在重复主键时，返回0  写入规格临时表
		return true;//excelModelMapper.insertSpecificationTmp(excelModel) > 0;
	}
	
	/* 将临时表中的数据合并
	 * (non-Javadoc)
	 * @see com.ydc.excel_to_db.service.ImportService#save(com.ydc.excel_to_db.domain.SpecificationModel)
	 */
	@Override
	public boolean save(SpecificationModel excelModel) {
		log.info("specificationModel  date:  {}",excelModel.getCol2());
		// INSERT IGNORE 存在重复主键时，返回0  写入规格临时表
		return excelModelMapper.insertSpecification(excelModel);
	}
	
	@Override
	public boolean savelist(List<SpecificationModel> excelModellist) {
		
		for (SpecificationModel specificationModel : excelModellist) {
			log.info("specificationModel  date:  {}",specificationModel.getCol2());
		}
		
		
		// INSERT IGNORE 存在重复主键时，返回0
		return true;//excelModelMapper.insertSpecification(excelModel) > 0;
	}

	/**
	 * @Description: 根据不同的失败类型的名称(failTypeName), 返回对应的数据
	 * @Param: [failTypeName]
	 * @Retrun: java.util.List<com.ydc.excel_to_db.domain.ExcelModel>
	 */
	@Override
	public List<Map<String, Object>> getFailList(String failTypeName,String fileName,OutputStream fileOut) {
		if ("format".equals(failTypeName)) {
//			return redisDao.getStringListValue(Constant.failListKey, SpecificationModel.class);
			return new ArrayList<Map<String, Object>>() ;
		} else if ("db".equals(failTypeName)) {
//			 return redisDao.getListValue(Constant.successListKey, ExcelModel.class);

			List<Map<String, Object>> allSiteInfos = excelModelMapper.exportSpecificationByNotGAll();
			List<List<Object>> excels = new ArrayList<>();
			
			
			 List<Map<String, Object>> successList = excelModelMapper.exportSpecificationByNotGAll();
			 for (Map<String, Object> map : allSiteInfos) {
					List<Object> row = new ArrayList<>();
					String col1 = map.get("col1").toString();
					String col2 = map.get("col2").toString();
					String col3 = map.get("col3").toString();
					String col4 = map.get("col4").toString();
					
					String col5 = map.get("col5").toString();
					String col6 = map.get("col6").toString();
					String col7 = map.get("col7").toString();
					String col8 = map.get("col8").toString();
					String col9 = map.get("col9").toString();
					String col10 = map.get("col10").toString();
					String col11 = map.get("col11").toString();
					
					row.add(col1);
					row.add(col2);
					row.add(col3);
					row.add(col4);
					row.add(col5);
					row.add(col6);
					row.add(col7);
					row.add(col8);
					row.add(col9);
					row.add(col10);
					row.add(col11);
					excels.add(row);
				}
			  ExcelUtil.exportExcel(excels, fileName, "xls", fileOut);
			return successList;
		} else {
			return new ArrayList<Map<String, Object>>() ;
		}
	}

	/**
	 * @Description: 根据key值，返回redis中对应的结果
	 * @Param: [key]
	 * @Retrun: long
	 */
	@Override
	public long getTempSize(String key) {
		return redisDao.getStringValue(key, long.class);
	}

	/**
	 * @Description: 获取同步结果页面中饼状图所需的数据
	 * @Param: []
	 * @Retrun: com.ydc.excel_to_db.vo.ExcelModelVo 封装的值对象
	 */
	@Override
	public ExcelModelVo getResultData() {
		// 获取格式校验失败的数据大小
		Long failSize = redisDao.getStringValue(Constant.failSizeKey, long.class);
		// 获取格式校验成功的数据大小
		Long succSize = redisDao.getStringValue(Constant.succSizeKey, long.class);
		// 获取导入数据库失败的数据大小
		Long failToDBSize = redisDao.getListSize(Constant.failToDBKey);
		return new ExcelModelVo(succSize, failSize, failToDBSize);
	}

}