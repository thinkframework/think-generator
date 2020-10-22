package io.github.thinkframework.generator.core.xml;

import io.github.thinkframework.generator.core.GeneratorFactoryBean;
import io.github.thinkframework.generator.core.annotation.GeneratorImportBeanDefinitionRegistrar;
import io.github.thinkframework.generator.core.config.GeneratorProperties;
import io.github.thinkframework.generator.core.config.GeneratorProperties.GeneratorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.stream.Collectors;

/**
 * 解析generator节点.
 *
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {
    private static final Logger log = LoggerFactory.getLogger(GeneratorBeanDefinitionParser.class);
    /**
     * 不要调用registerBeanDefinition方法
     *
     * @param element
     * @param parserContext
     * @param builder
     */
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
//        super.doParse(element, parserContext, builder);
        if("generator".equals(element.getTagName())){
            return;
        }
        // 从标签中取出对应的属性值
        String id = element.getAttribute("id");
        log.debug("加载 BeanDefinition: {}", id);
        builder.getBeanDefinition().setBeanClass(GeneratorFactoryBean.class);
        GeneratorProperties generatorProperties = new GeneratorProperties();

        generatorProperties.setStragegy(generatorStrategy(element));
        generatorProperties.setConfiguration(generatorConfiguration(element));
        builder.addPropertyValue("properties",generatorProperties);
    }

    /**
     * 设置配置
     * @param element
     * @return
     */
    private GeneratorConfiguration generatorConfiguration(Element element) {
        element = DomUtils.getChildElementByTagName(element, "configuration");

        GeneratorConfiguration generatorConfiguration = new GeneratorConfiguration();
        generatorConfiguration.setFrameName(DomUtils.getChildElementValueByTagName(element, "frameName"));//框架包
        generatorConfiguration.setCompanyName(DomUtils.getChildElementValueByTagName(element, "companyName"));//公司名称
        generatorConfiguration.setAppName(DomUtils.getChildElementValueByTagName(element, "appName"));//应用名称
        generatorConfiguration.setModuleName(DomUtils.getChildElementValueByTagName(element, "moduleName"));//模块名称
        generatorConfiguration.setAuthorName(DomUtils.getChildElementValueByTagName(element, "authorName"));//作者名称
        generatorConfiguration.setNamespace(DomUtils.getChildElementValueByTagName(element, "namespace"));//命名空间
        generatorConfiguration.setTemplate(DomUtils.getChildElementValueByTagName(element, "template"));//模板目录
        generatorConfiguration.setOutput(DomUtils.getChildElementValueByTagName(element, "output"));//输出目录

        generatorConfiguration.setPrefixs(
            DomUtils.getChildElementsByTagName(element, "prefixs")
                .stream().flatMap(child -> DomUtils.getChildElementsByTagName(child, "list").stream())
                .flatMap(child -> DomUtils.getChildElementsByTagName(child, "value").stream())
                .map(child -> DomUtils.getTextValue(child))
                .collect(Collectors.toList()));

        generatorConfiguration.setIgnores(
            DomUtils.getChildElementsByTagName(element, "ignores")
                .stream().flatMap(child -> DomUtils.getChildElementsByTagName(child, "list").stream())
                .flatMap(child -> DomUtils.getChildElementsByTagName(child, "value").stream())
                .map(child -> DomUtils.getTextValue(child))
                .collect(Collectors.toList()));

        generatorConfiguration.setExtensions(
            DomUtils.getChildElementsByTagName(element, "extensions")
                .stream().flatMap(child -> DomUtils.getChildElementsByTagName(child, "list").stream())
                .flatMap(child -> DomUtils.getChildElementsByTagName(child, "value").stream())
                .map(child -> DomUtils.getTextValue(child))
                .collect(Collectors.toList()));


        generatorConfiguration.setConverts(
            DomUtils.getChildElementsByTagName(element, "converts")
                .stream().flatMap(child -> DomUtils.getChildElementsByTagName(child, "map").stream())
                .flatMap(child -> DomUtils.getChildElementsByTagName(child, "entity").stream())
                .collect(Collectors.toMap(child -> child.getAttribute("key"), child -> child.getAttribute("value"))));

        return generatorConfiguration;
    }


    /**
     * 设置策略
     * @param element
     * @return
     */
    private GeneratorProperties.StragegyConfiguration generatorStrategy(Element element) {
        element = DomUtils.getChildElementByTagName(element, "strategy");

        GeneratorProperties.StragegyConfiguration generatorConfiguration = new GeneratorProperties.StragegyConfiguration();
        generatorConfiguration.setClazz(DomUtils.getChildElementValueByTagName(element, "clazz"));//策略类

        generatorConfiguration.setResponsibilitys(
            DomUtils.getChildElementsByTagName(element, "responsibilitys")
                .stream().flatMap(child -> DomUtils.getChildElementsByTagName(child, "list").stream())
                .flatMap(child -> DomUtils.getChildElementsByTagName(child, "value").stream())
                .map(child -> DomUtils.getTextValue(child))
                .collect(Collectors.toList()));

        return generatorConfiguration;
    }
}
