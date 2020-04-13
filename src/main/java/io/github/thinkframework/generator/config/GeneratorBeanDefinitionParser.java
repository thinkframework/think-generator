package io.github.thinkframework.generator.config;

import io.github.thinkframework.generator.GeneratorFactoryBean;
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
    Logger logger = LoggerFactory.getLogger(getClass());

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
        // 从标签中取出对应的属性值
        String id = element.getAttribute("id");
        logger.debug("加载 BeanDefinition: {}", id);
//        parserContext.getRegistry().registerBeanDefinition(id+"GeneratorConfiguration", parseConfiguration(element).getBeanDefinition());
//        builder.addPropertyReference("generatorConfiguration", id+"GeneratorConfiguration");
        builder.addPropertyValue("generatorConfiguration", generatorConfiguration(element));
        builder.getBeanDefinition().setBeanClass(GeneratorFactoryBean.class);
        parserContext.getRegistry().registerBeanDefinition(id, builder.getBeanDefinition());
    }

    /**
     * @param element
     * @return
     */
    private BeanDefinitionBuilder parseConfiguration(Element element) {

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
        element = DomUtils.getChildElementByTagName(element, "generatorConfiguration");

        builder.getBeanDefinition().setBeanClass(GeneratorConfiguration.class);

//        builder.addPropertyReference("dataSource", DomUtils.getChildElementValueByTagName(element, "dataSource"));//数据源
        builder.addPropertyValue("frameName", DomUtils.getChildElementValueByTagName(element, "frameName"));//框架包
        builder.addPropertyValue("companyName", DomUtils.getChildElementValueByTagName(element, "companyName"));//公司名称
        builder.addPropertyValue("appName", DomUtils.getChildElementValueByTagName(element, "appName"));//应用名称
        builder.addPropertyValue("moduleName", DomUtils.getChildElementValueByTagName(element, "moduleName"));//模块名称
        builder.addPropertyValue("authorName", DomUtils.getChildElementValueByTagName(element, "authorName"));//作者名称
        builder.addPropertyValue("namespace", DomUtils.getChildElementValueByTagName(element, "namespace"));//命名空间
        builder.addPropertyValue("template", DomUtils.getChildElementValueByTagName(element, "template"));//模板目录
        builder.addPropertyValue("output", DomUtils.getChildElementValueByTagName(element, "output"));//输出目录


        builder.addPropertyValue("prefixs",
            DomUtils.getChildElementsByTagName(element, "prefixs")
                .stream().flatMap(child -> DomUtils.getChildElementsByTagName(child, "list").stream())
                .flatMap(child -> DomUtils.getChildElementsByTagName(child, "value").stream())
                .map(child -> DomUtils.getTextValue(child))
                .collect(Collectors.toList()));


        builder.addPropertyValue("ignores",
            DomUtils.getChildElementsByTagName(element, "ignores")
                .stream().flatMap(child -> DomUtils.getChildElementsByTagName(child, "list").stream())
                .flatMap(child -> DomUtils.getChildElementsByTagName(child, "value").stream())
                .map(child -> DomUtils.getTextValue(child))
                .collect(Collectors.toList()));


        builder.addPropertyValue("extensions",
            DomUtils.getChildElementsByTagName(element, "extensions")
                .stream().flatMap(child -> DomUtils.getChildElementsByTagName(child, "list").stream())
                .flatMap(child -> DomUtils.getChildElementsByTagName(child, "value").stream())
                .map(child -> DomUtils.getTextValue(child))
                .collect(Collectors.toList()));


        builder.addPropertyValue("converts",
            DomUtils.getChildElementsByTagName(element, "converts")
                .stream().flatMap(child -> DomUtils.getChildElementsByTagName(child, "map").stream())
                .flatMap(child -> DomUtils.getChildElementsByTagName(child, "entity").stream())
                .collect(Collectors.toMap(child -> child.getAttribute("key"), child -> child.getAttribute("value"))));
        return builder;
    }


    private GeneratorConfiguration generatorConfiguration(Element element) {

        String id = element.getAttribute("id");
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
        element = DomUtils.getChildElementByTagName(element, "generatorConfiguration");

        builder.getBeanDefinition().setBeanClass(GeneratorConfiguration.class);
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
}
