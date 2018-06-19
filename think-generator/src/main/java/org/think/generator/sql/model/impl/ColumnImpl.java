package org.think.generator.sql.model.impl;

import org.think.generator.sql.model.Column;
import org.think.generator.sql.model.ImportedKey;

public class ColumnImpl implements Column {
	public ColumnImpl(){
		
	}
	
	public ColumnImpl(String columnName){
		this.columnName = columnName;
	}
	private String tableName;
	private String columnName;
	private int dataType;
	private String typeName;
	private int columnSize;
	private String bufferLength;
	private int decimalDigits;
	private int numPrecRadix;
	private int nullable;
	private String remarks;
	private String columnDef;
	private int charOctetLength;
	private int ordinalPosition;
	private String isNullable;
	private String isAutoincrement;
	private boolean PrimaryKey;
	private boolean exportedKey;
	private boolean isImportedKey;
	private ImportedKey importedKey;
	private boolean indexInfo;
	private boolean nonUnique;

	/**
	 * @return TABLE_NAME String - 表名称
	 *
	 */
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return COLUMN_NAME String - 列名称
	 */
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return DATA_TYPE int - 来自 java.sql.Types 的 SQL 类型
	 */
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return TYPE_NAME String - 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
	 */
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return COLUMN_SIZE int - 列的大小
	 */
	public int getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}
	/**
	 * @return BUFFER_LENGTH 未被使用
	 */
	public String getBufferLength() {
		return bufferLength;
	}
	public void setBufferLength(String bufferLength) {
		this.bufferLength = bufferLength;
	}
	/**
	 * @return DECIMAL_DIGITS int - 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null
	 */
	public int getDecimalDigits() {
		return decimalDigits;
	}
	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	/**
	 * @return NUM_PREC_RADIX int - 基数（通常为 10 或 2）
	 */
	public int getNumPrecRadix() {
		return numPrecRadix;
	}
	public void setNumPrecRadix(int numPrecRadix) {
		this.numPrecRadix = numPrecRadix;
	}
	/**
	 * @return NULLABLE int - 是否允许使用 NULL
	 * columnNoNulls - 可能不允许使用 NULL 值
	 * columnNullable - 明确允许使用 NULL 值
	 * columnNullableUnknown - 不知道是否可使用 null
	 */
	public int getNullable() {
		return nullable;
	}
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}
	/**
	 * @return REMARKS String - 描述列的注释（可为 null）
	 */
	public String getRemarks() {
		return null == remarks ? "" : remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return COLUMN_DEF String - 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
	 */
	public String getColumnDef() {
		return columnDef;
	}
	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}
	/**
	 * @return CHAR_OCTET_LENGTH int - 对于 char 类型，该长度是列中的最大字节数
	 */
	public int getCharOctetLength() {
		return charOctetLength;
	}
	public void setCharOctetLength(int charOctetLength) {
		this.charOctetLength = charOctetLength;
	}
	/**
	 * @return ORDINAL_POSITION int - 表中的列的索引（从 1 开始）
	 */
	public int getOrdinalPosition() {
		return ordinalPosition;
	}
	public void setOrdinalPosition(int ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}
	/**
	 * @return IS_NULLABLE String - ISO 规则用于确定列是否包括 null。
	 * YES --- 如果参数可以包括 NULL
	 * NO --- 如果参数不可以包括 NULL
	 * 空字符串 --- 如果不知道参数是否可以包括 null
	 */
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	/**
	 * @return IS_AUTOINCREMENT String - 指示此列是否自动增加
	 * YES --- 如果该列自动增加
	 * NO --- 如果该列不自动增加
	 */
	public String getIsAutoincrement() {
		return isAutoincrement;
	}
	public void setIsAutoincrement(String isAutoincrement) {
		this.isAutoincrement = isAutoincrement;
	}

	public boolean getPrimaryKey() {
		return PrimaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		PrimaryKey = primaryKey;
	}

	public boolean isExportedKey() {
		return exportedKey;
	}

	public void setExportedKey(boolean exportedKey) {
		this.exportedKey = exportedKey;
	}

	public boolean getIsImportedKey() {
		return isImportedKey;
	}

	public void setIsImportedKey(boolean isImportedKey) {
		this.isImportedKey = isImportedKey;
	}

	public ImportedKey getImportedKey() {
		return importedKey;
	}

	public void setImportedKey(ImportedKey importedKey) {
		this.importedKey = importedKey;
	}

	public boolean isIndexInfo() {
		return indexInfo;
	}

	public void setIndexInfo(boolean indexInfo) {
		this.indexInfo = indexInfo;
	}

	public boolean isNonUnique() {
		return nonUnique;
	}

	public void setNonUnique(boolean nonUnique) {
		this.nonUnique = nonUnique;
	}

	public boolean getUnique() {
		return false;
	}

	public String getNullabled() {
		return "true";
	}

	@Override
	public String toString() {
		return columnName;
	}
}
