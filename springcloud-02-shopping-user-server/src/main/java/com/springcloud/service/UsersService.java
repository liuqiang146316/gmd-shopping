package com.springcloud.service;
import org.springframework.data.domain.Page;
import com.springcloud.entity.Users;

/**
 * ģ�Ͳ�Ľӿڣ����ڶ����û�ģ�͵ķ���
 * 
 * @author ��ǿ
 *
 */
public interface UsersService {

	/**
	 * ��֤�û���¼�Ƿ�ɹ�
	 * 
	 * @param userId          �û����
	 * @param userpassword    �û�����
	 * @param jdictionId      �û�Ȩ�ޱ��
	 * @return    �ɹ����ص�¼�û���������Ϣ ��ʧ�ܷ���null
	 */
	public abstract Users login(Integer userId,String userPassword,Integer jdictionId);
	
	/**
	 * ����µ��û���Ϣ
	 * 
	 * @param users   �µ��û���Ϣ
	 * @return   �ɹ�����com.springcloud.entity.Users���͵�ʵ����ʧ�ܷ���null
	 */
	public abstract Users insert(Users users);
	
	/**
	 * ��ѯ���е��û���Ϣ
	 * 
	 * @param users       ��ѯ����
	 * @param pageNumber  ��ѯҳ�� 
	 * @return       �ɹ����ز�ѯҳ��  ʧ�ܷ���null      
	 */
	public abstract Page<Users> select(Users users,Integer pageNumber);
	
	/**
	 * �޸�USERS����ָ����ŵ��û�״̬
	 * 
	 * @param userId      �û����
	 * @param userStatus  �û�״̬
	 * @return    �ɹ����ش���0��������ʧ�ܷ���0
	 */
	public abstract Integer updateStatus(Integer userId,Integer userStatus);
	
	/**
	 * ����userId�����ݿ���û���Ϣ���µ��޸��û���Ϣģ̬����
	 * @param userId  �û����
	 * @return        �ɹ����޸��û���Ϣģ̬������ʾ�û���Ϣ  ʧ�ܷ���1
	 */
	public abstract Users selectById(Integer userId);
	
	/**
	 * �޸�ָ����ŵ��û���Ϣ
	 * 
	 * @param users  �޸ĵ��û���Ϣ
	 * @return       �޸ĳɹ����ش���0�����������򷵻�0
	 */
	public abstract Integer update(Users users);
	
	/**
	 * �޸�USERS���е�¼�û����û�ͷ��
	 * @param users  �޸���Ϣ
	 * @return		�޸ĳɹ����ش���0�����������򷵻�0
	 */
	public abstract Integer updateUserImage(Users users);
	
	/**
	 * �޸�USERS���е�¼�û����û�����
	 * @param userId  �û����
	 * @param userPassword  �û�����
	 * @return		�޸ĳɹ����ش���0�����������򷵻�0
	 */
	public abstract Integer updateUserPassword(Integer userId,String userPassword);
	
	/**
	 * �޸�USERS���е�¼�û����û�����
	 * @param userId   �û����
	 * @param userName  �û�����
	 * @return
	 */
	public abstract Integer updateUserName(Integer userId,String userName);

}
