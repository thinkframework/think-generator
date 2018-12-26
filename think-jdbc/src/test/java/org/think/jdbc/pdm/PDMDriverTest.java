package org.think.jdbc.pdm;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by lixiaobin on 2017/5/12.
 */
public class PDMDriverTest {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test(expected = SQLException.class)
    public void getConnectionWithClassPath() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:think:pdm:classpath://classpath.gsp");
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
    }


    @Test(expected = SQLException.class)
    public void getConnectionWithClassPathNotExists() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:think:pdm:classpath://none.gsp");
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
    }

    @Test
    public void getConnectionWithFile() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:think:pdm:file://file.gsp");
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
    }

    @Test(expected = SQLException.class)
    public void getConnectionWithFileNotExists() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:think:pdm:file://none.gsp");
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
    }


    @Test
    public void getTables() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:think:pdm:file://file.gsp");
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
        ResultSet resultSet = databaseMetaData.getTables(null,null,"project_info",null);
        while (resultSet.next()){
            Assert.assertEquals("project_info",resultSet.getString("COLUMN_NAME"));
            System.out.println(resultSet.getString("TABLE_NAME"));
        }
    }


    @Test
    public void getColumns() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:think:pdm:file://file.gsp");
        Assert.assertNotNull(connection);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        Assert.assertNotNull(databaseMetaData);
        ResultSet resultSet = databaseMetaData.getColumns(null,null,"project_info",null);
        while (resultSet.next()){
            logger.debug(resultSet.getString("TABLE_NAME"));
            logger.debug(resultSet.getString("ORDINAL_POSITION"));
            logger.debug(resultSet.getString("COLUMN_NAME"));
            logger.debug(resultSet.getString("DATA_TYPE"));
            logger.debug(resultSet.getString("TYPE_NAME"));
            logger.debug(resultSet.getString("NUM_PREC_RADIX"));
            logger.debug(resultSet.getString("DECIMAL_DIGITS"));
            logger.debug(resultSet.getString("COLUMN_SIZE"));
            logger.debug(resultSet.getString("NULLABLE"));
            logger.debug(resultSet.getString("IS_NULLABLE"));
            logger.debug(resultSet.getString("COLUMN_DEF"));
            logger.debug(resultSet.getString("REMARKS"));
        }
    }
}
