package io.github.thinkframework.generator.internal;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.config.GeneratorConfiguration;
import org.apache.commons.io.FileUtils;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 简单容器测试
 * 与spring解耦
 */
@Ignore
public class SimpleBeanFactoryTest {

    Logger logger = LoggerFactory.getLogger(SimpleBeanFactoryTest.class);

    @Autowired
    private ApplicationContext applicationContext;

    private DataSource dataSource;

    @Before
    public void before() throws SQLException, IOException {
        logger.debug("before");

         dataSource = new JdbcDataSource();

        ((JdbcDataSource)dataSource).setURL("jdbc:h2:mem:default");
        ((JdbcDataSource)dataSource).setUser("sa");
        ((JdbcDataSource)dataSource).setPassword("");

        try(Connection connection = dataSource.getConnection()){
            connection.prepareStatement(FileUtils.readFileToString(new File("schema-h2.sql")));
        }

        if(new File("generator_output").exists()){
            new File("generator_output").delete();
        }
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void application() throws Exception {

        GeneratorConfiguration generatorConfiguration = new GeneratorConfiguration();
        generatorConfiguration.setDataSourceName("dataSource");
        generatorConfiguration.setFrameName("com.glodon.bim5d");
        generatorConfiguration.setCompanyName("com.glodon.bim5d");
        generatorConfiguration.setAppName("app");
        generatorConfiguration.setModuleName("costenterprise");
        generatorConfiguration.setAuthorName("unascribed");
        generatorConfiguration.setTemplate("template/glodon/enterprise");

        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();
        simpleBeanFactory.add("dataSource",dataSource);
        simpleBeanFactory.add("generator",new GeneratorFactoryBean());

        GeneratorFactoryBean generatorFactoryBean = simpleBeanFactory.getBean("generator",GeneratorFactoryBean.class);
        generatorFactoryBean.beanFactory(simpleBeanFactory).generatorConfiguration(generatorConfiguration).afterPropertiesSet();
        generatorFactoryBean.getObject()
            .dataSourceName("dataSource").tableName("TEST")
            .generate();

    }

    @After
    public void after(){
        logger.debug("after");
    }
}
