package com.datasecurity.locker.UiController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import com.datasecurity.locker.Utility.LockerUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FileExplorer implements Initializable {

    @FXML
    private ListView<Button> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LockerUtility util = new LockerUtility();
        int size = util.getSize();
        Set<String> setkey = util.getName();
        String[] keys = setkey.toArray(new String[setkey.size()]);
		for(int i=0; i<size;i++){
            updateList(keys[i]);
        }
    }
    
    public void updateList(String name){
        Image img = new Image(getClass().getResourceAsStream("/Images/ic_lock.png"));
        ImageView butIcon = new ImageView(img);
        butIcon.setFitHeight(100);
        butIcon.setFitWidth(100);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("open");
        item1.setStyle("-fx-text-fill:#C3C6C8;");
        
        MenuItem item2 = new MenuItem("Unlock");
        item2.setStyle("-fx-text-fill:#C3C6C8;");

        MenuItem item3 = new MenuItem("Backup");
        item3.setStyle("-fx-text-fill:#C3C6C8;");

        contextMenu.getItems().addAll(item1, item2, item3);
        contextMenu.setStyle("-fx-background-color: #2C363F;");

        Button but = new Button(name, butIcon);
        but.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 20px;");
        but.setContextMenu(contextMenu);
        but.setMaxSize(900, 900);
        but.setAlignment(Pos.BASELINE_LEFT);
        but.setId("listBut");
        list.getItems().add(but);
    }

    @FXML
    void searchFileAction(ActionEvent event){
        
    }

}
