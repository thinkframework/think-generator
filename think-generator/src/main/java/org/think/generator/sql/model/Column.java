package org.think.generator.sql.model;

public interface Column {
	public String getColumnName();

	public String getRemarks();

	public int getDataType();

	public boolean getPrimaryKey();

	public boolean getIsImportedKey();

	public ImportedKey getImportedKey();

	public String getIsNullable();

	public int getColumnSize();
}
