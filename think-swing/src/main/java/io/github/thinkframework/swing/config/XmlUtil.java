package io.github.thinkframework.swing.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import io.github.thinkframework.jdbc.datasource.SimpleDataSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.sql.DataSource;
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
public class XmlUtil {
    public Resource resource = new ClassPathResource("applicationContext.xml");

    public XmlUtil(){

    }

    public XmlUtil(Resource resource){
        setResource(resource);
    }
    
    public List<DataSourceBean> getDataSourceBeans(){
        List<DataSourceBean> list = new ArrayList<DataSourceBean>();
        InputStream inputStream = null;
        try {
            String config = File.separator+System.getProperty("user.dir") + File.separator+"applicationContext.xml";
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
                DataSourceBean dataSourceBean = new DataSourceBean();
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
                dataSourceBean.setId(id);
                dataSourceBean.setDriverClassName(driverClassName);
                dataSourceBean.setUrl(url);
                dataSourceBean.setUsername(username);
                dataSourceBean.setPassword(password);
                dataSourceBean.setConnectProperties(connectProperties);
                list.add(dataSourceBean);
            }
        } catch(ParserConfigurationException exception){
            exception.printStackTrace();
        } catch (SAXException exception) {
            exception.printStackTrace();
        } catch (FileNotFoundException exception){
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (XPathExpressionException exception) {
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
        List<String> dataSourceNames = new ArrayList<String>();
        for (DataSourceBean dataSourceBean : getDataSourceBeans()){
            dataSourceNames.add(dataSourceBean.getId());
        }
        return dataSourceNames;
    }
    public DataSourceBean getDataSourceBean(String dataSourceName){
        DataSourceBean dataSourceBean = new DataSourceBean();
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
            dataSourceBean.setId(dataSourceName);
            dataSourceBean.setDriverClassName(driverClassName);
            dataSourceBean.setUrl(url);
            dataSourceBean.setUsername(username);
            dataSourceBean.setPassword(password);
        } catch(ParserConfigurationException exception){
            exception.printStackTrace();
        } catch (SAXException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (XPathExpressionException exception) {
            exception.printStackTrace();
        } finally {
            return dataSourceBean;
        }
    }


    public DataSource getDataSource(String dataSourceName){
        SimpleDataSource dataSourceBean = new SimpleDataSource();
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
            dataSourceBean.setDriverClassName(driverClassName);
            dataSourceBean.setUrl(url);
            dataSourceBean.setUsername(username);
            dataSourceBean.setPassword(password);
        } catch(ParserConfigurationException exception){
            exception.printStackTrace();
        } catch (SAXException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (XPathExpressionException exception) {
            exception.printStackTrace();
        } finally {
            return dataSourceBean;
        }
    }

    public void setDataSourceBean(DataSourceBean dataSourceBean){
        try {
            InputStream inputStream = resource.getInputStream();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            Node node = (Node)xPath.evaluate("//bean[@id='"+dataSourceBean.getId()+"']",document, XPathConstants.NODE);

            if(node == null){
                Element element = addDataSource(document,dataSourceBean);
                document.getDocumentElement().appendChild(element);
            }else{
                Element element =  updateDataSource((Element)node,dataSourceBean);
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


    private Element addDataSource(Document document,DataSourceBean dataSourceBean){
        Element bean = document.createElement("bean");
        bean.setAttribute("id",dataSourceBean.getId());

        bean.setAttribute("initProperties-method","initProperties");
        bean.setAttribute("destroy-method","close");
        Element driverClassName = document.createElement("driverClassName");
        driverClassName.setAttribute("name","driverClassName");
        driverClassName.setAttribute("value",dataSourceBean.getDriverClassName());
		Element url = document.createElement("url");
		url.setAttribute("name","url");
		url.setAttribute("value",dataSourceBean.getUrl());
		Element username = document.createElement("property");
		username.setAttribute("name","username");
		username.setAttribute("value",dataSourceBean.getUsername());
		Element password = document.createElement("property");
		password.setAttribute("name",dataSourceBean.getPassword());
		password.setAttribute("value","${username}");
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

    private Element updateDataSource(Element element,DataSourceBean dataSourceBean){
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        try {
            Element driverClassNameElement = (Element)xPath.evaluate("//property[@name='driverClassName']",element, XPathConstants.NODE);
            driverClassNameElement.setNodeValue(dataSourceBean.getDriverClassName());
            Element urlElement = (Element)xPath.evaluate("//property[@name='url']",element, XPathConstants.NODE);
            urlElement.setNodeValue(dataSourceBean.getUrl());
            Element usernameElement = (Element)xPath.evaluate("//property[@name='username']",element, XPathConstants.NODE);
            usernameElement.setNodeValue(dataSourceBean.getUsername());
            Element passwordElement = (Element)xPath.evaluate("//property[@name='password']",element, XPathConstants.NODE);
            passwordElement.setNodeValue(dataSourceBean.getPassword());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return element;
    }

    public DataSourceBean newDataSource(){
        DataSourceBean dataSourceBean = new DataSourceBean();
        dataSourceBean.setId(UUID.randomUUID().toString());
        return  dataSourceBean;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
