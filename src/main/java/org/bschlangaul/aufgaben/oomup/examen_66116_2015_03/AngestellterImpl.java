package org.bschlangaul.aufgaben.oomup.examen_66116_2015_03;

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
