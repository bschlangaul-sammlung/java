package org.bschlangaul.cli;

import org.bschlangaul.helfer.Farbe;

public class KonsoleHelfer {

  public static String erzeugeÜberschrift(String überschrift) {
    return String.format("\n%s\n", Farbe.rot(überschrift));
  }

  public static String erzeugeUnterÜberschrift(String überschrift) {
    return String.format("\n%s", Farbe.gelb(überschrift));
  }

  public static void gibÜberschriftAus(String überschrift) {
    System.out.println(erzeugeÜberschrift(überschrift));
  }

  public static void gibUnterÜberschriftAus(String überschrift) {
    System.out.println(erzeugeUnterÜberschrift(überschrift));
  }
}
