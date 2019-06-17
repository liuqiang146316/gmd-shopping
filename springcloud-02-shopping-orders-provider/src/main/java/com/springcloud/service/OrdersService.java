package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.Orders;

/**
 * ����ģ�Ͳ�Ľӿڣ����ڶ��嶩��ģ�͵ķ���
 * @author ��ǿ
 *
 */
public interface OrdersService {

	/**
	 * ��ѯ���������Ķ�����Ϣ����ҳ���ܣ�
	 * @param orders   ��ѯ����
	 * @param pageNumber  ҳ��
	 * @return		    �ɹ����ش���com.github.pagehelper.PageInfo<Goods>���͵�ʵ�������򷵻�null
	 */
	public abstract PageInfo<Orders> selectOrders(Orders orders,Integer pageNumber);

	/**
	 * �޸�ָ����ŵĶ���״̬
	 * @param orders
	 * @return
	 */
	public abstract Integer updateOrdersStatus(Orders orders);
	
	/**
	 * ����µĶ�����Ϣ
	 * 
	 * @param users   �µĶ�����Ϣ
	 * @return   �ɹ�����com.springcloud.entity.Users���͵�ʵ����ʧ�ܷ���null
	 */
	public abstract Orders insert(Orders orders);
	
	/**
	 * ��ѯָ�����ڷ�Χ�ڵ����۶�
	 * @param orders  ��ѯ����
	 * @return  �ɹ�����java.util.List���͵�ʵ�������򷵻�null
	 */
	public abstract  List<Orders> selectGroup(Orders orders);
	
}
