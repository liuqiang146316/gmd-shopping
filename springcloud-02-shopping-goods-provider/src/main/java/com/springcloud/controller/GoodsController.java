package com.springcloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;
import com.springcloud.vo.ResultValue;

/**
 * 商品模块控制层
 * 
 * @author 刘强
 *
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value="/insert")
	public ResultValue insert(Goods goods) {
		ResultValue rv = new ResultValue();
		
		try {
			Integer insert = this.goodsService.insert(goods);
			if(insert != null) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("goods", goods);
				rv.setDataMap(map);
				
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品信息录入失败！！！");
		return rv;
	}
	
	@RequestMapping(value="/select")
	public ResultValue select(Goods goods,@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			//查询满足条件的商品信息
			PageInfo<Goods> pageInfo = this.goodsService.select(goods, pageNumber);
			//从分页信息中获得商品信息
			List<Goods> list = pageInfo.getList();
			//如果查询到了满足条件的商品信息
			if(list!=null && list.size() > 0) {
				rv.setCode(0);
				
				//创建一个map集合
				Map<String,Object> map = new HashMap<>();
				//将商品信息以指定的键(goodsList)存入到Map集合中
				map.put("goodsList", list);
				
				//设置信息显示的页数
				PageUtils pageUtils = new PageUtils(12,pageInfo.getPages() * 12);
				pageUtils.setPageNumber(pageNumber);
				//将分页信息以指定的名字存入到Map中
				map.put("pageUtils", pageUtils);
				//将数据传入到datamap中
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("没有找到满足条件的商品信息！！！");
		return rv;
	}
	
	
	/**
	 * 修改商品信息  （热卖，状态，商品图片）
	 * @param goods  修改的商品信息
	 * @return
	 */
	@RequestMapping(value="/updateById")
	public ResultValue updateById(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			Integer update = this.goodsService.updateGoodsById(goods);
			System.out.println(update);
			if(update > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品信息修改失败！！！");
		return rv;
		
	}
	
	/**
	 * 修改指定编号的商品信息 
	 * @param goods  修改的商品信息
	 * @return
	 */
	@RequestMapping(value="/update")
	public ResultValue update(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			Integer update = this.goodsService.update(goods);
			System.out.println(update);
			if(update > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品信息修改失败！！！");
		return rv;
	}
	
	/**
	 * 查询商品销量情况
	 * @return
	 */
	@RequestMapping(value="/selectGroup")
	public ResultValue selectGroup() {
		ResultValue rv = new ResultValue();
		try {
			List<Goods> list = this.goodsService.selectGroup();
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				//创建两个集合，用于保存柱状图x轴与y轴的数据
				List<String> x = new ArrayList<>();
				List<Integer> y = new ArrayList<>();
				//将查询结果中商品名称存入x集合，销量存入y集合
				for(Goods goods:list) {
					x.add(goods.getGoodsName());
					y.add(goods.getGoodsSum());
				}
				Map<String,Object> map = new HashMap<>();
				map.put("x", x);
				map.put("y", y);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}

}
