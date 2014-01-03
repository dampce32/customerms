package org.linys.service.dict.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.linys.dao.dict.SaleItemDAO;
import org.linys.model.dict.SaleItem;
import org.linys.service.dict.SaleItemService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @description:消费项目Service实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class SaleItemServiceImpl implements SaleItemService {
	@Resource
	private SaleItemDAO saleItemDAO;

	public ServiceResult save(SaleItem model) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(model.getSaleItemName())){
			result.setMessage("请填写消费项目名称");
			return result;
		}
		
		SaleItem oldSaleItem = saleItemDAO.loadBySaleItemName(model.getSaleItemName());
		if(model.getSaleItemId()==null){//新增
			if(oldSaleItem!=null){
				result.setMessage("消费项目名称已存在，请重新输入消费项目名称");
				return result;
			}
			saleItemDAO.insert(model);
		}else{
			if(oldSaleItem!=null&&!oldSaleItem.getSaleItemId().equals(model.getSaleItemId())){
				result.setMessage("其他权限已使用该消费项目名称");
				return result;
			}
			saleItemDAO.update(model);
		}
		result.getData().put("saleItemId", model.getSaleItemId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(Integer page, Integer rows, SaleItem model) {
		List<SaleItem> list = saleItemDAO.query(page,rows,model);
		Long total = saleItemDAO.count(model);
		String[] properties = {"saleItemId","saleItemName","amount","isDiscount"};
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
			saleItemDAO.delete(id);
			haveDelete = true;
		}
		if(!haveDelete){
			result.setMessage("没有可删除的消费项目");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}
	
	
}
