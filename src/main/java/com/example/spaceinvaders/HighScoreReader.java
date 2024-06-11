package com.example.spaceinvaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HighScoreReader {
    public static int highscore;
        static public int getHighscore(){
            try (
                    BufferedReader in = Files.newBufferedReader(
                            Paths.get("src/main/resources/Gamedata/Highscore.txt"),
                            StandardCharsets.UTF_8
                    )
            ) {
                String line;
                while ((line = in.readLine()) != null) {
                    highscore = Integer.parseInt(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return highscore;
        }
    }
