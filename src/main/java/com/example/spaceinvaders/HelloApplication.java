package com.example.spaceinvaders;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public class HelloApplication extends Application {
    public Button playButton = new Button("PLAY");
    public Button quitButton = new Button("QUIT");
    public Image menu = null;
    public Image background = null;
    public ImageView viewMenu;
    public ImageView viewBackground;
    public Stage stage;
    public Group root = new Group();
    public Scene scene = new Scene(root);
    public MediaPlayer mediaPlayer2;
    public MediaPlayer mediaPlayer1;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.stage.setTitle("Space Invaders - Main Menu");
        this.stage.setScene(scene);
        this.stage.setFullScreen(true);
        this.stage.setFullScreenExitHint("");
        this.stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.stage.setResizable(false);
        this.stage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        this.stage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        this.stage.getIcons().add(new Image("file:src/main/resources/Images/space-invaders.png"));


        playButton.setPrefSize(450, 75);
        playButton.setLayoutX(stage.getWidth() / 2 - playButton.getPrefWidth() / 2);
        playButton.setLayoutY(stage.getHeight() / 2 + playButton.getPrefHeight() * 2 + 50);
        playButton.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(10))));
        playButton.setTextFill(Color.WHITE);
        playButton.setFont(new Font(25));
        playButton.setOnAction(event -> {
            playButton.setVisible(false);
            quitButton.setVisible(false);

            music1("src/main/resources/Sounds/Helldivers 2 Main Theme - A Cup Of Liber-Tea.mp3");

            onPlayButtonClick();

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

        quitButton.setPrefSize(450, 75);
        quitButton.setLayoutX(stage.getWidth() / 2 - quitButton.getPrefWidth() / 2);
        quitButton.setLayoutY(stage.getHeight() / 2 + quitButton.getPrefHeight() * 3 + 50);
        quitButton.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(10))));
        quitButton.setTextFill(Color.WHITE);
        quitButton.setFont(new Font(25));
        quitButton.setOnAction(event -> {
            System.exit(0);
        });


        setImg(menu, "src/main/resources/Images/MainMenu.png");
        viewMenu.setFitHeight(stage.getHeight());
        viewMenu.setFitWidth(stage.getWidth());
        viewMenu.setLayoutX(0);
        viewMenu.setLayoutY(0);


        root.getChildren().add(viewMenu);
        root.getChildren().addAll(playButton, quitButton);
        music2("src/main/resources/Sounds/Harvest Dawn.mp3");
        this.stage.show();

    }

    public void setImg(Image image, String src){
        try {
            image = new Image(new FileInputStream(src));
        } catch (FileNotFoundException ignored) {
        }
        viewMenu = new ImageView(image);
        viewMenu.toFront();
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

    public void onPlayButtonClick(){
        setImg(background, "src/main/resources/Images/Popekjabba.png");
        System.out.println("test");
    }
    public static void main(String[] args) {
        launch();
    }
}