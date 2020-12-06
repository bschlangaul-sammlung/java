package org.bschlangaul.aufgaben.sosy.eklausur;

public class Invariante {
  double geoSum(int n, double q) {
    // P: n >= 0, q > 0
    double res = 0;
    int i = 0;
    while (i < n) {
      double tmp = (1 - q) * Math.pow(q, n);
      res = res + tmp;
      i++;
    }
    return res;
  }
}
