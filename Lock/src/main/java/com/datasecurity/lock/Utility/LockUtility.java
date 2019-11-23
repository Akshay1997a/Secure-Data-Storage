package com.datasecurity.lock.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import com.datasecurity.lock.AlertMaker.AlertMaker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LockUtility {
    public final static String DEFAULT_EXTENSION = "encrypted";

    public Stage loadWindow(String title, String fxmlPath, Stage stage, boolean decoreted) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        Image icon = new Image("Images/icon.png");
        if (stage != null) {
            stage.setScene(scene);
        } else {
            Stage Secondarystage = new Stage();
            Secondarystage.setScene(scene);
        }
        if (decoreted) {
            stage.initStyle(StageStyle.DECORATED);
        } else {
            stage.initStyle(StageStyle.UNDECORATED);
        }
        stage.getIcons().add(icon);
        stage.setTitle(title);
        stage.show();
        return stage;
    }

    public boolean isFileLocked(String path) {
        int index = path.lastIndexOf(".");
        String ext = path.substring(index + 1);
        System.out.println(ext);
        if (ext.equals("encrypted")) {
            return true;
        } else {
            return false;
        }
    }

    public String getFileName(String sourcePath) {
        File file = new File(sourcePath);
        String fileName  = file.getName();
        int indx = fileName.lastIndexOf(".");
        return fileName.substring(0,indx);
    }

    public String getDestPath(String sourcePath) {
        int endIndex = sourcePath.lastIndexOf(".");
        String destPath = sourcePath.substring(0, endIndex) + "." + DEFAULT_EXTENSION;
        return destPath;
    }

    /*
     * public String getFileCreationTime(String filePath) throws IOException { Path
     * path = Paths.get(filePath); BasicFileAttributes obj=
     * Files.readAttributes(path, BasicFileAttributes.class); /*String time =
     * obj.toString(); int index = time.indexOf("T"); String date =
     * time.substring(0,index); return obj.toString(); }
     */

    public String getFileExtension(String sourcePath) {
        int lastIdx = sourcePath.lastIndexOf(".");
        String fileExtension = sourcePath.substring(lastIdx+1);
        return fileExtension;
    }

    public void createKey(String fileName, String ext, String path)
            throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("Files.json"));

        JSONObject jsonObject = (JSONObject) obj;
        
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(ext);
        jsonArray.add(path);

        jsonObject.put(fileName, jsonArray);

        try{
            FileWriter fw = new FileWriter("Files.json");
            fw.write(jsonObject.toJSONString());
            fw.flush();
            fw.close();
        }catch(Exception e){
            new AlertMaker().showErrorAlert("title", e.getMessage());
        }
    }
}