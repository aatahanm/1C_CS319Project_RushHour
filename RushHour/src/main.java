
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
        Vehicle[] car = new Vehicle[8];
        car[0] = new Car();
        car[1] = new Vehicle();
        car[2] = new Vehicle();
        car[3] = new Vehicle();
        car[4] = new Vehicle();
        car[5] = new Vehicle();
        car[6] = new Vehicle();
        car[7] = new Vehicle();

        car[0].createVehicle(2, 1, 2, "H");
        car[1].createVehicle(0, 0, 2, "H");
        car[2].createVehicle(4, 0, 2, "V");
        car[3].createVehicle(4,4,2,"H");
        car[4].createVehicle(1,0,3,"V");
        car[5].createVehicle(1,3,3,"V");
        car[6].createVehicle(0,5,3,"V");
        car[7].createVehicle(5,2,3,"H");

        Level a = new Level();
        a.createLevel(car, 2, 5, (Car) car[0]);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/LevelPlayView.fxml"));
        LevelPlayViewController cont = new LevelPlayViewController(a);
        loader.setController(cont);
        Pane root = loader.load();
        primaryStage.setTitle("Rush Hour");
        primaryStage.setScene(new Scene(root,600,500));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);

    }
}