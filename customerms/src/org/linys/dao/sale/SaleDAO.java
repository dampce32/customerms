package org.linys.dao.sale;

import java.util.List;

import org.linys.model.sale.Sale;

/**
 * @description:消费DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface SaleDAO {
	/**
	 * @description: 根据消费名称查询消费
	 * @created: 2014-1-3 下午7:50:14
	 * @author 以宋
	 * @param saleName
	 * @return
	 */
	Sale loadBySaleName(String saleName);
	/**
	 * @description: 插入消费
	 * @created: 2014-1-3 下午7:51:04
	 * @author 以宋
	 * @param model
	 */
	void insert(Sale model);
	/**
	 * @description: 更新消费
	 * @created: 2014-1-3 下午7:51:28
	 * @author 以宋
	 * @param model
	 */
	void update(Sale model);
	/**
	 * @description: 分页查询消费
	 * @created: 2014-1-3 下午7:51:50
	 * @author 以宋
	 * @param model
	 * @return
	 */
	List<Sale> query(Sale model);
	/**
	 * @description: 统计消费
	 * @created: 2014-1-3 下午7:52:20
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Long count(Sale model);
	/**
	 * @description: 删除消费
	 * @created: 2014-1-3 下午7:52:46
	 * @author 以宋
	 * @param saleId
	 */
	void delete(Integer saleId);
	/**
	 * @description: 根据消费属性查询消费
	 * @created: 2014-1-4 上午2:16:31
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Sale load(Sale model);
	/**
	 * @description: 删除消费时查询消费
	 * @created: 2014-1-10 下午8:14:48
	 * @author 以宋
	 * @param sale
	 * @return
	 */
	Sale loadDelete(Sale sale);

}
