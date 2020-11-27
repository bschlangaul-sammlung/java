package org.bschlangaul.liste.saake;

/**
 * Interface für alle Warteschlangen.
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 318),
 * heißt im Original „Queue“.
 */
public interface Warteschlange {
  public void enter(Object obj) throws WarteschlangeFehler;

  public Object leave() throws WarteschlangeFehler;

  public Object front() throws WarteschlangeFehler;

  public boolean isEmpty();
}
