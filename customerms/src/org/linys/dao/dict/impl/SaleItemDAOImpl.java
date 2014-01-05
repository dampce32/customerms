package org.linys.dao.dict.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.dict.SaleItemDAO;
import org.linys.model.dict.SaleItem;
import org.linys.util.PageUtil;
import org.springframework.stereotype.Repository;
/**
 * @description:消费项目DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class SaleItemDAOImpl implements SaleItemDAO {
	@Resource
	private SqlSession sqlSession;

	public SaleItem loadBySaleItemName(String saleItemName) {
		return (SaleItem) sqlSession.selectOne("SaleItemMapper.loadBySaleItemName",saleItemName);
	}

	public void insert(SaleItem model) {
		sqlSession.insert("SaleItemMapper.insert",model);
	}

	public void update(SaleItem model) {
		sqlSession.update("SaleItemMapper.update",model);
	}

	@SuppressWarnings("unchecked")
	public List<SaleItem> query(Integer page, Integer rows,
			SaleItem model) {
		return sqlSession.selectList("SaleItemMapper.query", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long count(SaleItem model) {
		return (Long) sqlSession.selectOne("SaleItemMapper.count",model);
	}

	public void delete(Integer saleItemId) {
		sqlSession.update("SaleItemMapper.delete",saleItemId);
	}

	@SuppressWarnings("unchecked")
	public List<SaleItem> querySelect(Integer page, Integer rows,SaleItem model) {
		return sqlSession.selectList("SaleItemMapper.querySelect", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long countSelect(SaleItem model) {
		return (Long) sqlSession.selectOne("SaleItemMapper.countSelect",model);
	}
	
	
}
