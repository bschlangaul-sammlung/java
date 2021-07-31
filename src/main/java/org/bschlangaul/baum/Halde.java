package org.bschlangaul.baum;

import java.util.Arrays;

import org.bschlangaul.baum.visualisierung.BaumReporter;
import org.bschlangaul.baum.visualisierung.StummerBaumReporter;

enum HaldenTyp {
  MIN, MAX
}

/**
 * Feld-Implementation einer Halde (nach
 * <a href="https://gist.github.com/snarkbait/86c7a4bc743e8f327dbc">Philboyd
 * Studge</a>
 */
@SuppressWarnings("unchecked")
public class Halde<T extends Comparable<T>> {

  public BaumReporter reporter = new StummerBaumReporter();

  private static final int STANDARD_KAPAZITÄT = 10;

  /**
   * Der Vergleichsfaktor wird zu den Vergleichsoperationen mit .compareTo()
   * multipliziert. Bei einer Min-Halde ist er 1, bei einer Max-Halde ist er -1.
   */
  private int vergleichsFaktor;

  /**
   * In diesem Feld wird die Halde gespeichert.
   */
  private T[] feld;

  /**
   * Der aktuelle Füllstand der Halde. Er wird hochgezählt, wenn ein neuer
   * Schlüsselwert eingefügt, und erniedrigt, wenn ein Schlüsselwert entnommen
   * wird.
   */
  private int füllstand;

  /**
   * Der Standard-Konstruktor.
   *
   * @param typ Ob es sich um eine Min-Halde oder eine Max-Halde handeln soll.
   */
  public Halde(HaldenTyp typ) {
    feld = (T[]) new Comparable[STANDARD_KAPAZITÄT];
    füllstand = 0;
    vergleichsFaktor = typ == HaldenTyp.MIN ? 1 : -1;
  }

  /**
   * Gib das Feld (Array) zurück, das die Schlüsselwerte der Halde speichert.
   *
   * @return Das Feld (Array) mit den Schlüsselwerten.
   */
  public T[] gibFeld() {
    return Arrays.copyOfRange(feld, 1, füllstand + 1);
  }

  /**
   * Füge einen Wert in die Halde ein.
   *
   * @param wert Der Wert, der eingefügt werden soll.
   */
  public void fügeEin(T wert) {
    reporter.berichteÜberschrift("Nach dem Einfügen von „" + wert + "“", 0);
    if (this.füllstand >= feld.length - 1) {
      feld = this.vergrößern();
    }
    füllstand++;
    feld[füllstand] = wert;
    steigeAuf();
    berichteBaum(0);
  }

  /**
   * Füge mehrere Werte auf einmal in die Halde ein.
   *
   * @param werte Der Wert, der eingefügt werden soll.
   */
  public void fügeEin(T... werte) {
    for (T wert : werte) {
      fügeEin(wert);
    }
  }

  /**
   * Entfernt den minimalen oder maximalen Wert aus der Halde.
   *
   * @return Der minimale oder maximale Wert.
   */
  public T entferne() {
    T ergebnis = gucke();
    vertausche(1, füllstand);
    feld[füllstand] = null;
    füllstand--;
    versickere();
    return ergebnis;
  }

  /**
   * Entferne einen bestimmten Wert aus der Halde.
   *
   * @param wert Der Wert, der entfernt werden soll.
   *
   * @return Wahr, wenn der Wert gefunden und entfernt wurde, sonst falsch.
   */
  public boolean entferne(T wert) {
    for (int i = 0; i < feld.length; i++) {
      if (wert.equals(feld[i])) {
        reporter.berichteÜberschrift("Nach dem Entfernen von „" + wert + "“", 0);
        vertausche(i, füllstand);
        feld[füllstand] = null;
        füllstand--;
        versickere(i);
        berichteBaum(0);
        return true;
      }
    }
    return false;
  }

