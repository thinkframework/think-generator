package io.github.thinkframework.swing.core.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * 对applicationContext.xml的处理类
 */
public class GeneratorXmlUtil {
    public Resource resource = new ClassPathResource("applicationContext.xml");

    public GeneratorXmlUtil(){

    }

    public GeneratorXmlUtil(Resource resource){
        setResource(resource);
    }

    public List<GeneratorDataSourceConfiguration> getDataSourceBeans(){
        List<GeneratorDataSourceConfiguration> list = new ArrayList<GeneratorDataSourceConfiguration>();
        InputStream inputStream = null;
        try {
            String config = File.separator+System.getProperty("user.dir") + File.separator+ "applicationContext.xml";
            inputStream = new FileInputStream(config);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            NodeList nodeList = (NodeList)xPath.evaluate(
                    "/beans/bean[@class=SimpleDataSource " +
                    "or" +
                    " @class='com.alibaba.druid.pool.DruidDataSource']",
                    document, XPathConstants.NODESET);
            for(int i=0;i<nodeList.getLength();i++) {
                GeneratorDataSourceConfiguration generatorDataSourceConfiguration = new GeneratorDataSourceConfiguration();
                Node node = nodeList.item(i);
                String id = (String) xPath.evaluate("@id", nodeList.item(i), XPathConstants.STRING);
                String driverClassName = (String)xPath.evaluate("//property[@name='driverClassName']/@value",node, XPathConstants.STRING);
                String url = (String)xPath.evaluate("//property[@name='url']/@value",node, XPathConstants.STRING);
                String username = (String)xPath.evaluate("//property[@name='username']/@value",node, XPathConstants.STRING);
                String password = (String)xPath.evaluate("//property[@name='password']/@value",node, XPathConstants.STRING);
                NodeList connectPropertiesList = (NodeList)xPath.evaluate("//property[@name='connectProperties']/props/prop",node, XPathConstants.NODESET);
                Properties connectProperties = new Properties();
                for(int j=0;j<connectPropertiesList.getLength();j++){
                    Node temp = connectPropertiesList.item(j);
                    String key = (String)xPath.evaluate("@key",temp, XPathConstants.STRING);
                    String value = (String)xPath.evaluate(".",temp, XPathConstants.STRING);
                    connectProperties.put(key,value);
                }
                generatorDataSourceConfiguration.setId(id);
                generatorDataSourceConfiguration.setDriverClassName(driverClassName);
                generatorDataSourceConfiguration.setUrl(url);
                generatorDataSourceConfiguration.setUsername(username);
                generatorDataSourceConfiguration.setPassword(password);
                generatorDataSourceConfiguration.setConnectProperties(connectProperties);
                list.add(generatorDataSourceConfiguration);
            }
        } catch(ParserConfigurationException | SAXException | IOException | XPathExpressionException exception) {
            exception.printStackTrace();
        } finally {
            try {
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return list;
        }
    }

    public List<String> getDataSourceNames(){
        List<String> dataSourceNames = new ArrayList<>();
        for (GeneratorDataSourceConfiguration generatorDataSourceConfiguration : getDataSourceBeans()){
            dataSourceNames.add(generatorDataSourceConfiguration.getId());
        }
        return dataSourceNames;
    }

    public GeneratorDataSourceConfiguration getDataSourceBean(String dataSourceName){
        GeneratorDataSourceConfiguration generatorDataSourceConfiguration = new GeneratorDataSourceConfiguration();
        try {
            InputStream inputStream = resource.getInputStream();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            Node node = (Node)xPath.evaluate("/beans/bean[@id='"+dataSourceName+"']",document, XPathConstants.NODE);
            String driverClassName = (String)xPath.evaluate("//property[@name='driverClassName']/@value",node, XPathConstants.STRING);
            String url = (String)xPath.evaluate("//property[@name='url']/@value",node, XPathConstants.STRING);
            String username = (String)xPath.evaluate("//property[@name='username']/@value",node, XPathConstants.STRING);
            String password = (String)xPath.evaluate("//property[@name='password']/@value",node, XPathConstants.STRING);
            generatorDataSourceConfiguration.setId(dataSourceName);
            generatorDataSourceConfiguration.setDriverClassName(driverClassName);
            generatorDataSourceConfiguration.setUrl(url);
            generatorDataSourceConfiguration.setUsername(username);
            generatorDataSourceConfiguration.setPassword(password);
        } catch(ParserConfigurationException exception){
            exception.printStackTrace();
        } catch (SAXException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (XPathExpressionException exception) {
            exception.printStackTrace();
        } finally {
            return generatorDataSourceConfiguration;
        }
    }


//    public DataSource getDataSource(String dataSourceName){
//        SimpleDataSource dataSourceBean = new SimpleDataSource();
//        try {
//            InputStream inputStream = resource.getInputStream();
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document = documentBuilder.parse(inputStream);
//            XPathFactory xPathFactory = XPathFactory.newInstance();
//            XPath xPath = xPathFactory.newXPath();
//            Node node = (Node)xPath.evaluate("/beans/bean[@id='"+dataSourceName+"']",document, XPathConstants.NODE);
//            String driverClassName = (String)xPath.evaluate("//property[@name='driverClassName']/@value",node, XPathConstants.STRING);
//            String url = (String)xPath.evaluate("//property[@name='url']/@value",node, XPathConstants.STRING);
//            String username = (String)xPath.evaluate("//property[@name='username']/@value",node, XPathConstants.STRING);
//            String password = (String)xPath.evaluate("//property[@name='password']/@value",node, XPathConstants.STRING);
//            dataSourceBean.setDriverClassName(driverClassName);
//            dataSourceBean.setUrl(url);
//            dataSourceBean.setUsername(username);
//            dataSourceBean.setPassword(password);
//        } catch(ParserConfigurationException exception){
//            exception.printStackTrace();
//        } catch (SAXException exception) {
//            exception.printStackTrace();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        } catch (XPathExpressionException exception) {
//            exception.printStackTrace();
//        } finally {
//            return dataSourceBean;
//        }
//    }

    public void setDataSourceBean(GeneratorDataSourceConfiguration generatorDataSourceConfiguration){
        try {
            InputStream inputStream = resource.getInputStream();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            Node node = (Node)xPath.evaluate("//bean[@id='"+ generatorDataSourceConfiguration.getId()+"']",document, XPathConstants.NODE);

            if(node == null){
                Element element = addDataSource(document, generatorDataSourceConfiguration);
                document.getDocumentElement().appendChild(element);
            }else{
                Element element =  updateDataSource((Element)node, generatorDataSourceConfiguration);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");  //缩进
            transformer.transform(new DOMSource(document),new StreamResult(System.out));
            transformer.transform(new DOMSource(document),new StreamResult(new FileOutputStream(resource.getFile())));
        } catch (IOException | ParserConfigurationException | SAXException| TransformerException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }


    private Element addDataSource(Document document, GeneratorDataSourceConfiguration generatorDataSourceConfiguration){
        Element bean = document.createElement("bean");
        bean.setAttribute("id", generatorDataSourceConfiguration.getId());
        bean.setAttribute("lazy-init","true");

        Element driverClassName = document.createElement("driverClassName");
        driverClassName.setAttribute("name","driverClassName");
        driverClassName.setAttribute("value", generatorDataSourceConfiguration.getDriverClassName());
        bean.appendChild(driverClassName);
		Element url = document.createElement("url");
		url.setAttribute("name","url");
		url.setAttribute("value", generatorDataSourceConfiguration.getUrl());
        bean.appendChild(url);
		Element username = document.createElement("property");
		username.setAttribute("name","username");
		username.setAttribute("value", generatorDataSourceConfiguration.getUsername());
        bean.appendChild(username);
		Element password = document.createElement("property");
		password.setAttribute("name", generatorDataSourceConfiguration.getPassword());
		password.setAttribute("value","${username}");
        bean.appendChild(password);
//        Element initialSize = document.createElement("property");
//        initialSize.setAttribute("name","initialSize");
//        initialSize.setAttribute("value","1");
//        bean.appendChild(initialSize);
//        Element minIdle = document.createElement("property");
//        minIdle.setAttribute("name","minIdle");
//        minIdle.setAttribute("value","1");
//        bean.appendChild(minIdle);
//        Element maxActive = document.createElement("property");
//        maxActive.setAttribute("name","maxActive");
//        maxActive.setAttribute("value","20");
//        bean.appendChild(maxActive);
//        Element maxWait = document.createElement("property");
//        maxWait.setAttribute("name","maxWait");
//        maxWait.setAttribute("value","60000");
//        bean.appendChild(maxWait);
//        Element timeBetweenEvictionRunsMillis = document.createElement("property");
//        timeBetweenEvictionRunsMillis.setAttribute("name","timeBetweenEvictionRunsMillis");
//        timeBetweenEvictionRunsMillis.setAttribute("value","60000");
//        bean.appendChild(timeBetweenEvictionRunsMillis);
//        Element minEvictableIdleTimeMillis = document.createElement("property");
//        minEvictableIdleTimeMillis.setAttribute("name","minEvictableIdleTimeMillis");
//        minEvictableIdleTimeMillis.setAttribute("value","300000");
//        bean.appendChild(minEvictableIdleTimeMillis);
//        Element validationQuery = document.createElement("property");
//        validationQuery.setAttribute("name","validationQuery");
//        validationQuery.setAttribute("value","SELECT 'x'");
//        bean.appendChild(validationQuery);
//        Element testWhileIdle = document.createElement("property");
//        testWhileIdle.setAttribute("name","testWhileIdle");
//        testWhileIdle.setAttribute("value","true");
//        bean.appendChild(testWhileIdle);
//        Element testOnBorrow = document.createElement("property");
//        testOnBorrow.setAttribute("name","testOnBorrow");
//        testOnBorrow.setAttribute("value","false");
//        bean.appendChild(testOnBorrow);
//        Element testOnReturn = document.createElement("property");
//        testOnReturn.setAttribute("name","testOnReturn");
//        testOnReturn.setAttribute("value","false");
//        bean.appendChild(testOnReturn);
//        Element poolPreparedStatements = document.createElement("property");
//        poolPreparedStatements.setAttribute("name","poolPreparedStatements");
//        poolPreparedStatements.setAttribute("value","true");
//        bean.appendChild(poolPreparedStatements);
//        Element maxPoolPreparedStatementPerConnectionSize = document.createElement("property");
//        maxPoolPreparedStatementPerConnectionSize.setAttribute("name","maxPoolPreparedStatementPerConnectionSize");
//        maxPoolPreparedStatementPerConnectionSize.setAttribute("value","20");
//        bean.appendChild(maxPoolPreparedStatementPerConnectionSize);
//        Element filters = document.createElement("property");
//        filters.setAttribute("name","filters");
//        filters.setAttribute("value","stat");
//        bean.appendChild(filters);
        return bean;
    }

    private Element updateDataSource(Element element, GeneratorDataSourceConfiguration generatorDataSourceConfiguration){
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        try {
            Element driverClassNameElement = (Element)xPath.evaluate("//property[@name='driverClassName']",element, XPathConstants.NODE);
            driverClassNameElement.setNodeValue(generatorDataSourceConfiguration.getDriverClassName());
            Element urlElement = (Element)xPath.evaluate("//property[@name='url']",element, XPathConstants.NODE);
            urlElement.setNodeValue(generatorDataSourceConfiguration.getUrl());
            Element usernameElement = (Element)xPath.evaluate("//property[@name='username']",element, XPathConstants.NODE);
            usernameElement.setNodeValue(generatorDataSourceConfiguration.getUsername());
            Element passwordElement = (Element)xPath.evaluate("//property[@name='password']",element, XPathConstants.NODE);
            passwordElement.setNodeValue(generatorDataSourceConfiguration.getPassword());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return element;
    }

    public GeneratorDataSourceConfiguration newDataSource(){
        GeneratorDataSourceConfiguration generatorDataSourceConfiguration = new GeneratorDataSourceConfiguration();
        generatorDataSourceConfiguration.setId(UUID.randomUUID().toString());
        return generatorDataSourceConfiguration;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public static class GeneratorDataSourceConfiguration {
        private String id;
        private String driverClassName;
        private String url;
        private String username;
        private String password;
        private Properties connectProperties;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Properties getConnectProperties() {
            return connectProperties;
        }

        public void setConnectProperties(Properties connectProperties) {
            this.connectProperties = connectProperties;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
