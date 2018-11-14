/**
 * The Level class contains the map of a Level. The function can create a map by
 * putting given Vehicle objects inside the field, initialize the end points,
 * check if a level is finished and can check if a Vehicle can move to a specified
 * location and moves it. It also keeps the map stored as a string.
 * Created by aatahanm on 11/11/2018.
 */
public class Level {

    private int map[][];
    private boolean unlocked;
    private int endX;
    private int endY;
    private Car objCar;

    /**
     * constructor of the Level class.Takes no parameters.
     */
    Level (){
        map = new int[6][6];
        endX = 0;
        endY = 0;
        unlocked = false;
        objCar = new Car();
        unlocked = false;
    }

    /**
     * A function that creates the initial level according given parameters.
     * @param vCollection Vehicle array. All vehicles inside a map.
     * @param endX int x coordinate of the endpoint.
     * @param endY int y coordinate of the endpoint.
     * @param objCar Car object.
     */
    public void createLevel(Vehicle[] vCollection, int endX, int endY,Car objCar){
        this.objCar=objCar;
        this.endX = endX;
        this.endY = endY;

        for(int i=0;i<6;i++){
            for(int j = 0; j<6;j++){
                map[i][j]=0;
            }
        }
        if(vCollection.length != 0) {
            for (int i = 0; i < vCollection.length; i++) {
                if (vCollection[i].getDirection() == "V") {
                    for (int j = 0; j < vCollection[i].getLength(); j++) {
                        map[vCollection[i].getX() + j][vCollection[i].getY()] = 1;
                    }
                } else if (vCollection[i].getDirection() == "H") {
                    for (int j = 0; j < vCollection[i].getLength(); j++) {
                        map[vCollection[i].getX()][vCollection[i].getY() + j] = 1;
                    }
                }

            }
        }
    }

    /**
     * a function that checks whether a given vehicle can move to a selected
     * point on the map. If a move is possible the function moves the vehicle
     * to the given point and returns true. If not it returns false.
     * @param v Vehicle object
     * @param x int x coordinate
     * @param y int y coordinate
     * @return
     */
    public boolean canMove(Vehicle v,int x, int y){
        if (map[x][y] != 1){
            if(v.getDirection().equals("H") && v.getX()==x){
                if(!(v.getY()-y >= 2) && v.getY() > y){
                    moveVehicle(v,x,y);
                    return true;
                }else if (!(y - (v.getY()+v.getLength()-1) >= 2) && v.getY() < y){
                    moveVehicle(v,x,y);
                    return true;
                }
            }else if (v.getDirection().equals("V") && v.getY()==y) {

                if(!(v.getX() - x >= 2) && v.getX() > x){
                    moveVehicle(v,x,y);
                    return true;
                }else if ( !(x - (v.getX()+v.getLength()-1) >= 2) && (v.getX() < x) ){
                    moveVehicle(v,x,y);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * A helper function of canMove function move a Vehicle object
     * to the given coordinates and updates the map
     * @param v Vehicle object
     * @param x int x coordinate
     * @param y int y coordinate
     */
    public void moveVehicle(Vehicle v,int x, int y){
        map[v.getX()][v.getY()] = 0;
        v.moveTo(x,y);
        map[x][y] = 1;
    }

    /**
     * A function that checks whether objCar has passed through endpoint
     * @return 'true' if a level is finished 'false' if not
     */
    public boolean isFinished(){
        if(objCar.getDirection().equals("H")){
            if((objCar.getY()+objCar.getLength()-1) == endY
                    && objCar.getX() == endX){

                return true;
            }
        }else if (objCar.getDirection().equals("V")){
            if((objCar.getX()+objCar.getLength()-1) == endX
                    && objCar.getY() == endY) {
                return true;
            }
        }
        return false;
    }

    /**
     * A function that stores the map as a string
     * @return string a text based visual representation of the game
     */
    public String toString(){
        String s = "";
        for(int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                s += " " + (map[i][j]);
            }
            s += "\n";
        }
        return s;
    }
}
