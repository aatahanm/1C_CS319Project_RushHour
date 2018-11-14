/**
 * Created by aatah on 11/12/2018.
 */
public class Game {

    private Level[] levels;
    private int score;
    private int time;

    /**
     *
     */
    Game() {
        levels = new Level[10];
        score = 0;
        time = 0;
    }

    public Level selectLevel(int i) {
        return levels[i];
    }

    public String highlightScore(int i) {
        return "HS:" + levels[i].getHighScore();
    }
}
