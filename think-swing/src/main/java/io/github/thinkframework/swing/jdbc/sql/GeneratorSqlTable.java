package io.github.thinkframework.swing.jdbc.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 内部使用的,基于SqlTableModel的可编辑的JTable
 * @see SqlTableModel
 * @author lixiaobin
 */
class GeneratorSqlTable extends JTable{
    private Logger logger = LoggerFactory.getLogger(getClass());
    private DataSource dataSource;
    private String sql;
    private GeneratorSqlTable(){

    }

    public GeneratorSqlTable(DataSource dataSource, String sql){
        super(new SqlTableModel(dataSource,sql),null,null);
        this.dataSource = dataSource;
        this.sql = sql;
//        execute();
    }

    private void execute(){
        Connection connection=null;
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            boolean result = statement.execute(sql);
            if(result) {
                ResultSet resultSet = statement.getResultSet();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int count = resultSetMetaData.getColumnCount();
                List<String> columns = new ArrayList<>();
                for (int i = 1; i <= count; i++) {
                    columns.add(resultSetMetaData.getColumnName(i));
                }
                getCustomTableModel().setColumnCount(columns.size());
                getCustomTableModel().setColumnIdentifiers(columns.toArray());
                getCustomTableModel().executeQuery();
            }else{
                int count = statement.getUpdateCount();
                Vector<String> data = new Vector<>();
                data.add("COUNT");
                Vector<String> columnNames = new Vector<>();
                columnNames.add(Integer.toString(count));
                //FIXME 这是啥?
//                setModel(new DefaultTableModel(data,columnNames));
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

    public void acceptChanges(){
        getCustomTableModel().acceptChanges();
    }

    protected SqlTableModel getCustomTableModel(){
        return (SqlTableModel)getModel();
    }
}
