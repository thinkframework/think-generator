package io.github.thinkframework.swing.event;

import org.springframework.context.ApplicationEvent;

/**
 * 列表事件
 */
public class ListEvent extends ApplicationEvent {
    private String tableType;
    private String tableName;

    public ListEvent(Object source) {
        super(source);
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
