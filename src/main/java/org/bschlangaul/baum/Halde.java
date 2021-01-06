package org.bschlangaul.baum;

enum HaldenTyp {
  MIN, MAX
}

/**
 * Feld-Implementation einer minimalen Halde (nach
 * <a href="https://codegym.cc/groups/posts/min-heap-in-java">codegym.cc</a>)
 *
 * https://gist.github.com/snarkbait/86c7a4bc743e8f327dbc
 */
public class Halde {
  @SuppressWarnings({ "rawtypes" })
  private Comparable[] halde;

  /**
   * Der aktuelle Füllstand der Halde. Er wird hochgezählt, wenn ein neuer
   * Schlüsselwert eingefügt wird und er wird erniedrigt, wenn ein Schlüsselwert
   * entnommen wird.
   */
  private int füllstand;
  private int kapazität;

  private HaldenTyp typ;

  public Halde(HaldenTyp typ, int kapazität) {
    this.typ = typ;
    this.kapazität = kapazität;
    füllstand = 0;
    halde = new Comparable[kapazität];
  }

  /**
   * Gib das Feld (Array) zurück, das die Schlüsselwerte der Halde speichert.
   *
   * @return Das Feld (Array) mit den Schlüsselwerten.
   */
  @SuppressWarnings({ "rawtypes" })
  public Comparable[] gibHaldenFeld() {
    return halde;
  }

  private int gibIndexEltern(int index) {
    return (index - 1) / 2;
  }

  private int gibIndexLinks(int index) {
    return (index * 2) + 1;
  }

  private int gibIndexRechts(int index) {
    return (index * 2) + 2;
  }

  private boolean istBlatt(int index) {
    if (gibIndexRechts(index) >= kapazität || gibIndexLinks(index) >= kapazität) {
      return true;
    }
    return false;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public boolean fügeEin(Comparable schlüssel) {
    if (füllstand >= kapazität) {
      return false;
    }
    halde[füllstand] = schlüssel;
    int aktuellerIndex = füllstand;

    while (halde[aktuellerIndex].compareTo(halde[gibIndexEltern(aktuellerIndex)]) < 0) {
      vertausche(aktuellerIndex, gibIndexEltern(aktuellerIndex));
      aktuellerIndex = gibIndexEltern(aktuellerIndex);
    }
    füllstand++;
    return true;
  }

  /**
   * Füge mehrere Schlüssel auf einmal ein.
   *
   * @param schlüssel Mehrere Schlüssel.
   *
   * @return Wahr, wenn das Einfügen erfolgreich war, d. h. alle Schlüssel
   *         eingefügt werden konnten. Konnte ein Schlüssel nicht eingefügt
   *         werden, wird falsch zurück gegeben.
   */
  @SuppressWarnings({ "rawtypes" })
  public boolean fügeEin(Comparable... schlüssel) {
    boolean ergebnis = true;
    boolean tmp;
    for (Comparable s : schlüssel) {
      tmp = fügeEin(s);
      if (!tmp) {
        ergebnis = false;
      }
    }
    return ergebnis;
  }

  // removes and returns the minimum element from the heap
  @SuppressWarnings({ "rawtypes" })
  public Comparable entferne() {
    // since its a min heap, so root = minimum
    Comparable popped = halde[0];
    halde[0] = halde[--füllstand];
    haldefiziere(0);
    return popped;
  }

  /**
   * Haldefiziere (heapify), d. h. stelle die Haldeneigenschaften am Knoten mit
   * der gegebenen Index-Nummer wieder her.
   *
   * @param index Index-Position im Feld, für dessen Knoten die
   *              Haldeneigenschaften wiederhergestellt werden soll.
   */
  private void haldefiziere(int index) {
    // Falls der Knoten kein Blattknoten ist und eins der beiden Kinder kleiner ist.
    if (!istBlatt(index)) {
      if (vergleiche(halde[index], halde[gibIndexLinks(index)])
          || vergleiche(halde[index], halde[gibIndexRechts(index)])) {
        if (!vergleiche(halde[gibIndexLinks(index)], halde[gibIndexRechts(index)])) {
          vertausche(index, gibIndexLinks(index));
          haldefiziere(gibIndexLinks(index));
        } else {
          vertausche(index, gibIndexRechts(index));
          haldefiziere(gibIndexRechts(index));
        }
      }
    }
  }

  /**
   * Damit wir gleichzeitig eine Min als auch eine Max-Halde implemenierten
   * könnten wird der vergleich in eine Methode ausgelagert.
   *
   * @param schlüssel1 Der erste Schlüsselwert, der verglichen werden soll.
   * @param schlüssel2 Der zweite Schlüsselwert, der verglichen werden soll.
   *
   * @return Bei der Min-Halde wahr, wenn der erste Schlüsselwert größer ist als
   *         der zweite Schlüsselwert, sonst falsch. Bei der Max-Halde wahr, wenn
   *         der erste Schlüsselwert kleiner ist als der zweite Schlüsselwert,
   *         sonst falsch.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private boolean vergleiche(Comparable schlüssel1, Comparable schlüssel2) {
    if (typ == HaldenTyp.MIN)
      return schlüssel1.compareTo(schlüssel1) > 0;
    else
      return schlüssel1.compareTo(schlüssel1) < 0;
  }

  /**
   * Vertausche die Schlüsselwerte im Feld {@link halde}.
   *
   * @param index1 Die Indexnummer des ersten Schlüsselwertes, der vertauscht
   *               werden soll.
   * @param index2 Die Indexnummer des zweiten Schlüsselwertes, der vertauscht
   *               werden soll.
   */
  @SuppressWarnings({ "rawtypes" })
  private void vertausche(int index1, int index2) {
    Comparable tmp;
    tmp = halde[index1];
    halde[index1] = halde[index2];
    halde[index2] = tmp;
  }

}
