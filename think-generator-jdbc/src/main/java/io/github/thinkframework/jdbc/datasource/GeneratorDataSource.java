package io.github.thinkframework.jdbc.datasource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 示例数据源
 * @author lixiaobin
 */
public class GeneratorDataSource implements DataSource{
    private String id;
    private Connection connection;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Properties connectProperties;
    public GeneratorDataSource(){

    }

    @Override
    public Connection getConnection() throws SQLException {
        if(connection == null) {
            Properties properties = new Properties();
            properties.put("user",username == null ? "" : username);
            properties.put("password",password == null ? "" : password);
            connection = DriverManager.getConnection(url,properties);
        }
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        if(connection == null) {
            connection = DriverManager.getConnection(url,username,password);
        }
        return connection;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getConnectProperties() {
        return connectProperties;
    }

    public void setConnectProperties(Properties connectProperties) {
        this.connectProperties = connectProperties;
    }
}
