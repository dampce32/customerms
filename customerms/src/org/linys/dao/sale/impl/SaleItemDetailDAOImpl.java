package org.linys.dao.sale.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.sale.SaleItemDetailDAO;
import org.linys.model.sale.Sale;
import org.linys.model.sale.SaleItemDetail;
import org.linys.util.PageUtil;
import org.springframework.stereotype.Repository;
/**
 * @description:消费项目明细DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class SaleItemDetailDAOImpl implements SaleItemDetailDAO {
	@Resource
	private SqlSession sqlSession;

	public SaleItemDetail loadBySaleItemDetailName(String saleName) {
		return (SaleItemDetail) sqlSession.selectOne("SaleItemDetailMapper.loadBySaleItemDetailName",saleName);
	}

	public void insert(SaleItemDetail model) {
		sqlSession.insert("SaleItemDetailMapper.insert",model);
	}

	public void update(SaleItemDetail model) {
		sqlSession.update("SaleItemDetailMapper.update",model);
	}

	@SuppressWarnings("unchecked")
	public List<SaleItemDetail> query(Integer page, Integer rows,
			SaleItemDetail model) {
		return sqlSession.selectList("SaleItemDetailMapper.query", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long count(SaleItemDetail model) {
		return (Long) sqlSession.selectOne("SaleItemDetailMapper.count",model);
	}

	public void delete(Integer saleId) {
		sqlSession.update("SaleItemDetailMapper.delete",saleId);
	}

	public SaleItemDetail load(SaleItemDetail model) {
		return (SaleItemDetail) sqlSession.selectOne("SaleItemDetailMapper.load",model);
	}

	@SuppressWarnings("unchecked")
	public List<SaleItemDetail> queryBySale(Sale model) {
		return sqlSession.selectList("SaleItemDetailMapper.queryBySale", model);
	}

	public void deleteArray(Integer[] delSaleItemDetailIdArray) {
		SaleItemDetail saleItemDetail = new SaleItemDetail();
		saleItemDetail.setIdArray(delSaleItemDetailIdArray);
		sqlSession.delete("SaleItemDetailMapper.deleteArray",saleItemDetail);
	}
}
