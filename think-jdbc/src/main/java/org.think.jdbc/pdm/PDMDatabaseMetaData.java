package org.think.jdbc.pdm;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
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
class PDMDatabaseMetaData implements DatabaseMetaData{
    private static final String CLASS_PATH = "jdbc:think:pdm:classpath://";
    private static final String FILE = "jdbc:think:pdm:file://";
    private String fileName;
    private String url;
    private PDMDatabaseMetaData(){

    }

    public PDMDatabaseMetaData(String url){
       this.url = url;
    }

    @Override
    public boolean allProceduresAreCallable() throws SQLException {
        return false;
    }

    @Override
    public boolean allTablesAreSelectable() throws SQLException {
        return false;
    }

    @Override
    public String getURL() throws SQLException {
        return url;
    }

    @Override
    public String getUserName() throws SQLException {
        return null;
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return false;
    }

    @Override
    public boolean nullsAreSortedHigh() throws SQLException {
        return false;
    }

    @Override
    public boolean nullsAreSortedLow() throws SQLException {
        return false;
    }

    @Override
    public boolean nullsAreSortedAtStart() throws SQLException {
        return false;
    }

    @Override
    public boolean nullsAreSortedAtEnd() throws SQLException {
        return false;
    }

    @Override
    public String getDatabaseProductName() throws SQLException {
        return null;
    }

    @Override
    public String getDatabaseProductVersion() throws SQLException {
        return null;
    }

    @Override
    public String getDriverName() throws SQLException {
        return null;
    }

    @Override
    public String getDriverVersion() throws SQLException {
        return null;
    }

    @Override
    public int getDriverMajorVersion() {
        return 0;
    }

    @Override
    public int getDriverMinorVersion() {
        return 0;
    }

    @Override
    public boolean usesLocalFiles() throws SQLException {
        return false;
    }

    @Override
    public boolean usesLocalFilePerTable() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesUpperCaseIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesLowerCaseIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesMixedCaseIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public String getIdentifierQuoteString() throws SQLException {
        return null;
    }

    @Override
    public String getSQLKeywords() throws SQLException {
        return null;
    }

    @Override
    public String getNumericFunctions() throws SQLException {
        return null;
    }

    @Override
    public String getStringFunctions() throws SQLException {
        return null;
    }

    @Override
    public String getSystemFunctions() throws SQLException {
        return null;
    }

    @Override
    public String getTimeDateFunctions() throws SQLException {
        return null;
    }

    @Override
    public String getSearchStringEscape() throws SQLException {
        return null;
    }

    @Override
    public String getExtraNameCharacters() throws SQLException {
        return null;
    }

    @Override
    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsColumnAliasing() throws SQLException {
        return false;
    }

    @Override
    public boolean nullPlusNonNullIsNull() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsConvert() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsConvert(int fromType, int toType) throws SQLException {
        return false;
    }

    @Override
    public boolean supportsTableCorrelationNames() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsExpressionsInOrderBy() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOrderByUnrelated() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsGroupBy() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsGroupByUnrelated() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsGroupByBeyondSelect() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsLikeEscapeClause() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMultipleResultSets() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMultipleTransactions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsNonNullableColumns() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMinimumSQLGrammar() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCoreSQLGrammar() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsExtendedSQLGrammar() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsANSI92FullSQL() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOuterJoins() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsFullOuterJoins() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsLimitedOuterJoins() throws SQLException {
        return false;
    }

    @Override
    public String getSchemaTerm() throws SQLException {
        return null;
    }

    @Override
    public String getProcedureTerm() throws SQLException {
        return null;
    }

    @Override
    public String getCatalogTerm() throws SQLException {
        return null;
    }

    @Override
    public boolean isCatalogAtStart() throws SQLException {
        return false;
    }

    @Override
    public String getCatalogSeparator() throws SQLException {
        return null;
    }

