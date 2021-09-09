package org.bschlangaul.examen.examen_66115.jahr_2015.herbst;

import java.util.Comparator;

public class Heapsort {


  void swap(W[] w, int a, int b) { // ...

  }

  // restores the max-heap property in w[i to k] using c
  void reheap(W[] w, Comparator<W> c, int i, int k) {
    int leftId = 2 * i + 1;
    int rightId = leftId + 1;
    int kidId;
    // ToDo: Code hier ergaenzen
  }

  // sorts w in-situ according to the order imposed by c
  void heapSort(W[] w, Comparator<W> c) {
    int n = w.length;

    // Phase 1: Max-Heap-Eigenschaft herstellen
    // (siehe Teilaufgabe a)
    // ToDo: Code hier ergaenzen

    // Phase 2: jeweils Maximum entnehmen und sortierte Liste am Ende aufbauen
    // (siehe Teilaufgabe b)
    // ToDo: Code hier ergaenzen
  }
}

// ascending order for field W.t
class ComparatorAscByFieldT implements Comparator<W> {
  // Returns a negative integer, zero, or a positive integer as the
  // first argument is less than, equal to, or greater than the second.
  @Override
  public int compare(W o1, W o2) { // ...
    return 0;
  }
}

class W {
  int t;
  String f;
  // ...
}
