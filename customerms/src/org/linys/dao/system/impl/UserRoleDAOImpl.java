package org.linys.dao.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.linys.dao.system.UserRoleDAO;
import org.linys.model.system.Role;
import org.linys.model.system.UserRole;
import org.springframework.stereotype.Repository;
/**
 * @description:用户角色DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-2
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class UserRoleDAOImpl implements UserRoleDAO {
	@Resource
	private SqlSession sqlSession;
	
	@SuppressWarnings("unchecked")
	public List<Role> queryRole(UserRole model) {
		return sqlSession.selectList("UserRoleMapper.queryRole", model);
	}

	public void delete(UserRole userRole) {
		sqlSession.delete("UserRoleMapper.delete", userRole);
	}

	public void insert(UserRole userRole) {
		sqlSession.insert("UserRoleMapper.insert", userRole);
	}

}
