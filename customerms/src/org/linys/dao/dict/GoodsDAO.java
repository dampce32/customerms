package org.linys.dao.dict;

import java.util.List;

import org.linys.model.dict.Goods;

/**
 * @description:产品DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface GoodsDAO {
	/**
	 * @description: 根据产品名称查询产品
	 * @created: 2014-1-3 下午7:50:14
	 * @author 以宋
	 * @param goodsName
	 * @return
	 */
	Goods loadByGoodsName(String goodsName);
	/**
	 * @description: 插入产品
	 * @created: 2014-1-3 下午7:51:04
	 * @author 以宋
	 * @param model
	 */
	void insert(Goods model);
	/**
	 * @description: 更新产品
	 * @created: 2014-1-3 下午7:51:28
	 * @author 以宋
	 * @param model
	 */
	void update(Goods model);
	/**
	 * @description: 分页查询产品
	 * @created: 2014-1-3 下午7:51:50
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<Goods> query(Integer page, Integer rows, Goods model);
	/**
	 * @description: 统计产品
	 * @created: 2014-1-3 下午7:52:20
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Long count(Goods model);
	/**
	 * @description: 删除产品
	 * @created: 2014-1-3 下午7:52:46
	 * @author 以宋
	 * @param goodsId
	 */
	void delete(Integer goodsId);
	/**
	 * @description: 添加查询消费产品
	 * @created: 2014-1-5 上午12:49:31
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<Goods> querySelect(Integer page, Integer rows, Goods model);
	/**
	 * @description: 统计添加查询消费产品
	 * @created: 2014-1-5 上午12:49:58
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Long countSelect(Goods model);

}
