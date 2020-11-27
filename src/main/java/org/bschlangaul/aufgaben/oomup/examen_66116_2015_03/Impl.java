package org.bschlangaul.aufgaben.oomup.examen_66116_2015_03;

public class Impl {

  public static abstract class PersonImpl implements Person {
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

  public static class AngestellterImpl extends PersonImpl implements Angestellter {
    protected int gehalt;

    public AngestellterImpl(String name, char geschlecht, int gehalt) {
      super(name, geschlecht);
      this.gehalt = gehalt;
    }

    public int getGehalt() {
      return gehalt;
    }
  }

  public static class KundeImpl extends PersonImpl implements Kunde {
    protected int kundennummer;

    public KundeImpl(String name, char geschlecht, int kundennummer) {
      super(name, geschlecht);
      this.kundennummer = kundennummer;
    }

    public int getKundennummer() {
      return kundennummer;
    }
  }
}
