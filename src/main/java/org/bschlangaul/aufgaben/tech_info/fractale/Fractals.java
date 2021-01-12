package org.bschlangaul.aufgaben.tech_info.fractale;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Fractals {

  /**
   * Specifies when the computation is aborted
   */
  public static double MAXLENGTH = 2.0;

  /**
   * Computes a the number of iterations for a Mandelbrot/Julia-set see (
   * http://plus.maths.org/issue9/features/mandelbrot/ )
   *
   * @param start   the initial value of the iteration (z_0)
   * @param step    the increment in each iteration (c)
   * @param maxIter maximal number of iterations that are processed
   *
   * @return number of iterations between [0..maxIter]
   */
  public static int computeIterations(ComplexImpl start, ComplexImpl step, int maxIter) {
    return 0;
  }

  /**
   * Creates a Julia-Picture in parallel with the size [x,y] using the area
   * [realBegin + (imBegin)i, realEnd + (imEnd)i)
   *
   * @param x         Number of pixels in x direction
   * @param y         Number of pixels in y direction
   * @param palette   Number of used colors (limits the iteration count)
   * @param realBegin Real begin of the area
   * @param imBegin   Imaginary begin of the area
   * @param realEnd   Real end of the area
   * @param imEnd     Imaginary end of the area
   * @param step      The increment of the Julia-Set
   * @param threads   Number of threads
   *
   * @return Pixel array of the picture
   */
  public Color[][] julia(int x, int y, Color[] palette, double realBegin, double imBegin, double realEnd, double imEnd,
      ComplexImpl step, int threads) {
    return null;
  }

  /**
   * Creates a Mandelbrot-Picture with the size [x,y] using the area [realBegin +
   * (imBegin)i, realEnd + (imEnd)i)
   *
   * @param x         Number of pixels in x direction
   * @param y         Number of pixels in y direction
   * @param realBegin Real begin of the area
   * @param imBegin   Imaginary begin of the area
   * @param realEnd   Real end of the area
   * @param imEnd     Imaginary end of the area
   * @param palette   Number of used colors (limits the iteration count)
   * @param threads   Number of threads
   *
   * @return Pixel array of the picture
   */
  public Color[][] mandelbrot(int x, int y, double realBegin, double imBegin, double realEnd, double imEnd,
      Color[] palette, int threads) {
    return null;
  }

  public static void main(String[] args) {
    int x = 500;
    int y = 500;
    Color[] palette = ColorPalette.createStandardGradient();
    Color[][] feld = new Fractals().mandelbrot(x, y, -1.5, -1, 0.5, 1, palette, 4);
    Canvas.show(feld, "full mandelbrot");
    Color[][] feld2 = new Fractals().mandelbrot(x, y, -0.87484, 0.22884, -0.85084, 0.25284, palette, 4);
    Color[][] feld3 = new Fractals().mandelbrot(x, y, -0.415, -0.683, -0.415 + 0.05, -0.683 + 0.05, palette, 4);
    Canvas.show(feld3, "mandel 3");
    Canvas.show(feld2, "mandel 2");
    Color[][] feld4 = new Fractals().julia(x, y, palette, -1, -1, 1, 1, new ComplexImpl(-0.81, -0.177), 4);
    Color[][] feld5 = new Fractals().julia(x, y, palette, -1, -1, 1, 1, new ComplexImpl(-0.8, 0.156), 4);
    Canvas.show(feld4, "Julia");
    Canvas.show(feld5, "Julia 2");
  }

  public static void saveImage(String path, Color[][] array, int x, int y) {
    // Helper method for saving a picture as an image file.
    BufferedImage img = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        img.setRGB(i, j, array[i][j].getRGB());
      }
    }

    File f = new File(path);
    try {
      ImageIO.write(img, "png", f);
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

}
