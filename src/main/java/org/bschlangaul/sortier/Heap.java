package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Heapsort-Algorithmus. (Nach Saake
 * Seite 412)
 */
public class Heap extends Sortieralgorithmus {
  private void versickere(int index, int letzterIndex) {
    berichte.feld("versickere " + index + " " + letzterIndex);
    int i = index + 1, j;
    // zahlen[i] hat linken Sohn
    while (2 * i <= letzterIndex) {
      // zahlen[j] ist linker Sohn von zahlen[i]
      j = 2 * i;
      // zahlen[i] hat auch rechten Sohn
      if (j < letzterIndex && zahlen[j - 1] < zahlen[j]) {
        // zahlen[j] ist jetzt kleiner
        j++;
      }

      if (zahlen[i - 1] < zahlen[j - 1]) {
        vertausche(i - 1, j - 1);
        // versickere weiter
        i = j;
      } else {
        // halte an, heap-Bedingung erfüllt
        break;
      }
    }
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Heapsort-Algorithmus.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public int[] sortiere() {
    int i;

    berichte.feld("Heap herstellen");
    for (i = zahlen.length / 2; i >= 0; i--) {
      versickere(i, zahlen.length);
    }

    berichte.feld("sortiere");
    for (i = zahlen.length - 1; i > 0; i--) {
      // tauscht jeweils letztes Element des Heaps mit dem ersten
      vertausche(0, i);
      // heap wird von Position 0 bis i hergestellt
      versickere(0, i);
    }
    return zahlen;
  }

  public static void main(String[] args) {
    new Heap().teste();
  }
}
