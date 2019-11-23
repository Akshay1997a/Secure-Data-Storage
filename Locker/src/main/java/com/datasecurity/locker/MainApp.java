package com.datasecurity.locker;

import com.datasecurity.locker.Utility.LockerUtility;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static Stage primaryStage = null;

    @Override
    public void start(Stage stage){
        LockerUtility util = new LockerUtility();
        try{
            stage = util.loadWindow("Login", "/fxml/Login.fxml", stage, false);
            primaryStage = stage;
        }catch(Exception e){

        }
    }

    public void setStage(Stage stage){
        primaryStage = stage;
    }

    public Stage getStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
