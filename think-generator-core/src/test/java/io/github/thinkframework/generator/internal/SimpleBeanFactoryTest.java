package io.github.thinkframework.generator.internal;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.design.chainofresponsibility.TableGeneratorResponsibility;
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
            connection.prepareStatement("DROP TABLE IF EXISTS TEST;");
            connection.prepareStatement("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));");
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

        GeneratorConfiguration generatorConfiguration = new GeneratorConfiguration()
//            .setFrameName("com.glodon.bim5d")
//            .setCompanyName("com.glodon.bim5d")
//            .setAppName("app")
//            .setModuleName("costenterprise")
//            .setAuthorName("unascribed")
//            .setTemplate("template/glodon/enterprise")
            ;

        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();
        simpleBeanFactory.add("generator",new GeneratorFactoryBean());
        simpleBeanFactory.add("tableGeneratorProvider",new TableGeneratorResponsibility());
        simpleBeanFactory.add("dataSource",dataSource);

        GeneratorFactoryBean generatorFactoryBean = simpleBeanFactory.getBean("generator",GeneratorFactoryBean.class);
//        generatorFactoryBean.beanFactory(simpleBeanFactory)
//            .postProcessBeanFactory(null);
        generatorFactoryBean.getObject().generate();

    }

    @After
    public void after(){
        logger.debug("after");
    }
}
