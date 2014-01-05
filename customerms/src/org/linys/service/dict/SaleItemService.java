package org.linys.service.dict;

import org.linys.model.dict.SaleItem;
import org.linys.vo.ServiceResult;

/**
 * @description:消费项目Service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface SaleItemService {
	/**
	 * @description: 保存消费项目
	 * @created: 2014-1-3 下午7:42:11
	 * @author 以宋
	 * @param model
	 * @return
	 */
	ServiceResult save(SaleItem model);
	/**
	 * @description: 分页查询消费项目
	 * @created: 2014-1-3 下午7:42:24
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	String query(Integer page, Integer rows, SaleItem model);
	/**
	 * @description: 批量消费项目删除
	 * @created: 2014-1-3 下午7:42:44
	 * @author 以宋
	 * @param ids
	 * @return
	 */
	ServiceResult mulDelete(String ids);
	/**
	 * @description: 添加查询消费项目
	 * @created: 2014-1-4 下午4:45:39
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @param ids 
	 * @return
	 */
	String querySelect(Integer page, Integer rows, SaleItem model, String ids);

}
