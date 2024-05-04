package com.example.spaceinvaders;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

//TODO add FileReader for Highscore
//TODO Display Highscore
//TODO add Enemy's to hit with Hitbox
//TODO add Bunkers with Hitbox to protect Spaceship

public class HelloApplication extends Application {
    public double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public Button playButton = new Button("PLAY");
    public Button quitButton = new Button("QUIT");
    public Image menu = null;
    public Image background = null;
    public Image shot = null;
    public double shotWidth = 32;
    public double shotHeight = 32;
    public double shotSpeed = 10;
    public Image spaceship = null;
    public double spaceshipWidth = 120;
    public double spaceshipHeight = 120;
    public double spaceshipSpeed = 20;
    public ImageView viewMenu;
    public ImageView viewBackground;
    public ImageView viewSpaceship;
    public ImageView viewShot;
    public Stage stage;
    public Group root = new Group();
    public Scene scene = new Scene(root);
    public MediaPlayer mediaPlayer2;
    public MediaPlayer mediaPlayer1;
    public MediaPlayer shotSoundSFX;
    public boolean is4k = screenHeight >= 1430.0;
    public boolean first = true;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Space Invaders - Main Menu");
        this.stage.setScene(scene);
        this.stage.setFullScreen(true);
        this.stage.setFullScreenExitHint("");
        this.stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.stage.setResizable(false);
        this.stage.setWidth(screenWidth);
        this.stage.setHeight(screenHeight);
        this.stage.getIcons().add(new Image("file:src/main/resources/Images/space-invaders.png"));

        //Create PlayButton
        setPlayButton();

        //Create ExitButton
        setExitButton();

        //Create Menu picture
        setMenu();

        //Add to root
        root.getChildren().addAll(viewMenu);
        root.getChildren().addAll(playButton, quitButton);

        //Add music
        music2("src/main/resources/Sounds/Harvest Dawn.mp3");

