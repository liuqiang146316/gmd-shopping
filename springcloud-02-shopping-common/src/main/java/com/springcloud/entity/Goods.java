package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
	/**
	 * 商品编号
	 */
    private Integer goodsId;

    /**
	 * 商品名称
	 */
    private String goodsName;
    
    /**
	 * 商品价格
	 */
    private Double goodsPrices;
    
    /**
	 * 商品折扣价格
	 */
    private Double goodsDiscount;
    
    /**
	 * 商品状态  0为预售，1热卖中，2为已下架
	 */
    private Integer goodsStatus;
    
    /**
	 * 商品数量
	 */
    private Integer goodsCount;
    
    /**
	 * 商品是否为新品   0是新品，1不是新品
	 */
    private Integer goodsIsNew;
    
    /**
	 * 商品是否热卖     0是热卖，1不是热卖
	 */
    private Integer goodsIsHot;
    
    /**
	 * 商品等级      0为1星，1为2星，3为4星，4为5星
	 */
    private Integer goodsLevel;
    
    /**
	 * 商品简介
	 */
    private String goodsBrief;
    
    /**
	 * 商品详情
	 */
    private String goodsDetails;
    
    /**
	 * 商品图片
	 */
    private String goodPhoto;
    
    /**
	 * 类别二编号
	 */
    private Integer class2Id;
    
    /**
	 * 查询条件，商品价格下限
	 */
    private Double goodsPriceMin;
    
    /**
	 * 查询条件，商品价格上限
	 */
    private Double goodsPriceMax;
    
    /**
	 * 查询条件，一级类型别编号
	 */
    private Integer class1Id;

    /**
	 * 商品成交数量，用于查询商品销售统计
	 */
    private Integer goodsSum;

}