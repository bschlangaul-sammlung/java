package org.bschlangaul.liste;

/**
 * Interface für alle Warteschlangen.
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 318), heißt im
 * Original „Queue“.
 */
public interface Warteschlange {
  /**
   * Betrete die Warteschlange. Das gegeben Objekt wird an das Ende der
   * Warteschlange eingereight. Diese Methode heißt im Englischen oft „enter()“
   * oder „enqueue()“.
   *
   * @param objekt Ein belieiges Objekt, das in die Warteschlange eingereiht
   *               werden kann.
   *
   * @throws WarteschlangeFehler Wirft einen Fehler, wenn die
   *                             Warteschlangenkapazität erschöpft ist.
   */
  public void betrete(Object objekt) throws WarteschlangeFehler;

  /**
   * Verlasse die Warteschlange. Das erste Objekt in der Warteschlange wird
   * entnommen und zurückgegeben. Diese Methode heißt im Englischen oft „leave()“
   * oder „dequeue()“.
   *
   * @return Das erste Objekt in der Warteschlange.
   *
   * @throws WarteschlangeFehler Wirft einen Fehler, wennn keine Elemente mehr in
   *                             der Warteschlange sind, die herausgenommen werden
   *                             könnten.
   */
  public Object verlasse() throws WarteschlangeFehler;

  /**
   * Gib das erste Element in der Warteschlange zurück, ohne es aus der Liste zu
   * entfernen. Diese Methode heißt im Englischen oft „front()“
   *
   * @return Das erste Objekt in der Warteschlange.
   *
   * @throws WarteschlangeFehler Wirft einen Fehler, wennn keine Elemente mehr in
   *                             der Warteschlange sind, die herausgenommen werden
   *                             könnten.
   */
  public Object gibAnfang() throws WarteschlangeFehler;

  /**
   * Diese Methode heißt im Englischen oft „isEmpty()“
   *
   * @return wahr, wenn die Warteschlange leer ist.
   */
  public boolean istLeer();
}
