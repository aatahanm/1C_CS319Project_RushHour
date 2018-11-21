package rh;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * Created by aatahanm on 11/20/2018.
 */
public class Sound {

    private Media pick;
    private MediaPlayer player;

    public Sound() {
    }

    public void playMouseOverSound() {
        pick = new Media(Paths.get("MouseOver_Button.mp3").toUri().toString()); // replace this with your own audio file
        player = new MediaPlayer(pick);
        player.play();
    }

    public void playClickSound() {
        pick = new Media(Paths.get("MouseClick_Button.mp3").toUri().toString()); // replace this with your own audio file
        player = new MediaPlayer(pick);
        player.play();
    }

    public void playMusic() {
        pick = new Media(Paths.get("theme.mp3").toUri().toString()); // replace this with your own audio file
        player = new MediaPlayer(pick);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }

    //TODO
    public void stopMusic() {
        player.stop();
    }
}
