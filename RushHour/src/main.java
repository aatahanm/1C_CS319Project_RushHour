
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import rh.Car;
import rh.Level;
import rh.Vehicle;
import rhGUI.LevelPlayViewController;

/**
 * The main class is a tester to check the implemented functions
 * work or not.
 * Created by aatahanm on 11/11/2018.
 */
public class main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/rhGUI/MenuView.fxml"));
        root = loader.load();
        primaryStage = new Stage();
        primaryStage.setTitle("Rush Hour");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);

    }
}