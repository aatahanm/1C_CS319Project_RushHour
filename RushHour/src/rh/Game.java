package rh;


import java.util.ArrayList;

/**
 * Created by aatah on 11/12/2018.
 */
public class Game {

    private ArrayList<Level> levels;
    private int score;
    private int time;

    /**
     *
     */
    Game() {
        levels = new ArrayList<>();
        score = 0;
        time = 0;
    }

    public Level selectLevel(int i) {
        return levels.get(i);
    }

    public String highlightScore(int i) {
        return "HS:" + levels.get(i).getHighScore();
    }
}
