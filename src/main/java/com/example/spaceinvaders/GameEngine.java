package com.example.spaceinvaders;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.*;

//TODO add FileReader for Highscore
//TODO Display Highscore
//TODO add Enemy's to hit with Hitbox
//TODO add Bunkers with Hitbox to protect Spaceship

public class GameEngine extends Application {
    public SceneBuilder sceneBuilder;
    public Sound sound;
    public EventHandler eventHandler;
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
        this.sceneBuilder = new SceneBuilder(this);
        this.sound = new Sound(this);
        this.eventHandler = new EventHandler(this, sound, sceneBuilder);

        //Create PlayButton
        sceneBuilder.setPlayButton();

        //Create ExitButton
        sceneBuilder.setExitButton();

        //Create Menu picture
        sceneBuilder.setMenu();

        //Add to root
        root.getChildren().addAll(viewMenu);
        root.getChildren().addAll(playButton, quitButton);

        //Add music
        sound.music2("src/main/resources/Sounds/Harvest Dawn.mp3");

        //show window
        this.stage.show();
    }

    public void shoot(){
        double posX = viewSpaceship.getX() + (spaceshipWidth / 2) - (shotWidth / 2);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> updateShot()));
        timeline.setCycleCount(100);
        timeline.play();

        viewShot.setX(posX);
        viewShot.setY(screenHeight - 145);

        sound.shotSound("src/main/resources/Sounds/Laser Shot.mp3");

        if (first){
            root.getChildren().add(viewShot);
            first = false;
        }
    }

    public void updateShot() {
        viewShot.setY(viewShot.getY() - shotSpeed);
    }

    public static void main(String[] args) {
        launch();
    }
}