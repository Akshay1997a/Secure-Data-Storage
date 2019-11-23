package com.datasecurity.locker.Utility;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

public class LockerUtility {
    
    public Stage loadWindow(String title, String fxmlPath, Stage stage, boolean decoreted) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Image icon = new Image("Images/ic_locker.png");
        if(stage != null){
            stage.setScene(scene);
        }
        else{
            Stage Secondarystage = new Stage();
            Secondarystage.setScene(scene);
            stage = Secondarystage;
        }
        if(decoreted){
            stage.initStyle(StageStyle.DECORATED);
        }else{
            stage.initStyle(StageStyle.UNDECORATED);
        }
        stage.getIcons().add(icon);
        stage.setTitle(title);
        stage.show();
        return stage;
    }

    public int getSize(){
        JSONParser parser=null;
        Object obj=null;
        JSONObject jsonObject=null;
        try {
            parser = new JSONParser();
            obj = parser.parse(new FileReader("Files.json"));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject.size();
    }

    public Set getName(){
        JSONParser jsonParser = null;
        Object obj = null;
        JSONObject jsonObject = null;
        try {
            jsonParser = new JSONParser();
            obj = jsonParser.parse(new FileReader("Files.json"));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> keys = jsonObject.keySet();
        return keys;
    }

    public String getPassword(){
        FileInputStream fin ;
        String password="";
        int read;
        try {
            fin = new FileInputStream("password.txt");
            while((read=fin.read()) != -1){
                password = password + (char) read;
            }
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
        return password;
    }

}