package org.bschlangaul.examen.examen_66115.jahr_2021.fruehjahr;

public class MinimumMaximum {

  public static int bestimmeMaximum(int[] a) {
    int max = a[0];
    for (int i = 1; i < a.length; i++) {
      if (a[i] > max) {
        max = a[i];
      }
    }
    return max;
  }

  /**
   * Diese Methode ist nicht optimiert. Es werden 2n - 2 Vergleiche benötigt.
   *
   * @param a Ein Feld mit Zahlen, in dem nach dem Minimum und dem Maximum gesucht
   *          werden soll.
   *
   * @return Ein Feld mit zwei Einträgen. Der erste Einträg enthält das Minimum,
   *         der zweite Eintrag das Maximum.
   */
  public static int[] minMaxNaiv(int[] a) {
    int max = a[0];
    int min = a[0];
    for (int i = 1; i < a.length; i++) {
      if (a[i] > max) {
        max = a[i];
      }
      if (a[i] < min) {
        max = a[i];
      }
    }
    return new int[] { min, max };
  }

  /**
   * Diese Methode ist optimiert. Es werden immer zwei Zahlen paarweise
   * betrachtet. Die Anzahl der Vergleiche reduziert sich auf 3n/2 + 2 bzw.
   * 3(n-1)/2 + 4 bei einer ungeraden Anzahl an Zahlen im Feld.
   *
   * nach <a href=
   * "https://www.techiedelight.com/find-minimum-maximum-element-array-using-minimum-comparisons/">techiedelight.com</a>
   *
   * @param a Ein Feld mit Zahlen, in dem nach dem Minimum und dem Maximum gesucht
   *          werden soll.
   *
   * @return Ein Feld mit zwei Einträgen. Der erste Einträg enthält das Minimum,
   *         der zweite Eintrag das Maximum.
   */
  public static int[] minMaxIterativPaarweise(int[] a) {
    int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    int n = a.length;

    boolean istUngerade = (n & 1) == 1;
    if (istUngerade) {
      n--;
    }

    for (int i = 0; i < n; i = i + 2) {
      int maximum, minimum;

      if (a[i] > a[i + 1]) {
        minimum = a[i + 1];
        maximum = a[i];
      } else {
        minimum = a[i];
        maximum = a[i + 1];
      }

      if (maximum > max) {
        max = maximum;
      }

      if (minimum < min) {
        min = minimum;
      }
    }

    if (istUngerade) {
      if (a[n] > max) {
        max = a[n];
      }

      if (a[n] < min) {
        min = a[n];
      }
    }
    return new int[] { min, max };
  }

  /**
   * Diese Methode ist nach dem Teile-und-Herrsche-Prinzip optimiert. Er
   * funktioniert so ähnlich wie der Mergesort.
   *
   * nach <a href=
   * "https://www.enjoyalgorithms.com/blog/find-the-minimum-and-maximum-value-in-an-array">enjoyalgorithms.com</a>
   *
   * @param a Ein Feld mit Zahlen, in dem nach dem Minimum und dem Maximum
   *          gesucht werden soll.
   * @param l Die linke Grenze.
   * @param r Die rechts Grenze.
   *
   * @return Ein Feld mit zwei Einträgen. Der erste Einträg enthält das Minimum,
   *         der zweite Eintrag das Maximum.
   */
  int[] minMaxRekursiv(int[] a, int l, int r) {
    int max, min;
    if (l == r) {
      max = a[l];
      min = a[l];
    } else if (l + 1 == r) {
      if (a[l] < a[r]) {
        max = a[r];
        min = a[l];
      } else {
        max = a[l];
        min = a[r];
      }
    } else {
      int mid = l + (r - l) / 2;
      int[] lErgebnis = minMaxRekursiv(a, l, mid);
      int[] rErgebnis = minMaxRekursiv(a, mid + 1, r);
      if (lErgebnis[0] > rErgebnis[0]) {
        max = lErgebnis[0];
      } else {
        max = rErgebnis[0];
      }
      if (lErgebnis[1] < rErgebnis[1]) {
        min = lErgebnis[1];
      } else {
        min = rErgebnis[1];
      }
    }
    int[] ergebnis = { max, min };
    return ergebnis;
  }

}
