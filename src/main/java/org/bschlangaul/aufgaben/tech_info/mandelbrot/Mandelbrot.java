package org.bschlangaul.aufgaben.tech_info.mandelbrot;

import java.awt.Color;

public class Mandelbrot {

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

        int n   = 512;   // create n-by-n image
        int max = 255;   // maximum number of iterations

        Picture picture = new Picture(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double x0 = xc - size/2 + size*i/n;
                double y0 = yc - size/2 + size*j/n;
                ComplexImpl z0 = new ComplexImpl(x0, y0);
                int gray = max - mand(z0, max);
                Color color = new Color(gray, gray, gray);
                picture.set(i, n-1-j, color);
            }
        }
        picture.show();
    }
}
