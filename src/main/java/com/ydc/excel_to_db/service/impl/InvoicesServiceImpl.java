package com.ydc.excel_to_db.service.impl;

import java.beans.Transient;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydc.excel_to_db.dao.CustomerModelMapper;
import com.ydc.excel_to_db.dao.ExcelModelMapper;
import com.ydc.excel_to_db.dao.PrintModelMapper;
import com.ydc.excel_to_db.dao.ProductCodeModelMapper;
import com.ydc.excel_to_db.domain.PrintModel;
import com.ydc.excel_to_db.domain.ProductCodeModel;
import com.ydc.excel_to_db.domain.SpecificationModel;
import com.ydc.excel_to_db.redis.RedisDao;
import com.ydc.excel_to_db.service.InvoicesService;
import com.ydc.excel_to_db.util.Constant;
import com.ydc.excel_to_db.util.DbToExcelTool;
import com.ydc.excel_to_db.util.xml.BuildXML;
import com.ydc.excel_to_db.vo.CustomerInfoModelVo;
import com.ydc.excel_to_db.vo.IndentModelVo;
import com.ydc.excel_to_db.vo.SpecificationModelVo;




@Service
public class InvoicesServiceImpl implements InvoicesService {
	@Autowired
	RedisDao redisDao;
	
	@Autowired
	ExcelModelMapper excelModelMapper;
	
	@Autowired
	PrintModelMapper printModelMapper;

	@Autowired
	ProductCodeModelMapper productCodeModelMapper;
	
	@Autowired
	CustomerModelMapper customerModelMapper;

	private static final Logger log = LoggerFactory.getLogger(InvoicesServiceImpl.class);

	private SpecificationModelVo selectvalu;
	
	
    SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
	@Override
	@Transient
	public List<SpecificationModelVo> getResultSpecificationAllNotGData() {
		//把临时表中的数据合并并更新到规格表中
//		List<IndentModelVo> indentdata = excelModelMapper.selectIndentByIsAll();
//		SpecificationModel excelModel  = new SpecificationModel(); 
//		for (IndentModelVo indentModelVo : indentdata) {
//			excelModel.setCol4(indentModelVo.getIndentnumber());
//			SpecificationModelVo specificationModelVo = excelModelMapper.selectSpecificationByindentNumberAndSpecificationAll(excelModel);
//			excelModelMapper.insertSpecification(specificationModelVo);
//			
//			excelModelMapper.updateByIsGenerateInvoice(indentModelVo);
//		}
		//清除临时表中的所有数据
//		excelModelMapper.deleteSpecificationModeltmp();
		return excelModelMapper.selectSpecificationByNotGAll();
	}
	
	@Override
	public List<SpecificationModelVo> getResultSpecificationAllIsGData() {
		return excelModelMapper.selectSpecificationByIsGAll();
	}
	
	@Override
	public List<SpecificationModelVo> getResultSpecificationDateAndNameData(
			String customername,
			String startTime, 
			String endTime ,
			int isgenerateinvoice) {
		return excelModelMapper.getResultSpecificationDateAndNameData(customername.trim(), startTime, endTime, isgenerateinvoice);
	}
	@Override
	public List<SpecificationModelVo> getResultSpecificationDateData(
			String startTime, 
			String endTime ,
			int isgenerateinvoice) {
		return excelModelMapper.selectSpecificationByDate(startTime,endTime,isgenerateinvoice);
	}
	@Override
	public List<SpecificationModelVo> getResultSpecificationNameData(String customername,int isgenerateinvoice) {
		return excelModelMapper.selectSpecificationByName(customername.trim(), isgenerateinvoice);
	}

	@Override
	public List<IndentModelVo> getResultIndentAllData() {
		return excelModelMapper.selectIndentByAll();
	}

	@Override
	public List<IndentModelVo> getResultIndentModelDateData(String startTime, String endTime) {
		return excelModelMapper.selectIndentByDate(startTime, endTime);
	}

	@Override
	public List<IndentModelVo> getResultIndentModelCustomerCodeData(String customerCode) {
		return excelModelMapper.selectIndentByCustomerCode(customerCode);
	}
	
