package org.linys.dao.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO {

	private SqlSession sqlSession;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int getPageCount(String totalSql) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("totalSql", totalSql);
		return (Integer) sqlSession.selectOne("CommonMapper.getPageCount",params);
	}

}
