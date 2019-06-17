package com.springcloud.dao;

import com.springcloud.entity.OrderDateail;
import java.util.List;

public interface OrderDateailMapper {
    int deleteByPrimaryKey(Integer orderDetailId);

    int insert(OrderDateail record);

    OrderDateail selectByPrimaryKey(Integer orderDetailId);

    List<OrderDateail> selectAll();

    int updateByPrimaryKey(OrderDateail record);
    
    /**
     * ��ѯָ����ŵĶ�����ϸ��Ϣ
     * @param orderId   �������
     * @return  
     */
    public abstract List<OrderDateail> selectByOrderId(Integer orderId);
}