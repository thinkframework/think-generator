package io.github.thinkframework.generator.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(null,
                null,
                null,
                clazz -> FXMain.getBean(clazz));

        primaryStage.setScene(new Scene(fxmlLoader.load(FXApplication.class.getResourceAsStream("view/main.fxml"))));
        primaryStage.show();
    }
}
