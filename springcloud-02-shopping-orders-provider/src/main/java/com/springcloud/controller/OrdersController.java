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
 * ����ģ����Ʋ�
 * 
 * @author ��ǿ
 *
 */
@RestController
@RequestMapping(value = "orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	/**
	 * ��ѯ���������Ķ�����Ϣ
	 * @param orders    ��ѯ����
	 * @param pageNumber   ҳ��
	 * @return  
	 */
	@RequestMapping(value = "/selectOrders")
	public ResultValue selectOrders(Orders orders, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();

		try {
			// ��ѯ������������Ʒ��Ϣ
			PageInfo<Orders> pageInfo = this.ordersService.selectOrders(orders, pageNumber);
			// �ӷ�ҳ��Ϣ�ػ�ö�����Ϣ
			List<Orders> list = pageInfo.getList();
			// �����ѯ����������������Ʒ��Ϣ
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				// ����һ��map����
				Map<String, Object> map = new HashMap<>();
				// ����Ʒ��Ϣ��ָ���ļ�(goodsList)���뵽Map������
				map.put("ordersList", list);
				// ������Ϣ��ʾ��ҳ��
				PageUtils pageUtils = new PageUtils(pageInfo.getPages() * PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);

				// ����ҳ��Ϣ��ָ�������ִ��뵽Map��
				map.put("pageUtils", pageUtils);
				// �����ݴ��뵽datamap��
				rv.setDataMap(map);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("û�в�ѯ�����������Ķ�����Ϣ������");
		return rv;
	}
	
		@RequestMapping(value="/selectGroup")
		public ResultValue selectGroup(Orders orders) {
			ResultValue rv = new ResultValue();
			try {
				List<Orders> list = this.ordersService.selectGroup(orders);
				//�ж��Ƿ��ѯ����Ϣ
				rv.setCode(0);
				//�����������ϣ����ڱ�����״ͼx����y�������
				List<String> x = new ArrayList<>();
				List<Double> y = new ArrayList<>();
				//����ѯ�������Ʒ���ƴ���x���ϣ���������y����
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
	    //�޸Ķ���״̬
		@RequestMapping(value = "/updateOrdersStatus")
		public ResultValue updateOrdersStatus(Orders orders) {
			ResultValue rv = new ResultValue();
			
			try {
				//����Service��ȡ������״̬
				Integer updateOrdersStatus = this.ordersService.updateOrdersStatus(orders);
				//�ж��Ƿ��ö���״̬
				if(updateOrdersStatus > 0) {
					rv.setCode(0);
					rv.setMessage("�޸Ķ���״̬�ɹ�");
					return rv;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			rv.setCode(1);
			rv.setMessage("�޸Ķ���״̬ʧ�ܣ�����");
			return rv;
			
		}
	

	    //����¼��
		@RequestMapping(value = "/insert")
		public ResultValue insert(Orders orders) {
			ResultValue rv = new ResultValue();
			try {
				Orders insert = this.ordersService.insert(orders);
				
				if(insert != null) {
					rv.setCode(0);
					rv.setMessage("�û�¼��ɹ�������");
					return rv;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			//¼��ʧ��
			rv.setCode(1);
			rv.setMessage("�û�¼��ʧ�ܣ�����");
			return rv;
		}
}
