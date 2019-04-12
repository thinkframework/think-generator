package io.github.thinkframework.generator.util;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.*;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.thinkframework.generator.context.GeneratorContext;

import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * FreeMarker工具类
 * @author lixiaobin
 */
public class FreeMarkerUtils {
	private Logger log = LoggerFactory.getLogger(getClass());
	private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
	private String[] templates;
	private String out;
	private String[] extensions;

	private Properties properties;

	public FreeMarkerUtils(Properties properties){
	    this.properties = properties;
	    init();
    }

    /**
     * 初始化一些模板需要的字段
     * @return 当前对象
     */
	public FreeMarkerUtils init(){
		templates = getProperties().getProperty("template", "input").split(",");
		for(int i = 0 ; i < templates.length; i++){
			if(!"/".equals(File.separator)){
				templates[i] = templates[i].replaceAll("/","\\\\");
			}
		}
		out = getProperties().getProperty("output", "output");
		if(!"/".equals(File.separator)){
			out = out.replaceAll("/","\\\\");
		}
		extensions = getProperties().getProperty("extensions", "java").split(",");
		return this;
	}

	public FreeMarkerUtils setTemplateLoader(String[] templateRootDirs) {
		try{
			FileTemplateLoader[] templateLoaders = new FileTemplateLoader[templateRootDirs.length];
			for(int i = 0; i < templateRootDirs.length; i++) {
				templateLoaders[i] = new FileTemplateLoader(new File(templateRootDirs[i]));
			}
			MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(templateLoaders);
			configuration.setTemplateLoader(multiTemplateLoader);
			configuration.setNumberFormat("###############");
			configuration.setBooleanFormat("true,false");
			configuration.setDefaultEncoding("UTF-8");

		} catch (IOException e) {
			throw new GeneratorRuntimeException("IO异常",e);
		} finally {
			return this;
		}
	}

	public FreeMarkerUtils setSharedVaribles(Properties properties) throws GeneratorRuntimeException {
		try {
			configuration.setSharedVaribles(properties);
		} catch (TemplateModelException e) {
			throw new GeneratorRuntimeException("模板异常异常",e);
		}finally {
			return this;
		}
	}

	public FreeMarkerUtils process(String input, Map model, String output) throws GeneratorRuntimeException {
		try {
			File file = new File(output);
			FileUtils.forceMkdirParent(file);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			Writer out = new OutputStreamWriter(fileOutputStream,"UTF-8");
			put(model);
			configuration.getTemplate(input).process(model,out);
//			fileOutputStream.close();
			out.close();
		}catch(IOException | TemplateException e) {
			throw new GeneratorRuntimeException("模板异常",e);
		} finally {
			return this;
		}
	}

	public FreeMarkerUtils process() {
		setTemplateLoader(templates).setSharedVaribles(getProperties());
		for (String template : templates) {
			Collection<File> files = listFiles(template, extensions, true);
			for (File file : files) {
				String path = file.toString();
				String input = path.replace(template, "");
				String output = path.replace(template, out);
				//TODO 文件输入输出,跨平台 lixiaobin
//				input =  System.getProperty("user.dir")+"/template/app"+input.replaceAll("/",File.separator);
//				output = System.getProperty("user.dir")+"/"+output.replaceAll("/",File.separator);
				output = processString(output,properties,configuration);
				if(!"/".equals(File.separator)){
					output = output.replaceAll("/","\\\\");
				}
				log.info("\n模板输入路径:{}\n模板输出路径:{}",input,output);
				process(input,properties,output);
			}
		}
		return this;
	}

	public String processString(String templateString,Map model,Configuration conf) throws GeneratorRuntimeException {
		StringWriter out = new StringWriter();
		try {
			Template template = new Template("模板路径",new StringReader(templateString),conf);
			template.process(model, out);
			return out.toString();
		}catch(IOException | TemplateException e) {
			throw new GeneratorRuntimeException("模板异常",e);
		}
	}

	public Collection<File> listFiles(String directory, String[] extensions, boolean recursive){
		return FileUtils.listFiles(new File(directory),extensions,recursive);
	}

    protected Properties getProperties() {
        return properties;
    }

    public FreeMarkerUtils setProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

	public void put(Map map){
		try {
			BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			TemplateHashModel staticModels = wrapper.getStaticModels();
			TemplateHashModel stringHelper = (TemplateHashModel) staticModels.get("io.github.thinkframework.generator.util.StringUtils");
			map.put("StringUtils",stringHelper);
		} catch (TemplateModelException e) {
			throw new GeneratorRuntimeException("模板异常",e);
		}
	}
}