    @Override
    public boolean supportsSchemasInDataManipulation() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsPositionedDelete() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsPositionedUpdate() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSelectForUpdate() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsStoredProcedures() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSubqueriesInComparisons() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSubqueriesInExists() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSubqueriesInIns() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCorrelatedSubqueries() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsUnion() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsUnionAll() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        return false;
    }

    @Override
    public int getMaxBinaryLiteralLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxCharLiteralLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnsInGroupBy() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnsInIndex() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnsInOrderBy() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnsInSelect() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnsInTable() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxConnections() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxCursorNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxIndexLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxSchemaNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxProcedureNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxCatalogNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxRowSize() throws SQLException {
        return 0;
    }

    @Override
    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        return false;
    }

    @Override
    public int getMaxStatementLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxStatements() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxTableNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxTablesInSelect() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxUserNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getDefaultTransactionIsolation() throws SQLException {
        return 0;
    }

    @Override
    public boolean supportsTransactions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
        return false;
    }

    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        return false;
    }

    @Override
    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        return false;
    }

    @Override
    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(getInputStream());
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            //获取所有table节点
            NodeList tables =  (NodeList)xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table']",document,XPathConstants.NODESET);
            List wrap = new ArrayList();
            for (int i=0;i<tables.getLength();i++){
                Node node = tables.item(i);
                //表名称
                String tableName = xPath.evaluate("*[local-name()='Code']",node);
                //TODO 对%的特殊处理 lixiaobin
                if(!tableName.equalsIgnoreCase(tableNamePattern) && !"%".equalsIgnoreCase(tableNamePattern)){
                    continue;
                }
                //表备注
                String remarks = xPath.evaluate("*[local-name()='Name']",node);
                Map map = new HashMap();
                map.put("TABLE_NAME",tableName);
                map.put("REMARKS",remarks);
                wrap.add(map);
            }
            return new PDMResultSet(wrap);
        }catch (Exception e){
            new SQLException(e);
        }
        return null;
    }

    @Override
    public ResultSet getSchemas() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getCatalogs() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getTableTypes() throws SQLException {
        Map map = new HashMap();
        //支持的表类型
        map.put("TABLE","VIEW");
        List wrap = new ArrayList();
        wrap.add(map);
        return new PDMResultSet(wrap);
    }

    @Override
    public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        PDMResultSet pdmResultSet = new PDMResultSet();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(getInputStream());
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("Model");
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            //匹配表名称
            NodeList tableNode = (NodeList)xPath.evaluate("//*[local-name()='Tables']/*[local-name()='Table' ][Code='"+tableNamePattern+"']",document,XPathConstants.NODE);
            //匹配表对应的列
            NodeList columnsNode = (NodeList)xPath.evaluate(       "*[local-name()='Columns']/*[local-name()='Column']",tableNode,XPathConstants.NODESET);
            List wrap = new ArrayList<>();

            for (int i=0;i<columnsNode.getLength();i++){
                Node node = columnsNode.item(i);
                //列名称
                String columnName = xPath.evaluate("*[local-name()='Code']",node);

                if("creator".equals(columnName) ||
                        "create_time".equals(columnName) ||
                        "editor".equals(columnName) ||
                        "edit_time".equals(columnName)){
                    //TODO 黑科技,之后删除 lixiaobin
                    continue;
                }
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
                //转换操作,之后扩展
                if(typeName.startsWith("varchar")){
                    dateType = Types.VARCHAR;
                }else if(typeName.startsWith("char")){
                    dateType = Types.CHAR;
                }else if(typeName.startsWith("date")){
                    dateType = Types.DATE;
                }else if(typeName.startsWith("timestamp")){
                    dateType = Types.TIMESTAMP;
               }
                else if(typeName.startsWith("int")){
                    dateType = Types.INTEGER;
                }
                else if(typeName.startsWith("bigint")){
                    dateType = Types.BIGINT;
                }
                else if(typeName.startsWith("numeric")){
                    dateType = Types.DECIMAL;
                }

                String columnSize = xPath.evaluate("*[local-name()='Length']",node);
                String nullable = xPath.evaluate("*[local-name()='Mandatory']",node);
                String num_prec_radix = xPath.evaluate("*[local-name()='Length']",node);
                String decimal_digits = xPath.evaluate("*[local-name()='Precision']",node);

                Map map = new HashMap();
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
        }catch (Exception e){
            new SQLException(e);
        }
        return pdmResultSet;
    }

    @Override
    public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
        PDMResultSet pdmResultSet = new PDMResultSet();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(getInputStream());
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("Model");
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
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

                    Map map = new HashMap();
                    map.put("TABLE_NAME",table);
                    map.put("KEY_SEQ",String.valueOf(j));
                    map.put("PK_NAME",pkName);
                    map.put("COLUMN_NAME",columnName);
                    wrap.add(map);
                }
            }
            pdmResultSet = new PDMResultSet(wrap);
        }catch (Exception e){
            throw new SQLException(e);
        }finally {
            return pdmResultSet;
        }
    }

    @Override
    public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
        PDMResultSet pdmResultSet = new PDMResultSet();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(getInputStream());
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

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
        }catch (Exception e){
            throw new SQLException(e);
        }finally {
            return pdmResultSet;
        }
    }

    @Override
    public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
        PDMResultSet pdmResultSet = new PDMResultSet();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(getInputStream());
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

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
        }catch (Exception e){
            throw new SQLException(e);
        }finally {
            return pdmResultSet;
        }
    }

    @Override
    public ResultSet getCrossReference(String parentCatalog, String parentSchema, String parentTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getTypeInfo() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
        PDMResultSet pdmResultSet = new PDMResultSet();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(getInputStream());
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
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
        }catch (Exception e){
            throw new SQLException(e);
        }finally {
            return pdmResultSet;
        }

    }

    @Override
    public boolean supportsResultSetType(int type) throws SQLException {
        return false;
    }

    @Override
    public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
        return false;
    }

    @Override
    public boolean ownUpdatesAreVisible(int type) throws SQLException {
        return false;
    }

    @Override
    public boolean ownDeletesAreVisible(int type) throws SQLException {
        return false;
    }

    @Override
    public boolean ownInsertsAreVisible(int type) throws SQLException {
        return false;
    }

    @Override
    public boolean othersUpdatesAreVisible(int type) throws SQLException {
        return false;
    }

    @Override
    public boolean othersDeletesAreVisible(int type) throws SQLException {
        return false;
    }

    @Override
    public boolean othersInsertsAreVisible(int type) throws SQLException {
        return false;
    }

    @Override
    public boolean updatesAreDetected(int type) throws SQLException {
        return false;
    }

    @Override
    public boolean deletesAreDetected(int type) throws SQLException {
        return false;
    }

    @Override
    public boolean insertsAreDetected(int type) throws SQLException {
        return false;
    }

    @Override
    public boolean supportsBatchUpdates() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public boolean supportsSavepoints() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsNamedParameters() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMultipleOpenResults() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsGetGeneratedKeys() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
        return null;
    }

    @Override
    public boolean supportsResultSetHoldability(int holdability) throws SQLException {
        return false;
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return 0;
    }

    @Override
    public int getDatabaseMajorVersion() throws SQLException {
        return 0;
    }

    @Override
    public int getDatabaseMinorVersion() throws SQLException {
        return 0;
    }

    @Override
    public int getJDBCMajorVersion() throws SQLException {
        return 0;
    }

    @Override
    public int getJDBCMinorVersion() throws SQLException {
        return 0;
    }

    @Override
    public int getSQLStateType() throws SQLException {
        return 0;
    }

    @Override
    public boolean locatorsUpdateCopy() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsStatementPooling() throws SQLException {
        return false;
    }

    @Override
    public RowIdLifetime getRowIdLifetime() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
        return null;
    }

    @Override
    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        return false;
    }

    @Override
    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getClientInfoProperties() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        return null;
    }

    @Override
    public boolean generatedKeyAlwaysReturned() throws SQLException {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    private InputStream getInputStream(){
        if(url.startsWith(CLASS_PATH)){
            fileName = url.substring(CLASS_PATH.length());
            PDMConnection.class.getClassLoader().getResourceAsStream(fileName);
        }else if(url.startsWith(FILE)){
            fileName = url.substring(FILE.length());
            try {
                return new FileInputStream(fileName);
            }catch(FileNotFoundException e){
                new RuntimeException("文件未找到",e);
            }
        }
        return null;
    }

//    private Map convertOracle(){
//        Map map = new HashMap();
//        map.put("",Types.)
//    }
}
