package rhGUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the controller class of the CreditsView screen. It has only one Event listener to return to main menu
 * Created by aatah on 11/19/2018.
 */
public class CreditsViewController {

    /**
     * The function closes the current window and opens main menu.
     * @param e MouseEvent
     */
    public void returnMenu(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/rhGUI/MenuView.fxml"));
            root = loader.load();
            stage = new Stage();
            stage.setTitle("Rush Hour");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            //((Node)(e.getSource())).getScene().getWindow().hide();
        }
        catch (IOException event) {
            event.printStackTrace();
        }
    }
}
