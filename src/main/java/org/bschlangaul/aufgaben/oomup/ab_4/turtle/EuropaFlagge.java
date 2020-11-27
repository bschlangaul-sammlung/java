package org.bschlangaul.aufgaben.oomup.ab_4.turtle;

import ch.aplu.turtle.*;
import java.awt.Color;

public class EuropaFlagge {
  Turtle turtle;

  public EuropaFlagge() {
    turtle = new Turtle();
    turtle.hideTurtle();
    turtle.penUp();
    turtle.clear(Color.blue);
  }

  private void zeichneStern() {
    int zacken = 5;
    int winkel = 60;
    int größe = 8;
    turtle.setPenColor(Color.yellow);
    turtle.penDown();
    int ausgleichsWinkel = 360 / zacken + winkel;
    turtle.fillToPoint(turtle.getX(), turtle.getY()) ;
    for (int i = 0; i < zacken; i++) {
      turtle.forward(größe);
      turtle.right(ausgleichsWinkel);
      turtle.forward(größe);
      turtle.left(winkel);
    }
    turtle.fillOff();
    turtle.penUp();
  }

  public void zeichne() {
    double anzahl = 27;
    double radius = 170;
    double rotation = 360 / anzahl;
    for (int i = 0; i < anzahl; i++) {
      turtle.forward(radius);
      zeichneStern();
      turtle.back(radius);
      turtle.right(rotation);
    }
  }

  public static void main(String[] args) {
    EuropaFlagge flagge = new EuropaFlagge();
    flagge.zeichne();
  }
}
