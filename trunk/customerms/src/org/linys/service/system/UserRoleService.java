package org.linys.service.system;

import org.linys.model.system.UserRole;
import org.linys.vo.ServiceResult;

/**
 * @description:用户角色service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-2
 * @author:以宋
 * @vesion:1.0
 */
public interface UserRoleService {
	/**
	 * @description: 查询用户的角色
	 * @created: 2014-1-2 下午12:29:49
	 * @author 以宋
	 * @param model
	 * @return
	 */
	String queryRole(UserRole model);
	/**
	 * @description: 更新用户角色
	 * @created: 2014-1-2 下午12:30:05
	 * @author 以宋
	 * @param model
	 * @param ids
	 * @param oldIds
	 * @return
	 */
	ServiceResult updateRole(UserRole model, String ids, String oldIds);

}
