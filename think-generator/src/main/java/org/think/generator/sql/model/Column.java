package org.think.generator.sql.model;

public interface Column {
	String getColumnName();

	String getRemarks();

	int getDataType();

	boolean getPrimaryKey();

	boolean getIsImportedKey();

	ImportedKey getImportedKey();

	String getIsNullable();

	int getColumnSize();
}
