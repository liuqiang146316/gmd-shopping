package com.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springcloud.entity.Users;

/**
 * �־û��㣬�����USERS����в����ķ���
 * @author ��ǿ
 *
 */
public interface UsersRepository extends JpaRepository<Users,Integer>,JpaSpecificationExecutor<Users>{

	/**
	 * �ж��û���¼�Ƿ�ɹ�
	 * 
	 * @param userId         �û����  
	 * @param userPassword   �û�����
	 * @param jdictionId     �û�Ȩ�ޱ��
	 * @return    �ɹ����ص�¼�û���������Ϣ ��ʧ�ܷ���null
	 */
	@Query("select new com.springcloud.entity.Users(u.userId,u.userName,u.userNumber,u.userPassword,u.userSex,"
			+ "u.userPhone,u.userSite,u.userBirthday,u.userEmail,u.userPhoto,u.jdictionId,u.userStatus) "
	 +"from Users u where u.userId=:userId and u.userPassword=:userPassword and u.jdictionId=:jdictionId")
	public abstract Users login(@Param("userId") Integer userId,@Param("userPassword") String userPassword,
			@Param("jdictionId") Integer jdictionId);
	
	/**
	 * �޸�USERS����ָ����ŵ��û�״̬
	 * 
	 * @param userId      �û����
	 * @param userStatus  �û�״̬
	 * @return    �ɹ����ش���0��������ʧ�ܷ���0
	 */
	@Modifying
	@Query("update Users set userStatus=:userStatus where userId=:userId")
	public abstract Integer updateStatus(@Param("userId") Integer userId,@Param("userStatus") Integer userStatus);
	
	
	/**
	 * �޸�USERS����ָ��USER_ID��ŵ��û���Ϣ
	 * @param users   �޸ĵ��û���Ϣ
	 * @return        �ɹ����ش���0��������ʧ�ܷ���0
	 */
	@Modifying
	@Query("update Users u set u.userNumber=:#{#users.userNumber},u.userSex=:#{#users.userSex},u.userPhone=:#{#users.userPhone},u.userBirthday=:#{#users.userBirthday},"
			+ "u.userEmail=:#{#users.userEmail},u.userSite=:#{#users.userSite} where u.userId=:#{#users.userId}")
	public abstract Integer update(@Param("users")Users users);
	
	
	
	/**
	 * �޸�USERS����ָ��USER_ID��ţ���¼�û������û�ͷ��
	 * @param users
	 * @return
	 */
	@Modifying
	@Query("update Users u set u.userPhoto=:#{#users.userPhoto} where u.userId=:#{#users.userId}")
	public abstract Integer updateUserImage(@Param("users") Users users);
	
	
	/**
	 * �޸�USERS����ָ��USER_ID��ţ���¼�û������û�����
	 * @param userId    �û����
	 * @param userPassword  �û�����
	 * @return
	 */
	@Modifying
	@Query("update Users set userPassword=:userPassword where userId=:userId")
	public abstract Integer updateUserPassword(@Param("userId") Integer userId,@Param("userPassword") String userPassword);

	/**
	 * �޸�USERS����ָ��USER_ID��ţ���¼�û������û��ǳ�
	 * @param userId   �û����
	 * @param userName �û�����
	 * @return
	 */
	@Modifying
	@Query("update Users set userName=:userName where userId=:userId")
	public abstract Integer updateUserName(@Param("userId") Integer userId,@Param("userName") String userName);
}
