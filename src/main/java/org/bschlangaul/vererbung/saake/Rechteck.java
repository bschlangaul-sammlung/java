package org.bschlangaul.vererbung.saake;

import java.io.OutputStream;
import java.io.InputStream;

/**
 * Saake Seite 289
 */
public class Rechteck extends GeomObjekt implements Speicherbar {
  public static final int BREITE = 100;
  public static final int HOEHE = 100;
  // Attributdeklarationen
  int x, y, b, h;
  int farbe;

  // Konstruktoren
  public Rechteck() {
    x = y = 0;
    b = h = 10;
  }

  public Rechteck(int xp, int yp, int br, int ho) {
    // Seite 296
    super(xp, yp);
    x = xp;
    y = yp;
    b = br;
    h = ho;
  }

  // Methode zum Berechnen der Fläche
  public int berechneFlaeche() {
    return b * h;
  }

  public void zeichnen() {

  }

  /**
   * Vergleiche Saake Seite 302
   *
   * @param out Instance der Klasse OutputStream
   */
  public void speichern(OutputStream out) {
  }

  /**
   * Vergleiche Saake Seite 302
   *
   * @param in Instanz der Klasse InputStream
   */
  public void laden(InputStream in) {
  }


  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Rechteck r1 = new Rechteck();
    r1.verschieben(50, 30);

    Rechteck r2 = new Rechteck(10, 10, 100, 20);
    int xpos = r1.x;
    r1.y = 30;
    // Seite 292
    Rechteck r3 = new Rechteck(10, 10, Rechteck.BREITE, Rechteck.HOEHE);
  }
}
