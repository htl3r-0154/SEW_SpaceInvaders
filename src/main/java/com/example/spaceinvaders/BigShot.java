package com.example.spaceinvaders;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class BigShot {
    public GameEngine gameEngine;
    public double posX;
    public double trueHeight;
    public Timeline timeline = new Timeline(new KeyFrame(Duration.millis(8), e -> updateShot()));

    public BigShot(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    public void bigshoot(){
        trueHeight = gameEngine.screenHeight - 220;

        gameEngine.shotSpeed = (int) (trueHeight / 1.44) / 100;

        posX = gameEngine.viewSpaceship.getX() + (gameEngine.spaceshipWidth / 2) - (gameEngine.bigshotWidth / 2);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        gameEngine.viewShot.setX(posX);
        gameEngine.viewShot.setY(trueHeight);

        gameEngine.sound.shotSound("src/main/resources/Sounds/Laser Shot.mp3");
        if (gameEngine.first){
            gameEngine.root.getChildren().add(gameEngine.viewbigShot);
            gameEngine.first = false;
        }
    }

    public void updateShot() {
        gameEngine.viewShot.setY(gameEngine.viewbigShot.getY() - gameEngine.bigshotSpeed);
        if (gameEngine.viewShot.getY() + gameEngine.shotHeight -12 < 0){
            resetImgShot();
        }
        gameEngine.bigcollisionCheck();
    }

    public void resetImgShot(){
        gameEngine.viewbigShot.setY(-100);
        gameEngine.viewbigShot.setX(0);
    }
}
