/**
 * The main class is a tester to check the implemented functions
 * work or not.
 * Created by aatahanm on 11/11/2018.
 */
public class main {

    public static void main(String[] args){
        Vehicle[] car = new Vehicle[3];
        car[0] = new Car();
        car[1] = new Vehicle();
        car[2] = new Vehicle();
        car[0].createVehicle(5,2,3,"H");
        car[1].createVehicle(0,3,2,"V");
        car[2].createVehicle(4,3,2,"H");
        Level a = new Level();
        a.createLevel(car,4,5,(Car)car[0]);
        a.canMove(car[0],5,5);
        a.canMove(car[1],2,3);
        a.canMove(car[2],4,5);

        System.out.print(a.toString());
        System.out.print(a.isFinished());


    }
}