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
	 * ������ϸ���
	 */
	private Integer orderDetailId;

	/**
	 * �������
	 */
    private Integer orderId;

    /**
	 * ��Ʒ���
	 */
    private Integer goodId;

    /**
	 * ��Ʒ����
	 */
    private String goodName;

    /**
	 * �ɽ���
	 */
    private Double transactionPrice;

    /**
	 * �ɽ�����
	 */
    private Integer transactionCount;

    
}