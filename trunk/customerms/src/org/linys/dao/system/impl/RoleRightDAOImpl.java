package org.linys.dao.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.linys.dao.system.RoleRightDAO;
import org.linys.model.system.Right;
import org.linys.model.system.RoleRight;
import org.springframework.stereotype.Repository;
/**
 * @description:角色权限DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-1
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class RoleRightDAOImpl implements RoleRightDAO {
	@Resource
	private SqlSession sqlSession;
	
	public Right getRootTreeNode(RoleRight model) {
		return (Right) sqlSession.selectOne("RoleRightMapper.getRootTreeNode",model);
	}

	@SuppressWarnings("unchecked")
	public List<Right> getChildrenTreeNode(RoleRight model) {
		return (List<Right>) sqlSession.selectList("RoleRightMapper.getChildrenTreeNode",model);
	}

	public void updateStatus(RoleRight model) {
		sqlSession.update("RoleRightMapper.updateStatus",model);
	}

	public RoleRight loadByRoleIdRightId(RoleRight model) {
		return (RoleRight) sqlSession.selectOne("RoleRightMapper.loadByRoleIdRightId",model);
	}

	public Integer countChildrenSameParent(RoleRight model) {
		return (Integer) sqlSession.selectOne("RoleRightMapper.countChildrenSameParent",model);
	}
}
