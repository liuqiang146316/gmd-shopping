package com.springcloud.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrdersMapper;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;

@Service
public  class OrderServiceImpl implements OrdersService {
	
	@Autowired
	private OrdersMapper ordersMapper;

	
	@Override
	public PageInfo<Orders> selectOrders(Orders orders, Integer pageNumber) {
		if(orders.getUser()!=null) {
			//�û������˼�%
			orders.getUser().setUserName("%" + orders.getUser().getUserName() + "%");
		}
		//����ÿҳ����Ϣ
		PageHelper.startPage(pageNumber + 1,PageUtils.PAGE_ROW_COUNT); 
		//��ѯ���������Ķ�����Ϣ ��list����
		List<Orders> list = this.ordersMapper.selectOrders(orders);
		//���ط�ҳ��Ϣ
		return new PageInfo<>(list);
	}


	/**
	 * ����µĶ�����Ϣ
	 */
	@Transactional
	@Override
	public Orders insert(Orders orders) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * �޸Ķ���״̬
	 */
	@Override
	public Integer updateOrdersStatus(Orders orders) {
		
		return this.ordersMapper.updataOrderStatus(orders);
	}


	/**
	 * ��ѯָ�����ڷ�Χ�ڵ����۶�
	 */
	@Override
	public List<Orders> selectGroup(Orders orders) {
		return this.ordersMapper.selectGroup(orders);
	}

}


