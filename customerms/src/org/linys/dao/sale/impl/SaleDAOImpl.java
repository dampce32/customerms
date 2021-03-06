package org.linys.dao.sale.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.sale.SaleDAO;
import org.linys.model.sale.Sale;
import org.springframework.stereotype.Repository;
/**
 * @description:消费DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class SaleDAOImpl implements SaleDAO {
	@Resource
	private SqlSession sqlSession;

	public Sale loadBySaleName(String saleName) {
		return (Sale) sqlSession.selectOne("SaleMapper.loadBySaleName",saleName);
	}

	public void insert(Sale model) {
		sqlSession.insert("SaleMapper.insert",model);
	}

	public void update(Sale model) {
		sqlSession.update("SaleMapper.update",model);
	}

	@SuppressWarnings("unchecked")
	public List<Sale> query(Sale model) {
		return sqlSession.selectList("SaleMapper.query", model, new RowBounds(model.getStart(),model.getRows()));
	}

	public Long count(Sale model) {
		return (Long) sqlSession.selectOne("SaleMapper.count",model);
	}

	public void delete(Integer saleId) {
		sqlSession.update("SaleMapper.delete",saleId);
	}

	public Sale load(Sale model) {
		return (Sale) sqlSession.selectOne("SaleMapper.load",model);
	}
	public Sale loadDelete(Sale sale) {
		return (Sale) sqlSession.selectOne("SaleMapper.loadDelete",sale);
	}
}
