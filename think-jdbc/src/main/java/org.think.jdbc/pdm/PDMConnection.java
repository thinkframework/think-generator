package org.think.jdbc.pdm;

import org.think.jdbc.xml.AbstractConnection;

import java.sql.*;
import java.util.Properties;

/**
 * Created by lixiaobin on 2017/4/25.
 */
class PDMConnection extends AbstractConnection implements Connection{

    public PDMConnection(String url,Properties properties) {
        super(url,properties);
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        if(databaseMetaData == null){
            databaseMetaData = new PDMDatabaseMetaData(url);
        }
        return databaseMetaData;
    }
}
