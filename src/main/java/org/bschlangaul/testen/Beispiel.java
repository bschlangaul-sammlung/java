package org.bschlangaul.testen;

public class Beispiel {
  int max(int x, int y) { // S
    int max = x; // 1
    int min = y; // 1
    if (y > x) { // 2
      max = y; // 3
      min = x; // 4
    }
    int z = 2 * max; // 5
    if (2 * max < 3 * min) {
      z = 3 * min; // 6
    }
    return z; // E
  }

  int minMax(int min, int max) { // S
    if (min > max) { // 1
      int tmp = max; // 2
      max = min; // 2
      min = tmp; // 2
    }
    return max - min; // E
  }

}
