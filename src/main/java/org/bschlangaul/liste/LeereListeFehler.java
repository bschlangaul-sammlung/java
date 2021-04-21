package org.bschlangaul.liste;

/**
 * Wird im Englischen oft „ListEmptyException“ genannt.
 */
class LeereListeFehler extends Exception {
  public LeereListeFehler(String fehlerMeldung) {
      super(fehlerMeldung);
  }
  public LeereListeFehler() {
    super();
  }
}
