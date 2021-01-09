package org.bschlangaul.examen.examen_66116.jahr_2015.fruehjahr;

public abstract class PersonImpl implements Person {
  protected String name;
  protected char geschlecht;

  public PersonImpl(String name, char geschlecht) {
    this.name = name;
    this.geschlecht = geschlecht;
  }

  public String getName() {
    return name;
  }

  public char getGeschlecht() {
    return geschlecht;
  }
}
