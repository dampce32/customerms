package org.linys.service.system.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.linys.dao.system.RightDAO;
import org.linys.dao.system.UserDAO;
import org.linys.model.system.Right;
import org.linys.model.system.User;
import org.linys.service.system.UserService;
import org.linys.util.JSONUtil;
import org.linys.util.MD5Util;
import org.linys.util.StringUtil;
import org.linys.util.TreeUtil;
import org.linys.vo.GlobalConstants;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @description:用户Service实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-2
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private RightDAO rightDAO;
	
	String[] properties = {"userId","userCode","userName","status"};
	public ServiceResult save(User model) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(model.getUserCode())){
			result.setMessage("请填写用户编号");
			return result;
		}
		if(StringUtils.isEmpty(model.getUserName())){
			result.setMessage("请填写用户名");
			return result;
		}
		User oldUser = userDAO.loadByUserCode(model.getUserCode());
		if(model.getUserId()==null){//新增
			if(oldUser!=null){
				result.setMessage("用户编号已存在，请重新输入用户编号");
				return result;
			}
			String passwords = MD5Util.getMD5(GlobalConstants.DEFAULT_PWD);
			model.setPasswords(passwords);
			userDAO.insert(model);
		}else{
			if(oldUser!=null&&!oldUser.getUserId().equals(model.getUserId())){
				result.setMessage("其他权限已使用该用户编号");
				return result;
			}
			userDAO.update(model);
		}
		result.getData().put("userId", model.getUserId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(Integer page, Integer rows, User model) {
		List<User> list = userDAO.query(page, rows, model);
		Long total = userDAO.count(model);
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
		for (Integer id : idArray) {
			if(idArray[0]!=null){
				userDAO.delete(id);
			}
		}
		result.setIsSuccess(true);
		return result;
	}

	public ServiceResult mulUpdateStatus(String ids, User model) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(ids)){
			result.setMessage("请选择要修改状态的用户");
			return result;
		}
		Integer[] idArray =StringUtil.splitToInteger(ids);
		if(idArray.length==0){
			result.setMessage("请选择要修改状态的用户");
			return result;
		}
		if(model==null||model.getStatus()==null){
			result.setMessage("请选择要修改成的状态");
			return result;
		}
		boolean haveUpdateStatus = false;
		for (Integer id : idArray) {
			User oldUser = userDAO.loadByUserId(id);
			if(oldUser!=null&&oldUser.getStatus().intValue()!=model.getStatus().intValue()){
				userDAO.updateStatus(id, model.getStatus());
				haveUpdateStatus = true;
			}
		}
		if(!haveUpdateStatus){
			result.setMessage("没有可修改状态的用户");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}

	public User login(String userCode, String passwords) {
		return userDAO.login(userCode,passwords);
	}

	public String getRootUrlRightTreeNode(Integer userId) {
		Right root = rightDAO.getRootTreeNode();
		if(root!=null){
			if(root.getIsLeaf()==0){//不是叶子节点
				List<Right> childrenRights = userDAO.getChildrenUrlRightTreeNode(userId,root.getRightId());
				root.setChildrenRights(childrenRights);
			}
		}
		String jsonString = TreeUtil.toJSONRightList(root.getChildrenRights());
		return jsonString;
	}

	public String getChildrenUrlRightTreeNode(Integer userId, Integer rightId) {
		List<Right> childrenRights = userDAO.getChildrenUrlRightTreeNode(userId,rightId);
		String jsonString = TreeUtil.toJSONRightList(childrenRights);
		return jsonString;
	}

	public String queryCombobox() {
		ServletContext servletContext = ServletActionContext.getServletContext();
		String jsonFilePath = "/WEB-INF/dictJson/"+User.class.getSimpleName()+".json";
		File jsonFile = new File(servletContext.getRealPath(jsonFilePath));
		String jsonString = null;
		try {
			if(!jsonFile.exists()){
				List<User> list = userDAO.queryCombobox();
				String[] properties = {"userId","userName"};
				jsonString = JSONUtil.toJsonWithoutRows(list,properties);
				FileUtils.writeStringToFile(jsonFile, jsonString,"UTF-8");
			}else{
				jsonString = FileUtils.readFileToString(jsonFile,"UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("员工combobox查询失败");
		}
		return jsonString;
	}

}
