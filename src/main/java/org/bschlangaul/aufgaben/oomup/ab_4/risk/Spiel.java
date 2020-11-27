package org.bschlangaul.aufgaben.oomup.ab_4.risk;

public class Spiel {
  private int gewinn;
  private boolean[] würfe;
  private boolean spielVerloren;
  private boolean spielBeendet;
  private int würfelSeitenAnzahl;

  public Spiel() {
    würfelSeitenAnzahl = 6;
    spielBeginnen();
  }

  public Spiel(int würfelSeitenAnzahl) {
    this.würfelSeitenAnzahl = würfelSeitenAnzahl;
    spielBeginnen();
  }

  private int gibWürfelZufallsZahl() {
    return (int) (Math.random() * würfelSeitenAnzahl) + 1;
  }

  public void spielBeginnen() {
    gewinn = 0;
    spielBeendet = false;
    spielVerloren = false;
    würfe = new boolean[würfelSeitenAnzahl];
  }

  public void würfle() {
    if (spielVerloren) {
      System.out.println(
        "Sie können nicht mehr würfeln, "+
        "weil Sie das Spiel bereits verloren haben.");
      return;
    }
    if (spielVerloren) {
      System.out.println(
          "Sie können nicht mehr würfeln, weil Sie das Spiel " +
          "bereits beendet haben. Starten sie ein neues Spiel");
      return;
    }
    int wurf = gibWürfelZufallsZahl();
    System.out.println("Sie haben " + wurf + " gewürfelt.");

    if (!würfe[wurf - 1]) {
      gewinn++;
      würfe[wurf - 1] = true;
      System.out.println("Sie können weiterspielen. " +
        "Ihr aktueller Punktestand ist " + gewinn + " Punkte.");
      return;
    }
    spielVerloren = true;
    System.out.println("Sie haben verloren. Sie haben die Zahl " +
      "schon einmal gewürfelt.");
  }

  public void berichteÜberWürfe() {
    for (int i = 0; i < würfe.length; i++) {
      boolean wurf = würfe[i];
      int zahl = i + 1;
      if (wurf) {
        System.out.println("Die Zahl " + zahl +
          " wurde bereits geworfen.");
      } else {
        System.out.println("Die Zahl " + zahl +
          " wurde noch nicht geworfen.");
      }
    }
  }

  public void spielBeenden() {
    if (spielBeendet) {
      System.out.println("Sie haben das Spiel bereits beendet.");
      return;
    }
    spielBeendet = true;
    if (spielVerloren) {
      System.out.println("Sie haben leider verloren");
    } else {
      System.out.println("Sie haben gewonnen. " +
        "Ihr Punktestand lautet: " + gewinn + ".");
    }
  }
}
