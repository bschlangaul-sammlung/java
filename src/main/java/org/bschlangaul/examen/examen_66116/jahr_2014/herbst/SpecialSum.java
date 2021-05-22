package org.bschlangaul.examen.examen_66116.jahr_2014.herbst;

public class SpecialSum {
  public static long specialSums(int until) {
    long sum = 0; // 0
    if (until > 0) { // 1
      for (int i = 1; i <= until; i++) { // 2 // 5
        if (i % 4 == 0 || i % 6 == 0) { // 3
          sum += i; // 4
        }
      }
    }
    return sum; // 6
  }

  public static long specialSumsRichtig(int until) {
    long sum = 0;
    if (until > 0) {
      for (int i = 1; i < until; i++) {
        if (i % 4 == 0 || i % 6 == 0) {
          sum += i;
        }
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    for (int i = 0; i <= 10; i++) {
      System.out.println(i + ": " + specialSums(i));
    }

    for (int i = 0; i <= 10; i++) {
      System.out.println(i + ": " + specialSumsRichtig(i));
    }
  }
}
