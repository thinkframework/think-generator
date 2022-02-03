package io.github.thinkframework.generator.fx.controler;

import io.github.thinkframework.generator.fx.model.GeneratorTableItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GeneratorTableController implements Initializable {

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

    private DataSource dataSource;

    private String tableName;

    public GeneratorTableController(DataSource dataSource, String tableName) {
        this.dataSource = dataSource;
        this.tableName = tableName;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 设置列绑定关系
        ordinalPosition.setCellValueFactory(new PropertyValueFactory<>("ordinalPosition"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("columnName"));
        typeName.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        decimalDigits.setCellValueFactory(new PropertyValueFactory<>("decimalDigits"));
        columnSize.setCellValueFactory(new PropertyValueFactory<>("columnSize"));
        isNullable.setCellValueFactory(new PropertyValueFactory<>("isNullable"));
        columnDef.setCellValueFactory(new PropertyValueFactory<>("columnDef"));
        remarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        // 获取列信息
        ObservableList<GeneratorTableItem> list= FXCollections.observableArrayList();
        try (Connection connection = dataSource.getConnection()){
            ResultSet rs = connection.getMetaData().getColumns(connection.getCatalog(), connection.getSchema(), tableName, "%");
            while (rs.next()) {
                GeneratorTableItem generatorTableItem = new GeneratorTableItem();
                generatorTableItem.setOrdinalPosition(Integer.toString(rs.getInt("ORDINAL_POSITION")));
                generatorTableItem.setColumnName(rs.getString("COLUMN_NAME"));
                generatorTableItem.setTypeName(rs.getString("TYPE_NAME"));
//                //TYPE_NAME String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
//                get.add(rs.getString(TYPE_NAME));
                //COLUMN_SIZE int => 列的大小
                generatorTableItem.setColumnSize(Integer.toString(rs.getInt("COLUMN_SIZE")));
                generatorTableItem.setDecimalDigits(Integer.toString(rs.getInt("DECIMAL_DIGITS")));
                generatorTableItem.setIsNullable(rs.getString("IS_NULLABLE"));
//                //COLUMN_DEF String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
                generatorTableItem.setColumnDef(rs.getString("COLUMN_DEF"));

                //REMARKS String => 描述列的注释（可为 null）
                generatorTableItem.setRemarks(rs.getString("REMARKS"));
                list.add(generatorTableItem);
            }
        } catch (SQLException e) {
            log.error("",e);
        }

        tableView.setItems(list);
    }

}
