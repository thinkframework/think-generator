package io.github.thinkframework.jdbc.xml;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by lixiaobin on 2017/4/30.
 */
public abstract class AbstractDriver implements Driver {

    @Override
    public abstract Connection connect(String url, Properties info) throws SQLException;

    @Override
    public abstract boolean acceptsURL(String url) throws SQLException;

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        throw new SQLException();
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
        throw new SQLFeatureNotSupportedException();
    }
}
