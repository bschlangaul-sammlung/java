package org.bschlangaul.liste;

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
   * {@inheritDoc}
   */
  public void fügeHinzu(Object wert) {
    liste.setzeErstes(wert);
  }

  /**
   * {@inheritDoc}
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

  /**
   * {@inheritDoc}
   */
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
