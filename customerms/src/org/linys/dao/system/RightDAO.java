package org.linys.dao.system;

import java.util.List;

import org.linys.model.system.Right;
/**
 * @Description:权限DAO
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-16
 * @Author lys
 */
public interface RightDAO {
	/**
	 * @Description: 取得跟权限
	 * @Created Time: 2013-4-16 下午3:07:02
	 * @Author lys
	 * @return
	 */
	Right getRootTreeNode();
	/**
	 * @Description: 取得权限下的子权限
	 * @Created Time: 2013-4-16 下午3:07:24
	 * @Author lys
	 * @param right
	 * @return
	 */
	List<Right> getChildrenTreeNode(Right right);
	/**
	 * @Description: 查询节点下的子节点权限列表
	 * @Created Time: 2013-4-16 下午3:16:11
	 * @Author lys
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	List<Right> queryChildren(Integer page, Integer rows, Right model);
	/**
	 * @Description: 统计节点下的子节点权限列表
	 * @Created Time: 2013-4-16 下午3:16:34
	 * @Author lys
	 * @param model
	 * @return
	 */
	Long countChildren(Right model);
	/**
	 * @Description: 更新权限是否叶子状态
	 * @Created Time: 2013-4-16 下午4:17:03
	 * @Author lys
	 * @param rightId
	 * @param b
	 */
	void updateIsLeaf(Integer rightId, Integer isLeaf);
	/**
	 * @Description: 根据拖拽目标节点和与目标节点的位置关系，改变与目标节点同父节点的排序值
	 * @Created: 2013-8-6 下午3:59:05
	 * @Author lys
	 * @param targetRight
	 * @param point
	 */
	void dropUpdateArray(Right targetRight, String point);
	/**
	 * @Description: 根据父权限主键编号查询子节点
	 * @Create: 2013-9-30 下午5:39:36
	 * @author lys
	 * @update logs
	 * @param rightPKCode
	 * @return
	 */
	List<Right> queryChildrenListByParentRightPKCode(String rightPKCode);
	/**
	 * @description: 根据属性权限编号查询权限
	 * @created: 2014-1-1 上午11:23:02
	 * @author 以宋
	 * @param property
	 * @param value
	 * @return
	 */
	Right loadByRightCode(String rightCode);
	/**
	 * @description: 取得属性rightPKCode的最大值
	 * @created: 2014-1-1 下午12:17:51
	 * @author 以宋
	 * @param string
	 * @param parentRightId
	 * @param string2
	 * @return
	 */
	String getMaxRightPKCode(Integer parentRightId);
	/**
	 * @description: 根据权限Id取得权限
	 * @created: 2014-1-1 下午1:27:27
	 * @author 以宋
	 * @param parentRightId
	 * @return
	 */
	Right loadByRightId(Integer rightId);
	/**
	 * @description: 取得父节点parentRightId下的最大排序值
	 * @created: 2014-1-1 下午1:28:49
	 * @author 以宋
	 * @param parentRightId
	 * @return
	 */
	Integer getMaxArray(Integer parentRightId);
	/**
	 * @description: 保存权限
	 * @created: 2014-1-1 下午1:34:03
	 * @author 以宋
	 * @param model
	 */
	void insert(Right model);
	/**
	 * @description: 更新权限
	 * @created: 2014-1-1 下午2:03:41
	 * @author 以宋
	 * @param model
	 */
	void update(Right model);
	/**
	 * @description: 
	 * @created: 2014-1-1 下午2:28:05
	 * @author 以宋
	 * @param id
	 */
	void delete(Integer rightId);
	/**
	 * @description: 统计权限parentRightId下孩子的个数
	 * @created: 2014-1-1 下午2:35:01
	 * @author 以宋
	 * @param parentId
	 * @return
	 */
	Long countChildrenByParentRightId(Integer parentRightId);
	/**
	 * @description: 更新权限rightId的状态
	 * @created: 2014-1-1 下午3:11:38
	 * @author 以宋
	 * @param rightId
	 * @param status
	 */
	void updateStatus(Integer rightId, Integer status);
	/**
	 * @description: 拖拽更新
	 * @created: 2014-1-1 下午4:51:57
	 * @author 以宋
	 * @param sourceRight
	 */
	void updateDragChange(Right right);
	/**
	 * @description: 取得
	 * @created: 2014-1-1 下午10:42:06
	 * @author 以宋
	 * @param rightId
	 * @return
	 */
	Right getParentRight(Integer rightId);
	
}
