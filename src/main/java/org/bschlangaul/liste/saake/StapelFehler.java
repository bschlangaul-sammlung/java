package org.bschlangaul.liste.saake;

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
