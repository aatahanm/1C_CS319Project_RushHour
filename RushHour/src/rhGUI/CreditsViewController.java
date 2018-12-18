package rhGUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import rh.Sound;

import java.awt.*;
import java.io.IOException;

/**
 * This is the controller class of the CreditsView screen. It has only one Event listener to return to main menu
 * Created by aatah on 11/19/2018.
 */
public class CreditsViewController {

    private boolean music;
    private Sound player = new Sound();
    private Sound backgroundMusic;
    private boolean sound;

    public CreditsViewController (Sound backgroundMusic,boolean music, boolean sound){
        this.backgroundMusic = backgroundMusic;
        this.music = music;
        this.sound = sound;
    }

    /**
     * The function changes current window to main menu.
     * @param e MouseEvent
     */
    public void returnMenu(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/MenuView.fxml"));
            MenuViewController cont = new MenuViewController(backgroundMusic, music,sound);
            loader.setController(cont);
            Pane root = loader.load();
            stage.setScene(new Scene(root, 800, 600));

        } catch (IOException event) {
            event.printStackTrace();
        }
        player.playClickSound(sound);
    }
}
