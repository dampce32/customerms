package org.linys.dao.customer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.customer.CustomerDAO;
import org.linys.model.customer.Customer;
import org.linys.util.PageUtil;
import org.springframework.stereotype.Repository;
/**
 * @description:会员DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {
	@Resource
	private SqlSession sqlSession;

	public Customer loadByCustomerName(String customerName) {
		return (Customer) sqlSession.selectOne("CustomerMapper.loadByCustomerName",customerName);
	}

	public void insert(Customer model) {
		sqlSession.insert("CustomerMapper.insert",model);
	}

	public void update(Customer model) {
		sqlSession.update("CustomerMapper.update",model);
	}

	@SuppressWarnings("unchecked")
	public List<Customer> query(Integer page, Integer rows,
			Customer model) {
		return sqlSession.selectList("CustomerMapper.query", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long count(Customer model) {
		return (Long) sqlSession.selectOne("CustomerMapper.count",model);
	}

	public void delete(Integer customerId) {
		sqlSession.update("CustomerMapper.delete",customerId);
	}

	public Customer load(Customer model) {
		return (Customer) sqlSession.selectOne("CustomerMapper.load",model);
	}
}
