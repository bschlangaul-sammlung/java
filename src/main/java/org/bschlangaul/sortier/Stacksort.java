package org.bschlangaul.sortier;

import java.util.Stack;

/**
 * Quelle: nach <a href=
 * "https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/">geeksforgeeks.org</a>
 */
class Stacksort extends Sortieralgorithmus {

  Stack<Integer> keller;

  /**
   * @param keller Der Kellerspeicher.
   *
   * @return Der sortierte Stapel.
   */
  public Stack<Integer> sortiere(Stack<Integer> keller) {
    Stack<Integer> tmpKeller = new Stack<Integer>();
    while (!keller.isEmpty()) {
      int tmp = keller.pop();

      while (!tmpKeller.isEmpty() && tmpKeller.peek() > tmp) {
        keller.push(tmpKeller.pop());
      }

      tmpKeller.push(tmp);
    }
    return tmpKeller;
  }

  public int[] sortiere() {
    keller = new Stack<Integer>();
    for (int i = 0; i < zahlen.length; i++) {
      keller.add(zahlen[i]);
    }
    Stack<Integer> ergebnisKeller = sortiere(keller);

    int i = 0;
    while (!ergebnisKeller.empty()) {
      zahlen[i] = ergebnisKeller.pop();
    }
    return zahlen;
  }

  public static void main(String args[]) {
    new Stacksort().teste();
  }
}
