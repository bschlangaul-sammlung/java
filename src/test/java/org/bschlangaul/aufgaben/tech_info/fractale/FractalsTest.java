package org.bschlangaul.aufgaben.tech_info.fractale;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FractalsTest {

  private Fractals fractal;
  public static final double EPSILON = 0.001;

  @Before
  public void setUp() {
    Fractals.MAXLENGTH = 2.0;
    fractal = new Fractals();
  }

  @Test
  public void testComplex() {
    assertEquals(5.0, new ComplexImpl(5, 4).nenneX(), EPSILON);
    assertEquals(4.0, new ComplexImpl(5, 4).nenneY(), EPSILON);
    assertEquals(new ComplexImpl(3, -4).nenneX(), new ComplexImpl(1, 3).plus(new ComplexImpl(2, -7)).nenneX(), EPSILON);
    assertEquals(new ComplexImpl(3, -4).nenneY(), new ComplexImpl(1, 3).plus(new ComplexImpl(2, -7)).nenneY(), EPSILON);
    assertEquals(new ComplexImpl(13, 13).nenneX(), new ComplexImpl(2, -3).mal(new ComplexImpl(-1, 5)).nenneX(),
        EPSILON);
    assertEquals(new ComplexImpl(13, 13).nenneY(), new ComplexImpl(2, -3).mal(new ComplexImpl(-1, 5)).nenneY(),
        EPSILON);
    assertEquals(3.6055512, new ComplexImpl(2, -3).betrag(), EPSILON);
    assertEquals(5.0990195, new ComplexImpl(-1, 5).betrag(), EPSILON);
  }

  @Test
  public void testComputeIterationsZeroZero() {
    assertEquals(1, Fractals.computeIterations(new ComplexImpl(0, 0), new ComplexImpl(0, 0), 1));
    assertEquals(5, Fractals.computeIterations(new ComplexImpl(0, 0), new ComplexImpl(0, 0), 5));
    assertEquals(10, Fractals.computeIterations(new ComplexImpl(0, 0), new ComplexImpl(0, 0), 10));
    assertEquals(50, Fractals.computeIterations(new ComplexImpl(0, 0), new ComplexImpl(0, 0), 50));
  }

  @Test
  public void testComputeIterationsZeroMinusOne() {
    assertEquals(1, Fractals.computeIterations(new ComplexImpl(0, 0), new ComplexImpl(-1, 0), 1));
    assertEquals(5, Fractals.computeIterations(new ComplexImpl(0, 0), new ComplexImpl(-1, 0), 5));
    assertEquals(10, Fractals.computeIterations(new ComplexImpl(0, 0), new ComplexImpl(-1, 0), 10));
    assertEquals(50, Fractals.computeIterations(new ComplexImpl(0, 0), new ComplexImpl(-1, 0), 50));
  }

  @Test
  public void testComputeIterationsZero0_405() {
    assertEquals(5, Fractals.computeIterations(new ComplexImpl(0, 0), new ComplexImpl(0.405, 0.405), 5));
    assertEquals(9, Fractals.computeIterations(new ComplexImpl(0, 0), new ComplexImpl(0.405, 0.405), 10));
  }

  @Test
  public void testComputeIterationsTwoZero() {
    assertEquals(0, Fractals.computeIterations(new ComplexImpl(2, 0), new ComplexImpl(0, 0), 5));
  }

  @Test
  public void testComputeIterationsWithAllNonZero() {
    assertEquals(2, Fractals.computeIterations(new ComplexImpl(-1, 0.3), new ComplexImpl(-0.1, -0.1), 2));
    assertEquals(4, Fractals.computeIterations(new ComplexImpl(-1, 0.3), new ComplexImpl(-0.1, -0.1), 4));
    assertEquals(4, Fractals.computeIterations(new ComplexImpl(-1, 0.3), new ComplexImpl(-0.1, -0.1), 8));
  }

  @Test
  public void testMandelbrotOneThreadDimensions() {
    final Color[][] result = fractal.mandelbrot(7, 11, 2, 2, 3, 3, createTestPalette(), 1);
    assertEquals(7, result.length);
    for (int i = 0; i < result.length; ++i) {
      assertEquals(11, result[i].length);
    }
  }

  @Test
  @Ignore
  public void testMandelbrotOneThreadSmall1() {
    final Color[][] result = fractal.mandelbrot(5, 5, 0.5, 0.5, 1, 1, createTestPalette(), 1);
    assertEquals(5, result.length);
    assertArrayEquals(new Color[] { Color.BLACK, Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE }, result[0]);
    assertArrayEquals(new Color[] { Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE }, result[1]);
    assertArrayEquals(new Color[] { Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED }, result[2]);
    assertArrayEquals(new Color[] { Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.RED }, result[3]);
    assertArrayEquals(new Color[] { Color.RED, Color.RED, Color.RED, Color.RED, Color.RED }, result[4]);
  }

  @Test
  public void testMandelbrotOneThreadSmall2() {
    final Color[][] result = fractal.mandelbrot(5, 5, -1, -1, 1, 1, createBWPalette(), 1);
    assertEquals(5, result.length);
    assertArrayEquals(new Color[] { Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.WHITE }, result[0]);
    assertArrayEquals(result[0], result[1]);
    assertArrayEquals(new Color[] { Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK }, result[2]);
    assertArrayEquals(result[1], result[3]);
    assertArrayEquals(new Color[] { Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE }, result[4]);
  }

  @Ignore
  @Test
  public void testMandelbrotOneThreadMedium1() {
    final Color[][] result = fractal.mandelbrot(50, 50, -1, -1, 1, 1, createBWPalette(), 1);
    assertEquals(50, result.length);
    assertEquals(Color.WHITE, result[0][0]);
    assertEquals(Color.WHITE, result[49][0]);
    assertEquals(Color.WHITE, result[18][6]);
    assertEquals(Color.BLACK, result[22][6]);
    assertEquals(Color.WHITE, result[26][6]);
    assertEquals(Color.BLACK, result[0][19]);
    assertEquals(Color.WHITE, result[36][19]);
    assertEquals(Color.BLACK, result[0][25]);
    assertEquals(Color.WHITE, result[34][25]);
    assertEquals(Color.BLACK, result[0][31]);
    assertEquals(Color.WHITE, result[36][31]);
    assertEquals(Color.WHITE, result[18][45]);
    assertEquals(Color.BLACK, result[22][45]);
    assertEquals(Color.WHITE, result[26][45]);
    assertEquals(Color.WHITE, result[0][49]);
    assertEquals(Color.WHITE, result[49][49]);
  }
  @Ignore
  @Test
  public void testMandelbrotOneThreadMedium2() {
    final Color[][] result = fractal.mandelbrot(50, 50, -0.749, 0.122, -0.739, 0.132, createBWPalette(), 1);
    assertEquals(50, result.length);
    assertEquals(Color.WHITE, result[33][0]);
    assertEquals(Color.BLACK, result[36][0]);
    assertEquals(Color.WHITE, result[43][0]);
    assertEquals(Color.BLACK, result[48][0]);
    assertEquals(Color.WHITE, result[2][8]);
    assertEquals(Color.BLACK, result[7][8]);
    assertEquals(Color.BLACK, result[11][8]);
    assertEquals(Color.BLACK, result[28][8]);
    assertEquals(Color.WHITE, result[31][8]);
    assertEquals(Color.BLACK, result[35][8]);
    assertEquals(Color.WHITE, result[12][25]);
    assertEquals(Color.BLACK, result[18][25]);
    assertEquals(Color.BLACK, result[25][25]);
    assertEquals(Color.WHITE, result[29][25]);
    assertEquals(Color.BLACK, result[34][25]);
    assertEquals(Color.WHITE, result[15][41]);
    assertEquals(Color.BLACK, result[19][41]);
    assertEquals(Color.WHITE, result[24][41]);
    assertEquals(Color.WHITE, result[42][41]);
    assertEquals(Color.BLACK, result[47][41]);
  }

  @Test
  @Ignore
  public void testMandelbrotOneThreadLarge() {
    final Color[][] result = fractal.mandelbrot(200, 200, -1, -1, 1.0 / 3.0, 1, createBWPalette(), 1);
    assertEquals(200, result.length);
    assertEquals(Color.WHITE, result[130][11]);
    assertEquals(Color.WHITE, result[136][11]);
    assertEquals(Color.BLACK, result[132][12]);
    assertEquals(Color.WHITE, result[128][14]);
    assertEquals(Color.BLACK, result[133][14]);
    assertEquals(Color.WHITE, result[138][14]);
    assertEquals(Color.WHITE, result[68][41]);
    assertEquals(Color.BLACK, result[71][41]);
    assertEquals(Color.BLACK, result[33][90]);
    assertEquals(Color.WHITE, result[36][90]);
    assertEquals(Color.BLACK, result[39][90]);
  }

  @Test
  public void testMandelbrotParDimensions() {
    final Color[][] result = fractal.mandelbrot(7, 11, 2, 2, 3, 3, createTestPalette(), 4);
    assertEquals(7, result.length);
    for (int i = 0; i < result.length; ++i) {
      assertEquals(11, result[i].length);
    }
  }

  @Test
  public void testMandelbrotParSmall1() {
    final Color[][] resultSeq = fractal.mandelbrot(5, 5, 0.5, 0.5, 1, 1, createTestPalette(), 1);
    final Color[][] resultPar = fractal.mandelbrot(5, 5, 0.5, 0.5, 1, 1, createTestPalette(), 4);

    assertTrue(Arrays.deepEquals(resultSeq, resultPar));
  }

  @Test
  public void testMandelbrotParSmall2() {
    final Color[][] resultSeq = fractal.mandelbrot(5, 5, -1, -1, 1, 1, createBWPalette(), 1);
    final Color[][] resultPar = fractal.mandelbrot(5, 5, -1, -1, 1, 1, createBWPalette(), 4);

    assertTrue(Arrays.deepEquals(resultSeq, resultPar));
  }

  @Test
  public void testMandelbrotParMedium1() {
    final Color[][] resultSeq = fractal.mandelbrot(50, 50, -1, -1, 1, 1, createBWPalette(), 1);
    final Color[][] resultPar = fractal.mandelbrot(50, 50, -1, -1, 1, 1, createBWPalette(), 4);

    assertTrue(Arrays.deepEquals(resultSeq, resultPar));
  }

  @Test
  public void testMandelbrotParMedium2() {
    final Color[][] resultSeq = fractal.mandelbrot(50, 50, -0.749, 0.122, -0.739, 0.132, createBWPalette(), 1);
    final Color[][] resultPar = fractal.mandelbrot(50, 50, -0.749, 0.122, -0.739, 0.132, createBWPalette(), 4);

    assertTrue(Arrays.deepEquals(resultSeq, resultPar));
  }

  @Test
  public void testMandelbrotParLarge1() {
    final Color[][] resultSeq = fractal.mandelbrot(200, 200, -1, -1, 1.0 / 3.0, 1, createBWPalette(), 1);
    final Color[][] resultPar = fractal.mandelbrot(200, 200, -1, -1, 1.0 / 3.0, 1, createBWPalette(), 4);

    assertTrue(Arrays.deepEquals(resultSeq, resultPar));
  }

  @Test
  public void testMandelbrotParLarge2() {
    final Color[][] resultSeq = fractal.mandelbrot(1000, 1000, -0.746, 0.13, -0.751, 0.135, createBWPalette(), 1);
    final Color[][] resultPar = fractal.mandelbrot(1000, 1000, -0.746, 0.13, -0.751, 0.135, createBWPalette(), 4);

    assertTrue(Arrays.deepEquals(resultSeq, resultPar));
  }
  @Ignore
  @Test
  public void testJuliaParSmall() {
    final Color[][] result = fractal.julia(5, 5, createTestPalette(), -1, -1, 1, 1, new ComplexImpl(1.5, 1.5), 4);
    assertArrayEquals(new Color[] { Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN }, result[0]);
    assertArrayEquals(new Color[] { Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED }, result[1]);
    assertArrayEquals(new Color[] { Color.RED, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED }, result[2]);
    assertArrayEquals(new Color[] { Color.RED, Color.RED, Color.GREEN, Color.GREEN, Color.GREEN }, result[3]);
    assertArrayEquals(new Color[] { Color.RED, Color.RED, Color.GREEN, Color.GREEN, Color.GREEN }, result[4]);
  }
  @Ignore
  @Test
  public void testJuliaParMedium() {
    final Color[][] result = fractal.julia(50, 50, createBWPalette(), -1, -1, 1, 1, new ComplexImpl(-0.15, 0.15), 4);
    assertEquals(Color.WHITE, result[19][2]);
    assertEquals(Color.BLACK, result[22][2]);
    assertEquals(Color.BLACK, result[27][2]);
    assertEquals(Color.WHITE, result[32][2]);
    assertEquals(Color.WHITE, result[0][19]);
    assertEquals(Color.BLACK, result[3][19]);
    assertEquals(Color.BLACK, result[49][19]);
    assertEquals(Color.BLACK, result[24][24]);
    assertEquals(Color.BLACK, result[25][25]);
    assertEquals(Color.BLACK, result[0][32]);
    assertEquals(Color.BLACK, result[46][32]);
    assertEquals(Color.WHITE, result[49][32]);
    assertEquals(Color.WHITE, result[19][49]);
    assertEquals(Color.BLACK, result[23][49]);
    assertEquals(Color.BLACK, result[28][49]);
    assertEquals(Color.WHITE, result[31][49]);
  }
  @Ignore
  @Test
  public void testJuliaParLarge() {
    final Color[][] result = fractal.julia(200, 200, createBWPalette(), -0.2, 0.1, 0, 0.3, new ComplexImpl(-0.8, 0.156),
        4);
    assertEquals(Color.WHITE, result[22][4]);
    assertEquals(Color.BLACK, result[44][5]);
    assertEquals(Color.WHITE, result[72][6]);
    assertEquals(Color.BLACK, result[97][5]);
    assertEquals(Color.WHITE, result[144][6]);
    assertEquals(Color.BLACK, result[60][59]);
    assertEquals(Color.WHITE, result[39][124]);
    assertEquals(Color.BLACK, result[148][131]);
    assertEquals(Color.WHITE, result[80][170]);
    assertEquals(Color.BLACK, result[138][170]);
  }

  private static Color[] createBWPalette() {
    final Color[] result = new Color[100];
    Arrays.fill(result, Color.WHITE);
    result[99] = Color.BLACK;
    return result;
  }

  private static Color[] createTestPalette() {
    return ColorPalette.create(5, new Color[] { Color.WHITE, Color.GREEN, Color.RED, Color.BLUE, Color.BLACK });
  }
}
