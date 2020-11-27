package org.bschlangaul.aufgaben.aud.pu_5.aufgabe_1;

public class Binaerbaum {
  public Knoten wurzel;

  public void elementEinfuegen(int wert) {
    if (wurzel == null) {
      wurzel = new Knoten(wert);
    } else {
      if (wert <= wurzel.getWert()) {
        if (wurzel.getLinks() != null) {
          wurzel.getLinks().elementEinfuegen(wert);
        } else {
          wurzel.setLinks(new Knoten(wert));
        }
      } else {
        if (wurzel.getRechts() != null) {
          wurzel.getRechts().elementEinfuegen(wert);
        } else {
          wurzel.setRechts(new Knoten(wert));
        }
      }
    }
  }

}
