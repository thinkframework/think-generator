package io.github.thinkframework.fx.core.control;

import com.alibaba.druid.pool.DruidDataSource;
import io.github.thinkframework.fx.core.control.config.GeneratorConfigControllerFactoryBean;
import io.github.thinkframework.fx.core.control.table.GeneratorTableControllerFactoryBean;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class MainController implements Initializable, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private ApplicationEventPublisher applicationEventPublisher;

    @FXML
    private ListView listView;

    @FXML
    private TreeView treeView;

    @FXML
    private WebView webView;

    @FXML
    private TabPane swingTabPanel;

    @FXML
    public void about(ActionEvent event){
        try {
            Stage aboutStage = new Stage();
            aboutStage.setScene(new Scene(new  FXMLLoader(getClass().getResource("/io/github/thinkframework/fx/core/control/about.fxml"),null,null,
                param -> applicationContext.getBean(param))
                .load()));
            aboutStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exit(ActionEvent event){
        System.exit(1);
    }

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
        webView.getEngine().load(getClass().getClassLoader().getResource("help.html").toString());

        listView.setItems(Stream.of(applicationContext.getBeanNamesForType(DruidDataSource.class))
            .collect(Collectors.toCollection(FXCollections::observableArrayList)));

        treeView.setRoot(new GeneratorTreeItem());

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            addDatasourcePanel(listView.getSelectionModel().getSelectedItem().toString());
        });

        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            GeneratorTreeItem<GeneratorTreeItem.Value> generatorTreeItem = (GeneratorTreeItem)treeView.getSelectionModel().getSelectedItem();
            if (generatorTreeItem != null) {
                if ("TABLE_NAME".equals(generatorTreeItem.getValue().getColumnLabel())) {//获取表相关的列
                    String dataSourceName = generatorTreeItem.getParent().getParent().getValue().getName();
                    String tableType = generatorTreeItem.getParent().getValue().getName();
                    String tableName = generatorTreeItem.getValue().getName();
                    addTablePanel(dataSourceName,tableName);
                }
            }
        });

        treeView.setOnContextMenuRequested(event1 -> {
            GeneratorTreeItem<GeneratorTreeItem.Value> generatorTreeItem = (GeneratorTreeItem) treeView.getSelectionModel().getSelectedItem();
            if(generatorTreeItem != null && "TABLE_NAME".equals(generatorTreeItem.getValue().getColumnLabel())){
                log.debug(generatorTreeItem.getValue().getColumnLabel());
            }
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void addDatasourcePanel(String dataSourceName) {
        Platform.runLater(() -> {
            int count = swingTabPanel.getTabs().size();
            boolean exists = false;
            for (int i = 0; i < count; i++) {
                if (swingTabPanel.getTabs().get(i).getText().equals(dataSourceName)) {
                    swingTabPanel.getSelectionModel().select(i);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                try {
                    //设置FactoryBean
                    GeneratorConfigControllerFactoryBean generatorTableFactoryBean = applicationContext.getBean(GeneratorConfigControllerFactoryBean.class);
                    generatorTableFactoryBean.setDataSource(applicationContext.getBean(dataSourceName, DataSource.class));
                    generatorTableFactoryBean.setDatasourceName(dataSourceName);
                    Tab tab = new Tab(dataSourceName,new  FXMLLoader(getClass().getResource("/io/github/thinkframework/fx/core/control/config.fxml"),null,null,
                        param -> applicationContext.getBean(param))
                        .load());
                    swingTabPanel.getTabs().add(tab);
                    swingTabPanel.getSelectionModel().select(tab);
                } catch (Exception e) {
                    log.error("",e);
                }
            }
        });
    }

    private void addTablePanel(String dataSourceName, String tableName) {
        Platform.runLater(() -> {
            int count = swingTabPanel.getTabs().size();
            boolean exists = false;
            for (int i = 0; i < count; i++) {
                if (swingTabPanel.getTabs().get(i).getText().equals(tableName)) {
                    swingTabPanel.getSelectionModel().select(i);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                try {
                    //设置FactoryBean
                    GeneratorTableControllerFactoryBean generatorTableFactoryBean = applicationContext.getBean(GeneratorTableControllerFactoryBean.class);
                    generatorTableFactoryBean.setDataSource(applicationContext.getBean(dataSourceName, DataSource.class));
                    generatorTableFactoryBean.setTableName(tableName);
                    //获取GeneratorTable
                    TableView generatorTableView = new  FXMLLoader(getClass().getResource("/io/github/thinkframework/fx/core/control/table.fxml"),null,null,
                        param -> applicationContext.getBean(param))
                        .load();
                    Tab tab = new Tab(tableName,generatorTableView);
                    swingTabPanel.getTabs().add(tab);
                    swingTabPanel.getSelectionModel().select(tab);
                } catch (Exception e) {
                    log.error("",e);
                }
            }
        });
    }


    @FXML
    public void generator(ActionEvent event) {
        ObservableList<GeneratorTreeItem<GeneratorTreeItem.Value>> treePaths = treeView.getSelectionModel().getSelectedItems();
        if (treePaths == null) {
            return;
        }
        for (int i = 0; i < treePaths.size(); i++) {
            GeneratorTreeItem<GeneratorTreeItem.Value> defaultMutableTreeNode = treePaths.get(i);
            try {
                if(!"TABLE_NAME".equals(defaultMutableTreeNode.getValue().getColumnLabel())){
                    continue;
                }
                Map<String,String> map = new HashMap<>();
                map.put("dataSourceName",defaultMutableTreeNode.getParent().getParent().getValue().getName());
                map.put("tableType",defaultMutableTreeNode.getParent().getValue().getName());
                map.put("tableName",defaultMutableTreeNode.getValue().getName());
                PayloadApplicationEvent payloadApplicationEvent = new PayloadApplicationEvent("generator",map);

                applicationEventPublisher.publishEvent(payloadApplicationEvent);

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
    }

    class GeneratorTreeItem<T extends GeneratorTreeItem.Value> extends TreeItem<T> {

        public GeneratorTreeItem() {
            super();
            setValue((T)new GeneratorTreeItem.Value("dataSource"));
        }

        public GeneratorTreeItem(T value) {
            super(value);
        }

        public GeneratorTreeItem(T value, Node graphic) {
            super(value, graphic);
        }

        @Override
        public boolean isLeaf() {
            return getValue().isLeaf();
        }

        @Override
        public ObservableList<TreeItem<T>> getChildren() {
            return children != null ? children : children();
        }


        @Override public String toString() {
            return getValue().getName();
        }

        ObservableList<TreeItem<T>> children ;

        protected ObservableList<TreeItem<T>> children() {
            children = super.getChildren();
            if (children.isEmpty()) {
                if (getValue().getColumnLabel() == null) {//获取所有数据源
                    children.addAll(Stream.of(applicationContext.getBeanNamesForType(DataSource.class))
                        .map(value -> (TreeItem<T>)new GeneratorTreeItem(new Value(value,"",false)))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                } else {
                    if ("".equals(getValue().getColumnLabel())) {//获取相关的表类型
                        String dataSourceName = getValue().getName();
                        try (Connection connection = applicationContext.getBean(dataSourceName, DataSource.class).getConnection()) {
                            ResultSet rs = connection.getMetaData().getTableTypes();
                            while (rs.next()) {
                                children.add(new GeneratorTreeItem(new Value(rs.getString("TABLE_TYPE"),  "TABLE_TYPE",false)));
                            }
                        } catch (SQLException ex) {
                            log.error("", ex);
                        }
                    } else if ("TABLE_TYPE".equals(getValue().getColumnLabel())) {//获取相关的表名称
                        String dataSourceName = (getParent()).getValue().getName();
                        try (Connection connection = applicationContext.getBean(dataSourceName, DataSource.class).getConnection()) {
                            String schema = null;
                            try {
                                schema = connection.getSchema();
                            } catch (SQLException e) {
                                log.error("{}", e.getClass().getName());
                            }
                            ResultSet rs = connection.getMetaData().getTables(connection.getCatalog(), schema, "%", new String[]{getValue().getName()});
                            while (rs.next()) {
                                children.add(new GeneratorTreeItem(new Value(rs.getString("TABLE_NAME"), "TABLE_NAME",  true)));
                            }
                        } catch (SQLException ex) {
                            log.error("", ex);
                        }
                    }
                }
            }
            return children;
        }

        @Getter
        @Setter
        class Value{

            private String name;

            private boolean leaf;

            private String columnLabel;

            public Value(String name) {
                this.name = name;
            }

            public Value(String name, String columnLabel) {
                this.name = name;
                this.columnLabel = columnLabel;
            }

            public Value(String name, String columnLabel, boolean leaf) {
                this.name = name;
                this.columnLabel = columnLabel;
                this.leaf = leaf;
            }

            @Override
            public String toString() {
                return name;
            }
        }
    }
}
