package com.ydc.excel_to_db.util.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ydc.excel_to_db.domain.PrintModel;
import com.ydc.excel_to_db.util.Constant;
import com.ydc.excel_to_db.util.common.Tools;
import com.ydc.excel_to_db.vo.CustomerInfoModelVo;
import com.ydc.excel_to_db.vo.SpecificationModelVo;

public class BuildXML {
    private static final Logger log = LoggerFactory.getLogger(BuildXML.class);

	static DecimalFormat df1 = new DecimalFormat("#.00");  
	static DecimalFormat dj = new DecimalFormat("#.0000000");  
	static DecimalFormat sl = new DecimalFormat("#.000");  
	@SuppressWarnings("unused")
	/**
	 * @param document  生成XML文件头
	 * @param cvo  购方信息
	 * @param zsl 此文件含有的发票信息数量
	 * @param djh 当前发票单据号
	 * @return
	 */
	public static Element generateTitle(Document document, CustomerInfoModelVo cvo, int zsl, int djh) {
		log.info("Document : {} , CustomerInfoModelVo: {} ",document.equals("") , cvo.equals(""));
		Element root = document.addElement("Kp");
		// 有此节点，则表示用带分类编码
		root.addElement("Version").addText("2.0");
		// 此文件含有的单据信息数量
		Element data = root.addElement("Fpxx");
		// 此文件含有的发票信息数量（20字节）
		data.addElement("Zsl").addText(String.valueOf(zsl));
		Element node1 = data.addElement("Fpsj");
			Element kpdwinfo = node1.addElement("Fp");
				// 当前发票单据号（20字节）
				kpdwinfo.addElement("Djh").setText(String.valueOf(djh));
				
				// 票据头部项目 服务明细
				// 购方名称（100字节）
				String Gfmc = cvo.getCustomername()!= null?cvo.getCustomername():"";
				kpdwinfo.addElement("Gfmc").setText(Gfmc);
				
				// 购方税号
				String Gfsh = cvo.getCustomeridentification()!= null?cvo.getCustomeridentification():"";
				kpdwinfo.addElement("Gfsh").setText(Gfsh);
				
				// 购方银行账号（100字节）
				String Gfyhzh = cvo.getCustomerbank()!= null?cvo.getCustomerbank():"";
				String account = cvo.getCustomeraccount() !=null ?cvo.getCustomeraccount():"";
				kpdwinfo.addElement("Gfyhzh").setText(Gfyhzh +" "+ account);
				
				// 购方地址电话（100字节）
				String Gfdzdh = cvo.getCustomeraddress()!= null?cvo.getCustomeraddress():"";
				String telephone = cvo.getCustomertelephone() !=null ?cvo.getCustomertelephone():"";
				kpdwinfo.addElement("Gfdzdh").setText(Gfdzdh + " " +telephone);
				
				// 备注（240字节）
				kpdwinfo.addElement("Bz").setText("");
				// 复核人（8字节）
				kpdwinfo.addElement("Fhr").setText("\r\n");
				// 收款人（8字节）
				kpdwinfo.addElement("Skr").setText("\r\n");
				// 商品编码版本号(20字节)（必输项）
				kpdwinfo.addElement("Spbmbbh").setText("28.0");
				// 含税标志 0：不含税税率，1：含税税率，2：差额税;中外合作油气田（原海洋石油）5%税率、1.5%税率为1，差额税为2，其他为0；
				kpdwinfo.addElement("Hsbz").setText("0");

		return kpdwinfo;
	}

