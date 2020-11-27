package org.bschlangaul.aufgaben.oomup.pu_3.schneckenrennen;

public class Schneckenrennen {
  private Rennschnecke b;
  private Rennschnecke l;
  private double bahnrekord = 30;

  public Schneckenrennen() {
    b = new Rennschnecke("Bert", "Tigerschnecke", 4);
    l = new Rennschnecke("Lisa", "Apfelschnecke", 3);
  }

  public void rennen() {
    for (int i = 0; i < 5; i++) {
      b.kriechen();
      l.kriechen();
    }
  }

  public void siegerErmitteln() {
    if (b.gibStrecke() > l.gibStrecke()) {
      System.out.println(b.gibName() + " hat gewonnen und ist " + b.gibStrecke() + " cm gelaufen.");
      if (b.gibStrecke() > bahnrekord) {
        System.out.println("Neuer Rekord");
        bahnrekord = b.gibStrecke(); // Speichert neuen Bahnrekord ab,
      }
    } else if (b.gibStrecke() < l.gibStrecke()) {
      System.out.println(b.gibName() + " hat gewonnen und ist " + b.gibStrecke() + " cm gelaufen.");
      if (l.gibStrecke() > bahnrekord) {
        System.out.println("Neuer Rekord");
        bahnrekord = l.gibStrecke();
      }
    } else {
      System.out.println("Unentschieden.");
    }
  }
}
