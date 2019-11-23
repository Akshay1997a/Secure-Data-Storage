package com.datasecurity.locker.UiController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.datasecurity.locker.Utility.LockerUtility;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Singin implements Initializable {

    @FXML
    TextField user_name, email_id;

    @FXML
    PasswordField pass_filed, conform_pass_field;

    @FXML
    Label email_error, password_error;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void singinButAction(ActionEvent event) {
        boolean chack_info_flag = false;
        boolean write_flag = false;
        String user_name = this.user_name.getText();
        String user_pass = this.pass_filed.getText();
        if (email_id.getText().length() > 8 && this.pass_filed.getText().equals(this.conform_pass_field.getText())) {
            flag = checkInfo(user_name, user_pass);
        } else {
            this.email_error.setText("Email address must contain more than 8 charactors");
            this.password_error.setText("Password not matched");
        }

        if (flag) {
            try {
                new LockerUtility().loadWindow("confermation", "/fxml/singin.fxml", null, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkInfo(String email, String pass){
        return false;
    }

}