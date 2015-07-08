package org.linys.service.dict.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.linys.dao.dict.DataDictDAO;
import org.linys.model.dict.DataDict;
import org.linys.service.dict.DataDictService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @description:数据字典Service实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class DataDictServiceImpl implements DataDictService {
	@Resource
	private DataDictDAO dataDictDAO;

	public ServiceResult save(DataDict model) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(model.getDataDictType())){
			result.setMessage("请填写数据字典类型");
			return result;
		}
		if(StringUtils.isEmpty(model.getDataDictName())){
			result.setMessage("请填写数据字典名称");
			return result;
		}
		
		DataDict oldDataDict = dataDictDAO.loadByTypeAndName(model.getDataDictType(),model.getDataDictName());
		if(model.getDataDictId()==null){//新增
			if(oldDataDict!=null){
				result.setMessage("数据字典名称已存在，请重新输入数据字典名称");
				return result;
			}
			dataDictDAO.insert(model);
		}else{
			if(oldDataDict!=null&&!oldDataDict.getDataDictId().equals(model.getDataDictId())){
				result.setMessage("其他权限已使用该数据字典名称");
				return result;
			}
			dataDictDAO.update(model);
		}
		result.getData().put("dataDictId", model.getDataDictId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(Integer page, Integer rows, DataDict model) {
		List<DataDict> list = dataDictDAO.query(page,rows,model);
		Long total = dataDictDAO.count(model);
		String[] properties = {"dataDictId","dataDictName"};
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
			dataDictDAO.delete(id);
			haveDelete = true;
		}
		if(!haveDelete){
			result.setMessage("没有可删除的数据字典");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}

	public String querySelect(Integer page, Integer rows, DataDict model, String ids) {
		Integer[] idArray = StringUtil.splitToInteger(ids);
		model.setIdArray(idArray);
		List<DataDict> list = dataDictDAO.querySelect(page,rows,model);
		Long total = dataDictDAO.countSelect(model);
		String[] properties = {"dataDictId","dataDictName"};
		return JSONUtil.toJson(list, properties, total);
	}

	public String queryCombobox(DataDict model) {
		String jsonString = null;
		try {
			List<DataDict> list = dataDictDAO.queryCombobox(model);
			String[] properties = {"dataDictId","dataDictName"};
			jsonString = JSONUtil.toJsonWithoutRows(list,properties);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("数据字典combobox查询失败");
		}
		return jsonString;
	}
	
	
}
