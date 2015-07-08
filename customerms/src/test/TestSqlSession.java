package test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class TestSqlSession {

	@Test
	public void testSqlSession() throws IOException {
		String conf = "mybatisConfiguration.xml";
		Reader reader = Resources.getResourceAsReader(conf);
		// 创建SessionFactory对象
		SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
		SqlSessionFactory sf = sfb.build(reader);
		//创建Session
		SqlSession session = sf.openSession();
		System.out.println(session);
		
		//关闭Session
		session.close();
	}

}
