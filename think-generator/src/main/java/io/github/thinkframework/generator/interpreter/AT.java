package io.github.thinkframework.generator.interpreter;

public class AT implements Expression {

    private String tableName;

    private String dataSourceName;

    public boolean interpret(Context ctx){
//        DataSource datasource = ctx.getBeanFactory().getBean(dataSourceName, DataSource.class);
        return false;
    }
}
