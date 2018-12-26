package org.think.jdbc.pdm;

import org.think.jdbc.xml.AbstractDatabaseMetaData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
import java.sql.*;
import java.util.*;

/**
 * Created by lixiaobin on 2017/4/25.
 */
class PDMDatabaseMetaData extends AbstractDatabaseMetaData implements DatabaseMetaData{
    private static final String CLASS_PATH = "jdbc:think:pdm:classpath://";
    private static final String FILE = "jdbc:think:pdm:file://";

    PDMDatabaseMetaData(String url){
       super(url);
    }

    @Override
    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
        try {
            Document document = document();
            XPath xPath = xPath();
            //获取所有table节点
            NodeList tables;
            if (tableNamePattern != null && tableNamePattern.contains("%")){
                tables = (NodeList) xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table'][contains(Code," + tableNamePattern.replace("%","") + ")]", document, XPathConstants.NODESET);
            }else{
                tables = (NodeList) xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table'][Code = " + tableNamePattern + "]", document, XPathConstants.NODESET);
            }
            List wrap = new ArrayList();
            for (int i=0;i<tables.getLength();i++){
                Node node = tables.item(i);
                //表名称
                String tableName = xPath.evaluate("*[local-name()='Code']",node);
                //表备注
                String remarks = xPath.evaluate("*[local-name()='Name']",node);
                Map map = new HashMap();
                map.put("TABLE_NAME",tableName);
                map.put("REMARKS",remarks);
                wrap.add(map);
            }
            return new PDMResultSet(wrap);
        } catch (XPathExpressionException e) {
            throw new SQLException(e.getMessage(),e);
        }
    }

    @Override
    public ResultSet getTableTypes() {
        return new PDMResultSet(new ArrayList(){
            {
                add(new HashMap<String,String>(){{put("TABLE_TYPE","TABLE");}});
                add(new HashMap<String,String>(){{put("TABLE_TYPE","VIEW");}});
            }
        });
    }

    @Override
    public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        try {
            Document document = document();
            XPath xPath = xPath();
            //匹配表对应的列
            NodeList columnsNode = null;
            if (tableNamePattern != null && tableNamePattern.contains("%")){
                columnsNode = (NodeList) xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table'][concat(text()," + tableNamePattern.replace("%","") + ")]/*[local-name()='Columns']/*[local-name()='Column']", document, XPathConstants.NODESET);
            }else{
                columnsNode = (NodeList) xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table'][text() = " + tableNamePattern + "]/*[local-name()='Columns']/*[local-name()='Column']", document, XPathConstants.NODESET);
            }
            List wrap = new ArrayList<>();

            for (int i=0;i<columnsNode.getLength();i++){
                Node node = columnsNode.item(i);
                //列名称
                String columnName = xPath.evaluate("*[local-name()='Code']",node);
                //列备注
                String remarks = xPath.evaluate("*[local-name()='Name']",node);
                //数据库的列类型
                String typeName = xPath.evaluate("*[local-name()='DataType']",node);
                //Types的列类型
                Integer dateType = 0;
                if(typeName != null){
                    //全部转换成大写
                    typeName = typeName.toLowerCase();
                }
                typeName = TypesHelper.typenames(typeName);
                dateType = TypesHelper.datetypes(typeName);

                String columnSize = xPath.evaluate("*[local-name()='Length']",node);
                String nullable = xPath.evaluate("*[local-name()='Mandatory']",node);
                String num_prec_radix = xPath.evaluate("*[local-name()='Length']",node);
                String decimal_digits = xPath.evaluate("*[local-name()='Precision']",node);

                Map<String, Object> map = new HashMap<>();
                map.put("TABLE_NAME",tableNamePattern.toLowerCase());
                map.put("ORDINAL_POSITION",i);
                map.put("COLUMN_NAME",columnName.toLowerCase());
                map.put("DATA_TYPE",dateType);
                map.put("TYPE_NAME",typeName);
                map.put("NUM_PREC_RADIX",Integer.parseInt("".equals(num_prec_radix) ? "0" : num_prec_radix));
                map.put("DECIMAL_DIGITS",Integer.parseInt("".equals(decimal_digits) ? "0" : decimal_digits));
                //todo lixiaobin
                map.put("COLUMN_SIZE",Integer.parseInt("".equals(columnSize) ? "0" : columnSize));
                map.put("NULLABLE","1".equals(nullable) ? 1 : 0);
                map.put("IS_NULLABLE","1".equals(nullable) ? "YES" : "NO");
                map.put("COLUMN_DEF","");
                map.put("REMARKS",remarks);
                wrap.add(map);
            }
            return new PDMResultSet(wrap);
        } catch (XPathExpressionException e) {
            throw new SQLException(e.getMessage(),e);
        }
    }

    @Override
    public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
        PDMResultSet pdmResultSet = new PDMResultSet();
        try {
            Document document = document();
            XPath xPath = xPath();
            //获取表
            NodeList tableNode = (NodeList)xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table' ][Code='"+table+"']",document,XPathConstants.NODE);
            //获取表对应的主键节点
            NodeList primaryKeysNode = (NodeList)xPath.evaluate("*[local-name()='PrimaryKey']/*[local-name()='Key']",tableNode,XPathConstants.NODESET);
            List wrap = new ArrayList<>();
            for (int i=0;i<primaryKeysNode.getLength();i++){
                Node primaryKey = primaryKeysNode.item(i);
                //获取主键引用对应的key
                String keyRef = xPath.evaluate("@Ref",primaryKey);
                //根据key获取对应的主键列
                Node keysNode = (Node)xPath.evaluate("*[local-name()='Keys']/*[local-name()='Key'][@Id='"+keyRef+"']",tableNode,XPathConstants.NODE);

                String pkName = xPath.evaluate("*[local-name()='Code']",keysNode);
                String name = xPath.evaluate("*[local-name()='Name']",keysNode);
                NodeList columnsNode = (NodeList)xPath.evaluate("*[local-name()='Key.Columns']/*[local-name()='Column']",keysNode,XPathConstants.NODESET);
                for(int j=0;j<columnsNode.getLength();j++){
                    Node columnRefNode = columnsNode.item(j);
                    String columnRef = xPath.evaluate("@Ref",columnRefNode);
                    Node columnNode = (Node)xPath.evaluate("*[local-name()='Columns']/*[local-name()='Column'][@Id='"+columnRef+"']",tableNode,XPathConstants.NODE);
                    String columnName = xPath.evaluate("Code",columnNode);

                    Map<String, Object> map = new HashMap<>();
                    map.put("TABLE_NAME",table);
                    map.put("KEY_SEQ",String.valueOf(j));
                    map.put("PK_NAME",pkName);
                    map.put("COLUMN_NAME",columnName);
                    wrap.add(map);
                }
            }
            pdmResultSet = new PDMResultSet(wrap);
        }catch (XPathExpressionException e){
            throw new SQLException(e);
        }finally {
            return pdmResultSet;
        }
    }

    @Override
    public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
        PDMResultSet pdmResultSet = new PDMResultSet();
        try {
            Document document = document();
            XPath xPath = xPath();

            Map map = new HashMap();
            NodeList tableNode = (NodeList)xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table' ][Code='"+table+"']",document,XPathConstants.NODE);

            String ref = xPath.evaluate("@Id",tableNode);

            Node tableRefNode = (Node)xPath.evaluate("//*[local-name()='References']/*[local-name()='Reference']/*[local-name()='ChildTable']/*[local-name()='Table'][@Ref='"+ref+"']/../..",document,XPathConstants.NODE);

            String parentTable = xPath.evaluate("*[local-name()='ParentTable']/*[local-name()='Table']/@Ref",tableRefNode);
            Node parentTableNode = (Node)xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table' ][@Id='"+parentTable+"']",document,XPathConstants.NODE);

            String parentKey = xPath.evaluate("*[local-name()='ParentKey']/*[local-name()='Key']/@Ref",tableRefNode);
            Node parentKeyNode1 = (Node)xPath.evaluate("*[local-name()='Keys']/*[local-name()='Key' ][@Id='"+parentKey+"']",parentTableNode,XPathConstants.NODE);
            String pkName = xPath.evaluate("*[local-name()='Code']",parentKeyNode1);
            String name = xPath.evaluate("*[local-name()='Name']",parentKeyNode1);
            NodeList parentKeyNode = (NodeList)xPath.evaluate("/*[local-name()='Key.Columns']/*[local-name()='Column']",parentKeyNode1,XPathConstants.NODESET);

            String pkcolumnName = "";
            for(int j=0;j<parentKeyNode.getLength();j++){
                Node columnRefNode = parentKeyNode.item(j);
                String columnRef = xPath.evaluate("@Ref",columnRefNode);
                Node columnNode = (Node)xPath.evaluate("*[local-name()='Columns']/*[local-name()='Column'][@Id='"+columnRef+"']",tableNode,XPathConstants.NODE);
                pkcolumnName = xPath.evaluate("Code",columnNode);
            }

            String childTable = xPath.evaluate("*[local-name()='ChildTable']/*[local-name()='Table']/@Ref",tableRefNode);
            Node childTableNode = (Node)xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table' ][@Id='"+childTable+"']",document,XPathConstants.NODE);


            NodeList columns = (NodeList) xPath.evaluate("*[local-name()='Joins']/*[local-name()='ReferenceJoin']//*[local-name()='Column']",tableRefNode,XPathConstants.NODESET);

            List<Map> wrap = new ArrayList<>();
            for (int i=0;i<columns.getLength();i++){
                Node primaryKey = columns.item(i);
                //获取主键引用对应的key
                String keyRef = xPath.evaluate("@Ref",primaryKey);
                //根据key获取对应的主键列
                Node node = null;

                if((node = (Node)xPath.evaluate("*[local-name()='Columns']/*[local-name()='Column'][@Id='"+keyRef+"']",parentTableNode,XPathConstants.NODE)) != null ){

                    String columnRef1 = xPath.evaluate("Code",parentTableNode);
                    String columnRef = xPath.evaluate("Code",node);
                    map.put("PKTABLE_NAME",columnRef1);
                    map.put("PKCOLUMN_NAME",columnRef);
                    map.put("KEY_SEQ",String.valueOf(i));
                }
                if((node = (Node)xPath.evaluate("*[local-name()='Columns']/*[local-name()='Column'][@Id='"+keyRef+"']",childTableNode,XPathConstants.NODE)) != null ){
                    String columnRef1 = xPath.evaluate("Code",childTableNode);
                    String columnRef = xPath.evaluate("Code",node);
                    map.put("FKTABLE_NAME",columnRef1);
                    map.put("FKCOLUMN_NAME",columnRef);
                    map.put("KEY_SEQ",String.valueOf(i));
                }
                //todo 需要处理
                boolean flag = false;
                for(Map tmp : wrap){
                    if(tmp.get("PKTABLE_NAME").equals(map.get("PKTABLE_NAME"))){
                        flag = true;
                    }
                }
                if(flag){
                    continue;
                }
                wrap.add(map);
            }
            pdmResultSet = new PDMResultSet(wrap);
        }catch (XPathExpressionException e) {
            throw new SQLException(e.getMessage(),e);
        }finally {
            return pdmResultSet;
        }
    }

    @Override
    public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
        PDMResultSet pdmResultSet = new PDMResultSet();
        try {
            Document document = document();
            XPath xPath = xPath();

            Map map = new HashMap();
            NodeList tableNode = (NodeList)xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table' ][Code='"+table+"']",document,XPathConstants.NODE);

            String ref = xPath.evaluate("@Id",tableNode);

            Node tableRefNode = (Node)xPath.evaluate("//*[local-name()='References']/*[local-name()='Reference']/*[local-name()='ParentTable']/*[local-name()='Table'][@Ref='"+ref+"']/../..",document,XPathConstants.NODE);

            String parentTable = xPath.evaluate("*[local-name()='ParentTable']/*[local-name()='Table']/@Ref",tableRefNode);
            Node parentTableNode = (Node)xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table' ][@Id='"+parentTable+"']",document,XPathConstants.NODE);

            String parentKey = xPath.evaluate("*[local-name()='ParentKey']/*[local-name()='Key']/@Ref",tableRefNode);
            Node parentKeyNode1 = (Node)xPath.evaluate("*[local-name()='Keys']/*[local-name()='Key' ][@Id='"+parentKey+"']",parentTableNode,XPathConstants.NODE);
            String pkName = xPath.evaluate("*[local-name()='Code']",parentKeyNode1);
            String name = xPath.evaluate("*[local-name()='Name']",parentKeyNode1);
            NodeList parentKeyNode = (NodeList)xPath.evaluate("/*[local-name()='Key.Columns']/*[local-name()='Column']",parentKeyNode1,XPathConstants.NODESET);

            String pkcolumnName = "";
            for(int j=0;j<parentKeyNode.getLength();j++){
                Node columnRefNode = parentKeyNode.item(j);
                String columnRef = xPath.evaluate("@Ref",columnRefNode);
                Node columnNode = (Node)xPath.evaluate("*[local-name()='Columns']/*[local-name()='Column'][@Id='"+columnRef+"']",tableNode,XPathConstants.NODE);
                pkcolumnName = xPath.evaluate("Code",columnNode);
            }

            String childTable = xPath.evaluate("*[local-name()='ChildTable']/*[local-name()='Table']/@Ref",tableRefNode);
            Node childTableNode = (Node)xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table' ][@Id='"+childTable+"']",document,XPathConstants.NODE);


            NodeList columns = (NodeList) xPath.evaluate("*[local-name()='Joins']/*[local-name()='ReferenceJoin']//*[local-name()='Column']",tableRefNode,XPathConstants.NODESET);

            List<Map> wrap = new ArrayList<>();
            for (int i=0;i<columns.getLength();i++){
                Node primaryKey = columns.item(i);
                //获取主键引用对应的key
                String keyRef = xPath.evaluate("@Ref",primaryKey);
                //根据key获取对应的主键列
                Node node = null;

                if((node = (Node)xPath.evaluate("*[local-name()='Columns']/*[local-name()='Column'][@Id='"+keyRef+"']",parentTableNode,XPathConstants.NODE)) != null ){

                    String columnRef1 = xPath.evaluate("Code",parentTableNode);
                    String columnRef = xPath.evaluate("Code",node);
                    map.put("PKTABLE_NAME",columnRef1);
                    map.put("PKCOLUMN_NAME",columnRef);
                    map.put("KEY_SEQ",String.valueOf(i));
                }
                if((node = (Node)xPath.evaluate("*[local-name()='Columns']/*[local-name()='Column'][@Id='"+keyRef+"']",childTableNode,XPathConstants.NODE)) != null ){
                    String columnRef1 = xPath.evaluate("Code",childTableNode);
                    String columnRef = xPath.evaluate("Code",node);
                    map.put("FKTABLE_NAME",columnRef1);
                    map.put("FKCOLUMN_NAME",columnRef);
                    map.put("KEY_SEQ",String.valueOf(i));
                }
                //todo 需要处理
                boolean flag = false;
                for(Map tmp : wrap){
                    if(tmp.get("FKTABLE_NAME").equals(map.get("FKTABLE_NAME"))){
                        flag = true;
                    }
                }
                if(flag){
                    continue;
                }
                wrap.add(map);
            }
            pdmResultSet = new PDMResultSet(wrap);
        }catch (XPathExpressionException e) {
            throw new SQLException(e.getMessage(),e);
        }finally {
            return pdmResultSet;
        }
    }

    @Override
    public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
        PDMResultSet pdmResultSet = new PDMResultSet();
        try {
            Document document = document();
            XPath xPath = xPath();
            //获取表
            NodeList tableNode = (NodeList)xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table' ][Code='"+table+"']",document,XPathConstants.NODE);
            //获取表对应的主键节点
            NodeList indexsNode = (NodeList)xPath.evaluate("*[local-name()='Indexes']/*[local-name()='Index']",tableNode,XPathConstants.NODESET);
            List wrap = new ArrayList<>();
            for (int i=0;i<indexsNode.getLength();i++){
                Node indexNode = indexsNode.item(i);
                //获取主键引用对应的key
                String Id = xPath.evaluate("@Id",indexNode);
                String pkName = xPath.evaluate("*[local-name()='Code']",indexNode);
                String name = xPath.evaluate("*[local-name()='Name']",indexNode);
                NodeList columnsNode = (NodeList)xPath.evaluate("*[local-name()='IndexColumns']/*[local-name()='IndexColumn']/*[local-name()='Column']/*[local-name()='Column']",indexNode,XPathConstants.NODESET);
                for(int j=0;j<columnsNode.getLength();j++){
                    Node columnRefNode = columnsNode.item(j);
                    String columnRef = xPath.evaluate("@Ref",columnRefNode);
                    Node columnNode = (Node)xPath.evaluate("*[local-name()='Column']/*[local-name()='Column'][@Id='"+columnRef+"']",tableNode,XPathConstants.NODE);
                    String columnName = xPath.evaluate("Code",columnNode);

                    Map map = new HashMap();
                    map.put("TABLE_NAME",table);
                    map.put("KEY_SEQ",String.valueOf(j));
                    map.put("PK_NAME",pkName);
                    map.put("COLUMN_NAME",columnName);
                    wrap.add(map);
                }
            }
            pdmResultSet = new PDMResultSet(wrap);
        }catch (XPathExpressionException e) {
            throw new SQLException(e.getMessage(),e);
        }finally {
            return pdmResultSet;
        }

    }

    public Document document() throws SQLException {
      try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(getInputStream());
        } catch (ParserConfigurationException | IOException |SAXException e) {
            throw new SQLException(e.getMessage(),e);
        }
    }

    public XPath xPath(){
        XPathFactory xPathFactory = XPathFactory.newInstance();
        return xPathFactory.newXPath();
    }

    protected InputStream getInputStream() throws SQLException {
        try {
            if(url.startsWith(CLASS_PATH)){
                return AbstractDatabaseMetaData.class.getClassLoader().getResourceAsStream(url.substring(CLASS_PATH.length()));
            }else if(url.startsWith(FILE)){
                return new FileInputStream(url.substring(FILE.length()));
            }
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            throw new SQLException(e.getMessage(),e);
        }
    }
}
