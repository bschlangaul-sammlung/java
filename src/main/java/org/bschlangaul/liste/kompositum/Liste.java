package org.bschlangaul.liste.kompositum;

/**
 * Eine Liste mit Kompositium.
 */
public class Liste {

  /**
   * Ein Verweis auf das erste Listenelement.
   */
  private ListenElement erster;

  /**
   * Erzeuge eine leere Liste.
   *
   * Auf das Attribut {@link erster} wird eine Instanz der Klasse {@link Abschluss} gesetzt.
   */
  public Liste() {
    erster = new Abschluss();
  }

  /**
   * Gib das erste Listenelement zurück.
   *
   * @return Das erste Listenelement.
   */
  public ListenElement gibErstesElement() {
    return erster;
  }

  /**
   * Gib das erste Datenelement zurück.
   *
   * @return Das erste Datenelement.
   */
  public DatenElement gibErstesDatenElement() {
    return erster.gibDaten();
  }

  /**
   * Gib die Summer des Alters aller Datenelemente. zurück.
   *
   * @return Die Summer des Alters aller Datenelemente.
   */
  public int gibSummeAlter() {
    return erster.gibSummeAlter();
  }

  /**
   * Gib das letzte Datenelement zurück.
   *
   * @return Das letzte Datenelement.
   */
  public DatenElement gibLetzteDaten() {
    return erster.gibLetzteDaten(erster.gibDaten());
  }

  /**
   * Füge eine Datenelement hinten in die Liste ein.
   *
   * @param daten Ein Datenelement.
   */
  public void fügeHintenEin(DatenElement daten) {
    erster = erster.fügeHintenEin(daten);
  }

  /**
   * Füge eine Datenelement vor in die Liste ein.
   *
   * @param daten Ein Datenelement.
   */
  public void fügeVorneEin(DatenElement daten) {
    DatenKnoten neuerKnoten = new DatenKnoten(erster, daten);
    erster = neuerKnoten;
  }

  /**
   * Entnehme eine Datenelement vor aus der Liste.
   *
   * @return Ein Datenelement.
   */
  public DatenElement vorneEntnehmen() {
    DatenElement alterKnotendaten = erster.gibDaten();
    erster = erster.gibNächstes();
    return alterKnotendaten;
  }

  /**
   * Gib die Anzahl der Datenknoten zurück.
   *
   * @return Die Anzahl der Datenknoten.
   */
  public int gibAnzahlDatenKnoten() {
    return erster.gibAnzahlDatenKnoten();
  }

  /**
   * Gib die Daten in der Konsole aus.
   */
  public void gibDatenAus() {
    erster.gibDatenAus();
  }

  public static void main(String[] args) {
    Liste liste = new Liste();

    DatenElement max = new DatenElement("Max Mustermann", 33);
    DatenElement maria = new DatenElement("Maria Mustermann", 34);

    liste.fügeHintenEin(max);
    liste.fügeHintenEin(maria);

    DatenElement adam = new DatenElement("Adam Adamczewski", 18);

    liste.fügeVorneEin(adam);

    System.out.println(liste.gibAnzahlDatenKnoten());
    liste.gibDatenAus();

    System.out.println(liste.gibSummeAlter());
  }
}
