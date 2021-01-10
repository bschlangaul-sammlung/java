package org.bschlangaul.liste;

/**
 * Implementierung einer Warteschlange (Queue) auf Basis eines Feldes (Arrays).
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 321/322), heißt im
 * Original „ArrayQueue“.
 */
public class FeldWarteschlange implements Warteschlange {
  /**
   * Feld (Array), das die Element der Warteschlange aufnimmt.
   */
  private Object[] elemente = null;

  private int untererIndex = 0;
  private int obererIndex = 0;

  /**
   * Erzeuge eine Warteschlange mit der gegeben Kapazität an Objekten.
   *
   * @param kapazität Wie viele Elemente die Warteschlange aufnehmen kann.
   */
  public FeldWarteschlange(int kapazität) {
    elemente = new Object[kapazität];
  }

  /**
   * Erzeuge eine Warteschlange mit der Standardkapazität von 100 Objekten.
   */
  public FeldWarteschlange() {
    elemente = new Object[100];
  }

  /**
   * {@inheritDoc}
   */
  public void betrete(Object objekt) throws WarteschlangeFehler {
    if ((elemente.length - untererIndex + obererIndex) % elemente.length == elemente.length - 1)
      // Kapazität ist erschöpft
      throw new WarteschlangeFehler();
    elemente[obererIndex] = objekt;
    // oberen Zeiger aktualisieren
    obererIndex = (obererIndex + 1) % elemente.length;
  }

  /**
   * {@inheritDoc}
   */
  public Object verlasse() throws WarteschlangeFehler {
    if (istLeer())
      throw new WarteschlangeFehler();
    Object objekt = elemente[untererIndex];
    elemente[untererIndex] = null;
    // unteren Zeiger aktualisieren
    untererIndex = (untererIndex + 1) % elemente.length;
    return objekt;
  }

  /**
   * {@inheritDoc}
   */
  public Object gibAnfang() throws WarteschlangeFehler {
    if (istLeer())
      throw new WarteschlangeFehler();
    return elemente[untererIndex];
  }

  /**
   * {@inheritDoc}
   */
  public boolean istLeer() {
    return untererIndex == obererIndex;
  }
}
