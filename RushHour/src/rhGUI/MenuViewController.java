package rhGUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import rh.Sound;


import java.io.IOException;

/**
 * This is the controller class of MenuView screen
 * Created by aatahanm on 11/19/2018.
 */
public class MenuViewController {

    private Sound player = new Sound();
    private Sound backgroundMusic = new Sound();

    public MenuViewController (){
        backgroundMusic.playMusic();
    }

    public void play(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/rhGUI/LevelSelectionView.fxml"));
            root = loader.load();
            stage = new Stage();
            stage.setTitle("Rush Hour");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            //((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException event) {
            event.printStackTrace();
        }
        player.playClickSound();
    }

    /**
     * A listener that closes the window
     *
     * @param e MouseEvent
     */
    public void exit(MouseEvent e) {
        player.playClickSound();

        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    /**
     * A listener that closes the current window and opens Credits screne
     *
     * @param e MouseEvent
     */
    public void openCredits(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/rhGUI/CreditsView.fxml"));
            root = loader.load();
            stage = new Stage();
            stage.setTitle("Rush Hour");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        } catch (IOException event) {
            event.printStackTrace();
        }
        player.playClickSound();

    }

    /**
     * A function that produces the mouseOverButton sound
     *
     * @param e
     */
    public void mouseOverButton(MouseEvent e) {
        player.playMouseOverSound();
    }

    public void stopMusic(){
        backgroundMusic.stopMusic();
    }

}
