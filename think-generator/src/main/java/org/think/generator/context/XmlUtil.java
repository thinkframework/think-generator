//package org.think.generator.context;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.think.jdbc.datasource.SimpleDataSource;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import javax.sql.DataSource;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.*;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import javax.xml.xpath.XPath;
//import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathExpressionException;
//import javax.xml.xpath.XPathFactory;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.util.UUID;
//
///**
// * 对applicationContext.xml的处理类
// */
//public class XmlUtil {
//    public Resource resource = new ClassPathResource("applicationContext.xml");
//
//    public XmlUtil(){
//
//    }
//
//    public XmlUtil(Resource resource){
//        setResource(resource);
//    }
//
//    public List<SimpleDataSource> getSimpleDataSources(){
//        List<SimpleDataSource> list = new ArrayList<SimpleDataSource>();
//        InputStream inputStream = null;
//        try {
//            String config = File.separator+System.getProperty("user.dir") + File.separator+"applicationContext.xml";
//            inputStream = new FileInputStream(config);
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document = documentBuilder.parse(inputStream);
//            XPathFactory xPathFactory = XPathFactory.newInstance();
//            XPath xPath = xPathFactory.newXPath();
//            NodeList nodeList = (NodeList)xPath.evaluate(
//                    "/beans/bean[@class='org.think.jdbc.datasource.SimpleDataSource' " +
//                    "or" +
//                    " @class='com.alibaba.druid.pool.DruidDataSource']",
//                    document, XPathConstants.NODESET);
//            for(int i=0;i<nodeList.getLength();i++) {
//                SimpleDataSource SimpleDataSource = new SimpleDataSource();
//                Node node = nodeList.item(i);
//                String id = (String) xPath.evaluate("@id", nodeList.item(i), XPathConstants.STRING);
//                String driverClassName = (String)xPath.evaluate("//property[@name='driverClassName']/@value",node, XPathConstants.STRING);
//                String url = (String)xPath.evaluate("//property[@name='url']/@value",node, XPathConstants.STRING);
//                String username = (String)xPath.evaluate("//property[@name='username']/@value",node, XPathConstants.STRING);
//                String password = (String)xPath.evaluate("//property[@name='password']/@value",node, XPathConstants.STRING);
//                NodeList connectPropertiesList = (NodeList)xPath.evaluate("//property[@name='connectProperties']/props/prop",node, XPathConstants.NODESET);
//                Properties connectProperties = new Properties();
//                for(int j=0;j<connectPropertiesList.getLength();j++){
//                    Node temp = connectPropertiesList.item(j);
//                    String key = (String)xPath.evaluate("@key",temp, XPathConstants.STRING);
//                    String value = (String)xPath.evaluate(".",temp, XPathConstants.STRING);
//                    connectProperties.put(key,value);
//                }
//                SimpleDataSource.setId(id);
//                SimpleDataSource.setDriverClassName(driverClassName);
//                SimpleDataSource.setUrl(url);
//                SimpleDataSource.setUsername(username);
//                SimpleDataSource.setPassword(password);
//                SimpleDataSource.setConnectProperties(connectProperties);
//                list.add(SimpleDataSource);
//            }
//        } catch(ParserConfigurationException exception){
//            exception.printStackTrace();
//        } catch (SAXException exception) {
//            exception.printStackTrace();
//        } catch (FileNotFoundException exception){
//            exception.printStackTrace();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        } catch (XPathExpressionException exception) {
//            exception.printStackTrace();
//        } finally {
//            try {
//                inputStream.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return list;
//        }
//    }
//
//    public List<String> getDataSourceNames(){
//        List<String> dataSourceNames = new ArrayList<String>();
//        for (SimpleDataSource SimpleDataSource : getSimpleDataSources()){
//            dataSourceNames.add(SimpleDataSource.getId());
//        }
//        return dataSourceNames;
//    }
//    public SimpleDataSource getSimpleDataSource(String dataSourceName){
//        SimpleDataSource SimpleDataSource = new SimpleDataSource();
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
//            SimpleDataSource.setId(dataSourceName);
//            SimpleDataSource.setDriverClassName(driverClassName);
//            SimpleDataSource.setUrl(url);
//            SimpleDataSource.setUsername(username);
//            SimpleDataSource.setPassword(password);
//        } catch(ParserConfigurationException exception){
//            exception.printStackTrace();
//        } catch (SAXException exception) {
//            exception.printStackTrace();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        } catch (XPathExpressionException exception) {
//            exception.printStackTrace();
//        } finally {
//            return SimpleDataSource;
//        }
//    }
//
////
////    public DataSource getDataSource(String dataSourceName){
////        SimpleDataSource SimpleDataSource = new SimpleDataSource();
////        try {
////            InputStream inputStream = resource.getInputStream();
////            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
////            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
////            Document document = documentBuilder.parse(inputStream);
////            XPathFactory xPathFactory = XPathFactory.newInstance();
////            XPath xPath = xPathFactory.newXPath();
////            Node node = (Node)xPath.evaluate("/beans/bean[@id='"+dataSourceName+"']",document, XPathConstants.NODE);
////            String driverClassName = (String)xPath.evaluate("//property[@name='driverClassName']/@value",node, XPathConstants.STRING);
////            String url = (String)xPath.evaluate("//property[@name='url']/@value",node, XPathConstants.STRING);
////            String username = (String)xPath.evaluate("//property[@name='username']/@value",node, XPathConstants.STRING);
////            String password = (String)xPath.evaluate("//property[@name='password']/@value",node, XPathConstants.STRING);
////            SimpleDataSource.setDriverClassName(driverClassName);
////            SimpleDataSource.setUrl(url);
////            SimpleDataSource.setUsername(username);
////            SimpleDataSource.setPassword(password);
////        } catch(ParserConfigurationException exception){
////            exception.printStackTrace();
////        } catch (SAXException exception) {
////            exception.printStackTrace();
////        } catch (IOException exception) {
////            exception.printStackTrace();
////        } catch (XPathExpressionException exception) {
////            exception.printStackTrace();
////        } finally {
////            return SimpleDataSource;
////        }
////    }
//
//    public void setSimpleDataSource(SimpleDataSource SimpleDataSource){
//        try {
//            InputStream inputStream = resource.getInputStream();
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document = documentBuilder.parse(inputStream);
//
//            XPathFactory xPathFactory = XPathFactory.newInstance();
//            XPath xPath = xPathFactory.newXPath();
//            Node node = (Node)xPath.evaluate("//bean[@id='"+SimpleDataSource.getId()+"']",document, XPathConstants.NODE);
//
//            if(node == null){
//                Element element = addDataSource(document,SimpleDataSource);
//                document.getDocumentElement().appendChild(element);
//            }else{
//                Element element =  updateDataSource((Element)node,SimpleDataSource);
//            }
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");  //缩进
//            transformer.transform(new DOMSource(document),new StreamResult(System.out));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TransformerConfigurationException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        } catch (XPathExpressionException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private Element addDataSource(Document document,SimpleDataSource SimpleDataSource){
//        Element bean = document.createElement("bean");
//        bean.setAttribute("id",SimpleDataSource.getId());
//
//        bean.setAttribute("initProperties-method","initProperties");
//        bean.setAttribute("destroy-method","close");
//        Element driverClassName = document.createElement("driverClassName");
//        driverClassName.setAttribute("name","driverClassName");
//        driverClassName.setAttribute("value",SimpleDataSource.getDriverClassName());
//		Element url = document.createElement("url");
//		url.setAttribute("name","url");
//		url.setAttribute("value",SimpleDataSource.getUrl());
//		Element username = document.createElement("property");
//		username.setAttribute("name","username");
//		username.setAttribute("value",SimpleDataSource.getUsername());
//		Element password = document.createElement("property");
//		password.setAttribute("name",SimpleDataSource.getPassword());
//		password.setAttribute("value","${username}");
////        Element initialSize = document.createElement("property");
////        initialSize.setAttribute("name","initialSize");
////        initialSize.setAttribute("value","1");
////        bean.appendChild(initialSize);
////        Element minIdle = document.createElement("property");
////        minIdle.setAttribute("name","minIdle");
////        minIdle.setAttribute("value","1");
////        bean.appendChild(minIdle);
////        Element maxActive = document.createElement("property");
////        maxActive.setAttribute("name","maxActive");
////        maxActive.setAttribute("value","20");
////        bean.appendChild(maxActive);
////        Element maxWait = document.createElement("property");
////        maxWait.setAttribute("name","maxWait");
////        maxWait.setAttribute("value","60000");
////        bean.appendChild(maxWait);
////        Element timeBetweenEvictionRunsMillis = document.createElement("property");
////        timeBetweenEvictionRunsMillis.setAttribute("name","timeBetweenEvictionRunsMillis");
////        timeBetweenEvictionRunsMillis.setAttribute("value","60000");
////        bean.appendChild(timeBetweenEvictionRunsMillis);
////        Element minEvictableIdleTimeMillis = document.createElement("property");
////        minEvictableIdleTimeMillis.setAttribute("name","minEvictableIdleTimeMillis");
////        minEvictableIdleTimeMillis.setAttribute("value","300000");
////        bean.appendChild(minEvictableIdleTimeMillis);
////        Element validationQuery = document.createElement("property");
////        validationQuery.setAttribute("name","validationQuery");
////        validationQuery.setAttribute("value","SELECT 'x'");
////        bean.appendChild(validationQuery);
////        Element testWhileIdle = document.createElement("property");
////        testWhileIdle.setAttribute("name","testWhileIdle");
////        testWhileIdle.setAttribute("value","true");
////        bean.appendChild(testWhileIdle);
////        Element testOnBorrow = document.createElement("property");
////        testOnBorrow.setAttribute("name","testOnBorrow");
////        testOnBorrow.setAttribute("value","false");
////        bean.appendChild(testOnBorrow);
////        Element testOnReturn = document.createElement("property");
////        testOnReturn.setAttribute("name","testOnReturn");
////        testOnReturn.setAttribute("value","false");
////        bean.appendChild(testOnReturn);
////        Element poolPreparedStatements = document.createElement("property");
////        poolPreparedStatements.setAttribute("name","poolPreparedStatements");
////        poolPreparedStatements.setAttribute("value","true");
////        bean.appendChild(poolPreparedStatements);
////        Element maxPoolPreparedStatementPerConnectionSize = document.createElement("property");
////        maxPoolPreparedStatementPerConnectionSize.setAttribute("name","maxPoolPreparedStatementPerConnectionSize");
////        maxPoolPreparedStatementPerConnectionSize.setAttribute("value","20");
////        bean.appendChild(maxPoolPreparedStatementPerConnectionSize);
////        Element filters = document.createElement("property");
////        filters.setAttribute("name","filters");
////        filters.setAttribute("value","stat");
////        bean.appendChild(filters);
//        return bean;
//    }
//
//    private Element updateDataSource(Element element,SimpleDataSource SimpleDataSource){
//        XPathFactory xPathFactory = XPathFactory.newInstance();
//        XPath xPath = xPathFactory.newXPath();
//        try {
//            Element driverClassNameElement = (Element)xPath.evaluate("//property[@name='driverClassName']",element, XPathConstants.NODE);
//            driverClassNameElement.setNodeValue(SimpleDataSource.getDriverClassName());
//            Element urlElement = (Element)xPath.evaluate("//property[@name='url']",element, XPathConstants.NODE);
//            urlElement.setNodeValue(SimpleDataSource.getUrl());
//            Element usernameElement = (Element)xPath.evaluate("//property[@name='username']",element, XPathConstants.NODE);
//            usernameElement.setNodeValue(SimpleDataSource.getUsername());
//            Element passwordElement = (Element)xPath.evaluate("//property[@name='password']",element, XPathConstants.NODE);
//            passwordElement.setNodeValue(SimpleDataSource.getPassword());
//        } catch (XPathExpressionException e) {
//            e.printStackTrace();
//        }
//        return element;
//    }
//
//    public SimpleDataSource newDataSource(){
//        SimpleDataSource SimpleDataSource = new SimpleDataSource();
//        SimpleDataSource.setId(UUID.randomUUID().toString());
//        return  SimpleDataSource;
//    }
//
//    public Resource getResource() {
//        return resource;
//    }
//
//    public void setResource(Resource resource) {
//        this.resource = resource;
//    }
//}
