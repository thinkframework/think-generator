package io.github.thinkframework.generator.context;

import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.util.TypesUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 配置文件解析.
 * 基于xpath
 * 实现原型设计模式
 * @author lixiaobin
 * @since 1.0.0
 */
public class GeneratorProperties implements Cloneable{

    private static final String CLASS_PATH = "classpath://";
    private static final String FILE = "file://";

    private String clonfigLocation;
    private String clonfigProperties;

    private ApplicationContext applicationContext;

    private Properties properties = new Properties();

    private List<String> prefixs = new ArrayList<>();
    private List<String> ignores = new ArrayList<>();

    private DataSource dataSource;
    private String dataSourceName;
    private String tableName;

    public GeneratorProperties() {
    }

    public GeneratorProperties setProperties(Properties properties){
        this.properties = properties;
        return this;
    }


    public GeneratorProperties applicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
        return this;
    }

    public GeneratorProperties setDefaultProperties(){
        return setProperties(CLASS_PATH+GeneratorEnum.DEFAULT_APPLICATION_CONTEXT_PROPERTIES.value());
    }

    public GeneratorProperties setProperties(String properties){
        this.clonfigProperties = properties;
        return this;
    }


    public GeneratorProperties setDefaultConfiguration(){
        return setConfiguration(CLASS_PATH+GeneratorEnum.DEFAULT_APPLICATION_CONTEXT_XML.value());
    }

    public GeneratorProperties setConfiguration(String configuration){
        this.clonfigLocation = configuration;
        return this;
    }

    public GeneratorProperties putAll(Map map){
        this.properties.putAll(map);
        return this;
    }

    public GeneratorProperties dataSource(DataSource dataSource){
        this.dataSource = dataSource;
        return this;
    }

    public GeneratorProperties setDefaultDataSource(){
        return dataSource("dataSource");
    }

    public GeneratorProperties dataSource(String dataSource){
        this.dataSourceName = dataSource;
        return this;
    }

    public GeneratorProperties tableName(String tableName){
        this.tableName = tableName;
        return this;
    }

