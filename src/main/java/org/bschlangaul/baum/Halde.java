package org.bschlangaul.baum;

import java.util.Arrays;

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
  private static final int STANDARD_KAPAZITÄT = 10;

  private T[] halde;
  private int füllstand;

  /**
   * Der aktuelle Füllstand der Halde. Er wird hochgezählt, wenn ein neuer
   * Schlüsselwert eingefügt wird und er wird erniedrigt, wenn ein Schlüsselwert
   * entnommen wird.
   */
  private HaldenTyp typ;

  /**
   * Default Constructor
   * <p>
   * default capacity of 9 (0 index is not used)
   * <p>
   * default type of heap is min
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
   * adds a generic type T to heap
   * <p>
   * percolates down the tree
   *
   * @param value type T value
   */
  public void fügeEin(T value) {
    // resize if needed
    if (this.füllstand >= halde.length - 1) {
      halde = this.resize();
    }

    füllstand++;
    halde[füllstand] = value;
    steigeAuf();
  }

  /**
   * Removes min or max item from heap
   * <p>
   * re-heapifies
   *
   * @return value of T that is minimum or maximum value in heap
   */
  public T remove() {
    T result = peek();

    vertausche(1, füllstand);
    halde[füllstand] = null;
    füllstand--;

    versickere();

    return result;
  }

  /**
   * Remove specific object from heap
   *
   * @param value type T
   * @return true if found and removed
   */
  public boolean remove(T value) {
    for (int i = 0; i < halde.length; i++) {
      if (value.equals(halde[i])) {
        System.out.println(i);
        vertausche(i, füllstand);
        halde[füllstand] = null;
        füllstand--;
        // bubbleUp();
        versickere();
        return true;
      }
    }
    return false;
  }

  /**
   * Removes min or max item from heap same as <code>remove()</code> but does not
   * throw exception on empty
   * <p>
   * re-heapifies
   *
   * @return value of T that is minimum or maximum value in heap; or
   *         <code>null</code> if empty
   */
  public T poll() {
    if (isEmpty())
      return null;

    T result = peek();

    vertausche(1, füllstand);
    halde[füllstand] = null;
    füllstand--;

    versickere();

    return result;
  }

  /**
   * Checks if heap is empty
   *
   * @return <code>true</code> if empty
   */
  public boolean isEmpty() {
    return füllstand == 0;
  }

  /**
   * returns min/max value without removing it
   *
   * @return value type T
   * @throws IllegalStateException if empty
   */
  public T peek() {
    if (isEmpty())
      throw new IllegalStateException();
    return halde[1];
  }

  /**
   * Length/size of heap
   *
   * @return int size of heap
   */
  public int length() {
    return füllstand;
  }

  /**
   * Add DEFAULT_CAPACITY to length of <code>heap</code> array
   *
   * @return new array of type T
   */
  private T[] resize() {
    // add 10 to array capacity
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
        // find smaller of child values
        int smaller = gibIndexLinks(index);
        if (hatRechts(index) && halde[gibIndexLinks(index)].compareTo(halde[gibIndexRechts(index)]) > 0) {
          smaller = gibIndexRechts(index);
        }
        if (halde[index].compareTo(halde[smaller]) > 0) {
          vertausche(index, smaller);
        } else
          break;
        index = smaller;
      }
    } else {
      while (hatLinks(index)) {
        // find larger of child values
        int larger = gibIndexLinks(index);
        if (hatRechts(index) && halde[gibIndexLinks(index)].compareTo(halde[gibIndexRechts(index)]) < 0) {
          larger = gibIndexRechts(index);
        }
        if (halde[index].compareTo(halde[larger]) < 0) {
          vertausche(index, larger);
        } else
          break;
        index = larger;
      }
    }
  }

  /**
   * if child has a parent
   *
   * @param index integer - index
   * @return true if index > 1
   */
  private boolean hatEltern(int index) {
    return index > 1;
  }

  /**
   * Get left index mathematically
   *
   * @param index index
   * @return index of left node from index i
   */
  private int gibIndexLinks(int index) {
    return index * 2;
  }

  /**
   * Get right index mathematically
   *
   * @param index index
   * @return index of right node from index i
   */
  private int gibIndexRechts(int index) {
    return index * 2 + 1;
  }

  /**
   * Test to see if node has left child
   *
   * @param index index
   *
   * @return true if it does
   */
  private boolean hatLinks(int index) {
    return gibIndexLinks(index) <= füllstand;
  }

  /**
   * Test to see if node has right child
   *
   * @param index index
   *
   * @return true if it does
   */
  private boolean hatRechts(int index) {
    return gibIndexRechts(index) <= füllstand;
  }

  /**
   * get index of parent from child node
   *
   * @param index index
   *
   * @return index of parent
   */
  private int gibIndexEltern(int index) {
    return index / 2;
  }

  /**
   * get parent value
   *
   * @param index index
   *
   * @return value of type T
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
   * gelten. Deshalb schummeln wir hier einen Baum, damit wir ihn darstellen
   * können.
   *
   * @return Ein Repräsentation als Binärbaum
   */
  public BinaerBaum gibBinaerBaum() {
    BinaererSuchBaum baum = new BinaererSuchBaum();

    T[] haldenFeld = gibHaldenFeld();

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

}
