package com.datasecurity.lock;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import com.datasecurity.lock.AlertMaker.AlertMaker;
import com.datasecurity.lock.Utility.LockUtility;

public class MainApp extends Application {
    public static Stage primaryStage;
    public static String sourcePath;

    @Override
    public void start(Stage stage) throws IOException {
        LockUtility util = new LockUtility();
        stage = util.loadWindow("Lock", "/fxml/Scene.fxml", stage, false);
        this.primaryStage = stage;
        Platform.setImplicitExit(false);
        stage.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                MainApp mainApp = new MainApp();
                boolean result = new AlertMaker().showConfermationAlert("Lock",
                        "are you sure you want to stop encrypting?");
                if (result) {
                    mainApp.closePlatform();
                } else {
                    event.consume();
                    getStage().show();
                }
            }

        });
    }

    public Stage getStage() {
        return this.primaryStage;
    }

    public void closePlatform() {
        System.exit(0);
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public static void main(String[] args) {
        try {
            sourcePath = args[0];
            launch(args);
        } catch (Exception e) {
            System.exit(0);
        }
    }

}
