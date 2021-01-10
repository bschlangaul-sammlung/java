package org.bschlangaul.liste.einfach;

/**
 * Implementation einer einfach verkettenen Liste.
 */
public class Liste {
  private Element anfang;

  public void fügeElementEin(int wert) {
    Element neuesElement = new Element(wert);
    if (anfang == null) {
      anfang = neuesElement;
    } else {
      neuesElement.setzteNachfolger(anfang);
      anfang = neuesElement;
    }
  }

  public void fügeElementSortiertEin(int wert) {
    Element neuesElement = new Element(wert);
    if (anfang == null) {
      anfang = neuesElement;
    } else {
      Element aktuellesElement = anfang;
      // vor das 1. Element einfügen
      if (anfang.gibWert() > wert) {
        neuesElement.setzteNachfolger(anfang);
        anfang = neuesElement;
      } else {
        // Einfügestelle suchen
        while (aktuellesElement.gibNachfolger() != null && aktuellesElement.gibNachfolger().gibWert() < wert) {
          aktuellesElement = aktuellesElement.gibNachfolger();
        }
        // als letztes einfügen
        if (aktuellesElement.gibNachfolger() == null) {
          aktuellesElement.setzteNachfolger(neuesElement);
        }
        // Zwischendrin einfügen
        else {
          neuesElement.setzteNachfolger(aktuellesElement.gibNachfolger());
          aktuellesElement.setzteNachfolger(neuesElement);
        }
      }
    }
  }

  public void fügeElementSortiertEinRekursiv(int w) {
    Element neuesElement = new Element(w);
    if (anfang == null) {
      anfang = neuesElement;
    } else {
      if (anfang.gibWert() > w) {
        neuesElement.setzteNachfolger(anfang);
        anfang = neuesElement;
      } else {
        anfang.fügeSortiertEinRekursiv(w);
      }
    }
  }

  public void entfernenElement(int wert) {
    if (anfang == null) {
      return;
    } else {
      if (anfang.gibWert() == wert) {
        anfang = anfang.gibNachfolger();
      } else {
        Element aktuellesElement = anfang;
        // Löschstelle suchen
        while (aktuellesElement.gibNachfolger() != null && aktuellesElement.gibNachfolger().gibWert() != wert) {
          aktuellesElement = aktuellesElement.gibNachfolger();
        }
        // nicht enthalten
        if (aktuellesElement.gibNachfolger() == null) {
          return;
        }
        // Löschen
        else {
          if (aktuellesElement.gibNachfolger().gibNachfolger() == null) {
            aktuellesElement.setzteNachfolger(null);
          } else {
            aktuellesElement.setzteNachfolger(aktuellesElement.gibNachfolger().gibNachfolger());
          }
        }
      }
    }
  }

  public int gibLänge() {
    if (anfang == null) {
      return 0;
    } else {
      return anfang.gibRestlänge();
    }
  }
}
