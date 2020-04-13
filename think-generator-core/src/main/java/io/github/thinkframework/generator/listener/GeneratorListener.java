package io.github.thinkframework.generator.listener;

import io.github.thinkframework.generator.Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class GeneratorListener implements ApplicationContextAware, ApplicationListener {
    Pattern pattern = Pattern.compile("(?<generator>).generatro(?<table>\\w+)");

    private ApplicationContext applicationContext;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Matcher matcher = pattern.matcher(event.getSource().toString());
        if (matcher.matches()) {
            applicationContext.getBean(Generator.class)
                .tableName(matcher.group("table"))
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
