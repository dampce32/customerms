package org.linys.dao.system;

import java.util.List;

import org.linys.model.system.Role;
/**
 * @Description:角色DAO
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
public interface RoleDAO{
	/**
	 * @description: 按角色编号查询角色
	 * @created: 2014-1-1 下午6:54:04
	 * @author 以宋
	 * @param roleCode
	 * @return
	 */
	Role loadByRoleCode(String roleCode);
	/**
	 * @Description: 分页查询角色
	 * @Created Time: 2013-4-17 上午10:50:58
	 * @Author lys
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<Role> query(Integer page, Integer rows, Role model);
	/**
	 * @Description: 统计角色
	 * @Created Time: 2013-4-17 上午10:51:16
	 * @Author lys
	 * @param model
	 * @return
	 */
	Long count(Role model);
	/**
	 * @description: 取得最大array值
	 * @created: 2014-1-1 下午6:54:48
	 * @author 以宋
	 * @return
	 */
	Integer getMaxArray();
	/**
	 * @description: 新建角色
	 * @created: 2014-1-1 下午6:56:09
	 * @author 以宋
	 * @param model
	 */
	void insert(Role model);
	/**
	 * @description: 修改角色
	 * @created: 2014-1-1 下午7:06:18
	 * @author 以宋
	 * @param model
	 */
	void update(Role model);
	/**
	 * @description: 根据roleId取得角色
	 * @created: 2014-1-1 下午7:29:20
	 * @author 以宋
	 * @param roleId
	 * @return
	 */
	Role loadByRoleId(Integer roleId);
	/**
	 * @description: 删除角色
	 * @created: 2014-1-1 下午7:32:45
	 * @author 以宋
	 * @param id
	 */
	void delete(Integer roleId);
	/**
	 * @description: 更新状态
	 * @created: 2014-1-1 下午7:36:57
	 * @author 以宋
	 * @param id
	 * @param status
	 */
	void updateStatus(Integer roleId, Integer status);
	/**
	 * @description: 更新排序
	 * @created: 2014-1-1 下午7:41:20
	 * @author 以宋
	 * @param roleId
	 * @param updateRoleId
	 */
	void updateArray(Integer roleId, Integer updateRoleId);
	
}
