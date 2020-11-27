package org.bschlangaul.aufgaben.oomup.pu_2.schildkroete;

/**
 * Deine Schildkröte hat folgende Methoden zur Verfügung:
 * Vorwärts Laufen
 * um die angegebene Entfernung laufeZu(double xNeu, double yNeu) Laufen zu den
 * angegebenen Koordinaten warte(int anzahlZyklen) Warten die angebene Anzahl an
 * Programmzyklen setzeStiftfarbe(String farbe) Setzt die Stiftfarbe auf die
 * übergeben Farbe; mögliche Farben: rot, schwarz, blau, gelb, gruen, orange,
 * magenta versteckeSchildkroete() Macht die Schildkroete unsichtbar
 * zeigeSchildkroete() Macht die Schildkroete wieder sichtbar pralleAb()
 * Abprallen der Schildkroete am Rand der Spielwelt
 *
 */
public class meineSchildkroete extends Schildkroete {
  /**
   * Act - tut, was auch immer meineSchildkroete tun will. Diese Methode wird
   * aufgerufen, sobald der 'Act' oder 'Run' Button in der Umgebung angeklickt
   * werden.
   */
  public void act() {
    // 1
    // a)
    // zeichneQuadrat(100);

    // b)
    // nachVorneDannQuadrat();

    // c)
    // i)
    // gestricheltesQuadrat(300, 20);

    // ii)
    // verschachtelteQuadrate();

    // iii)
    // zeichneLabyrinth(20);

    // 2
    zeichneKreis(150);

    zeichneBlume();
  }

  public void zeichneQuadrat(int seitenLaenge) {
    for (int i = 0; i < 4; i++) {
      laufe(seitenLaenge);
      dreheLinks(90);
    }
  }

  public void nachVorneDannQuadrat() {
    nehmeStiftAuf();
    laufe(150);
    setzeStiftAb();
    zeichneQuadrat(150);
  }

  public void gestrichelteLinie(int laenge, int anzahlStriche) {
    int teilStueck = laenge / (anzahlStriche * 2);
    for (int i = 0; i < anzahlStriche / 2; i++) {
      nehmeStiftAuf();
      laufe(teilStueck);
      setzeStiftAb();
      laufe(teilStueck);
    }
  }

  public void gestricheltesQuadrat(int laenge, int anzahlStriche) {
    for (int i = 0; i < 4; i++) {
      gestrichelteLinie(laenge, anzahlStriche);
      dreheLinks(90);
    }
  }

  public void verschachtelteQuadrate() {
    for (int i = 20; i <= 100; i = i + 20) {
      zeichneQuadrat(i);
    }
  }

  public void zeichneLabyrinth(int groesse) {
    for (int i = 0; i <= groesse; i++) {
      laufe(10 * i);
      dreheLinks(90);
    }
  }

  public void zeichneKreis(int groesse) {
    for (int i = 0; i < 360; i++) {
      laufe(groesse / 100);
      dreheLinks(1);
    }
  }

  public void zeichneBlume() {
    for (int i = 0; i < 360; i++) {
      zeichneKreis(150);
      dreheLinks(i);
      zeichneKreis(200);
      dreheLinks(i + 1);
    }
  }
}
