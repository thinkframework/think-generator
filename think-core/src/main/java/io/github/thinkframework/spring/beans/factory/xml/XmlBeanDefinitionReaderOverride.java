package io.github.thinkframework.spring.beans.factory.xml;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 自定义BeanDefinitionReader,忽略文件不存在异常
 * @author lixiaobin
 */
public class XmlBeanDefinitionReaderOverride extends XmlBeanDefinitionReader {
    /**
     * Create new XmlBeanDefinitionReader for the given bean factory.
     *
     * @param registry the BeanFactory to load bean definitions into,
     *                 in the form of a BeanDefinitionRegistry
     */
    public XmlBeanDefinitionReaderOverride(BeanDefinitionRegistry registry) {
        super(registry);
    }



    /**
     * Load bean definitions from the specified XML file.
     * @param resource the resource descriptor for the XML file
     * @return the number of bean definitions found
     * @throws BeanDefinitionStoreException in case of loading or parsing errors
     */
    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        if(resource.exists()) {
            return loadBeanDefinitions(new EncodedResource(resource));
        }
        return 0;
    }
}
