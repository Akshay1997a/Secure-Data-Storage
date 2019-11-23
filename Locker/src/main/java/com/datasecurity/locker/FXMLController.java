package com.datasecurity.locker;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class FXMLController implements Initializable {

	double xoffset = 0;
	double yoffset = 0;

	@FXML
	Button closeBut;
	@FXML
	BorderPane borderPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML
	void closeButAction(ActionEvent event){
		System.exit(0);
	}

	@FXML
    public void onDrag(MouseEvent event) {
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
	void dashboardButAction(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
			borderPane.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	void fileexplorerButAction(ActionEvent event){
		Parent root;
		try{
			root = FXMLLoader.load(getClass().getResource("/fxml/fileexplorer.fxml"));
			borderPane.setCenter(root);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	@FXML
	void settingsButAction(ActionEvent event){
		Parent root;
		try{
			root = FXMLLoader.load(getClass().getResource("/fxml/settings.fxml"));
			borderPane.setCenter(root);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	@FXML
	void aboutButAction(ActionEvent event){
		Parent root;
		try{
			root = FXMLLoader.load(getClass().getResource("/fxml/about.fxml"));
			borderPane.setCenter(root);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}