	/**
	 * 根据用户提供ID为发票的开票内容
	 */
	@Override
	@Transient
	public boolean putResultSpecificationIdData(String id,String invoicetype) {
		HashMap<CustomerInfoModelVo, List<SpecificationModelVo>> invoicesbody = new HashMap<>();
		
		CustomerInfoModelVo customerInfoModelVo =new CustomerInfoModelVo();
		SpecificationModelVo spmodeltmp = null;
		
    	int retrunint = 0;
    	String idtoprint = id;
    	String[] isarry= id.split(",");
    	for (int i = 0; i < isarry.length; i++) {
    		id = isarry[i];
    		retrunint = excelModelMapper.updatespecificationByIsGenerateInvoice(id);
    		if (retrunint > 0) {
    			//生成发票内容
    			selectvalu = excelModelMapper.selectSpecificationByID(id);
    			ProductCodeModel productCodeModel = new ProductCodeModel();
    			productCodeModel.setSpecificationtype(selectvalu.getName());
    			
    			//根据规格找对应的规格码
    			String spbmvalue = productCodeModelMapper.selectProductCodeModelByName(productCodeModel).getSpbmcode();
    			selectvalu.setSpbmcode(spbmvalue);
    			spmodeltmp = getSpecificationModelByIdGetCustomername(id);
    			customerInfoModelVo.setCustomername(spmodeltmp.getCustomername());
    			
    			//将要开发票的内容写入合并表
    			excelModelMapper.insertSpecificationMerge(selectvalu);
			}
    	}
    	//将合并表中的数据按规格合并生成不含税发票
    	List<SpecificationModelVo>  list =  excelModelMapper.selectSpecificationMerge();
    	CustomerInfoModelVo customerInfoVo = customerModelMapper.selectCustomerInfoModelByID(customerInfoModelVo);
		if (customerInfoVo != null) {
			invoicesbody.put(customerInfoVo, list);
		}else {
			invoicesbody.put(customerInfoModelVo, list);
		}
		invoicetype = invoicetype.equals("PTFP")?"PTFP":"ZYFP";
    	putIndentModelToXml(invoicetype, idtoprint, invoicesbody);
		boolean isnull = (retrunint > 0)? true:false;
		
		for (int i = 0; i < isarry.length; i++) {
			id = isarry[i];
			//清除所有的已合并数据
			log.info("this's delete id values :{}",id);
			excelModelMapper.deleteSpecificationMerge(id);
		}
		return isnull;
	}
	
	/**
	 *  生成发票成功后写入打印表
	 * @param buildtype
	 * @param idstring
	 * @param invoicesbody
	 * @return
	 */
	@Transient
	public boolean putIndentModelToXml(String buildtype , String idstring ,HashMap<CustomerInfoModelVo, List<SpecificationModelVo>> invoicesbody) {
		String  fpname = null ;
		if (buildtype.equals("PTFP")) {
		 Date now = new Date();
			fpname = Constant.PTFP + ft.format(now) + ".XML";
		} else{
			 Date now = new Date();
			fpname = Constant.ZYFP + ft.format(now) + ".XML";
		}
//		if (invoicesbody.size() > 8) {
			Date now = new Date();
			String  listfpname = buildtype + "_LIST_" + ft.format(now) + ".xls"; ;
			//生成清单类型的发票
			try {
				createListExcelLDocument(idstring, invoicesbody, listfpname,0);
			} catch (UnsupportedEncodingException e) {
				log.info("生成清单类型的发票发生异常 : {}", e);
			}
//		}
			//生成标准的发票
			createStandardXMLDocument(idstring, invoicesbody, fpname,0);
		return true;
	}
	
	/*
	 * 根据规格表中的ID找到对应的公司名 以此作为发票购买信息
	 */
	private  SpecificationModelVo getSpecificationModelByIdGetCustomername(String id) {
		SpecificationModelVo spmodeltmp= excelModelMapper.selectSpecificationByID(id);
		return spmodeltmp;
	}
	
