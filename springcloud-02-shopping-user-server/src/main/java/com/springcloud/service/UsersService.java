package com.springcloud.service;
import org.springframework.data.domain.Page;
import com.springcloud.entity.Users;

/**
 * 模型层的接口，用于定义用户模型的方法
 * 
 * @author 刘强
 *
 */
public interface UsersService {

	/**
	 * 验证用户登录是否成功
	 * 
	 * @param userId          用户编号
	 * @param userpassword    用户密码
	 * @param jdictionId      用户权限编号
	 * @return    成功返回登录用户的完整信息 ，失败返回null
	 */
	public abstract Users login(Integer userId,String userPassword,Integer jdictionId);
	
	/**
	 * 添加新的用户信息
	 * 
	 * @param users   新的用户信息
	 * @return   成功返回com.springcloud.entity.Users类型的实例，失败返回null
	 */
	public abstract Users insert(Users users);
	
	/**
	 * 查询所有的用户信息
	 * 
	 * @param users       查询条件
	 * @param pageNumber  查询页数 
	 * @return       成功返回查询页数  失败返回null      
	 */
	public abstract Page<Users> select(Users users,Integer pageNumber);
	
	/**
	 * 修改USERS表中指定编号的用户状态
	 * 
	 * @param userId      用户编号
	 * @param userStatus  用户状态
	 * @return    成功返回大于0的整数，失败返回0
	 */
	public abstract Integer updateStatus(Integer userId,Integer userStatus);
	
	/**
	 * 根据userId将数据库的用户信息更新到修改用户信息模态框中
	 * @param userId  用户编号
	 * @return        成功在修改用户信息模态框中显示用户信息  失败返回1
	 */
	public abstract Users selectById(Integer userId);
	
	/**
	 * 修改指定编号的用户信息
	 * 
	 * @param users  修改的用户信息
	 * @return       修改成功返回大于0的整数，否则返回0
	 */
	public abstract Integer update(Users users);
	
	/**
	 * 修改USERS表中登录用户的用户头像
	 * @param users  修改信息
	 * @return		修改成功返回大于0的整数，否则返回0
	 */
	public abstract Integer updateUserImage(Users users);
	
	/**
	 * 修改USERS表中登录用户的用户密码
	 * @param userId  用户编号
	 * @param userPassword  用户密码
	 * @return		修改成功返回大于0的整数，否则返回0
	 */
	public abstract Integer updateUserPassword(Integer userId,String userPassword);
	
	/**
	 * 修改USERS表中登录用户的用户姓名
	 * @param userId   用户编号
	 * @param userName  用户姓名
	 * @return
	 */
	public abstract Integer updateUserName(Integer userId,String userName);

}
