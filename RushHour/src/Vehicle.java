/**
 * The Vehicle class contains the coordinates, length and direction
 * of a vehicle. It can create a new vehicle and move a vehicle to
 * a new location.
 * Created by aatahanm on 11/11/2018.
 */
public class Vehicle {

    private int x;
    private int y;
    private int length;
    private String direction;

    /**
     * Constructor. Takes no parameters and initializes the coordinates
     * and length as zero.
     */
    Vehicle(){
        x = 0;
        y = 0;
        length = 0;
        direction = "";
    }

    /**
     * A function that creates a new Vehicle according the given parameters.
     * @param x int x coordinate
     * @param y int y coordinate
     * @param length int length of a vehicle.
     * @param direction int direction of a vehicle.
     */
    public void createVehicle(int x,int y,int length,String direction){
        this.x = x;
        this.y = y;
        this.length = length;
        this.direction = direction;
    }

    /**
     * A function that moves a vehicle to the given location by changing
     * its coordinates
     * @param x int x coordinate
     * @param y int y coordinate
     */
    public void moveTo(int x, int y){
        if (this.x >= x)
            this.x = x;
        else
            this.x++;
        if(this.y >= y)
            this.y = y;
        else
            this.y++;
    }

    /**
     * Getter function for direction.
     * @return string direction.
     */
    public String getDirection(){
        return direction;
    }

    /**
     * Getter function for length
     * @return int length
     */
    public int getLength(){
        return length;
    }

    /**
     * Getter function for x
     * @return int x
     */
    public int getX(){
        return x;
    }

    /**
     * Getter function for y
     * @return int y
     */
    public int getY(){
        return y;
    }
}
