package org.bschlangaul.examen.examen_66115.jahr_2017.fruehjahr;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FibonacciTest {
  private void testFib(String methodName) {
    Fibonacci.fibNaive(1);
    assertEquals(1, Fibonacci.invokeMethod(methodName, 1));
    assertEquals(1, Fibonacci.invokeMethod(methodName, 2));
    assertEquals(2, Fibonacci.invokeMethod(methodName, 3));
    assertEquals(3, Fibonacci.invokeMethod(methodName, 4));
    assertEquals(5, Fibonacci.invokeMethod(methodName, 5));
    assertEquals(8, Fibonacci.invokeMethod(methodName, 6));
    assertEquals(13, Fibonacci.invokeMethod(methodName, 7));
    assertEquals(21, Fibonacci.invokeMethod(methodName, 8));
    assertEquals(34, Fibonacci.invokeMethod(methodName, 9));
    assertEquals(55, Fibonacci.invokeMethod(methodName, 10));
  }

  private void testSos(String methodName) {
    Fibonacci.fibNaive(1);
    assertEquals(1, Fibonacci.invokeMethod(methodName, 1));
    assertEquals(2, Fibonacci.invokeMethod(methodName, 2));
    assertEquals(6, Fibonacci.invokeMethod(methodName, 3));
    assertEquals(15, Fibonacci.invokeMethod(methodName, 4));
    assertEquals(40, Fibonacci.invokeMethod(methodName, 5));
    assertEquals(104, Fibonacci.invokeMethod(methodName, 6));
    assertEquals(273, Fibonacci.invokeMethod(methodName, 7));
    assertEquals(714, Fibonacci.invokeMethod(methodName, 8));
    assertEquals(1870, Fibonacci.invokeMethod(methodName, 9));
    assertEquals(4895, Fibonacci.invokeMethod(methodName, 10));
  }

  @Test
  public void fibNaive() {
    testFib("fibNaive");
  }

  @Test
  public void fibDP() {
    testFib("fibDP");
  }

  @Test
  public void fibIter() {
    testFib("fibIter");
  }

  @Test
  public void sosNaive() {
    testSos("sosNaive");
  }

  @Test
  public void sosDP() {
    testSos("sosDP");
  }

  @Test
  public void sosIter() {
    testSos("sosIter");
  }

}
