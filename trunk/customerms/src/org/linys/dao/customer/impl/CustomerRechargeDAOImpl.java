package org.linys.dao.customer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.customer.CustomerRechargeDAO;
import org.linys.model.customer.CustomerRecharge;
import org.linys.util.PageUtil;
import org.springframework.stereotype.Repository;
/**
 * @description:会员充值DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class CustomerRechargeDAOImpl implements CustomerRechargeDAO {
	@Resource
	private SqlSession sqlSession;

	public void insert(CustomerRecharge model) {
		sqlSession.insert("CustomerRechargeMapper.insert",model);
	}

	@SuppressWarnings("unchecked")
	public List<CustomerRecharge> query(Integer page, Integer rows,
			CustomerRecharge model) {
		return sqlSession.selectList("CustomerRechargeMapper.query", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long count(CustomerRecharge model) {
		return (Long) sqlSession.selectOne("CustomerRechargeMapper.count",model);
	}
}
