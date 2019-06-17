package com.springcloud.service;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.OrderDateail;

/**
 * 订单明细模块的模型层，用于定义订单明细模块的方法
 * @author 刘强
 *
 */
public interface OrderDetailsService {

	/**
	 * 查询指定订单编号的订单明细信息（分页功能）
	 * @param orderId
	 * @return
	 */
	public abstract PageInfo<OrderDateail> selectByOrderId(Integer orderId,Integer pageNumber);
}
