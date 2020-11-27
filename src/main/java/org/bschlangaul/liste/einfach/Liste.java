package org.bschlangaul.liste.einfach;

public class Liste {
  private Element anfang;
  // private Element ende;

  public void einfuegenElement(int w) {
    Element neuesElement = new Element(w);
    if (anfang == null) {
      anfang = neuesElement;
    } else {
      neuesElement.nachfolgerSetzen(anfang);
      anfang = neuesElement;
    }
  }

  public void sortiertEinfuegenElement(int w) {
    Element neuesElement = new Element(w);
    if (anfang == null) {
      anfang = neuesElement;
    } else {
      Element aktuellesElement = anfang;
      // vor das 1. Element einf�gen
      if (anfang.wertGeben() > w) {
        neuesElement.nachfolgerSetzen(anfang);
        anfang = neuesElement;
      } else {
        // Einfügestelle suchen
        while (aktuellesElement.nachfolgerGeben() != null && aktuellesElement.nachfolgerGeben().wertGeben() < w) {
          aktuellesElement = aktuellesElement.nachfolgerGeben();
        }
        // als letztes einfügen
        if (aktuellesElement.nachfolgerGeben() == null) {
          aktuellesElement.nachfolgerSetzen(neuesElement);
        }
        // Zwischendrin einfügen
        else {
          neuesElement.nachfolgerSetzen(aktuellesElement.nachfolgerGeben());
          aktuellesElement.nachfolgerSetzen(neuesElement);
        }
      }
    }
  }

  public void entfernenElement(int w) {
    if (anfang == null) {
      return;
    } else {
      if (anfang.wertGeben() == w) {
        anfang = anfang.nachfolgerGeben();
      } else {
        Element aktuellesElement = anfang;
        // Löschstelle suchen
        while (aktuellesElement.nachfolgerGeben() != null && aktuellesElement.nachfolgerGeben().wertGeben() != w) {
          aktuellesElement = aktuellesElement.nachfolgerGeben();
        }
        // nicht enthalten
        if (aktuellesElement.nachfolgerGeben() == null) {
          return;
        }
        // Löschen
        else {
          if (aktuellesElement.nachfolgerGeben().nachfolgerGeben() == null) {
            aktuellesElement.nachfolgerSetzen(null);
          } else {
            aktuellesElement.nachfolgerSetzen(aktuellesElement.nachfolgerGeben().nachfolgerGeben());
          }
        }
      }
    }
  }

  public void sortiertEinfuegenElementrek(int w) {
    Element neuesElement = new Element(w);
    if (anfang == null) {
      anfang = neuesElement;
    } else {
      if (anfang.wertGeben() > w) {
        neuesElement.nachfolgerSetzen(anfang);
        anfang = neuesElement;
      } else {
        anfang.sortiertEinfuegenRek(w);
      }
    }
  }

  public int laengeGeben() {
    if (anfang == null) {
      return 0;
    } else {
      return anfang.restlaengeGeben();
    }
  }
}
