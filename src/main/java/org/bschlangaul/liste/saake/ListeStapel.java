package org.bschlangaul.liste.saake;

/**
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 329-330)
 * heißt im Original „ListStack“.
 */
public class ListeStapel implements Stapel {
  /**
   * Liste zur Verwaltung der Elemente
   */
  private Liste liste;

  public ListeStapel() {
    liste = new Liste();
  }

  /**
   * Element vorn anfügen
   */
  public void fügeHinzu(Object objekt) {
    liste.fügeErstesHinzu(objekt);
  }

  /**
   * Element von vorn entfernen.
   */
  public Object entnimm() throws StapelFehler {
    if (istLeer())
      throw new StapelFehler();

    try {
      return liste.entferneErstes();
    } catch (LeereListeFehler fehler) {
      return null;
    }
  }

  public Object zeigeOberstes() throws StapelFehler {
    if (istLeer())
      throw new StapelFehler();
    try {
      return liste.gibErstes();
    } catch (LeereListeFehler fehler) {
      return null;
    }
  }

  public boolean istLeer() {
    return liste.istLeer();
  }

  public static void main(String[] args) throws StapelFehler {
    ListeStapel stapel = new ListeStapel();
    stapel.fügeHinzu("eins");
    stapel.fügeHinzu("zwei");
    stapel.fügeHinzu("drei");

    System.out.println(stapel.entnimm());
    System.out.println(stapel.entnimm());
    System.out.println(stapel.entnimm());
  }
}
