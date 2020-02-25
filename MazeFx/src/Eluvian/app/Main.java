package Eluvian.app;
/*
 * Main will imitate various controllers for the program
 * @author Qiyang Zhong
 */
import Eluvian.view.PaneController;
import Eluvian.view.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Eluvian/view/MazeApp.fxml"));
        Parent root = loader.load();

        PaneController mainController = loader.getController();

        primaryStage.setTitle("MazeFx @Qiyang Zhong");
        Scene scene = new Scene(root, 1000, 600);

        mainController.start(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
