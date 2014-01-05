package org.linys.service.dict;

import org.linys.model.dict.Goods;
import org.linys.vo.ServiceResult;

/**
 * @description:产品Service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface GoodsService {
	/**
	 * @description: 保存产品
	 * @created: 2014-1-3 下午7:42:11
	 * @author 以宋
	 * @param model
	 * @return
	 */
	ServiceResult save(Goods model);
	/**
	 * @description: 分页查询产品
	 * @created: 2014-1-3 下午7:42:24
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	String query(Integer page, Integer rows, Goods model);
	/**
	 * @description: 批量产品删除
	 * @created: 2014-1-3 下午7:42:44
	 * @author 以宋
	 * @param ids
	 * @return
	 */
	ServiceResult mulDelete(String ids);
	/**
	 * @description: 添加查询消费产品
	 * @created: 2014-1-5 上午12:47:17
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @param ids
	 * @return
	 */
	String querySelect(Integer page, Integer rows, Goods model, String ids);

}
