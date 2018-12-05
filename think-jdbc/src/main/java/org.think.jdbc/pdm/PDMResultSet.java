package org.think.jdbc.pdm;

import org.think.jdbc.xml.AbstractResultSet;

import java.sql.*;
import java.util.Optional;

/**
 * Created by lixiaobin on 2017/4/25.
 */
class PDMResultSet extends AbstractResultSet implements ResultSet{

    public PDMResultSet(){
        super();
    }

    public PDMResultSet(Iterable iterable){
        super(iterable);
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        return next.get(columnLabel) == null ? 0 : (int) next.get(columnLabel);
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        return (String) next.get(columnLabel);
    }
}
