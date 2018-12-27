package org.think.swing.jdbc.sql;

import com.sun.rowset.CachedRowSetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * 内部使用的一个基于CachedRowSet的DefaultTableModel
 * @see javax.sql.rowset.CachedRowSet
 * @author lixiaobin
 */
class SqlTableModel extends DefaultTableModel {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private DataSource dataSource;
    private String sql;
    private CachedRowSet cachedRowSet;

    private SqlTableModel(){

    }

    public SqlTableModel(DataSource dataSource,String sql){
        this.dataSource = dataSource;
        this.sql = sql;
    }
    public void executeQuery(){
        Connection connection=null;
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            boolean result = statement.execute(sql);
            if(result) {
                cachedRowSet = new CachedRowSetImpl();
                cachedRowSet.setCommand(sql);
                cachedRowSet.execute(connection);
                while (cachedRowSet.next()) {
                    Vector<String> vector = new Vector<String>();
                    for (int i = 0; i < getColumnCount(); i++) {
                        vector.add(cachedRowSet.getString(getColumnName(i)));
                    }
                    addRow(vector);
                }
            }else{
                int count = statement.getUpdateCount();
                String[] counts = new String[]{"COUNT"};
                setColumnCount(counts.length);
                setColumnIdentifiers(counts);
                Vector<String> vector = new Vector<String>();
                vector.add(Integer.toString(count));
                addRow(vector);
            }
        } catch (SQLException ex) {
            logger.error("",ex);
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("",ex);
                }
            }
        }
    }

    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue,row,column);
        try{
            cachedRowSet.beforeFirst();
            for(int i=0;cachedRowSet.next();i++){
                if(row == i){
                cachedRowSet.updateObject(column,aValue);
                    break;
                }
            }
        } catch (SQLException ex) {
            logger.error("",ex);
        }
    }

    public void acceptChanges(){
        Connection connection=null;
        try{
            connection = dataSource.getConnection();
            cachedRowSet.acceptChanges(connection);
        } catch (SQLException ex) {
            logger.error("",ex);
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("",ex);
                }
            }
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
