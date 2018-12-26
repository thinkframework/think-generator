package org.think.generator.provider.adapter;

import org.think.generator.context.GeneratorContext;
import org.think.generator.lang.Clazz;
import org.think.generator.lang.annotation.SimpleAnnotation;
import org.think.generator.lang.impl.ClazzImpl;
import org.think.generator.lang.impl.ClazzPackageImpl;
import org.think.generator.lang.reflect.ClazzField;
import org.think.generator.lang.reflect.ClazzMethod;
import org.think.generator.lang.reflect.RemarksInvocationHandler;
import org.think.generator.lang.reflect.impl.ClazzFieldImpl;
import org.think.generator.lang.reflect.impl.ClazzMethodImpl;
import org.think.generator.sql.TableBuilder;
import org.think.generator.sql.model.Column;
import org.think.generator.sql.model.ExportedKey;
import org.think.generator.sql.model.ImportedKey;
import org.think.generator.sql.model.Table;
import org.think.generator.sql.model.impl.ColumnImpl;
import org.think.generator.util.StringUtils;
import org.think.generator.util.TypesUtils;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TableClassBuild {

    private TableClassBuild() {

    }

    public static Clazz buildClass(Table table) {
        String className = StringUtils.className(table.getTableName());
        ClazzImpl clazz = new ClazzImpl(className);
        clazz.setClazzPackage(new ClazzPackageImpl());

        clazz.setFields(buildField(table));
        clazz.setMethods(buildMethod(table));

        clazz.setImportedFields(getImportedKeyFields(table));
        clazz.setExportedFields(getExportedKeyFields(table));

        clazz.setImportedMethods(getImportedKeyMethods(table));
        clazz.setExportedMethods(getExportedKeyMethods(table));

        Clazz proxy = clazz;
        return proxy;
    }

    /**
     * 根据列生成生成字段信息
     *
     * @param table
     * @return 字段信息
     */
    private static Set<ClazzField> buildField(Table table) {
        return table.getColumns().stream()
                .map(column -> new ColumnFieldAdapter(column))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * 根据列生成方法信息
     * @param table
     * @return 方法信息
     */
    private static Set<ClazzMethod> buildMethod(Table table) {
        return table.getColumns().stream()
                .map(column -> new ColumnMethodAdapter(column))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * 外键.
     * 本表的外键对应其他表的主键
     *
     * @param table
     * @return
     */
    private static Set<ClazzField> getExportedKeyFields(Table table) {
        Set<ClazzField> fields = new LinkedHashSet<ClazzField>();
        for (ExportedKey exportedKey : table.getExportedKeys()) {
            exportedKey.getFktableName();

            String className = StringUtils.className(StringUtils.replacePrefix(exportedKey.getFktableName()));
            String fieldName = StringUtils.fieldName(StringUtils.replacePrefix(exportedKey.getFkcolumnName()));

            if (fieldName.endsWith("Id")) {
                //截取末尾的Id;
                fieldName = fieldName.substring(0, fieldName.length() - "Id".length());
            }
            //集合名称
            String set = className.substring(0, 1).toLowerCase() + className.substring(1) + "s";
            Clazz clazz = new ClazzImpl(className);
            ClazzFieldImpl field = new ClazzFieldImpl(set, clazz);

            field.addAnnotation(new SimpleAnnotation("@OneToMany(mappedBy=\"" + StringUtils.uncapitalize(fieldName) + "\")"));
            field.addAnnotation(new SimpleAnnotation("@JsonIgnore"));
            field.addAnnotation(new SimpleAnnotation("@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)"));

            fields.add(new ColumnFieldAdapter(field,
                    new TableBuilder().addDataSource(getDataSource())
                            .addTableName(exportedKey.getFktableName())
                            .build().getRemarks()));
        }
        return fields;
    }

    /**
     * 主键.
     * 本表的主键对应其他表的外键
     *
     * @param table
     * @return
     */
    private static Set<ClazzField> getImportedKeyFields(Table table) {
        Set<ClazzField> fields = new LinkedHashSet<ClazzField>();
        for (ImportedKey importedKey : table.getImportedKeys()) {
            importedKey.getFktableName();

            String className = StringUtils.className(StringUtils.replacePrefix(importedKey.getPktableName()));
            String fieldName = StringUtils.fieldName(StringUtils.replacePrefix(importedKey.getFkcolumnName()));
            if (fieldName.endsWith("Id")) {
                //截取末尾的Id;
                fieldName = fieldName.substring(0, fieldName.length() - "Id".length());
            }
            if ("id".equals(fieldName)) {
                fieldName = "parent";
            }
            Clazz clazz = new ClazzImpl(className);
            ClazzFieldImpl field = new ClazzFieldImpl(fieldName, clazz);

            field.addAnnotation(new SimpleAnnotation("@ManyToOne"));
//            field.addAnnotation(new SimpleAnnotation("@JoinColumn(name = \""+importedKey.getPkcolumnName()+"\")"));=
            fields.add(new ColumnFieldAdapter(field,
                    new TableBuilder().addDataSource(getDataSource())
                            .addTableName(importedKey.getPktableName())
                            .build().getRemarks()));
        }
        return fields;
    }

    private static Set<ClazzMethod> getExportedKeyMethods(Table table) {
        Set<ClazzMethod> methods = new LinkedHashSet<ClazzMethod>();
        for (ExportedKey exportedKey : table.getExportedKeys()) {
            exportedKey.getFktableName();

            String className = StringUtils.className(StringUtils.replacePrefix(exportedKey.getFktableName()));
            String fieldName = StringUtils.fieldName(StringUtils.replacePrefix(exportedKey.getPktableName()));
            Clazz clazz = new ClazzImpl("Set", "<" + className + ">", className + "");
            ClazzFieldImpl field = new ClazzFieldImpl(className + "", clazz);

            ClazzMethodImpl method = new ClazzMethodImpl();

            method.setReturnType(clazz);
            method.setName(className + "");
            Set<Clazz> parameterTypes = new LinkedHashSet<Clazz>();
            parameterTypes.add(clazz);
            method.setParameterTypes(parameterTypes);
            String remarks =
                    new TableBuilder().addDataSource(getDataSource())
                            .addTableName(exportedKey.getPktableName())
                            .build().getRemarks();
            ClazzMethod proxy = (ClazzMethod) RemarksInvocationHandler.proxy(method, remarks);
            methods.add(proxy);
        }

        return methods;
    }

    private static Set<ClazzMethod> getImportedKeyMethods(Table table) {
        Set<ClazzMethod> methods = new LinkedHashSet<ClazzMethod>();
        for (ImportedKey importedKey : table.getImportedKeys()) {
            importedKey.getFktableName();

            String className = StringUtils.className(StringUtils.replacePrefix(importedKey.getPktableName()));
            String fieldName = StringUtils.fieldName(StringUtils.replacePrefix(importedKey.getFkcolumnName()));
            if (fieldName.endsWith("Id")) {
                //截取末尾的Id;
                fieldName = fieldName.substring(0, fieldName.length() - "Id".length());
            }
            if ("id".equals(fieldName)) {
                fieldName = "parent";
            }

            Clazz clazz = new ClazzImpl(className, "", fieldName);

            ClazzMethodImpl method = new ClazzMethodImpl();

            method.setReturnType(clazz);
            method.setName(fieldName);
            Set<Clazz> parameterTypes = new LinkedHashSet<Clazz>();
            parameterTypes.add(clazz);
            method.setParameterTypes(parameterTypes);
            String remarks =
                    new TableBuilder().addDataSource(getDataSource())
                            .addTableName(importedKey.getPktableName())
                            .build().getRemarks();
            ClazzMethod proxy = (ClazzMethod) RemarksInvocationHandler.proxy(method, remarks);
            methods.add(proxy);
        }

        return methods;
    }

    protected static DataSource getDataSource() {
        return GeneratorContext.getContext().getDataSource();
    }
}
