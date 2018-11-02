package org.think.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class GeneratorAppTest extends AbstractJUnit4SpringContextTests {
	@Resource(name = "mysql")
	private DataSource dataSource;
	private String tableName;

	@Test
	public void authUser() throws Exception {
		tableName = "milestone%";
		new GeneratorFacade().generatorTable(dataSource, tableName,"costenterprise");
	}
}