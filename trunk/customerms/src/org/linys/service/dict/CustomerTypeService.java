package org.linys.service.dict;

import org.linys.model.dict.CustomerType;
import org.linys.vo.ServiceResult;

/**
 * @description:会员类型Service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface CustomerTypeService {
	/**
	 * @description: 保存会员类型
	 * @created: 2014-1-3 下午7:42:11
	 * @author 以宋
	 * @param model
	 * @return
	 */
	ServiceResult save(CustomerType model);
	/**
	 * @description: 分页查询会员类型
	 * @created: 2014-1-3 下午7:42:24
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	String query(Integer page, Integer rows, CustomerType model);
	/**
	 * @description: 批量会员类型删除
	 * @created: 2014-1-3 下午7:42:44
	 * @author 以宋
	 * @param ids
	 * @return
	 */
	ServiceResult mulDelete(String ids);

}
