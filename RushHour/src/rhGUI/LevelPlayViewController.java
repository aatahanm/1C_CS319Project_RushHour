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
import rh.Storage;
import rh.Tutorial;

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
    private Sound special1 = new Sound();
    private Sound backgroundMusic;
    private boolean music;
    private boolean sound;
    private boolean finished;
    private Storage data;
    private int levelNum;
    private ImageView popup;
    private ImageView popup2;
    private boolean specialStarted = false;
    private Pane specialPane;
    private Tutorial t = new Tutorial();

    @FXML
    private ImageView popupBackground = new ImageView();
    @FXML
    private ImageView play = new ImageView();
    @FXML
    private Text endMsg = new Text();

    @FXML
    private ImageView backgroundImg;
    @FXML
    private ImageView nextLevel;
    @FXML
    private ImageView selectLevel;
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
    @FXML
    private ImageView homeButton;
    @FXML
    private ImageView returnButton;


    /**
     * The constructor
     *
     * @param sLevel seleted Level
     */
    public LevelPlayViewController(Level sLevel, Sound backgroundMusic, boolean music, boolean sound, Storage data, int levelNum) {
        this.backgroundMusic = backgroundMusic;
        this.music = music;
        this.sLevel = sLevel;
        this.sound = sound;
        this.data = data;
        rLevel = new Level();
        this.levelNum = levelNum;
        finished = false;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime()));

        //initializing the horizontal vehicle images
        iCollectionH = new Image[10];
        for (int i = 0; i < 10; i++) {
            if (i < 6)
                iCollectionH[i] = new Image(("rhGUI/Images/1" + "H" + Integer.toString(i) + ".png"));
            else
                iCollectionH[i] = new Image(("rhGUI/Images/2" + "H" + Integer.toString(i - 6) + ".png"));
        }
        //initializing the vertical vehicle images
        iCollectionV = new Image[10];
        for (int i = 0; i < 10; i++) {
            if (i < 6)
                iCollectionV[i] = new Image(("rhGUI/Images/1" + "V" + Integer.toString(i) + ".png"));
            else
                iCollectionV[i] = new Image(("rhGUI/Images/2" + "V" + Integer.toString(i - 6) + ".png"));
        }
    }

    public void homeButtonEntered() {
        Image image = new Image("/rhGUI/Images/homeO.png");
        homeButton.setImage(image);
        player.playMouseOverSound(sound);
    }

    public void homeButtonExited() {
        Image image = new Image("/rhGUI/Images/home.png");
        homeButton.setImage(image);
    }

    public void returnButtonEntered() {
        Image image = new Image("/rhGUI/Images/returnO.png");
        returnButton.setImage(image);
        player.playMouseOverSound(sound);
    }

    public void returnButtonExited() {
        Image image = new Image("/rhGUI/Images/return.png");
        returnButton.setImage(image);
    }

    /**
     * A eventlistener to exit from the level and return to main menu.
     *
     * @param e MouseEvent
     */
    public void returnMenu(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/MenuView.fxml"));
            MenuViewController cont = new MenuViewController(backgroundMusic, music, sound, data);
            loader.setController(cont);
            Pane root = loader.load();
            stage.setScene(new Scene(root, 800, 600));
            stage.setResizable(true);
        } catch (IOException event) {
            event.printStackTrace();
        }
        player.playClickSound(sound);
        if (levelNum + 1 == 3 && specialStarted)
            special1.stopEffect(sound);
    }

    //TODO
    public void restartLevel(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        player.playClickSound(sound);
        if (levelNum + 1 == 3)
            special1.stopEffect(sound);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/LevelPlayView.fxml"));
            LevelPlayViewController cont = new LevelPlayViewController(sLevel, backgroundMusic, music, sound, data, levelNum);
            loader.setController(cont);
            Pane root = loader.load();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException event) {
            event.printStackTrace();
        }

    }

    /**
     * A function that increases the timer 1 second at a time and resets it for each minute passes.
     */
    private void updateTime() {
        int seconds = timeSeconds.get();
        if (levelNum + 1 == 3 || levelNum + 1 == 6 || levelNum + 1 == 9 || levelNum + 1 == 12) {
            timeSeconds.set(seconds - 1);
            if (timeSeconds.get() < 57 && levelNum + 1 == 3) {
                popup.setImage(null);
            } else if (timeSeconds.get() < 57 && levelNum + 1 == 12) {
                popup.setImage(null);
                popup2.setImage(null);
            }
        } else {
            timeSeconds.set(seconds + 1);
            if (seconds == 59) {
                timeMinutes++;
                timeSeconds.set(0);
                timeM.setText(Integer.toString(timeMinutes));
            }
        }

        if (levelNum + 1 == 3 || levelNum + 1 == 6 || levelNum + 1 == 9 || levelNum + 1 == 12) {
            if (timeSeconds.get() == 0) {
                finished = true;
                ImageView dialogB = new ImageView("rhGUI/Images/Credits_Parchment.png");

                Text endMsg = new Text("Unfortunately you failed this level.\n");
                endMsg.setLayoutX(45);
                endMsg.setLayoutY(120);
                dialogB.setFitHeight(300);
                dialogB.setFitWidth(400);
                Pane s = new Pane();

                s.setLayoutX(200);
                s.setLayoutY(155);

                s.getChildren().addAll(dialogB, endMsg);

                //special1.stopEffect(sound);
                myPane.getChildren().add(s);
                timeline.stop();
            }
        }

    }

    EventHandler<MouseEvent> openNextLevel = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            player.playClickSound(sound);
            int nextLvlNum = levelNum + 1;
            Level next = new Level();
            try {
                next = data.getLevel(nextLvlNum);
            } catch (Exception evnt) {
                evnt.printStackTrace();
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/LevelPlayView.fxml"));
                LevelPlayViewController cont = new LevelPlayViewController(next, backgroundMusic, music, sound, data, nextLvlNum);
                loader.setController(cont);
                Pane root = loader.load();
                stage.setScene(new Scene(root, 800, 600));
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    };

    EventHandler<MouseEvent> returnLevelSelection = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/rhGUI/LevelSelectionView.fxml"));
                LevelSelectionViewController cont = new LevelSelectionViewController(backgroundMusic, music, sound, data);
                loader.setController(cont);
                Pane root = loader.load();
                stage.setScene(new Scene(root, 800, 600));
            } catch (IOException e) {
                e.printStackTrace();
            }

            player.playClickSound(sound);

        }

    };

    EventHandler<MouseEvent> nextLevelEntered = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Image a = new Image("rhGUI/Images/nextLevelOver.png");
            ImageView s = (ImageView) event.getSource();
            s.setImage(a);
            player.playMouseOverSound(sound);

        }

    };

    EventHandler<MouseEvent> nextLevelExited = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Image a = new Image("rhGUI/Images/nextLevel.png");
            ImageView s = (ImageView) event.getSource();
            s.setImage(a);
            player.playMouseOverSound(sound);

        }

    };

    EventHandler<MouseEvent> selectLevelEntered = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Image a = new Image("rhGUI/Images/levelSelectOver.png");
            ImageView s = (ImageView) event.getSource();
            s.setImage(a);
            player.playMouseOverSound(sound);

        }

    };

    EventHandler<MouseEvent> okButtonEntered = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Image a = new Image("rhGUI/Images/okButtonOver.png");
            ImageView s = (ImageView) event.getSource();
            s.setImage(a);
            player.playMouseOverSound(sound);

        }

    };

    EventHandler<MouseEvent> okButtonExited = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Image a = new Image("rhGUI/Images/okButton.png");
            ImageView s = (ImageView) event.getSource();
            s.setImage(a);
            player.playMouseOverSound(sound);

        }

    };

    EventHandler<MouseEvent> selectLevelExited = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Image a = new Image("rhGUI/Images/levelSelect.png");
            ImageView s = (ImageView) event.getSource();
            s.setImage(a);
            player.playMouseOverSound(sound);

        }

    };

    EventHandler<MouseEvent> startSpecialLevel = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (levelNum + 1 == 3) {
                special1.playSpecial1Background(sound);
                popup = new ImageView("rhGUI/Images/special1popup.png");

                popup.setFitHeight(300);
                popup.setFitWidth(400);
                popup.setLayoutX(400);
                popup.setLayoutY(355);

                myPane.getChildren().add(popup);
                player.playSpecialPopup(sound);
            } else if (levelNum + 1 == 12) {
                popup = new ImageView("rhGUI/Images/special12popup1.png");

                popup.setFitHeight(375);
                popup.setFitWidth(300);
                popup.setLayoutX(100);
                popup.setLayoutY(175);

                popup2 = new ImageView("rhGUI/Images/special12popup2.png");

                popup2.setLayoutX(370);
                popup2.setLayoutY(150);

                myPane.getChildren().addAll(popup, popup2);
                player.playSpecial12Popup(sound);
            } else if (levelNum == 13) {
                startTimer();
                if(t.instructionLeft()) {
                    popUpTutorialInformation(t.getNextInstruction(),t.getNextLocX(),t.getNextLocY(),t.getICount());
                }else{
                    specialPane.getChildren().removeAll();
                    specialPane.setVisible(false);
                    myPane.getChildren().remove(specialPane);
                }
            }
            if(levelNum != 13) {
                startTimer();
                specialStarted = true;
                specialPane.getChildren().removeAll();
                specialPane.setVisible(false);
            }


        }

    };


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

        if (a.getVehicle(i).isMainCar()) {
            if (a.isFinished()) {
                finished = true;
                popUpLevelFinished();
            }
        }
    }

    private void popUpTutorialInformation(String text,int x , int y,int popupNo){
        specialStarted = true;
        myPane.getChildren().remove(specialPane);
        specialPane.setLayoutX(0);
        specialPane.setLayoutY(0);

        myPane.getChildren().add(specialPane);

        Image ply = new Image("rhGUI/Images/tutorialpopup.png");

        if(popupNo > 4){
            ply = new Image("rhGUI/Images/tutorialpopup2.png");
        }else if (popupNo == 4){
            ply = new Image("rhGUI/Images/tutorialpopup3.png");
        }
        popupBackground.setImage(ply);
        Image button = new Image("rhGUI/Images/okButton.png");
        play.setImage(button);

        play.setLayoutX(130);
        play.setLayoutY(140);
        if(popupNo == 4){
            play.setLayoutY(192);
        }
        play.setFitHeight(36);
        play.setFitWidth(82);


        endMsg.setText(text);

        endMsg.setLayoutX(69);
        endMsg.setLayoutY(55);
        if(popupNo == 4){
            endMsg.setLayoutY(110);
        }
        popupBackground.setFitHeight(250);
        popupBackground.setFitWidth(340);
        specialPane = new Pane();

        specialPane.setLayoutX(x);
        specialPane.setLayoutY(y);

        specialPane.getChildren().addAll(popupBackground, endMsg, play);
        play.setOnMouseClicked(startSpecialLevel);
        play.setOnMouseEntered(okButtonEntered);
        play.setOnMouseExited(okButtonExited);
        myPane.getChildren().add(specialPane);

    }

    private void popUpLevelInformation(String text) {

        Image ply = new Image("rhGUI/Images/Credits_Parchment.png");
        popupBackground.setImage(ply);
        Image button = new Image("rhGUI/Images/okButton.png");
        play.setImage(button);

        play.setLayoutX(140);
        play.setLayoutY(200);
        play.setFitHeight(48);
        play.setFitWidth(110);


        endMsg.setText(text);
        endMsg.setLayoutX(45);
        endMsg.setLayoutY(110);
        popupBackground.setFitHeight(300);
        popupBackground.setFitWidth(400);
        specialPane = new Pane();

        specialPane.setLayoutX(200);
        specialPane.setLayoutY(155);

        specialPane.getChildren().addAll(popupBackground, endMsg, play);
        play.setOnMouseClicked(startSpecialLevel);
        play.setOnMouseEntered(okButtonEntered);
        play.setOnMouseExited(okButtonExited);
        myPane.getChildren().add(specialPane);
        timeline.stop();
    }

    private void popUpLevelFinished() {
        int highscore = 0;
        if (levelNum + 1 == 3 || levelNum + 1 == 6 || levelNum + 1 == 9 || levelNum + 1 == 12) {
            highscore = 3000 - moveC * 10 + timeSeconds.get() * 20;
        } else {
            highscore = 3000 - moveC * 10 - timeSeconds.get() * 20;
        }

        ImageView dialogB = new ImageView("rhGUI/Images/Credits_Parchment.png");
        nextLevel = new ImageView("rhGUI/Images/nextLevel.png");
        selectLevel = new ImageView("rhGUI/Images/levelSelect.png");

        nextLevel.setLayoutY(200);
        nextLevel.setLayoutX(240);
        nextLevel.setFitHeight(50);
        nextLevel.setFitWidth(100);
        nextLevel.setOnMouseClicked(openNextLevel);
        nextLevel.setOnMouseEntered(nextLevelEntered);
        nextLevel.setOnMouseExited(nextLevelExited);

        selectLevel.setLayoutX(50);
        selectLevel.setLayoutY(200);
        selectLevel.setFitHeight(48);
        selectLevel.setFitWidth(140);
        selectLevel.setOnMouseClicked(returnLevelSelection);
        selectLevel.setOnMouseEntered(selectLevelEntered);
        selectLevel.setOnMouseExited(selectLevelExited);

        if(levelNum != 13) {
            if (sLevel.getHighScore() < highscore) {
                endMsg = new Text("Congratulations! You completed this level\n" +
                        "by breaking a new record!"
                        + "\nPrevious Score was: " + sLevel.getHighScore()
                        + "\nYour Score is: " + highscore +
                        "\n\t    What would you like to do?\n");

                sLevel.setHighScore(highscore);
            } else {
                endMsg = new Text("Congratulations! You completed this level.\n" +
                        "\t    What would you like to do?\n"
                        + "Your Score is: " + highscore
                );
            }
        }else{
            endMsg = new Text("Congratulations! You completed the tutorial\n" +
                    "you are ready to play the game!");
        }
        endMsg.setLayoutX(45);
        endMsg.setLayoutY(110);
        dialogB.setFitHeight(300);
        dialogB.setFitWidth(400);
        Pane s = new Pane();

        s.setLayoutX(200);
        s.setLayoutY(155);
        if (levelNum + 1 == 12 || levelNum == 13) {
            nextLevel.setVisible(false);
        }
        s.getChildren().addAll(dialogB, endMsg, nextLevel, selectLevel);

        Level next = new Level();
        if (levelNum + 1 != 12 && levelNum != 13) {
            try {
                next = data.getLevel((levelNum + 1));
            } catch (Exception evnt) {
                evnt.printStackTrace();
            }

            next.unlockLevel();
        }


        myPane.getChildren().add(s);
        timeline.stop();
        if (levelNum + 1 == 3) {
            special1.stopEffect(sound);
            player.playSpecial1Finished(sound);
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

    public void startTimer() {
        timeline.setCycleCount(Animation.INDEFINITE); // repeat over and over again
        timeline.play();
        timeS.textProperty().bind(timeSeconds.asString());
        timeM.setText(Integer.toString(timeMinutes));
        moves.setText(Integer.toString(moveC));
    }

    /**
     * A function that puts the vehicles inside the level to their position by calling the addVehicle function and
     * assigns event listeners to each of them If a move is possible it call moveVehicle function and increases move
     * count by 1.
     */
    public void initialize() {
        Level a = new Level();
        levelNo.setText("Level: " + (levelNum + 1));
        if (levelNum == 13) {
            levelNo.setText("TUTORIAL");
        }
        a.copyLevel(sLevel.getMap(), sLevel.getUnlocked(), sLevel.getEndX(),
                sLevel.getEndY(), sLevel.getObjMainCar(), sLevel.getHighScore(), sLevel.getvCollection(), sLevel.getVehicleCount());


        t.copyLevel(sLevel.getMap(), sLevel.getUnlocked(), sLevel.getEndX(),
                sLevel.getEndY(), sLevel.getObjMainCar(), sLevel.getHighScore(), sLevel.getvCollection(),
                sLevel.getVehicleCount());

        Random rand = new Random();


        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!finished) {
                    ImageView vehicle = (ImageView) event.getSource();
                    //left click is moving down or right
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
                        //right click is moving up or left
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
        if (levelNum + 1 == 12) {
            image = new Image("rhGUI/Images/batmobile.png");
        } else if (levelNum + 1 == 3) {
            image = new Image("rhGUI/Images/policecar.png");
        } else if (levelNum + 1 == 6) {
            image = new Image("rhGUI/Images/ambulance.png");
        } else if (levelNum + 1 == 9) {
            image = new Image("rhGUI/Images/firetruck.png");
        }

        addVehicle(image, a, 0, eventHandler);

        for (int i = 1; i < a.getVehicleCount(); i++) {
            int img2 = rand.nextInt(6);
            int img3 = (rand.nextInt(4) + 6);
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

        if (levelNum + 1 == 3 || levelNum + 1 == 6 || levelNum + 1 == 9 || levelNum + 1 == 12 || levelNum == 13) {

            if (levelNum + 1 == 3) {
                timeSeconds.set(60);
                popUpLevelInformation("In this level you are a police officer. You need \n " +
                        "to pass the EXIT before the bomb explodes.\nPeople's lives are in your hands.");

            } else if (levelNum + 1 == 6) {
                timeSeconds.set(60);
                popUpLevelInformation("In this level you are an ambulance driver. You \n need to pass the EXIT in " +
                        "time to save the \npatient. Be quick!");
            } else if (levelNum + 1 == 9) {
                timeSeconds.set(60);
                popUpLevelInformation("In this level you are a firefighter. You \n need to pass the EXIT in time to save" +
                        " lives.");
            } else if (levelNum + 1 == 12) {
                timeSeconds.set(60);

                Image spcl = new Image("rhGUI/Images/special12background.jpg");
                backgroundImg.setImage(spcl);
                popUpLevelInformation("You are the hero Gotham deserves, but \nnot the one it needs now.");

            } else if (levelNum == 13) {
                timeSeconds.set(0);
                System.out.println("he");
                popUpLevelInformation("Welcome to the tutorial. In this section you will \nlearn how to interact with the game" +
                        "what your \nobjective is and things that you should consider.");
            }
        } else {
            timeSeconds.set(STARTTIME);
            startTimer();
        }

    }

}

