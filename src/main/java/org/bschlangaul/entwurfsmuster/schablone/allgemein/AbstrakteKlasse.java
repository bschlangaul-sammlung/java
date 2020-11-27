package org.bschlangaul.entwurfsmuster.schablone.allgemein;

public abstract class AbstrakteKlasse {

  public void schablonenMethode() {
    primitiveMethode1();
    primitiveMethode2();
  }

  public abstract void primitiveMethode1();

  public abstract void primitiveMethode2();

  /**
   * Wird überschrieben von der konkreten Klasse.
   */
  public void hakenMethode1() {
  }


  /**
   * Wird nicht überschreiben von der konkreten Klasse.
   */
  public void hakenMethode2() {
  }
}
