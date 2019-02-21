package com.ydc.excel_to_db.util.common;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheCall{
	 public static <K,V> Cache<K , V> callableCached() throws Exception {
         Cache<K, V> cache = CacheBuilder
         .newBuilder()
         .maximumSize(100)      //大小设置（long）
         .expireAfterAccess(12, TimeUnit.HOURS) //写入后存活时间
         .build();
         return cache;
	 }
	 
	 public static Cache<String, Map<String,String>> cacheFormCallable = null;
	 
	 public static Cache<String, Map<String,String>> getCache(){
		 if(cacheFormCallable == null){
			 try {
				cacheFormCallable = callableCached();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 return cacheFormCallable;
	 }
}
