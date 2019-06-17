package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.dao.OrderDateailMapper;
import com.springcloud.entity.OrderDateail;
import com.springcloud.service.OrderDetailsService;

/**
 * ������ϸģ��ģ�Ͳ��ʵ���࣬����ʵ�ֶ�����ϸģ��ķ���
 * @author ��ǿ
 *
 */
@Service
public class OrdersdetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDateailMapper OrderDateailMapper;
	
	@Override
	public PageInfo<OrderDateail> selectByOrderId(Integer orderId,Integer pageNumber) {
		
		
		
		//���÷�ҳ��Ϣ
		PageHelper.startPage(pageNumber + 1,5);
		//��ѯ������ŵĶ�����ϸ��Ϣ
		List<OrderDateail> list = this.OrderDateailMapper.selectByOrderId(orderId);
		return new PageInfo<>(list);
	}

}
