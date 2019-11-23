package com.datasecurity.unlock;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import com.datasecurity.unlock.Decryption.Decryption;
import com.datasecurity.unlock.Utility.UnlockUtility;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class FXMLController implements Initializable, Runnable {

    double xoffset = 0;
    double yoffset = 0;

    @FXML
    public Circle circle;

    @FXML
    public ImageView dataImg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLController controller = new FXMLController();
        Thread thread = new Thread(controller);
        setRotate(circle, true, 360, 10);
        blinkImg(dataImg, true, 1);
        thread.start();
    }

    public void setRotate(Circle c, boolean reverse, int angle, int duration) {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration), c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setCycleCount(10000);
        rt.setRate(3);
        rt.play();
    }

    public void blinkImg(ImageView dataImg, boolean reverse, int duration) {
        FadeTransition ft = new FadeTransition(Duration.seconds(duration), dataImg);
        ft.setFromValue(1.0);
        ft.setToValue(0.7);
        ft.setCycleCount(10000);
        ft.play();
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
    public void onPrasse(MouseEvent event) {
        MainApp mainApp = new MainApp();
        double x = event.getScreenX();
        double y = event.getScreenY();
        xoffset = mainApp.getStage().getX() - x;
        yoffset = mainApp.getStage().getY() - y;
    }

    @Override
    public void run() {
        try {
            Decryption decryption = new Decryption();
            MainApp mainApp = new MainApp();
            UnlockUtility util = new UnlockUtility();
            String sourcePath = mainApp.getSourcePath();
            String destPath = util.getDestPath(sourcePath);
            System.out.print(sourcePath + " "+destPath);
            if (decryption.AES(sourcePath, destPath, "akshaydighorikar")) {
                util.removeKey(util.getFileName(sourcePath));
                File file = new File(sourcePath);
                file.delete();
                mainApp.closePlatform();
            }
        } catch (Exception e) {

        }
    }
}
