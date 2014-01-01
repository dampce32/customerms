package org.linys.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.linys.dao.system.RoleDAO;
import org.linys.model.system.Role;
import org.linys.service.system.RoleService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @Description:角色Service实现类
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
@Service
public class RoleServiceImpl  implements RoleService {
	@Resource
	private RoleDAO roleDAO;
//	@Resource
//	private TeacherDAO teacherDAO;
//	@Resource
//	private RoleRightDAO roleRightDAO;
	/*
	 * (non-Javadoc)   
	 * @see com.csit.service.RoleService#save(com.csit.model.Role, java.lang.Integer)
	 */
	public ServiceResult save(Role model) {
		/*
		 *新增角色，
		 *将当前用户的权限，赋予新增的角色 
		 */
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(model.getRoleCode())){
			result.setMessage("请填写角色编号");
			return result;
		}
		if(StringUtils.isEmpty(model.getRoleName())){
			result.setMessage("请填写角色名");
			return result;
		}
		
		Role oldRole = roleDAO.loadByRoleCode(model.getRoleCode());
		if(model.getRoleId()==null){//新增
			if(oldRole!=null){
				result.setMessage("角色编号已存在，请重新输入角色编号");
				return result;
			}
			//取得最大的排序值
			Integer maxArray = roleDAO.getMaxArray();
			model.setArray(maxArray+1);
			roleDAO.insert(model);
//			/*
//			 * 新增角色，并将当前教师的权限赋值给新增的角色
//			 */
//			List<Map<String,Object>> list = teacherDAO.queryTeacherRight(teacherId);
//			//取出当前教师整合后的角色权限
//			for (Map<String, Object> map : list) {
//				RoleRight roleRight = new RoleRight();
//				
//				RoleRightId roleRightId = new RoleRightId();
//				roleRightId.setRoleId(model.getRoleId());
//				
//				String rightId = map.get("RightID").toString();
//				roleRightId.setRightId(rightId);
//				
//				String state = map.get("State").toString();
//				if("1".equals(state)){
//					roleRight.setState(1);
//				}else{
//					roleRight.setState(0);
//				}
//				
//				roleRight.setId(roleRightId);
//				roleRightDAO.save(roleRight);
//			}
		}else{
			if(oldRole!=null&&!oldRole.getRoleId().equals(model.getRoleId())){
				result.setMessage("其他权限已使用该角色编号");
				return result;
			}
			roleDAO.update(model);
		}
		result.getData().put("roleId", model.getRoleId());
		result.setIsSuccess(true);
		return result;
	}
	public String query(Integer page, Integer rows, Role model) {
		List<Role> list = roleDAO.query(page,rows,model);
		Long total = roleDAO.count(model);
		String[] properties = {"roleId","roleCode","roleName","status","array"};
		return JSONUtil.toJson(list, properties, total);
	}
	public ServiceResult mulDelete(String ids) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(ids)){
			result.setMessage("请选择要删除的记录");
			return result;
		}
		Integer[] idArray = StringUtil.splitToInteger(ids);
		if(idArray.length==0){
			result.setMessage("请选择要删除的记录");
			return result;
		}
		boolean haveDelete = false;
		for (Integer id : idArray) {
			Role item = roleDAO.loadByRoleId(idArray[0]);
			if("超级管理员".equals(item.getRoleName())){
				continue;
			}else{
				roleDAO.delete(id);
				haveDelete = true;
			}
		}
		if(!haveDelete){
			result.setMessage("没有可删除的角色");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}
	public ServiceResult mulUpdateStatus(String ids, Role model) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(ids)){
			result.setMessage("请选择要修改状态的角色");
			return result;
		}
		Integer[] idArray =StringUtil.splitToInteger(ids);
		if(idArray.length==0){
			result.setMessage("请选择要修改状态的角色");
			return result;
		}
		if(model==null||model.getStatus()==null){
			result.setMessage("请选择要修改成的状态");
			return result;
		}
		boolean haveUpdateStatus = false;
		for (Integer id : idArray) {
			Role oldRole = roleDAO.loadByRoleId(id);
			if(oldRole!=null&&oldRole.getStatus().intValue()!=model.getStatus().intValue()){
				roleDAO.updateStatus(id, model.getStatus());
				haveUpdateStatus = true;
			}
		}
		if(!haveUpdateStatus){
			result.setMessage("没有可修改状态的角色");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}
	public ServiceResult updateArray(Integer roleId, Integer updateRoleId) {
		ServiceResult result = new ServiceResult(false);
		if(roleId==null||updateRoleId==null){
			result.setMessage("请选择要改变排序的角色");
			return result;
		}
		roleDAO.updateArray(roleId,updateRoleId);
		result.setIsSuccess(true);
		return result;
	}
}
