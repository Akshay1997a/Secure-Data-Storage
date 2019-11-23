package com.datasecurity.locker.UiController;

import java.net.URL;
import java.util.ResourceBundle;
import com.datasecurity.locker.MainApp;
import com.datasecurity.locker.AlertMaker.AlertMaker;
import com.datasecurity.locker.Utility.LockerUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class Login implements Initializable {

    int trypass = 0;
    double xoffset = 0;
    double yoffset = 0;

    @FXML
    PasswordField passField;
    @FXML
    Label errorLabel;
    @FXML
    AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onPasswordFieldAction(ActionEvent event) {
        onLoginAction(event);
    }

    @FXML
    public void onLoginAction(ActionEvent event) {
        Stage stage = new Stage();
        LockerUtility util = new LockerUtility();
        MainApp mainApp = new MainApp();
        if (passField.getText().equals(util.getPassword())) {
            try {
                stage = util.loadWindow("Locker", "/fxml/Scene1.fxml", null, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mainApp.getStage().close();
            mainApp.setStage(stage);
        } else {
            System.out.println(util.getPassword()+"  "+ passField.getText());
            errorLabel.setText("Incorrect Password!");
            passField.setText("");
            trypass++;
            if (trypass == 3) {
                new AlertMaker().showInfoAlert("Login", "You have exceeded your limits");
                System.exit(0);
            }
        }
    }

    @FXML
    void closeButAction(ActionEvent event){
        System.exit(0);
    }

    @FXML
    public void onDragged(MouseEvent event) {
        MainApp mainApp = new MainApp();
        double x = event.getScreenX();
        double y = event.getScreenY();
        mainApp.getStage().setX(x + xoffset);
        mainApp.getStage().setY(y + yoffset);
    }

    @FXML
    public void onPressed(MouseEvent event) {
        MainApp mainApp = new MainApp();
        double x = event.getScreenX();
        double y = event.getScreenY();
        xoffset = mainApp.getStage().getX() - x;
        yoffset = mainApp.getStage().getY() - y;
    }

}