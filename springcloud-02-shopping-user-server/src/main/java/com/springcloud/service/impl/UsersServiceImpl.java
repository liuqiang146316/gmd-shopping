package com.springcloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.repository.UsersRepository;
import com.springcloud.service.UsersService;

/**
 * 模型层的实现类
 * 
 * @author 刘强
 * @param <DataBaseConnection>
 *
 */
@Service
public class UsersServiceImpl<DataBaseConnection> implements UsersService {
	
	@Autowired
	private UsersRepository UsersRepository;
	
	

	@Override
	public  Users login(Integer userId,String userPassword,Integer jdictionId) {
		return this.UsersRepository.login(userId, userPassword, jdictionId);
	}

	/**
	 * 添加用户信息
	 */
	@Override
	public Users insert(Users users) {
		
		return this.UsersRepository.save(users);
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public Page<Users> select(Users users,Integer pageNumber){
		
		//根据查询条件，创建动态条件
		@SuppressWarnings("serial")
		Specification<Users> specifatio = new Specification<Users>() {
			
			@Transactional
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				//创建List集合，用于保存所有的where条件
				List<Predicate> whereList = new ArrayList<>();
				
				//根据Users中的查询数据，动态创建查询条件
				if(users.getUserName()!=null && !users.getUserName().trim().equals("")) {
					whereList.add(criteriaBuilder.like(root.get("userName"), "%" + users.getUserName() + "%"));
				}
				if(users.getUserStatus()!=-1) {
					whereList.add(criteriaBuilder.equal(root.get("userStatus"), users.getUserStatus()));
				}
				//将所有的条件以关系连接在一起，并返回
				return criteriaBuilder.and(whereList.toArray(new Predicate[whereList.size()]));
			}
		};
		//创建JPA的分页信息
		PageRequest of = PageRequest.of(pageNumber, PageUtils.PAGE_ROW_COUNT);
		//将数据返回到控制台
		return this.UsersRepository.findAll(specifatio,of);
	}

	/**
	 * 根据userId修改用户状态
	 */
	@Transactional
	@Override
	public Integer updateStatus(Integer userId, Integer userStatus) {
		return this.UsersRepository.updateStatus(userId, userStatus);
	}

	/**
	 * 根据userId将数据库的用户信息更新到修改用户信息模态框中
	 */
	@Override
	public Users selectById(Integer userId) {
		return this.UsersRepository.findById(userId).get();
	}

	//增删改需要@Transactional注释
	@Transactional
	@Override
	public Integer update(Users users) {
		return this.UsersRepository.update(users);
	}

   //修改登录用户的信息(头像,密码，昵称)
	@Transactional
	@Override
	public Integer updateUserImage(Users users) {
		if(users.getUserPhoto()!=null && !users.getUserPhoto().trim().equals("")) {
			return this.UsersRepository.updateUserImage(users);
		}
		return 0;
	}

	@Transactional
	@Override
	public Integer updateUserPassword(Integer userId, String userPassword) {
		return this.UsersRepository.updateUserPassword(userId, userPassword);
	}

	@Transactional
	@Override
	public Integer updateUserName(Integer userId, String userName) {
		return this.UsersRepository.updateUserName(userId, userName);
	}

}
