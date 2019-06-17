package com.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springcloud.entity.Users;

/**
 * 持久化层，定义对USERS表进行操作的方法
 * @author 刘强
 *
 */
public interface UsersRepository extends JpaRepository<Users,Integer>,JpaSpecificationExecutor<Users>{

	/**
	 * 判断用户登录是否成功
	 * 
	 * @param userId         用户编号  
	 * @param userPassword   用户密码
	 * @param jdictionId     用户权限编号
	 * @return    成功返回登录用户的完整信息 ，失败返回null
	 */
	@Query("select new com.springcloud.entity.Users(u.userId,u.userName,u.userNumber,u.userPassword,u.userSex,"
			+ "u.userPhone,u.userSite,u.userBirthday,u.userEmail,u.userPhoto,u.jdictionId,u.userStatus) "
	 +"from Users u where u.userId=:userId and u.userPassword=:userPassword and u.jdictionId=:jdictionId")
	public abstract Users login(@Param("userId") Integer userId,@Param("userPassword") String userPassword,
			@Param("jdictionId") Integer jdictionId);
	
	/**
	 * 修改USERS表中指定编号的用户状态
	 * 
	 * @param userId      用户编号
	 * @param userStatus  用户状态
	 * @return    成功返回大于0的整数，失败返回0
	 */
	@Modifying
	@Query("update Users set userStatus=:userStatus where userId=:userId")
	public abstract Integer updateStatus(@Param("userId") Integer userId,@Param("userStatus") Integer userStatus);
	
	
	/**
	 * 修改USERS表中指定USER_ID编号的用户信息
	 * @param users   修改的用户信息
	 * @return        成功返回大于0的整数，失败返回0
	 */
	@Modifying
	@Query("update Users u set u.userNumber=:#{#users.userNumber},u.userSex=:#{#users.userSex},u.userPhone=:#{#users.userPhone},u.userBirthday=:#{#users.userBirthday},"
			+ "u.userEmail=:#{#users.userEmail},u.userSite=:#{#users.userSite} where u.userId=:#{#users.userId}")
	public abstract Integer update(@Param("users")Users users);
	
	
	
	/**
	 * 修改USERS表中指定USER_ID编号（登录用户）的用户头像
	 * @param users
	 * @return
	 */
	@Modifying
	@Query("update Users u set u.userPhoto=:#{#users.userPhoto} where u.userId=:#{#users.userId}")
	public abstract Integer updateUserImage(@Param("users") Users users);
	
	
	/**
	 * 修改USERS表中指定USER_ID编号（登录用户）的用户密码
	 * @param userId    用户编号
	 * @param userPassword  用户密码
	 * @return
	 */
	@Modifying
	@Query("update Users set userPassword=:userPassword where userId=:userId")
	public abstract Integer updateUserPassword(@Param("userId") Integer userId,@Param("userPassword") String userPassword);

	/**
	 * 修改USERS表中指定USER_ID编号（登录用户）的用户昵称
	 * @param userId   用户编号
	 * @param userName 用户姓名
	 * @return
	 */
	@Modifying
	@Query("update Users set userName=:userName where userId=:userId")
	public abstract Integer updateUserName(@Param("userId") Integer userId,@Param("userName") String userName);
}
