package org.bschlangaul.examen.examen_46115.jahr_2015.herbst;

public class Induktion {
  double function(int n) {
    if (n == 1)
      return 0.5 * n;
    else
      return 1.0 / (n * (n + 1)) + function(n - 1);
  }
}
