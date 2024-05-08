package com.example.spaceinvaders;

public class Enemy {
    public GameEngine gameEngine;
    public double posX;
    public double posY;

    public Enemy(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        posX = this.gameEngine.viewEnemies.getX();
        posY = this.gameEngine.viewEnemies.getY();
    }
}
