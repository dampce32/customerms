package org.linys.service.system;

import org.linys.model.system.Role;
import org.linys.vo.ServiceResult;
/**
 * @Description:角色Service
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
public interface RoleService{
	/**
	 * @Description: 保存角色
	 * @Created Time: 2013-4-17 上午10:41:11
	 * @Author lys
	 * @param model
	 * @param integerSesion
	 * @return
	 */
	ServiceResult save(Role model);
	/**
	 * @Description: 分页查询角色
	 * @Created Time: 2013-4-17 上午10:42:04
	 * @Author lys
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	String query(Integer page, Integer rows, Role model);
	/**
	 * @Description: 批量角色删除
	 * @Created Time: 2013-4-17 上午11:14:18
	 * @Author lys
	 * @param ids
	 * @return
	 */
	ServiceResult mulDelete(String ids);
	/**
	 * @Description: 批量修改角色状态
	 * @Created Time: 2013-4-17 上午11:14:31
	 * @Author lys
	 * @param ids
	 * @param model
	 * @return
	 */
	ServiceResult mulUpdateStatus(String ids, Role model);
	/**
	 * @Description: 更新角色顺序
	 * @Create: 2013-11-27 上午08:53:15
	 * @author jcf
	 * @update logs
	 * @param roleId
	 * @param updateRoleId
	 * @return
	 */
	ServiceResult updateArray(Integer roleId, Integer updateRoleId);
}
