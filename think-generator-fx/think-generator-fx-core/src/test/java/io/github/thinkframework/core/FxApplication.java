package io.github.thinkframework.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
@SpringBootApplication
public class FxApplication extends Application {
    public static ApplicationContext applicationContext;
    public static FXMLLoader fxmlLoader;
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FxApplication.primaryStage = primaryStage;
        primaryStage.setTitle("Think Application");
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(param -> applicationContext.getBean(param));
        fxmlLoader.setLocation(getClass().getResource("/io/github/thinkframework/fx/core/control/main.fxml"));
        Pane myPane = fxmlLoader.load();
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        applicationContext =
            new SpringApplicationBuilder(FxApplication.class)
                .headless(false)
                .run(args);
        launch(args);
    }
}
