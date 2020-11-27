package org.bschlangaul.aufgaben.aud.examen_66115_2017_03;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <a href="https://www.studon.fau.de/file2861014_download.html">Angabe:
 * PUE_AUD_3.pdf</a>
 * <a href="https://www.studon.fau.de/file2893062_download.html">Lösung:
 * PUE_AUD_3_Lsg.pdf</a>
 */
public class Fibonacci {
  static long[] fib = new long[47];
  static long[] sos = new long[47];

  public static long fibNaive(int n) {
    if (n <= 2) {
      return 1;
    }
    return fibNaive(n - 1) + fibNaive(n - 2);
  }

  public static long sosNaive(int n) {
    if (n <= 1) {
      return fibNaive(n);
    }
    return fibNaive(n) * fibNaive(n) + sosNaive(n - 1);
  }

  public static long fibDP(int n) {
    // Nachschauen, ob die Fibonacci-Zahl bereits berechnet wurde.
    if (fib[n] != 0) {
      return fib[n];
    }
    // Die Fibonacci-Zahl neu berechnen.
    if (n <= 2) {
      fib[n] = 1;
    } else {
      fib[n] = fibDP(n - 1) + fibDP(n - 2);
    }
    return fib[n];
  }

  public static long sosDP(int n) {
    // Nachschauen, ob die Quadratsumme bereits berechnet wurde.
    if (sos[n] != 0) {
      return sos[n];
    }
    // Die Quadratsumme neu berechnen.
    if (n <= 1) {
      sos[n] = fibDP(n);
    } else {
      long tmp = fibDP(n);
      sos[n] = tmp * tmp + sosDP(n - 1);
    }
    return sos[n];
  }

  public static long fibIter(int n) {
    long a = 1;
    long b = 1;
    for (int i = 2; i < n; i++) {
      long tmp = a + b;
      b = a;
      a = tmp;
    }
    return a;
  }

  public static long sosIter(int n) {
    long a = 1;
    long b = 0;
    long sosSum = 1;
    for (int i = 2; i <= n; i++) {
      long tmp = a + b;
      b = a;
      a = tmp;
      sosSum += a * a;
    }
    return sosSum;
  }

  public static String fixColumn(long n) {
    return (n + "     ").substring(0, 5);
  }

  public static void invokeStaticMethod(String methodeName)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    Method method = Fibonacci.class.getMethod(methodeName, int.class);
    System.out.println("\n" + methodeName);
    for (int i = 1; i <= 10; i++) {
      System.out.print(fixColumn((long) method.invoke(null, i)));
    }
  }

  public static void main(String args[])
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    invokeStaticMethod("fibNaive");
    invokeStaticMethod("sosNaive");
    invokeStaticMethod("fibDP");
    invokeStaticMethod("sosDP");
    invokeStaticMethod("fibIter");
    invokeStaticMethod("sosIter");
    System.out.println();
  }
}
