package org.linys.service.dict.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.linys.dao.dict.CustomerTypeDAO;
import org.linys.model.dict.CustomerType;
import org.linys.service.dict.CustomerTypeService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @description:会员类型Service实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {
	@Resource
	private CustomerTypeDAO customerTypeDAO;

	public ServiceResult save(CustomerType model) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(model.getCustomerTypeName())){
			result.setMessage("请填写会员类型名称");
			return result;
		}
		
		CustomerType oldCustomerType = customerTypeDAO.loadByCustomerTypeName(model.getCustomerTypeName());
		if(model.getCustomerTypeId()==null){//新增
			if(oldCustomerType!=null){
				result.setMessage("会员类型名称已存在，请重新输入会员类型名称");
				return result;
			}
			customerTypeDAO.insert(model);
		}else{
			if(oldCustomerType!=null&&!oldCustomerType.getCustomerTypeId().equals(model.getCustomerTypeId())){
				result.setMessage("其他权限已使用该会员类型名称");
				return result;
			}
			customerTypeDAO.update(model);
		}
		result.getData().put("customerTypeId", model.getCustomerTypeId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(Integer page, Integer rows, CustomerType model) {
		List<CustomerType> list = customerTypeDAO.query(page,rows,model);
		Long total = customerTypeDAO.count(model);
		String[] properties = {"customerTypeId","customerTypeName","discount"};
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
			customerTypeDAO.delete(id);
			haveDelete = true;
		}
		if(!haveDelete){
			result.setMessage("没有可删除的会员类型");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}

	public String queryCombobox() {
		ServletContext servletContext = ServletActionContext.getServletContext();
		String jsonFilePath = "/WEB-INF/dictJson/"+CustomerType.class.getSimpleName()+".json";
		File jsonFile = new File(servletContext.getRealPath(jsonFilePath));
		String jsonString = null;
		try {
			if(!jsonFile.exists()){
				List<CustomerType> list = customerTypeDAO.queryCombobox();
				String[] properties = {"customerTypeId","customerTypeName"};
				jsonString = JSONUtil.toJsonWithoutRows(list,properties);
				FileUtils.writeStringToFile(jsonFile, jsonString,"UTF-8");
			}else{
				jsonString = FileUtils.readFileToString(jsonFile,"UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("会员类型combobox查询失败");
		}
		return jsonString;
	}
	
	
}
