package com.github.pshcode.mycoinasset;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pshcode.mycoinasset.config.MyCoinAssetApplication;
import com.github.pshcode.mycoinasset.mapper.MyCoinMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyCoinAssetApplication.class)
public class MyCoinAssetApplicationTests {
	@Qualifier("dataSource")
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	private MyCoinMapper cityMapper;

	@Test
	public void contextLoads() {

	}

	@Test
	public void testConnection() throws Exception {
		System.out.println("dataSource: " + dataSource);

		Connection connection = dataSource.getConnection();

		System.out.println("connection: " + connection);
	}

	@Test
	public void testSqlSessionFactory() throws Exception {
		System.out.println("sqlSessionFactory: " + sqlSessionFactory);
	}
}
