package com.springcloud.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7720953201494230240L;

	/**
	 * 订单编号
	 */
	private Integer orderId;

	/**
	 * 当前订单的用户信息
	 */
	private Users user;

	/**
	 * 收货人姓名
	 */
	private String consigneeName;

	/**
	 * 收货人电话
	 */
	private String consigneeNumber;

	/**
	 * 收货人地址
	 */
	private String consigneeSite;

	/**
	 * 订单总额
	 */
	private Double orderAmount;

	/**
	 * 下单时间，默认为当前时间
	 */

	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date orderTime;

	/**
	 * 订单状态 0为待付款 1为待发货 2为待收货 3为已付款 4为已退货
	 */
	private Integer orderStatus;

	/**
	 * 起始的订单日期
	 */
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date orderDateMin;

	/**
	 * 结束的订单日期
	 */
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date orderDateMax;

	/**
	 * 查询条件，起始年月
	 */
	private String startMonth;

	/**
	 * 查询条件，终止年月
	 */
	private String endMonth;
	
	/**
	 * 统计结果的年月日
	 */
	private String orderMonth;
	
	/**
	 * 统计结果的销售额
	 */
	private Double orderPrice;

}