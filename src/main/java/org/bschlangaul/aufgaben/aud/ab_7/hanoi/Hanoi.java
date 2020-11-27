package org.bschlangaul.aufgaben.aud.ab_7.hanoi;

/**
 * https://www.studon.fau.de/file2617984_download.html
 */
public class Hanoi {
  private int anzahlScheiben = 3;
  private int[] turm0helper, turm1helper, turm2helper;
  Turm turm0 = new Turm();
  Turm turm1 = new Turm();
  Turm turm2 = new Turm();

  public Hanoi(int anzahlScheiben) {
    if (anzahlScheiben <= 0) {
      System.out.println("Zu wenige Scheiben. Defaulfall (anzahlScheiben = 3) wird angewendet!");
    } else {
      this.anzahlScheiben = anzahlScheiben;
    }
    for (int i = this.anzahlScheiben; i > 0; i--) {
      turm0.legeDrauf(new Element(i));
    }
    zeigeTürme();
  }

  /**
   * @param n Höhe des Turms (Anzahl der Scheiben).
   * @param quelle Der Ausgangs-Turm.
   * @param ziel Der Ziel-Turm.
   * @param hilfe Der Hilfs-Turm in der Mitte.
   */
  public void hanoi(int n, Turm quelle, Turm ziel, Turm hilfe) {
    if (n == 1) {
      verschiebeScheibe(quelle, ziel);
      System.out.println("Fertig!");
    } else {
      hanoi(n - 1, quelle, hilfe, ziel);
      verschiebeScheibe(quelle, ziel);
      hanoi(n - 1, hilfe, ziel, quelle);
    }
  }

  public void verschiebeScheibe(Turm quelle, Turm ziel) {
    // Bereinigt die Konsole
    System.out.print('\u000C');

    if (quelle == ziel) {
      System.out.println("Quelle ist gleich dem Ziel!");
      return;
    }
    ziel.legeDrauf(quelle.nimmHerunter());
    zeigeTürme();
  }

  /**
   * Zeige die drei Türme in der Konsole
   */
  public void zeigeTürme() {
    Element merker = turm0.gibOben();
    int zeiger0 = 0;
    int zeiger1 = 0;
    int zeiger2 = 0;
    turm0helper = new int[this.anzahlScheiben];
    turm1helper = new int[this.anzahlScheiben];
    turm2helper = new int[this.anzahlScheiben];
    int i = 0;
    while (merker != null) {
      turm0helper[i++] = merker.gibWert();
      merker = merker.gibNächstes();
      zeiger0++;
    }
    merker = turm1.gibOben();
    i = 0;
    while (merker != null) {
      turm1helper[i++] = merker.gibWert();
      merker = merker.gibNächstes();
      zeiger1++;
    }
    merker = turm2.gibOben();
    i = 0;
    while (merker != null) {
      turm2helper[i++] = merker.gibWert();
      merker = merker.gibNächstes();
      zeiger2++;
    }

    int help0 = zeiger0 % this.anzahlScheiben;
    int help1 = zeiger1 % this.anzahlScheiben;
    int help2 = zeiger2 % this.anzahlScheiben;

    for (int j = 0; j < turm0helper.length; j++) {
      System.out.print(turm0helper[help0++] + "  " + turm1helper[help1++] + "  " + turm2helper[help2++]);
      System.out.println();
      help0 = help0 % this.anzahlScheiben;
      help1 = help1 % this.anzahlScheiben;
      help2 = help2 % this.anzahlScheiben;
    }

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      // ignore
    }
  }

  public static void main(String[] args) {
    Hanoi h = new Hanoi(5);
    h.hanoi(5, h.turm0, h.turm2, h.turm1);
  }
}
