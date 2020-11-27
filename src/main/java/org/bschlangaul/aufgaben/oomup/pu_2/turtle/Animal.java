package org.bschlangaul.aufgaben.oomup.pu_2.turtle;

import greenfoot.*;

public class Animal extends Actor {
  /**
   * Test if we are close to one of the edges of the world. Return true is we are.
   *
   * @return Wahr wenn sich das Tier am Rand befindet.
   */
  public boolean atWorldEdge() {
    if (getX() < 10 || getX() > getWorld().getWidth() - 10)
      return true;
    if (getY() < 10 || getY() > getWorld().getHeight() - 10)
      return true;
    else
      return false;
  }

  /**
   * Return true if we can see an object of class 'clss' right where we are. False
   * if there is no such object here.
   *
   * @param clss Ein Akteur.
   *
   * @return Wahr wenn der Akteur gesehen werden kann.
   */
  public boolean canSee(Class<Actor> clss) {
    Actor actor = getOneObjectAtOffset(0, 0, clss);
    return actor != null;
  }

  /**
   * Try to eat an object of class 'clss'. This is only successful if there is
   * such an object where we currently are. Otherwise this method does nothing.
   *
   * @param clss Ein Akteur.
   */
  public void eat(Class<Actor> clss) {
    Actor actor = getOneObjectAtOffset(0, 0, clss);
    if (actor != null) {
      getWorld().removeObject(actor);
    }
  }
}
