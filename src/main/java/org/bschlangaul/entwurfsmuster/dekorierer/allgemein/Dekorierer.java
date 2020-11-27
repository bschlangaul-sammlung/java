package org.bschlangaul.entwurfsmuster.dekorierer.allgemein;

public abstract class Dekorierer extends Komponente {
  protected Komponente komponente;

  /**
   * Konstruktor zum komfortablen Initiieren am Client
   *
   * @param komponente Die Komponente.
   */
  public Dekorierer(Komponente komponente) {
    this.komponente = komponente;
  }
  // agiere() wird nicht implementiert.
}
