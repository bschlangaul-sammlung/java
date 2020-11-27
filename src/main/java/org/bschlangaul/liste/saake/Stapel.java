package org.bschlangaul.liste.saake;

/**
 * Interface für alle Stapel.
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 316),
 * heißt im Original „Stack“.
 */
public interface Stapel {
  /**
   * „push()“
   *
   * @param objekt Ein beliebiges Objekt.
   *
   * @throws StapelFehler Ein Stapelfehler.
   */
  public void fügeHinzu(Object objekt) throws StapelFehler;

  /**
   * „pop()“
   *
   * @return Ein beliebiges Objekt.
   *
   * @throws StapelFehler Ein Stapelfehler.
   */
  public Object entnimm() throws StapelFehler;

  /**
   * „top()“
   *
   * @return Ein beliebiges Objekt.
   *
   * @throws StapelFehler Ein Stapelfehler.
   */
  public Object zeigeOberstes() throws StapelFehler;

  /**
   * „isEmpty()“
   *
   * @return wahr wenn der Stapel leer ist.
   */
  public boolean istLeer();
}
