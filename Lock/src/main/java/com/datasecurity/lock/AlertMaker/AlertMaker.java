package com.datasecurity.lock.AlertMaker;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class AlertMaker {
    public void showInfoAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        Image icon = new Image("Images/icon.png");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void showWarningAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        Image icon = new Image("Images/icon.png");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public boolean showConfermationAlert(String title, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        Image icon = new Image("Images/icon.png");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
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
        Image icon = new Image("Images/icon.png");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
