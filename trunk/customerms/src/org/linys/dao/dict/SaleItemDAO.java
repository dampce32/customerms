package org.linys.dao.dict;

import java.util.List;

import org.linys.model.dict.SaleItem;

/**
 * @description:消费项目DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface SaleItemDAO {
	/**
	 * @description: 根据消费项目名称查询消费项目
	 * @created: 2014-1-3 下午7:50:14
	 * @author 以宋
	 * @param saleItemName
	 * @return
	 */
	SaleItem loadBySaleItemName(String saleItemName);
	/**
	 * @description: 插入消费项目
	 * @created: 2014-1-3 下午7:51:04
	 * @author 以宋
	 * @param model
	 */
	void insert(SaleItem model);
	/**
	 * @description: 更新消费项目
	 * @created: 2014-1-3 下午7:51:28
	 * @author 以宋
	 * @param model
	 */
	void update(SaleItem model);
	/**
	 * @description: 分页查询消费项目
	 * @created: 2014-1-3 下午7:51:50
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<SaleItem> query(Integer page, Integer rows, SaleItem model);
	/**
	 * @description: 统计消费项目
	 * @created: 2014-1-3 下午7:52:20
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Long count(SaleItem model);
	/**
	 * @description: 删除消费项目
	 * @created: 2014-1-3 下午7:52:46
	 * @author 以宋
	 * @param saleItemId
	 */
	void delete(Integer saleItemId);
	/**
	 * @description: 添加查询消费项目
	 * @created: 2014-1-4 下午4:47:09
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<SaleItem> querySelect(Integer page, Integer rows, SaleItem model);
	/**
	 * @description: 统计添加查询消费项目
	 * @created: 2014-1-4 下午4:47:50
	 * @author 以宋
	 * @param model
	 * @param ids
	 * @return
	 */
	Long countSelect(SaleItem model);

}
