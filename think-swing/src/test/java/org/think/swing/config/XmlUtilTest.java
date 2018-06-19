package org.think.swing.config;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Properties;

public class XmlUtilTest {
    private XmlUtil xmlUtil;
    private Properties properties;
    private String id;
    @Before
    public void setup(){
        xmlUtil = new XmlUtil();
        Resource resource = new ClassPathResource("applicationContext.xml");
        xmlUtil.setResource(resource);
        properties = new Properties();
        properties.put("driverClassName","com.mysql.jdbc.Driver");
        properties.put("url","jdbc:mysql://localhost:3306/think?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false");
        properties.put("username","root");
        properties.put("password","root");
        id = "mysql";
    }

    @Test
    public void getDataSourceNames(){
        List<String> list = xmlUtil.getDataSourceNames();
    }

    @Test
    public void getDataSourceProperties(){
//         List<Properties> list = xmlUtil.getDataSourceProperties();
    }

    @Test
    public void getDataSourcePropertiesById(){
        String id = "mysql";
//        Properties properties = xmlUtil.getDataSourceProperties(id);
    }

    @Test
    public void setDataSourceProperties(){
//        xmlUtil.setDataSourceProperties(properties);
    }
}
