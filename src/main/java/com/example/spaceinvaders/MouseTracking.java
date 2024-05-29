package com.example.spaceinvaders;

public class MouseTracking extends GameEngine {
    public MouseTracking(){
        root.setOnMouseMoved(event -> {
            while (!dead) {
                System.out.println(event.getSceneX());
                System.out.println(event.getSceneY());
            }
        });
    }
}
