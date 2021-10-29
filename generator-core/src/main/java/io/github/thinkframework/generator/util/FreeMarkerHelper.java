package io.github.thinkframework.generator.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * FreeMarker工具类
 *
 * @author hdhxby
 */
public class FreeMarkerHelper {

    private GeneratorConfiguration generatorConfiguration;

    public FreeMarkerHelper generatorConfiguration(GeneratorConfiguration generatorConfiguration){
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }
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
            TemplateHashModel stringHelper = (TemplateHashModel) staticModels.get(StringUtils.class.getName());
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

    public File file(Map map,File file,File output) throws GeneratorRuntimeException {
        try (FileWriter writer = new FileWriter(output)) {
            new Template(file.getName(), new FileReader(file), configuration(map.get("template_path").toString()))
                .process(map, writer);
            return output;
        } catch (IOException | TemplateException e) {
            throw new GeneratorRuntimeException(e);
        }
    }

    public String string(Map map,String input) throws GeneratorRuntimeException {
        try (Writer writer = new StringWriter()) {
            new Template(input, new StringReader(input), configuration(map.get("template_path").toString()))
                .process(map, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            throw new GeneratorRuntimeException(e);
        }
    }
}
