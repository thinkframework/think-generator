package io.github.thinkframework.swing.control.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.tree.TreePath;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorTree extends JTree implements ApplicationContextAware, ApplicationEventPublisherAware, MessageSourceAware, InitializingBean {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;

    private ApplicationEventPublisher applicationEventPublisher;

    private MessageSource messageSource;



    private String[] getDataSourceNames() {
        return applicationContext.getBeanNamesForType(DataSource.class);
    }

    protected DataSource getDataSource(String id) {
        return applicationContext.getBean(id, DataSource.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void afterPropertiesSet() {
        expandPath(new TreePath(getModel().getRoot()));
    }
}
