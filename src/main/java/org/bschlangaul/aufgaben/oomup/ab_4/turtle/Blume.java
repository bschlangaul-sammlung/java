package org.bschlangaul.aufgaben.oomup.ab_4.turtle;

import ch.aplu.turtle.*;

public class Blume {
  Turtle turtle;

  public Blume() {
    turtle = new Turtle();
    turtle.speed(-1);
  }

  private void sechseckZeichnen() {
    for (int i = 0; i < 6; i++) {
      turtle.forward(50);
      turtle.right(60);
    }
  }

  public void blumeZeichnen() {
    int anzahl = 20;
    double drehung = 360 / anzahl;
    turtle.speed(1000);
    for (int i = 0; i < anzahl; i++) {
      sechseckZeichnen();
      turtle.right(drehung);
    }
  }

  public static void main(String[] args) {
    Blume blume = new Blume();
    blume.blumeZeichnen();
  }
}