//    public GeneratorProperties defaultProperties(){
//        return setDefaultProperties().setDefaultConfiguration().setDefaultDataSource();
//    }

    /**
     * 生成配置信息
     * @return
     */
    public GeneratorProperties build() {
        //1.读取properties文件,默认generator.properties
        Optional.ofNullable(clonfigProperties).ifPresent(properties ->{
            try (InputStream inputStream = getInputStream(properties)) {
                this.properties.load(inputStream);
            } catch (IOException e) {
                throw new GeneratorRuntimeException("IO异常", e);
            }
        });
        //2.读取xml文件,默认applicationContext.xml
        Optional.ofNullable(clonfigLocation).ifPresent(location -> {
            try (InputStream inputStream = getInputStream(location)){
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(inputStream);
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath = xPathFactory.newXPath();
                Element element = (Element) xPath.evaluate("//*[local-name()='generator']/*[local-name()='generatorConfiguration']", document, XPathConstants.NODE);
                String dataSource = xPath.evaluate("dataSource", element);
                String core = xPath.evaluate("core", element);
                String frameName = xPath.evaluate("frameName", element);
                String companyName = xPath.evaluate("companyName", element);
                String appName = xPath.evaluate("appName", element);
                String moduleName = xPath.evaluate("moduleName", element);
                String authorName = xPath.evaluate("authorName", element);
                String namespace = xPath.evaluate("namespace", element);
                String template = xPath.evaluate("template", element);
                String extensions = xPath.evaluate("extensions", element);
                NodeList prefixsList = (NodeList) xPath.evaluate("prefixs/list/value", element,XPathConstants.NODESET);
                String prefix ="",ignore="";
                for(int i=0;i<prefixsList.getLength();i++){
                    prefixs.add(xPath.evaluate("text()", prefixsList.item(i)));
                    prefix += xPath.evaluate("text()", prefixsList.item(i)) + ",";
                }
                NodeList ignoresList = (NodeList) xPath.evaluate("ignores/list/value", element,XPathConstants.NODESET);
                for(int i=0;i<ignoresList.getLength();i++){
                    ignores.add(xPath.evaluate("text()", ignoresList.item(i)));
                    ignore += xPath.evaluate("text()", ignoresList.item(i)) + ",";
                }

                NodeList convertsList = (NodeList) xPath.evaluate("converts/props/prop", element,XPathConstants.NODESET);
                for(int i=0;i<convertsList.getLength();i++){
                    Integer type = Types.class.getField(xPath.evaluate("@key", convertsList.item(i)).replace("java.sql.Types.",""))
                            .getInt(Types.class);
                    Class clazz = Class.forName(xPath.evaluate("text()", convertsList.item(i)));
                    TypesUtils.put(type, clazz);
                }
                convertsList = (NodeList) xPath.evaluate("converts/map/entity", element,XPathConstants.NODESET);
                for(int i=0;i<convertsList.getLength();i++){
                    Integer type = Types.class.getField(xPath.evaluate("@key", convertsList.item(i)).replace("java.sql.Types.",""))
                            .getInt(Types.class);
                    Class clazz = Class.forName(xPath.evaluate("@value", convertsList.item(i)));
                    TypesUtils.put(type, clazz);
                }

                String output = xPath.evaluate("output", element);

                Properties loadProperties = new Properties();
                loadProperties.put("dataSource", dataSource);
                loadProperties.put("core", core);
                loadProperties.put("frameName", frameName);
                loadProperties.put("companyName", companyName);
                loadProperties.put("appName", appName);
                loadProperties.put("moduleName", moduleName);
                loadProperties.put("authorName", authorName);
                loadProperties.put("namespace", namespace);
                loadProperties.put("template", template);
                loadProperties.put("extensions", extensions);
                loadProperties.put("prefix", prefix.length() == 0 ? "" : prefix.substring(0,prefix.length()-1));
                loadProperties.put("ignore", ignore.length() == 0 ? "" : ignore.substring(0,ignore.length()-1));
                loadProperties.put("output", output);

                this.properties.putAll(loadProperties);
            } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
                throw new GeneratorRuntimeException("IO异常", e);
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
                throw new GeneratorRuntimeException("反射异常", e);
            }
        });
        initProperties();
        //从spring容器获取数据源
        if(dataSource == null && applicationContext != null && dataSourceName != null){
            dataSource = (DataSource) applicationContext.getBean(dataSourceName);
        }
        Assert.notNull(dataSource,"数据源不能为空");
        return this;
    }

    /**
     * 扩展属性
     * @return
     */
    protected Properties initProperties() {
        Iterator iterator = System.getProperties().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            properties.put("env_" + ((String) entry.getKey()).replaceAll("\\.", "_"), entry.getValue());
        }

        String now_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        properties.put("now_yyyyMMddHHmmss", now_yyyyMMddHHmmss);

        Properties path = new Properties();
        for (Map.Entry entity : properties.entrySet()) {
            String key = (String) entity.getKey();
            String value = (String) entity.getValue();
            path.put(key.replace("generator.", ""), value);
            String key_path = key.replace("generator.", "") + "_path";
            String value_path = value.replace(".", "/");
            path.put(key_path, value_path);
        }
        properties.putAll(path);

        this.properties = path;

        return properties;
    }

    public String getProperty(String key) {
        return getProperty(key, "");
    }

    public String getProperty(String key, String defaultValue) {
        return getProperties().getProperty(key, defaultValue);
    }

    public void setProperty(String key) {
        setProperty(key, "");
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        //处理文件路径
        properties.setProperty(key + "_path", value.replace(".", "/"));
    }

    public Properties getProperties() {
        return properties;
    }

    protected InputStream getInputStream(String configLocation) throws GeneratorRuntimeException {
        if(configLocation.startsWith(CLASS_PATH)){
            return GeneratorProperties.class.getClassLoader().getResourceAsStream(configLocation.substring(CLASS_PATH.length()));
        }else if(configLocation.startsWith(FILE)){
            try {
                return new FileInputStream(configLocation.substring(FILE.length()));
            }catch(FileNotFoundException e){
                throw new GeneratorRuntimeException("文件未找到",e);
            }
        }
        return null;
    }


    public DataSource getDataSource() {
        return dataSource;
    }

    public GeneratorProperties setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        setProperty("tableName",tableName);
    }

    @Override
    public GeneratorProperties clone() throws CloneNotSupportedException {
        GeneratorProperties clone =  (GeneratorProperties) super.clone();
        clone.properties = (Properties) clone.properties.clone();
        clone.tableName = null;
        return  clone;
    }
}
