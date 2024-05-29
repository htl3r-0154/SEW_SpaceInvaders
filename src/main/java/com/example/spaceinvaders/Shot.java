package com.example.spaceinvaders;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Shot {
    public GameEngine gameEngine;
    public double posX;
    public double trueHeight;
    public Timeline timeline = new Timeline(new KeyFrame(Duration.millis(8), e -> updateShot()));

    public Shot(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void shoot(){
        trueHeight = gameEngine.screenHeight - 220;

        gameEngine.shotSpeed = (int) (trueHeight / 1.44) / 100;

        posX = gameEngine.viewSpaceship.getX() + (gameEngine.spaceshipWidth / 2) - (gameEngine.shotWidth / 2);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        gameEngine.viewShot.setX(posX);
        gameEngine.viewShot.setY(trueHeight);

        gameEngine.sound.shotSound("src/main/resources/Sounds/Laser Shot.mp3");

        if (gameEngine.first){
            gameEngine.root.getChildren().add(gameEngine.viewShot);
            gameEngine.first = false;
        }
    }

    public void updateShot() {
        gameEngine.viewShot.setY(gameEngine.viewShot.getY() - gameEngine.shotSpeed);
        if (gameEngine.viewShot.getY() + gameEngine.shotHeight -12 < 0){
            resetImgShot();
        }
        gameEngine.collisionCheck();
    }

    public void resetImgShot(){
        gameEngine.viewShot.setY(-100);
        gameEngine.viewShot.setX(0);
    }
}
