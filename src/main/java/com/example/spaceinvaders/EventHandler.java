package com.example.spaceinvaders;

import javafx.scene.input.KeyEvent;

public class EventHandler {
    SceneBuilder sceneBuilder;
    GameEngine gameEngine;
    Sound sound;
    public EventHandler(GameEngine gameEngine, Sound sound, SceneBuilder sceneBuilder) {
        this.gameEngine = gameEngine;
        this.sound = sound;
        this.sceneBuilder = sceneBuilder;
    }


    public void playButtonClick(){
        gameEngine.playButton.setVisible(false);
        gameEngine.quitButton.setVisible(false);

        sound.music1("src/main/resources/Sounds/Helldivers 2 Main Theme - A Cup Of Liber-Tea.mp3");

        if (gameEngine.is4k){
            sceneBuilder.setImgBackground(gameEngine.background, "src/main/resources/Images/GameBackground4k.png");
        } else {
            sceneBuilder.setImgBackground(gameEngine.background, "src/main/resources/Images/GameBackground!4k.png");
        }

        sceneBuilder.setImgSpaceship("src/main/resources/Images/Spaceship.png");

        gameEngine.viewBackground.setFitHeight(gameEngine.stage.getHeight());
        gameEngine.viewBackground.setFitWidth(gameEngine.stage.getWidth());
        gameEngine.viewBackground.setLayoutX(0);
        gameEngine.viewBackground.setLayoutY(0);
        gameEngine.viewSpaceship.setX(gameEngine.screenWidth / 2 - gameEngine.spaceship.getWidth() / 2);
        gameEngine.viewSpaceship.setY(gameEngine.stage.getHeight() - 180);

        sceneBuilder.setImgShot("src/main/resources/Images/Shot1.png");
        gameEngine.viewShot.setY(-100);
        gameEngine.viewShot.setX(0);

        gameEngine.scene.setOnKeyPressed(this::keyPressed);

        gameEngine.root.getChildren().addAll(gameEngine.viewBackground, gameEngine.viewSpaceship);
    }

    public void keyPressed(KeyEvent e){
        switch (e.getCode()) {
            case LEFT -> {
                if (!(gameEngine.viewSpaceship.getX() < (gameEngine.screenWidth * 0.02))) {
                    gameEngine.viewSpaceship.setX(gameEngine.viewSpaceship.getX() - gameEngine.spaceshipSpeed) ;
                }
            }
            case RIGHT -> {
                if (!(gameEngine.viewSpaceship.getX() + gameEngine.spaceshipWidth > gameEngine.screenWidth - (gameEngine.screenWidth * 0.02))) {
                    gameEngine.viewSpaceship.setX(gameEngine.viewSpaceship.getX() + gameEngine.spaceshipSpeed);
                }
            }
            case UP -> {
                if (gameEngine.viewShot.getY() + gameEngine.shotHeight < 0){
                    gameEngine.shoot();
                }
            }
        }
    }

}
