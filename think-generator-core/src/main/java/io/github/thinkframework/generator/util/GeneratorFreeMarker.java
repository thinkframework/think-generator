package io.github.thinkframework.generator.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * FreeMarker工具类
 *
 * @author lixiaobin
 */
@Slf4j
public class GeneratorFreeMarker {


    /**
     * FreeMarker配置
     *
     * @throws GeneratorRuntimeException
     */
    private Configuration configuration(String template) throws GeneratorRuntimeException {
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        try {
            Map map = new HashMap();
            BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
            TemplateHashModel staticModels = wrapper.getStaticModels();
            TemplateHashModel stringHelper = (TemplateHashModel) staticModels.get("io.github.thinkframework.generator.util.StringUtils");
            map.put("StringUtils", stringHelper);
            configuration.setSharedVaribles(map);
            //TODO 如果要支持include的话
//            configuration.setTemplateLoader(new FileTemplateLoader(new File(template)));
            configuration.setNumberFormat("###############");
            configuration.setBooleanFormat("true,false");
            configuration.setDefaultEncoding("UTF-8");
            return configuration;
        } catch (TemplateException  e) {
            throw new GeneratorRuntimeException(e);
        }
    }

    /**
     * 输出一个文件
     *
     * @param model
     * @param input
     * @throws GeneratorRuntimeException
     */
    public File process(Map model, File input, File output) throws GeneratorRuntimeException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(output), "UTF-8")) {
            new Template(input.getPath(), new FileReader(input), configuration(model.get("template_path").toString()))
                .process(model, writer);
            return output;
        } catch (IOException | TemplateException e) {
            throw new GeneratorRuntimeException(e);
        }
    }


    /**
     * 输出一个字符串
     *
     * @param model
     * @param input
     * @param output
     * @throws GeneratorRuntimeException
     */
    public String process(Map model, String input, String output) throws GeneratorRuntimeException {
        try (Writer writer = new StringWriter()) {
            //模板出路径
            new Template(input, new StringReader(output), new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS))
                .process(model, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            throw new GeneratorRuntimeException(e);
        }
    }
}
