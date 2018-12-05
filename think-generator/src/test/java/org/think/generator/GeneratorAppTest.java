package org.think.generator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class GeneratorAppTest{
	ApplicationContext applicationContext;
	private DataSource dataSource;
	private String tableName;

	@Before
	public void before(){
		applicationContext = new FileSystemXmlApplicationContext("applicationContext.xml");
		dataSource = (DataSource)applicationContext.getBean("mysql");
	}

	@Test
	public void authUser() throws Exception {
		tableName = "%";
		new GeneratorFacade().generatorTable(dataSource, tableName,"costenterprise");
	}
}