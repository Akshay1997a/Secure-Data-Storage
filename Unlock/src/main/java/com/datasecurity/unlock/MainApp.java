package com.datasecurity.unlock;

import com.datasecurity.unlock.Utility.UnlockUtility;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static Stage primaryStage;
    public static String sourcePath;

    @Override
    public void start(Stage stage) throws Exception {
        UnlockUtility util = new UnlockUtility();
        stage = util.loadWindow("Unlock", "/fxml/Login.fxml", stage, false);
        this.primaryStage = stage;
    }

    public Stage getStage() {
        return primaryStage;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void closePlatform() {
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            sourcePath = args[0];
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
