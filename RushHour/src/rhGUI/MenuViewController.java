package rhGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import rh.*;


import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the controller class of MenuView screen
 * Created by aatahanm on 11/19/2018.
 */
public class MenuViewController {

    private Sound player = new Sound();
    private Sound backgroundMusic;
    private boolean music;
    private boolean sound;
    private Storage data;


    @FXML
    ImageView exit = new ImageView();
    @FXML
    ImageView play = new ImageView();
    @FXML
    ImageView credits = new ImageView();
    @FXML
    ImageView tutorial = new ImageView();
    @FXML
    ImageView musicButton = new ImageView();
    @FXML
    ImageView soundButton = new ImageView();


    public MenuViewController (Sound backgroundMusic, boolean music, boolean sound, Storage data){
        this.backgroundMusic = backgroundMusic;
        this.music = music;
        this.sound = sound;
        this.data = data;
    }

    public void openTutorial(MouseEvent e){
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Tutorial t = new Tutorial();
        ArrayList<Vehicle> vCollection = new ArrayList<>();
        MainCar objMainCar = new MainCar();

        objMainCar.createVehicle(2,1,2,"H");
        vCollection.add(objMainCar);

        Vehicle vehicle = new Vehicle();

        vehicle.createVehicle(1,3,3,"V");
        vCollection.add(vehicle);

        vehicle = new Vehicle();

        vehicle.createVehicle(4,3,2,"H");
        vCollection.add(vehicle);

        t.createLevel(vCollection,2,5,(MainCar)vCollection.get(0));
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/LevelPlayView.fxml"));
            LevelPlayViewController cont = new LevelPlayViewController((Level)t, backgroundMusic, music, sound,data,13);
            loader.setController(cont);
            Pane root = loader.load();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException evnt) {
            evnt.printStackTrace();
        }

        player.playClickSound(sound);

    }

    public void play(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/LevelSelectionView.fxml"));
            LevelSelectionViewController cont = new LevelSelectionViewController(backgroundMusic, music, sound,data);
            loader.setController(cont);
            Pane root = loader.load();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException event) {
            event.printStackTrace();
        }

        player.playClickSound(sound);
    }

    /**
     * A listener that closes the window
     *
     * @param e MouseEvent
     */
    public void exit(MouseEvent e) {
    player.playClickSound(sound);

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/CreditsView.fxml"));
            CreditsViewController cont = new CreditsViewController(backgroundMusic, music, sound,data);
            loader.setController(cont);
            Pane root = loader.load();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException event) {
            event.printStackTrace();
        }

        player.playClickSound(sound);

    }

    public void mouseEnteredExit(MouseEvent e){
        player.playMouseOverSound(sound);
        Image image = new Image("/rhGUI/Images/exitO.png");
        exit.setImage(image);
    }

    public void mouseExitedExit(MouseEvent e){
        Image image = new Image("/rhGUI/Images/exit.png");
        exit.setImage(image);
    }

    public void mouseEnteredPlay(MouseEvent e){
        player.playMouseOverSound(sound);
        Image image = new Image("/rhGUI/Images/PlayO.png");
        play.setImage(image);
    }

    public void mouseExitedPlay(MouseEvent e){
        Image image = new Image("/rhGUI/Images/PLay.png");
        play.setImage(image);
    }

    public void mouseEnteredTutorial(MouseEvent e){
        player.playMouseOverSound(sound);
        Image image = new Image("/rhGUI/Images/TutorialO.png");
        tutorial.setImage(image);
    }

    public void mouseExitedTutorial(MouseEvent e){
        Image image = new Image("/rhGUI/Images/Tutorial.png");
        tutorial.setImage(image);
    }

    public void mouseEnteredCredits(MouseEvent e){
        player.playMouseOverSound(sound);
        Image image = new Image("/rhGUI/Images/CreditsO.png");
        credits.setImage(image);
    }

    public void mouseExitedCredits(MouseEvent e){
        Image image = new Image("/rhGUI/Images/Credits.png");
        credits.setImage(image);
    }

    public void mouseEnteredMusic(MouseEvent e){
        player.playMouseOverSound(sound);
        Image image = new Image("/rhGUI/Images/musiconOver.png");
        musicButton.setImage(image);
    }

    public void mouseExitedMusic(MouseEvent e){
        if(music) {
            Image image = new Image("/rhGUI/Images/musiconn.png");
            musicButton.setImage(image);
        }else{
            Image image = new Image("/rhGUI/Images/musicoff.png");
            musicButton.setImage(image);
        }
    }

    public void mouseEnteredSound(MouseEvent e){
        player.playMouseOverSound(sound);
        Image image = new Image("/rhGUI/Images/SoundOver.png");
        soundButton.setImage(image);

    }

    public void mouseExitedSound(MouseEvent e){
        if(sound) {
            Image image = new Image("/rhGUI/Images/soundonn2.png");
            soundButton.setImage(image);
        }else{
            Image image = new Image("/rhGUI/Images/Soundoff.png");
            soundButton.setImage(image);
        }
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

    public void stopSound(){
        Image image = new Image("/rhGUI/Images/Soundoff.png");
        soundButton.setImage(image);
        if(sound) {
            sound = false;
        }
        else {
            sound = true;
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
        if(sound) {
            Image image = new Image("/rhGUI/Images/soundonn2.png");
            soundButton.setImage(image);
        }else{
            Image image = new Image("/rhGUI/Images/Soundoff.png");
            soundButton.setImage(image);
        }

        if(music) {
            Image image = new Image("/rhGUI/Images/musiconn.png");
            musicButton.setImage(image);
        }else{
            Image image = new Image("/rhGUI/Images/musicoff.png");
            musicButton.setImage(image);
        }

    }

}
