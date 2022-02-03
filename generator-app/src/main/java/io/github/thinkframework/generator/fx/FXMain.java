package io.github.thinkframework.generator.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FXMain {

    public static void main(String[] args) throws Exception {
        System.setProperty("java.awt.headless", "false");
        FXApplication.setApplicationContext(new ClassPathXmlApplicationContext("applicationContext.xml"));
        Application.launch(FXApplication.class,args);
    }
}
