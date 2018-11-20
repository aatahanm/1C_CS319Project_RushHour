package rhGUI;

/**
 * This Class is the Controller class of the LevelPlayView screen. It assigns the values of the components inside
 * LevelPlayView according a given level. It assigns the eventlisteners of vehicle objects inside that level. The class
 * also keeps track of the elapsed time and moves made.
 * Created by aatahanm on 11/12/2018.
 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import rh.Level;
import rh.Sound;

import java.io.IOException;
import java.util.Random;


public class LevelPlayViewController {

    private static final int STARTTIME = 0;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private int timeMinutes = 0;
    private Level sLevel;
    private Level rLevel;
    private Timeline timeline;
    private ImageView vec;
    private StackPane stack;
    private Text text;
    private Sound player = new Sound();

    @FXML
    private Pane myPane;
    @FXML
    private Label moves;
    @FXML
    private Rectangle[][] lMap;
    @FXML
    private Rectangle testRec;
    @FXML
    private int moveC = 0;
    @FXML
    private Image[] iCollectionH;
    @FXML
    private Image[] iCollectionV;
    @FXML
    private Label levelNo;
    @FXML
    private Label timeS;
    @FXML
    private Label timeM;

    /**
     * The constructor
     *
     * @param sLevel seleted Level
     */
    public LevelPlayViewController(Level sLevel) {
        this.sLevel = sLevel;
        rLevel = sLevel;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime()));

        iCollectionH = new Image[8];
        for (int i = 0; i < 8; i++) {
            if (i < 4)
                iCollectionH[i] = new Image(("rhGUI/Images/1" + "H" + Integer.toString(i) + ".png"));
            else
                iCollectionH[i] = new Image(("rhGUI/Images/2" + "H" + Integer.toString(i - 4) + ".png"));
        }
        iCollectionV = new Image[8];
        for (int i = 0; i < 8; i++) {
            if (i < 4)
                iCollectionV[i] = new Image(("rhGUI/Images/1" + "V" + Integer.toString(i) + ".png"));
            else
                iCollectionV[i] = new Image(("rhGUI/Images/2" + "V" + Integer.toString(i - 4) + ".png"));
        }
    }

    /**
     * A eventlistener to exit from the level and return to main menu.
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

    //TODO
    public void restartLevel(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        player.playClickSound();
    }

    /**
     * A function that increases the timer 1 second at a time and resets it for each minute passes.
     */
    private void updateTime() {
        int seconds = timeSeconds.get();
        timeSeconds.set(seconds + 1);
        if (seconds == 59) {
            timeMinutes++;
            timeSeconds.set(0);
            timeM.setText(Integer.toString(timeMinutes));
        }
    }

    /**
     * A function that updates a vehicle by putting it into its new location
     *
     * @param a       Level
     * @param vehicle ImageView
     * @param i       id of the Vehicle
     */
    private void moveVehicle(Level a, ImageView vehicle, int i) {

        vehicle.setId(Integer.toString(i));
        vehicle.setX(56 + a.getVehicle(i).getY() * 73);
        vehicle.setY(100 + a.getVehicle(i).getX() * 74);

        Text text = new Text("");
        StackPane stack = new StackPane();
        stack.getChildren().addAll(vehicle, text);
        stack.setLayoutX(vehicle.getX());
        stack.setLayoutY(vehicle.getY());
        myPane.getChildren().add(stack);
        moveC++;
        moves.setText(Integer.toString(moveC));
        if (a.isFinished()) {
            timeline.stop();
            levelNo.setText("Level Completed");
        }
    }

    /**
     * A function that puts a vehicle inside a level to its appropriate location.
     *
     * @param image        Image of the vehicle
     * @param a            Level
     * @param i            index of the vehicle
     * @param eventHandler EventHandler of the vehicle object
     */
    private void addVehicle(Image image, Level a, int i, EventHandler<MouseEvent> eventHandler) {

        vec = new ImageView(image);
        vec.setId(Integer.toString(i));
        vec.setPreserveRatio(false);
        if (a.getVehicle(i).getDirection().equals("H")) {
            vec.setFitHeight(60);
            vec.setFitWidth(a.getVehicle(i).getLength() * 68);
        } else if (a.getVehicle(i).getDirection().equals("V")) {
            vec.setFitHeight(a.getVehicle(i).getLength() * 68);
            vec.setFitWidth(60);
        }
        vec.setX(56 + a.getVehicle(i).getY() * 73);
        vec.setY(100 + a.getVehicle(i).getX() * 74);

        text = new Text("");
        stack = new StackPane();
        stack.getChildren().addAll(vec, text);
        stack.setLayoutX(vec.getX());
        stack.setLayoutY(vec.getY());

        myPane.getChildren().add(stack);
        vec.setOnMouseClicked(eventHandler);
    }

    /**
     * A function that puts the vehicles inside the level to their position by calling the addVehicle function and
     * assigns event listeners to each of them If a move is possible it call moveVehicle function and increases move
     * count by 1.
     */
    public void initialize() {
        Level a = sLevel;

        timeline.setCycleCount(Animation.INDEFINITE); // repeat over and over again
        timeSeconds.set(STARTTIME);
        timeline.play();
        timeS.textProperty().bind(timeSeconds.asString());
        timeM.setText(Integer.toString(timeMinutes));
        moves.setText(Integer.toString(moveC));

        Random rand = new Random();


        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ImageView vehicle = (ImageView) event.getSource();

                if (event.getButton() == MouseButton.PRIMARY) {
                    for (int i = 0; i < a.getVehicleCount(); i++) {
                        if (vehicle.getId().equals(Integer.toString(i))) {
                            if (a.getVehicle(i).getDirection().equals("V") &&
                                    a.canMove(a.getVehicle(i), a.getVehicle(i).getX() + a.getVehicle(i).getLength(),
                                            a.getVehicle(i).getY())) {
                                myPane.getChildren().remove(((ImageView) event.getSource()).getParent());
                                moveVehicle(a, vehicle, i);

                            } else if (a.getVehicle(i).getDirection().equals("H") &&
                                    a.canMove(a.getVehicle(i), a.getVehicle(i).getX(),
                                            a.getVehicle(i).getY() + a.getVehicle(i).getLength())) {
                                myPane.getChildren().remove(((ImageView) event.getSource()).getParent());
                                moveVehicle(a, vehicle, i);
                            }
                        }
                    }
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    for (int i = 0; i < a.getVehicleCount(); i++) {
                        if (vehicle.getId().equals(Integer.toString(i))) {
                            if (a.getVehicle(i).getDirection().equals("V") &&
                                    a.canMove(a.getVehicle(i), a.getVehicle(i).getX() - 1, a.getVehicle(i).getY())) {
                                myPane.getChildren().remove(((ImageView) event.getSource()).getParent());
                                moveVehicle(a, vehicle, i);
                            } else if (a.getVehicle(i).getDirection().equals("H") &&
                                    a.canMove(a.getVehicle(i), a.getVehicle(i).getX(), a.getVehicle(i).getY() - 1)) {
                                myPane.getChildren().remove(((ImageView) event.getSource()).getParent());
                                moveVehicle(a, vehicle, i);
                            }
                        }
                    }

                }

            }

        };

        Image exitV = new Image("rhGUI/Images/exit.jpg");
        ImageView exit = new ImageView(exitV);
        exit.setFitHeight(68);
        exit.setFitWidth(68);
        exit.setX(420);
        exit.setY(246);
        myPane.getChildren().add(exit);

        Image image = new Image("rhGUI/Images/objCarH.png");

        addVehicle(image, a, 0, eventHandler);

        for (int i = 1; i < a.getVehicleCount(); i++) {
            int img2 = rand.nextInt(3);
            int img3 = (rand.nextInt(3) + 4);
            if (a.getVehicle(i).getDirection().equals("H")) {


                if (a.getVehicle(i).getLength() == 2)
                    addVehicle(iCollectionH[img2], a, i, eventHandler);
                else
                    addVehicle(iCollectionH[img3], a, i, eventHandler);

            }


            if (a.getVehicle(i).getDirection().equals("V")) {

                if (a.getVehicle(i).getLength() == 2)
                    addVehicle(iCollectionV[img2], a, i, eventHandler);
                else
                    addVehicle(iCollectionV[img3], a, i, eventHandler);


            }
        }

    }

}

