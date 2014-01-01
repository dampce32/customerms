package org.linys.service.system;

import java.util.List;

import org.linys.model.system.Right;
import org.linys.vo.ServiceResult;
/**
 * @Description:权限Service
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-16
 * @Author lys
 */
public interface RightService{
	/**
	 * @Description: 取得跟权限
	 * @Created Time: 2013-4-16 下午3:04:22
	 * @Author lys
	 * @return
	 */
	String getRootTreeNode();
	/**
	 * @Description: 取得子节点
	 * @Created Time: 2013-4-16 下午3:04:38
	 * @Author lys
	 * @param model
	 * @return
	 */
	List<Right> getChildrenTreeNode(Right model);
	/**
	 * @Description: 查询节点下的孩子节点
	 * @Created Time: 2013-4-16 下午3:11:08
	 * @Author lys
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	String queryChildren(Integer page, Integer rows, Right model);
	/**
	 * @Description: 保存权限
	 * @Created Time: 2013-4-16 下午4:12:49
	 * @Author lys
	 * @param model
	 * @param string 
	 * @return
	 */
	ServiceResult save(Right model);
	/**
	 * @Description: 批量删除权限
	 * @Created Time: 2013-4-16 下午5:09:05
	 * @Author lys
	 * @param ids
	 * @return
	 */
	ServiceResult mulDelete(String ids);
	/**
	 * @Description: 批量修改权限状态
	 * @Created Time: 2013-4-16 下午11:14:04
	 * @Author lys
	 * @param ids
	 * @param model
	 * @return
	 */
	ServiceResult mulUpdateStatus(String ids, Right model);
	/**
	 * @Description: 拖拽改变权限排序
	 * @Created: 2013-8-6 下午3:34:19
	 * @Author lys
	 * @param sourceId
	 * @param targetId
	 * @param point
	 * @return
	 */
	ServiceResult dropUpdateArray(String sourceId, String targetId, String point);
}
