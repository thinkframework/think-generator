package org.think.jdbc.pdm;

import org.think.jdbc.xml.AbstractDriver;

import java.sql.*;
import java.util.Properties;

/**
 * Created by lixiaobin on 2017/4/30.
 */
public class PDMDriver extends AbstractDriver implements Driver {

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
}
