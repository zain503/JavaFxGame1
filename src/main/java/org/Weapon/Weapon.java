package org.Weapon;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.InterFace.IWeapon;

import java.util.Random;

public class Weapon implements IWeapon {
    public Circle gunshot;
    public double playerpositionx;
    public double playerpositiony;
    public double offsetx;
    public double offsety;
    public Weapon(){
    }
    public EventHandler<KeyEvent> keyReleased = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {
            System.out.println("fired Released");
            switch (event.getCode()) {
                case F:
                    break;
                case S:
                    break;
              }
        }

    };
    public AnimationTimer timer = new AnimationTimer() {

        public void handle1(long now) {

            // timerLabel.setText(Long.toString(elapsedMillis / 1000));
        }
        @Override
        public void handle(long now) {
            System.out.println("timer");
            playerpositionx += offsetx;
            playerpositiony+=offsety;
            gunshot.relocate(playerpositionx,playerpositiony);
        }

    };
    public EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {
            System.out.println("fired pressed");
            switch (event.getCode()) {
                case F:
                    break;
                case S:
                    break;




            }

        }
    };

    @Override
    public void ShotForword(Circle gunshot) {
        offsetx=gunshot.getLayoutX()+5;
        offsety=gunshot.getLayoutY();
    }

    @Override
    public void ShotBackword(Circle gunshot) {
        offsetx=gunshot.getLayoutX()+5;
        offsety=gunshot.getLayoutY();
    }
}
