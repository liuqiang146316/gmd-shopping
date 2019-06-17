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
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;
import com.springcloud.vo.ResultValue;

/**
 * 订单模块控制层
 * 
 * @author 刘强
 *
 */
@RestController
@RequestMapping(value = "orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	/**
	 * 查询满足条件的订单信息
	 * @param orders    查询条件
	 * @param pageNumber   页数
	 * @return  
	 */
	@RequestMapping(value = "/selectOrders")
	public ResultValue selectOrders(Orders orders, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();

		try {
			// 查询满足条件的商品信息
			PageInfo<Orders> pageInfo = this.ordersService.selectOrders(orders, pageNumber);
			// 从分页信息重获得订单信息
			List<Orders> list = pageInfo.getList();
			// 如果查询到了满足条件的商品信息
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				// 创建一个map集合
				Map<String, Object> map = new HashMap<>();
				// 将商品信息以指定的键(goodsList)存入到Map集合中
				map.put("ordersList", list);
				// 设置信息显示的页数
				PageUtils pageUtils = new PageUtils(pageInfo.getPages() * PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);

				// 将分页信息以指定的名字存入到Map中
				map.put("pageUtils", pageUtils);
				// 将数据传入到datamap中
				rv.setDataMap(map);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("没有查询到满足条件的订单信息！！！");
		return rv;
	}
	
		@RequestMapping(value="/selectGroup")
		public ResultValue selectGroup(Orders orders) {
			ResultValue rv = new ResultValue();
			try {
				List<Orders> list = this.ordersService.selectGroup(orders);
				//判断是否查询到信息
				rv.setCode(0);
				//创建两个集合，用于保存柱状图x轴与y轴的数据
				List<String> x = new ArrayList<>();
				List<Double> y = new ArrayList<>();
				//将查询结果中商品名称存入x集合，销量存入y集合
				for(Orders user:list) {
					x.add(user.getOrderMonth());
					y.add(user.getOrderPrice());
				}
				Map<String,Object> map = new HashMap<>();
				map.put("x", x);
				map.put("y", y);
				rv.setDataMap(map);
				return rv;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			rv.setCode(1);
			return rv;
		}
	    //修改订单状态
		@RequestMapping(value = "/updateOrdersStatus")
		public ResultValue updateOrdersStatus(Orders orders) {
			ResultValue rv = new ResultValue();
			
			try {
				//调用Service获取到订单状态
				Integer updateOrdersStatus = this.ordersService.updateOrdersStatus(orders);
				//判断是否获得订单状态
				if(updateOrdersStatus > 0) {
					rv.setCode(0);
					rv.setMessage("修改订单状态成功");
					return rv;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			rv.setCode(1);
			rv.setMessage("修改订单状态失败！！！");
			return rv;
			
		}
	

	    //订单录入
		@RequestMapping(value = "/insert")
		public ResultValue insert(Orders orders) {
			ResultValue rv = new ResultValue();
			try {
				Orders insert = this.ordersService.insert(orders);
				
				if(insert != null) {
					rv.setCode(0);
					rv.setMessage("用户录入成功！！！");
					return rv;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			//录入失败
			rv.setCode(1);
			rv.setMessage("用户录入失败！！！");
			return rv;
		}
}
