package org.think.jdbc.pdm;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by lixiaobin on 2017/4/30.
 */
public class PDMDriver implements Driver {
    public PDMDriver() throws SQLException {
    }

    static {
        try {
            DriverManager.registerDriver(new PDMDriver());
        } catch (SQLException var1) {
            throw new RuntimeException("Can't register driver!");
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return new PDMConnection(url,info);
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        if(url != null){
            if(url.startsWith("jdbc:think:pdm")){
                return true;
            }
        }
        return false;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 1;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
