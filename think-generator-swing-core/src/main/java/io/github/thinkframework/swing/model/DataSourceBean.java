package io.github.thinkframework.swing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

/**
 * 数据源的设置对象
 * @author lixiaobin
 */
@Getter
@Setter
public class DataSourceBean {
    private String id;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Properties connectProperties;


    @Override
    public String toString() {
        return id;
    }
}
