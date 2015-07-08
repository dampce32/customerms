package org.linys.service.countCard;

import org.linys.model.countCard.CountCard;
import org.linys.vo.ServiceResult;

public interface CountCardService {

	/***
	 * 保存计次卡
	 */
	ServiceResult save(CountCard model);
	
	/***
	 * 分页查询计次卡
	 */
	String query(CountCard model);
	
	/**
	 * @description: 批量计次卡删除
	 */
	ServiceResult mulDelete(String ids);
}
