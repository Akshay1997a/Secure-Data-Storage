package com.datasecurity.lock;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import com.datasecurity.lock.Utility.LockUtility;
import com.datasecurity.lock.AlertMaker.AlertMaker;
import com.datasecurity.lock.Encryption.Encryption;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.control.TextArea;

public class FXMLController implements Initializable, Runnable {

    public double xoffset = 0;
    public double yoffset = 0;

    @FXML
    public Circle circle;

    @FXML
    public ImageView dataImg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainApp mainApp = new MainApp();
        LockUtility util = new LockUtility();
        FXMLController controller = new FXMLController();
        Thread thread = new Thread(controller);

        setRotate(circle, true, 360, 10);
        blinkImg(dataImg, true, 1);
        if (!util.isFileLocked(mainApp.getSourcePath())) {
            thread.start();
        } else {
            new AlertMaker().showWarningAlert("Lock", "File is already Locked");
            mainApp.closePlatform();
        }
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

    public void run() {
        try {
            MainApp mainApp = new MainApp();
            LockUtility util = new LockUtility();
            Encryption encryption = new Encryption();
            String sourcePath = mainApp.getSourcePath();

            boolean result = encryption.AES(sourcePath, util.getDestPath(sourcePath),"akshaydighorikar");
            if (result) {
                util.createKey(util.getFileName(sourcePath), util.getFileExtension(sourcePath), mainApp.getSourcePath());
                File file = new File(sourcePath);
                file.delete();
                mainApp.closePlatform();
            } else {
                new AlertMaker().showErrorAlert("Lock", "file Not Encrypted");
            }
        } catch (Exception e) {

        }
    }
}
