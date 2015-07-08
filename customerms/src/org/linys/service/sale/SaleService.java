package org.linys.service.sale;

import org.linys.model.sale.Sale;
import org.linys.vo.ServiceResult;

/**
 * @description:消费Service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface SaleService {
	/**
	 * @description: 保存消费
	 * @created: 2014-1-3 下午7:42:11
	 * @author 以宋
	 * @param model
	 * @param delSaleItemDetailIds 
	 * @param isDiscounts 
	 * @param amounts 
	 * @param saleItemIds 
	 * @param saleItemDetailIds 
	 * @param delSaleGoodsDetailIds 
	 * @param userIdsGoods 
	 * @param isDiscountsGoods 
	 * @param amountsGoods 
	 * @param goodsIds 
	 * @param saleGoodsDetailIds 
	 * @param delSaleItemDetailIds2 
	 * @return
	 */
	ServiceResult save(Sale model, String saleItemDetailIds, String saleItemIds, String amounts, String isDiscounts, String userIds, String delSaleItemDetailIds, 
			String saleGoodsDetailIds, String goodsIds, String amountsGoods, String isDiscountsGoods, String userIdsGoods, String delSaleGoodsDetailIds);
	/**
	 * @description: 分页查询消费
	 * @created: 2014-1-3 下午7:42:24
	 * @author 以宋
	 * @param model
	 * @return
	 */
	String query(Sale model);
	/**
	 * @description: 批量消费删除
	 * @created: 2014-1-3 下午7:42:44
	 * @author 以宋
	 * @param ids
	 * @return
	 */
	ServiceResult mulDelete(String ids);
	/**
	 * @description: 打开初始化
	 * @created: 2014-1-4 下午8:56:54
	 * @author 以宋
	 * @param model
	 * @return
	 */
	ServiceResult init(Sale model);

}
