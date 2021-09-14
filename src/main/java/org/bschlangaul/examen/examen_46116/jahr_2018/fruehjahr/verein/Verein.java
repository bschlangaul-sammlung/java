package org.bschlangaul.examen.examen_46116.jahr_2018.fruehjahr.verein;

public class Verein {
  private String name;

  public Verein(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static void main(String[] args) {
    Verein verein1 = new Verein("FC Staatsexamen");
    Spieler spieler1 = new Spieler("Hugo Meier", verein1);
    Spieler spieler2 = new Spieler("Frank Huber", verein1);
    Mannschaft deutscheMannschaft = new Mannschaft("Deutschland");
    deutscheMannschaft.hinzufuegen(spieler1);
    deutscheMannschaft.hinzufuegen(spieler2);
    spieler1.schiesseTor();
  }
}
