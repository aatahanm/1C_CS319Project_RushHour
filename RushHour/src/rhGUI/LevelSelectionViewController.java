package rhGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rh.*;

import java.io.IOException;

/**
 * Created by aatahanm on 11/20/2018.
 */
public class LevelSelectionViewController {

    private boolean music;
    private Sound player = new Sound();
    private Sound backgroundMusic;
    private Level selectedLevel;
    private boolean sound;
    private Storage data ;

    @FXML
    Pane myPane = new Pane();
    @FXML
    ImageView homeButton;

    public LevelSelectionViewController(Sound backgroundMusic, boolean music, boolean sound, Storage data){
        this.backgroundMusic = backgroundMusic;
        this.music = music;
        this.sound = sound;
        this.data = data;
    }

    public void homeButtonEntered(){
        Image image= new Image("/rhGUI/Images/homeO.png");
        homeButton.setImage(image);
        player.playMouseOverSound(sound);
    }

    public void homeButtonExited(){
        Image image= new Image("/rhGUI/Images/home.png");
        homeButton.setImage(image);
    }

    /**
     * The function closes the current window and opens main menu.
     *
     * @param e MouseEvent
     */
    public void returnMenu(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        //stage.close();

        //Stage primaryStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/MenuView.fxml"));
            MenuViewController cont = new MenuViewController(backgroundMusic, music, sound,data);
            loader.setController(cont);
            Pane root = loader.load();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException event) {
            event.printStackTrace();
        }
        player.playClickSound(sound);
    }
    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            StackPane s = (StackPane) event.getSource();
            Text text = (Text)s.getChildren().get(1);

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            int levelNo = 0;
            try {
                levelNo = Integer.valueOf(text.getText())-1;
                selectedLevel = data.getLevel(levelNo);
            }catch (Exception es) {
                es.printStackTrace();
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/LevelPlayView.fxml"));
                LevelPlayViewController cont = new LevelPlayViewController(selectedLevel, backgroundMusic, music, sound,data,levelNo);
                loader.setController(cont);
                Pane root = loader.load();
                stage.setScene(new Scene(root, 800, 600));
            } catch (IOException evnt) {
                evnt.printStackTrace();
            }
            player.playClickSound(sound);
        }

    };


    public void initialize(){

        int t= 0;

        try {
            t = data.getLevelsSize();
            data.getLevel(0).unlockLevel();
        }catch (Exception es) {
            es.printStackTrace();
        }

        Color lvlBColor = new Color(0.2,0.7,0.5,1);
        Color lvlNoColor = new Color(0.5,0.9,0.1,1);

        for(int i = 0; i < t ; i++){
            Level l = new Level();
            try {
                l = data.getLevel(i);
            }catch (Exception es) {
                es.printStackTrace();
            }

            Text lvlNo = new Text();
            StackPane stack = new StackPane();
            Circle lvl = new Circle();

            lvlNo.setText(Integer.toString(i+1));
            lvl.setRadius(38);

            lvl.setStroke(Color.BLACK);
            lvl.setSmooth(true);
            if(i > 3 && i < 8){
                stack.setLayoutY(310);
                stack.setLayoutX(200 + (i-4)*100);
            } else if (i > 7) {
                stack.setLayoutY(410);
                stack.setLayoutX(200 + (i-8)*100);
            }else{
                stack.setLayoutY(210);
                stack.setLayoutX(200 + i * 100);
            }

            if(l.getUnlocked()) {
                lvl.setFill(Color.LIMEGREEN);
                stack.getChildren().addAll(lvl,lvlNo);
            }else{
                ImageView locked = new ImageView("rhGUI/Images/levelLocked.png");
                locked.setFitHeight(50);
                locked.setFitWidth(35);
                lvl.setFill(Color.DARKRED);
                stack.getChildren().addAll(lvl,lvlNo, locked);
            }


            myPane.getChildren().add(stack);
            stack.setOnMouseClicked(eventHandler);
        }



    }
}
