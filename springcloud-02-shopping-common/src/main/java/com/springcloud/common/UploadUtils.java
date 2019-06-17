package com.springcloud.common;

import java.util.Date;

/**
 * �ļ��ϴ��Ĺ�����
 * 
 * @author ��ǿ 
 *
 */
public class UploadUtils {
	
	/**
	 * ������ļ������ƣ�������+4λ�������
	 * @return
	 */
	public static String getFileName() {
		String fileName = null;
		
		//���1-1000֮��������
		int num = (int) (Math.random()*1000+1);
		
		//�����������Ϊ4λ������1�������Ϊ0001��������ǰ������0����ȡ��������λ
		String temNum = "000" +num;
		temNum = temNum.substring(temNum.length()-4);
		
		//��ú�����
		Date date = new Date();
		fileName = date.getTime() + temNum;
		
		return fileName;
	}

	/**
	 * �����ļ�����չ��
	 * @param fileName �ļ���
	 * @return �ɹ������ļ���չ�������򷵻�null
	 * @return
	 */
	public static String getExendedName(String fileName) {
		if(fileName == null || fileName.length() == 0) {
			return null;
		} 
		//��ȡ�ļ��������.���һ�γ��ֵ�λ��
		int index = fileName.lastIndexOf(".");
		if(index == -1) {
			return null;
		}
		return fileName.substring(index);
		
	}

}
