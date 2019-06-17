package com.springcloud.dao;

import com.springcloud.entity.Orders;
import java.util.List;

public interface OrdersMapper {
	int deleteByPrimaryKey(Integer orderId);

	int insert(Orders record);

	Orders selectByPrimaryKey(Integer orderId);

	List<Orders> selectAll();

	int updateByPrimaryKey(Orders record);

	/**
	 * ��ѯORDERS�������������Ķ�����Ϣ
	 * 
	 * @param orders ��ѯ����
	 * @return �ɹ�����java.util.LIst���͵�ʵ�������򷵻�null
	 */
	public abstract List<Orders> selectOrders(Orders orders);

	/**
	 * �޸�ORDERS����ָ��ORDER_ID�Ķ�����Ϣ
	 * 
	 * @param orders �޸ĵĶ�����Ϣ
	 * @return �ɹ����ش���0�����������򷵻�null
	 */
	public abstract Integer updataOrderStatus(Orders orders);

	/**
	 * ��ѯָ�����ڷ�Χ�ڵ����۶�
	 * 
	 * @param orders
	 * @return  �ɹ�����java.util.LIst���͵�ʵ�������򷵻�null
	 */
	public abstract List<Orders> selectGroup(Orders orders);
}