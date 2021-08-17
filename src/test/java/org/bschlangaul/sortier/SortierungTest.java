package org.bschlangaul.sortier;

import static org.junit.Assert.*;
import org.junit.Test;

public class SortierungTest {

  void testeMehrere(int[] test, int[] richtig) {
    String[] algorithmen = {
        // "BinaryTree",
        "BubbleIterativ", "BubbleMinimal", "BubbleRekursiv", "Heap", "InsertionIterativ", "InsertionMinimal",
        "InsertionRekursiv", "Merge", "MergeMinimal", "QuickHorare", "QuickIterativ", "QuickMinimal", "QuickSaake",
        "SelectionHalbRekursiv", "SelectionLinksIterativ", "SelectionMinimal", "SelectionRechtsIterativ",
        "SelectionRekursiv"
        // "Stacksort",
    };

    for (String algorithmus : algorithmen) {
      int[] ergebnis = new Sortierer(algorithmus).zahlen(test.clone()).sortiere();
      assertArrayEquals(richtig, ergebnis);
    }
  }

  @Test
  public void rückwärtsSortiert() {
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
    int[] test = { 4, 7, 1, 10, 8, 3, 6, 2, 9, 5 };
    int[] richtig = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    testeMehrere(test, richtig);
  }

  @Test
  public void leeresFeld() {
    int[] test = {};
    int[] richtig = {};
    testeMehrere(test, richtig);
  }

  @Test
  public void einWert() {
    int[] test = { 4 };
    int[] richtig = { 4 };
    testeMehrere(test, richtig);
  }

  @Test
  public void negativeZahlen() {
    int[] test = { -5, 5, -26, 42, 8, 78, -1, 0, -74 };
    int[] richtig = { -74, -26, -5, -1, 0, 5, 8, 42, 78 };
    testeMehrere(test, richtig);
  }

  @Test
  public void nurNegativeZahlen() {
    int[] test = { -1, -2, -3, -4, -5, -6, -7, -8, -9, -10 };
    int[] richtig = { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1 };
    testeMehrere(test, richtig);
  }

  @Test
  public void doppeleZahlen() {
    int[] test = { 0, 0, -10, -10, 5, 5 };
    int[] richtig = { -10, -10, 0, 0, 5, 5 };
    testeMehrere(test, richtig);
  }
}
