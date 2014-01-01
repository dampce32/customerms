package org.linys.dao.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.system.RightDAO;
import org.linys.model.system.Right;
import org.linys.util.PageUtil;
import org.springframework.stereotype.Repository;
/**
 * @Description:权限DAO实现类
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-16
 * @Author lys
 */
@Repository
public class RightDAOImpl implements RightDAO{
	@Resource
	private SqlSession sqlSession;
	
	public Right getRootTreeNode() {
		return (Right) sqlSession.selectOne("RightMapper.getRootTreeNode");
	}
	
	@SuppressWarnings("unchecked")
	public List<Right> getChildrenTreeNode(Right model) {
		return (List<Right>) sqlSession.selectList("RightMapper.getChildrenTreeNode",model);
	}
	
	public Right loadByRightCode(String rightCode) {
		return (Right) sqlSession.selectOne("RightMapper.loadByRightCode",rightCode);
	}

	public Right loadByRightId(Integer rightId) {
		return (Right) sqlSession.selectOne("RightMapper.loadByRightId",rightId);
	}

	public Integer getMaxArray(Integer parentRightId) {
		return (Integer) sqlSession.selectOne("RightMapper.getMaxArray",parentRightId);
	}
	
	public String getMaxRightPKCode(Integer parentRightId) {
		return (String) sqlSession.selectOne("RightMapper.getMaxRightPKCode",parentRightId);
	}
	
	public void insert(Right model) {
		sqlSession.insert("RightMapper.insert",model);
	}

	public void update(Right model) {
		sqlSession.update("RightMapper.update",model);
	}
	
	public void delete(Integer rightId) {
		sqlSession.delete("RightMapper.delete",rightId);
	}

	public void updateIsLeaf(Integer rightId, Integer isLeaf) {
		Right right = new Right();
		right.setRightId(rightId);
		right.setIsLeaf(isLeaf);
		sqlSession.update("RightMapper.updateIsLeaf",right);
	}

	@SuppressWarnings("unchecked")
	public List<Right> queryChildren(Integer page, Integer rows, Right model) {
		return sqlSession.selectList("RightMapper.queryChildren", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long countChildren(Right model) {
		return (Long) sqlSession.selectOne("RightMapper.countChildren",model);
	}
	public Long countChildrenByParentRightId(Integer parentRightId) {
		return (Long) sqlSession.selectOne("RightMapper.countChildrenByParentRightId",parentRightId);
	}
	
	public void updateStatus(Integer rightId, Integer status) {
		Right right = new Right();
		right.setRightId(rightId);
		right.setStatus(status);
		sqlSession.update("RightMapper.updateStatus",right);
	}

	public void updateDragChange(Right right) {
		sqlSession.update("RightMapper.updateDragChange",right);
	}
	@SuppressWarnings("unchecked")
	public List<Right> queryChildrenListByParentRightPKCode(String rightPKCode) {
		return (List<Right>) sqlSession.selectList("RightMapper.queryChildrenListByParentRightPKCode",rightPKCode);
	}
	public void dropUpdateArray(Right targetRight, String point) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentRightId", targetRight.getParentRightId());
		map.put("point", point);
		map.put("targetArray", targetRight.getArray());
 		sqlSession.update("RightMapper.dropUpdateArray",map);
	}

	public Right getParentRight(Integer rightId) {
		return (Right) sqlSession.selectOne("RightMapper.getParentRight",rightId);
	}
}
