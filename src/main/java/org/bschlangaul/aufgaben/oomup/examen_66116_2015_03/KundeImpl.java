package org.bschlangaul.aufgaben.oomup.examen_66116_2015_03;

public class KundeImpl extends PersonImpl implements Kunde {
  protected int kundennummer;

  public KundeImpl(String name, char geschlecht, int kundennummer) {
    super(name, geschlecht);
    this.kundennummer = kundennummer;
  }

  public int getKundennummer() {
    return kundennummer;
  }
}
