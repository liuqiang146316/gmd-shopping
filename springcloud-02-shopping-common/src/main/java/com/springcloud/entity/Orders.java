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
	 * �������
	 */
	private Integer orderId;

	/**
	 * ��ǰ�������û���Ϣ
	 */
	private Users user;

	/**
	 * �ջ�������
	 */
	private String consigneeName;

	/**
	 * �ջ��˵绰
	 */
	private String consigneeNumber;

	/**
	 * �ջ��˵�ַ
	 */
	private String consigneeSite;

	/**
	 * �����ܶ�
	 */
	private Double orderAmount;

	/**
	 * �µ�ʱ�䣬Ĭ��Ϊ��ǰʱ��
	 */

	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date orderTime;

	/**
	 * ����״̬ 0Ϊ������ 1Ϊ������ 2Ϊ���ջ� 3Ϊ�Ѹ��� 4Ϊ���˻�
	 */
	private Integer orderStatus;

	/**
	 * ��ʼ�Ķ�������
	 */
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date orderDateMin;

	/**
	 * �����Ķ�������
	 */
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date orderDateMax;

	/**
	 * ��ѯ��������ʼ����
	 */
	private String startMonth;

	/**
	 * ��ѯ��������ֹ����
	 */
	private String endMonth;
	
	/**
	 * ͳ�ƽ����������
	 */
	private String orderMonth;
	
	/**
	 * ͳ�ƽ�������۶�
	 */
	private Double orderPrice;

}