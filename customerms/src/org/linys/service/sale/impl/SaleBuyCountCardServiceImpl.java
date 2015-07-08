package org.linys.service.sale.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.linys.dao.sale.SaleBuyCountCardDAO;
import org.linys.model.sale.SaleBuyCountCard;
import org.linys.service.sale.SaleBuyCountCardService;
import org.linys.util.DateUtil;
import org.linys.util.JSONUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;

/**
 * 计次卡消费管理ServiceImpl
 * @author wxy530
 *
 */
@Service
public class SaleBuyCountCardServiceImpl implements SaleBuyCountCardService{

	@Resource
	private SaleBuyCountCardDAO saleBuyCountCardDAO;
	
	public String query(SaleBuyCountCard model) {
		List<SaleBuyCountCard> list = saleBuyCountCardDAO.query(model);
		Long total = saleBuyCountCardDAO.count(model);
		String[] properties = {"saleBuyCountCardId","customerBuyCountCardId","saleDate","saleCount",
	                           "canCount", "customerCode","customerName","amount",
	                           "canSaleCount","countCardTypeName","userName"};
		return JSONUtil.toJson(list,properties,total);
	}

	public ServiceResult save(SaleBuyCountCard model) {
		ServiceResult result = new ServiceResult(false);
		model.setSaleDate(DateUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:sss"));
		saleBuyCountCardDAO.insert(model);
		result.getData().put("saleBuyCountCardId", model.getSaleBuyCountCardId());
		result.setIsSuccess(true);
		return result;
	}

	public void update(SaleBuyCountCard model) {
		saleBuyCountCardDAO.update(model);
		
	}
	
	public ServiceResult delete(Integer saleBuyCountCardId) {
		ServiceResult result = new ServiceResult(false);
		saleBuyCountCardDAO.delete(saleBuyCountCardId);
		result.getData().put("saleBuyCountCardId",saleBuyCountCardId);
		result.setIsSuccess(true);
		return result;
	}

}