        //show window
        this.stage.show();
    }

    public void setImgMenu(Image image, String src){
        try {
            image = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored) {
        }
        viewMenu = new ImageView(image);
        viewMenu.toFront();
    }

    public void setImgBackground(Image image, String src){
        try {
            image = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored) {
        }
        viewBackground = new ImageView(image);
        viewBackground.toFront();
    }

    public void setImgSpaceship(String src){
        try {
            spaceship = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored){
        }

        viewSpaceship = new ImageView(spaceship);

        FadeTransition fade = new FadeTransition();
        fade.setNode(viewSpaceship);
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void setImgShot(String src){
        try {
            shot = new Image((new FileInputStream(src)));
        } catch (FileNotFoundException ignored) {
        }

        viewShot = new ImageView(shot);
        viewShot.toFront();
    }

    public void music1(String path){
        Media m = new Media(Paths.get(path).toUri().toString());
        mediaPlayer1 = new MediaPlayer(m);
        mediaPlayer1.setVolume(0);
        mediaPlayer1.play();
        mediaPlayer1.setAutoPlay(true);
        mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
    }
    public void music2(String path){
        Media m = new Media(Paths.get(path).toUri().toString());
        mediaPlayer2 = new MediaPlayer(m);
        mediaPlayer2.play();
        mediaPlayer2.setAutoPlay(true);
        mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void shoot(){
        double posX = viewSpaceship.getX() + (spaceshipWidth / 2) - (shotWidth / 2);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> updateShot()));
        timeline.setCycleCount(100);
        timeline.play();

        viewShot.setX(posX);
        viewShot.setY(screenHeight - 145);

        shotSound("src/main/resources/Sounds/Laser Shot.mp3");

        if (first){
            root.getChildren().add(viewShot);
            first = false;
        }
    }

    public void shotSound(String path){
        Media m = new Media(Paths.get(path).toUri().toString());
        shotSoundSFX = new MediaPlayer(m);
        shotSoundSFX.play();
    }

    public void updateShot() {
        viewShot.setY(viewShot.getY() - shotSpeed);
    }

    //TODO Make onPlayButtonClick a new class for better code
    public void PlayButtonClick(){
        playButton.setVisible(false);
        quitButton.setVisible(false);

        music1("src/main/resources/Sounds/Helldivers 2 Main Theme - A Cup Of Liber-Tea.mp3");

        if (is4k){
            setImgBackground(background, "src/main/resources/Images/GameBackground4k.png");
        } else {
            setImgBackground(background, "src/main/resources/Images/GameBackground!4k.png");
        }

        setImgSpaceship("src/main/resources/Images/Spaceship.png");

        viewBackground.setFitHeight(stage.getHeight());
        viewBackground.setFitWidth(stage.getWidth());
        viewBackground.setLayoutX(0);
        viewBackground.setLayoutY(0);
        viewSpaceship.setX(screenWidth / 2 - spaceship.getWidth() / 2);
        viewSpaceship.setY(stage.getHeight() - 180);

        setImgShot("src/main/resources/Images/Shot1.png");
        viewShot.setY(-100);
        viewShot.setX(0);

        //TODO make ButtonListener own method or class
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT -> {
                    if (!(viewSpaceship.getX() < (screenWidth * 0.02))) {
                        viewSpaceship.setX(viewSpaceship.getX() - spaceshipSpeed) ;
                    }
                }
                case RIGHT -> {
                    if (!(viewSpaceship.getX() + spaceshipWidth > screenWidth - (screenWidth * 0.02))) {
                        viewSpaceship.setX(viewSpaceship.getX() + spaceshipSpeed);
                    }
                }
                case UP -> {
                    if (viewShot.getY() + shotHeight < 0){
                        shoot();
                    }
                }
            }
        });

        root.getChildren().addAll(viewBackground, viewSpaceship);
    }

    public void setPlayButton(){
        playButton.setPrefSize(450, 75);
        playButton.setLayoutX(stage.getWidth() / 2 - playButton.getPrefWidth() / 2);
        if (!is4k) {
            playButton.setLayoutY(stage.getHeight() / 2 + playButton.getPrefHeight() * 2 + 50);
        } else {
            playButton.setLayoutY(stage.getHeight() / 2 + playButton.getPrefHeight() * 4);
        }
        playButton.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(10))));
        playButton.setTextFill(Color.WHITE);
        playButton.setFont(new Font(25));
        playButton.setOnAction(e -> {

            PlayButtonClick();

            //TODO Make new Sound/SFX class for background music and transitions
            mediaPlayer1.setVolume(20);
            mediaPlayer2.setVolume(80);
            mediaPlayer1.setVolume(40);
            mediaPlayer2.setVolume(60);
            mediaPlayer1.setVolume(60);
            mediaPlayer2.setVolume(40);
            mediaPlayer1.setVolume(80);
            mediaPlayer2.setVolume(20);
            mediaPlayer1.setVolume(100);
            mediaPlayer2.stop();

        });
    }

    public void setExitButton(){
        quitButton.setPrefSize(450, 75);
        quitButton.setLayoutX(stage.getWidth() / 2 - quitButton.getPrefWidth() / 2);
        if (!is4k) {
            quitButton.setLayoutY(stage.getHeight() / 2 + quitButton.getPrefHeight() * 3 + 50);
        } else {
            quitButton.setLayoutY(stage.getHeight() / 2 + quitButton.getPrefHeight() * 5);
        }
        quitButton.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(10))));
        quitButton.setTextFill(Color.WHITE);
        quitButton.setFont(new Font(25));
        quitButton.setOnAction(e -> System.exit(0));
    }

    public void setMenu() {
        setImgMenu(menu, "src/main/resources/Images/MainMenu.png");
        viewMenu.setFitHeight(stage.getHeight());
        viewMenu.setFitWidth(stage.getWidth());
        viewMenu.setLayoutX(0);
        viewMenu.setLayoutY(0);
    }

    public static void main(String[] args) {
        launch();
    }
}