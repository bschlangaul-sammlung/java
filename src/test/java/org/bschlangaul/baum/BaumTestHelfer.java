package org.bschlangaul.baum;

import java.util.ArrayList;
import java.util.Collections;

public class BaumTestHelfer {
  @SuppressWarnings({ "rawtypes" })
  public static Comparable[] macheFeld(Comparable... schlüssel) {
    return schlüssel;
  }
  @SuppressWarnings({ "rawtypes" })

  public static ArrayList<Comparable> macheListe(Comparable... zahlen) {
    ArrayList<Comparable> liste = new ArrayList<>();
    Collections.addAll(liste, zahlen);
    return liste;
  }

}