	/**
	 * 
	 * @param idstring 传入要生成的内容ID号
	 * @param invoicesbody  要生成的发票的数据
	 * @param fpname 要生成的发票名
	 * @param returngeneratebodyamount  控制每张发票打印的内容不得超过8行
	 * @return
	 */
		public PrintModel createStandardXMLDocument(String idstring, HashMap<CustomerInfoModelVo, List<SpecificationModelVo>> invoicesbody, String fpname, int returncount) {
			PrintModel printModel = null;
			Double sumvalue =00.00;
			Double sumInvoiceamount = 00.00;
			String[] strtoarry= idstring.split(",");
			//购货方客户名称
			String customerName = null;
			boolean flag = false;
			
			//开几个客户的发票
			Set<CustomerInfoModelVo> keys = invoicesbody.keySet();
			
			//发票内容的总条数
			for (CustomerInfoModelVo cvo : keys) {
				int xmltotalcontent = strtoarry.length ;
				xmltotalcontent = invoicesbody.get(cvo).size();
				customerName = cvo.getCustomername();

				//根据发票的内容确定按8条内容能生成几张发票
				int circulationcfrequency = xmltotalcontent/8 == 0?1:xmltotalcontent/8;
				int residuerow = xmltotalcontent %8;
				
				//此文件含有的发票信息数量
				int circulationcfrequencysumpage = 0;
					circulationcfrequencysumpage = residuerow == xmltotalcontent?1:circulationcfrequency;
				int	zsl = circulationcfrequencysumpage;
				if (returncount < circulationcfrequency) {
					// 发票购方信息
//					Element node2 = BuildXML.generateTitle(document, cvo, zsl, 1).addElement("Spxx");
					
					//发票每张按只能有8列开票项目
					for (int i = 1; i <=circulationcfrequency; i++) {
						// 开始生成XML文件
						Document document = DocumentHelper.createDocument();
						document.setXMLEncoding("GBK");
						String newid="";
						Element node2 = BuildXML.generateTitle(document, cvo, zsl=1, 1).addElement("Spxx");
						int index = 0;
						// 生成标准发票开票项为8项
						for (;returncount < i * 8; returncount++) {
							if (returncount < xmltotalcontent) {
								if (returncount < 8 ) {
									log.info("returncount: {}   ,  xmltotalcontent:{}  ,invoicesbody.get(cvo).size() :{} " ,returncount ,xmltotalcontent , invoicesbody.get(cvo).size());
									BuildXML.generateBody(node2, returncount, invoicesbody.get(cvo).get(returncount));
									newid=newid+strtoarry[returncount]+",";
									//计算发票总额
									SpecificationModelVo suminvoiceamountValue = excelModelMapper.sumSpecificationByID(strtoarry[returncount].toString());
									sumvalue = Double.parseDouble(suminvoiceamountValue.getTaxincludedamount().toString());
									sumInvoiceamount=(sumInvoiceamount+sumvalue);
								}else if (returncount < i * 8) {
									BuildXML.generateBody(node2, index++, invoicesbody.get(cvo).get(returncount));
									newid=newid+strtoarry[returncount]+",";
									//计算发票总额
									SpecificationModelVo suminvoiceamountValue = excelModelMapper.sumSpecificationByID(strtoarry[returncount].toString());
									sumvalue = Double.parseDouble(suminvoiceamountValue.getTaxincludedamount().toString());
									sumInvoiceamount=(sumInvoiceamount+sumvalue);
								}
							} 
						}
						String[] str1 = fpname.split("_");
						fpname = str1[0]+"_"+str1[1].substring(0, 14)+ "_" +i+".XML";
						
						flag = BuildXML.putOutXml(document, fpname);
						printModel = BuildXML.putPrintDate(customerName, fpname, newid,sumInvoiceamount);
						//写入打印表
						printModelMapper.insertIndent(printModel);
					}
				}
				//发票每张按只能有8列开票项目之外的其它列
				if (returncount < xmltotalcontent) {
					String newid="";
					int lotnumber=0 ;
					lotnumber++;
					String[] str1 = fpname.split("_");
					fpname = str1[0]+"_"+str1[1].substring(0, 14)+"_"+(circulationcfrequencysumpage + lotnumber)+".XML";
					// 开始生成XML文件
					Document documenlot = DocumentHelper.createDocument();
					documenlot.setXMLEncoding("GBK");
					// 发票购方信息
					Element node2 = BuildXML.generateTitle(documenlot, cvo, 1, 1).addElement("Spxx");
//					Element nodelot = BuildXML.generateTitle(documenlot, cvo, circulationcfrequencysumpage, circulationcfrequencysumpage).addElement("Spxx");
					lotnumber = 0;
					for (; returncount < xmltotalcontent; returncount++) {
						if (returncount < strtoarry.length) {
							BuildXML.generateBody(node2, lotnumber++, invoicesbody.get(cvo).get(returncount));
							newid=newid+strtoarry[returncount]+",";
							SpecificationModelVo suminvoiceamountValue = excelModelMapper.sumSpecificationByID(strtoarry[returncount].toString());
							sumvalue = Double.parseDouble(suminvoiceamountValue.getTaxincludedamount().toString());
							sumInvoiceamount=(sumInvoiceamount+sumvalue);
						}
					}
					flag = BuildXML.putOutXml(documenlot, fpname);
					printModel = BuildXML.putPrintDate(customerName, fpname, newid,sumInvoiceamount);
					//写入打印表
					printModelMapper.insertIndent(printModel);
				}
			}
			return printModel;
		}
		/**
		 * 
		 * @param idstring 传入要生成的内容ID号
		 * @param invoicesbody  要生成的发票的数据
		 * @param fpname 要生成的发票名
		 * @param returngeneratebodyamount  控制每张发票打印的内容不得超过8行
		 * @return
		 * @throws UnsupportedEncodingException 
		 */
		public PrintModel createListExcelLDocument(String idstring, HashMap<CustomerInfoModelVo, List<SpecificationModelVo>> invoicesbody, String fpname, int returncount) throws UnsupportedEncodingException {
			PrintModel printModel = null;
			Double sumvalue =00.00;
			Double sumInvoiceamount = 00.00;
			String[] strtoarry= idstring.split(",");
			
			 String sheetName="发票清单";
		     HSSFWorkbook wb = new HSSFWorkbook();
		     HSSFSheet sheet = wb.createSheet(sheetName);//定义sheet名
		     DbToExcelTool exportExcel = new DbToExcelTool(wb,sheet);//调用Excel工具类
		     fpname = new String(fpname.getBytes("GBK"), "GBK");//修改编码格式
		     //定义第一行标题
		        List<String> list=new ArrayList<String>();
		        list.add("序号");
		        list.add("货物或应税劳务、服务名称");
		        list.add("计量单位");
		        list.add("规格型号");
		        list.add("数量");
		        list.add("金额");
		        list.add("税率");
		        list.add("商品税目");
		        list.add("折扣金额");
		        list.add("税额");
		        list.add("折扣税额");
		        list.add("折扣率");
		        list.add("单价");
		        list.add("价格方式");
		        list.add("税收分类编码版本号");
		        list.add("税收分类编码");
		        list.add("企业商品编码");
		        list.add("使用优惠政策标识");
		        list.add("零税率标识");
		        list.add("优惠政策说明");
		        list.add("中外合作油气田标识");
		        //定义第一行
		        exportExcel.createNormalTwoRow(list, 0);
		        
				Set<CustomerInfoModelVo> keys = invoicesbody.keySet(); // 此行可省略，直接将map.keySet()写在for-each循环的条件中
				//发票内容的总条数
				int xmltotalcontent = 0 ;
				//购货方客户名称
				String customerName = null;
				String newid="";
				log.info("input id string  {} , {}",idstring ,returncount);

				for (CustomerInfoModelVo key : keys) {
					xmltotalcontent = invoicesbody.get(key).size();
					customerName = key.getCustomername();
					// 生成标准发票开票项为8项
					for (;returncount < xmltotalcontent; returncount++) {
						if (returncount < strtoarry.length) {
							 //导入数据
					        exportExcel.createColumHeader(invoicesbody.get(key));
							log.info("strtoarry arrary length:  {} ,returncount index: {} , vlues: {}",strtoarry.length,returncount, strtoarry[returncount]);

					        newid=newid+strtoarry[returncount]+",";
								//计算发票总额
								SpecificationModelVo suminvoiceamountValue = excelModelMapper.sumSpecificationByID(strtoarry[returncount].toString());
								sumvalue = Double.parseDouble(suminvoiceamountValue.getTaxincludedamount().toString());
								sumInvoiceamount=(sumInvoiceamount+sumvalue);
							}
						} 
					}
		       
		       
		        //输出文件流，把相应的Excel工作簿 输出到本地
		        exportExcel.outputExcel(Constant.EXPORTPATH+fpname);
		        
			//写入打印表
		    printModel = BuildXML.putPrintDate(customerName, fpname, newid,sumInvoiceamount);
			printModelMapper.insertIndent(printModel);
			return printModel;
		}

