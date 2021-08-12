package org.bschlangaul.examen.examen_66115.jahr_2018.fruehjahr;

@SuppressWarnings("unused")
public class MasterTheorem {
  public static int m(int[] r, int lo, int hi) {
    if (lo < 8 || hi <= 10 || lo >= r.length || hi > r.length) {
      throw new IllegalArgumentException();
    }

    if (hi - lo == 1) {
      return r[lo];
    } else if (hi - lo == 2) {
      return Math.max(r[lo], r[lo + 1]); // O(1)
    } else {
      int s = (hi - lo) / 3;
      int x = m(r, lo, lo + s);
      int y = m(r, lo + s, lo + 2 * s);
      int z = m(r, lo + 2 * s, hi);
      return Math.max(Math.max(x, y), 2); // O(1)
    }
  }
}
