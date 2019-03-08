package io.github.thinkframework.swing.jdbc.meta.table;

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
 * 简单的根据DatabaseMetaData信息生成TableModel
 * @author lixiaobin
 */
class MetaTableModel extends AbstractTableModel {
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

    private MetaTableModel(){

    }

    public MetaTableModel(DataSource dataSource,String tableName){
        super();

        try (Connection connection = dataSource.getConnection()){
            ResultSet rs = connection.getMetaData().getColumns(null, null, tableName, "%");
            int i=0;
            while (rs.next()) {
                List<String> list= new ArrayList<String>();
                list.add(Integer.toString(rs.getInt(ORDINAL_POSITION)));
                list.add(rs.getString(COLUMN_NAME));
                list.add(rs.getString(TYPE_NAME));
//                //TYPE_NAME String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
//                list.add(rs.getString(TYPE_NAME));
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

    @Override
    public String getColumnName(int column) {
        return columns[column];
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
}
