package io.github.thinkframework.generator.beans.factory.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 注册NamespaceHandler.
 *
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("generator", new GeneratorBeanDefinitionParser());
    }
}
