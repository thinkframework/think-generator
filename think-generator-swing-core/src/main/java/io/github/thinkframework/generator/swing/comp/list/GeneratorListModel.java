package io.github.thinkframework.generator.swing.comp.list;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import javax.swing.*;

/**
 * 数据源列表
 * @author hdhxby
 */
public class GeneratorListModel extends AbstractListModel implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public int getSize() {
        return applicationContext.getBeanNamesForType(DataSource.class).length;
    }

    @Override
    public Object getElementAt(int index) {
        return applicationContext.getBeanNamesForType(DataSource.class)[index];
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
