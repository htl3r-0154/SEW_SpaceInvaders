package com.example.spaceinvaders;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.awt.*;

//TODO add FileReader for Highscore
//TODO Display Highscore
//TODO add Enemy's to hit with Hitbox
//TODO add Bunkers with Hitbox to protect Spaceship

public class GameEngine extends Application {
    public SceneBuilder sceneBuilder;
    public Sound sound;
    public EventHandler eventHandler;
    public Enemy enemy;
    public Shot shot;
    public final double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public Button playButton = new Button("PLAY");
    public Button quitButton = new Button("QUIT");
    public Image menuImg = null;
    public Image backgroundImg = null;
    public Image shotImg = null;
    public final double shotWidth = 8;
    public final double shotHeight = 24;
    public int shotSpeed;
    public Image spaceshipImg = null;
    public final double spaceshipWidth = 120;
    public final double spaceshipHeight = 120;
    public final double spaceshipSpeed = 20;
    public Image enemiesImg = null;
    public final double enemy3Width = 60;
    public final double enemy3Height = 60;
    public ImageView viewMenu;
    public ImageView viewBackground;
    public ImageView viewSpaceship;
    public ImageView viewShot;
    public ImageView viewEnemies;
    public Stage stage;
    public Group root = new Group();
    public Scene scene = new Scene(root);
    public MediaPlayer mediaPlayer2;
    public MediaPlayer mediaPlayer1;
    public MediaPlayer shotSoundSFX;
    public final boolean is4k = screenHeight >= 1430.0;
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

    public void initEnemy(){
        this.enemy = new Enemy(this);
    }

    public void initShot(){
        this.shot = new Shot(this);
    }


    public void collisionCheck() {
        for (int i = 0; i < shotWidth; i++) {
            for (int j = 0; j < enemy3Width; j++) {
                if (viewShot.getX() + i == viewEnemies.getX() + j){
                    for (int k = 0; k < enemy3Height; k++) {
                        for (int l = 0; l < shotHeight; l++) {
                            if (viewShot.getY() + l == viewEnemies.getY() + k){
                                sceneBuilder.resetImgEnemy();
                                sceneBuilder.resetImgShot();
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}