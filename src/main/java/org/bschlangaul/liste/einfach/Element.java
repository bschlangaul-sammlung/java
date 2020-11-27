package org.bschlangaul.liste.einfach;

// einfacheListe.zip
// Modul 2.2: Algorithmen und Datenstrukturen Digitale Präsenztage AUD_4 Digitaler Präsenztag
// https://www.studon.fau.de/file2975901_download.html
public class Element {
  private Element nachfolger;
  private int wert;

  public Element(int w) {
    wert = w;
  }

  public Element nachfolgerGeben() {
    return nachfolger;
  }

  public int wertGeben() {
    return wert;
  }

  public void nachfolgerSetzen(Element neuerNachfolger) {
    nachfolger = neuerNachfolger;
  }

  public void wertSetzen(int w) {
    wert = w;
  }

  public void sortiertEinfuegenRek(int w) {
    if (nachfolger == null) {
      nachfolger = new Element(w);
    } else {
      if (nachfolger.wertGeben() > w) {
        Element neuesElement = new Element(w);
        neuesElement.nachfolger = nachfolger;
        nachfolger = neuesElement;
      } else {
        nachfolger.sortiertEinfuegenRek(w);
      }
    }
  }

  public int restlaengeGeben() {
    if (nachfolger == null) {
      return 1;
    } else {
      return nachfolger.restlaengeGeben() + 1;
    }
  }
}
