package org.think.generator.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.think.generator.GeneratorFacade;
import org.w3c.dom.Element;

import java.util.Map;
import java.util.Properties;

/**
 * 解析generator节点.
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {
    Log logger = LogFactory.getLog(getClass());
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
//        super.doParse(element, parserContext, builder);
        // 从标签中取出对应的属性值
        logger.debug("generator parse..."+element.getNodeName());
        Map map = parseChild(element);
//        setContext generatorFacade = new setContext((Properties)map,"");
//        builder.addPropertyValue("generator",generatorFacade);
    }

    private Map parseChild(Element element){
        String core = DomUtils.getChildElementValueByTagName(element,"core");
        String frameName = DomUtils.getChildElementValueByTagName(element,"frameName");
        String companyName = DomUtils.getChildElementValueByTagName(element,"companyName");
        String appName = DomUtils.getChildElementValueByTagName(element,"appName");
        String moduleName = DomUtils.getChildElementValueByTagName(element,"moduleName");
        String authorName = DomUtils.getChildElementValueByTagName(element,"authorName");
        String namespace = DomUtils.getChildElementValueByTagName(element,"namespace");
        String template = DomUtils.getChildElementValueByTagName(element,"template");
        String extensions = DomUtils.getChildElementValueByTagName(element,"extensions");
        String prefix = DomUtils.getChildElementValueByTagName(element,"prefix");
        String ignore = DomUtils.getChildElementValueByTagName(element,"ignore");
        String output = DomUtils.getChildElementValueByTagName(element,"output");

        Properties properties = new Properties();
        properties.put("core",core);//核心包
        properties.put("frameName",frameName);//框架包
        properties.put("companyName",companyName);//公司名称
        properties.put("appName",appName);//应用名称
        properties.put("moduleName",moduleName);//模块名称
        properties.put("authorName",authorName);//作者名称
        properties.put("namespace",namespace);//命名空间
        properties.put("template",template);//模板目录
        properties.put("extensions",extensions);
        properties.put("prefix",prefix);//表名前缀
        properties.put("ignore",ignore);//忽略的字段
        properties.put("output",output);//输出目录
        return properties;
    }
}
