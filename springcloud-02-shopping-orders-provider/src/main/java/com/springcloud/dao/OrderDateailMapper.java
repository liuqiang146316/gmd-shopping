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
     * 查询指定编号的订单明细信息
     * @param orderId   订单编号
     * @return  
     */
    public abstract List<OrderDateail> selectByOrderId(Integer orderId);
}