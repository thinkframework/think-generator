package io.github.thinkframework.generator.fx.view.table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 简单的根据DatabaseMetaData信息生成TableModel
 * @author hdhxby
 */
public class GeneratorTableModel extends AbstractTableModel{

    private DataSource dataSource;

    private String tableName;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
    private Log log = LogFactory.getLog(getClass());
    private String ORDINAL_POSITION = "ORDINAL_POSITION";
    private String COLUMN_NAME = "COLUMN_NAME";
    private String TYPE_NAME = "TYPE_NAME";
    private String DECIMAL_DIGITS = "DECIMAL_DIGITS";
    private String COLUMN_SIZE = "COLUMN_SIZE";
    private String IS_NULLABLE =        "IS_NULLABLE";
    private String COLUMN_DEF = "COLUMN_DEF";
    private String REMARKS = "REMARKS";
    String[] columns = new String[] {ORDINAL_POSITION,
            COLUMN_NAME,
            TYPE_NAME,
            COLUMN_SIZE,
            DECIMAL_DIGITS,
            IS_NULLABLE,
            COLUMN_DEF,
            REMARKS};
    List<List<String>> rows= new ArrayList<>();

    public GeneratorTableModel(DataSource dataSource, String tableName) {
        super();
        this.dataSource = dataSource;
        this.tableName = tableName;
    }

    @Override
    public String getColumnName(int column) {
        return resourceBundle.getString(columns[column]);
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void afterPropertiesSet() {
        try (Connection connection = dataSource.getConnection()){
            String schema = null;
            try {
                schema = connection.getSchema();
            } catch (SQLException e) {
                log.error(e.getClass().getName());
            }
            ResultSet rs = connection.getMetaData().getColumns(connection.getCatalog(), schema, tableName, "%");
            while (rs.next()) {
                List<String> list= new ArrayList<>();
                list.add(Integer.toString(rs.getInt(ORDINAL_POSITION)));
                list.add(rs.getString(COLUMN_NAME));
                list.add(rs.getString(TYPE_NAME));
//                //TYPE_NAME String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
//                get.add(rs.getString(TYPE_NAME));
                //COLUMN_SIZE int => 列的大小
                list.add(Integer.toString(rs.getInt(COLUMN_SIZE)));
                list.add(Integer.toString(rs.getInt(DECIMAL_DIGITS)));
                list.add(rs.getString(IS_NULLABLE));
//                //COLUMN_DEF String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
                list.add(rs.getString(COLUMN_DEF));

                //REMARKS String => 描述列的注释（可为 null）
                list.add(rs.getString(REMARKS));
                rows.add(list);
            }
        } catch (SQLException e) {
            log.error("",e);
        }
    }
}
