package com.ydc.excel_to_db.util.common;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScanIsFileExist {
    private static final Logger log =  LoggerFactory.getLogger(ScanIsFileExist.class);  

    /**
     * 判断多级路径是否存在，不存在就创建 
     *  
     * @param filePath 支持带文件名的Path：如：D:\news\2014\12\abc.text，和不带文件名的Path：如：D:\news\2014\12
     */
	public static boolean isExistDir(String filePath) {
		boolean isexistDir = false;
		String paths[] = { "" };
		// 切割路径
		try {
			String tempPath = new File(filePath).getCanonicalPath();// File对象转换为标准路径并进行切割，有两种windows和linux
			paths = tempPath.split("\\\\");// windows
			if (paths.length == 1) {
				paths = tempPath.split("/");
			} // linux
		} catch (IOException e) {
			System.out.println("切割路径错误");
			e.printStackTrace();
		}
		// 判断是否有后缀
		boolean hasType = false;
		if (paths.length > 0) {
			String tempPath = paths[paths.length - 1];
			if (tempPath.length() > 0) {
				if (tempPath.indexOf(".") > 0) {
					hasType = true;
					isexistDir = true;
					log.info("{} the file come from DB", tempPath);
				}
			}
		}
		// 创建文件夹
		String dir = paths[0];
		for (int i = 0; i < paths.length - (hasType ? 2 : 1); i++) {// 注意此处循环的长度，有后缀的就是文件路径，没有则文件夹路径
			try {
				dir = dir + "/" + paths[i + 1];// 采用linux下的标准写法进行拼接，由于windows可以识别这样的路径，所以这里采用警容的写法
				File dirFile = new File(dir);
				if (dirFile.exists()) {
					isexistDir = true;
					// dirFile.mkdir();
					log.info("当前目录: {} ", dirFile.getCanonicalFile());
				}
			} catch (Exception e) {
				log.info("文件夹创建发生异常");
				e.printStackTrace();
			}
		}
		return isexistDir;
	}
    
	// 判断文件夹是否存在
	public static boolean judeDirExists(File file) {
		boolean isdirectory = false;
		if (file.exists()) {
			if (file.isDirectory()) {
				log.info("{} directory exists ",file);
				isdirectory = true;
			} else {
				log.info("{} : the directory file not exists, can not create dir... ",file);
				isdirectory = false;
			}
		} else {
			log.info(" {} : the file not exists, can not create file... ",file);
			//file.mkdir();
			isdirectory = false;
		}
		return isdirectory;
	}

	
	
	
}