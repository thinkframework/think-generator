package io.github.thinkframework.generator.core.internal.builder.table;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.internal.adapter.ColumnFieldAdapter;
import io.github.thinkframework.generator.core.internal.adapter.ColumnMethodAdapter;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.core.internal.lang.impl.ClazzPackageImpl;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzField;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;
import io.github.thinkframework.generator.core.util.StringUtils;
import io.github.thinkframework.generator.core.proxy.IgnonreInvocationHandler;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 通过表生成对应的类
 *
 * @author hdhxby
 */
public class TableClassBuilder {

    private GeneratorConfiguration generatorConfiguration;

    public TableClassBuilder generatorConfiguration(GeneratorConfiguration generatorConfiguration){
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public Clazz build(Table table) {
        String className = StringUtils.upperCamel(table.getTableName());
        ClazzImpl clazz = new ClazzImpl(className);
        clazz.setClazzPackage(new ClazzPackageImpl());

        clazz.setFields(buildField(table));
        clazz.setMethods(buildMethod(table));

        if(generatorConfiguration.getAdvanced()) {
//            clazz.setImportedFields(getImportedKeyFields(table));
//            clazz.setExportedFields(getExportedKeyFields(table));
//            clazz.setImportedMethods(getImportedKeyMethods(table));
//            clazz.setExportedMethods(getExportedKeyMethods(table));
        }
        Clazz proxy = clazz;
        return proxy;
    }


    /**
     * 根据列生成生成字段信息
     *
     * @param table
     * @return 字段信息
     */
    private Set<ClazzField> buildField(Table table) {
        return table.getColumns().stream()
            .map(column -> {
                ColumnFieldAdapter columnFieldAdapter = new ColumnFieldAdapter(column,
                    new ColumnFieldBuilder().generatorConfiguration(generatorConfiguration).build(column));
                return columnFieldAdapter;
            })
            .map(column -> (ClazzField) IgnonreInvocationHandler.proxy(column,generatorConfiguration.getIgnores().contains(column.getColumnName())))
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * 根据列生成方法信息
     *
     * @param table
     * @return 方法信息
     */
    private Set<ClazzMethod> buildMethod(Table table) {
        return table.getColumns().stream()
            .map(column -> {
                ColumnMethodAdapter columnMethodAdapter = new ColumnMethodAdapter(column,
                    new ColumnMethodBuilder().generatorConfiguration(generatorConfiguration).build(column));
                return columnMethodAdapter;
            })
            .map(column -> (ClazzMethod) IgnonreInvocationHandler.proxy(column,generatorConfiguration.getIgnores().contains(column.getColumnName())))
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }
//
//    /**
//     * 外键.
//     * 本表的外键对应其他表的主键
//     *
//     * @param table
//     * @return
//     */
//    private Set<ClazzField> getExportedKeyFields(Table table) {
//        Set<ClazzField> fields = new LinkedHashSet<ClazzField>();
//        for (ExportedKey exportedKey : table.getExportedKeys()) {
//            exportedKey.getFktableName();
//
//            String className = StringUtils.className(StringUtils.replacePrefix(exportedKey.getFktableName()));
//            String fieldName = StringUtils.fieldName(StringUtils.replacePrefix(exportedKey.getFkcolumnName()));
//
//            if (fieldName.endsWith("Id")) {
//                //截取末尾的Id;
//                fieldName = fieldName.substring(0, fieldName.length() - "Id".length());
//            }
//            //集合名称
//            String set = className.substring(0, 1).toLowerCase() + className.substring(1) + "s";
//            Clazz clazz = new ClazzImpl(className);
//            ClazzFieldImpl field = new ClazzFieldImpl(set, clazz);
//
//            field.addAnnotation(new SimpleAnnotation("@OneToMany(mappedBy=\"" + StringUtils.uncapitalize(fieldName) + "\")"));
//            field.addAnnotation(new SimpleAnnotation("@JsonIgnore"));
//            field.addAnnotation(new SimpleAnnotation("@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)"));
//
//            fields.add(new ColumnFieldAdapter(field,
//                    new TableBuilder().addDataSource(getDataSource())
//                            .addTableName(exportedKey.getFktableName())
//                            .build().getRemarks()));
//        }
//        return fields;
//    }
//
//    /**
//     * 主键.
//     * 本表的主键对应其他表的外键
//     *
//     * @param table
//     * @return
//     */
//    private Set<ClazzField> getImportedKeyFields(Table table) {
//        Set<ClazzField> fields = new LinkedHashSet<ClazzField>();
//        for (ImportedKey importedKey : table.getImportedKeys()) {
//            importedKey.getFktableName();
//
//            String className = StringUtils.className(StringUtils.replacePrefix(importedKey.getPktableName()));
//            String fieldName = StringUtils.fieldName(StringUtils.replacePrefix(importedKey.getFkcolumnName()));
//            if (fieldName.endsWith("Id")) {
//                //截取末尾的Id;
//                fieldName = fieldName.substring(0, fieldName.length() - "Id".length());
//            }
//            if ("id".equals(fieldName)) {
//                fieldName = "parent";
//            }
//            Clazz clazz = new ClazzImpl(className);
//            ClazzFieldImpl field = new ClazzFieldImpl(fieldName, clazz);
//
//            field.addAnnotation(new SimpleAnnotation("@ManyToOne"));
////            field.addAnnotation(new SimpleAnnotation("@JoinColumn(name = \""+importedKey.getPkcolumnName()+"\")"));=
//            fields.add(new ColumnFieldAdapter(field,
//                    new TableBuilder().addDataSource(getDataSource())
//                            .addTableName(importedKey.getPktableName())
//                            .build().getRemarks()));
//        }
//        return fields;
//    }
//
//    private Set<ClazzMethod> getExportedKeyMethods(Table table) {
//        Set<ClazzMethod> methods = new LinkedHashSet<ClazzMethod>();
//        for (ExportedKey exportedKey : table.getExportedKeys()) {
//            exportedKey.getFktableName();
//
//            String className = StringUtils.className(StringUtils.replacePrefix(exportedKey.getFktableName()));
//            String fieldName = StringUtils.fieldName(StringUtils.replacePrefix(exportedKey.getPktableName()));
//            Clazz clazz = new ClazzImpl("Set", "<" + className + ">", className + "");
//            ClazzFieldImpl field = new ClazzFieldImpl(className + "", clazz);
//
//            ClazzMethodImpl method = new ClazzMethodImpl();
//
//            method.setReturnType(clazz);
//            method.setName(className + "");
//            Set<Clazz> parameterTypes = new LinkedHashSet<Clazz>();
//            parameterTypes.add(clazz);
//            method.setParameterTypes(parameterTypes);
//            String remarks =
//                    new TableBuilder().addDataSource(getDataSource())
//                            .addTableName(exportedKey.getPktableName())
//                            .build().getRemarks();
//            ClazzMethod proxy = (ClazzMethod) RemarksInvocationHandler.proxy(method, remarks);
//            methods.add(proxy);
//        }
//
//        return methods;
//    }
//
//    private Set<ClazzMethod> getImportedKeyMethods(Table table) {
//        Set<ClazzMethod> methods = new LinkedHashSet<ClazzMethod>();
//        for (ImportedKey importedKey : table.getImportedKeys()) {
//            importedKey.getFktableName();
//
//            String className = StringUtils.className(StringUtils.replacePrefix(importedKey.getPktableName()));
//            String fieldName = StringUtils.fieldName(StringUtils.replacePrefix(importedKey.getFkcolumnName()));
//            if (fieldName.endsWith("Id")) {
//                //截取末尾的Id;
//                fieldName = fieldName.substring(0, fieldName.length() - "Id".length());
//            }
//            if ("id".equals(fieldName)) {
//                fieldName = "parent";
//            }
//
//            Clazz clazz = new ClazzImpl(className, "", fieldName);
//
//            ClazzMethodImpl method = new ClazzMethodImpl();
//
//            method.setReturnType(clazz);
//            method.setName(fieldName);
//            Set<Clazz> parameterTypes = new LinkedHashSet<Clazz>();
//            parameterTypes.add(clazz);
//            method.setParameterTypes(parameterTypes);
//            String remarks =
//                    new TableBuilder().addDataSource(getDataSource())
//                            .addTableName(importedKey.getPktableName())
//                            .build().getRemarks();
//            ClazzMethod proxy = (ClazzMethod) RemarksInvocationHandler.proxy(method, remarks);
//            methods.add(proxy);
//        }
//
//        return methods;
//    }
}
