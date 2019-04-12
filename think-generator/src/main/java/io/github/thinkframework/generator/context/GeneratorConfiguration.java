package io.github.thinkframework.generator.context;

import org.springframework.beans.BeanUtils;

/**
 * 配置文件
 */
public class GeneratorConfiguration {

    private String dataSource;

    /**
     * 获取数据源
     * @return 数据源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置数据源
     * @param dataSource 数据源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public void map(){
        //TODO 对象转map freemaker要用到
    }
}
