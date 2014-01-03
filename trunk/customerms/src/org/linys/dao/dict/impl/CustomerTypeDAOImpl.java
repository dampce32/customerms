package org.linys.dao.dict.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.dict.CustomerTypeDAO;
import org.linys.model.dict.CustomerType;
import org.linys.util.PageUtil;
import org.springframework.stereotype.Repository;
/**
 * @description:会员类型DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class CustomerTypeDAOImpl implements CustomerTypeDAO {
	@Resource
	private SqlSession sqlSession;

	public CustomerType loadByCustomerTypeName(String customerTypeName) {
		return (CustomerType) sqlSession.selectOne("CustomerTypeMapper.loadByCustomerTypeName",customerTypeName);
	}

	public void insert(CustomerType model) {
		sqlSession.insert("CustomerTypeMapper.insert",model);
	}

	public void update(CustomerType model) {
		sqlSession.update("CustomerTypeMapper.update",model);
	}

	@SuppressWarnings("unchecked")
	public List<CustomerType> query(Integer page, Integer rows,
			CustomerType model) {
		return sqlSession.selectList("CustomerTypeMapper.query", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long count(CustomerType model) {
		return (Long) sqlSession.selectOne("CustomerTypeMapper.count",model);
	}

	public void delete(Integer customerTypeId) {
		sqlSession.update("CustomerTypeMapper.delete",customerTypeId);
	}

	@SuppressWarnings("unchecked")
	public List<CustomerType> queryCombobox() {
		return sqlSession.selectList("CustomerTypeMapper.queryCombobox");
	}
}
