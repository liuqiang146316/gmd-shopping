package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDateail implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4732809856497738081L;

	/**
	 * 订单明细编号
	 */
	private Integer orderDetailId;

	/**
	 * 订单编号
	 */
    private Integer orderId;

    /**
	 * 商品编号
	 */
    private Integer goodId;

    /**
	 * 商品名称
	 */
    private String goodName;

    /**
	 * 成交价
	 */
    private Double transactionPrice;

    /**
	 * 成交数量
	 */
    private Integer transactionCount;

    
}