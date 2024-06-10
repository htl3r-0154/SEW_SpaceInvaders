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
    int screenWidth = 0;
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

    public int getHor(){
        return (int) posX;
    }
    ArrayList<Integer> availablePositions = new ArrayList<Integer>(Arrays.asList((GameEngine.getScreenWidth() / 9) - GameEngine.getEnemyWidth() / 2, (GameEngine.getScreenWidth() / 9) * (1+1) - GameEngine.getEnemyWidth() / 2, (GameEngine.getScreenWidth() / 9) * (2+1) - GameEngine.getEnemyWidth() / 2, (GameEngine.getScreenWidth() / 9) * (3+1) - GameEngine.getEnemyWidth() / 2, (GameEngine.getScreenWidth() / 9) * (4+1) - GameEngine.getEnemyWidth() / 2, (GameEngine.getScreenWidth() / 9) * (5+1) - GameEngine.getEnemyWidth() / 2,(GameEngine.getScreenWidth() / 9) * (6+1) - GameEngine.getEnemyWidth() / 2, (GameEngine.getScreenWidth() / 9) * (7+1) - GameEngine.getEnemyWidth() / 2));
    public void resetImgEnemy(ImageView viewEnemy) {
        int generatePosition = availablePositions.get((int) (Math.random()*availablePositions.size()));
        viewEnemy.setX(generatePosition);
        availablePositions.remove(availablePositions.indexOf(generatePosition));
        System.out.println("___________________________");
        System.out.println(generatePosition);
        System.out.println(availablePositions);
        System.out.println("___________________________");

        if(availablePositions.isEmpty()){
            //availablePositions.addAll(Arrays.asList((int) (gameEngine.screenWidth / 9) * 2, (int) (gameEngine.screenWidth / 9) * 3, (int) (gameEngine.screenWidth / 9) * 4, (int) (gameEngine.screenWidth / 9) * 5, (int) (gameEngine.screenWidth / 9) * 6, (int) (gameEngine.screenWidth / 9) * 7, (int) (gameEngine.screenWidth / 9) * 8, (int) (gameEngine.screenWidth / 9 * 9)));
        }

        viewEnemy.setY(-60);
    }
}
