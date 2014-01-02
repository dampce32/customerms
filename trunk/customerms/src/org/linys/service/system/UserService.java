package org.linys.service.system;

import org.linys.model.system.User;
import org.linys.vo.ServiceResult;

/**
 * @description:用户Service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-2
 * @author:以宋
 * @vesion:1.0
 */
public interface UserService {
	/**
	 * @description: 保存用户
	 * @created: 2014-1-2 上午1:23:16
	 * @author 以宋
	 * @param model
	 * @return
	 */
	ServiceResult save(User model);
	/**
	 * @description: 分页查询用户
	 * @created: 2014-1-2 上午1:23:42
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	String query(Integer page, Integer rows, User model);
	/**
	 * @description: 批量用户删除
	 * @created: 2014-1-2 上午1:24:03
	 * @author 以宋
	 * @param ids
	 * @return
	 */
	ServiceResult mulDelete(String ids);
	/**
	 * @description: 批量修改用户状态
	 * @created: 2014-1-2 上午1:25:12
	 * @author 以宋
	 * @param ids
	 * @param model
	 * @return
	 */
	ServiceResult mulUpdateStatus(String ids, User model);
	/**
	 * @description: 根据用户编号，用户密码查询用户
	 * @created: 2014-1-2 下午8:52:55
	 * @author 以宋
	 * @param userCode
	 * @param md5
	 * @return
	 */
	User login(String userCode, String passwords);

}
