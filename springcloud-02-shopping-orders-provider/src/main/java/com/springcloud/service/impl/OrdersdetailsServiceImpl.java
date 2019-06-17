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
 * 订单明细模块模型层的实现类，用于实现订单明细模块的方法
 * @author 刘强
 *
 */
@Service
public class OrdersdetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDateailMapper OrderDateailMapper;
	
	@Override
	public PageInfo<OrderDateail> selectByOrderId(Integer orderId,Integer pageNumber) {
		
		
		
		//设置分页信息
		PageHelper.startPage(pageNumber + 1,5);
		//查询订单编号的订单明细信息
		List<OrderDateail> list = this.OrderDateailMapper.selectByOrderId(orderId);
		return new PageInfo<>(list);
	}

}
