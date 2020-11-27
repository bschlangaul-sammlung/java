package org.bschlangaul.entwurfsmuster.einzelstueck;

/**
 * Schatten Seite 249
 */
public class NoficationManagerServiceThreadSafe {
  private NoficationManagerServiceThreadSafe() {
  }

  private static NoficationManagerServiceThreadSafe notmanservice = new NoficationManagerServiceThreadSafe();

  public static NoficationManagerServiceThreadSafe getInstance() {
    return notmanservice;
  }

}
