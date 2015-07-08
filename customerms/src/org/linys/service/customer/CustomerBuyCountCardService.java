package org.linys.service.customer;

import org.linys.model.customer.CustomerBuyCountCard;
import org.linys.vo.ServiceResult;

public interface CustomerBuyCountCardService {
	
	/**
	 * 保存会员购买计次卡
	 */
	public ServiceResult save(CustomerBuyCountCard model);
	
	public String query(CustomerBuyCountCard model);
	
	/**
	 * 会员购买计次卡
	 * @return
	 */
	public String queryCustomerBuyCountCard(CustomerBuyCountCard model);
	
}
