package io.github.thinkframework.generator;

import io.github.thinkframework.generator.core.AbstractGenerator;
import io.github.thinkframework.generator.core.chain.ConfigurationResponsibility;
import io.github.thinkframework.generator.core.chain.IDResponsibility;
import io.github.thinkframework.generator.core.chain.NameResponsibility;
import io.github.thinkframework.generator.core.command.FreeMarkerStringCommand;
import io.github.thinkframework.generator.core.command.WalkFileTreeCommand;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.util.FreeMarkerHelper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 容器测试
 */
public class GeneratorTest {

    private Logger log = LoggerFactory.getLogger(GeneratorTest.class);

    private GeneratorConfiguration generatorConfiguration;

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
        Generator generator = new AbstractGenerator(generatorConfiguration){};

        generator.generate(() -> new GeneratorContext.Builder<>()
                        .configuration(generatorConfiguration)
                        .source("helloword")
                        .build(),
                new ConfigurationResponsibility()
                        .compose(new IDResponsibility()
                            .compose(new NameResponsibility())),
                (context -> {
                    log.debug(new FreeMarkerHelper()
                            .generatorConfiguration(((GeneratorContext) context).getConfiguration())
                            .string(((GeneratorContext) context).getProperties(),
                                    "\r" +
                                    "package ${companyName}.${appName}.${moduleName}\n" +
                                            "import ${frameName};\n" +
                                            "/**\n" +
                                            " * @author ${authorName}\n" +
                                            " */\n" +
                                            "public class ${name} {\n" +
                                            " private ${id.type} ${id.name};\n" +
                                            "}"));
                }));
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void template_notExist() throws Exception {
        GeneratorConfiguration configuration = generatorConfiguration.clone();
        configuration.setTemplate("notexists");
        Generator generator = new AbstractGenerator(configuration){};
        generator.generate(() -> new GeneratorContext.Builder<>()
                        .configuration(generatorConfiguration)
                        .source("helloword")
                        .build(),
                new ConfigurationResponsibility()
                        .compose(new IDResponsibility()
                                .compose(new NameResponsibility())),
                new WalkFileTreeCommand());
    }
}
