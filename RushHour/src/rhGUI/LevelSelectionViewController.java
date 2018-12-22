package rhGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Storage test ;

    @FXML
    Pane myPane = new Pane();

    public LevelSelectionViewController(Sound backgroundMusic, boolean music, boolean sound){
        this.backgroundMusic = backgroundMusic;
        this.music = music;
        this.sound = sound;
    }

    public void openLevel(MouseEvent e)
           {
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
            MenuViewController cont = new MenuViewController(backgroundMusic, music, sound);
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

            try {
                selectedLevel = test.getLevel(Integer.valueOf(text.getText()));
                System.out.println(text.getText());
            }catch (Exception es) {
                es.printStackTrace();
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/LevelPlayView.fxml"));
                LevelPlayViewController cont = new LevelPlayViewController(selectedLevel, backgroundMusic, music, sound);
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
        test = new Storage();
        try {
            t = test.getLevels().size();
        }catch (Exception es) {
            es.printStackTrace();
        }

        Color lvlBColor = new Color(0.2,0.7,0.5,1);

        for(int i = 0; i < t ; i++){

            Text lvlNo = new Text();
            StackPane stack = new StackPane();
            Circle lvl = new Circle();

            lvlNo.setText(Integer.toString(i));
            lvl.setRadius(38);
            lvl.setFill(lvlBColor);
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

            stack.getChildren().addAll(lvl,lvlNo);
            myPane.getChildren().add(stack);
            stack.setOnMouseClicked(eventHandler);
        }



    }
}
