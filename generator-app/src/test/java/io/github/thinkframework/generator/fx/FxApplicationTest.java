package io.github.thinkframework.generator.fx;

import io.github.thinkframework.generator.fx.FXApplication;
import javafx.application.Application;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FxApplicationTest {

    public static void main(String[] args) throws Exception {
        System.setProperty("java.awt.headless", "false");
        FXApplication.setApplicationContext(new ClassPathXmlApplicationContext("applicationContext.xml"));
        Application.launch(FXApplication.class,args);
    }
}
