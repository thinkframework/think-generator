package io.github.thinkframework.fx.core.control.table;

import lombok.Data;

@Data
public class Line {
    private String ordinalPosition = "ORDINAL_POSITION";
    private String columnName = "COLUMN_NAME";
    private String typeName = "TYPE_NAME";
    private String decimalDigits = "DECIMAL_DIGITS";
    private String columnSize = "COLUMN_SIZE";
    private String isNullable = "IS_NULLABLE";
    private String columnDef = "COLUMN_DEF";
    private String remarks = "REMARKS";
}
