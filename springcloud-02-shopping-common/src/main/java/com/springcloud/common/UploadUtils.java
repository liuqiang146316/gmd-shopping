package com.springcloud.common;

import java.util.Date;

/**
 * 文件上传的工具类
 * 
 * @author 刘强 
 *
 */
public class UploadUtils {
	
	/**
	 * 获得新文件的名称（毫秒数+4位随机数）
	 * @return
	 */
	public static String getFileName() {
		String fileName = null;
		
		//获得1-1000之间的随机数
		int num = (int) (Math.random()*1000+1);
		
		//将随机数设置为4位，如获得1，将其变为0001。在其结果前加三个0，再取倒数后四位
		String temNum = "000" +num;
		temNum = temNum.substring(temNum.length()-4);
		
		//获得毫秒数
		Date date = new Date();
		fileName = date.getTime() + temNum;
		
		return fileName;
	}

	/**
	 * 返回文件的扩展名
	 * @param fileName 文件名
	 * @return 成功返回文件扩展名，否则返回null
	 * @return
	 */
	public static String getExendedName(String fileName) {
		if(fileName == null || fileName.length() == 0) {
			return null;
		} 
		//获取文件名中最后.最后一次出现的位置
		int index = fileName.lastIndexOf(".");
		if(index == -1) {
			return null;
		}
		return fileName.substring(index);
		
	}

}
