package org.bschlangaul.examen.examen_46116.jahr_2013.fruehjahr.t2_ta1_a1;

import java.util.Date;

public class Order {
  public static double VAT = 0.19;

  protected Date date;

  protected boolean shipped;

  public double calcPrice() {
    return 0.1d;
  }

  public double calcWeight() {
    return 0.2d;
  }

  public double calcTax(double tax) {
    return 0.3d;
  }
}
