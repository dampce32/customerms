package org.linys.service.system;

import org.linys.model.system.RoleRight;
import org.linys.vo.ServiceResult;

/**
 * @description:权限角色Service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-1
 * @author:以宋
 * @vesion:1.0
 */
public interface RoleRightService {
	/**
	 * @description: 取得跟角色权限
	 * @created: 2014-1-1 下午9:36:21
	 * @author 以宋
	 * @param model
	 * @return
	 */
	String getRootTreeNode(RoleRight model);
	/**
	 * @description: 取得角色权限节点下的子权限
	 * @created: 2014-1-1 下午9:37:34
	 * @author 以宋
	 * @param model
	 * @return
	 */
	String getChildrenTreeNode(RoleRight model);
	/**
	 * @description: 更新角色权限状态
	 * @created: 2014-1-1 下午10:32:16
	 * @author 以宋
	 * @param model
	 * @return
	 */
	ServiceResult updateStatus(RoleRight model);

}
