package org.linys.service.customer;

import org.linys.model.customer.CustomerRecharge;
import org.linys.vo.ServiceResult;

/**
 * @description:会员充值Service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface CustomerRechargeService {
	/**
	 * @description: 保存会员充值
	 * @created: 2014-1-3 下午7:42:11
	 * @author 以宋
	 * @param model
	 * @return
	 */
	ServiceResult save(CustomerRecharge model);

}
