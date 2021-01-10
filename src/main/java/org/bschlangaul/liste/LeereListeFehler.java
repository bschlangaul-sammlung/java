package org.bschlangaul.liste;

/**
 * Wird im Englischen ofr „ListEmptyException“ genannt.
 */
@SuppressWarnings("serial")
class LeereListeFehler extends Exception {
  public LeereListeFehler(String fehlerMeldung) {
      super(fehlerMeldung);
  }
  public LeereListeFehler() {
    super();
  }
}
