package com.example.spaceinvaders;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Sound {
    public GameEngine gameEngine;

    public Sound(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }
    public void music1(String path){
        Media m = new Media(Paths.get(path).toUri().toString());
        gameEngine.mediaPlayer1 = new MediaPlayer(m);
        gameEngine.mediaPlayer1.setVolume(0);
        gameEngine.mediaPlayer1.play();
        gameEngine.mediaPlayer1.setAutoPlay(true);
        gameEngine.mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
    }
    public void music2(String path){
        Media m = new Media(Paths.get(path).toUri().toString());
        gameEngine.mediaPlayer2 = new MediaPlayer(m);
        gameEngine.mediaPlayer2.play();
        gameEngine.mediaPlayer2.setAutoPlay(true);
        gameEngine.mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void shotSound(String path){
        Media m = new Media(Paths.get(path).toUri().toString());
        gameEngine.shotSoundSFX = new MediaPlayer(m);
        gameEngine.shotSoundSFX.play();
    }
}
