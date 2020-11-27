package org.bschlangaul.entwurfsmuster.einzelstueck;

/**
 * Schatten Seite 248
 */
public class NoficationManagerService {
  private NoficationManagerService() {
  }

  private static NoficationManagerService notmanservice = null;

  public static NoficationManagerService getInstance() {
    if (notmanservice == null) {
      notmanservice = new NoficationManagerService();
    }
    return notmanservice;
  }

}
