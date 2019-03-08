package io.github.thinkframework.generator.context;

import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.util.TypesUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public class GeneratorProperties {

    private static final String CLASS_PATH = "classpath://";
    private static final String FILE = "file://";

    private String clonfigLocation = "classpath://applicationContext.xml";
    private String dataSource = "dataSource";

    private Properties properties = new Properties();
    private List<String> prefixs = new ArrayList<>();
    private List<String> ignores = new ArrayList<>();

    public GeneratorProperties() {
        this(new Properties());
    }

    public GeneratorProperties(Properties properties) {
        this(properties, properties.getProperty("configLocation", "classpath://applicationContext.xml"),
                properties.getProperty("dataSource", "dataSource"),
                properties.getProperty("tableName"));
    }

    public GeneratorProperties(Properties properties, String configLocation, String dataSource,String tableName) {
        this.clonfigLocation = configLocation;
        this.dataSource = dataSource;
        if (properties != null) {
            this.properties.putAll(properties);
        }

        try (InputStream inputStream = getInputStream(configLocation)){
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            Element element = (Element) xPath.evaluate("//*[local-name()='generator']", document, XPathConstants.NODE);
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

//			InputStream inputStream = GeneratorProperties.class.getClassLoader().getResourceAsStream(GeneratorEnum.DEFAULT_APPLICATION_CONTEXT_PROPERTIES.value());
//			loadProperties.load(inputStream);
            this.properties.putAll(loadProperties);
        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            throw new GeneratorRuntimeException("IO异常", e);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            throw new GeneratorRuntimeException("反射异常", e);
        }
        initProperties();
    }

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

    protected Properties getProperties() {
        return properties;
    }

    protected void setProperties(Properties properties) {
        this.properties = properties;
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
}
