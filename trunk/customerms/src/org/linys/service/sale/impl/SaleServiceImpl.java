package org.linys.service.sale.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.linys.dao.sale.SaleDAO;
import org.linys.model.sale.Sale;
import org.linys.service.sale.SaleService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @description:消费Service实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class SaleServiceImpl implements SaleService {
	@Resource
	private SaleDAO saleDAO;

	public ServiceResult save(Sale model) {
		ServiceResult result = new ServiceResult(false);
		if(model.getSaleId()==null){//新增
			saleDAO.insert(model);
		}else{
			saleDAO.update(model);
		}
		result.getData().put("saleId", model.getSaleId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(Integer page, Integer rows, Sale model) {
		List<Sale> list = saleDAO.query(page,rows,model);
		Long total = saleDAO.count(model);
		String[] properties = {"saleId","saleDate","customerId","sourceAmount","discount","amount","userId","userName","customerName"};
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
			saleDAO.delete(id);
			haveDelete = true;
		}
		if(!haveDelete){
			result.setMessage("没有可删除的消费");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}
	
	
}
