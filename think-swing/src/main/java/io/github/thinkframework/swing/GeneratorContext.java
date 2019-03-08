package io.github.thinkframework.swing;

import io.github.thinkframework.swing.config.GeneratorConfigureFrame;
import io.github.thinkframework.swing.config.XmlUtil;
import io.github.thinkframework.swing.control.GeneratorControlFrame;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;

/**
 * Mediator 中介者
 * @author hdhxby
 * @email hdhxby@qq.com
 */
@Component
public class GeneratorContext implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	private static GeneratorContext ourInstance = new GeneratorContext();
	public static GeneratorContext getInstance() {
		return ourInstance;
	}

	private GeneratorContext() {
		if(applicationContext == null){
			String config = File.separator+System.getProperty("user.dir") + File.separator+"applicationContext.xml";
			applicationContext = new FileSystemXmlApplicationContext(config);
		}
	}
	private GeneratorControlFrame generatorControlFrame;
	private GeneratorConfigureFrame generatorConfigureFrame;

	public GeneratorControlFrame getGeneratorControlFrame(){
		if(generatorControlFrame == null){
			generatorControlFrame = new GeneratorControlFrame();
		}
		return generatorControlFrame;
	}


	public GeneratorConfigureFrame getGeneratorConfigureFrame(){
		if(generatorConfigureFrame == null){
			generatorConfigureFrame = new GeneratorConfigureFrame();
		}
		return generatorConfigureFrame;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext	= applicationContext;
	}

	public DataSource getDataSource(String id){

		return (DataSource)getApplicationContext().getBean(id);
	}

	public List<String> getDataSourceNames() {
		return new XmlUtil().getDataSourceNames();
	}
}
