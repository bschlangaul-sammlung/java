package org.bschlangaul.examen.examen_66116.jahr_2015.fruehjahr;

public class AngestellterImpl extends PersonImpl implements Angestellter {
  protected int gehalt;

  public AngestellterImpl(String name, char geschlecht, int gehalt) {
    super(name, geschlecht);
    this.gehalt = gehalt;
  }

  public int getGehalt() {
    return gehalt;
  }

}
