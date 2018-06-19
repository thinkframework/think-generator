package org.think.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class Application{
    private final static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args){

        log.info(System.getProperty("java.class.path"));
        if(args.length < 1){
            log.info("no args.");
            return;
        }
        String tableName = args[0];
        new GeneratorFacade().generatorTable(null,tableName);
    }
}