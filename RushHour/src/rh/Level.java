package rh;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The Level class contains the map of a Level. The function can create a map by
 * putting given Vehicle objects inside the field, initialize the end points,
 * check if a level is finished and can check if a Vehicle can move to a specified
 * location and moves it. It also keeps the map stored as a string.
 * Created by aatahanm on 11/11/2018.
 */
public class Level {

    private static final int maxWidth = 6;
    private static final int maxHeight = 6;
    private static final int minWidth = 0;
    private static final int minHeight = 0;
    private int map[][];
    private boolean unlocked;
    private int endX;
    private int endY;
    private Car objCar;
    private int highScore;
    private ArrayList<Vehicle> vCollection;
    private int vLength;

    /**
     * constructor of the Level class.Takes no parameters.
     */
    public Level() {
        map = new int[maxHeight][maxHeight];
        endX = 0;
        endY = 0;
        objCar = new Car();
        unlocked = false;
        highScore = 0;
        vCollection = new ArrayList<>();
        vLength = 0;
    }

    public Level(int map[][], boolean unlocked, int endX, int endY, Car objCar, int highScore,
                 ArrayList<Vehicle> vCollection, int vLength){

        int mp[][] = new int[6][6];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++ ){
                mp[i][j] = map[i][j];
            }
        }
        this.map = mp;
        this.unlocked = unlocked;
        this.endY = endY;
        this.endX = endX;

        this.highScore = highScore;

        ArrayList<Vehicle> vc = new ArrayList<>();
        Car c = new Car();
        c.createVehicle(vCollection.get(0).getX(),vCollection.get(0).getY(),vCollection.get(0).getLength(),
                vCollection.get(0).getDirection());
        this.objCar = c;

        vc.add(c);
        for (int i = 1;  i < vCollection.size(); i++ ){
            Vehicle v = new Vehicle();
            v.createVehicle(vCollection.get(i).getX(),vCollection.get(i).getY(),
                    vCollection.get(i).getLength(),vCollection.get(i).getDirection());
            vc.add(v);
        }


        //c.createVehicle(objCar.getX(),objCar.getY(),objCar.getLength(),objCar.getDirection());
        this.objCar = (Car)vc.get(0);

        this.vCollection = vc;

        this.vLength = vLength;
    }

    /**
     * A function that creates the initial level according given parameters.
     *
     * @param vCollection Vehicle array. All vehicles inside a map.
     * @param endX        int x coordinate of the endpoint.
     * @param endY        int y coordinate of the endpoint.
     * @param objCar      Car object.
     */
    public void createLevel(ArrayList<Vehicle> vCollection, int endX, int endY, Car objCar) {
        this.objCar = objCar;
        this.endX = endX;
        this.endY = endY;
        this.vCollection = vCollection;
        this.vLength = vCollection.size();

        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < maxWidth; j++) {
                map[i][j] = 0;
            }
        }
        if (vCollection.size() != 0) {
            for (int i = 0; i < vCollection.size(); i++) {
                if (vCollection.get(i).getDirection().equals("V")) {
                    for (int j = 0; j < vCollection.get(i).getLength(); j++) {
                        map[vCollection.get(i).getX() + j][vCollection.get(i).getY()] = 1;
                    }
                } else if (vCollection.get(i).getDirection().equals("H")) {
                    for (int j = 0; j < vCollection.get(i).getLength(); j++) {
                        map[vCollection.get(i).getX()][vCollection.get(i).getY() + j] = 1;
                    }
                }

            }
        }
    }

    /**
     * a function that checks whether a given vehicle can move to a selected
     * point on the map. If a move is possible the function moves the vehicle
     * to the given point and returns true. If not it returns false.
     *
     * @param v Vehicle object
     * @param x int x coordinate
     * @param y int y coordinate
     * @return
     */
    public boolean canMove(Vehicle v, int x, int y) {
        if (x < maxHeight && x >= minHeight && y < maxWidth && y >= minWidth) {
            if (map[x][y] != 1) {
                if (v.getDirection().equals("H") && v.getX() == x) {
                    if (!(v.getY() - y >= 2) && v.getY() > y) {
                        moveVehicle(v, x, y);
                        return true;
                    } else if (!(y - (v.getY() + v.getLength() - 1) >= 2) && v.getY() < y) {
                        moveVehicle(v, x, y);
                        return true;
                    }
                } else if (v.getDirection().equals("V") && v.getY() == y) {

                    if (!(v.getX() - x >= 2) && v.getX() > x) {
                        moveVehicle(v, x, y);
                        return true;
                    } else if (!(x - (v.getX() + v.getLength() - 1) >= 2) && (v.getX() < x)) {
                        moveVehicle(v, x, y);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * A helper function of canMove function move a Vehicle object
     * to the given coordinates and updates the map
     *
     * @param v Vehicle object
     * @param x int x coordinate
     * @param y int y coordinate
     */
    public void moveVehicle(Vehicle v, int x, int y) {
        if (v.getDirection().equals("H") && v.getY() < y) {
            map[v.getX()][v.getY()] = 0;
            v.moveTo(x, y);
            map[x][y++] = 1;
        } else if (v.getDirection().equals("H") && v.getY() > y) {
            map[x][y] = 1;
            v.moveTo(x, y);
            map[x][y + v.getLength()] = 0;
        }
        if (v.getDirection().equals("V") && v.getX() > x) {
            map[v.getX() + v.getLength() - 1][v.getY()] = 0;
            v.moveTo(x, y);
            map[x][y] = 1;
        } else if (v.getDirection().equals("V")) {
            map[v.getX()][v.getY()] = 0;
            v.moveTo(x, y);
            map[x++][y] = 1;
        }
    }

    /**
     * A function that checks whether objCar has passed through endpoint
     *
     * @return 'true' if a level is finished 'false' if not
     */
    public boolean isFinished() {
        System.out.println("heyoo");
        if (objCar.getDirection().equals("H")) {
            if ((objCar.getY() + objCar.getLength() - 1) == endY
                    && objCar.getX() == endX) {
                return true;
            }
        } else if (objCar.getDirection().equals("V")) {
            if ((objCar.getX() + objCar.getLength() - 1) == endX
                    && objCar.getY() == endY) {
                return true;
            }
        }
        return false;
    }

    public void setHighScore (int score){
        highScore = score;
    }

    public Vehicle getVehicle(int x) {
        return vCollection.get(x);
    }

    public int getVehicleCount() {
        return vLength;
    }

    public int[][] getMap(){ return map;}

    public ArrayList<Vehicle> getvCollection(){return vCollection;}

    public int getEndX(){ return endX;}

    public int getEndY(){ return endY;}

    public Car getObjCar(){ return objCar;}

    public boolean getUnlocked(){ return unlocked;}

    //TODO
    public int getHighScore() {
        return highScore;
    }

    public void unlockLevel(){
        unlocked = true;
    }


    /**
     * A function that stores the map as a string
     *
     * @return string a text based visual representation of the game
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                s += " " + (map[i][j]);
            }
            s += "\n";
        }
        return s;
    }

}
