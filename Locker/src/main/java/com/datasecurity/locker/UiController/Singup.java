package com.datasecurity.locker.UiController;

import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import com.datasecurity.locker.MainApp;
import com.datasecurity.locker.Database.Database;
import com.datasecurity.locker.Utility.LockerUtility;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Singup implements Initializable {

    double xoffset = 0;
    double yoffset = 0;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    TextField name, user_name;

    @FXML
    PasswordField password, confirm_password;

    @FXML
    Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onSingupAction(ActionEvent event) {
        LockerUtility util = new LockerUtility();
        MainApp mainApp = new MainApp();
        Stage stage = new Stage();
        Database db = new Database();

        byte[] key = null;
        try {
            key = util.generateKey();
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        
        if(!this.password.getText().equals(null)){
            if(this.password.getText().equals(this.confirm_password.getText())){
                if(!u_nameExist(this.user_name.getText())){
                    boolean flag = db.writeData(this.name.getText(), this.user_name.getText(), this.password.getText(), key);
                    if(flag){
                        try {
                            stage = util.loadWindow("Locker", "/fxml/Scene1.fxml", null, false);
                            util.storePasswordHash(password.getText());
                            util.storeKeyHash(key);
                            mainApp.getStage().close();
                            mainApp.setStage(stage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mainApp.getStage().close();
                    }
                }
            }else{
                this.errorLabel.setText("Password not matched");
                this.password.setText("");
                this.confirm_password.setText("");
            }
        }
    }

    @FXML
    public void openSingin(ActionEvent event){
        LockerUtility util = new LockerUtility();
        MainApp mainApp = new MainApp();
        Stage stage = new Stage();

        try {
            stage = util.loadWindow("Sing In", "/fxml/Singin.fxml", null, false);
            new MainApp().getStage().close();
            mainApp.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean u_nameExist(String u_name){
        return false;
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

    @FXML
    void closeButAction(ActionEvent event){
        System.exit(0);
    }
}