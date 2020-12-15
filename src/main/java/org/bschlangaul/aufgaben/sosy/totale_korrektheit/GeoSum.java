package org.bschlangaul.aufgaben.sosy.totale_korrektheit;

public class GeoSum {
  // Math.pow(q, n) == q^n
  double geoSum(int n, double q) {
    if (n == 0) {
      return 1 - q;
    } else {
      return (1 - q) * Math.pow(q, n) + geoSum(n - 1, q);
    }
  }

  public static void main(String[] args) {
    GeoSum s = new GeoSum();
    for (int j = 0; j < 10; j++) {
      System.out.println(s.geoSum(j, 2));
    }
  }
}
