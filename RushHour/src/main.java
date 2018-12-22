
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;
import rh.*;
import rhGUI.LevelPlayViewController;
import rhGUI.MenuViewController;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * The main class is a tester to check the implemented functions
 * work or not.
 * Created by aatahanm on 11/11/2018.
 */
public class main extends Application{

    private boolean music = true;
    private Sound backgroundMusic = new Sound();
    private Storage data = new Storage();

    @Override
    public void start(Stage primaryStage) throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/MenuView.fxml"));
            MenuViewController cont = new MenuViewController(backgroundMusic, true,true, data);
            cont.playMusic(music);
            loader.setController(cont);
            Pane root = loader.load();
            primaryStage.setTitle("Rush Hour");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.setResizable(true);
            primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);

    }
}