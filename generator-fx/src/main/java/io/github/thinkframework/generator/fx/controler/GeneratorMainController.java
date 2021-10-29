package io.github.thinkframework.generator.fx.controler;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.fx.FXApplication;
import io.github.thinkframework.generator.fx.model.GeneratorTreeItem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneratorMainController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(GeneratorMainController.class);

    @FXML
    private ListView listView;

    @FXML
    private TreeView treeView;

    @FXML
    private WebView webView;

    @FXML
    private TabPane swingTabPanel;

    @FXML
    private TabPane datasourceTabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 加载首页
        webView.getEngine().load(getClass().getClassLoader().getResource("help.html").toString());
        // 树形选择事件
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof GeneratorTreeItem) {//获取表相关的列
                GeneratorTreeItem<String> generatorTreeItem = (GeneratorTreeItem) newValue;
                addTablePanel(generatorTreeItem.getDatasource(), generatorTreeItem.getTableType(), generatorTreeItem.getValue());
            }
        });
        // 列表选择事件
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // 数据源
            TreeItem<String> datasource =  new TreeItem(newValue != null ? newValue : oldValue);
            try (Connection connection = FXApplication.getBean((String) newValue, DataSource.class).getConnection()) {
                ResultSet tableTypes = connection.getMetaData().getTableTypes();
                while (tableTypes.next()) {
                    //表类型
                    TreeItem<String> tableType =  new TreeItem(tableTypes.getString("TABLE_TYPE"));
                    ResultSet tables = connection.getMetaData().getTables(connection.getCatalog(), connection.getSchema(), "%", new String[]{tableType.getValue()});
                    while (tables.next()) {
                        // 表
                        TreeItem<String> table = new GeneratorTreeItem(tables.getString("TABLE_NAME"),datasource.getValue(),tableType.getValue());
                        tableType.getChildren().add(table);
                    }
                    datasource.getChildren().add(tableType);
                }
            } catch (SQLException ex) {
                log.error("", ex);
            }

           treeView.setRoot(datasource);

           datasourceTabPane.getSelectionModel().select(1);
        });

        refresh(null);
    }

    private void addTablePanel(String dataSourceName, String tableType, String tableName) {
        Platform.runLater(() -> {
            int count = swingTabPanel.getTabs().size();
            for (int i = 0; i < count; i++) {
                if (swingTabPanel.getTabs().get(i).getText().equals(tableName)) {
                    swingTabPanel.getSelectionModel().select(i);
                    return;
                }
            }

            try {
                //获取GeneratorTable
                TableView generatorTableView = new FXMLLoader(FXApplication.class.getResource("view/table.fxml"),
                        FXApplication.resourceBundle,
                        null,
                        clazz -> {
                            GeneratorTableController tableController = new GeneratorTableController(FXApplication.getBean(dataSourceName, DataSource.class),tableName);
                            return tableController;
                        })
                        .load();
                Tab tab = new Tab(tableName,generatorTableView);
                swingTabPanel.getTabs().add(tab);
                swingTabPanel.getSelectionModel().select(tab);
            } catch (Exception e) {
                log.error("",e);
            }
        });
    }


    @FXML
    public void refresh(ActionEvent event) {
        listView.setItems(Stream.of(FXApplication.getBeanNamesForType(DataSource.class))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }

    @FXML
    public void generator(ActionEvent event) {
        ObservableList<TreeItem> treePaths = treeView.getSelectionModel().getSelectedItems();
        if (treePaths == null) {
            return;
        }
        try {
            for (int i = 0; i < treePaths.size(); i++) {
                TreeItem<String> treeItem = treePaths.get(i);
                if (!(treeItem instanceof GeneratorTreeItem)) {//获取表相关的列
                    continue;
                }
                GeneratorTreeItem<String> generatorTreeItem = (GeneratorTreeItem) treeItem;
                FXApplication.getBean(Generator.class)
                        .generate(FXApplication.getBean(generatorTreeItem.getDatasource(), DataSource.class),
                        generatorTreeItem.getValue());
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("提示");
            alert.setHeaderText("操作成功");
            alert.setContentText("是否打开输出目录?");

            Optional result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
//                    FileSystemUtils.openDirectory(new File(System.getProperty("user.dir")));
            }
        } catch (Exception e) {
            log.error("",e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText("操作失败");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }


    @FXML
    public void exit(ActionEvent event){
        System.exit(1);
    }

}
