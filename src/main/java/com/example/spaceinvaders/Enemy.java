package com.example.spaceinvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        posX = (gameEngine.screenWidth / 9) * (col + 1) - gameEngine.enemyWidth / 2.0;
        posY = row * 100 + 100;
        this.image = images[(int) (Math.random() * 3)];
        this.view = new ImageView(image);
        this.view.setX(posX);
        this.view.setY(posY);
        this.view.setFitWidth(gameEngine.enemyWidth);
        this.view.setFitHeight(gameEngine.enemyHeight);
    }

    public void resetImgEnemy(ImageView viewEnemy) { //change array
        ArrayList<Integer> availablePositions = new ArrayList<>(Arrays.asList(60, 120, 180, 240, 300, 360, 420, 480, 540, 600, 660, 720, 780, 840, 900, 960, 1020, 1080, 1140, 1200, 1260, 1320, 1380));
        int generatePosition = availablePositions.get((int) (Math.random()*availablePositions.size()));
        viewEnemy.setX(generatePosition);
        availablePositions.remove((Integer) generatePosition);
        if(availablePositions.isEmpty()){
            availablePositions.addAll(Arrays.asList(60,120,180,240,300,360,420,480,540,600,660,720,780,840,900,960,1020,1080,1140,1200,1260,1320,1380));
        }

        viewEnemy.setY(-60);
    }




}
