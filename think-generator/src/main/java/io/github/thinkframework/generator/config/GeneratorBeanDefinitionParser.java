package io.github.thinkframework.generator.config;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

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
        String id = element.getAttribute("id");
        logger.debug("初始化BeanDefinition: {}", id);
        GeneratorConfiguration generatorConfiguration = parse(element);

        builder.addPropertyValue("generatorConfiguration",generatorConfiguration);

        BeanDefinition beanDefinition = builder.getBeanDefinition();
        ((AbstractBeanDefinition) beanDefinition).setBeanClass(GeneratorFactoryBean.class);

        parserContext.getRegistry().registerBeanDefinition(id,beanDefinition);
    }

    private GeneratorConfiguration parse(Element element) {
        element = DomUtils.getChildElementByTagName(element,"generatorConfiguration");
        GeneratorConfiguration generatorConfiguration = new GeneratorConfiguration();
        generatorConfiguration.setDataSourceName(DomUtils.getChildElementValueByTagName(element, "dataSourceName"));//数据源
        generatorConfiguration.setFrameName(DomUtils.getChildElementValueByTagName(element, "frameName"));//框架包
        generatorConfiguration.setCompanyName(DomUtils.getChildElementValueByTagName(element, "companyName"));//公司名称
        generatorConfiguration.setAppName(DomUtils.getChildElementValueByTagName(element, "appName"));//应用名称
        generatorConfiguration.setModuleName(DomUtils.getChildElementValueByTagName(element, "moduleName"));//模块名称
        generatorConfiguration.setAuthorName(DomUtils.getChildElementValueByTagName(element, "authorName"));//作者名称
        generatorConfiguration.setNamespace(DomUtils.getChildElementValueByTagName(element, "namespace"));//命名空间
        generatorConfiguration.setTemplate(DomUtils.getChildElementValueByTagName(element, "template"));//模板目录
        generatorConfiguration.setOutput(DomUtils.getChildElementValueByTagName(element, "output"));//输出目录


//        XPathFactory xPathFactory = XPathFactory.newInstance();
//        XPath xPath = xPathFactory.newXPath();
//
//        try {
//            NodeList prefixsList = (NodeList) xPath.evaluate("prefixs/list/value", element,XPathConstants.NODESET);
//            List<String> prefixs = new ArrayList<>();
//            for(int i=0;i<prefixsList.getLength();i++){
//                prefixs.add(xPath.evaluate("text()", prefixsList.item(i)));
//            }
//            generatorConfiguration.setPrefixs(prefixs);
//
//            NodeList ignoresList = (NodeList) xPath.evaluate("ignores/list/value", element,XPathConstants.NODESET);
//            List<String> ignores = new ArrayList<>();
//            for(int i=0;i<ignoresList.getLength();i++){
//                ignores.add(xPath.evaluate("text()", ignoresList.item(i)));
//            }
//            generatorConfiguration.setIgnores(ignores);
//
//
//
//            NodeList extensionsList = (NodeList) xPath.evaluate("extensions/list/value", element,XPathConstants.NODESET);
//            List<String> extensions = new ArrayList<>();
//            for(int i=0;i<extensionsList.getLength();i++){
//                extensions.add(xPath.evaluate("text()", ignoresList.item(i)));
//            }
//            generatorConfiguration.setExtensions(extensions);
//
////            NodeList convertsList = (NodeList) xPath.evaluate("converts/props/prop", element,XPathConstants.NODESET);
////            for(int i=0;i<convertsList.getLength();i++){
////                Integer type = Types.class.getField(xPath.evaluate("@key", convertsList.item(i)).replace("java.sql.Types.",""))
////                    .getInt(Types.class);
////                Class clazz = Class.forName(xPath.evaluate("text()", convertsList.item(i)));
////                TypesUtils.put(type, clazz);
////            }
////            convertsList = (NodeList) xPath.evaluate("converts/map/entity", element,XPathConstants.NODESET);
////            for(int i=0;i<convertsList.getLength();i++){
////                Integer type = Types.class.getField(xPath.evaluate("@key", convertsList.item(i)).replace("java.sql.Types.",""))
////                    .getInt(Types.class);
////                Class clazz = Class.forName(xPath.evaluate("@value", convertsList.item(i)));
////                TypesUtils.put(type, clazz);
////            }
//        } catch (XPathExpressionException e) {
//            e.printStackTrace();
//        }

        return generatorConfiguration;
    }

}
