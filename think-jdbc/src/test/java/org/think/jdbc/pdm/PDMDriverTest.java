package org.think.jdbc.pdm;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lixiaobin on 2017/5/12.
 */
public class PDMDriverTest {
    @Test
    public void getConnectionWithClassPath() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:think:pdm:classpath://classpath.pdm");
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
    }

    @Test
    public void getConnectionWithFile() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:think:pdm:file://file.pdm");
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
    }
}
