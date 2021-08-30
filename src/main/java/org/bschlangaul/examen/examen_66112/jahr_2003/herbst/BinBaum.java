package org.bschlangaul.examen.examen_66112.jahr_2003.herbst;

public class BinBaum {

  private Knoten wurzel = null;

  public void setzeWurzel(Knoten knoten) {
    wurzel = knoten;
  }

  public void fügeEin(int zahl) {
    Knoten aktueller = wurzel;
    Knoten neuerKnoten = new Knoten(zahl);
    if (wurzel == null) {
      wurzel = neuerKnoten;
      return;
    }
    while (aktueller != null) {
      // suche links
      if (zahl <= aktueller.gibZahl() && aktueller.gibLinks() != null) {
        aktueller = aktueller.gibLinks();
        // fuege ein
      } else if (zahl <= aktueller.gibZahl() && aktueller.gibLinks() == null) {
        aktueller.setzeLinks(neuerKnoten);
        break;
      }
      // suche rechts
      if (zahl > aktueller.gibZahl() && aktueller.gibRechts() != null) {
        aktueller = aktueller.gibRechts();
        // fuege ein
      } else if (zahl > aktueller.gibZahl() && aktueller.gibRechts() == null) {
        aktueller.setzeRechts(neuerKnoten);
        break;
      }
    }
  }

  public static void besuchePostOrder(Knoten knoten) {
    // Sonderfall leerer (Teil-)Baum
    if (knoten == null) {
      System.out.println("Leerer Baum");
    } else {
      // Linker
      if (knoten.gibLinks() != null) {
        besuchePostOrder(knoten.gibLinks());
      }
      // Rechter
      if (knoten.gibRechts() != null) {
        besuchePostOrder(knoten.gibRechts());
      }
      System.out.println(knoten.gibZahl());
    }
  }

  public int berechneSumme(Knoten knoten) {
    int ergebnis = 0;

    // Sonderfall: leerer Unterbaum
    if (knoten == null) {
      return 0;
    }
    // linker
    if (knoten.gibLinks() != null) {
      ergebnis = ergebnis + berechneSumme(knoten.gibLinks());
    }
    // rechter
    if (knoten.gibRechts() != null) {
      ergebnis = ergebnis + berechneSumme(knoten.gibRechts());
    }
    // Wurzel
    ergebnis = ergebnis + knoten.gibZahl();
    return ergebnis;
  }

  public boolean istSortiert(Knoten knoten) {
    // Baum leer
    if (knoten == null) {
      return true;
    }

    // linker Nachfolger nicht okay
    if (knoten.gibLinks() != null && knoten.gibLinks().gibZahl() > knoten.gibZahl()) {
      return false;
    }

    // rechter Nachfolger nicht okay
    if (knoten.gibRechts() != null && knoten.gibRechts().gibZahl() <= knoten.gibZahl()) {
      return false;
    }
    // sonst prüfe Teilbaeume
    return (istSortiert(knoten.gibRechts()) && istSortiert(knoten.gibLinks()));
  }

  public static void main(String[] args) {
    BinBaum baum = new BinBaum();

    baum.fügeEin(5);
    baum.fügeEin(7);
    baum.fügeEin(4);
    baum.fügeEin(11);
    baum.fügeEin(6);
    baum.fügeEin(2);

    besuchePostOrder(baum.wurzel);
  }
}
