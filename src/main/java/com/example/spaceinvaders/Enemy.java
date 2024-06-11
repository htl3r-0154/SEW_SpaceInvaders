package com.example.spaceinvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Enemy {
    public GameEngine gameEngine;
    public double posX;
    public double posY;
    public Image[] images = new Image[]{new Image("file:src/main/resources/Images/Enemy1.png"), new Image("file:src/main/resources/Images/Enemy2.png"), new Image("file:src/main/resources/Images/Enemy3.png")};
    public Image image;
    public ImageView view;

    public Enemy(GameEngine gameEngine, int row, int col) {
        this.gameEngine = gameEngine;
        posX = (gameEngine.screenWidth / 9) * (col + 1) - gameEngine.enemyWidth / 2;
        posY = row * 100 + 100;
        this.image = images[(int) (Math.random() * 3)];
        this.view = new ImageView(image);
        this.view.setX(posX);
        this.view.setY(posY);
        this.view.setFitWidth(gameEngine.enemyWidth);
        this.view.setFitHeight(gameEngine.enemyHeight);
    }

    public void resetImgEnemy(ImageView viewEnemy, int offset) {
        int randomIndex = (int) (Math.random() * GameEngine.rowcounter.size());
        int random = GameEngine.rowcounter.get(randomIndex);
        GameEngine.rowcounter.remove(randomIndex);
        viewEnemy.setX(((GameEngine.getScreenWidth() / 9) * (random + 1) - GameEngine.getEnemyWidth() / 2)+offset);
        viewEnemy.setY(-60);
        /*for (int i = 0; i < gameEngine.enemies.size(); i++) {
            resetCollision(viewEnemy, i);
        }*/

    }

    /*public void resetCollision(ImageView viewEnemy, int index) {
        Enemy iEnemy = gameEngine.enemies.get(index);
        double iEnemyLeft = iEnemy.posX;
        double iEnemyTop = iEnemy.posY;
        double viewEnemyTop = viewEnemy.getY();
        double iEnemyRight = iEnemyLeft +GameEngine.getEnemyWidth();
        if (iEnemyRight >= viewEnemy.getX() && iEnemyRight <= viewEnemy.getX()+GameEngine.getEnemyWidth()) {
            if(viewEnemyTop + GameEngine.getEnemyHeight() >= iEnemyTop && viewEnemyTop <= iEnemyTop + GameEngine.getEnemyHeight()){
                viewEnemy.setImage(new Image("file:src/main/resources/Images/alienship.png"));

            }
        }
    }*/
}