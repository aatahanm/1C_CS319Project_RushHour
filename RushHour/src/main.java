
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import rh.Car;
import rh.Level;
import rh.Vehicle;
import rhGUI.LevelApp;

/**
 * The main class is a tester to check the implemented functions
 * work or not.
 * Created by aatahanm on 11/11/2018.
 */
public class main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/LevelApp.fxml"));
        LevelApp cont = new LevelApp();
        loader.setController(cont);
        Pane root = loader.load();
        primaryStage.setTitle("Rush Hour");
        primaryStage.setScene(new Scene(root,600,500));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    /*    Vehicle[] car = new Vehicle[3];
        car[0] = new Car();
        car[1] = new Vehicle();
        car[2] = new Vehicle();
        car[0].createVehicle(5,2,3,"H");
        car[1].createVehicle(0,3,2,"V");
        car[2].createVehicle(4,3,2,"H");
        Level a = new Level();
        a.createLevel(car,4,5,(Car)car[0]);
        System.out.print(car[1].getX());
        System.out.print(a.canMove(car[1],2,3));
        System.out.print(car[1].getX());
    System.out.print(a.toString());
    System.out.print(a.isFinished());*/

    }
}