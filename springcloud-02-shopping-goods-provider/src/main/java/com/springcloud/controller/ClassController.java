package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;
import com.springcloud.service.ClassService;
import com.springcloud.vo.ResultValue;

/**
 * һ�������������Ŀ�����
 * 
 * @author ��ǿ
 *
 */
@RestController
@RequestMapping("class")
public class ClassController {

	@Autowired
	private ClassService classService;
	
	/**
	 * ��ѯһ��������е���Ϣ
	 * @return
	 */
	@RequestMapping(value="/selectAll")
	public ResultValue selectAll() {
		ResultValue rv = new ResultValue();
		
		try {
			//����Service��Ӧ�ķ�����ѯ��Ӧ����������Ϣ���������ѯ���
			List<Class1> list = this.classService.selectAllClass1();
			//�����ѯ�ɹ�
			if(list != null && list.size() > 0) {
				//���ý����״̬Ϊ0
				rv.setCode(0);
				//����Map����
				Map<String,Object> map = new HashMap<>();
				//����ѯ������뵽Map��
				map.put("class1List",list);
				//��Map���ϴ��뵽ResultValue���� 
				rv.setDataMap(map);
				//����ResultValue����
				return rv;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
		
	}
	
	/**
	 * ����һ������ѯ��Ӧ ���������Ϣ
	 * @return
	 */
	@RequestMapping(value="/selectById")
	public ResultValue selectById(@RequestParam("class1Id") Integer class1Id) {
		ResultValue rv = new ResultValue();
		
		try {
			//����Service��Ӧ�ķ�����ѯ��Ӧ����������Ϣ���������ѯ���
			List<Class2> list = this.classService.selectClass2ByClass1Id(class1Id);
			//�����ѯ�ɹ�
			//���������������ܵ�������Ϊ����Ϊ�ղ��ܵ��÷���
			if(list != null && list.size() > 0) {
				//���ý����״̬Ϊ0
				rv.setCode(0);
				//����Map����
				Map<String,Object> map = new HashMap<>();
				//����ѯ������뵽Map��
				map.put("class2List",list);
				//��Map���ϴ��뵽ResultValue���� 
				rv.setDataMap(map);
				//����ResultValue����
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
		
	}
}
