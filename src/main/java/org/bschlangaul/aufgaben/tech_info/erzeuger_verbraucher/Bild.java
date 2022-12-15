package org.bschlangaul.aufgaben.tech_info.erzeuger_verbraucher;

import ea.edu.*;

public class Bild extends BildE {

  private static final long serialVersionUID = 5000921732042288798L;

  public static String gibPfad(String pfad) {
    return "./src/main/java/org/bschlangaul/aufgaben/tech_info/erzeuger_verbraucher/bilder/" + pfad;
  }

  /**
   * BILD Konstruktor
   *
   * @param x    x-Koordinate im Fenster (Pixel)
   * @param y    y-Koordinate im Fenster (Pixel)
   * @param name Name der Grafik-Datei (im Projekt-Ordner)
   */
  public Bild(int x, int y, String name) {
    super(x, y, name);
    this.setzeMittelpunkt(x, y);
  }

  /**
   * Methode verschiebenUm
   *
   * @param deltaX Pixel in x-Richtung (wird bei Bedarf auf ganze Pixel gerundet)
   * @param deltaY Pixel in y-Richtung (wird bei Bedarf auf ganze Pixel gerundet)
   */
  public void verschiebenUm(double deltaX, double deltaY) {
    super.bewegen((int) (Math.round(deltaX)), (int) (Math.round(deltaY)));
  }

  /**
   * Methode setzeMittelpunkt
   *
   * @param x x-Koordinate des Mittelpunkts (Pixel)
   * @param y y-Koordinate des Mittelpunkts (Pixel)
   */
  public void setzeMittelpunkt(int x, int y) {
    super.mittelpunktSetzen(x, y);
  }

  /**
   * Methode nenneMx
   *
   * @return x-Koordinate des Mittelpunkts (Pixel)
   */
  public int nenneMx() {
    return super.zentrum().x();
  }

  /**
   * Methode nenneMY
   *
   * @return y-Koordinate des Mittelpunkts (Pixel)
   */
  public int nenneMy() {
    return super.zentrum().y();
  }

}
