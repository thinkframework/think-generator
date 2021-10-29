package io.github.thinkframework.generator.pdm;

import io.github.thinkframework.generator.xml.AbstractResultSet;

import java.sql.*;

/**
 * Created by lixiaobin on 2017/4/25.
 */
class PDMResultSet extends AbstractResultSet implements ResultSet{

    PDMResultSet(){
        super();
    }

    PDMResultSet(Iterable iterable){
        super(iterable);
    }

    @Override
    public int getInt(String columnLabel) {
        return next.get(columnLabel) == null ? 0 : (int) next.get(columnLabel);
    }

    @Override
    public String getString(String columnLabel) {
        return (String) next.get(columnLabel);
    }
}
