package io.github.thinkframework.swing.event;

import org.springframework.context.PayloadApplicationEvent;
import org.springframework.core.ResolvableType;

import java.util.Map;

/**
 * 树事件
 */
public class TreeEvent extends PayloadApplicationEvent<Map<String,String>> {
    private String dataSourceName;
    private String tableType;
    private String tableName;

    public TreeEvent(Object source, Map map) {
        super(source,map);
    }

    public String getDataSourceName() {
        return getPayload().get("dataSourceName");
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getTableType() {
        return getPayload().get("tableType");
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTableName() {
        return getPayload().get("tableName");
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(PayloadApplicationEvent.class, ResolvableType.forInstance(getPayload()));
    }
}
