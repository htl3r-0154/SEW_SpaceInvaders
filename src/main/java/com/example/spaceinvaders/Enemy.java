package com.example.spaceinvaders;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Enemy {
    public GameEngine gameEngine;
    public double posX;
    public double posY;
    public Image[] images = new Image[]{new Image("file:src/main/resources/Images/Enemy1.png"), new Image("file:src/main/resources/Images/Enemy2.png"), new Image("file:src/main/resources/Images/Enemy3.png")};
    public Image image;
    public ImageView view;
    public int moveCounter = 0;
    public boolean first = true;
    public int speed = 1000;
    public Timeline timeline = new Timeline(new KeyFrame(Duration.millis(speed), e -> updateEnemies()));

    public Enemy(GameEngine gameEngine, int row, int col) {
        this.gameEngine = gameEngine;
        posX = (gameEngine.screenWidth / 9) * (col + 1) - gameEngine.enemyWidth / 2.0;
        posY = row * 100 + 100;
        this.image = images[(int) (Math.random() * 3)];
        this.view = new ImageView(image);
        this.view.setX(posX);
        this.view.setY(posY);
        this.view.setFitWidth(gameEngine.enemyWidth);
        this.view.setFitHeight(gameEngine.enemyHeight);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void checkSpeed(){
        switch (gameEngine.enemies.size()){
            case 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24:
                speed = 750;
                break;
            case 10, 11, 12, 13:
                speed = 600;
                break;
            case 5, 6, 7, 8, 9:
                speed = 500;
                break;
            case 3, 4:
                speed = 400;
                break;
            case 2, 1:
                speed = 350;
                break;
        }
    }

    public void resetImgEnemy(ImageView viewEnemy) {
        viewEnemy.setX(0);
        viewEnemy.setY(-200);
        viewEnemy.setVisible(false);
    }

    private void updateEnemies() {
        checkSpeed();
        if (first) {
            for (int i = 0; i < gameEngine.enemies.size(); i++) {
                gameEngine.enemies.get(i);
            }
        }
    }
}
