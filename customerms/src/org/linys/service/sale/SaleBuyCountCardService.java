package org.linys.service.sale;

import org.linys.model.sale.SaleBuyCountCard;
import org.linys.vo.ServiceResult;

public interface SaleBuyCountCardService {

	/**
	 * 分页查询会员计次卡消费记录
	 * @param model
	 * @return
	 */
	public String query(SaleBuyCountCard model);
	
	/**
	 * 保存会员计次卡消费记录
	 * @param model
	 * @return
	 */
	public ServiceResult save(SaleBuyCountCard model);
	
	/**
	 * 更新会员已消费次数
	 * @param model
	 */
	public void update(SaleBuyCountCard model);
	
	/**
	 * 删除会员计次卡消费记录
	 * @param model
	 * @return
	 */
	public ServiceResult delete(Integer saleBuyCountCardId);
}
