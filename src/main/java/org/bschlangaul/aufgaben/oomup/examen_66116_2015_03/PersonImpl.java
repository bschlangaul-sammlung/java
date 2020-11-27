package org.bschlangaul.aufgaben.oomup.examen_66116_2015_03;

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
