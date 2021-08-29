package org.bschlangaul.aufgaben.aud.listen.hanoi;

/**
 * https://www.studon.fau.de/file2617984_download.html
 */
public class Element {
  private int wert;
  private Element nächstes;

  public Element(int wert) {
    this.wert = wert;
    nächstes = null;
  }

  public int gibWert() {
    return wert;
  }

  public Element gibNächstes() {
    return nächstes;
  }

  public void setzeNächstes(Element next) {
    this.nächstes = next;
  }
}
