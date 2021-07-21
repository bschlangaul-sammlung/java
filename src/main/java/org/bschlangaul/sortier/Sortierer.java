package org.bschlangaul.sortier;

import java.lang.reflect.InvocationTargetException;

public class Sortierer {

  public static int[] sortiere(String algorithmusKlassenName, int[] zahlen) {
    try {
      Sortieralgorithmus algorithmus = (Sortieralgorithmus) Class.forName("org.bschlangaul.sortier." + algorithmusKlassenName)
          .getDeclaredConstructor().newInstance();
      algorithmus.setzeZahlen(zahlen);
      algorithmus.sortiere();
      return zahlen;
    } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
        | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return zahlen;
  }

  public static void main(String[] args) {
    Sortierer.sortiere("Stacksort", new int[] { 3, 2, 1 });
  }

}
