package com.example.spaceinvaders;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Shot {
    public GameEngine gameEngine;
    public double posX;


    public Shot(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void shoot(){
        posX = gameEngine.viewSpaceship.getX() + (gameEngine.spaceshipWidth / 2) - (gameEngine.shotWidth / 2);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> updateShot()));

        if (gameEngine.is4k){
            timeline.setCycleCount(132); // Height = 24 | Speed = 1000px/s | ScreenHeight = 1440px
        } else {
            timeline.setCycleCount(100);
        }
        timeline.play();

        gameEngine.viewShot.setX(posX);
        gameEngine.viewShot.setY(gameEngine.screenHeight - 145);

        gameEngine.sound.shotSound("src/main/resources/Sounds/Laser Shot.mp3");

        if (gameEngine.first){
            gameEngine.root.getChildren().add(gameEngine.viewShot);
            gameEngine.first = false;
        }
    }

    public void updateShot() {
        gameEngine.viewShot.setY(gameEngine.viewShot.getY() - gameEngine.shotSpeed);
        gameEngine.collisionCheck();
    }
}
