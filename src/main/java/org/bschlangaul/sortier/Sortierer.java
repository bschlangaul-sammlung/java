package org.bschlangaul.sortier;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hüllklasse mit der die verschiedenen Sortieralgorithmen über den Klassennamen
 * aufgerufen werden können.
 *
 * Diese Klasse verwendet das Entwurfsmuster des Erbauers (Builder).
 */
public class Sortierer {

  Sortieralgorithmus algorithmus;

  boolean zeigeInKonsole = false;

  public Sortierer() {
  }

  public Sortierer(String algorithmusKlassenName) {
    setzeAlgorithmus(algorithmusKlassenName);
  }

  private void setzeAlgorithmus(String algorithmusKlassenName) {
    try {
      algorithmus = (Sortieralgorithmus) Class.forName("org.bschlangaul.sortier." + algorithmusKlassenName)
          .getDeclaredConstructor().newInstance();
    } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
        | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Sortierer algorithmus(String algorithmusKlassenName) {
    setzeAlgorithmus(algorithmusKlassenName);
    return this;
  }


  private int[] konvertiereZahlen(List<String> zahlen) {
    List<Integer> echteZahlen = new ArrayList<Integer>();
    for (String zahl : zahlen) {
      String gereinigt = zahl.replaceAll(",", "").trim();
      if (!gereinigt.equals("")) {
        echteZahlen.add(Integer.parseInt(gereinigt));
      }
    }
    return echteZahlen.stream().mapToInt(i -> i).toArray();
  }

  private int[] konvertiereZahlen(String[] zahlen) {
    return konvertiereZahlen(Arrays.asList(zahlen));
  }

  /**
   * Setzte die zu sortierenden Zahlen.
   *
   * @param zahlen Die zu sortierenden Zahlen.
   *
   * @return Eine Instanz des Erbauers.
   */
  public Sortierer zahlen(int... zahlen) {
    algorithmus.setzeZahlen(zahlen);
    return this;
  }

  public Sortierer zahlen(List<String> zahlen) {
    algorithmus.setzeZahlen(konvertiereZahlen(zahlen));
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
  public Sortierer zahlen(String... zahlen) {
    algorithmus.setzeZahlen(konvertiereZahlen(zahlen));
    return this;
  }

  public Sortierer pivot(String position) {
    if (!(algorithmus instanceof Quick)) {
      throw new RuntimeException("Diese Methode kannt nur bei Quicksort-Algorithmen angewendet werden.");
    }

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
  public Sortierer konsole() {
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
    new Sortierer("BubbleIterativ").zahlen(3, 2, 1).konsole();
    new Sortierer("QuickSaake").zahlen(3, 2, 1, 49, 2, 5).pivot("rechts").konsole().sortiere();
    new Sortierer("SelectionLinksIterativ").zahlen("3,", ",", " ", "1").konsole().sortiere();
  }

}
