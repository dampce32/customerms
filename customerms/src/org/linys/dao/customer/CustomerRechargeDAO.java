package org.linys.dao.customer;

import java.util.List;

import org.linys.model.customer.CustomerRecharge;

/**
 * @description:会员充值DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface CustomerRechargeDAO {
	/**
	 * @description: 插入会员充值
	 * @created: 2014-1-3 下午7:51:04
	 * @author 以宋
	 * @param model
	 */
	void insert(CustomerRecharge model);
	/**
	 * @description: 分页查询会员充值历史
	 * @created: 2014-1-10 下午10:04:01
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<CustomerRecharge> query(Integer page, Integer rows,
			CustomerRecharge model);
	/**
	 * @description: 统计查询会员充值历史
	 * @created: 2014-1-10 下午10:04:44
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Long count(CustomerRecharge model);
}
