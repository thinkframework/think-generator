package org.think.generator.context;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * ThreadLocal Mediator（中介者）.
 * 线程绑定
 */
public class GeneratorContext{
	private GeneratorProperties generatorProperties;
	private DataSource dataSource;
	static ThreadLocal<GeneratorContext> context = new ThreadLocal<GeneratorContext>();

	/**
	 * 获取线程绑定的GeneratorContext
	 * @return GeneratorContext
	 */
	public static  GeneratorContext getContext(){
		GeneratorContext generatorContext = context.get();
		if(generatorContext == null){
			generatorContext = new GeneratorContext();
			setContext(generatorContext);
		}

		return context.get();
	}

	public static void setContext(GeneratorContext generatorContext){
		context.set(generatorContext);
	}

	public GeneratorContext(){

	}
	public GeneratorContext(DataSource dataSource,String tableName){
		this(dataSource,new Properties(),tableName);
	}

	public GeneratorContext(DataSource dataSource,Properties properties,String tableName){
		if(dataSource != null) {
			this.dataSource = dataSource;
		}
		if(properties == null){
			properties = new Properties();
		}
		properties.setProperty("tableName",tableName);
		generatorProperties = new GeneratorProperties(properties);
	}

	public void setProperties(Properties properties) {
		generatorProperties = new GeneratorProperties();
		generatorProperties.setProperties(properties);
	}

	protected GeneratorProperties getGeneratorProperties() {
		return generatorProperties;
	}

	protected void setGeneratorProperties(GeneratorProperties generatorProperties) {
		this.generatorProperties = generatorProperties;
	}

	public Properties getProperties() {
		return getGeneratorProperties().getProperties();
	}

	public Object getProperty(String key){
		return getProperties().getProperty(key);
	}

	public Object getProperty(String key,String defaultValue){
		return getProperties().getProperty(key,defaultValue);
	}

	public void setProperty(String key,String value) {
		getProperties().setProperty(key, value);
	}

	public DataSource getDataSource(){
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getTableName() {
		return generatorProperties.getProperty("tableName");
	}

	public void setTableName(String tableName) {
		generatorProperties.setProperty("tableName",tableName);
	}
}