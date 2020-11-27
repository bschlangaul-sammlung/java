package org.bschlangaul.aufgaben.aud.ab_2;

/**
 * Vorlage für verschiedene Suchalgorithmen.
 *
 * <a href="https://www.studon.fau.de/file2506781_download.html">Angabe: AB_2
 * Rekursion_Sortieren_Komplexitaet.pdf</a>
 * <a href="https://www.studon.fau.de/file2506784_download.html">Lösung: AB_2
 * Rekursion_Sortieren_Komplexitaet_Lsg.pdf</a>
 */
public class Suchalgorithmen {
  private final Eingabefenster fenster;

  /**
   * Konstruktor: Erzeugt ein neues Objekt der Klasse Suchalgorithmen und macht es
   * mit dem zugehörigen Eingabefenster bekannt.
   */
  public Suchalgorithmen(Eingabefenster fenster) {
    this.fenster = fenster;
  }

  /**
   * Sequenzielle Suche: Durchsucht das Array nach dem Wert und gibt dessen
   * Position als Ergebnis zurück.
   *
   * @param array Ein Feld mit Zahlen.
   * @param wert Die Zahl, die gesucht werden soll.
   *
   * @return Die Indexnummer der gesuchten Zahl.
   */
  public int sucheSequenziell(int[] array, int wert) {
    fenster.schreibeZeile("\nFühre die Methode sucheSequenziell() aus:");
    fenster.schreibe("Suche in diesem Feld: ");
    fenster.schreibeArray(array);
    fenster.schreibeZeile("");
    // Wiederhole für alle Elemente des Arrays:
    for (int i = 0; i < array.length; i++) {
      fenster.schreibeZeile("Überprüfen des Werts an der Position " + i);
      // Wenn das Element an der Stelle i der gesuchte Wert ist:
      if (array[i] == wert) {
        fenster.schreibeZeile("Fertig :-)");
        // Gib die Position i als Ergebnis zurück:
        return i;
      }
    }
    fenster.schreibeZeile("Nichts gefunden :-(");
    // Gib den Sonderfalls -1 (nichts gefunden) als Ergebnis zurück:
    return -1;
  }

  /**
   * Binäre Suche: Durchsucht das Array nach dem Wert und gibt dessen Position als
   * Ergebnis zurück.
   *
   * @param array Ein Feld mit Zahlen.
   * @param wert Die Zahl, die gesucht werden soll.
   *
   * @return Die Indexnummer der gesuchten Zahl.
   */
  public int sucheBinaer(int[] array, int wert) {
    fenster.schreibeZeile("\nFühre die Methode sucheBinaer() aus:");
    fenster.schreibe("Suche in diesem Feld: ");
    fenster.schreibeArray(array);
    fenster.schreibeZeile("");
    int u = 0;
    int o = array.length - 1;
    fenster.schreibeZeile("Suchbereich: " + u + " bis " + o);
    while (u <= o) {
      int m = (u + o) / 2;
      if (array[m] == wert) {
        fenster.schreibe("Mitte: " + m);
        fenster.schreibeZeile(", Treffer : -) ");
        return m;
      } else if (array[m] > wert) {
        o = m - 1;
        fenster.schreibe("Mitte: " + m);
        fenster.schreibeZeile(", also neuer Bereich: " + u + " bis " + o);
      } else {
        u = m + 1;
        fenster.schreibe("Mitte: " + m);
        fenster.schreibeZeile(", also neuer Bereich: " + u + " bis " + o);
      }
    }
    fenster.schreibeZeile("Nichts gefunden: -( ");
    return -1;
  }

  public static void main(String[] args) {
    Eingabefenster fenster = new Eingabefenster();
    Suchalgorithmen suchen = new Suchalgorithmen(fenster);
    int[] array = new int[] { 3, 5, 7, 17, 42, 23 };
    suchen.sucheSequenziell(array, 17);
    suchen.sucheBinaer(array, 17);
  }
}
