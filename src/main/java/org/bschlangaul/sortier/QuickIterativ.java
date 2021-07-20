package org.bschlangaul.sortier;

/**
 * Nach dem Pseudocode Staatsexamen/66115/2019/09/Thema-1/Aufgabe-5.tex
 */
public class QuickIterativ extends Sortieralgorithmus {

  private int[] sortiereIterativ(int links, int rechts) {
    int i = links;
    int j = rechts;
    if (j > i) {
      int x = zahlen[links];
      do {
        while (zahlen[i] < x) {
          i++;
        }
        while (zahlen[j] > x) {
          j--;
        }
        if (i <= j) {
          vertausche(i, j);
          i++;
          j--;
        }
      } while (i <= j);
      sortiereIterativ(links, j);
      sortiereIterativ(i, rechts);
    }
    return zahlen;
  }

  public int[] sortiere() {
    return sortiereIterativ(0, zahlen.length - 1);
  }

  public static void main(String[] args) {
    new QuickIterativ().teste();
  }
}
