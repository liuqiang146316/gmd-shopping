package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.OrderDateail;
import com.springcloud.service.OrderDetailsService;
import com.springcloud.vo.ResultValue;

/**
 * 订单明细模块的控制层
 * @author 刘强
 *
 */
@RestController
@RequestMapping("orderDetails")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService ordersDetailsService;
	
	@RequestMapping(value="/selectByOrderId")
	public ResultValue selectByOrderId(@RequestParam("orderId") Integer orderId,@RequestParam("pageNumber") Integer pageNumber) {
       ResultValue rv = new ResultValue();
		
		try {
			//调用service中的方法，并获得查询结果
			PageInfo<OrderDateail> pageInfo = this.ordersDetailsService.selectByOrderId(orderId, pageNumber);
			List<OrderDateail> list = pageInfo.getList();
			//如果成功
			if(list != null && list.size() > 0) {
			//设置结果的状态为0
				rv.setCode(0);
			//创建Map集合保存查询结果
				Map<String,Object> map = new HashMap<>();
			//将查询结果保存到Map集合中
				map.put("orderDetailsList", list);
			
				PageUtils pageUtils = new PageUtils(5,pageInfo.getPages()*5);
				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);
			//将Map结合添加到ResultValue对象
				rv.setDataMap(map);
			//返回ResultValue对象
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("获得用户信息失败！！！");
		return rv;
	}
	
}
