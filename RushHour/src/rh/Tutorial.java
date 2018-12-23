package rh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by aatah on 12/23/2018.
 */
public class Tutorial extends Level {

    private Queue<String> instructions;
    private Queue<Integer> placementX;
    private Queue<Integer> placementY;
    private int iCount;

    public Tutorial(){
        super();
        instructions = new LinkedList<>();
        placementX = new LinkedList<>();
        placementY = new LinkedList<>();

        placementX.add(130);
        placementX.add(300);
        placementX.add(280);
        placementX.add(430);

        placementX.add(330);
        placementX.add(300);
        placementX.add(350);
        placementX.add(410);

        placementY.add(0);
        placementY.add(150);
        placementY.add(40);
        placementY.add(40);

        placementY.add(150);
        placementY.add(0);
        placementY.add(120);
        placementY.add(120);


        instructions.add("The red car is the main car \nwhich must pass through the \nEXIT in order to complete a level." +
                " \nEvery 3 levels there are special levels \nin which main car is different.");
        instructions.add("You need to clear main car's path. \nTo do this, you need to move this car \nfirst. If you press left" +
                "click on a vehicle, \nit goes to the RIGHT or DOWN. This \nprocess is the opposite for right click.");
        instructions.add("Then, you need to move this \nvehicle in order to clear main \ncar's path.");
        instructions.add("Finally, you just need \nto move main car to the EXIT.");
        instructions.add("This counter indicates how much \ntime you spent in solving a level, but \nkeep in mind that in special level" +
                " it \nindicates the remaining time to solve \nthe level. Time affects your score.");
        instructions.add("This counter indicates how many \nmoves you made in a level. \nMoves affect your score.");
        instructions.add("This button directs you to the \nmain menu.");
        instructions.add("This button restarts the level.");

        iCount = 0;
 }

    public String getNextInstruction(){

        return instructions.poll();
    }

    public int getICount(){

        return iCount++;}

    public int getNextLocX(){
        return placementX.poll();
    }

    public int getNextLocY(){
        return placementY.poll();
    }

    public boolean instructionLeft(){
        if(instructions.isEmpty()){
            return false;
        }
        return true;
    }


}
