package com.example.spaceinvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {
    public GameEngine gameEngine;
    public double posX;
    public double posY;

    public Enemy(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        posX = this.gameEngine.viewEnemy3.getX();
        posY = this.gameEngine.viewEnemy3.getY();
    }

    public void resetImgEnemy(ImageView viewEnemy) {
        viewEnemy.setX(0);
        viewEnemy.setY(-200);
    }
}
