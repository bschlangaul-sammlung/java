package org.bschlangaul.aufgaben.tech_info.monte_carlo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MonteCarloTest {

  private MonteCarlo m;
  public static final int THREADS = 8;
  public static final double EPSILON = 0.1;
  public static final int ITERATIONS = 100000;

  @Before
  public void before() {
    m = new MonteCarloImpl();
  }

  private Polynomial p1 = new Polynomial(new double[] { 0, 1 });
  private Polynomial p2 = new Polynomial(new double[] { 0, 0, 1 });
  private Polynomial p3 = new Polynomial(new double[] { 0, 0, 0, 1 });
  private Polynomial p4 = new Polynomial(new double[] { 0, 1, 1 });
  private Polynomial p5 = new Polynomial(new double[] { -1, 0, 1 });
  private Polynomial p6 = new Polynomial(new double[] { 1, 0, 1 });

  @Test
  public void testIntegral() {
    double area = m.computeIntegral(p1, 0, 1, 0, p1.compute(1), THREADS, ITERATIONS);
    assertEquals(0.5, area, EPSILON);
  }

  @Test
  public void testIntegral2() {
    double area = m.computeIntegral(p2, 0, 1, 0, p2.compute(1), THREADS, ITERATIONS);
    assertEquals(1 / 3.0, area, EPSILON);
  }

  @Test
  public void testIntegral3() {
    double area = m.computeIntegral(p3, 0, 1, 0, p3.compute(1), THREADS, ITERATIONS);
    assertEquals(0.25, area, EPSILON);
  }

  @Test
  public void testIntegral4() {
    System.out.println(p2.compute(1));
    double area = m.computeIntegral(p4, 0, 1, 0, p4.compute(1), THREADS, ITERATIONS);
    assertEquals(0.5 + 1 / 3.0, area, EPSILON);
  }

  @Test
  public void testIntegral5() {
    Kreis k = new Kreis();
    double area = m.computeIntegral(k, -1, 1, 0, 1, THREADS, ITERATIONS);
    assertEquals(Math.PI, area * 2, EPSILON);
  }

  @Test
  public void testIntegral6() {
    double area = m.computeIntegral(p5, 0, 1, -1, 0, THREADS, ITERATIONS);
    assertEquals(-2.0 / 3.0, area, EPSILON);
  }

  @Test

  public void testIntegral7() {
    double area = m.computeIntegral(p6, 0, 1, 1, 2, THREADS, ITERATIONS);
    assertEquals(1.0 + 1.0 / 3.0, area, EPSILON);
  }

  @Test
  public void testIntegralOneThread() {
    double area = m.computeIntegral(p1, 0, 1, 0, p1.compute(1), 1, ITERATIONS);
    assertEquals(0.5, area, EPSILON);
  }

  @Test
  public void testIntegral2OneThread() {
    double area = m.computeIntegral(p2, 0, 1, 0, p2.compute(1), 1, ITERATIONS);
    assertEquals(1 / 3.0, area, EPSILON);
  }

  @Test
  public void testIntegral3OneThread() {
    double area = m.computeIntegral(p3, 0, 1, 0, p3.compute(1), 1, ITERATIONS);
    assertEquals(0.25, area, EPSILON);
  }

  @Test
  public void testIntegral4OneThread() {
    double area = m.computeIntegral(p4, 0, 1, 0, p4.compute(1), 1, ITERATIONS);
    assertEquals(0.5 + 1 / 3.0, area, EPSILON);
  }

  @Test
  public void testIntegral5OneThread() {
    Kreis k = new Kreis();
    double area = m.computeIntegral(k, -1, 1, 0, 1, 1, ITERATIONS);
    assertEquals(Math.PI, area * 2, EPSILON);
  }

  @Test
  public void testIntegral6OneThread() {
    double area = m.computeIntegral(p5, 0, 1, -1, 0, 1, ITERATIONS);
    assertEquals(-2.0 / 3.0, area, EPSILON);
  }

  @Test
  public void testIntegral7OneThread() {
    double area = m.computeIntegral(p6, 0, 1, 1, 2, 1, ITERATIONS);
    assertEquals(1.0 + 1.0 / 3.0, area, EPSILON);
  }

}
