package rhGUI; /**
 * Created by aatah on 11/12/2018.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rh.Car;
import rh.Level;
import rh.Vehicle;

import java.util.Random;


public class LevelApp {
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


   public LevelApp(){
       // lMap = new Rectangle[6][6];
       // lMap[0][0].getArcHeight();
    }

    public void testMouseClicked() {

    }

    public void mouseOver(MouseEvent e) {
        ImageView hmm = (ImageView) e.getSource();
        System.out.print(hmm.getId());
        testRec.setFill(Color.AQUA);
    }

    public void returnMenu(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void mdada(ActionEvent e) {

        //System.out.print(5);
    }


    public void initialize() {

       moves.setText(Integer.toString(moveC));

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
        car[3].createVehicle(4,4,2,"H");
        car[4].createVehicle(1,0,3,"V");
        car[5].createVehicle(1,3,3,"V");
        car[6].createVehicle(0,5,3,"V");
        car[7].createVehicle(5,2,3,"H");

        Level a = new Level();
        a.createLevel(car, 2, 5, (Car) car[0]);
        //a.canMove(car[0],5,5);
        //a.canMove(car[1],2,3);
        //a.canMove(car[2],4,5);

        Random rand = new Random();

        iCollectionH = new Image[8];
        for (int i = 0; i < 8; i++) {
            if(i<4)
                iCollectionH[i] = new Image(("rhGUI/1" + "H" + Integer.toString(i) + ".png"));
            else
                iCollectionH[i] = new Image(("rhGUI/2" + "H" + Integer.toString(i-4) + ".png"));
        }
        iCollectionV = new Image[8];
        for (int i = 0; i < 8; i++) {
            if ( i<4)
                iCollectionV[i] = new Image(("rhGUI/1" + "V" + Integer.toString(i) + ".png"));
            else
                iCollectionV[i] = new Image(("rhGUI/2" + "V" + Integer.toString(i-4) + ".png"));
        }
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ImageView vehicle = (ImageView) event.getSource();

                if (event.getButton() == MouseButton.PRIMARY) {
                    for (int i = 0; i < a.getVehicleCount(); i++) {
                        if (vehicle.getId().equals(Integer.toString(i))) {
                            if (car[i].getDirection().equals("V")) {
                                if (a.canMove(car[i], car[i].getX() + car[i].getLength(), car[i].getY())) {
                                    myPane.getChildren().remove(((ImageView) event.getSource()).getParent());
                                    vehicle.setId(Integer.toString(i));
                                    vehicle.setX(55 + car[i].getY() * 55);
                                    vehicle.setY(102 + car[i].getX() * 55);

                                    Text text = new Text("");
                                    StackPane stack = new StackPane();
                                    stack.getChildren().addAll(vehicle, text);
                                    stack.setLayoutX(vehicle.getX());
                                    stack.setLayoutY(vehicle.getY());
                                    myPane.getChildren().add(stack);
                                    moveC++;
                                    moves.setText(Integer.toString(moveC));
                                    if(a.isFinished()){
                                        levelNo.setText("Level Completed!");
                                    }
                                }
                            } else if (car[i].getDirection().equals("H")) {
                                if (a.canMove(car[i], car[i].getX(), car[i].getY() + car[i].getLength())) {
                                    myPane.getChildren().remove(((ImageView) event.getSource()).getParent());
                                    vehicle.setId(Integer.toString(i));
                                    vehicle.setX(55 + car[i].getY() * 55);
                                    vehicle.setY(102 + car[i].getX() * 55);

                                    Text text = new Text("");
                                    StackPane stack = new StackPane();
                                    stack.getChildren().addAll(vehicle, text);
                                    stack.setLayoutX(vehicle.getX());
                                    stack.setLayoutY(vehicle.getY());
                                    myPane.getChildren().add(stack);
                                    moveC++;
                                    moves.setText(Integer.toString(moveC));
                                    if(a.isFinished()){
                                        levelNo.setText("Level Completed");
                                    }
                                }

                            }
                        }
                    }
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    for (int i = 0; i < a.getVehicleCount(); i++) {
                        if (vehicle.getId().equals(Integer.toString(i))) {
                            if (car[i].getDirection().equals("V")) {
                                if (a.canMove(car[i], car[i].getX() - 1, car[i].getY())) {
                                    myPane.getChildren().remove(((ImageView) event.getSource()).getParent());
                                    vehicle.setId(Integer.toString(i));
                                    vehicle.setX(55 + car[i].getY() * 55);
                                    vehicle.setY(102 + car[i].getX() * 55);

                                    Text text = new Text("");
                                    StackPane stack = new StackPane();
                                    stack.getChildren().addAll(vehicle, text);
                                    stack.setLayoutX(vehicle.getX());
                                    stack.setLayoutY(vehicle.getY());
                                    myPane.getChildren().add(stack);
                                    moveC++;
                                    moves.setText(Integer.toString(moveC));
                                }
                            } else if (car[i].getDirection().equals("H")) {
                                if (a.canMove(car[i], car[i].getX(), car[i].getY() - 1)) {
                                    myPane.getChildren().remove(((ImageView) event.getSource()).getParent());
                                    vehicle.setId(Integer.toString(i));
                                    vehicle.setX(55 + car[i].getY() * 55);
                                    vehicle.setY(102 + car[i].getX() * 55);

                                    Text text = new Text("");
                                    StackPane stack = new StackPane();
                                    stack.getChildren().addAll(vehicle, text);
                                    stack.setLayoutX(vehicle.getX());
                                    stack.setLayoutY(vehicle.getY());
                                    myPane.getChildren().add(stack);
                                    moveC++;
                                    moves.setText(Integer.toString(moveC));
                                }

                            }
                        }
                    }

                }

            }

        };

        Image exitV = new Image("rhGUI/exit.jpg");
        ImageView exit = new ImageView(exitV);
        exit.setFitHeight(50);
        exit.setFitWidth(50);
        exit.setX(331);
        exit.setY(212);
        myPane.getChildren().add(exit);

        Image image = new Image("rhGUI/objCarH.png");

        ImageView vec = new ImageView(image);
        vec.setId(Integer.toString(0));
        vec.setPreserveRatio(false);
        vec.setFitHeight(46);
        vec.setFitWidth(a.getVehicle(0).getLength() * 50);
        vec.setX(55 + a.getVehicle(0).getY() * 55);
        vec.setY(102 + a.getVehicle(0).getX() * 55);

        Text text = new Text("");
        StackPane stack = new StackPane();
        stack.getChildren().addAll(vec, text);
        stack.setLayoutX(vec.getX());
        stack.setLayoutY(vec.getY());

        myPane.getChildren().add(stack);

        vec.setOnMouseClicked(eventHandler);

        for (int i = 1; i < a.getVehicleCount(); i++) {
            if (car[i].getDirection().equals("H")) {
                int img2 = rand.nextInt(3);
                int img3 = (rand.nextInt(3)+4) ;
                System.out.print(img3);
                if (car[i].getLength() == 2)
                    image = iCollectionH[img2];
                else
                    image = iCollectionH[img3];

                vec = new ImageView(image);
                vec.setId(Integer.toString(i));
                vec.setPreserveRatio(false);
                vec.setFitHeight(46);
                vec.setFitWidth(car[i].getLength() * 50);
                vec.setX(55 + car[i].getY() * 55);
                vec.setY(102 + car[i].getX() * 55);

                text = new Text("");
                stack = new StackPane();
                stack.getChildren().addAll(vec, text);
                stack.setLayoutX(vec.getX());
                stack.setLayoutY(vec.getY());

                myPane.getChildren().add(stack);

                vec.setOnMouseClicked(eventHandler);

            }
            if (car[i].getDirection().equals("V")) {

                int img2 = rand.nextInt(3);
                int img3 = (rand.nextInt(3)+4);

                if (car[i].getLength() == 2)
                    image = iCollectionV[img2];
                else
                    image = iCollectionV[img3];

                vec = new ImageView(image);
                vec.setId(Integer.toString(i));
                vec.setFitHeight(car[i].getLength() * 50);
                vec.setFitWidth(46);
                vec.setX(55 + car[i].getY() * 55);
                vec.setY(102 + car[i].getX() * 55);

                text = new Text("");
                stack = new StackPane();
                stack.getChildren().addAll(vec, text);
                stack.setLayoutX(vec.getX());
                stack.setLayoutY(vec.getY());


                myPane.getChildren().add(stack);

                vec.setOnMouseClicked(eventHandler);

            }
        }

    }

}

