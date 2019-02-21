package com.ydc.excel_to_db.util.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ydc.excel_to_db.util.common.inter.KeyAndValue;

public class MapUtils {
	public static Map<String,String> getMap(List<? extends KeyAndValue> list){
		Map<String,String> map = new LinkedHashMap<>();
		for(KeyAndValue keyAndValue : list){
			map.put(keyAndValue.getKey(), keyAndValue.getValue());
		}
		return map;
	}
}
