package org.linys.service.customer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.linys.dao.customer.CustomerDAO;
import org.linys.model.customer.Customer;
import org.linys.service.customer.CustomerService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @description:会员Service实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	@Resource
	private CustomerDAO customerDAO;

	public ServiceResult save(Customer model) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(model.getCustomerCode())){
			result.setMessage("请填写会员号");
			return result;
		}
		if(StringUtils.isEmpty(model.getCustomerName())){
			result.setMessage("请填写会员名称");
			return result;
		}
		if(model.getCustomerTypeId()==null){
			result.setMessage("请填写会员类型");
			return result;
		}
		Customer oldCustomer = customerDAO.load(model);
		if(model.getCustomerId()==null){//新增
			if(oldCustomer!=null){
				result.setMessage("会员号已存在，请重新输入会员号");
				return result;
			}
			customerDAO.insert(model);
		}else{
			if(oldCustomer!=null&&!oldCustomer.getCustomerId().equals(model.getCustomerId())){
				result.setMessage("其他会员已使用该会员号");
				return result;
			}
			customerDAO.update(model);
		}
		result.getData().put("customerId", model.getCustomerId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(Integer page, Integer rows, Customer model) {
		List<Customer> list = customerDAO.query(page,rows,model);
		Long total = customerDAO.count(model);
		String[] properties = {"customerId","customerCode","customerName","customerTypeId","telephone","wechat"};
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
			customerDAO.delete(id);
			haveDelete = true;
		}
		if(!haveDelete){
			result.setMessage("没有可删除的会员");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}
	
	
}