  /**
   * Entferne den minimalen oder maximalen Wert aus der Halde. Werfe aber im
   * Gegensatz zu <code>entferne()</code> keine Ausnahme.
   *
   * Heißt im Engischen of „poll“.
   *
   * @return Der minimale oder maximale Wert oder null.
   */
  public T köpfe() {
    if (istLeer()) {
      return null;
    }
    T ergebnis = gucke();
    vertausche(1, füllstand);
    feld[füllstand] = null;
    füllstand--;
    versickere();
    return ergebnis;
  }

  /**
   * Überprüfe, ob die Halde leer ist.
   *
   * @return Wahr, wenn die Halde leer ist, sonst falsch.
   */
  public boolean istLeer() {
    return füllstand == 0;
  }

  /**
   * Gib den minimalen oder maximalen Wert, ohne ihn aus der Halde zu entfernen.
   *
   * Heißt im Englischen oft „peek“
   *
   * @return Der minimale oder maximale Wert.
   *
   * @throws IllegalStateException falls die Halde leer ist.
   */
  public T gucke() {
    if (istLeer()) {
      throw new IllegalStateException();
    }
    return feld[1];
  }

  /**
   * Gib die Größe der Halde, d. h. die Anzahl der Haldenelemente.
   *
   * @return Die Größe der Halde, d. h. die Anzahl der Haldenelemente.
   */
  public int größe() {
    return füllstand;
  }

  /**
   * Vergrößere das Feld, in dem die Halde gespeichert ist, um die
   * Standardkapazität.
   *
   * @return Ein neues, vergrößertes Feld.
   */
  private T[] vergrößern() {
    return Arrays.copyOf(feld, feld.length + STANDARD_KAPAZITÄT);
  }

  /**
   * Die Methode wird oft „bubbleUp“ genannt.
   */
  private void steigeAuf() {
    int i = füllstand;
    while (hatEltern(i) && (gibElternWert(i).compareTo(feld[i]) * vergleichsFaktor > 0)) {
      vertausche(i, gibEltern(i));
      i = gibEltern(i);
    }
  }

  /**
   * Die Methode wird oft „percolate“ oder „bubbleDown“ genannt.
   */
  private void versickere() {
    versickere(1);
  }

  /**
   * Die Methode wird oft „percolate“ oder „bubbleDown“ genannt.
   *
   * @param i Die Index-Nummer des Werts, der versickert werden soll.
   */
  private void versickere(int i) {
    while (hatLinks(i)) {
      int j = gibLinks(i);
      if (hatRechts(i) && feld[gibLinks(i)].compareTo(feld[gibRechts(i)]) * vergleichsFaktor > 0) {
        j = gibRechts(i);
      }
      if (feld[i].compareTo(feld[j]) * vergleichsFaktor > 0) {
        vertausche(i, j);
      } else {
        break;
      }
      i = j;
    }
  }

  private T gibWert(int i) {
    return feld[i];
  }

  /**
   * Überprüfe ob der Knoten einen Elternknoten hat.
   *
   * @param i Die Index-Nummer des Knoten.
   *
   * @return Wahr wenn der Knoten einen Elternknoten hat.
   */
  private boolean hatEltern(int i) {
    return i > 1;
  }

  /**
   * Berechne die Index-Nummer des linken Kindknotens.
   *
   * @param i Die Index-Nummer des Knoten.
   *
   * @return Die Index-Nummer des linken Kindknotens.
   */
  private int gibLinks(int i) {
    return i * 2;
  }

  /**
   * Berechne die Index-Nummer des rechten Kindknotens.
   *
   * @param i Die Index-Nummer des Knoten.
   *
   * @return Die Index-Nummer des rechten Kindknotens.
   */
  private int gibRechts(int i) {
    return i * 2 + 1;
  }

  /**
   * Berechne die Index-Nummer des Elternknoten.
   *
   * @param i Die Index-Nummer des Knoten.
   *
   * @return Die Index-Nummer des Elternknoten.
   */
  private int gibEltern(int i) {
    return i / 2;
  }

  /**
   * Überprüfe, ob der Knoten einen linken Kindknoten hat.
   *
   * @param i Die Index-Nummer des Knoten.
   *
   * @return Wahr, wenn der Knoten ein linkes Kind hat.
   */
  private boolean hatLinks(int i) {
    return gibLinks(i) <= füllstand;
  }

