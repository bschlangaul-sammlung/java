package org.bschlangaul.examen.examen_66115.jahr_2017.fruehjahr;

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

  public static long invokeMethod(String methodName, int n) {
    try {
      Method method = Fibonacci.class.getMethod(methodName, int.class);
      return (long) method.invoke(null, n);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public static void showResult(String methodName) {
    System.out.println("\n" + methodName);
    for (int i = 1; i <= 10; i++) {
      System.out.print(fixColumn(invokeMethod(methodName, i)));
    }
  }

  public static void main(String args[]) {
    showResult("fibNaive");
    showResult("sosNaive");
    showResult("fibDP");
    showResult("sosDP");
    showResult("fibIter");
    showResult("sosIter");
    System.out.println();
  }
}
