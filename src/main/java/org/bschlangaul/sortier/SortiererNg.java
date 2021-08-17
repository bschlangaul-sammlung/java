package org.bschlangaul.sortier;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hüllklasse mit der die verschiedenen Sortieralgorithmen über den Klassennamen
 * aufgerufen werden können.
 *
 * Diese Klasse verwendet das Entwurfsmuster des Erbauers (Builder).
 */
public class SortiererNg {

  Sortieralgorithmus algorithmus;

  boolean zeigeInKonsole = false;

  public SortiererNg(String algorithmusKlassenName) {
    try {
      algorithmus = (Sortieralgorithmus) Class.forName("org.bschlangaul.sortier." + algorithmusKlassenName)
          .getDeclaredConstructor().newInstance();
    } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
        | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Setzte die zu sortierenden Zahlen.
   *
   * @param zahlen Die zu sortierenden Zahlen.
   *
   * @return Eine Instanz des Erbauers.
   */
  public SortiererNg zahlen(int... zahlen) {
    algorithmus.setzeZahlen(zahlen);
    return this;
  }

  /**
   * Setze die zu sortierenden Zahlen. Sortiere auch eine Zahlenreihe, die als
   * String übergeben wird. Die Strings werden von Leerzeichen gesäubert und ein
   * mögliches Komma entfernt.
   *
   * @param zahlen Eine Zahlenreihe, die als String übergeben wird.
   *
   * @return Eine Instanz des Erbauers.
   */
  public SortiererNg zahlen(String... zahlen) {
    List<Integer> echteZahlen = new ArrayList<Integer>();
    for (int i = 0; i < zahlen.length; i++) {
      String gereinigt = zahlen[i].replaceAll(",", "").trim();
      if (!gereinigt.equals("")) {
        echteZahlen.add(Integer.parseInt(gereinigt));
      }
    }
    algorithmus.setzeZahlen(echteZahlen.stream().mapToInt(i -> i).toArray());
    return this;
  }

  public SortiererNg pivot(String position) {
    Quick quick = (Quick) algorithmus;

    switch (position) {
      case "mitte":
        quick.setztePivotMitte();
        break;

      case "rechts":
        quick.setztePivotRechts();
        break;

      case "links":
        quick.setztePivotLinks();
        break;
      default:
        throw new IllegalArgumentException("Position muss sein: links, mitte, rechts: " + position);
    }
    return this;
  }

  /**
   * Zeige Ergebnis in der Konsole.
   */
  public SortiererNg konsole() {
    zeigeInKonsole = true;
    algorithmus.aktiviereKonsolenAusgabe();
    return this;
  }

  /**
   * Sortiere. Diese Methode muss als setztes aufgerufen werden.
   *
   * @return Das sortiere Zahlenfeld.
   */
  public int[] sortiere() {
    if (zeigeInKonsole) {
      System.out.println("Sortieralgorithmus: " + algorithmus.getClass().getName());
      algorithmus.berichte.feld("Eingabe");
    }

    int[] ergebnis = algorithmus.sortiere();

    if (zeigeInKonsole) {
      algorithmus.berichte.feld("Ausgabe");
    }

    return ergebnis;
  }

  public static void main(String[] args) {
    new SortiererNg("BubbleIterativ").zahlen(3, 2, 1).konsole();
    new SortiererNg("QuickSaake").zahlen(3, 2, 1, 49, 2, 5).pivot("rechts").konsole().sortiere();
    new SortiererNg("SelectionLinksIterativ").zahlen("3,", ",", " ", "1").konsole().sortiere();
  }

}
