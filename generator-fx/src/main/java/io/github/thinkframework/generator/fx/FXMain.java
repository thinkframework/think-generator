package io.github.thinkframework.generator.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FXMain {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =  SpringApplication.run(FXMain.class,args);

        Application.launch(FXApplication.class, args);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }
}
