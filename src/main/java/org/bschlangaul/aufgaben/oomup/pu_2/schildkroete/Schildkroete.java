package org.bschlangaul.aufgaben.oomup.pu_2.schildkroete;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Schildkroete extends Actor {
  String stiftfarbe;
  boolean stiftAbgesetzt;
  double xKoordinate;
  double yKoordinate;
  double richtung;

  public Schildkroete() {
    stiftfarbe = "blau";
    setRotation(270);
    richtung = 270;
    stiftAbgesetzt = true;
    Greenfoot.setSpeed(25);
  }

  /**
   * Stift absetzen.
   */
  public void setzeStiftAb() {
    stiftAbgesetzt = true;
  }

  /**
   * Stift aufnehmen.
   */
  public void nehmeStiftAuf() {
    stiftAbgesetzt = false;
  }

  /**
   * Drehen um grad Grad nach rechts.
   *
   * @param grad Grad.
   */
  public void dreheRechts(double grad) {
    richtung = richtung + grad;
    if (richtung > 360) {
      richtung = richtung % 360;
    }
    setRotation((int) richtung);
  }

  /**
   * Drehen um grad Grad nach links.
   *
   * @param grad Grad.
   */
  public void dreheLinks(double grad) {
    richtung = richtung - grad;
    if (richtung < 0) {
      richtung = richtung % 360;
    }
    setRotation((int) richtung);
  }

  public void laufe(double entfernung) {
    double richtungRad = Math.toRadians(richtung);// Richtung im Bogenmaß
    double xEntfernung = entfernung * Math.cos(richtungRad);
    double yEntfernung = entfernung * Math.sin(richtungRad);
    laufeZu(xKoordinate + xEntfernung, yKoordinate + yEntfernung);
  }

  public void laufeZu(double xNeu, double yNeu) {
    if (stiftAbgesetzt) {
      drawLine(xKoordinate, yKoordinate, xNeu, yNeu);
    }

    xKoordinate = xNeu;
    yKoordinate = yNeu;
    setLocation(xKoordinate, yKoordinate);
  }

  private void setLocation(double x, double y) {
    xKoordinate = x;
    yKoordinate = y;
    super.setLocation((int) Math.floor(x), (int) Math.floor(y));
  }

  private void drawLine(double x1, double y1, double x2, double y2) {
    GreenfootImage bild = getWorld().getBackground();
    bild.setColor(uebersetzeFarbe(stiftfarbe));
    bild.drawLine((int) Math.ceil(x1), (int) Math.ceil(y1), (int) Math.ceil(x2), (int) Math.ceil(y2));
  }

  public void addedToWorld(World world) {
    xKoordinate = getX();
    yKoordinate = getY();
  }

  public void warte(int anzahlZyklen) {
    Greenfoot.delay(anzahlZyklen);
  }

  /**
   * mögliche Farben: rot, schwarz, blau, gelb, gruen, orange, magenta
   *
   * @param farbe Der Name der Farbe: rot, schwarz, blau, gelb, gruen, orange, magenta.
   */
  public void setzeStiftfarbe(String farbe) {
    stiftfarbe = farbe;
  }

  /**
   * Farbe: String in Color übersetzen
   *
   * @param farbe Der Name der Farbe: rot, schwarz, blau, gelb, gruen, orange, magenta.
   *
   * @return Eine Java Color-Klasse.
   */
  private Color uebersetzeFarbe(String farbe) {
    if (farbe.equals("rot")) {
      return Color.RED;
    } else if (farbe.equals("schwarz")) {
      return Color.BLACK;
    } else if (farbe.equals("blau")) {
      return Color.BLUE;
    } else if (farbe.equals("gelb")) {
      return Color.YELLOW;
    } else if (farbe.equals("gruen")) {
      return Color.GREEN;
    } else if (farbe.equals("orange")) {
      return Color.ORANGE;
    } else if (farbe.equals("magenta")) {
      return Color.MAGENTA;
    } else {
      return Color.BLACK;
    }
  }

  public void versteckeSchildkroete() {
    this.getImage().clear();
  }

  public void zeigeSchildkroete() {
    this.setImage("turtle2.png");
  }

  public void pralleAb() {
    if (xKoordinate <= 0 || xKoordinate >= getWorld().getWidth()) {
      richtung = 180 - richtung;
    } else if (yKoordinate <= 0 || yKoordinate >= getWorld().getHeight()) {
      richtung = 360 - richtung;
    }
  }
}
