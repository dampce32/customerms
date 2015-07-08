package org.linys.service.customer.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.linys.dao.customer.CustomerBuyCountCardDAO;
import org.linys.model.countCard.CountCard;
import org.linys.model.customer.CustomerBuyCountCard;
import org.linys.service.customer.CustomerBuyCountCardService;
import org.linys.util.DateUtil;
import org.linys.util.JSONUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;

/**
 * 会员购买计次卡Service
 * @author wxy530
 *
 */
@Service
public class CustomerBuyCountCardServiceImpl implements CustomerBuyCountCardService{
	
	@Resource
	private CustomerBuyCountCardDAO customerBuyCountCardDAO;

	public ServiceResult save(CustomerBuyCountCard model) {
		ServiceResult result = new ServiceResult(false);
		model.setBuyCountCardDate(DateUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:sss"));
		model.setAlreadySaleCount(0);
		customerBuyCountCardDAO.insert(model);
		result.getData().put("customerBuyCountCardId", model.getCustomerBuyCountCardId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(CustomerBuyCountCard model) {
		List<CountCard> list = customerBuyCountCardDAO.query(model);
		Long total = customerBuyCountCardDAO.count(model);
		String[] properties = {"buyCountCardDate","countCardTypeName","amount:level","canSaleCount","canCount"};
		return JSONUtil.toJson(list, properties, total);
	}

	public String queryCustomerBuyCountCard(CustomerBuyCountCard model) {
		List<CustomerBuyCountCard> list = customerBuyCountCardDAO.queryCustomerBuyCountCard(model);
		Long total = customerBuyCountCardDAO.countCustomerBuyCountCard();
		String[] properties = {"customerBuyCountCardId","countCardTypeName","amount", "canSaleCount","canCount",
			                   "alreadySaleCount","customerCode","customerName"};
		return JSONUtil.toJson(list, properties, total);
	}

}
