package com.ydc.excel_to_db.util.common;

import java.util.List;

import com.ydc.excel_to_db.util.constant.RestInterfaceConstant;
import com.ydc.excel_to_db.util.model.CorrectResultModel;
import com.ydc.excel_to_db.util.model.ErorrResultModel;
import com.ydc.excel_to_db.util.model.ExpandResultModel;

/**
 *
 * @author herman
 * @version
 * @date 2016年10月14日
 */
@SuppressWarnings("rawtypes")
public class RestApiEncapsulation {

	private CorrectResultModel crm;

	private ErorrResultModel erm;
	
	private ExpandResultModel er;

	public CorrectResultModel EncapCorrectResult(Object object) {

		if (object != null) {
//			if (object instanceof List && ((List) object).size() == 1) {
//				object = ((List) object).get(0);
//			}
			crm = new CorrectResultModel();
			crm.setData(object);
			crm.setCode(RestInterfaceConstant.Code_200);
			crm.setStatus(RestInterfaceConstant.STATUS_200);
		}
		return crm;

	}
	
	public ExpandResultModel EncapExpandResult(Object object,Object object1) {

		if (object != null) {
			if (object instanceof List && ((List) object).size() == 1) {
				object = ((List) object).get(0);
			}
			er = new ExpandResultModel();
			er.setData(object);
			er.setCurrentAreaData(object1);
			er.setCode(RestInterfaceConstant.Code_200);
			er.setStatus(RestInterfaceConstant.STATUS_200);

		}
		return er;

	}
	
	
	
	/**
	 * 用于特殊处理：返回结果为一条时，也作为一个数组返回
	 * @param object
	 * @return
	 */
	public CorrectResultModel EncapCorrectResult1(Object object) {

		if (object != null) {
			crm = new CorrectResultModel();
			crm.setData(object);
			crm.setCode(RestInterfaceConstant.Code_200);
			crm.setStatus(RestInterfaceConstant.STATUS_200);

		}
		return crm;

	}

	public ErorrResultModel EncapErrorResult(Object object) {

		erm = new ErorrResultModel();
		erm.setCode(RestInterfaceConstant.Code_500);
		erm.setStatus(RestInterfaceConstant.STATUS_500);
		erm.setMessage(RestInterfaceConstant.STATUS_500_MESSAGE);
		erm.setDetail(object);
		return erm;
	}
	
	public ErorrResultModel EncapExecptionResult(Object object) {

		erm = new ErorrResultModel();
		erm.setCode(RestInterfaceConstant.Code_405);
		erm.setStatus(RestInterfaceConstant.STATUS_450_CODE);
		erm.setMessage(RestInterfaceConstant.STATUS_450_MESSAGE);
		erm.setDetail(object);
		return erm;
	}

}
