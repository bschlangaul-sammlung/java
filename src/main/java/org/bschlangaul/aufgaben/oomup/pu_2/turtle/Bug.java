package org.bschlangaul.aufgaben.oomup.pu_2.turtle;

import greenfoot.*;

public class Bug extends Animal {


  public void act() {
    move(4);
    if (Greenfoot.getRandomNumber(100) < 10) {
      turn(Greenfoot.getRandomNumber(40) - 20);
    }
    if (atWorldEdge()) {
      turn(Greenfoot.getRandomNumber(180) - 90);
    }
  }
}
