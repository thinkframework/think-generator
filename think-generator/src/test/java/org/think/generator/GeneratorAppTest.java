package org.think.generator;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class GeneratorAppTest{
	ApplicationContext applicationContext;
	private DataSource dataSource;
	private String tableName;

	@Before
	public void before(){
//		applicationContext = new FileSystemXmlApplicationContext("applicationContext.xml");
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		dataSource = (DataSource)applicationContext.getBean("dataSource");
	}

    @Test
    public void authUser1() throws Exception {
        tableName = "%";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml",dataSource, tableName,"costenterprise");
    }
}