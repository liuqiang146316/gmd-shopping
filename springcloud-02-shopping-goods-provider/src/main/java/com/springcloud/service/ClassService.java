package com.springcloud.service;

import java.util.List;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;

/**
 * ���ڶ���һ�������������ģ��ķ���
 * 
 * @author ��ǿ
 *
 */
public interface ClassService {

	/**
	 * ��ѯ����һ��������Ϣ
	 * 
	 * @return   �ɹ�����java.util.List���͵�ʵ�壬���򷵻�null
	 */
	public abstract List<Class1> selectAllClass1();
	
	/**
	 * ����һ������Ų�ѯ��Ӧ�Ķ��������Ϣ
	 * 
	 * @return   �ɹ�����java.util.List���͵�ʵ�壬���򷵻�null
	 */
	public abstract List<Class2> selectClass2ByClass1Id(Integer class1Id);
}
