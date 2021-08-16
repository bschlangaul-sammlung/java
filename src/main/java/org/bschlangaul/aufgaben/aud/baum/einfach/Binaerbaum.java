package org.bschlangaul.aufgaben.aud.baum.einfach;

public class Binaerbaum {
  public Knoten wurzel;

  public void fügeEin(int wert) {
    if (wurzel == null) {
      wurzel = new Knoten(wert);
    } else {
      if (wert <= wurzel.gibWert()) {
        if (wurzel.gibLinks() != null) {
          wurzel.gibLinks().fügeEin(wert);
        } else {
          wurzel.setzeLinks(new Knoten(wert));
        }
      } else {
        if (wurzel.gibRechts() != null) {
          wurzel.gibRechts().fügeEin(wert);
        } else {
          wurzel.setzeRechts(new Knoten(wert));
        }
      }
    }
  }

}
