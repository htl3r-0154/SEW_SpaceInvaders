package com.example.spaceinvaders;

import javafx.animation.FadeTransition;
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
        if(!GameEngine.resetrow.isEmpty()){
            int randomIndex = (int) (Math.random() * GameEngine.resetrow.size());
            int random = GameEngine.resetrow.get(randomIndex);
            GameEngine.resetrow.remove(randomIndex);
            viewEnemy.setX(((GameEngine.getScreenWidth() / 9) * (random + 1) - GameEngine.getEnemyWidth() / 2)+offset);
            viewEnemy.setY(0);
        }else{
            int randomIndex = (int) (Math.random() * GameEngine.backuprow.size());
            int random = GameEngine.backuprow.get(randomIndex);
            GameEngine.backuprow.remove(randomIndex);
            viewEnemy.setX(((GameEngine.getScreenWidth() / 9) * (random + 1) - GameEngine.getEnemyWidth() / 2)+offset);
            viewEnemy.setY(-100);
        }
        FadeTransition ft = new FadeTransition(Duration.millis(500), viewEnemy);

        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
}