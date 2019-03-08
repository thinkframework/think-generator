package io.github.thinkframework.generator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class GeneratorAppTest {
    ApplicationContext applicationContext;
    private DataSource dataSource;
    private String tableName;

    @Before
    public void before() {
//		applicationContext = new FileSystemXmlApplicationContext("applicationContext.xml");
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        dataSource = (DataSource) applicationContext.getBean("project");
    }

    @Test
    public void authUser1() throws Exception {
        tableName = "bim5d_cost_geqtitle";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");

        tableName = "bim5d_cost_geqbqitem";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_geqnorm_item";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_geqlmmdetail";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_geqcost_classify_cbkm";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_geqresource";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_geqbid_summary";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_non_entity_cost_classify";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_non_entity_cost_detail_classify";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_non_entity_cost_field_define";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_non_entity_cost_template";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_geqcbkm_contrast";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_geqcbkm_contrast";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_master_index_info";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_master_index_info_value";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_index_node";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_geqindex";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
        tableName = "bim5d_cost_receipt_info";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");

        tableName = "bim5d_cost_bid_node";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
    }


    @Test
    public void authUser2() throws Exception {
        tableName = "bim5d_cost_cbkm";
        new GeneratorFacade().generatorTable("classpath://applicationContext.xml", dataSource, tableName, "costproject");
    }
}
