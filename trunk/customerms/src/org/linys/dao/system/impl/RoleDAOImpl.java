package org.linys.dao.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.system.RoleDAO;
import org.linys.model.system.Role;
import org.linys.util.PageUtil;
import org.springframework.stereotype.Repository;
/**
 * @Description:角色DAO实现类
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
@Repository
public class RoleDAOImpl  implements RoleDAO {
	@Resource
	private SqlSession sqlSession;
	
	public Role loadByRoleCode(String roleCode) {
		return (Role) sqlSession.selectOne("RoleMapper.loadByRoleCode",roleCode);
	}
	public Integer getMaxArray() {
		return (Integer) sqlSession.selectOne("RoleMapper.getMaxArray");
	}
	public void insert(Role model) {
		sqlSession.insert("RoleMapper.insert",model);
	}
	
	public void update(Role model) {
		sqlSession.insert("RoleMapper.update",model);
	}
	@SuppressWarnings("unchecked")
	public List<Role> query(Integer page, Integer rows, Role model) {
		return sqlSession.selectList("RoleMapper.query", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}
	
	public Long count(Role model) {
		return (Long) sqlSession.selectOne("RoleMapper.count",model);
	}
	
	public Role loadByRoleId(Integer roleId) {
		return (Role) sqlSession.selectOne("RoleMapper.loadByRoleId",roleId);
	}
	
	public void delete(Integer roleId) {
		sqlSession.delete("RoleMapper.delete",roleId);
	}
	
	public void updateStatus(Integer roleId, Integer status) {
		Role role = new Role();
		role.setRoleId(roleId);
		role.setStatus(status);
		sqlSession.update("RoleMapper.updateStatus",role);
	}
	public void updateArray(Integer roleId, Integer updateRoleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		map.put("updateRoleId", updateRoleId);
		sqlSession.update("RoleMapper.updateArray",map);
	}
}
