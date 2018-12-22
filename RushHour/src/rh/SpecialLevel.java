package rh;

import rh.Level;

/**
 * Created by aatah on 12/22/2018.
 */
public class SpecialLevel extends Level {

    private int remainingTime;

    public SpecialLevel(){
        super();
    }

    public void setRemainingTime(int time){
        remainingTime = time;
    }

    public int getRemainingTime(){
        return remainingTime;
    }

    public boolean failLevel(){
        if(remainingTime == 0){
            return true;
        }
        return false;
    }
}
