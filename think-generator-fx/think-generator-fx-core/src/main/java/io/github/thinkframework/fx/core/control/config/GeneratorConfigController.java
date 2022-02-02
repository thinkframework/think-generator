package io.github.thinkframework.fx.core.control.config;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class GeneratorConfigController implements Initializable, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @FXML
    private TextField idField;

    @FXML
    private TextField driverClassNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextArea connectPropertiesTextArea;

    private String dataSourceName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idField.setText(dataSourceName);
//        DruidDataSource dataSource = applicationContext.getBean(dataSourceName,DruidDataSource.class);
//        driverClassNameField.setText(dataSource.getDriverClassName());
//        urlField.setText(dataSource.getUrl());
//        usernameField.setText(dataSource.getUsername());
//        passwordField.setText(dataSource.getPassword());
//        connectPropertiesTextArea.setText(dataSource.getProperties());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
}
