package io.github.thinkframework.generator.config;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.listener.GeneratorListener;
import io.github.thinkframework.generator.provider.ConfigurationGeneratorProvider;
import io.github.thinkframework.generator.provider.TableGeneratorProvider;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GeneratorBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {

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
        String clazz = element.getAttribute("clazz");
        log.debug("加载 BeanDefinition: {}", id);
        builder.addPropertyValue("clazz", clazz);
        builder.getBeanDefinition().setBeanClass(GeneratorFactoryBean.class);
        parserContext.getRegistry().registerBeanDefinition(id, builder.getBeanDefinition());

        BeanDefinitionBuilder generatorProperties =  BeanDefinitionBuilder.genericBeanDefinition();
        generatorProperties.getBeanDefinition().setBeanClass(GeneratorProperties.class);
        generatorProperties.addPropertyValue("configuration", generatorConfiguration(element));
        parserContext.getRegistry().registerBeanDefinition("generatorProperties", generatorProperties.getBeanDefinition());

        BeanDefinitionBuilder configurationGeneratorProvider =  BeanDefinitionBuilder.genericBeanDefinition();
        configurationGeneratorProvider.getBeanDefinition().setBeanClass(ConfigurationGeneratorProvider.class);
        parserContext.getRegistry().registerBeanDefinition("configurationGeneratorProvider", configurationGeneratorProvider.getBeanDefinition());

        BeanDefinitionBuilder tableGeneratorProvider =  BeanDefinitionBuilder.genericBeanDefinition();
        tableGeneratorProvider.getBeanDefinition().setBeanClass(TableGeneratorProvider.class);
        parserContext.getRegistry().registerBeanDefinition("tableGeneratorProvider", tableGeneratorProvider.getBeanDefinition());


        BeanDefinitionBuilder generatorListener =  BeanDefinitionBuilder.genericBeanDefinition();
        generatorListener.getBeanDefinition().setBeanClass(GeneratorListener.class);
        parserContext.getRegistry().registerBeanDefinition("generatorListener", generatorListener.getBeanDefinition());
    }

    private GeneratorConfiguration generatorConfiguration(Element element) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
        element = DomUtils.getChildElementByTagName(element, "configuration");

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
