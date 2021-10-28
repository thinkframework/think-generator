package io.github.thinkframework.generator.fx.view.tree;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.tree.TreePath;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
public class GeneratorTree extends JTree implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    protected DataSource getDataSource(String id) {
        return applicationContext.getBean(id, DataSource.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void afterPropertiesSet() {
        expandPath(new TreePath(getModel().getRoot()));
    }
}
