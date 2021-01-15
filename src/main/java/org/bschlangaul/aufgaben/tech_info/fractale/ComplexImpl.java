package org.bschlangaul.aufgaben.tech_info.fractale;

/**
 * https://introcs.cs.princeton.edu/java/32class/Complex.java.html
 */
public class ComplexImpl implements Complex {

  double x;
  double y;

  public ComplexImpl(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public ComplexImpl plus(ComplexImpl z) {
    return new ComplexImpl(x + z.nenneX(), y + z.nenneY());
  }

  public double betrag() {
    return Math.hypot(x, y);
    //return Math.sqrt(x * x + y * y) ;
  }

  public ComplexImpl mal(ComplexImpl b) {
    double aX = x;
    double aY = y;
    double bX = b.nenneX();
    double bY = b.nenneY();
    // (aX + aY) * (bX + bY)
    // aX*bX + aX*bY + aY*bX + aY*bY
    return new ComplexImpl(aX * bX + aY * bY * -1, aX * bY + aY * bX);
  }

  public double nenneX() {
    return x;
  }

  public double nenneY() {
    return y;
  }
}
