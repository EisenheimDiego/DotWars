package ac.ucr.b66958.proyecto.media;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class GameMediaPlayer {

    private static Media media;
    private static MediaPlayer mediaPlayer;

    public static void playEndGame(){
        media = new Media(new File(
                "src/main/java/ac/ucr/b66958/proyecto/media/sounds/endgame.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static void playAmbiance(){
        media = new Media(new File(
                "src/main/java/ac/ucr/b66958/proyecto/media/sounds/The Midnight Wood.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }

    public static void playStart(){
        media = new Media(new File(
                "src/main/java/ac/ucr/b66958/proyecto/media/sounds/fight.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static void playDead(){
        Media dead = new Media(new File(
                "src/main/java/ac/ucr/b66958/proyecto/media/sounds/fatality.mp3").toURI().toString());
        MediaPlayer playerDead = new MediaPlayer(dead);
        playerDead.play();
    }

    public static void playHit(){
        Media hit = new Media(new File(
                "src/main/java/ac/ucr/b66958/proyecto/media/sounds/hit.mp3").toURI().toString());
        MediaPlayer playerHit = new MediaPlayer(hit);
        playerHit.play();
    }

}
