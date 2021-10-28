package io.github.thinkframework.generator.fx.controler.table;

import io.github.thinkframework.generator.fx.model.Line;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GeneratorTableController implements Initializable, ApplicationContextAware, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(GeneratorTableController.class);

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn ordinalPosition;

    @FXML
    private TableColumn columnName;

    @FXML
    private TableColumn typeName;

    @FXML
    private TableColumn decimalDigits;

    @FXML
    private TableColumn columnSize;

    @FXML
    private TableColumn isNullable;

    @FXML
    private TableColumn columnDef;

    @FXML
    private TableColumn remarks;

    private ApplicationContext applicationContext;

    private DataSource dataSource;

    private String tableName;


    ObservableList<Line> list= FXCollections.observableArrayList();

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ordinalPosition.setCellValueFactory(new PropertyValueFactory<>("ordinalPosition"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("columnName"));
        typeName.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        decimalDigits.setCellValueFactory(new PropertyValueFactory<>("decimalDigits"));
        columnSize.setCellValueFactory(new PropertyValueFactory<>("columnSize"));
        isNullable.setCellValueFactory(new PropertyValueFactory<>("isNullable"));
        columnDef.setCellValueFactory(new PropertyValueFactory<>("columnDef"));
        remarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        tableView.setItems(list);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
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


    @Override
    public void afterPropertiesSet() throws Exception {
        try (Connection connection = dataSource.getConnection()){
            String schema = null;
            try {
                schema = connection.getSchema();
            } catch (SQLException e) {
                log.error(e.getClass().getName());
            }
            ResultSet rs = connection.getMetaData().getColumns(connection.getCatalog(), schema, tableName, "%");
            while (rs.next()) {
                Line line = new Line();
                line.setOrdinalPosition(Integer.toString(rs.getInt("ORDINAL_POSITION")));
                line.setColumnName(rs.getString("COLUMN_NAME"));
                line.setTypeName(rs.getString("TYPE_NAME"));
//                //TYPE_NAME String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
//                get.add(rs.getString(TYPE_NAME));
                //COLUMN_SIZE int => 列的大小
                line.setColumnSize(Integer.toString(rs.getInt("COLUMN_SIZE")));
                line.setDecimalDigits(Integer.toString(rs.getInt("DECIMAL_DIGITS")));
                line.setIsNullable(rs.getString("IS_NULLABLE"));
//                //COLUMN_DEF String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
                line.setColumnDef(rs.getString("COLUMN_DEF"));

                //REMARKS String => 描述列的注释（可为 null）
                line.setRemarks(rs.getString("REMARKS"));
                list.add(line);
            }
        } catch (SQLException e) {
            log.error("",e);
        }
    }
}
