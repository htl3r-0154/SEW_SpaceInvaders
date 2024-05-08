package com.example.spaceinvaders;

public class Enemy {
    public GameEngine gameEngine;
    public double xPos;
    public double yPos;

    public Enemy(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        xPos = this.gameEngine.viewEnemies.getX();
        yPos = this.gameEngine.viewEnemies.getY();
    }
}
