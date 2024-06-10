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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;

//TODO add FileReader for Highscore
//TODO Display score & highscore
//TODO if score highscore then save to /resources/Gamedata/Highscore
//TODO add Bunkers with Hitbox to protect Spaceship

public class GameEngine extends Application {
    public int score = 0;
    public SceneBuilder sceneBuilder;
    public Sound sound;
    public EventHandler eventHandler;
    public Enemy enemy;

    public final double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public Button playButton = new Button("PLAY");
    public Button quitButton = new Button("QUIT");
    public Image menuImg = null;
    public Image backgroundImg = null;
    public Shot shot;
    public BigShot bigshot;
    public Image shotImg = null;
    public Image bigshotImg = null;
    public final double shotWidth = 10;
    public final double shotHeight = 110;
    public final double bigshotWidth = 90;
    public final double bigshotHeight = 110;
    public int shotSpeed;
    public int bigshotSpeed;
    public Image spaceshipImg = null;
    public final double spaceshipWidth = 120;
    public final double spaceshipHeight = 120;
    public final double spaceshipSpeed = 20;
    public final static double enemyWidth = 60;
    public final double enemyHeight = 60;
    public ImageView viewMenu;
    public ImageView viewBackground;
    public ImageView viewSpaceship;
    public ImageView viewShot;
    public ImageView viewbigShot;
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public Stage stage;
    public Group root = new Group();
    public Scene scene = new Scene(root);
    public MediaPlayer mediaPlayer2;
    public MediaPlayer mediaPlayer1;
    public MediaPlayer shotSFX;
    public MediaPlayer explosionSFX1;
    public MediaPlayer explosionSFX2;
    public MediaPlayer explosionSFX3;
    public final boolean is4k = screenHeight >= 1430.0;
    public boolean first = true;
    public int highscore = HighScoreReader.getHighscore();
    public Text highscoreText = new Text("Highscore: " + highscore);
    public int enemiesLeft = 24;
    public Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> updateEnemies()));
    public int moveCounter = 5;
    public boolean movementLeft = true;
    public boolean dead = false;


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
        //Create Score
        sceneBuilder.setScore();

        //Create PlayButton
        sceneBuilder.setPlayButton();

        //Create ExitButton
        sceneBuilder.setExitButton();

        //Create Menu picture
        sceneBuilder.setMenu();

        //Add to root
        root.getChildren().addAll(viewMenu, playButton, quitButton, highscoreText);

        //Add music
        sound.music2("src/main/resources/Sounds/Harvest Dawn.mp3");

        //show window
        this.stage.show();
    }
    public static int getScreenWidth(){
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    public static int getEnemyWidth(){
        return (int) enemyWidth;
    }

    public void initShot() {
        this.shot = new Shot(this);
    }
    public void initbigShot() {
        this.bigshot = new BigShot(this);
    }


    public void collisionCheck() {
        for (int i = 0; i < enemies.size(); i++) {
            collisionCheck(enemies.get(i).view, i);
        }
    }
    public void bigcollisionCheck() {
        for (int i = 0; i < enemies.size(); i++) {
            bigcollisionCheck(enemies.get(i).view, i);
        }
    }

    private void collisionCheck(ImageView viewEnemy, int index) {
        double rightX = viewShot.getX() + shotWidth;
        double leftX = viewShot.getX();
        double bottomY = viewShot.getY() + shotHeight;
        double topY = viewShot.getY();
        if (rightX >= viewEnemy.getX() && leftX <= viewEnemy.getX() + enemyWidth) {
            if (bottomY >= viewEnemy.getY() && topY <= viewEnemy.getY() + enemyHeight) {
                shot.resetImgShot();
                enemies.get(index).resetImgEnemy(viewEnemy);
                sound.explodeSound1("src/main/resources/Sounds/sinus-bomb.mp3");
                enemiesLeft--;
                checkEnemiesLeft();
                score += 10;
                System.out.println(score);
            }
        }
    }
    private void bigcollisionCheck(ImageView viewEnemy, int index) {
        double rightX = viewbigShot.getX() + bigshotWidth;
        double leftX = viewbigShot.getX();
        double bottomY = viewbigShot.getY() + bigshotHeight;
        double topY = viewbigShot.getY();
        if (rightX >= viewEnemy.getX() && leftX <= viewEnemy.getX() + enemyWidth) {
            if (bottomY >= viewEnemy.getY() && topY <= viewEnemy.getY() + enemyHeight) {
                bigshot.resetImgShot();
                enemies.get(index).resetImgEnemy(viewEnemy);
                sound.explodeSound1("src/main/resources/Sounds/sinus-bomb.mp3");
                enemiesLeft--;
                checkEnemiesLeft();
                score += 10;
                System.out.println(score);
            }
        }
    }
    public void checkEnemiesLeft() {
        KeyFrame keyFrame = new KeyFrame(Duration.millis(750), e -> updateEnemies());
        timeline.play();
        timeline = new Timeline(keyFrame);
    }

    private void updateEnemies() {//?
        moveCounter++;

        if (moveCounter != 11) {
            if (movementLeft) {
                for (Enemy value : enemies) {
                    value.view.setX(value.view.getX() - 30);
                }
            } else {
                for (Enemy value : enemies) {
                    value.view.setX(value.view.getX() + 30);
                }
            }
        } else {//go vertical
            for (Enemy value : enemies) {
                value.view.setY(value.view.getY() + (screenWidth - spaceshipHeight) / 20);
            }
        }
        if (moveCounter == 11) {
            moveCounter = 0;
            movementLeft = !movementLeft;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}