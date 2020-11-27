package org.bschlangaul.aufgaben.oomup.pu_2.apluturtle;

import ch.aplu.turtle.*; // Bibliothek fuer Turtle
import java.awt.Color; // Bibliothek fuer Farben

public class Schildkroete {
  Turtle joe;

  public Schildkroete() {
    joe = new Turtle();
    joe.setColor(Color.green);
    joe.setPenColor(Color.blue);
  }

  // Aufgabe 1
  public void quadrat(int seitenlaenge) {
    for (int i = 0; i < 4; i = i + 1) {
      joe.forward(seitenlaenge);
      joe.right(90);
    }
  }

  // Aufgabe 2
  public void blume(int seitenlaenge) {
    for (int i = 0; i < 18; i = i + 1) {
      quadrat(seitenlaenge);
      joe.right(360 / 18);
    }
  }

  // Aufgabe 3 Test
  public void sechseck() {
    for (int i = 0; i < 6; i = i + 1) {
      joe.forward(100);
      joe.right(60);
      i = i + 1;
    }
  }

  // Aufgabe 4
  public void bergkette() {
    joe.setPos(-190, 0);
    int seitenlaenge = 10;
    joe.fillToHorizontal(0);
    while (joe.isInPlayground()) {
      berg(seitenlaenge);
      seitenlaenge = seitenlaenge + 10;
    }
  }

  // vorgegebene Methode für Aufgabe 4:
  public void berg(int seitenlaenge) {
    joe.right(45);
    joe.forward(seitenlaenge);
    joe.right(90);
    joe.forward(seitenlaenge);
    joe.left(135);
  }

  // vorgegebene Methode fuer Aufgabe 5:
  /*
   * Methode zum Setzen der Farbe mit einem int Wert dabei wird der Parameter
   * farbe durch 7 geteilt (Anzahl der Farben) je nach Rest wird dann die farbe
   * gesetzt 0 = gelb auch bei 7, 14, 21, 28,... 1 = orange auch bei 8, 15, 22,
   * 29,... 2 = rot auch bei 9, 16, 23, 30,... 3 = pink auch bei 10, 17, 24,
   * 31,... 4 = blau auch bei 11, 18, 25, 32,... 5 = cyan auch bei 12, 19, 26,
   * 33,... 6 = grün auch bei 13, 20, 27, 34,...
   */

  public void farbeSetzen(int farbe) {
    Color c;
    int f = farbe % 7;
    switch (f) {
      case 0:
        c = Color.yellow;
        break;
      case 1:
        c = Color.orange;
        break;
      case 2:
        c = Color.red;
        break;
      case 3:
        c = Color.pink;
        break;
      case 4:
        c = Color.blue;
        break;
      case 5:
        c = Color.cyan;
        break;
      case 6:
        c = Color.green;
        break;
      default:
        c = Color.black;
    }
    joe.setPenColor(c);
  }
}
