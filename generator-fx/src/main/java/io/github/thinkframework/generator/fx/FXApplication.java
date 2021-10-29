package io.github.thinkframework.generator.fx;

import io.github.thinkframework.generator.fx.controler.GeneratorMainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Locale;
import java.util.ResourceBundle;

public class FXApplication extends Application {

    static ApplicationContext applicationContext;

    public static ResourceBundle resourceBundle;

    @Override
    public void start(Stage primaryStage) throws Exception {

        resourceBundle = ResourceBundle.getBundle("messages", Locale.SIMPLIFIED_CHINESE);

        FXMLLoader fxmlLoader = new FXMLLoader(FXApplication.class.getResource("view/main.fxml"),
                resourceBundle,
                null,
                clazz -> new GeneratorMainController());

        Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static void setApplicationContext(ApplicationContext applicationContext) {
        FXApplication.applicationContext = applicationContext;
    }

    public static String[] getBeanNamesForType(@Nullable Class<?> type) {
        return applicationContext.getBeanNamesForType(type);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

}
