package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
	/**
	 * ��Ʒ���
	 */
    private Integer goodsId;

    /**
	 * ��Ʒ����
	 */
    private String goodsName;
    
    /**
	 * ��Ʒ�۸�
	 */
    private Double goodsPrices;
    
    /**
	 * ��Ʒ�ۿۼ۸�
	 */
    private Double goodsDiscount;
    
    /**
	 * ��Ʒ״̬  0ΪԤ�ۣ�1�����У�2Ϊ���¼�
	 */
    private Integer goodsStatus;
    
    /**
	 * ��Ʒ����
	 */
    private Integer goodsCount;
    
    /**
	 * ��Ʒ�Ƿ�Ϊ��Ʒ   0����Ʒ��1������Ʒ
	 */
    private Integer goodsIsNew;
    
    /**
	 * ��Ʒ�Ƿ�����     0��������1��������
	 */
    private Integer goodsIsHot;
    
    /**
	 * ��Ʒ�ȼ�      0Ϊ1�ǣ�1Ϊ2�ǣ�3Ϊ4�ǣ�4Ϊ5��
	 */
    private Integer goodsLevel;
    
    /**
	 * ��Ʒ���
	 */
    private String goodsBrief;
    
    /**
	 * ��Ʒ����
	 */
    private String goodsDetails;
    
    /**
	 * ��ƷͼƬ
	 */
    private String goodPhoto;
    
    /**
	 * �������
	 */
    private Integer class2Id;
    
    /**
	 * ��ѯ��������Ʒ�۸�����
	 */
    private Double goodsPriceMin;
    
    /**
	 * ��ѯ��������Ʒ�۸�����
	 */
    private Double goodsPriceMax;
    
    /**
	 * ��ѯ������һ�����ͱ���
	 */
    private Integer class1Id;

    /**
	 * ��Ʒ�ɽ����������ڲ�ѯ��Ʒ����ͳ��
	 */
    private Integer goodsSum;

}