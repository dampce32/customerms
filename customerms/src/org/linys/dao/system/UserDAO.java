package org.linys.dao.system;

import java.util.List;

import org.linys.model.system.User;

/**
 * @description:用户DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-2
 * @author:以宋
 * @vesion:1.0
 */
public interface UserDAO {
	/**
	 * @description: 根据用户编号查询用户
	 * @created: 2014-1-2 上午1:31:43
	 * @author 以宋
	 * @param userCode
	 * @return
	 */
	User loadByUserCode(String userCode);
	/**
	 * @description: 插入用户
	 * @created: 2014-1-2 上午1:32:07
	 * @author 以宋
	 * @param model
	 */
	void insert(User model);
	/**
	 * @description: 更新用户
	 * @created: 2014-1-2 上午1:32:43
	 * @author 以宋
	 * @param model
	 */
	void update(User model);
	/**
	 * @description: 分页查询用户
	 * @created: 2014-1-2 上午1:34:53
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<User> query(Integer page, Integer rows, User model);
	/**
	 * @description: 统计用户
	 * @created: 2014-1-2 上午1:35:09
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Long count(User model);
	/**
	 * @description: 删除用户
	 * @created: 2014-1-2 上午1:36:51
	 * @author 以宋
	 * @param id
	 */
	void delete(Integer userId);
	/**
	 * @description: 根据userId查询用户
	 * @created: 2014-1-2 上午1:39:01
	 * @author 以宋
	 * @param userId
	 * @return
	 */
	User loadByUserId(Integer userId);
	/**
	 * @description: 更新用户状态
	 * @created: 2014-1-2 上午1:39:48
	 * @author 以宋
	 * @param userId
	 * @param status
	 */
	void updateStatus(Integer userId, Integer status);

}
