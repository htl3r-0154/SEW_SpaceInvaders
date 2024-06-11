package com.example.spaceinvaders;

import javafx.animation.FadeTransition;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.beans.binding.Bindings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class SceneBuilder {
    public GameEngine gameEngine;

    public SceneBuilder(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void setImgMenu(Image image, String src) {
        try {
            image = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored) {
        }
        gameEngine.viewMenu = new ImageView(image);
        gameEngine.viewMenu.toFront();

        FadeTransition fade = new FadeTransition();
        fade.setNode(gameEngine.viewMenu);
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void setImgBackground(Image image, String src) {
        try {
            image = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored) {
        }
        gameEngine.viewBackground = new ImageView(image);
        gameEngine.viewBackground.toFront();
    }

    public void setImgSpaceship(String src) {
        try {
            gameEngine.spaceshipImg = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored) {
        }

        gameEngine.viewSpaceship = new ImageView(gameEngine.spaceshipImg);

        FadeTransition fade = new FadeTransition();
        fade.setNode(gameEngine.viewSpaceship);
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void setImgShot(String src) {
        try {
            gameEngine.shotImg = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored) {
        }

        gameEngine.viewShot = new ImageView(gameEngine.shotImg);
        gameEngine.shot.resetImgShot();
        gameEngine.viewShot.toFront();
    }

    public void setVidIntro(String src) {
        Media media1 = new Media(Paths.get(src).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media1);
        gameEngine.intro = new MediaView(mediaPlayer);
        gameEngine.root.getChildren().add(gameEngine.intro);

        DoubleProperty width = gameEngine.intro.fitWidthProperty();
        DoubleProperty height = gameEngine.intro.fitHeightProperty();
        width.bind(Bindings.selectDouble(gameEngine.intro.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(gameEngine.intro.sceneProperty(), "height"));
        gameEngine.intro.setPreserveRatio(true);
        mediaPlayer.play();

        //show window
        gameEngine.stage.show();
        gameEngine.IntroNeeded = false;
        mediaPlayer.setOnEndOfMedia(() -> gameEngine.setup());
    }

    public void setPlayButton() {
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
        gameEngine.playButton.setOnAction(_ -> {
            gameEngine.eventHandler.playButtonClick();
            gameEngine.mediaPlayer2.stop();
        });

        FadeTransition fade = new FadeTransition();
        fade.setNode(gameEngine.playButton);
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void setExitButton() {
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
        gameEngine.quitButton.setOnAction(_ -> System.exit(187));

        FadeTransition fade = new FadeTransition();
        fade.setNode(gameEngine.quitButton);
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void setMainMenuButton(){
        gameEngine.mainMenuButton.setPrefSize(450, 75);
        gameEngine.mainMenuButton.setLayoutX(gameEngine.stage.getWidth() / 2 - gameEngine.quitButton.getPrefWidth() / 2);
        if (!gameEngine.is4k) {
            gameEngine.mainMenuButton.setLayoutY(gameEngine.stage.getHeight() / 2 + gameEngine.mainMenuButton.getPrefHeight() * 3 + 50);
        } else {
            gameEngine.mainMenuButton.setLayoutY(gameEngine.stage.getHeight() / 2 + gameEngine.mainMenuButton.getPrefHeight() * 5);
        }
        gameEngine.mainMenuButton.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(10))));
        gameEngine.mainMenuButton.setTextFill(Color.WHITE);
        gameEngine.mainMenuButton.setFont(new Font(25));
        gameEngine.mainMenuButton.setOnAction(_ -> gameEngine.eventHandler.mainMenuButtonClick());
    }

    public void setScore(){
        gameEngine.scoreText = new Text("Score: " + gameEngine.score);
        gameEngine.scoreText.setVisible(true);
        gameEngine.scoreText.setTextAlignment(TextAlignment.CENTER);
        gameEngine.scoreText.setX(gameEngine.screenWidth / 2 - 200);
        gameEngine.scoreText.setY(gameEngine.screenHeight / 2);
        gameEngine.scoreText.setFont(new Font(80));
        gameEngine.scoreText.setFill(Color.SILVER);
        gameEngine.scoreText.setStrokeWidth(4);
        gameEngine.scoreText.setStroke(Color.RED);
        gameEngine.scoreText.toFront();
    }

    public void setNewScore(){
        gameEngine.scoreText = new Text("New Highscore: " + gameEngine.score);
        gameEngine.scoreText.setVisible(true);
        gameEngine.scoreText.setTextAlignment(TextAlignment.CENTER);
        gameEngine.scoreText.setX(gameEngine.screenWidth / 2 - 300);
        gameEngine.scoreText.setY(gameEngine.screenHeight / 2);
        gameEngine.scoreText.setFont(new Font(80));
        gameEngine.scoreText.setFill(Color.RED);
        gameEngine.scoreText.setStrokeWidth(4);
        gameEngine.scoreText.setStroke(Color.GOLD);
        gameEngine.scoreText.toFront();
    }

    public void setMenu() {
        gameEngine.sceneBuilder.setImgMenu(gameEngine.menuImg, "src/main/resources/Images/MainMenu.png");
        gameEngine.viewMenu.setFitHeight(gameEngine.stage.getHeight());
        gameEngine.viewMenu.setFitWidth(gameEngine.stage.getWidth());
        gameEngine.viewMenu.setLayoutX(0);
        gameEngine.viewMenu.setLayoutY(0);
    }

    public void setHighscore() {
        gameEngine.highscoreText.setTextAlignment(TextAlignment.RIGHT);
        gameEngine.highscoreText.setX(gameEngine.screenWidth - 600);
        gameEngine.highscoreText.setY(100);
        gameEngine.highscoreText.setFont(new Font(80));
        gameEngine.highscoreText.setFill(Color.WHITE);
        gameEngine.highscoreText.setStrokeWidth(4);
        gameEngine.highscoreText.setStroke(Color.RED);
    }
}

