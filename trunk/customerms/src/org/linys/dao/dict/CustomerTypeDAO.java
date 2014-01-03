package org.linys.dao.dict;

import java.util.List;

import org.linys.model.dict.CustomerType;

/**
 * @description:会员类型DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface CustomerTypeDAO {
	/**
	 * @description: 根据会员类型名称查询会员类型
	 * @created: 2014-1-3 下午7:50:14
	 * @author 以宋
	 * @param customerTypeName
	 * @return
	 */
	CustomerType loadByCustomerTypeName(String customerTypeName);
	/**
	 * @description: 插入会员类型
	 * @created: 2014-1-3 下午7:51:04
	 * @author 以宋
	 * @param model
	 */
	void insert(CustomerType model);
	/**
	 * @description: 更新会员类型
	 * @created: 2014-1-3 下午7:51:28
	 * @author 以宋
	 * @param model
	 */
	void update(CustomerType model);
	/**
	 * @description: 分页查询会员类型
	 * @created: 2014-1-3 下午7:51:50
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<CustomerType> query(Integer page, Integer rows, CustomerType model);
	/**
	 * @description: 统计会员类型
	 * @created: 2014-1-3 下午7:52:20
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Long count(CustomerType model);
	/**
	 * @description: 删除会员类型
	 * @created: 2014-1-3 下午7:52:46
	 * @author 以宋
	 * @param customerTypeId
	 */
	void delete(Integer customerTypeId);

}
