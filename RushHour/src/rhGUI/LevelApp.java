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

        Vehicle[] car = new Vehicle[4];
        car[0] = new Car();
        car[1] = new Vehicle();
        car[2] = new Vehicle();
        car[3] = new Vehicle();
        car[0].createVehicle(5, 2, 3, "H");
        car[1].createVehicle(0, 3, 2, "V");
        car[2].createVehicle(4, 3, 2, "H");
        car[3].createVehicle(0,0,2,"V");
        Level a = new Level();
        a.createLevel(car, 4, 5, (Car) car[0]);
        //a.canMove(car[0],5,5);
        //a.canMove(car[1],2,3);
        //a.canMove(car[2],4,5);

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

                                    Text text = new Text(Integer.toString(i));
                                    StackPane stack = new StackPane();
                                    stack.getChildren().addAll(vehicle, text);
                                    stack.setLayoutX(vehicle.getX());
                                    stack.setLayoutY(vehicle.getY());
                                    myPane.getChildren().add(stack);
                                    moveC++;
                                    moves.setText(Integer.toString(moveC));
                                    System.out.print(a.toString());
                                }
                            } else if (car[i].getDirection().equals("H")) {
                                if (a.canMove(car[i], car[i].getX(), car[i].getY() + car[i].getLength())) {
                                    myPane.getChildren().remove(((ImageView) event.getSource()).getParent());
                                    vehicle.setId(Integer.toString(i));
                                    vehicle.setX(91 + car[i].getY() * 45);
                                    vehicle.setY(105 + car[i].getX() * 55);

                                    Text text = new Text(Integer.toString(i));
                                    StackPane stack = new StackPane();
                                    stack.getChildren().addAll(vehicle, text);
                                    stack.setLayoutX(vehicle.getX());
                                    stack.setLayoutY(vehicle.getY());
                                    myPane.getChildren().add(stack);
                                    moveC++;
                                    moves.setText(Integer.toString(moveC));
                                    System.out.print(a.toString());
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

                                    Text text = new Text(Integer.toString(i));
                                    StackPane stack = new StackPane();
                                    stack.getChildren().addAll(vehicle, text);
                                    stack.setLayoutX(vehicle.getX());
                                    stack.setLayoutY(vehicle.getY());
                                    myPane.getChildren().add(stack);
                                    moveC++;
                                    moves.setText(Integer.toString(moveC));
                                    System.out.print(a.toString());
                                }
                            } else if (car[i].getDirection().equals("H")) {
                                if (a.canMove(car[i], car[i].getX(), car[i].getY() - 1)) {
                                    myPane.getChildren().remove(((ImageView) event.getSource()).getParent());
                                    vehicle.setId(Integer.toString(i));
                                    vehicle.setX(55 + car[i].getY() * 55);
                                    vehicle.setY(102 + car[i].getX() * 55);

                                    Text text = new Text(Integer.toString(i));
                                    StackPane stack = new StackPane();
                                    stack.getChildren().addAll(vehicle, text);
                                    stack.setLayoutX(vehicle.getX());
                                    stack.setLayoutY(vehicle.getY());
                                    myPane.getChildren().add(stack);
                                    moveC++;
                                    moves.setText(Integer.toString(moveC));
                                    System.out.print(a.toString());
                                }

                            }
                        }
                    }

                }

            }

        };

        for (int i = 0; i < a.getVehicleCount(); i++) {
            if (car[i].getDirection().equals("H")) {
                Image image;

                if (car[i].getLength() == 2)
                    image = new Image("rhGUI/batmobile.png");
                else
                    image = new Image("rhGUI/truckH.png");

                ImageView vec = new ImageView(image);
                vec.setId(Integer.toString(i));
                vec.setPreserveRatio(false);
                vec.setFitHeight(46);
                vec.setFitWidth(car[i].getLength() * 50);
                vec.setX(55 + car[i].getY() * 55);
                vec.setY(102 + car[i].getX() * 55);

                Text text = new Text(Integer.toString(i));
                StackPane stack = new StackPane();
                stack.getChildren().addAll(vec, text);
                stack.setLayoutX(vec.getX());
                stack.setLayoutY(vec.getY());

                myPane.getChildren().add(stack);

                vec.setOnMouseClicked(eventHandler);

            }
            if (car[i].getDirection().equals("V")) {

                Image image;

                if (car[i].getLength() == 2)
                    image = new Image("rhGUI/objcarV.png");
                else
                    image = new Image("rhGUI/truckV.png");

                ImageView vec = new ImageView(image);
                vec.setId(Integer.toString(i));
                vec.setFitHeight(car[i].getLength() * 50);
                vec.setFitWidth(46);
                vec.setX(55 + car[i].getY() * 55);
                vec.setY(102 + car[i].getX() * 55);

                Text text = new Text(Integer.toString(i));
                StackPane stack = new StackPane();
                stack.getChildren().addAll(vec, text);
                stack.setLayoutX(vec.getX());
                stack.setLayoutY(vec.getY());


                myPane.getChildren().add(stack);

                vec.setOnMouseClicked(eventHandler);

            }
        }

    }

}

