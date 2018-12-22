package rh;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * The sound class play different sound for different function calls. Depending on the function call pick and player
 * properties are assigned and used to play the assigned sound/music.
 * Created by aatahanm on 11/20/2018.
 */
public class Sound {

    private Media pick;
    private MediaPlayer player;

    public Sound() {
    }

    /**
     * This function picks the mouseOver effect sound and plays it depending on the give sound value
     * @param sound boolean
     */
    public void playMouseOverSound(boolean sound) {
        if(sound) {
            pick = new Media(Paths.get("src/rhGUI/Sound/MouseOver_Button.mp3").toUri().toString()); // replace this with your own audio file
            player = new MediaPlayer(pick);
            player.setVolume(0.3);
            player.play();
        }
    }

    /**
     * This function pick the click button sound and plays it depending on the given sound value.
     * @param sound boolean
     */
    public void playClickSound(boolean sound) {
        if(sound) {
            pick = new Media(Paths.get("src/rhGUI/Sound/MouseClick_Button.mp3").toUri().toString()); // replace this with your own audio file
            player = new MediaPlayer(pick);
            player.setVolume(0.5);
            player.play();
        }
    }

    /**
     * This function picks the background music of the game and plays it. It continues to play the same music unless
     * stopMusic function is called.
     */
    public void playMusic() {
        pick = new Media(Paths.get("src/rhGUI/Sound/theme.mp3").toUri().toString()); // replace this with your own audio file
        player = new MediaPlayer(pick);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setVolume(0.7);
        player.play();
    }

    public void stopMusic() {
        player.stop();
    }
}
