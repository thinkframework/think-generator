package io.github.thinkframework.generator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.Map;
import java.util.Properties;

/**
 * 解析generator节点.
 *
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
//        super.doParse(element, parserContext, builder);
        // 从标签中取出对应的属性值
        logger.debug("generator parse..." + element.getNodeName());
        Map map = parseChild(element);
//        setContext generatorFacade = new setContext((Properties)map,"");
//        builder.addPropertyValue("generator",generatorFacade);
    }

    private Map parseChild(Element element) {
        Properties properties = new Properties();
        properties.put("frameName", DomUtils.getChildElementValueByTagName(element, "frameName"));//框架包
//        properties.put("companyName", DomUtils.getChildElementValueByTagName(element, "companyName"));//公司名称
//        properties.put("appName", DomUtils.getChildElementValueByTagName(element, "appName"));//应用名称
//        properties.put("moduleName", DomUtils.getChildElementValueByTagName(element, "moduleName"));//模块名称
//        properties.put("authorName", DomUtils.getChildElementValueByTagName(element, "authorName"));//作者名称
//        properties.put("namespace", DomUtils.getChildElementValueByTagName(element, "namespace"));//命名空间
//        properties.put("template", DomUtils.getChildElementValueByTagName(element, "template"));//模板目录
//        properties.put("extensions", DomUtils.getChildElementValueByTagName(element, "extensions"));
//        properties.put("prefix", DomUtils.getChildElementValueByTagName(element, "prefix"));//表名前缀
//        properties.put("ignore", DomUtils.getChildElementValueByTagName(element, "ignore"));//忽略的字段
//        properties.put("output", DomUtils.getChildElementValueByTagName(element, "output"));//输出目录
        return properties;
    }
}
