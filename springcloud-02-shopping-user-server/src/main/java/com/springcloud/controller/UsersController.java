package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.service.UsersService;
import com.springcloud.vo.ResultValue;

/**
 * ���Ʋ㣬������ͼ������ݲ�����ģ�Ͳ���Ӧ�ķ����������ݷ��ص���ͼ����
 * 
 * @author ��ǿ
 *
 */
@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	/**
	 * �жϵ�½�Ƿ�ɹ�
	 * 
	 * @param userId       �û����
	 * @param userpassword �û�Ȩ��
	 * @param jdictionId   Ȩ�ޱ��
	 * @return
	 */
	@RequestMapping(value="/login")
	public ResultValue login(@RequestParam("userId") Integer userId,@RequestParam("userPassword") String userpassword,@RequestParam("jdictionId") Integer jdictionId) {
		ResultValue rv = new ResultValue();
		try {
			Users login = this.usersService.login(userId, userpassword, jdictionId);
			if(login != null) {
				rv.setCode(0);
				
				Map<String, Object> map = new HashMap<>();
				map.put("loginUser", login);
				rv.setDataMap(map);
				
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		rv.setCode(1);
		rv.setMessage("��¼��Ϣ����ȷ�����������룡����");
		return rv;
		
	}
	
	//�û�¼��
	@RequestMapping(value = "/insert")
	public ResultValue insert(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Users insert = this.usersService.insert(users);
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
	
	//��ҳ��ѯ
	@RequestMapping(value = "/select")
	public ResultValue select(Users users,@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			Page<Users> page = this.usersService.select(users, pageNumber);
			//��÷�ҳ������
			List<Users> list = page.getContent();
			//�ж��Ƿ��ѯ��������
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				
				Map<String,Object> map = new HashMap<>();
				//����ҳ��������ӵ�Map��
				map.put("userList",list);
				
				PageUtils pageUtils = new PageUtils((int)page.getTotalElements());
				pageUtils.setPageNumber(pageNumber);
				//����ҳ��Ϣ��ӵ�Map��
				map.put("pageUtils", pageUtils);
				
				//��Map��ӵ�ResultValue������
				rv.setDataMap(map);
				
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
	
	
	/**
	 * �ڲ�ѯҳ���޸��û�״̬��Ϣ   
	 * @param userId     �û����
	 * @param userStatus �û�״̬
	 * @return           �ɹ������޸ĵ��û�״̬��ʧ�ܷ���1
	 */
	@RequestMapping(value="/updateStatus")
	public ResultValue updatStatus(@Param("userId") Integer userId,@Param("userStatus") Integer userStatus) {
		ResultValue rv = new ResultValue();
		
		try {
			Integer status = this.usersService.updateStatus(userId, userStatus);
			if(status > 0) {
				rv.setCode(0);
				rv.setMessage("�û�״̬�޸ĳɹ�������");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("�û�״̬�޸�ʧ�ܣ�����");
		return rv;
	}
	
	//����userId���û���Ϣ���µ��޸��û���Ϣģ̬���н����޸�
	@RequestMapping(value="/select/{userId}")
	public ResultValue selectById(@PathVariable("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		
		try {
			//����service�еķ���������ò�ѯ���
			Users users = this.usersService.selectById(userId);
			//����ɹ�
			if(users != null) {
			//���ý����״̬Ϊ0
				rv.setCode(0);
			//����Map���ϱ����ѯ���
				Map<String,Object> map = new HashMap<>();
			//����ѯ������浽Map������
				map.put("users", users);
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
	
	@RequestMapping(value="/update")
	public ResultValue update(Users users) {
		ResultValue rv =new ResultValue();
		
		try {
			//����service�еķ����޸��û���Ϣ��������޸��Ƿ�ɹ�
			Integer update = this.usersService.update(users);
			//����޸ĳɹ�
			if(update > 0) {
				//���ý����״̬Ϊ0
				rv.setCode(0);
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("�û���Ϣ�޸�ʧ�ܣ�����");
		return rv;
	}
	
	
	//�޸ĵ�¼�û�����Ϣ(ͷ��,���룬�ǳ�)
	@RequestMapping(value="/updateUserName")
	public ResultValue updateUserName(@Param("userId") Integer userId,@Param("userName") String userName) {
		ResultValue rv =new ResultValue();
		
		try {
			//����service�еķ����޸��û���Ϣ��������޸��Ƿ�ɹ�
			Integer updateName = this.usersService.updateUserName(userId, userName);
			//����޸ĳɹ�
			if(updateName > 0) {
				//���ý����״̬Ϊ0
				rv.setCode(0);
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("�û���Ϣ�޸�ʧ�ܣ�����");
		return rv;
	}
	
	@RequestMapping(value="/updateUserPassword")
	public ResultValue updateUserPassword(@Param("userId") Integer userId,@Param("userPassword") String userPassword) {
		ResultValue rv =new ResultValue();
		
		try {
			//����service�еķ����޸��û���Ϣ��������޸��Ƿ�ɹ�
			Integer updatePassword = this.usersService.updateUserPassword(userId, userPassword);
			//����޸ĳɹ�
			if(updatePassword > 0) {
				//���ý����״̬Ϊ0
				rv.setCode(0);
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("�û���Ϣ�޸�ʧ�ܣ�����");
		return rv;
	}
	
	@RequestMapping(value="/updateUserImage")
	public ResultValue updateUserImage(Users users) {
		ResultValue rv =new ResultValue();
		
		try {
			//����service�еķ����޸��û���Ϣ��������޸��Ƿ�ɹ�
			Integer updateImage = this.usersService.updateUserImage(users);
			//����޸ĳɹ�
			if(updateImage > 0) {
				//���ý����״̬Ϊ0
				rv.setCode(0);
				rv.setMessage("�û���Ϣ�޸ĳɹ�");
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("�û���Ϣ�޸�ʧ�ܣ�����");
		return rv;
	}
}
