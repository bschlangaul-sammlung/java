package org.bschlangaul.aufgaben.oomup.ab_3.supermarkt;

import greenfoot.World;
import greenfoot.Greenfoot;

public class MyWorld extends World {


  public MyWorld() {
    super(600, 566, 1);
    prepare();
  }

  public void act() {
    lebensmittelErzeugen();
  }

  /**
   * Bereite die Welt für den Programmstart vor. Das heisst : Erzeuge die
   * Anfangs - Objekte und fuege sie der Welt hinzu .
   */
  private void prepare() {
    Einkaufswagen einkaufswagen = new Einkaufswagen();
    addObject(einkaufswagen, 163, 469);
  }

  private void lebensmittelErzeugen() {
    if (Greenfoot.getRandomNumber(150) < 1) {
      int random = Greenfoot.getRandomNumber(580);
      if (random > 0) {
        int zufall = Greenfoot.getRandomNumber(6);
        if (zufall == 0) {
          JunkFood hamburger = new Hamburger();
          addObject(hamburger, random, 0);
        } else if (zufall == 1) {
          JunkFood muffin = new Muffin();
          addObject(muffin, random, 0);
        } else if (zufall == 2) {
          JunkFood pommes = new Pommes();
          addObject(pommes, random, 0);
        } else if (zufall == 3) {
          Obst birne = new Birne();
          addObject(birne, random, 0);
        } else if (zufall == 4) {
          Obst apfel = new Apfel();
          addObject(apfel, random, 0);
        } else if (zufall == 5) {
          Obst banane = new Banane();
          addObject(banane, random, 0);
        }
      }
    }
  }
}
