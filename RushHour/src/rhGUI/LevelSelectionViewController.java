package rhGUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import rh.Car;
import rh.Level;
import rh.Sound;
import rh.Vehicle;

import java.io.IOException;

/**
 * Created by aatahanm on 11/20/2018.
 */
public class LevelSelectionViewController {

    private Sound player = new Sound();

    public void openLevel(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

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
        car[3].createVehicle(4, 4, 2, "H");
        car[4].createVehicle(1, 0, 3, "V");
        car[5].createVehicle(1, 3, 3, "V");
        car[6].createVehicle(0, 5, 3, "V");
        car[7].createVehicle(5, 2, 3, "H");

        Level a = new Level();
        a.createLevel(car, 2, 5, (Car) car[0]);

        Stage primaryStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/LevelPlayView.fxml"));
            LevelPlayViewController cont = new LevelPlayViewController(a);
            loader.setController(cont);
            Pane root = loader.load();
            primaryStage.setTitle("Rush Hour");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.setResizable(true);
            primaryStage.show();
        } catch (IOException event) {
            event.printStackTrace();
        }
        player.playClickSound();
    }

    /**
     * The function closes the current window and opens main menu.
     *
     * @param e MouseEvent
     */
    public void returnMenu(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/MenuView.fxml"));
            MenuViewController cont = new MenuViewController();
            loader.setController(cont);
            Pane root = loader.load();
            primaryStage.setTitle("Rush Hour");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.setResizable(true);
            primaryStage.show();
        } catch (IOException event) {
            event.printStackTrace();
        }
        player.playClickSound();
    }
}
