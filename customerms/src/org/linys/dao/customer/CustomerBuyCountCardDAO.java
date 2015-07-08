package org.linys.dao.customer;

import java.util.List;

import org.linys.annotation.MyBatisRepository;
import org.linys.model.countCard.CountCard;
import org.linys.model.customer.CustomerBuyCountCard;

/***
 * 购买计次卡
 * @author wxy530
 *
 */
@MyBatisRepository
public interface CustomerBuyCountCardDAO {
	
	/***
	 * 插入会员购买计次卡
	 * @param model
	 */
	public void insert(CustomerBuyCountCard model);
	
	/**
	 * 查询该会员购买的计次卡
	 * @param model
	 * @return
	 */
	public List<CountCard> query(CustomerBuyCountCard model);
	
	/**
	 * 统计查询该会员购买的计次卡历史
	 * @param model
	 * @return
	 */
	public Long count(CustomerBuyCountCard model);
	
	/**
	 * 会员购买计次卡
	 * @return
	 */
	public List<CustomerBuyCountCard> queryCustomerBuyCountCard(CustomerBuyCountCard model);
	
	/**
	 * 查询会员购买计次卡总条数
	 * @return
	 */
	public Long countCustomerBuyCountCard();
	
}
