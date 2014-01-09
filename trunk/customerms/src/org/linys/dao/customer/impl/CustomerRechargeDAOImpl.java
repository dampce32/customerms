package org.linys.dao.customer.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.linys.dao.customer.CustomerRechargeDAO;
import org.linys.model.customer.CustomerRecharge;
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
}
