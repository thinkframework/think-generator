package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import io.github.thinkframework.generator.sql.TableFactory;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
import io.github.thinkframework.generator.util.GeneratorFreeMarker;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Types;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 * 生成器对象
 *
 * @author lixiaobin
 * @since 1.0.0
 */
@Slf4j
public class GeneratorString implements Generator<DataSource,String>,InitializingBean {

    private GeneratorConfiguration generatorConfiguration;

    private List<GeneratorProvider> generatorProviders;

    private DataSource dataSource;

    private String tableName;

    /**
     * 生成
     *
     * @return
     */
    public void generate() throws GeneratorRuntimeException {
        Assert.notNull(generatorConfiguration, "配置文件不存在");
        log.info("传入的表名称:{}", tableName);

        new TableFactory(dataSource)
            .getTables(tableName)//获取表,模糊查询
            .parallelStream()//并行执行
            .map(TableImpl::getTableName)//获取表名称
            .map(tableName -> GeneratorContext.get(generatorConfiguration).dataSource(dataSource).tableName(tableName))//设置环境上下文
            .peek(generatorContext -> generatorProviders.forEach(generatorProvider -> generatorProvider.build(generatorContext)))//调用所有的提供者,填充数据
            .forEach(generatorContext -> {
                //调用输出
                try {
                    if (StringUtils.isNotEmpty(generatorConfiguration.getTemplate())) {//模板目录必须存在
                        Files.walkFileTree(Paths.get(new File(generatorConfiguration.getTemplate()).toURI()), new SimpleFileVisitor<Path>() {
                            @Override
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                Objects.requireNonNull(file);
                                Objects.requireNonNull(attrs);
                                if (generatorConfiguration.getExtensions().stream().noneMatch(extension -> file.toFile().getName().lastIndexOf(extension) > -1)) {
                                    return FileVisitResult.CONTINUE;
                                }
                                GeneratorFreeMarker generatorFreeMarker = new GeneratorFreeMarker()
                                    .configuration(generatorContext);
                                //输出文件路径
                                File output = new File(generatorFreeMarker.process(generatorContext.getProperties(),
                                    file.toFile().getPath(),
                                    file.toFile().getPath().replace(generatorConfiguration.getTemplate().replaceAll("[\\/]", Matcher.quoteReplacement(File.separator)),
                                        generatorConfiguration.getOutput().replaceAll("[\\/]", Matcher.quoteReplacement(File.separator)))));
                                //生成父文件夹
                                if (Files.notExists(Paths.get(output.getParentFile().toURI()))) {
                                    Files.createDirectories(Paths.get(output.getParentFile().toURI()));
                                }
                                //删除已经存在的文件
                                Files.deleteIfExists(Paths.get(output.toURI()));
                                //创建文件
                                Files.createFile(Paths.get(output.toURI()));
                                //输出文件
                                generatorFreeMarker
                                    .process(generatorContext.getProperties(), file.toFile(), output);
                                return FileVisitResult.CONTINUE;
                            }
                        });
                    } else {
                        throw new GeneratorRuntimeException("模板目录不存在");
                    }
                } catch (IOException e) {
                    throw new GeneratorRuntimeException(e);
                }
            });
    }

    public GeneratorConfiguration getGeneratorConfiguration() {
        return generatorConfiguration;
    }

    public GeneratorString generatorConfiguration(GeneratorConfiguration generatorConfiguration) {
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public void setGeneratorConfiguration(GeneratorConfiguration generatorConfiguration) {
        this.generatorConfiguration = generatorConfiguration;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public GeneratorString dataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<GeneratorProvider> getGeneratorProviders() {
        return generatorProviders;
    }

    public GeneratorString generatorProviders(List<GeneratorProvider> generatorProviders) {
        this.generatorProviders = generatorProviders;
        return this;
    }

    public void setGeneratorProviders(List<GeneratorProvider> generatorProviders) {
        this.generatorProviders = generatorProviders;
    }

    public GeneratorString tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //覆盖数据库类型
        generatorConfiguration.getConverts().forEach((key, value) -> {
            try {
                TypesUtils.put(Types.class.getField((key).replace("java.sql.Types.", ""))
                        .getInt(Types.class),
                    Class.forName(value));
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                throw new GeneratorRuntimeException("反射异常", e);
            }
        });
    }
}
