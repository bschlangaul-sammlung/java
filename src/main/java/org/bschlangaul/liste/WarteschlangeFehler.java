package org.bschlangaul.liste;

/**
 * heißt im Original „QueueException“.
 */
public class WarteschlangeFehler extends Exception {
  public WarteschlangeFehler(String errorMessage) {
      super(errorMessage);
  }
  public WarteschlangeFehler() {
    super();
  }
}
