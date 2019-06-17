package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.Orders;

/**
 * 订单模型层的接口，用于定义订单模型的方法
 * @author 刘强
 *
 */
public interface OrdersService {

	/**
	 * 查询满足条件的订单信息（分页功能）
	 * @param orders   查询条件
	 * @param pageNumber  页数
	 * @return		    成功返回大于com.github.pagehelper.PageInfo<Goods>类型的实例，否则返回null
	 */
	public abstract PageInfo<Orders> selectOrders(Orders orders,Integer pageNumber);

	/**
	 * 修改指定编号的订单状态
	 * @param orders
	 * @return
	 */
	public abstract Integer updateOrdersStatus(Orders orders);
	
	/**
	 * 添加新的订单信息
	 * 
	 * @param users   新的订单信息
	 * @return   成功返回com.springcloud.entity.Users类型的实例，失败返回null
	 */
	public abstract Orders insert(Orders orders);
	
	/**
	 * 查询指定日期范围内的销售额
	 * @param orders  查询条件
	 * @return  成功返回java.util.List类型的实例，否则返回null
	 */
	public abstract  List<Orders> selectGroup(Orders orders);
	
}
