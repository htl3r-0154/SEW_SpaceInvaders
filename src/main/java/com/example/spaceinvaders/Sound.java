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
        gameEngine.mediaPlayer1.setVolume(0.3);
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
        gameEngine.shotSFX = new MediaPlayer(m);
        gameEngine.shotSFX.play();
    }

    public void explodeSound1(String path){
        if (gameEngine.explosionSFX1 == null) {
            Media m = new Media(Paths.get(path).toUri().toString());
            gameEngine.explosionSFX1 = new MediaPlayer(m);
            gameEngine.explosionSFX1.setVolume(0.4);
            gameEngine.explosionSFX1.play();
            gameEngine.explosionSFX1.setOnEndOfMedia(() -> gameEngine.explosionSFX1 = null);
        }
        if (gameEngine.explosionSFX1.getStatus().equals(MediaPlayer.Status.PLAYING)){
            explodeSound2(path);
        }

    }

    public void explodeSound2(String path){
        if (gameEngine.explosionSFX2 == null) {
            Media m = new Media(Paths.get(path).toUri().toString());
            gameEngine.explosionSFX2 = new MediaPlayer(m);
            gameEngine.explosionSFX2.setVolume(0.4);
            gameEngine.explosionSFX2.play();
            gameEngine.explosionSFX2.setOnEndOfMedia(() -> gameEngine.explosionSFX2 = null);
        }
        if (gameEngine.explosionSFX2.getStatus().equals(MediaPlayer.Status.PLAYING)){
            explodeSound3(path);
        }
    }

    public void explodeSound3(String path){
        Media m = new Media(Paths.get(path).toUri().toString());
        gameEngine.explosionSFX3 = new MediaPlayer(m);
        gameEngine.explosionSFX3.setVolume(0.4);
        gameEngine.explosionSFX3.play();
    }
}
