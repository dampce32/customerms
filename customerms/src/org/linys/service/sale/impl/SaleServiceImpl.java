package org.linys.service.sale.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.linys.dao.customer.CustomerDAO;
import org.linys.dao.sale.SaleDAO;
import org.linys.dao.sale.SaleGoodsDetailDAO;
import org.linys.dao.sale.SaleItemDetailDAO;
import org.linys.model.customer.Customer;
import org.linys.model.sale.Sale;
import org.linys.model.sale.SaleGoodsDetail;
import org.linys.model.sale.SaleItemDetail;
import org.linys.service.sale.SaleService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @description:消费Service实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class SaleServiceImpl implements SaleService {
	@Resource
	private SaleDAO saleDAO;
	@Resource
	private SaleItemDetailDAO saleItemDetailDAO;
	@Resource
	private SaleGoodsDetailDAO saleGoodsDetailDAO;
	@Resource
	private CustomerDAO customerDAO;
	

	public ServiceResult save(Sale model, String saleItemDetailIds, String saleItemIds, String amounts, String isDiscounts, String userIds, String delSaleItemDetailIds,
			String saleGoodsDetailIds, String goodsIds, String amountsGoods, String isDiscountsGoods, String userIdsGoods, String delSaleGoodsDetailIds) {
		ServiceResult result = new ServiceResult(false);
		//消费项目
//		Integer[] saleItemDetailIdArray = StringUtil.splitToInteger(saleItemDetailIds);
		Integer[] saleItemIdArray = StringUtil.splitToInteger(saleItemIds);
		Float[] amountArray = StringUtil.splitToFloat(amounts);
		Integer[] isDiscountArray = StringUtil.splitToInteger(isDiscounts);
		Integer[] userIdArray = StringUtil.splitToInteger(userIds);
		//消费产品
//		Integer[] saleGoodsDetailIdArray = StringUtil.splitToInteger(saleGoodsDetailIds);
		Integer[] goodsIdArray = StringUtil.splitToInteger(goodsIds);
		Float[] amountsGoodArray = StringUtil.splitToFloat(amountsGoods);
		Integer[] isDiscountsGoodArray = StringUtil.splitToInteger(isDiscountsGoods);
		Integer[] userIdsGoodArray = StringUtil.splitToInteger(userIdsGoods);
//		Integer[] delSaleGoodsDetailIdArray = StringUtil.splitToInteger(delSaleGoodsDetailIds);
		Customer customer = new Customer();
		customer.setCustomerId(model.getCustomerId());
		customer=customerDAO.load(customer);
		
		if(model.getSaleId()==null){//新增
			if(model.getPayByCash()==null){
				model.setPayByCash(0f);	
			}
			model.setPayByCard(model.getAmount()-model.getPayByCash());//会员卡付款
			model.setBalance(customer.getAmount()-model.getAmount()+model.getPayByCash());//消费后的会员卡余额
			saleDAO.insert(model);
			//消费项目
			for (int i = 0; i < saleItemIdArray.length; i++) {
				Integer saleItemId = saleItemIdArray[i];
				Float amount = amountArray[i];
				Integer isDiscount = isDiscountArray[i];
				Integer userId = userIdArray[i];
				SaleItemDetail saleItemDetail = new SaleItemDetail();
				saleItemDetail.setSaleId(model.getSaleId());
				saleItemDetail.setSaleItemId(saleItemId);
				saleItemDetail.setAmount(amount);
				saleItemDetail.setIsDiscount(isDiscount);
				saleItemDetail.setIsDiscount(isDiscount);
				saleItemDetail.setUserId(userId);
				saleItemDetailDAO.insert(saleItemDetail);
			}
			//消费产品
			for (int i = 0; i < goodsIdArray.length; i++) {
				Integer goodsId = goodsIdArray[i];
				Float amount = amountsGoodArray[i];
				Integer isDiscount = isDiscountsGoodArray[i];
				Integer userId = userIdsGoodArray[i];
				SaleGoodsDetail saleGoodsDetail = new SaleGoodsDetail();
				saleGoodsDetail.setSaleId(model.getSaleId());
				saleGoodsDetail.setGoodsId(goodsId);
				saleGoodsDetail.setAmount(amount);
				saleGoodsDetail.setIsDiscount(isDiscount);
				saleGoodsDetail.setIsDiscount(isDiscount);
				saleGoodsDetail.setUserId(userId);
				saleGoodsDetailDAO.insert(saleGoodsDetail);
			}
			//更新会员的账号信息
			if(customer.getAmount()>=model.getAmount()){//帐号的钱够付
				model.setAmount(customer.getAmount()-model.getAmount());
			}else{
				model.setAmount(customer.getAmount()-model.getAmount()+model.getPayByCash());
			}
			customerDAO.saleUpdate(model);
		}else{
			saleDAO.update(model);
		}
		result.getData().put("saleId", model.getSaleId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(Sale model) {
		List<Sale> list = saleDAO.query(model);
		Long total = saleDAO.count(model);
		String[] properties = {"saleId","saleDate","customerId","notIntoDiscountAmount","intoDiscountAmount","discount","amount","balance","payByCash","payByCard","userId","userName","customerName"};
		return JSONUtil.toJson(list, properties, total);
	}

	public ServiceResult mulDelete(String ids) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(ids)){
			result.setMessage("请选择要删除的记录");
			return result;
		}
		Integer[] idArray = StringUtil.splitToInteger(ids);
		if(idArray.length==0){
			result.setMessage("请选择要删除的记录");
			return result;
		}
		boolean haveDelete = false;
		for (Integer id : idArray) {
			//将订单表中的消费金额加入到客户账号中
			Sale sale = new Sale();
			sale.setSaleId(id);
			sale =	saleDAO.loadDelete(sale);
			
			customerDAO.saleDeleteUpdate(sale);
			
			saleDAO.delete(id);
			haveDelete = true;
		}
		if(!haveDelete){
			result.setMessage("没有可删除的消费");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}

	public ServiceResult init(Sale model) {
		ServiceResult result = new ServiceResult(false);
		if(model==null||model.getSaleId()==null){
			result.setMessage("请选择打开的消费单");
			return result;
		}
		Sale sale = saleDAO.load(model);
		String[] properties ={"saleId","saleDate","customerId","notIntoDiscountAmount","intoDiscountAmount","discount","amount","userId","userName","customerName","customerAmount"};
		result.addData("saleData", JSONUtil.toJson(sale, properties));
		
		//查找消费单下的消费项目明细
		List<SaleItemDetail> saleItemDetailList = saleItemDetailDAO.queryBySale(model);
		String[] propertiesSaleItemDetail ={"saleItemDetailId","saleItemId","saleItemName","amount","userId","userName","isDiscount"};
		result.addData("saleItemDetailData", JSONUtil.toJson(saleItemDetailList, propertiesSaleItemDetail));
		
		//查找消费单下的消费产品明细
		List<SaleGoodsDetail> saleGoodsDetailList = saleGoodsDetailDAO.queryBySale(model);
		String[] propertiesSaleGoodsDetail ={"saleGoodsDetailId","goodsId","goodsName","amount","userId","userName","isDiscount"};
		result.addData("saleGoodsDetailData", JSONUtil.toJson(saleGoodsDetailList, propertiesSaleGoodsDetail));
		
		result.setIsSuccess(true);
		return result;
	}
	
	
}