	@Override
	public List<SpecificationModelVo> getResultSpecificationNumberAllData(String indentNumber) {
		return excelModelMapper.selectSpecificationByIndentNumber(indentNumber);
	}

	@Override
	public SpecificationModelVo getResultSpecificationId(SpecificationModelVo specificationModelVo) {
		return excelModelMapper.selectSpecificationByID(specificationModelVo.getId());
	}

	@Override
	public int putResultSpecificationId(SpecificationModelVo specificationModelVo) {
		return excelModelMapper.updatespecificationByNotGenerateInvoice(specificationModelVo.getId());
	}

	@Override
	public boolean delInvoicesDeleteAll() {
		excelModelMapper.deleteAllIndentmodell();
		excelModelMapper.deleteAllPrintmodel();
		excelModelMapper.deleteAllSpecificationMerge();
		excelModelMapper.deleteAllSpecificationmodel();
		excelModelMapper.deleteAllSpecificationmodelTmp();
		return true;
	}

	@Override
	public boolean delInvoicesDeleteGenerate() {
		excelModelMapper.deleteAllIndentmodell();
		excelModelMapper.deleteAllPrintmodel();
		excelModelMapper.deleteAllSpecificationMerge();
		excelModelMapper.deleteAllSpecificationmodelByGenerate();
		excelModelMapper.deleteAllSpecificationmodelTmp();
		return true;
	}

}