package org.bschlangaul.aufgaben.oomup.ab_3.supermarkt;

import greenfoot.Actor;

public class Lebensmittel extends Actor {

  public void act() {
  }

  protected void fallen() {
    if (getY() < 565) {
      setLocation(this.getX(), this.getY() + 5);
    } else {
      getWorld().removeObject(this);
    }
  }
}
