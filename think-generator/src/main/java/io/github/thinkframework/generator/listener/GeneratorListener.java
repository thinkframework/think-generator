package io.github.thinkframework.generator.listener;

import io.github.thinkframework.generator.Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;

import java.util.Map;

@Slf4j
public class GeneratorListener implements ApplicationContextAware, ApplicationListener {
    private ApplicationContext applicationContext;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof PayloadApplicationEvent && "generator".equals(event.getSource())) {
            log.info("调用成功");
            Map<String,String> map = ((PayloadApplicationEvent<Map<String, String>>)event).getPayload();
            Generator generator = applicationContext.getBean(Generator.class);
            generator.dataSourceName(map.get("dataSource"))
                .tableName(map.get("tableName"))
                .generate();
        }
    }

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
