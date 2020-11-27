package org.bschlangaul.aufgaben.oomup.pu_2.turtle;

import greenfoot.*;

/**
 *
 */
public class Turtle extends Animal {
  private int points = 0;

  /**
   *
   */
  public void act() {
    this.getWorld().showText("Punkte: " + points, 75, 25);
    move(4);
    if (Greenfoot.isKeyDown("right")) {
      turn(5);
    }
    if (Greenfoot.isKeyDown("left")) {
      turn(-5);
    }
    if (isTouching(Salat.class)) {
      removeTouching(Salat.class);
      Greenfoot.playSound("slurp.wav");
      points = points + 1;
      this.getWorld().showText("Punkte: " + points, 75, 25);
    }
    if (isTouching(Bug.class)) {
      removeTouching(Bug.class);
      Greenfoot.playSound("slurp.wav");
      points = points + 5;
      this.getWorld().showText("Punkte: " + points, 75, 25);
    }
    if (points >= 15) {
      Greenfoot.playSound("applause.wav");
      this.getWorld().showText("GEWONNEN!", 300, 240);
      Greenfoot.stop();
    }
  }
}
