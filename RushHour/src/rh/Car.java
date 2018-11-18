package rh;

/**
 * The Car class extends the Vehicle class and its functions.
 * the class is used to separate the objective car with the
 * rest of the Vehicles.
 * Created by aatahanm on 11/11/2018.
 */
public class Car extends Vehicle {

    private int x;
    private int y;
    private int length;
    private String direction;

    public Car (){
        super();
    }

    public void createVehicle(int x, int y, int length, String direction) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.direction = direction;
    }

    public void moveTo(int x, int y) {
        if (this.x >= x)
            this.x = x;
        else
            this.x++;
        if (this.y >= y)
            this.y = y;
        else
            this.y++;
    }

    public String getDirection(){
        return direction;
    }

    public int getLength(){
        return length;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
