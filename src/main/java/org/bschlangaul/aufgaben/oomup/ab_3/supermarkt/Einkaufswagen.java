package org.bschlangaul.aufgaben.oomup.ab_3.supermarkt;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Einkaufswagen extends Actor {
  boolean right;
  private int punkte;

  public Einkaufswagen() {
    right = true;
    punkte = 0;
  }

  public void act() {
    bewegen();
    punkteAktualisieren();
    this.getWorld().showText(" Punkte : " + punkte, 500, 25);
  }

  private void bewegen() {
    if (Greenfoot.isKeyDown(" right ")) {
      if (!right) {
        getImage().mirrorHorizontally();
        right = true;
      }
      move(5);
    }
    if (Greenfoot.isKeyDown(" left ")) {
      if (right) {
        getImage().mirrorHorizontally();
        right = false;
      }
      move(-5);
    }
  }

  private void punkteAktualisieren() {
    if (isTouching(Obst.class)) {
      punkte = punkte + 1;
      removeTouching(Obst.class);
    }
    if (isTouching(JunkFood.class)) {
      punkte = punkte - 1;
      removeTouching(JunkFood.class);
      if (punkte < 0) {
        getWorld().showText(" GAME OVER ", 300, 300);
        Greenfoot.stop();
      }
    }
  }
}
