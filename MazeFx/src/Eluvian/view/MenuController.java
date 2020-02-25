package Eluvian.view;
/*
 * Manging actions for the top menu bar
 * @author Qiyang Zhong
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class MenuController {
    @FXML
    MenuItem menuAbout, menuSetting, menuTerminate, fileLog, fileReport;

    @FXML
    private void terminate(ActionEvent event){
        Alert alertTerminate = new Alert(Alert.AlertType.CONFIRMATION);
        alertTerminate.setTitle("Confirm action");
        alertTerminate.setHeaderText("Do you want to close the application?");
        ButtonType okButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alertTerminate.getButtonTypes().setAll(okButton, noButton);

        Optional<ButtonType> result = alertTerminate.showAndWait();
        if (result.get() == noButton){
            return;
        }
        System.exit(0);
        Platform.exit();
    }

    @FXML
    private void about(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About MazeFx");
        alert.setHeaderText("MazeFx 2020.02 (beta edition)");
        alert.setContentText("Build #1.2 \n @Qiyang Zhong");
        alert.showAndWait();
    }

    @FXML
    private void preference(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Setting");
        alert.setHeaderText("No preferences");
        alert.showAndWait();
    }
}
