package org.linys.service.dict;

import org.linys.model.dict.DataDict;
import org.linys.vo.ServiceResult;

/**
 * @description:数据字典Service
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface DataDictService {
	/**
	 * @description: 保存数据字典
	 * @created: 2014-1-3 下午7:42:11
	 * @author 以宋
	 * @param model
	 * @return
	 */
	ServiceResult save(DataDict model);
	/**
	 * @description: 分页查询数据字典
	 * @created: 2014-1-3 下午7:42:24
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	String query(Integer page, Integer rows, DataDict model);
	/**
	 * @description: 批量数据字典删除
	 * @created: 2014-1-3 下午7:42:44
	 * @author 以宋
	 * @param ids
	 * @return
	 */
	ServiceResult mulDelete(String ids);
	/**
	 * @description: 添加查询数据字典
	 * @created: 2014-1-4 下午4:45:39
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @param ids 
	 * @return
	 */
	String querySelect(Integer page, Integer rows, DataDict model, String ids);
	/**
	 * @Description: combobox查询
	 * @Created: 2015-1-10 上午9:04:54
	 * @author lys
	 * @param model
	 * @return
	 */
	String queryCombobox(DataDict model);

}
