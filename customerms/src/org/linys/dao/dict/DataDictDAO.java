package org.linys.dao.dict;

import java.util.List;

import org.linys.model.dict.DataDict;

/**
 * @description:数据字典DAO
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public interface DataDictDAO {
	/**
	 * @description: 根据数据字典名称查询数据字典
	 * @created: 2014-1-3 下午7:50:14
	 * @author 以宋
	 * @param dataDictName
	 * @return
	 */
	DataDict loadByDataDictName(String dataDictName);
	/**
	 * @description: 插入数据字典
	 * @created: 2014-1-3 下午7:51:04
	 * @author 以宋
	 * @param model
	 */
	void insert(DataDict model);
	/**
	 * @description: 更新数据字典
	 * @created: 2014-1-3 下午7:51:28
	 * @author 以宋
	 * @param model
	 */
	void update(DataDict model);
	/**
	 * @description: 分页查询数据字典
	 * @created: 2014-1-3 下午7:51:50
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<DataDict> query(Integer page, Integer rows, DataDict model);
	/**
	 * @description: 统计数据字典
	 * @created: 2014-1-3 下午7:52:20
	 * @author 以宋
	 * @param model
	 * @return
	 */
	Long count(DataDict model);
	/**
	 * @description: 删除数据字典
	 * @created: 2014-1-3 下午7:52:46
	 * @author 以宋
	 * @param dataDictId
	 */
	void delete(Integer dataDictId);
	/**
	 * @description: 添加查询数据字典
	 * @created: 2014-1-4 下午4:47:09
	 * @author 以宋
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<DataDict> querySelect(Integer page, Integer rows, DataDict model);
	/**
	 * @description: 统计添加查询数据字典
	 * @created: 2014-1-4 下午4:47:50
	 * @author 以宋
	 * @param model
	 * @param ids
	 * @return
	 */
	Long countSelect(DataDict model);
	/**
	 * @Description: 根据数据字典类型+数据字典名称查询数据字典
	 * @Created: 2015-1-9 下午11:11:46
	 * @author lys
	 * @param dataDictType
	 * @param dataDictName
	 * @return
	 */
	DataDict loadByTypeAndName(String dataDictType, String dataDictName);
	/**
	 * @Description: combobox查询
	 * @Created: 2015-1-10 上午9:06:58
	 * @author lys
	 * @param model
	 * @return
	 */
	List<DataDict> queryCombobox(DataDict model);

}
