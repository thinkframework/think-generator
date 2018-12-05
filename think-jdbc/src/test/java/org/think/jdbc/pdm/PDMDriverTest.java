package org.think.jdbc.pdm;

import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

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


    @Test
    public void getTables() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:think:pdm:file://file.pdm");
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
        ResultSet resultSet = databaseMetaData.getTables(null,null,"project_info",null);
        while (resultSet.next()){
            System.out.println(resultSet.getString("TABLE_NAME"));
        }
    }


    @Test
    public void getColumns() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:think:pdm:file://file.pdm");
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
        ResultSet resultSet = databaseMetaData.getColumns(null,null,"project_info",null);
        while (resultSet.next()){
            System.out.println(resultSet.getString("COLUMN_NAME"));
        }
    }
}
