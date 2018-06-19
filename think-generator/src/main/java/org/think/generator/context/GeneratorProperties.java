package org.think.generator.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.xml.DomUtils;
import org.think.generator.lang.GeneratorRuntimeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件解析.
 * 基于xpath
 * @author lixiaobin
 * @since 1.0.0
 */
public class GeneratorProperties {
	private Properties properties = new Properties();
	public GeneratorProperties(){
		this(null,null);
	}

	public GeneratorProperties(Properties properties){
		this(properties,null);
	}

	public GeneratorProperties(Properties properties,String resource){
		if(properties != null) {
			this.properties.putAll(properties);
		}
		FileInputStream inputStream = null;
		try {
			String config = File.separator+System.getProperty("user.dir") + File.separator+"applicationContext.xml";
            inputStream = new FileInputStream(config);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();
//			Element element = document.getElementById("gererator");
			Element element = (Element) xPath.evaluate("//*[local-name()='generator']",document, XPathConstants.NODE);
			String core = xPath.evaluate("core",element);// DomUtils.getChildElementValueByTagName(element,"core");
			String frameName = xPath.evaluate("frameName",element);//DomUtils.getChildElementValueByTagName(element,"frameName");
			String companyName = xPath.evaluate("companyName",element);//DomUtils.getChildElementValueByTagName(element,"companyName");
			String appName = xPath.evaluate("appName",element);//DomUtils.getChildElementValueByTagName(element,"appName");
			String moduleName = xPath.evaluate("moduleName",element);//DomUtils.getChildElementValueByTagName(element,"moduleName");
			String authorName = xPath.evaluate("authorName",element);//DomUtils.getChildElementValueByTagName(element,"authorName");
			String namespace = xPath.evaluate("namespace",element);//DomUtils.getChildElementValueByTagName(element,"namespace");
			String template = xPath.evaluate("template",element);//DomUtils.getChildElementValueByTagName(element,"template");
			String extensions = xPath.evaluate("extensions",element);//DomUtils.getChildElementValueByTagName(element,"extensions");
			String prefix = xPath.evaluate("prefix",element);//DomUtils.getChildElementValueByTagName(element,"prefix");
			String output = xPath.evaluate("output",element);//DomUtils.getChildElementValueByTagName(element,"output");

			Properties loadProperties = new Properties();
			loadProperties.put("core",core);
			loadProperties.put("frameName",frameName);
			loadProperties.put("companyName",companyName);
			loadProperties.put("appName",appName);
			loadProperties.put("moduleName",moduleName);
			loadProperties.put("authorName",authorName);
			loadProperties.put("namespace",namespace);
			loadProperties.put("template",template);
			loadProperties.put("extensions",extensions);
			loadProperties.put("prefix",prefix);
			loadProperties.put("output",output);

//            XPathFactory xPathFactory = XPathFactory.newInstance();
//			InputStream inputStream = GeneratorProperties.class.getClassLoader().getResourceAsStream(GeneratorEnum.DEFAULT_APPLICATION_CONTEXT_PROPERTIES.value());
//			loadProperties.load(inputStream);
			this.properties.putAll(loadProperties);
		} catch (IOException e) {
			throw new GeneratorRuntimeException("IO异常",e);
		} catch (ParserConfigurationException e) {
			throw new GeneratorRuntimeException("IO异常",e);
		} catch (SAXException e) {
			throw new GeneratorRuntimeException("IO异常",e);
		} catch (XPathExpressionException e) {
			throw new GeneratorRuntimeException("IO异常",e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new GeneratorRuntimeException("IO异常",e);
			}
		}
		initProperties();
	}

	protected Properties initProperties(){
		Iterator iterator = System.getProperties().entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			properties.put("env_"+((String)entry.getKey()).replaceAll("\\.","_"),entry.getValue());
		}

		String now_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		properties.put("now_yyyyMMddHHmmss",now_yyyyMMddHHmmss);

		Properties path = new Properties();
		for (Map.Entry entity:properties.entrySet()) {
			String key = (String)entity.getKey();
			String value = (String)entity.getValue();
			path.put(key.replace("generator.", ""),value);
			String key_path = key.replace("generator.", "") + "_path";
			String value_path = value.replace(".", "/");
			path.put(key_path,value_path);
		}
		properties.putAll(path);

		this.properties = path;

		return properties;
	}

	public String getProperty(String key) {
		return getProperty(key,"");
	}
	
	public String getProperty(String key,String defaultValue) {
		return getProperties().getProperty(key, defaultValue);
	}

	public void setProperty(String key){
		setProperty(key,"");
	}

	public void setProperty(String key,String value){
		properties.setProperty(key,value);
		properties.setProperty(key+"_path",value.replace(".","/"));
	}

	protected Properties getProperties() {
		return properties;
	}

	protected void setProperties(Properties properties) {
		this.properties = properties;
	}

}
