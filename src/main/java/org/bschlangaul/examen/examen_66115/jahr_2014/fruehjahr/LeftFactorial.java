package org.bschlangaul.examen.examen_66115.jahr_2014.fruehjahr;

import java.math.BigInteger;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.ONE;

public class LeftFactorial {

  BigInteger sub(BigInteger a, BigInteger b) {
    return a.subtract(b);
  }

  BigInteger mul(BigInteger a, BigInteger b) {
    return a.multiply(b);
  }

  BigInteger mul(int a, BigInteger b) {
    return mul(BigInteger.valueOf(a), b);
  }

  // returns the left factorial !n
  BigInteger lfBig(int n) {
    if (n <= 0 || n >= Short.MAX_VALUE) {
      return ZERO;
    } else if (n == 1) {
      return ONE;
    } else {
      return sub(mul(n, lfBig(n - 1)), mul(n - 1, lfBig(n - 2)));
    }
  }

  BigInteger[] store;

  BigInteger dp(int n, BigInteger[] store) {
    if (n > 1 && store[n] != null) {
      return store[n];
    }
    if (n <= 0 || n >= Short.MAX_VALUE) {
      return ZERO;
    } else if (n == 1) {
      return ONE;
    } else {
      BigInteger result = sub(mul(n, dp(n - 1, store)), mul(n - 1, dp(n - 2, store)));
      store[n] = result;
      return result;
    }
  }

  BigInteger dp(int n) {
    store = new BigInteger[n + 1];
    return dp(n, store);
  }

  long lfLong(int n) {
    if (n <= 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    } else {
      return n * lfLong(n - 1) - (n - 1) * lfLong(n - 2);
    }
  }

  public static void main(String[] args) {
    LeftFactorial lf = new LeftFactorial();

    for (int i = 0; i < 15; i++) {
      System.out.println(lf.lfBig(i));
    }

    for (int i = 0; i < 15; i++) {
      System.out.println(lf.dp(i));
    }

    for (int i = 0; i < 15; i++) {
      System.out.println(lf.lfLong(i));
    }
  }
}
