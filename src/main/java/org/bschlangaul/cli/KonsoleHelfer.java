package org.bschlangaul.cli;

import org.bschlangaul.helfer.Farbe;

public class KonsoleHelfer {

  public static String erzeugeÜberschrift(String überschrift) {
    return String.format("\n%s\n", Farbe.rot(überschrift));
  }

  public static void gibÜberschriftAus(String überschrift) {
    System.out.println(erzeugeÜberschrift(überschrift));
  }
}
