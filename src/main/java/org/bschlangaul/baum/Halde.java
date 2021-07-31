package org.bschlangaul.baum;

import java.util.Arrays;

import org.bschlangaul.baum.visualisierung.BaumReporter;
import org.bschlangaul.baum.visualisierung.StummerBaumReporter;

enum HaldenTyp {
  MIN, MAX
}

@SuppressWarnings("unchecked")

/**
 * Feld-Implementation einer Halde (nach
 * <a href="https://gist.github.com/snarkbait/86c7a4bc743e8f327dbc">Philboyd
 * Studge</a>
 */
public class Halde<T extends Comparable<T>> {

  public BaumReporter reporter = new StummerBaumReporter();

  private static final int STANDARD_KAPAZITÄT = 10;

  private T[] halde;

  /**
   * Der aktuelle Füllstand der Halde. Er wird hochgezählt, wenn ein neuer
   * Schlüsselwert eingefügt, und erniedrigt, wenn ein Schlüsselwert entnommen
   * wird.
   */
  private int füllstand;

  /**
   * Ob es sich um eine Min-Halde oder eine Max-Halde handelt.
   */
  private HaldenTyp typ;

  /**
   * Der Standard-Konstruktor.
   *
   * @param typ Ob es sich um eine Min-Halde oder eine Max-Halde handeln soll.
   */
  public Halde(HaldenTyp typ) {
    halde = (T[]) new Comparable[STANDARD_KAPAZITÄT];
    füllstand = 0;
    this.typ = typ;
  }

  /**
   * Gib das Feld (Array) zurück, das die Schlüsselwerte der Halde speichert.
   *
   * @return Das Feld (Array) mit den Schlüsselwerten.
   */
  public T[] gibHaldenFeld() {
    return Arrays.copyOfRange(halde, 1, füllstand + 1);
  }

  /**
   * Füge einen Wert in die Halde ein.
   *
   * @param wert Der Wert, der eingefügt werden soll.
   */
  public void fügeEin(T wert) {
    reporter.berichteÜberschrift("Nach dem Einfügen von „" + wert + "“", 0);
    if (this.füllstand >= halde.length - 1) {
      halde = this.vergrößern();
    }
    füllstand++;
    halde[füllstand] = wert;
    steigeAuf();
    berichteBaum(0);
  }

  /**
   * Entfernt den minimalen oder maximalen Wert aus der Halde.
   *
   * @return Der minimale oder maximale Wert.
   */
  public T entferne() {
    T ergebnis = gucke();
    vertausche(1, füllstand);
    halde[füllstand] = null;
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
    for (int i = 0; i < halde.length; i++) {
      if (wert.equals(halde[i])) {
        reporter.berichteÜberschrift("Nach dem Entfernen von „" + wert + "“", 0);
        vertausche(i, füllstand);
        halde[füllstand] = null;
        füllstand--;
        versickere();
        berichteBaum(0);
        return true;
      }
    }
    return false;
  }

  /**
   * Entferne den minimalen oder maximalen Wert aus der Halde, werfe aber im
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
    halde[füllstand] = null;
    füllstand--;
    versickere();
    return ergebnis;
  }

  /**
   * Überprüft, ob die Halde leer ist.
   *
   * @return <code>true</code> if empty
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
    return halde[1];
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
    return Arrays.copyOf(halde, halde.length + STANDARD_KAPAZITÄT);
  }

  /**
   * Die Methode wird oft „bubbleUp“ genannt.
   */
  private void steigeAuf() {
    int index = füllstand;
    if (typ == HaldenTyp.MIN) {
      while (hatEltern(index) && (gibElternSchlüssel(index).compareTo(halde[index]) > 0)) {
        vertausche(index, gibIndexEltern(index));
        index = gibIndexEltern(index);
      }
    } else {
      while (hatEltern(index) && (gibElternSchlüssel(index).compareTo(halde[index]) < 0)) {
        vertausche(index, gibIndexEltern(index));
        index = gibIndexEltern(index);
      }
    }
  }

