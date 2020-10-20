package io.github.thinkframework.generator.swing.comp.sql;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 内部使用的一个基于CachedRowSet的DefaultTableModel
 * @see javax.sql.rowset.CachedRowSet
 * @author lixiaobin
 */
public class GeneratorSqlModel extends AbstractTableModel {

    private Log log = LogFactory.getLog(getClass());
    List<String> columns = new ArrayList<>();
    List<List<Object>> rows= new ArrayList<>();

    private DataSource dataSource;
    private String sql;

    @Override
    public String getColumnName(int column) {
        return columns.get(column);
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows.get(rowIndex).get(columnIndex);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void afterPropertiesSet() {
        try (Connection connection = dataSource.getConnection()){
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            for(int i=1;i < resultSet.getMetaData().getColumnCount();i++){
                columns.add(resultSet.getMetaData().getColumnName(i));
            }
            while (resultSet.next()){
                List row = new ArrayList();
                for(int i=0;i < columns.size();i++){
                    row.add(resultSet.getObject(columns.get(i)));
                }
                rows.add(row);
            }
        } catch (SQLException e) {
            log.error("",e);
        }
    }
}
