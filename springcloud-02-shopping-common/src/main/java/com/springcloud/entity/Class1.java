package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CLASS1对应的实体类，用于表中一行一级类别信息
 * 
 * @author 刘强
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class1 {
	
	/**
	 * 一级类别编号
	 */
    private Integer class1Id;
    
    /**
	 * 一级类别名称
	 */
    private String class1Name;
    
    /**
	 * 序号
	 */
    private Integer calss1Num;
    
    /**
	 * 一级类别备注
	 */
    private String remake;

    
}