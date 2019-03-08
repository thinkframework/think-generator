package io.github.thinkframework.thinkframework.datasource;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.thinkframework.jdbc.datasource.SimpleDataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * Created by lixiaobin on 2017/5/12.
 */
public class SimpleDataSourceTest {
    Logger logger = LoggerFactory.getLogger(SimpleDataSourceTest.class);
    @Test
    public void getConnectionWithClassPath() throws SQLException {
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setUrl("jdbc:think:pdm:classpath://classpath.pdm");
        dataSource.setDriverClassName(null);
        dataSource.setUsername(null);
        dataSource.setPassword(null);
        dataSource.setConnectProperties(null);

        Connection connection = dataSource.getConnection();
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
        logger.debug(databaseMetaData.getURL());
        logger.debug(SimpleDataSourceTest.class.getClassLoader().getResource("classpath.pdm").toString());
    }

    @Test
    public void getConnectionWithFile() throws SQLException {
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setUrl("jdbc:think:pdm:file://file.pdm");
        dataSource.setDriverClassName(null);
        dataSource.setUsername(null);
        dataSource.setPassword(null);
        dataSource.setConnectProperties(null);

        Connection connection = dataSource.getConnection();
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
        logger.debug(databaseMetaData.getURL());
        Assert.assertTrue(new File("file.pdm").exists());
    }
}
