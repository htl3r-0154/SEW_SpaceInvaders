package com.example.spaceinvaders;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EventHandler {
    SceneBuilder sceneBuilder;
    GameEngine gameEngine;
    Sound sound;

    public EventHandler(GameEngine gameEngine, Sound sound, SceneBuilder sceneBuilder) {
        this.gameEngine = gameEngine;
        this.sound = sound;
        this.sceneBuilder = sceneBuilder;
    }


    /**
     * Method gets called after the play button has been clicked
     * - starts music
     * - changes background
     * - summons spaceship
     * - summons enemies
     */
    public void playButtonClick() {
        gameEngine.stage.setTitle("Space Invaders - In Game");
        gameEngine.playButton.setVisible(false);
        gameEngine.quitButton.setVisible(false);

        sound.music1("src/main/resources/Sounds/Helldivers 2 Main Theme - A Cup Of Liber-Tea.mp3");

        if (gameEngine.is4k) {
            sceneBuilder.setImgBackground(gameEngine.backgroundImg, "src/main/resources/Images/GameBackground4k.png");
        } else {
            sceneBuilder.setImgBackground(gameEngine.backgroundImg, "src/main/resources/Images/background.png");
        }

        sceneBuilder.setImgSpaceship("src/main/resources/Images/Spaceship.png");

        gameEngine.viewBackground.setFitHeight(gameEngine.stage.getHeight());
        gameEngine.viewBackground.setFitWidth(gameEngine.stage.getWidth());
        gameEngine.viewBackground.setLayoutX(0);
        gameEngine.viewBackground.setLayoutY(0);
        gameEngine.viewSpaceship.setX(gameEngine.screenWidth / 2 - gameEngine.spaceshipWidth / 2);
        gameEngine.viewSpaceship.setY(gameEngine.screenHeight - gameEngine.spaceshipHeight - gameEngine.spaceBetweenSpaceshipAndScreenEnd);
        gameEngine.initShot();
        gameEngine.initbigShot();
        sceneBuilder.setImgShot("src/main/resources/Images/shot_gif-small.gif");
        //sceneBuilder.setImgbigShot("src/main/resources/Images/shot_gif.gif");

        gameEngine.scene.setOnKeyPressed(this::keyPressed);
        gameEngine.scene.setOnMouseClicked(this::mouseClicked);
        gameEngine.scene.setOnMouseMoved(this::mouseMoved);
        gameEngine.scene.setOnMouseClicked(this::mouseClicked);
        gameEngine.root.getChildren().addAll(gameEngine.viewBackground, gameEngine.viewSpaceship);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                Enemy enemy = new Enemy(gameEngine, i, j);
                gameEngine.enemies.add(enemy);
                gameEngine.root.getChildren().add(enemy.view);
                enemy.view.toFront();
            }
        }

        gameEngine.timeline.setCycleCount(Timeline.INDEFINITE);
        gameEngine.timeline.play();
    }

    private void mouseClicked(MouseEvent e) {
        if (!gameEngine.dead) {
            if (e.getButton() == MouseButton.SECONDARY) {
                if (gameEngine.viewbigShot.getY() < 0) {
                    gameEngine.bigshot.timeline.stop();
                    gameEngine.bigshot.bigshoot();
                }
            }
            if (e.getButton() == MouseButton.PRIMARY) {
                if (gameEngine.viewShot.getY() < 0) {
                    gameEngine.shot.timeline.stop();
                    gameEngine.shot.shoot();
                }
            }
        }
    }


    public void mainMenuButtonClick() {
        gameEngine.scoreText.setVisible(false);
        gameEngine.root.getChildren().clear();
        gameEngine.enemies.clear();
        gameEngine.root.getChildren().clear();
        gameEngine.stage.hide();
        gameEngine.root = new Group();
        gameEngine.scene = new Scene(gameEngine.root);
        gameEngine.stage = new Stage();
        gameEngine.playButton = new Button("PLAY");
        gameEngine.quitButton = new Button("QUIT");
        gameEngine.mainMenuButton = new Button("MAIN MENU");
        gameEngine.start(gameEngine.stage);
        gameEngine.moveCounter = 5;
        gameEngine.movementLeft = true;
        gameEngine.first = true;
        gameEngine.shot.timeline.stop();
        gameEngine.score = 0;
        gameEngine.dead = false;
        gameEngine.highScoreReaderAndWriter.getHighscore();
    }

    public void mouseMoved(MouseEvent e) {
        if (!gameEngine.dead) {
            gameEngine.viewSpaceship.setX(e.getSceneX() - gameEngine.spaceshipWidth / 2 + 10);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (!gameEngine.dead) {
            switch (e.getCode()) {
                case LEFT, A -> {
                    if (!(gameEngine.viewSpaceship.getX() < (gameEngine.screenWidth * 0.02))) {
                        gameEngine.viewSpaceship.setX(gameEngine.viewSpaceship.getX() - gameEngine.spaceshipSpeed);
                    }
                }
                case RIGHT, D -> {
                    if (!(gameEngine.viewSpaceship.getX() + gameEngine.spaceshipWidth > gameEngine.screenWidth - (gameEngine.screenWidth * 0.02))) {
                        gameEngine.viewSpaceship.setX(gameEngine.viewSpaceship.getX() + gameEngine.spaceshipSpeed);
                    }
                }
                case UP, W, SPACE -> {
                    if (gameEngine.viewShot.getY() < 0) {
                        gameEngine.shot.timeline.stop();
                        gameEngine.shot.shoot();
                    }
                }
                case Q, S, DOWN -> {
                    if (gameEngine.viewbigShot.getY() < 0) {
                        gameEngine.bigshot.timeline.stop();
                        gameEngine.bigshot.bigshoot();
                    }
                }
            }
        }
    }

    public void endGame() {
        gameEngine.dead = true;
        gameEngine.mediaPlayer1.stop();
        gameEngine.timeline.stop();
        gameEngine.sceneBuilder.setMainMenuButton();
        if (gameEngine.checkHighscore()) {
            sceneBuilder.setNewScore();
            gameEngine.root.getChildren().add(gameEngine.scoreText);
            sound.newHighscoreSound("src/main/resources/Sounds/newHighscore.mp3");
            gameEngine.highScoreReaderAndWriter.writeHighScore(gameEngine.score);
        } else {
            sceneBuilder.setScore();
            gameEngine.root.getChildren().add(gameEngine.scoreText);
        }
        gameEngine.root.getChildren().addAll(gameEngine.mainMenuButton);
        gameEngine.mainMenuButton.toFront();


    }
}
