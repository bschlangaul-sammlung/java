package org.bschlangaul.baum;

import java.util.Arrays;

/**
 * Feld-Implementation einer minimalen Halde (nach
 * <a href="https://codegym.cc/groups/posts/min-heap-in-java">codegym.cc</a>)
 */
public class MinimaleHalde {
  @SuppressWarnings({ "rawtypes" })
  private Comparable[] halde;

  /**
   * Der aktuelle Füllstand der Halde. Er wird hochgezählt, wenn ein neuer
   * Schlüsselwert eingefügt wird und er wird erniedrigt, wenn ein Schlüsselwert
   * entnommen wird.
   */
  private int füllstand;
  private int kapazität;

  public MinimaleHalde(int kapazität) {
    this.kapazität = kapazität;
    füllstand = 0;
    halde = new Comparable[kapazität];
  }

  @SuppressWarnings({ "rawtypes" })
  public Comparable[] gibHaldenFeld() {
    return halde;
  }

  private int gibIndexEltern(int index) {
    return (index - 1) / 2;
  }

  private int gibIndexLinkesKind(int index) {
    return (index * 2) + 1;
  }

  private int gibIndexRechtesKind(int index) {
    return (index * 2) + 2;
  }

  private boolean istBlatt(int index) {
    if (gibIndexRechtesKind(index) >= kapazität || gibIndexLinkesKind(index) >= kapazität) {
      return true;
    }
    return false;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void fügeEin(Comparable schlüssel) {
    if (füllstand >= kapazität) {
      return;
    }
    halde[füllstand] = schlüssel;
    int current = füllstand;

    while (halde[current].compareTo(halde[gibIndexEltern(current)]) < 0) {
      vertausche(current, gibIndexEltern(current));
      current = gibIndexEltern(current);
    }
    füllstand++;
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
  @SuppressWarnings({ "unchecked" })
  private void haldefiziere(int index) {
    // Falls der Knoten kein Blattknoten ist und eins der beiden Kinder kleiner ist.
    if (!istBlatt(index)) {
      if (halde[index].compareTo(halde[gibIndexLinkesKind(index)]) > 0
          || halde[index].compareTo(halde[gibIndexRechtesKind(index)]) > 0) {
        if (halde[gibIndexLinkesKind(index)].compareTo(halde[gibIndexRechtesKind(index)]) < 0) {
          vertausche(index, gibIndexLinkesKind(index));
          haldefiziere(gibIndexLinkesKind(index));
        } else {
          vertausche(index, gibIndexRechtesKind(index));
          haldefiziere(gibIndexRechtesKind(index));
        }
      }
    }
  }

  // builds the min-heap using the minHeapify
  public void minHeap() {
    for (int i = (füllstand - 1 / 2); i >= 1; i--) {
      haldefiziere(i);
    }
  }

  // Function to print the contents of the heap
  public void printHeap() {
    for (int i = 0; i < (füllstand / 2); i++) {
      System.out.print("Parent : " + halde[i]);
      if (gibIndexLinkesKind(i) < füllstand)
        System.out.print(" Left : " + halde[gibIndexLinkesKind(i)]);
      if (gibIndexRechtesKind(i) < füllstand)
        System.out.print(" Right :" + halde[gibIndexRechtesKind(i)]);
      System.out.println();
    }
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

  public static void main(String[] arg) {
    MinimaleHalde minHeap = new MinimaleHalde(7);
    minHeap.fügeEin(3);
    minHeap.fügeEin(13);
    minHeap.fügeEin(7);
    minHeap.fügeEin(16);
    minHeap.fügeEin(21);
    minHeap.fügeEin(12);
    minHeap.fügeEin(9);
    minHeap.minHeap();

    System.out.println("The Min Heap is : " + Arrays.toString(minHeap.halde));
    minHeap.printHeap();
    System.out.println("\nThe Min Value is : " + minHeap.entferne());
    System.out.println("\nThe Min Heap is :" + Arrays.toString(minHeap.halde));
    minHeap.printHeap();
  }
}
