package org.linys.dao.customer;

import java.util.List;

import org.linys.model.customer.Customer;
import org.linys.model.sale.Sale;

/**
 * @description:会员DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface CustomerDAO {
	/**
	 * @description: 根据会员名称查询会员
	 * @created: 2014-1-3 下午7:50:14
	 * @author 以宋
	 * @param customerName
	 * @return
	 */
	Customer loadByCustomerName(String customerName);
	/**
	 * @description: 插入会员
	 * @created: 2014-1-3 下午7:51:04
	 * @author 以宋
	 * @param model
	 */
	void insert(Customer model);
	/**
	 * @description: 更新会员
	 * @created: 2014-1-3 下午7:51:28
	 * @author 以宋
	 * @param model
	 */
	void update(Customer model);
	/**
	 * @description: 分页查询会员
	 * @created: 2014-1-3 下午7:51:50
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<Customer> query(Integer page, Integer rows, Customer model);
	/**
	 * @description: 统计会员
	 * @created: 2014-1-3 下午7:52:20
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Long count(Customer model);
	/**
	 * @description: 删除会员
	 * @created: 2014-1-3 下午7:52:46
	 * @author 以宋
	 * @param customerId
	 */
	void delete(Integer customerId);
	/**
	 * @description: 根据会员属性查询会员
	 * @created: 2014-1-4 上午2:16:31
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Customer load(Customer model);
	/**
	 * @description: 消费更新用户账号信息
	 * @created: 2014-1-8 下午8:49:34
	 * @author 以宋
	 * @param model
	 */
	void saleUpdate(Sale model);
	/**
	 * @description: 消费删除更新会员的会员卡金额
	 * @created: 2014-1-10 下午8:20:31
	 * @author 以宋
	 * @param sale
	 */
	void saleDeleteUpdate(Sale sale);

}
