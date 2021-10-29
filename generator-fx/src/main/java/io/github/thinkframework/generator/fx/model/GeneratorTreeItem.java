package io.github.thinkframework.generator.fx.model;

import javafx.scene.control.TreeItem;

public class GeneratorTreeItem<T> extends TreeItem<T> {
    private String datasource;
    private String tableType;

    public GeneratorTreeItem(T value, String datasource, String tableType) {
        super(value);
        this.datasource = datasource;
        this.tableType = tableType;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
}