package org.linys.dao.dict.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.linys.dao.dict.DataDictDAO;
import org.linys.model.dict.DataDict;
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
public class DataDictDAOImpl implements DataDictDAO {
	@Resource
	private SqlSession sqlSession;

	public DataDict loadByDataDictName(String dataDictName) {
		return (DataDict) sqlSession.selectOne("DataDictMapper.loadByDataDictName",dataDictName);
	}

	public void insert(DataDict model) {
		sqlSession.insert("DataDictMapper.insert",model);
	}

	public void update(DataDict model) {
		sqlSession.update("DataDictMapper.update",model);
	}

	@SuppressWarnings("unchecked")
	public List<DataDict> query(Integer page, Integer rows,
			DataDict model) {
		return sqlSession.selectList("DataDictMapper.query", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long count(DataDict model) {
		return (Long) sqlSession.selectOne("DataDictMapper.count",model);
	}

	public void delete(Integer dataDictId) {
		sqlSession.update("DataDictMapper.delete",dataDictId);
	}

	@SuppressWarnings("unchecked")
	public List<DataDict> querySelect(Integer page, Integer rows,DataDict model) {
		return sqlSession.selectList("DataDictMapper.querySelect", model, new RowBounds(PageUtil.getPageBegin(page, rows),rows));
	}

	public Long countSelect(DataDict model) {
		return (Long) sqlSession.selectOne("DataDictMapper.countSelect",model);
	}

	public DataDict loadByTypeAndName(String dataDictType, String dataDictName) {
		DataDict dataDict = new DataDict();
		dataDict.setDataDictType(dataDictType);
		dataDict.setDataDictName(dataDictName);
		return (DataDict) sqlSession.selectOne("DataDictMapper.load",dataDict);
	}

	@SuppressWarnings("unchecked")
	public List<DataDict> queryCombobox(DataDict model) {
		return sqlSession.selectList("DataDictMapper.queryCombobox", model);
	}
	
	
}
