package org.bschlangaul.aufgaben.tech_info.fractale;

import java.awt.Color;

public class ColorMandelbrot {

    // return number of iterations to check if c = a + ib is in Mandelbrot set
    public static int mand(ComplexImpl z0, int max) {
      ComplexImpl z = z0;
        for (int t = 0; t < max; t++) {
            if (z.betrag() > 2.0) return t;
            z = z.mal(z).plus(z0);
        }
        return max;
    }


    public static void main(String[] args)  {
        double xc   = Double.parseDouble(args[0]);
        double yc   = Double.parseDouble(args[1]);
        double size = Double.parseDouble(args[2]);

        int n = 512;

        int ITERS = 256;


        // read in color map
        Color[] colors = new Color[ITERS];
        for (int t = 0; t < ITERS; t++) {
            int r = StdIn.readInt();
            int g = StdIn.readInt();
            int b = StdIn.readInt();
            colors[t] = new Color(r, g, b);
        }


        // compute Mandelbrot set
        Picture picture = new Picture(n, n);
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {
                double x = xc - size/2 + size*col/n;
                double y = yc - size/2 + size*row/n;
                ComplexImpl z0 = new ComplexImpl(x, y);
                int t = mand(z0, ITERS - 1);
                picture.set(col, n-1-row, colors[t]);
            }
        }
        picture.show();
    }

}
