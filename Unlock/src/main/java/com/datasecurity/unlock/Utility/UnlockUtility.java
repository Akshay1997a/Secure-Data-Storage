package com.datasecurity.unlock.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UnlockUtility {
    
    public Stage loadWindow(String title, String fxmlPath, Stage stage, boolean decoreted) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Image icon = new Image("Images/ic_unlock.png");
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
    
    public String getFileName(String sourcePath){
        File file = new File(sourcePath);
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        return fileName.substring(0,index);
    }

    public String getDestPath(String sourcePath) throws IOException {
        FileInputStream fin = new FileInputStream(sourcePath);
        String ext = "";
        int readInput;
        while ((readInput = fin.read()) != '?'){
            ext = ext + (char) readInput;
        }
        fin.close();
        sourcePath = sourcePath.replace("encrypted", ext);
        return sourcePath;
    }

    public String getPassword() throws IOException {
        String password="";
        int readInt;
        FileInputStream file=null;
        try {
            file = new FileInputStream("Password.txt");
        } catch (FileNotFoundException e) {
            File file1 = new File("Password.txt");
            file1.createNewFile();
        }
        while((readInt = file.read()) != -1){
            password=password+ (char) readInt;
        }
        return password;
    }


    /*public String getFileCreationTime(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        BasicFileAttributes obj=  Files.readAttributes(path, BasicFileAttributes.class);
        /*String time = obj.toString();
        int index = time.indexOf("T");
        String date = time.substring(0,index);
        return obj.toString();
    }*/

    public void removeKey(String key) throws IOException, ParseException {
        FileReader fr = new FileReader("Files.json");

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fr);
        JSONObject jsonObject = (JSONObject) obj;

        jsonObject.remove(key);

        FileWriter fw = new FileWriter("Files.json");
        fw.write(jsonObject.toJSONString());
        fw.flush();
        fw.close();
        fr.close();
    }
}