  /**
   * Überprüfe, ob der Knoten einen rechten Kindknoten hat.
   *
   * @param i Die Index-Nummer des Knoten.
   *
   * @return Wahr, wenn der Knoten ein rechtes Kind hat.
   */
  private boolean hatRechts(int i) {
    return gibRechts(i) <= füllstand;
  }

  /**
   * get parent value
   *
   * @param i Die Index-Nummer des Knoten.
   *
   * @return Der Schlüsselwert des Elternknoten.
   */
  private T gibElternWert(int i) {
    return feld[gibEltern(i)];
  }

  /**
   * Vertausche die Schlüsselwerte im Feld {@link halde}.
   *
   * @param index1 Die Indexnummer des ersten Schlüsselwertes, der vertauscht
   *               werden soll.
   * @param index2 Die Indexnummer des zweiten Schlüsselwertes, der vertauscht
   *               werden soll.
   */
  private void vertausche(int index1, int index2) {
    T wert1 = gibWert(index1);
    T wert2 = gibWert(index2);
    berichteBaum(String.format("Nach Vertauschen von „%s“ und „%s“", wert1, wert2), 1);

    T tmp = feld[index1];
    feld[index1] = feld[index2];
    feld[index2] = tmp;
  }

  /**
   * Konvertiere die Halde zu Text.
   *
   * @return Alle Werte der Halde als Text mit Kommas zusammengehängt.
   */
  @Override
  public String toString() {
    String ausgabe = "";
    for (T schlüssel : gibFeld()) {
      ausgabe += schlüssel + ", ";
    }
    return ausgabe;
  }

  /**
   * Exportiere die Halde als Binärbaum. Hier kann kein „normaler“ binärer
   * Suchbaum verwendet werden, da in diesem Baum ganz andere Einfügeregeln
   * gelten. Deshalb schummeln wir etwas und erzeugen einen Baum, indem wir die
   * Knoten einzeln erzeugen und zu einem Baum zusammenfügen.
   *
   * @return Ein Repräsentation als Binärbaum
   */
  public BinaerBaum gibBinaerBaum() {
    BinaererSuchBaum baum = new BinaererSuchBaum();

    T[] haldenFeld = gibFeld();

    berichteHaldenFeldAlsTabelle(haldenFeld);

    BaumKnoten[] knoten = new BaumKnoten[haldenFeld.length];
    for (int i = 0; i < haldenFeld.length; i++) {
      knoten[i] = new BaumKnoten(haldenFeld[i]);
    }

    for (int i = 0; i < haldenFeld.length; i++) {
      BaumKnoten k = knoten[i];
      // Die Halde setzt den ersten Wert auf das 2. Element des Feldes.
      if (hatLinks(i + 1))
        k.setzeLinks(knoten[gibLinks(i + 1) - 1]);
      if (hatRechts(i + 1))
        k.setzeRechts(knoten[gibRechts(i + 1) - 1]);
    }
    // Der erste Knoten ist auf rechts gesetzt.
    baum.kopf.setzeRechts(knoten[0]);
    return baum;
  }

  private void berichteHaldenFeldAlsTabelle(T[] haldenFeld) {
    String[] kopfZeile = new String[haldenFeld.length];
    for (int i = 0; i < haldenFeld.length; i++) {
      kopfZeile[i] = String.valueOf(i);
    }

    String[][] zeilen = new String[1][haldenFeld.length];

    for (int i = 0; i < haldenFeld.length; i++) {
      zeilen[0][i] = String.valueOf(haldenFeld[i]);
    }

    reporter.berichteTabelle(kopfZeile, zeilen);
  }

  public void berichteBaum(int redselig) {
    BinaerBaum haldenBaum = gibBinaerBaum();
    reporter.berichteBaum(haldenBaum, redselig);
  }

  public void berichteBaum(String überschrift, int redselig) {
    BinaerBaum haldenBaum = gibBinaerBaum();
    reporter.berichteBaum(haldenBaum, überschrift, redselig);
  }

}
