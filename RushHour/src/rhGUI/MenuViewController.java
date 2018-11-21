package rhGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private boolean music;

    @FXML
    ImageView exit = new ImageView();
    @FXML
    ImageView play = new ImageView();
    @FXML
    ImageView credits = new ImageView();
    @FXML
    ImageView tutorial = new ImageView();

    public MenuViewController (){

    }

    public void play(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/rhGUI/LevelSelectionView.fxml"));
            root = loader.load();
            stage.setTitle("Rush Hour");
            stage.setScene(new Scene(root, 800, 600));
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

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/rhGUI/CreditsView.fxml"));
            Parent root = loader.load();
            stage.setTitle("Rush Hour");
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException event) {
            event.printStackTrace();
        }
        player.playClickSound();

    }

    public void mouseEnteredExit(MouseEvent e){
        player.playMouseOverSound();
        Image image = new Image("/rhGUI/Images/exitO.png");
        exit.setImage(image);
    }

    public void mouseExitedExit(MouseEvent e){
        Image image = new Image("/rhGUI/Images/exit.png");
        exit.setImage(image);
    }

    public void mouseEnteredPlay(MouseEvent e){
        player.playMouseOverSound();
        Image image = new Image("/rhGUI/Images/PlayO.png");
        play.setImage(image);
    }

    public void mouseExitedPlay(MouseEvent e){
        Image image = new Image("/rhGUI/Images/PLay.png");
        play.setImage(image);
    }

    public void mouseEnteredTutorial(MouseEvent e){
        player.playMouseOverSound();
        Image image = new Image("/rhGUI/Images/TutorialO.png");
        tutorial.setImage(image);
    }

    public void mouseExitedTutorial(MouseEvent e){
        Image image = new Image("/rhGUI/Images/Tutorial.png");
        tutorial.setImage(image);
    }

    public void mouseEnteredCredits(MouseEvent e){
        player.playMouseOverSound();
        Image image = new Image("/rhGUI/Images/CreditsO.png");
        credits.setImage(image);
    }

    public void mouseExitedCredits(MouseEvent e){
        Image image = new Image("/rhGUI/Images/Credits.png");
        credits.setImage(image);
    }

    public void stopMusic(){
        if(music) {
            backgroundMusic.stopMusic();
            music = false;
        }
        else {
            backgroundMusic.playMusic();
            music = true;
        }
    }

    public void playMusic(boolean music) {
        this.music = music;
        if(music)
            backgroundMusic.playMusic();
    }

    public boolean getMusic(){
        return music;
    }

    public void initialize(){

    }

}
