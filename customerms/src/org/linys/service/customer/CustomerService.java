package org.linys.service.customer;

import org.linys.model.customer.Customer;
import org.linys.vo.ServiceResult;

/**
 * @description:会员Service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface CustomerService {
	/**
	 * @description: 保存会员
	 * @created: 2014-1-3 下午7:42:11
	 * @author 以宋
	 * @param model
	 * @return
	 */
	ServiceResult save(Customer model);
	/**
	 * @description: 分页查询会员
	 * @created: 2014-1-3 下午7:42:24
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	String query(Integer page, Integer rows, Customer model);
	/**
	 * @description: 批量会员删除
	 * @created: 2014-1-3 下午7:42:44
	 * @author 以宋
	 * @param ids
	 * @return
	 */
	ServiceResult mulDelete(String ids);

}
