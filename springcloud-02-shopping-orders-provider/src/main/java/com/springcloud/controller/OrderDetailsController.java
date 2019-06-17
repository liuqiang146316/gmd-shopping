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
 * ������ϸģ��Ŀ��Ʋ�
 * @author ��ǿ
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
			//����service�еķ���������ò�ѯ���
			PageInfo<OrderDateail> pageInfo = this.ordersDetailsService.selectByOrderId(orderId, pageNumber);
			List<OrderDateail> list = pageInfo.getList();
			//����ɹ�
			if(list != null && list.size() > 0) {
			//���ý����״̬Ϊ0
				rv.setCode(0);
			//����Map���ϱ����ѯ���
				Map<String,Object> map = new HashMap<>();
			//����ѯ������浽Map������
				map.put("orderDetailsList", list);
			
				PageUtils pageUtils = new PageUtils(5,pageInfo.getPages()*5);
				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);
			//��Map�����ӵ�ResultValue����
				rv.setDataMap(map);
			//����ResultValue����
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("����û���Ϣʧ�ܣ�����");
		return rv;
	}
	
}
