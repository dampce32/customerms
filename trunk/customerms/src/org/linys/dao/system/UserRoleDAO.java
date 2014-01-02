package org.linys.dao.system;

import java.util.List;

import org.linys.model.system.Role;
import org.linys.model.system.UserRole;

/**
 * @description:用户角色DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-2
 * @author:以宋
 * @vesion:1.0
 */
public interface UserRoleDAO {
	/**
	 * @description: 查询用户下的角色
	 * @created: 2014-1-2 下午12:38:32
	 * @author 以宋
	 * @param model
	 * @return
	 */
	List<Role> queryRole(UserRole model);
	/**
	 * @description: 删除用户角色
	 * @created: 2014-1-2 下午12:39:09
	 * @author 以宋
	 * @param userRole
	 */
	void delete(UserRole userRole);
	/**
	 * @description: 插入用户角色
	 * @created: 2014-1-2 下午12:39:43
	 * @author 以宋
	 * @param userRole
	 */
	void insert(UserRole userRole);

}
