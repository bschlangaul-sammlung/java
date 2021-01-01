package org.bschlangaul.cli;

import org.bschlangaul.helfer.Farbe;

public class Helfer {
  public static void gibÜberschriftAus(String überschrift) {
    System.out.println(String.format("\n%s\n", Farbe.rot(überschrift)));
  }
}
