package org.linys.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.linys.dao.system.UserRoleDAO;
import org.linys.model.system.Role;
import org.linys.model.system.UserRole;
import org.linys.service.system.UserRoleService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @description:用户角色Service实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-2
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Resource
	private UserRoleDAO userRoleDAO;
	public String queryRole(UserRole model) {
		List<Role> list = userRoleDAO.queryRole(model);
		String[] properties = {"roleId","roleCode","roleName","status"};
		String ajaxString = JSONUtil.toJson(list,properties);
		return ajaxString;
	}

	public ServiceResult updateRole(UserRole model, String ids, String oldIds) {
		ServiceResult result = new ServiceResult(false);
		if(model==null||model.getUserId()==null){
			result.setMessage("请选择用户");
			return result;
		}
		Integer[] idArray = {};
		if(StringUtils.isNotEmpty(ids)){
			idArray = StringUtil.splitToInteger(ids);
		}
		
		Integer[] oldIdArray = {} ;
		if(StringUtils.isNotEmpty(oldIds)){
			oldIdArray = StringUtil.splitToInteger(oldIds);
		}
		
		List<Integer> deleteIdList = new ArrayList<Integer>();
		List<Integer> addIdList = new ArrayList<Integer>();
		for (Integer oldId : oldIdArray) {
			boolean isDel = true;
			for (Integer id : idArray) {
				if(oldId.equals(id)){
					isDel =false;
					break;
				}
			}
			if(oldId!=null&&isDel){
				deleteIdList.add(oldId);
			}
		}
		for (Integer id : idArray) {
			boolean isAdd = true;
			for (Integer oldId : oldIdArray) {
				if(oldId.equals(id)){
					isAdd =false;
					break;
				}
			}
			if(id!=null&&isAdd){
				addIdList.add(id);
			}
		}
		
		if(addIdList.size()==0&&deleteIdList.size()==0){
			result.setMessage("角色没修改");
			return result;
		}
		Integer UserId = model.getUserId();
		for (Integer id : deleteIdList) {
			UserRole userRole = new UserRole();
			userRole.setUserId(UserId);
			userRole.setRoleId(id);
			userRoleDAO.delete(userRole);
		}
		for (Integer id : addIdList) {
			UserRole userRole = new UserRole();
			userRole.setUserId(UserId);
			userRole.setRoleId(id);
			userRoleDAO.insert(userRole);
		}
		result.setIsSuccess(true);
		return result;
	}

}
