package org.bschlangaul.examen.examen_46116.jahr_2018.fruehjahr.verein;

public class Mannschaft {
  @SuppressWarnings("unused")
  private String land;
  private int anzahlSpieler;
  private Spieler[] team;

  public Mannschaft(String land) {
    this.land = land;
    anzahlSpieler = 0;
    team = new Spieler[20];
  }

  public void hinzufuegen(Spieler s) {
    team[anzahlSpieler] = s;
    anzahlSpieler++;
  }

  public String torschuetzenkoenig() {
    if (anzahlSpieler == 0) {
      return "Kein Spieler vorhanden";
    }
    Spieler koenig = team[0];
    for (int i = 0; i < anzahlSpieler; i++) {
      Spieler spieler = team[i];
      if (spieler.getTore() > koenig.getTore()) {
        koenig = spieler;
      }
    }
    return koenig.getName();
  }
}
