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
 * ģ�Ͳ��ʵ����
 * 
 * @author ��ǿ
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
	 * ����û���Ϣ
	 */
	@Override
	public Users insert(Users users) {
		
		return this.UsersRepository.save(users);
	}
	
	/**
	 * ��ҳ��ѯ
	 */
	@Override
	public Page<Users> select(Users users,Integer pageNumber){
		
		//���ݲ�ѯ������������̬����
		@SuppressWarnings("serial")
		Specification<Users> specifatio = new Specification<Users>() {
			
			@Transactional
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				//����List���ϣ����ڱ������е�where����
				List<Predicate> whereList = new ArrayList<>();
				
				//����Users�еĲ�ѯ���ݣ���̬������ѯ����
				if(users.getUserName()!=null && !users.getUserName().trim().equals("")) {
					whereList.add(criteriaBuilder.like(root.get("userName"), "%" + users.getUserName() + "%"));
				}
				if(users.getUserStatus()!=-1) {
					whereList.add(criteriaBuilder.equal(root.get("userStatus"), users.getUserStatus()));
				}
				//�����е������Թ�ϵ������һ�𣬲�����
				return criteriaBuilder.and(whereList.toArray(new Predicate[whereList.size()]));
			}
		};
		//����JPA�ķ�ҳ��Ϣ
		PageRequest of = PageRequest.of(pageNumber, PageUtils.PAGE_ROW_COUNT);
		//�����ݷ��ص�����̨
		return this.UsersRepository.findAll(specifatio,of);
	}

	/**
	 * ����userId�޸��û�״̬
	 */
	@Transactional
	@Override
	public Integer updateStatus(Integer userId, Integer userStatus) {
		return this.UsersRepository.updateStatus(userId, userStatus);
	}

	/**
	 * ����userId�����ݿ���û���Ϣ���µ��޸��û���Ϣģ̬����
	 */
	@Override
	public Users selectById(Integer userId) {
		return this.UsersRepository.findById(userId).get();
	}

	//��ɾ����Ҫ@Transactionalע��
	@Transactional
	@Override
	public Integer update(Users users) {
		return this.UsersRepository.update(users);
	}

   //�޸ĵ�¼�û�����Ϣ(ͷ��,���룬�ǳ�)
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
