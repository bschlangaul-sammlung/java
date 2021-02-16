package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

// https://de.wikipedia.org/wiki/Heron-Verfahren
// https://www.programmieraufgaben.ch/aufgabe/wurzelziehen-nach-dem-verfahren-von-heron/kugctg53

public class QuadratWurzel {

  public static double wurzelDouble(double z) {
    // q = Quadratzahl
    // w = Quadratwurzel
    double w = z / 2;
    while (Math.abs(w * w - z) > 0.01) {
      w = (w + z / w) / 2;
    }
    return w;
  }

  public static int wurzelInt(int q) {
    // q = Quadratzahl
    // w = Quadratwurzel
    int w = q / 2;
    while (w * w - q > 0) {
      w = (w + q / w) / 2;
    }
    return w;
  }

  public static void main(String[] args) {
    System.out.println(wurzelDouble(50));
    System.out.println(wurzelInt(50));
  }
}
