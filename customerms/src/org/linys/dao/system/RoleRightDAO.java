package org.linys.dao.system;

import java.util.List;

import org.linys.model.system.Right;
import org.linys.model.system.RoleRight;

/**
 * @description:角色权限DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-1
 * @author:以宋
 * @vesion:1.0
 */
public interface RoleRightDAO {
	/**
	 * @description: 取得跟角色权限
	 * @created: 2014-1-1 下午9:41:12
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Right getRootTreeNode(RoleRight model);
	/**
	 * @description: 取得权限下的子权限
	 * @created: 2014-1-1 下午9:53:43
	 * @author 以宋
	 * @param root
	 * @return
	 */
	List<Right> getChildrenTreeNode(RoleRight root);
	/**
	 * @description: 更新角色权限的状态
	 * @created: 2014-1-1 下午10:35:23
	 * @author 以宋
	 * @param model
	 */
	void updateStatus(RoleRight model);
	/**
	 * @description: 根据roleId和rightId取得角色权限
	 * @created: 2014-1-1 下午10:49:00
	 * @author 以宋
	 * @param model
	 * @return
	 */
	RoleRight loadByRoleIdRightId(RoleRight model);
	/**
	 * @description: 统计相同父节点下相同状态的子节点个数
	 * @created: 2014-1-1 下午11:02:25
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Integer countChildrenSameParent(RoleRight model);

}
