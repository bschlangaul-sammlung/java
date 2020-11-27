package org.bschlangaul.liste.saake;

/**
 * heißt im Original „LeereListeFehler“.
 */
@SuppressWarnings("serial")
class LeereListeFehler extends Exception {
  public LeereListeFehler(String errorMessage) {
      super(errorMessage);
  }
  public LeereListeFehler() {
    super();
  }
}
