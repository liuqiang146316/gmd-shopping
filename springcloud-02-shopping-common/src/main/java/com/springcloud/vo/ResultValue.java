package com.springcloud.vo;

import java.util.Map;

import lombok.Data;

/*
 * ���屾��Ŀ�����е�Controller���صĽ������
 * @author ��ǿ
 * 
 */
@Data
public class ResultValue implements java.io.Serializable{

	
	private static final long serialVersionUID = -7333058567132357663L;

	/**
	 * ���õ�ǰ���صĽ��״̬��0��ʾ�ɹ���1��ʾʧ��
	 */
	private Integer code;
	
	/**
	 * ���÷��ص���Ϣ
	 */
	private String message;
	
	/**
	 * ���÷��ص�����
	 */
	private Map<String,Object> dataMap;
	
}
