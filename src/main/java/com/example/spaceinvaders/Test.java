package com.example.spaceinvaders;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.animation.ScaleTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        Image initialImage = null;
        Image zoomImage = null;

        try {
            initialImage = new Image(new FileInputStream("src/main/resources/Images/MainMenu.png"));
            zoomImage = new Image(new FileInputStream("src/main/resources/Images/Popekjabba.png"));
        } catch (FileNotFoundException ignored) {
        }


        ImageView imageView = new ImageView(initialImage);
        StackPane root = new StackPane(imageView);

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        // Wait for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create a TranslateTransition to move the image to the desired position
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), imageView);
        translateTransition.setToX(200);
        translateTransition.setToY(200);

        // Create a ScaleTransition to zoom the image
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), imageView);
        scaleTransition.setToX(4);
        scaleTransition.setToY(4);

        // Play both transitions together
        translateTransition.play();
        scaleTransition.play();

        // Set the new image
        imageView.setImage(zoomImage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}