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
			//用户名两端加%
			orders.getUser().setUserName("%" + orders.getUser().getUserName() + "%");
		}
		//设置每页的信息
		PageHelper.startPage(pageNumber + 1,PageUtils.PAGE_ROW_COUNT); 
		//查询满足条件的订单信息 用list接受
		List<Orders> list = this.ordersMapper.selectOrders(orders);
		//返回分页信息
		return new PageInfo<>(list);
	}


	/**
	 * 添加新的订单信息
	 */
	@Transactional
	@Override
	public Orders insert(Orders orders) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 修改订单状态
	 */
	@Override
	public Integer updateOrdersStatus(Orders orders) {
		
		return this.ordersMapper.updataOrderStatus(orders);
	}


	/**
	 * 查询指定日期范围内的销售额
	 */
	@Override
	public List<Orders> selectGroup(Orders orders) {
		return this.ordersMapper.selectGroup(orders);
	}

}