	@SuppressWarnings("unused")
	/**
	 * 
	 * @param node2 发票父级节点
	 * @param xhcount  开票项目数据
	 * @param svo  各项的数据内容
	 * @return
	 */
	public static Element generateBody(Element node2, int xhcount, SpecificationModelVo svo) {
		
		// 票据中间项目 服务明细
		Element fwqdlist = node2.addElement("Sph");
				// 序号
				fwqdlist.addElement("Xh").setText(String.valueOf(xhcount + 1));
				// 商品名称，金额为负数时此行是折扣行，折扣行的商品名称应与上一行的商品名称一致（100字节）
				String Spmc = svo.getName().equals(Constant.SELLVALUE)?Constant.INVOICEVALUE:svo.getName();
					   Spmc = Spmc.equals(Constant.SELLQFVALUE)?Constant.INVOICEVALUE:Spmc;
				fwqdlist.addElement("Spmc").setText(Spmc);
				// 规格型号（40字节）
				fwqdlist.addElement("Ggxh").setText(svo.getSpecification().substring(0, 3));
				// 计量单位（32字节）
				fwqdlist.addElement("Jldw").setText(svo.getMeasurementunit());
				// 商品编码(19字节)（必输项）
				fwqdlist.addElement("Spbm").setText(Tools.addZeroForNum(svo.getSpbmcode(), 19));
				// fwqdlist.addElement("Spbm").setText(svo.getSpecification());
				// 企业商品编码（20字节）
				fwqdlist.addElement("Qyspbm").setText("\r\n");
				// 是否使用优惠政策标识0：不使用，1：使用（1字节）
				fwqdlist.addElement("Syyhzcbz").setText("0");
				// 零税率标识 空：非零税率，0：出口退税，1：免税，2：不征收，3普通零税率（1字节）
				fwqdlist.addElement("Lslbz").setText("3");
				// 优惠政策说明（50字节）
				fwqdlist.addElement("Yhzcsm").setText("\r\n");
				// 单价（中外合作油气田（原海洋石油）5%税率，单价为含税单价）
				
				//原数为：含税金额 处理成不税金额 
				BigDecimal BZSL=new BigDecimal(Constant.HSLV);
				BigDecimal Je= svo.getTaxincludedamount().divide(BZSL,6,RoundingMode.HALF_UP);
				BigDecimal Quantity=svo.getQuantity();
				BigDecimal taxincludedprice = Je.divide(Quantity,6,RoundingMode.HALF_UP);
				
				fwqdlist.addElement("Dj").setText(String.valueOf(dj.format(taxincludedprice)));
				// 数量
				fwqdlist.addElement("Sl").setText(String.valueOf(sl.format(svo.getQuantity())));
				// 金额，当金额为负数时为折扣行
				fwqdlist.addElement("Je").setText(String.valueOf(df1.format(Je)));
				// 税率
				fwqdlist.addElement("Slv").setText(Constant.FPSLV);
				// 税额
				BigDecimal FPSLV=new BigDecimal(Constant.FPSLV);
				BigDecimal  se = Je.multiply(FPSLV);
				fwqdlist.addElement("Se").setText(String.valueOf(dj.format(se)));
				// 扣除额，用于差额税计算
				String kce = "";//String.valueOf(df1.format(svo.getTaxes().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
				fwqdlist.addElement("Kce").setText((kce));
			
			return fwqdlist;
	}
	
	//输出XML文件
	public static Boolean putOutXml(Document document, String fpname) {
		boolean flag = false;
		FileWriter outfile = null;
		try {
			outfile = new FileWriter(Constant.EXPORTPATH+fpname); // 文件输出格式
			document.write(outfile);
			OutputFormat format = OutputFormat.createPrettyPrint(); // 转换成字符串
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(System.out, format);
			writer.write(document);
			
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outfile != null) {
				try {
					outfile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	public static  PrintModel putPrintDate(String customerName,String fpname ,String idstring ,Double sumInvoiceamount) {
		 Date now = new Date();
		PrintModel printModel = new PrintModel();
//		 写入打印表
		printModel.setGenerateName(fpname);
		printModel.setCustomerName(customerName);
		printModel.setGenerateId(idstring);
		//0：未生成发票 1：已生成发票
		printModel.setIsGenerateInvoice("已生成发票");
		//0：未打印发票 1：已打印发票
		printModel.setIsPrint("未打印发票");
		printModel.setGenerateDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now));
		printModel.setPrintDate("");
		printModel.setPrintContent(System.out.toString());
		printModel.setInvoiceamount(df1.format(sumInvoiceamount));
		return printModel;
	}
}