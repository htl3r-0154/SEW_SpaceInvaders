package com.example.spaceinvaders;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SceneBuilder {
    public GameEngine gameEngine;
    public SceneBuilder(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    public void setImgMenu(Image image, String src){
        try {
            image = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored) {
        }
        gameEngine.viewMenu = new ImageView(image);
        gameEngine.viewMenu.toFront();
    }

    public void setImgBackground(Image image, String src){
        try {
            image = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored) {
        }
        gameEngine.viewBackground = new ImageView(image);
        gameEngine.viewBackground.toFront();
    }

    public void setImgSpaceship(String src){
        try {
            gameEngine.spaceship = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored){
        }

        gameEngine.viewSpaceship = new ImageView(gameEngine.spaceship);

        FadeTransition fade = new FadeTransition();
        fade.setNode(gameEngine.viewSpaceship);
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void setImgShot(String src){
        try {
            gameEngine.shot = new Image((new FileInputStream(src)));
        } catch (FileNotFoundException ignored) {
        }

        gameEngine.viewShot = new ImageView(gameEngine.shot);
        gameEngine.viewShot.toFront();
    }

    public void setPlayButton(){
        gameEngine.playButton.setPrefSize(450, 75);
        gameEngine.playButton.setLayoutX(gameEngine.stage.getWidth() / 2 - gameEngine.playButton.getPrefWidth() / 2);
        if (!gameEngine.is4k) {
            gameEngine.playButton.setLayoutY(gameEngine.stage.getHeight() / 2 + gameEngine.playButton.getPrefHeight() * 2 + 50);
        } else {
            gameEngine.playButton.setLayoutY(gameEngine.stage.getHeight() / 2 + gameEngine.playButton.getPrefHeight() * 4);
        }
        gameEngine.playButton.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(10))));
        gameEngine.playButton.setTextFill(Color.WHITE);
        gameEngine.playButton.setFont(new Font(25));
        gameEngine.playButton.setOnAction(e -> {

            gameEngine.eventHandler.playButtonClick();

            //TODO Make new Sound/SFX class for background music and transitions
            gameEngine.mediaPlayer1.setVolume(20);
            gameEngine.mediaPlayer2.setVolume(80);
            gameEngine.mediaPlayer1.setVolume(40);
            gameEngine.mediaPlayer2.setVolume(60);
            gameEngine.mediaPlayer1.setVolume(60);
            gameEngine.mediaPlayer2.setVolume(40);
            gameEngine.mediaPlayer1.setVolume(80);
            gameEngine.mediaPlayer2.setVolume(20);
            gameEngine.mediaPlayer1.setVolume(100);
            gameEngine.mediaPlayer2.stop();

        });
    }

    public void setExitButton(){
        gameEngine.quitButton.setPrefSize(450, 75);
        gameEngine.quitButton.setLayoutX(gameEngine.stage.getWidth() / 2 - gameEngine.quitButton.getPrefWidth() / 2);
        if (!gameEngine.is4k) {
            gameEngine.quitButton.setLayoutY(gameEngine.stage.getHeight() / 2 + gameEngine.quitButton.getPrefHeight() * 3 + 50);
        } else {
            gameEngine.quitButton.setLayoutY(gameEngine.stage.getHeight() / 2 + gameEngine.quitButton.getPrefHeight() * 5);
        }
        gameEngine.quitButton.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(10))));
        gameEngine.quitButton.setTextFill(Color.WHITE);
        gameEngine.quitButton.setFont(new Font(25));
        gameEngine.quitButton.setOnAction(e -> System.exit(0));
    }

    public void setMenu() {
        gameEngine.sceneBuilder.setImgMenu(gameEngine.menu, "src/main/resources/Images/MainMenu.png");
        gameEngine.viewMenu.setFitHeight(gameEngine.stage.getHeight());
        gameEngine.viewMenu.setFitWidth(gameEngine.stage.getWidth());
        gameEngine.viewMenu.setLayoutX(0);
        gameEngine.viewMenu.setLayoutY(0);
    }
}

