package org.think.generator.provider.adapter;

import org.think.generator.lang.impl.ClazzImpl;
import org.think.generator.lang.annotation.*;
import org.think.generator.lang.reflect.ClazzField;
import org.think.generator.lang.reflect.impl.ClazzFieldImpl;
import org.think.generator.sql.model.Column;
import org.think.generator.sql.model.impl.ColumnImpl;
import org.think.generator.util.StringUtils;
import org.think.generator.util.TypesUtils;

import java.sql.Types;

public class ColumnFieldBuild {
    private ColumnFieldBuild(){

    }

    public static ClazzField buildField(Column column){
        String columnName = column.getColumnName();
        Class clazz = TypesUtils.dataType(column.getDataType());
        ClazzImpl classType = new ClazzImpl(clazz);

        String fieldName = StringUtils.fieldName(columnName);
        ClazzFieldImpl field = new ClazzFieldImpl();
        field.setType(classType);
//        TODO 审阅主键问题 lixiaobin
//        if(column.getPrimaryKey()) {
//            field.setName("id");
//        }else {
            field.setName(fieldName);
//        }

        if(column.getPrimaryKey()) {
            field.addAnnotation(new SimpleAnnotation("@Id"));
            field.addAnnotation(new SimpleAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)"));
//            field.addAnnotation(new SimpleAnnotation("@GenericGenerator(name=\"idGenerator\", strategy=\"uuid\")"));
        }

        if(!column.getPrimaryKey() && (((ColumnImpl)column).isExportedKey() || ((ColumnImpl)column).getIsImportedKey())) {
            field.addAnnotation(new SimpleAnnotation("@Transient"));
        }else{
        }

        if(column.getDataType() == Types.DECIMAL){
            field.addAnnotation(new SimpleAnnotation("@Column(name=\""+columnName+"\",precision = "+((ColumnImpl)column).getNumPrecRadix()+",scale="+((ColumnImpl)column).getDecimalDigits()+")"));
            ((ColumnImpl) column).setColumnSize(0);
        }else{
            if(column.getDataType() == Types.VARCHAR &&  column.getColumnSize() != 0) {
                field.addAnnotation(new SimpleAnnotation("@Size(max=" + column.getColumnSize() + ")"));
            }else{
                ((ColumnImpl) column).setColumnSize(0);
            }
            field.addAnnotation(new SimpleAnnotation("@Column(name=\""+columnName+"\")"));
        }

        return field;
    }

}
