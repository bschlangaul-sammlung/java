package org.bschlangaul.aufgaben.oomup.pu_2.turtle;

import greenfoot.*;

/**
 *
 */
public class Snake extends Animal {

  /**
   * Act - do whatever the Snake wants to do. This method is called whenever the
   * 'Act' or 'Run' button gets pressed in the environment.
   */
  public void act() {
    move(4);
    if (Greenfoot.getRandomNumber(100) < 10) {
      turn(Greenfoot.getRandomNumber(40) - 20);
    }
    if (atWorldEdge()) {
      turn(Greenfoot.getRandomNumber(180) - 90);
    }
    if (isTouching(Turtle.class)) {
      removeTouching(Turtle.class);
      Greenfoot.playSound("au.wav");
      this.getWorld().showText("VERLOREN! ", 300, 240);
      Greenfoot.stop();
    }
  }
}
