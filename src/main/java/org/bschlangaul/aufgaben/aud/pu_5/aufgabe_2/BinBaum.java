package org.bschlangaul.aufgaben.aud.pu_5.aufgabe_2;

public class BinBaum {

  private Knoten wurzel = null;

  public void setWurzel(Knoten k) {
    wurzel = k;
  }

  public void fuegeEin(int zahl) {
    Knoten aktueller = wurzel;
    Knoten neuerKnoten = new Knoten(zahl);
    if (wurzel == null) {
      wurzel = neuerKnoten;
      return;
    }
    while (aktueller != null) {
      if (zahl <= aktueller.getZahl() && aktueller.getLinkerTB() != null) { // suche links
        aktueller = aktueller.getLinkerTB();
      } else if (zahl <= aktueller.getZahl() && aktueller.getLinkerTB() == null) { // fuege ein
        aktueller.setLinkerTB(neuerKnoten);
        break;
      }
      if (zahl > aktueller.getZahl() && aktueller.getRechterTB() != null) { // suche rechts
        aktueller = aktueller.getRechterTB();
      } else if (zahl > aktueller.getZahl() && aktueller.getRechterTB() == null) { // fuege ein
        aktueller.setRechterTB(neuerKnoten);
        break;
      }
    }
  }

  public static void postOrder(Knoten k) { // (Teil)Baum ab Knoten k
    if (k == null) { // Sonderfall leerer (Teil-)Baum
      System.out.println("Leerer Baum");
    } else {
      if (k.getLinkerTB() != null) { // Linker
        postOrder(k.getLinkerTB());
      }
      if (k.getRechterTB() != null) { // Rechter
        postOrder(k.getRechterTB());
      }
      System.out.println(k.getZahl());
    }
  }// Wurzel

  public int summe(Knoten k) {
    int erg = 0;

    if (k == null) // Sonderfall: leerer Unterbaum
    {
      return 0;
    } else {
      if (k.getLinkerTB() != null) { // linker
        erg = erg + summe(k.getLinkerTB());
      }
      if (k.getRechterTB() != null) { // rechter
        erg = erg + summe(k.getRechterTB());
      }
      erg = erg + k.getZahl(); // Wurzel
      return erg;
    }
  }

  public boolean isSorted(Knoten k) {
    if (k == null) {
      return true;
    } // Baum leer
    else {
      if (k.getLinkerTB() != null && k.getLinkerTB().getZahl() > k.getZahl()) {
        return false;
      } // linker Nachfolger nicht okay

      if (k.getRechterTB() != null && k.getRechterTB().getZahl() <= k.getZahl()) {
        return false;
      } // rechter Nachfolger nicht okay

      return (isSorted(k.getRechterTB()) && isSorted(k.getLinkerTB())); // sonst pruefe Teilbaeume
    }
  }

  public static void main(String[] args) {
    BinBaum meinBinBaum = new BinBaum();

    meinBinBaum.fuegeEin(5);
    meinBinBaum.fuegeEin(7);
    meinBinBaum.fuegeEin(4);
    meinBinBaum.fuegeEin(11);
    meinBinBaum.fuegeEin(6);
    meinBinBaum.fuegeEin(2);

    postOrder(meinBinBaum.wurzel);
  }
}
