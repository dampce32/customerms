package org.linys.dao.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.system.UserDAO;
import org.linys.model.system.Right;
import org.linys.model.system.User;
import org.linys.util.PageUtil;
import org.springframework.stereotype.Repository;
/**
 * @description:用户DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-2
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class UserDAOImpl implements UserDAO {
	@Resource
	private SqlSession sqlSession;
	
	public User loadByUserCode(String userCode) {
		return (User) sqlSession.selectOne("UserMapper.loadByUserCode",userCode);
	}

	public void insert(User model) {
		sqlSession.insert("UserMapper.insert",model);
	}

	public void update(User model) {
		sqlSession.update("UserMapper.update",model);
	}

	@SuppressWarnings("unchecked")
	public List<User> query(Integer page, Integer rows, User model) {
		return sqlSession.selectList("UserMapper.query", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long count(User model) {
		return (Long) sqlSession.selectOne("UserMapper.count",model);
	}

	public void delete(Integer userId) {
		sqlSession.delete("UserMapper.delete",userId);
	}

	public User loadByUserId(Integer userId) {
		return (User) sqlSession.selectOne("UserMapper.loadByUserId",userId);
	}

	public void updateStatus(Integer userId, Integer status) {
		User user = new User();
		user.setUserId(userId);
		user.setStatus(status);
		sqlSession.update("UserMapper.updateStatus",user);
	}

	public User login(String userCode, String passwords) {
		User user = new User();
		user.setUserCode(userCode);
		user.setPasswords(passwords);
		return (User) sqlSession.selectOne("UserMapper.login",user);
	}

	public Right getRootUrlRightTreeNode(Integer userId) {
		return (Right) sqlSession.selectOne("UserMapper.getRootUrlRightTreeNode",userId);
	}

	@SuppressWarnings("unchecked")
	public List<Right> getChildrenUrlRightTreeNode(Integer userId,
			Integer rightId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("rightId", rightId);
		return (List<Right>) sqlSession.selectList("UserMapper.getChildrenUrlRightTreeNode",map);
	}

	@SuppressWarnings("unchecked")
	public List<User> queryCombobox() {
		return sqlSession.selectList("UserMapper.queryCombobox");
	}

}
