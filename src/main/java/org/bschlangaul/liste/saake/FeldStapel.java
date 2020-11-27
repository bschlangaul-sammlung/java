package org.bschlangaul.liste.saake;

/**
 * Implementierung des Stapeles auf Basis eines Feldes.
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 319/320), heißt im
 * Original „ArrayStack“.
 */
public class FeldStapel implements Stapel {

  /**
   * Elemente
   */
  private Object elements[] = null;

  /**
   * aktuelle Anzahl.
   */
  private int anzahlElemente = 0;

  /**
   * Stapel mit vorgegebener Kapazität erzeugen.
   *
   * @param size Die Anzahl der möglichen Stapelelemente.
   */
  public FeldStapel(int size) {
    elements = new Object[size];
  }

  /**
   * Stack mit Standardkapazität erzeugen
   */
  public FeldStapel() {
    elements = new Object[100];
  }

  public void fügeHinzu(Object objekt) throws StapelFehler {
    if (anzahlElemente == elements.length)
      // Kapazität erschöpft
      throw new StapelFehler();
    elements[anzahlElemente++] = objekt;
  }

  public Object entnimm() throws StapelFehler {
    if (istLeer())
      // Stack ist leer
      throw new StapelFehler();
    Object o = elements[--anzahlElemente];
    elements[anzahlElemente] = null;
    return o;
  }

  public Object zeigeOberstes() throws StapelFehler {
    if (istLeer())
      throw new StapelFehler();
    return elements[anzahlElemente - 1];
  }

  public boolean istLeer() {
    return anzahlElemente == 0;
  }

  public static void main(String[] args) throws StapelFehler {
    FeldStapel stapel = new FeldStapel(3);
    stapel.fügeHinzu("eins");
    stapel.fügeHinzu("zwei");
    stapel.fügeHinzu("drei");

    System.out.println(stapel.entnimm());
    System.out.println(stapel.entnimm());
    System.out.println(stapel.entnimm());
  }
}
