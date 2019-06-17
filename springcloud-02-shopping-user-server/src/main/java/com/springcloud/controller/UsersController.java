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
 * 控制层，接受视图层的数据并调用模型层相应的方法，将数据返回到视图层中
 * 
 * @author 刘强
 *
 */
@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	/**
	 * 判断登陆是否成功
	 * 
	 * @param userId       用户编号
	 * @param userpassword 用户权限
	 * @param jdictionId   权限编号
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
		rv.setMessage("登录信息不正确，请重新输入！！！");
		return rv;
		
	}
	
	//用户录入
	@RequestMapping(value = "/insert")
	public ResultValue insert(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Users insert = this.usersService.insert(users);
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
	
	//分页查询
	@RequestMapping(value = "/select")
	public ResultValue select(Users users,@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			Page<Users> page = this.usersService.select(users, pageNumber);
			//获得分页的数据
			List<Users> list = page.getContent();
			//判断是否查询到了数据
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				
				Map<String,Object> map = new HashMap<>();
				//将分页的数据添加到Map中
				map.put("userList",list);
				
				PageUtils pageUtils = new PageUtils((int)page.getTotalElements());
				pageUtils.setPageNumber(pageNumber);
				//将分页信息添加到Map中
				map.put("pageUtils", pageUtils);
				
				//将Map添加到ResultValue对象中
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
	 * 在查询页面修改用户状态信息   
	 * @param userId     用户编号
	 * @param userStatus 用户状态
	 * @return           成功返回修改的用户状态，失败返回1
	 */
	@RequestMapping(value="/updateStatus")
	public ResultValue updatStatus(@Param("userId") Integer userId,@Param("userStatus") Integer userStatus) {
		ResultValue rv = new ResultValue();
		
		try {
			Integer status = this.usersService.updateStatus(userId, userStatus);
			if(status > 0) {
				rv.setCode(0);
				rv.setMessage("用户状态修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户状态修改失败！！！");
		return rv;
	}
	
	//根据userId将用户信息更新到修改用户信息模态框中进行修改
	@RequestMapping(value="/select/{userId}")
	public ResultValue selectById(@PathVariable("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		
		try {
			//调用service中的方法，并获得查询结果
			Users users = this.usersService.selectById(userId);
			//如果成功
			if(users != null) {
			//设置结果的状态为0
				rv.setCode(0);
			//创建Map集合保存查询结果
				Map<String,Object> map = new HashMap<>();
			//将查询结果保存到Map集合中
				map.put("users", users);
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
	
	@RequestMapping(value="/update")
	public ResultValue update(Users users) {
		ResultValue rv =new ResultValue();
		
		try {
			//调用service中的方法修改用户信息，并获得修改是否成功
			Integer update = this.usersService.update(users);
			//如果修改成功
			if(update > 0) {
				//设置结果的状态为0
				rv.setCode(0);
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息修改失败！！！");
		return rv;
	}
	
	
	//修改登录用户的信息(头像,密码，昵称)
	@RequestMapping(value="/updateUserName")
	public ResultValue updateUserName(@Param("userId") Integer userId,@Param("userName") String userName) {
		ResultValue rv =new ResultValue();
		
		try {
			//调用service中的方法修改用户信息，并获得修改是否成功
			Integer updateName = this.usersService.updateUserName(userId, userName);
			//如果修改成功
			if(updateName > 0) {
				//设置结果的状态为0
				rv.setCode(0);
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息修改失败！！！");
		return rv;
	}
	
	@RequestMapping(value="/updateUserPassword")
	public ResultValue updateUserPassword(@Param("userId") Integer userId,@Param("userPassword") String userPassword) {
		ResultValue rv =new ResultValue();
		
		try {
			//调用service中的方法修改用户信息，并获得修改是否成功
			Integer updatePassword = this.usersService.updateUserPassword(userId, userPassword);
			//如果修改成功
			if(updatePassword > 0) {
				//设置结果的状态为0
				rv.setCode(0);
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息修改失败！！！");
		return rv;
	}
	
	@RequestMapping(value="/updateUserImage")
	public ResultValue updateUserImage(Users users) {
		ResultValue rv =new ResultValue();
		
		try {
			//调用service中的方法修改用户信息，并获得修改是否成功
			Integer updateImage = this.usersService.updateUserImage(users);
			//如果修改成功
			if(updateImage > 0) {
				//设置结果的状态为0
				rv.setCode(0);
				rv.setMessage("用户信息修改成功");
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息修改失败！！！");
		return rv;
	}
}
