package org.linys.dao.sale;

import java.util.List;

import org.linys.model.sale.Sale;
import org.linys.model.sale.SaleGoodsDetail;

/**
 * @description:消费产品明细DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface SaleGoodsDetailDAO {
	/**
	 * @description: 插入消费产品明细
	 * @created: 2014-1-3 下午7:51:04
	 * @author 以宋
	 * @param model
	 */
	void insert(SaleGoodsDetail model);
	/**
	 * @description: 更新消费产品明细
	 * @created: 2014-1-3 下午7:51:28
	 * @author 以宋
	 * @param model
	 */
	void update(SaleGoodsDetail model);
	/**
	 * @description: 根据消费单查找消费产品明细
	 * @created: 2014-1-4 下午9:03:49
	 * @author 以宋
	 * @param model
	 * @return
	 */
	List<SaleGoodsDetail> queryBySale(Sale model);
	/**
	 * @description: 删除多个消费产品明细
	 * @created: 2014-1-4 下午9:25:19
	 * @author 以宋
	 * @param delSaleGoodsDetailIdArray
	 */
	void deleteArray(Integer[] delSaleGoodsDetailIdArray);

}
