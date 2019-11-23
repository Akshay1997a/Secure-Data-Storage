package com.datasecurity.unlock;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;
import java.util.ResourceBundle;
import com.datasecurity.unlock.MainApp;
import com.datasecurity.unlock.AlertMaker.AlertMaker;
import com.datasecurity.unlock.Utility.UnlockUtility;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class Login implements Initializable {
    @FXML
    Button closeBut, unlockBut;

    @FXML
    Label errorLabel;

    @FXML
    PasswordField passField;

    @FXML
    AnchorPane anchorPane;

    int passTry = 0;
    double xoffset = 0;
    double yoffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void unlockButAction(ActionEvent event) {
        passFieldAction(event);
    }

    @FXML
    public void passFieldAction(ActionEvent event) {
        try {
            String password = new UnlockUtility().getPassword();
            if (passField.getText().equals(password)) {
                MainApp mainApp = new MainApp();
                new UnlockUtility().loadWindow("Unlock", "/fxml/Scene.fxml", null, false);
                mainApp.getStage().close();
            } else {
                errorLabel.setText("Incorect Password!");
                passField.setText("");
                passTry++;
                if (passTry == 3) {
                    new AlertMaker().showErrorAlert("Unlock", "Limite exicuted");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onPressed(MouseEvent event) {
        MainApp mainApp = new MainApp();
        double x = event.getScreenX();
        double y = event.getScreenY();
        xoffset = mainApp.getStage().getX() - x;
        yoffset = mainApp.getStage().getY() - y;
    }

    @FXML
    void onDragged(MouseEvent event) {
        MainApp mainApp = new MainApp();
        double x = event.getScreenX();
        double y = event.getScreenY();
        mainApp.getStage().setX(x + xoffset);
        mainApp.getStage().setY(y + yoffset);
    }

    @FXML
    public void closeButAction(ActionEvent event) {
        System.exit(0);
    }
}