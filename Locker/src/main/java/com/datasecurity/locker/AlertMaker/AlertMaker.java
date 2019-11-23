package com.datasecurity.locker.AlertMaker;

import com.datasecurity.locker.MainApp;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class AlertMaker {
    public void showInfoAlert(String title, String content) {
        MainApp mainApp = new MainApp();
        Alert alert = new Alert(AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        Image icon = mainApp.getStage().getIcons().get(0);
        stage.getIcons().add(icon);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void showWarningAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        MainApp mainApp = new MainApp();
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        Image icon = mainApp.getStage().getIcons().get(0);
        stage.getIcons().add(icon);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public boolean showConfermationAlert(String title, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        MainApp mainApp = new MainApp();
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        Image icon = mainApp.getStage().getIcons().get(0);
        stage.getIcons().add(icon);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public void showErrorAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        MainApp mainApp = new MainApp();
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        Image icon = mainApp.getStage().getIcons().get(0);
        stage.getIcons().add(icon);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
