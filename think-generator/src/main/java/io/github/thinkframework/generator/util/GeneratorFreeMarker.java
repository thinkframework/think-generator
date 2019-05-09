package io.github.thinkframework.generator.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.*;
import io.github.thinkframework.generator.config.GeneratorConfiguration;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * FreeMarker工具类
 * @author lixiaobin
 */
public class GeneratorFreeMarker {
	private Logger log = LoggerFactory.getLogger(getClass());

    private Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

    private GeneratorConfiguration generatorConfiguration;

    public void setTemplateLoader(GeneratorConfiguration generatorConfiguration) throws GeneratorRuntimeException {
        this.generatorConfiguration = generatorConfiguration;
        try {

            configuration.setDirectoryForTemplateLoading(new File(generatorConfiguration.getTemplate()));

            Map map = new HashMap();
            BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
            TemplateHashModel staticModels = wrapper.getStaticModels();
            TemplateHashModel stringHelper = (TemplateHashModel) staticModels.get("io.github.thinkframework.generator.util.StringUtils");
            map.put("StringUtils", stringHelper);

            configuration.setSharedVaribles(map);

            //todo 这三行是否需要
            configuration.setNumberFormat("###############");
            configuration.setBooleanFormat("true,false");
            configuration.setDefaultEncoding("UTF-8");
        } catch (IOException | TemplateModelException e){
            new GeneratorRuntimeException(e);
        }
    }

	public void process(Map model) {
            FileUtils.listFiles(new File(generatorConfiguration.getTemplate()), generatorConfiguration.getExtensions().stream().toArray(String[]::new), true)
                .stream().map(File::toString).forEach(input -> {
                try {
                    //模板出路径
                    StringWriter stringWriter = new StringWriter();
                    new Template("模板路径",new StringReader(input.replace(generatorConfiguration.getTemplate(),generatorConfiguration.getOutput())),configuration)
                        .process(model, stringWriter);
                    log.info("\n模板输入路径:{}\n模板输出路径:{}",input,stringWriter);

                    File file = new File(stringWriter.toString());
                    FileUtils.forceMkdirParent(file);
                    Writer out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
                    configuration.getTemplate(input)
                        .process(model,out);
                    out.close();
                }catch(IOException | TemplateException e) {
                    throw new GeneratorRuntimeException("模板异常",e);
                }
            });
	}
}
