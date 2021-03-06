package org.linys.service.customer.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.linys.dao.customer.CustomerRechargeDAO;
import org.linys.model.customer.CustomerRecharge;
import org.linys.service.customer.CustomerRechargeService;
import org.linys.util.DateUtil;
import org.linys.util.JSONUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;

/**
 * @description:会员充值Service实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class CustomerRechargeServiceImpl implements CustomerRechargeService {
	@Resource
	private CustomerRechargeDAO customerRechargeDAO;

	public ServiceResult save(CustomerRecharge model) {
		ServiceResult result = new ServiceResult(false);
		model.setRechargeDate(DateUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:sss"));
		customerRechargeDAO.insert(model);
		result.getData().put("customerRechargeId", model.getCustomerRechargeId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(Integer page, Integer rows, CustomerRecharge model) {
		List<CustomerRecharge> list = customerRechargeDAO.query(page,rows,model);
		Long total = customerRechargeDAO.count(model);
		String[] properties = {"customerRechargeId","rechargeDate","amount"};
		return JSONUtil.toJson(list, properties, total);
	}
}
