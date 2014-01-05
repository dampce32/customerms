package org.linys.dao.dict.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.dict.GoodsDAO;
import org.linys.model.dict.Goods;
import org.linys.util.PageUtil;
import org.springframework.stereotype.Repository;
/**
 * @description:产品DAO实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Repository
public class GoodsDAOImpl implements GoodsDAO {
	@Resource
	private SqlSession sqlSession;

	public Goods loadByGoodsName(String goodsName) {
		return (Goods) sqlSession.selectOne("GoodsMapper.loadByGoodsName",goodsName);
	}

	public void insert(Goods model) {
		sqlSession.insert("GoodsMapper.insert",model);
	}

	public void update(Goods model) {
		sqlSession.update("GoodsMapper.update",model);
	}

	@SuppressWarnings("unchecked")
	public List<Goods> query(Integer page, Integer rows,
			Goods model) {
		return sqlSession.selectList("GoodsMapper.query", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long count(Goods model) {
		return (Long) sqlSession.selectOne("GoodsMapper.count",model);
	}

	public void delete(Integer goodsId) {
		sqlSession.update("GoodsMapper.delete",goodsId);
	}

	@SuppressWarnings("unchecked")
	public List<Goods> querySelect(Integer page, Integer rows, Goods model) {
		return sqlSession.selectList("GoodsMapper.querySelect", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long countSelect(Goods model) {
		return (Long) sqlSession.selectOne("GoodsMapper.countSelect",model);
	}
}
