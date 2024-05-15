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
    public int speed = 1000;

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
    }



    public void resetImgEnemy(ImageView viewEnemy) {
        viewEnemy.setX(0);
        viewEnemy.setY(-200);
        viewEnemy.setVisible(false);
    }




}