  /**
   * Die Methode wird oft „percolate“ oder „bubbleDown“ genannt.
   */
  private void versickere() {
    int index = 1;
    if (typ == HaldenTyp.MIN) {
      while (hatLinks(index)) {
        int kleiner = gibIndexLinks(index);
        if (hatRechts(index) && halde[gibIndexLinks(index)].compareTo(halde[gibIndexRechts(index)]) > 0) {
          kleiner = gibIndexRechts(index);
        }
        if (halde[index].compareTo(halde[kleiner]) > 0) {
          vertausche(index, kleiner);
        } else
          break;
        index = kleiner;
      }
    } else {
      while (hatLinks(index)) {
        int größer = gibIndexLinks(index);
        if (hatRechts(index) && halde[gibIndexLinks(index)].compareTo(halde[gibIndexRechts(index)]) < 0) {
          größer = gibIndexRechts(index);
        }
        if (halde[index].compareTo(halde[größer]) < 0) {
          vertausche(index, größer);
        } else
          break;
        index = größer;
      }
    }
  }

  private T gibSchlüssel(int index) {
    return halde[index];
  }

  /**
   * Überprüfe ob der Knoten einen Elternknoten hat.
   *
   * @param index Die Index-Nummer des Knoten.
   *
   * @return Wahr wenn der Knoten einen Elternknoten hat.
   */
  private boolean hatEltern(int index) {
    return index > 1;
  }

  /**
   * Berechne die Index-Nummer des linken Kindknotens.
   *
   * @param index Die Index-Nummer des Knoten.
   *
   * @return Die Index-Nummer des linken Kindknotens.
   */
  private int gibIndexLinks(int index) {
    return index * 2;
  }

  /**
   * Berechne die Index-Nummer des rechten Kindknotens.
   *
   * @param index Die Index-Nummer des Knoten.
   *
   * @return Die Index-Nummer des rechten Kindknotens.
   */
  private int gibIndexRechts(int index) {
    return index * 2 + 1;
  }

  /**
   * Berechne die Index-Nummer des Elternknoten.
   *
   * @param index Die Index-Nummer des Knoten.
   *
   * @return Die Index-Nummer des Elternknoten.
   */
  private int gibIndexEltern(int index) {
    return index / 2;
  }

  /**
   * Überprüfe, ob der Knoten einen linken Kindknoten hat.
   *
   * @param index Die Index-Nummer des Knoten.
   *
   * @return Wahr, wenn der Knoten ein linkes Kind hat.
   */
  private boolean hatLinks(int index) {
    return gibIndexLinks(index) <= füllstand;
  }

  /**
   * Überprüfe, ob der Knoten einen rechten Kindknoten hat.
   *
   * @param index Die Index-Nummer des Knoten.
   *
   * @return Wahr, wenn der Knoten ein rechtes Kind hat.
   */
  private boolean hatRechts(int index) {
    return gibIndexRechts(index) <= füllstand;
  }

  /**
   * get parent value
   *
   * @param index Die Index-Nummer des Knoten.
   *
   * @return Der Schlüsselwert des Elternknoten.
   */
  private T gibElternSchlüssel(int index) {
    return halde[gibIndexEltern(index)];
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
    T schlüssel1 = gibSchlüssel(index1);
    T schlüssel2 = gibSchlüssel(index2);
    berichteBaum(String.format("Nach Vertauschen von „%s“ und „%s“", schlüssel1, schlüssel2), 1);

    T tmp = halde[index1];
    halde[index1] = halde[index2];
    halde[index2] = tmp;
  }

  /**
   * Konvertiere die Halde zu Text.
   *
   * @return Alle Werte der Halde als Text mit Kommas zusammengehängt.
   */
  @Override
  public String toString() {
    String ausgabe = "";
    for (T schlüssel : gibHaldenFeld()) {
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

    T[] haldenFeld = gibHaldenFeld();

    berichteHaldenFeldAlsTabelle(haldenFeld);

    BaumKnoten[] knoten = new BaumKnoten[haldenFeld.length];
    for (int i = 0; i < haldenFeld.length; i++) {
      knoten[i] = new BaumKnoten(haldenFeld[i]);
    }

    for (int i = 0; i < haldenFeld.length; i++) {
      BaumKnoten k = knoten[i];
      // Die Halde setzt den ersten Wert auf das 2. Element des Feldes.
      if (hatLinks(i + 1))
        k.setzeLinks(knoten[gibIndexLinks(i + 1) - 1]);
      if (hatRechts(i + 1))
        k.setzeRechts(knoten[gibIndexRechts(i + 1) - 1]);
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
