package org.linys.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.linys.dao.system.RightDAO;
import org.linys.dao.system.RoleRightDAO;
import org.linys.model.system.Right;
import org.linys.model.system.RoleRight;
import org.linys.service.system.RoleRightService;
import org.linys.util.TreeUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @description:角色权限Service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-1
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class RoleRightServiceImpl implements RoleRightService {
	@Resource
	private RoleRightDAO roleRightDAO;
	@Resource
	private RightDAO rightDAO;
	
	public String getRootTreeNode(RoleRight model) {
		Right root = roleRightDAO.getRootTreeNode(model);
		if(root!=null){
			if(root.getIsLeaf()==0){//不是叶子节点
				model.setRightId(root.getRightId());
				List<Right> childrenRights = roleRightDAO.getChildrenTreeNode(model);
				root.setChildrenRights(childrenRights);
			}
		}
		return TreeUtil.toJSON(TreeUtil.toTreeNode(root));
	}
	
	public String getChildrenTreeNode(RoleRight model) {
		List<Right> childrenRights = roleRightDAO.getChildrenTreeNode(model);
		String jsonString = TreeUtil.toJSONRightList(childrenRights);
		return jsonString;
	}

	public ServiceResult updateStatus(RoleRight model) {
		ServiceResult result = new ServiceResult(false);
		if(model==null||model.getRoleId()==null||model.getRightId()==null){
			result.setMessage("请选择你要更新的角色权限节点");
			return result;
		}
		if(model.getStatus()==null){
			result.setMessage("请选勾选你要修改的状态");
			return result;
		}
		/*
		 * 勾选状态
		 * true：表明从未勾选到勾选状态
		 *	如果是叶子节点，则	勾选勾选父亲节点中未勾选的节点 、本节点
		 *     不是叶子节点，则选勾选父亲节点中未勾选的节点 和、本节点、子节点
		 *	
		 * false: 从勾选到未勾选
		 *  更新本节点和子节点未未勾选状态
		 *  更新父辈节点中子节点中没有勾选的节点
		 */
		
		//更新本节点
		roleRightDAO.updateStatus(model);
		
		if(model.getStatus()==1){//从未勾选到勾选状态
			setParentTrue(model);
		}else{
			setParentFalse(model);
		}
		setChildrenStatus(model);
		result.setIsSuccess(true);
		return result;
	}

	/**
	 * @Description: 从未勾选到勾选情况下更新父节点的状态
	 * @Create: 2012-10-27 下午10:27:01
	 * @author lys
	 * @update logs
	 * @param model
	 * @throws Exception
	 */
	private void setParentTrue(RoleRight model){
		Right parentRight = rightDAO.getParentRight(model.getRightId());
		if(parentRight!=null){
			RoleRight roleRight = new RoleRight();
			roleRight.setRoleId(model.getRoleId());
			roleRight.setRightId(parentRight.getRightId());
			
			RoleRight parentRoleRight = roleRightDAO.loadByRoleIdRightId(roleRight);
			if(parentRoleRight.getStatus().intValue()==0){
				roleRight.setStatus(1);
				roleRight.setRightId(parentRight.getRightId());
				roleRightDAO.updateStatus(roleRight);
				
				setParentTrue(parentRoleRight);
			}
		}
	}
	
	/**
	 * @Description: 从勾选到未勾选情况下更新父节点的状态
	 * @Create: 2012-10-27 下午10:04:51
	 * @author lys
	 * @update logs
	 * @param model
	 * @throws Exception
	 */
	private void setParentFalse(RoleRight model){
		/*
		 * 取得当前权限节点的父节点，判断该节点下的子节点的状态是否都为未勾选，如果是修改父节点为未勾选，并查找递归父节点
		 * 如果父节点的子节点的孩子节点不是都为未勾选状态，则不操作
		 */
		RoleRight roleRight = new RoleRight();
		roleRight.setRoleId(model.getRoleId());
		roleRight.setRightId(model.getRightId());
		roleRight.setStatus(1);
		
		Integer count = roleRightDAO.countChildrenSameParent(roleRight);
		if(count==0){
			Right parentRight = rightDAO.getParentRight(roleRight.getRightId());
			if(parentRight!=null){
				roleRight.setRightId(parentRight.getRightId());
				roleRight.setStatus(0);
				roleRightDAO.updateStatus(roleRight);
				setParentFalse(roleRight);
			}
		}
	}
	/**
	 * @Description: 更新子节点的状态
	 * @Create: 2012-10-27 下午9:57:28
	 * @author lys
	 * @update logs
	 * @param model
	 * @param state
	 * @throws Exception
	 */
	private void setChildrenStatus(RoleRight model){
		Right currentRight = rightDAO.loadByRightId(model.getRightId());
		if(currentRight.getIsLeaf()==0){
			List<Right> children = rightDAO.getChildrenTreeNode(currentRight);
			for (Right right : children) {
				model.setRightId(right.getRightId());
				roleRightDAO.updateStatus(model);
				setChildrenStatus(model);
			}
		}
	}
}
