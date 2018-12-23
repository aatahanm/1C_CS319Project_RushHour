package rh;

import java.util.ArrayList;

/**
 * Created by aatah on 12/23/2018.
 */
public class Tutorial extends Level {

    private ArrayList<String> instructions;
    private int iCount;

    public Tutorial(){
        super();
        instructions = new ArrayList<>() ;

        instructions.add("The red car is the main car which must pass through the EXIT");
        instructions.add("Each vehicle inside the map can be moved by Left Cl");

        iCount = 0;
 }

    public String getNextInstruction(){
        iCount++;
        return instructions.get(iCount-1);
    }

    public boolean instructionLeft(){
        if(instructions.get(iCount).equals("")){
            return false;
        }
        return true;
    }


}
