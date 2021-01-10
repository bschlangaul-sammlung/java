package org.bschlangaul.liste.einfach;

/**
 * Das Datenelement bzw. der Datenknoten der einfachen Listen.
 */
public class Element {
  private Element nachfolger;
  private int wert;

  public Element(int wert) {
    this.wert = wert;
  }

  public Element gibNachfolger() {
    return nachfolger;
  }

  public int gibWert() {
    return wert;
  }

  public void setzteNachfolger(Element neuerNachfolger) {
    nachfolger = neuerNachfolger;
  }

  public void setzeWert(int wert) {
    this.wert = wert;
  }

  public void fügeSortiertEinRekursiv(int wert) {
    if (nachfolger == null) {
      nachfolger = new Element(wert);
    } else {
      if (nachfolger.gibWert() > wert) {
        Element neuesElement = new Element(wert);
        neuesElement.nachfolger = nachfolger;
        nachfolger = neuesElement;
      } else {
        nachfolger.fügeSortiertEinRekursiv(wert);
      }
    }
  }

  public int gibRestlänge() {
    if (nachfolger == null) {
      return 1;
    } else {
      return nachfolger.gibRestlänge() + 1;
    }
  }
}
