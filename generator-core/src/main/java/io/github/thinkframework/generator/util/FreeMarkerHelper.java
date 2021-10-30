package io.github.thinkframework.generator.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import io.github.thinkframework.generator.core.command.FreeMarkerFileCommand;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * FreeMarker工具类
 *
 * @author hdhxby
 */
public class FreeMarkerHelper {

    private static final Logger log = LoggerFactory.getLogger(FreeMarkerHelper.class);

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

    public <T extends OutputStream> T stream(Map map,InputStream file,T output) throws GeneratorRuntimeException {
        try (Writer writer = new OutputStreamWriter(output)) {
            // TODO 默认名称是否有用
            new Template(map.getOrDefault("name","").toString(), new InputStreamReader(file), configuration(map.get("template_path").toString()))
                    .process(map, writer);
            return output;
        } catch (IOException | TemplateException e) {
            throw new GeneratorRuntimeException(e);
        }
    }

    public File file(Map map,File input,File output) throws GeneratorRuntimeException {
        log.info("file: {} -> {}.",input, output);
        try {
            stream(map, new FileInputStream(input),new FileOutputStream(output));
            return output;
        } catch (IOException e) {
            throw new GeneratorRuntimeException(e);
        }
    }

    public String string(Map map,String input) throws GeneratorRuntimeException {
        try (Writer writer = new StringWriter()) {
            // TODO 默认名称是否有用
            new Template(map.getOrDefault("name","").toString(), new StringReader(input), configuration(map.get("template").toString()))
                    .process(map, writer);
            log.debug(writer.toString());
            return writer.toString();
        } catch (IOException | TemplateException e) {
            throw new GeneratorRuntimeException(e);
        }
    }
}
