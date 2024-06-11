package com.example.spaceinvaders;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HighScoreReaderAndWriter {
    GameEngine gameEngine;

    public HighScoreReaderAndWriter(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void getHighscore() {
        try (
                BufferedReader in = Files.newBufferedReader(
                        Paths.get("src/main/resources/Gamedata/Highscore.txt"),
                        StandardCharsets.UTF_8
                )
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                gameEngine.highscore = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeHighScore(int score) {
        String str = String.valueOf(score);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Gamedata/Highscore.txt"));
            writer.write(str);
            writer.close();
        } catch (IOException ignored) {
        }
    }
}

