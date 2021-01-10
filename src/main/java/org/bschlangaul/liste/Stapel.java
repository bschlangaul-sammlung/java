package org.bschlangaul.liste;

/**
 * Interface für alle Stapel.
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 316), heißt im
 * Original „Stack“.
 */
public interface Stapel {
  /**
   * Ein Element vorne anfügen. Diese Methode heißt im Englischen oft „push()“.
   *
   * @param wert Ein beliebiges Objekt.
   *
   * @throws StapelFehler Ein Stapelfehler.
   */
  public void fügeHinzu(Object wert) throws StapelFehler;

  /**
   * Ein Element von vorne entfernen. Diese Methode heißt im Englischen oft
   * „pop()“.
   *
   * @return Ein beliebiges Objekt.
   *
   * @throws StapelFehler Ein Stapelfehler.
   */
  public Object entnimm() throws StapelFehler;

  /**
   * Diese Methode heißt im Englischen oft „top()“.
   *
   * @return Ein beliebiges Objekt.
   *
   * @throws StapelFehler Ein Stapelfehler.
   */
  public Object zeigeOberstes() throws StapelFehler;

  /**
   * Diese Methode heißt im Englischen oft „isEmpty()“.
   *
   * @return wahr, wenn der Stapel leer ist.
   */
  public boolean istLeer();
}
