package org.bschlangaul.aufgaben.aud.ab_7.hanoi;

/**
 * https://www.studon.fau.de/file2617984_download.html
 */
public class Turm {
  private Element oben;

  public Turm() {
    oben = null;
  }

  public Element gibOben() {
    return oben;
  }

  public void legeDrauf(Element e) {
    if (oben == null) {
      oben = e;
    } else if (oben.gibWert() == e.gibWert()) {
      System.out.println("Fehler! schieben sind gleich gross");
    } else if (oben.gibWert() < e.gibWert()) {
      System.out.println("Fehler! Größere Scheiben dürfen nicht auf kleinere");
    } else {
      e.setzeNächstes(oben);
      oben = e;
    }
  }

  public Element nimmHerunter() {
    if (oben == null) {
      System.out.println("Turm ist bereits leer!");
      return null;
    } else {
      Element merker = new Element(oben.gibWert());
      oben = oben.gibNächstes();
      return merker;
    }
  }

}
