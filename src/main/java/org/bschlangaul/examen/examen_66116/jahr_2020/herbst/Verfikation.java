package org.bschlangaul.examen.examen_66116.jahr_2020.herbst;

public class Verfikation {

  /**
   * Suche in einem Feld nach einem Wert.
   *
   * @param a Ein Feld in dem nach einem Werte gesucht werden soll.
   * @param m Der gesuchte Wert.
   *
   * @return Falls der Wert gefunden wurde, wird die Index-Nummer im
   * Feld ausgegeben. Wenn der Wert nicht gefunden wurde, wird -1
   * ausgegeben.
   */
  public static int sucheWertInFeld(int[] a, int m) {
    int n = a.length;

    int i = -1;
    // (1)
    int j = 0;
    // (2)
    while (i == -1 && j < n) // (3)
    { // (4)
      if (a[j] == m) {
        // (5)
        i = j;
        // (6)
      } else {
        // (7)
        j = j + 1;
        // (8)
      }
      // (9)
    }

    return i;
  }

  public static void main(String[] args) {
    int[] a = new int[10];
    a[4] = 3;
    System.out.println(sucheWertInFeld(a, 3)); // 4
    System.out.println(sucheWertInFeld(a, 7)); // -1
  }
}
