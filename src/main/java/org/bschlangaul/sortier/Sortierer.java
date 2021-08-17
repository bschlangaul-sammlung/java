package org.bschlangaul.sortier;

import java.lang.reflect.InvocationTargetException;

/**
 * Hüllklasse mit der die verschiedenen Sortieralgorithmen über den Klassennamen
 * aufgerufen werden können.
 */
public class Sortierer {

  public static int[] sortiere(String algorithmusKlassenName, int[] zahlen) {
    try {
      Sortieralgorithmus algorithmus = (Sortieralgorithmus) Class
          .forName("org.bschlangaul.sortier." + algorithmusKlassenName).getDeclaredConstructor().newInstance();
      algorithmus.setzeZahlen(zahlen);
      algorithmus.sortiere();
      return zahlen;
    } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
        | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return zahlen;
  }

  /**
   * Sortiere auch eine Zahlenreihe, die als String übergeben wird. Die Strings
   * werden von Leerzeichen gesäubert und ein mögliches Komma entfernt.
   *
   * @param algorthmusKlassenName Der Klassenname eines Sortieralgorithmus.
   * @param zahlen                Eine Zahlenreihe, die als String übergeben wird.
   *
   * @return Die sortiere Zahlenreihe als Integer-Feld.
   */
  public static int[] sortiere(String algorthmusKlassenName, String[] zahlen) {
    int[] echteZahlen = new int[zahlen.length];
    for (int i = 0; i < zahlen.length; i++) {
      echteZahlen[i] = Integer.parseInt(zahlen[i].replaceAll(",", "").trim());
    }
    return sortiere(algorthmusKlassenName, echteZahlen);
  }

  public static void main(String[] args) {
    Sortierer.sortiere("Stacksort", new int[] { 3, 2, 1 });
  }

}
