package io.github.thinkframework.generator;

import io.github.thinkframework.generator.table.GeneratorTables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * 容器测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorApplication.class)
public class GeneratorTablesApplicationTest {

    public static Logger logger = LoggerFactory.getLogger(GeneratorTablesApplicationTest.class);

    @Autowired
    private GeneratorTables generator;

    @Autowired
    private DataSource dataSource;

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        generator.generate(dataSource,"TEST_TABLE");
    }
}
