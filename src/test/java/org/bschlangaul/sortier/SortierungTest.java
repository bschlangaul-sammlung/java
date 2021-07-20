package org.bschlangaul.sortier;

import static org.junit.Assert.*;
import org.junit.Test;

public class SortierungTest {

  void testeMehrere(int[] test, int[] richtig) {
    assertArrayEquals(BubbleSort.sortiere(test.clone()), richtig);
    assertArrayEquals(BubbleSort.sortiereRekursiv(test.clone()), richtig);
    assertArrayEquals(HeapSort.sortiere(test.clone()), richtig);
    assertArrayEquals(InsertionSort.sortiere(test.clone()), richtig);
    assertArrayEquals(InsertionSort.sortiereRekursiv(test.clone()), richtig);
    assertArrayEquals(MergeSort.sortiere(test.clone()), richtig);
    assertArrayEquals(QuickSort.sortiere(test.clone()), richtig);
    assertArrayEquals(QuickSortDoWhile.sortiere(test.clone()), richtig);
    assertArrayEquals(SelectionSort.sortiereHalbRekursiv(test.clone()), richtig);
    assertArrayEquals(SelectionSort.sortiereIterativ(test.clone()), richtig);
    assertArrayEquals(SelectionSort.sortiereRekursiv(test.clone()), richtig);

    int[] tmp;
    tmp = test.clone();
    Sammlung.bubblesort(tmp);
    assertArrayEquals(tmp, richtig);

    tmp = test.clone();
    Sammlung.insertionsort(tmp);
    assertArrayEquals(tmp, richtig);

    tmp = test.clone();
    Sammlung.selectionsort(tmp);
    assertArrayEquals(tmp, richtig);

    tmp = test.clone();
    Sammlung.selectionsortMin(tmp);
    assertArrayEquals(tmp, richtig);

    tmp = test.clone();
    Sammlung.selectionsortFor(tmp);
    assertArrayEquals(tmp, richtig);

    tmp = test.clone();
    Sammlung.mergesort(tmp);
    assertArrayEquals(tmp, richtig);

    tmp = test.clone();
    Sammlung.quicksort(tmp);
    assertArrayEquals(tmp, richtig);
  }

  @Test
  public void rückwärtsSortiert() {
    // reverse sorted
    int[] test = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    int[] richtig = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    testeMehrere(test, richtig);
  }

  @Test
  public void bereitsSortiert() {
    int[] test = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int[] richtig = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    testeMehrere(test, richtig);
  }

  @Test
  public void zufülligeSortierung() {
    // random array
    int[] test = { 4, 7, 1, 10, 8, 3, 6, 2, 9, 5 };
    int[] richtig = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    testeMehrere(test, richtig);
  }

  @Test
  public void leeresFeld() {
    // empty array
    int[] test = {};
    int[] richtig = {};
    testeMehrere(test, richtig);
  }

  @Test
  public void einWert() {
    // array with one entry
    int[] test = { 4 };
    int[] richtig = { 4 };
    testeMehrere(test, richtig);
  }

  @Test
  public void negativeZahlen() {
    // random array with negativ numers
    int[] test = { -5, 5, -26, 42, 8, 78, -1, 0, -74 };
    int[] richtig = { -74, -26, -5, -1, 0, 5, 8, 42, 78 };
    testeMehrere(test, richtig);
  }
}
