package org.think.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.think.generator.context.GeneratorContext;
import org.think.generator.lang.GeneratorRuntimeException;
import org.think.generator.sql.TableFactory;
import org.think.generator.sql.model.impl.TableImpl;
import org.think.generator.util.StringUtils;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;

/**
 *
 */
public class GeneratorFacade {
    private Logger logger = LoggerFactory.getLogger(GeneratorContext.class);
    public GeneratorFacade(){
        GeneratorContext.setContext(new GeneratorContext());
    }
    private void setContext(DataSource dataSource, String tableName) {
        GeneratorContext.setContext(new GeneratorContext(dataSource,tableName));
    }

    public void generatorTable(DataSource dataSource,String tableName) {
        setContext(dataSource,tableName);
        Collection<TableImpl> tables = new TableFactory(dataSource).getTables(tableName);
        for(TableImpl table : tables) {
            GeneratorContext.getContext().setTableName(table.getTableName());
            try {
                new Generator().generate();
            }catch (Exception e){
                throw new GeneratorRuntimeException(table.getTableName(),e);
            }
//            printA(tables);
//            printB(tables);
        }
    }


    public void generatorTable(DataSource dataSource,String tableName,String modelName) {
        setContext(dataSource,tableName);
        GeneratorContext.getContext().setProperty("moduleName",modelName);
        Collection<TableImpl> tables = new TableFactory(dataSource).getTables(tableName);
        for(TableImpl table : tables) {
            GeneratorContext.getContext().setTableName(table.getTableName());
            try {
                new Generator().generate();
            }catch (Exception e){
                throw new GeneratorRuntimeException(table.getTableName(),e);
            }
//            printA(tables);
//            printB(tables);
        }
    }

    public void printA(Collection<TableImpl> tables){

        try {
            String output = (String)GeneratorContext.getContext().getProperty("output");
            String companyName = (String)GeneratorContext.getContext().getProperty("comapnyName_path");
            String appName = (String)GeneratorContext.getContext().getProperty("appName");
            String modelName = (String)GeneratorContext.getContext().getProperty("modelName");
            String path = output +File.separator +"src"+File.separator + "main" + File.separator+
                    "webapp" + File.separator + "app" +
                    File.separator + modelName +File.separator+modelName+".module.ts";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            StringBuffer stringBuffer = new StringBuffer();
            for (String line = null; (line = bufferedReader.readLine()) != null; line = null) {
                if (line.contains("/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */")) {
                    for(TableImpl table : tables) {
                        String className = StringUtils.className(table.getTableName());
                        stringBuffer.append("import { ").append(appName).append(className).append("Module } from './").append(className).append("/.module';").append(System.lineSeparator());
                    }
                }

                if (line.contains("/* jhipster-needle-add-entity-module - JHipster will add entity modules here */")) {
                    for(TableImpl table : tables) {
                        String className = StringUtils.className(table.getTableName());
                        stringBuffer.append("\t\t").append(appName).append(className).append("Module,").append(System.lineSeparator());
                    }
                }
                stringBuffer.append(line).append(System.lineSeparator());
            }

            System.out.println(stringBuffer.toString());
        }catch (Exception e){
            throw new GeneratorRuntimeException("异常",e);
        }
    }

    public void printB(Collection<TableImpl> tables){
        try {
            String output = (String)GeneratorContext.getContext().getProperty("output");
            String companyName = (String)GeneratorContext.getContext().getProperty("comapnyName_path");
            String appName = (String)GeneratorContext.getContext().getProperty("appName");
            String modelName = (String)GeneratorContext.getContext().getProperty("modelName");
            String path = output +File.separator +"src"+File.separator + "main" + File.separator+
                    "webapp" + File.separator + "app" +
                    File.separator + modelName +File.separator+modelName+".module.ts";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            StringBuffer stringBuffer = new StringBuffer();
            for (String line = null; (line = bufferedReader.readLine()) != null; line = null) {
                if (line.contains("/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */")) {
                    for(TableImpl table : tables) {
                        String className = StringUtils.className(table.getTableName());
                        stringBuffer.append("import { ").append(appName).append(className).append("Module } from './").append(className).append("/.module';").append(System.lineSeparator());
                    }
                }

                if (line.contains("/* jhipster-needle-add-entity-module - JHipster will add entity modules here */")) {
                    for(TableImpl table : tables) {
                        String className = StringUtils.className(table.getTableName());
                        stringBuffer.append("\t\t").append(appName).append(className).append("Module,").append(System.lineSeparator());
                    }
                }
                stringBuffer.append(line).append(System.lineSeparator());
            }

            System.out.println(stringBuffer.toString());
        }catch (Exception e){
            logger.error("e",e);
        }
    }
}
