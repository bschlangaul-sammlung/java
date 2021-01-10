package org.bschlangaul.liste;

/**
 * heißt im Original „StackException“.
 */
@SuppressWarnings("serial")
class StapelFehler extends Exception {
  public StapelFehler(String errorMessage) {
      super(errorMessage);
  }
  public StapelFehler() {
    super();
  }
}
