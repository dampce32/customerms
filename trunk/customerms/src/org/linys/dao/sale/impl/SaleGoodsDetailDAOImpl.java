package org.linys.dao.sale.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.linys.dao.sale.SaleGoodsDetailDAO;
import org.linys.model.sale.Sale;
import org.linys.model.sale.SaleGoodsDetail;
import org.springframework.stereotype.Repository;
/**
 * @description:消费产品明细DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class SaleGoodsDetailDAOImpl implements SaleGoodsDetailDAO {
	@Resource
	private SqlSession sqlSession;

	public void insert(SaleGoodsDetail model) {
		sqlSession.insert("SaleGoodsDetailMapper.insert",model);
	}

	public void update(SaleGoodsDetail model) {
		sqlSession.update("SaleGoodsDetailMapper.update",model);
	}

	@SuppressWarnings("unchecked")
	public List<SaleGoodsDetail> queryBySale(Sale model) {
		return sqlSession.selectList("SaleGoodsDetailMapper.queryBySale", model);
	}

	public void deleteArray(Integer[] delSaleGoodsDetailIdArray) {
		SaleGoodsDetail saleGoodsDetail = new SaleGoodsDetail();
		saleGoodsDetail.setIdArray(delSaleGoodsDetailIdArray);
		sqlSession.delete("SaleGoodsDetailMapper.deleteArray",saleGoodsDetail);
	}
}
