package io.github.thinkframework.generator.clazz;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 容器测试
 */
public class GeneratorClazzTest {

    private static Logger logger = LoggerFactory.getLogger(GeneratorClazzTest.class);

    private static DataSource dataSource;

    private GeneratorConfiguration generatorConfiguration;


    @BeforeClass
    public static void beforeClass(){
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema-h2.sql")
                .build();
    }

    @Before
    public void before() {
        generatorConfiguration = new GeneratorConfiguration();
        // packageName = companyName + appName + moduleName
        generatorConfiguration.setFrameName("io.github.thinkframework"); // 框架包
        generatorConfiguration.setCompanyName("companyName"); // 公司名称
        generatorConfiguration.setAppName("appName"); // 应用名称
        generatorConfiguration.setModuleName("moduleName"); // 模块名称
        generatorConfiguration.setAuthorName("authorName"); // 作者名称
        generatorConfiguration.setNamespace("namespace"); // 命名空间
        generatorConfiguration.setTemplate("template"); // 模板目录
        generatorConfiguration.setOutput("output"); // 输出目录

        generatorConfiguration.setPrefixs(List.of("t_","table_")); //表前缀

        generatorConfiguration.setIgnores(List.of("id","created_by","created_date","last_modified_by","last_modified_date","version")); // 忽略字段

        generatorConfiguration.setExtensions(List.of("java","xml")); // 文件后缀

        generatorConfiguration.setConverts(Map.of("java.sql.Types.TIMESTAMP","java.time.Instant",
                "java.sql.Types.BIGINT","java.math.BigInteger")); // 类型映射
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        GeneratorClazz generator = new GeneratorClazz(generatorConfiguration);
        generator.generate(this.getClass());
    }
}
