package org.think.generator.lang.impl;

import org.think.generator.lang.Clazz;
import org.think.generator.lang.ClazzPackage;
import org.think.generator.lang.annotation.ClazzAnnotation;
import org.think.generator.lang.annotation.ClazzAnnotations;
import org.think.generator.lang.reflect.ClazzField;
import org.think.generator.lang.reflect.ClazzMethod;

import java.util.Collection;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class ClazzImpl implements Clazz {
    private ClazzPackage clazzPackage;
    private String name;
    private String generic="";
    private String simpleName="";
    private Collection<ClazzField> fields;
    private Collection<ClazzField> importedFields;
    private Collection<ClazzField> exportedFields;

    private Collection<ClazzMethod> methods;

    private Collection<ClazzMethod> importedMethods;
    private Collection<ClazzMethod> exportedMethods;
    private ClazzAnnotations annotations = new ClazzAnnotations();

    public ClazzImpl(){

    }

    public ClazzImpl(String simpleName,String generic,String name){
        this.simpleName = simpleName;
        this.generic = generic;
        this.name = name;
    }

    public ClazzImpl(Class clazz){
        setName(clazz.getName());
        setSimpleName(clazz.getSimpleName());
    }

    public ClazzImpl(String simpleName){
        setSimpleName(simpleName);
    }


    public ClazzImpl(String packageName,String simpleName){
        clazzPackage = new ClazzPackageImpl(packageName);
        setSimpleName(simpleName);
    }

    @Override
    public ClazzPackage getPackage() {
        return clazzPackage;
    }


    public ClazzPackage getClazzPackage() {
        return clazzPackage;
    }

    public void setClazzPackage(ClazzPackage clazzPackage) {
        this.clazzPackage = clazzPackage;
    }

    @Override
    public String getName() {
        return getSimpleName();
    }

    @Override
    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    @Override
    public Collection<ClazzField> getFields() {
        return fields;
    }

    public void setFields(Collection<ClazzField> fields) {
        this.fields = fields;
    }

    public Collection<ClazzField> getImportedFields() {
        return importedFields;
    }

    public void setImportedFields(Collection<ClazzField> importedFields) {
        this.importedFields = importedFields;
    }

    public Collection<ClazzField> getExportedFields() {
        return exportedFields;
    }

    public void setExportedFields(Collection<ClazzField> exportedFields) {
        this.exportedFields = exportedFields;
    }

    @Override
    public Collection<ClazzMethod> getMethods() {
        return methods;
    }

    public void setMethods(Collection<ClazzMethod> methods) {
        this.methods = methods;
    }

    public Collection<ClazzMethod> getImportedMethods() {
        return importedMethods;
    }

    public void setImportedMethods(Collection<ClazzMethod> importedMethods) {
        this.importedMethods = importedMethods;
    }

    public Collection<ClazzMethod> getExportedMethods() {
        return exportedMethods;
    }

    public void setExportedMethods(Collection<ClazzMethod> exportedMethods) {
        this.exportedMethods = exportedMethods;
    }

    @Override
    public ClazzAnnotations getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ClazzAnnotations annotations) {
        this.annotations = annotations;
    }

    public void addAnnotation(ClazzAnnotation annotation) {
        annotations.add(annotation);
    }

    @Override
    public String toString() {
        return simpleName+generic;
    }

    public void setName(String name) {
        this.name = name;
    }
}
