package org.bschlangaul.examen.examen_66115.jahr_2020.herbst;

public class UngeradeGerade {

  /**
   * Eine klassische Binäre Suche, die in einem sortierten Feld eine
   * Zahl findet. Kann so nicht auf die geforderte
   * ungerade-aufsteigend-gerade-absteigend Folge angewendet werden.
   *
   * @param feld Ein aufsteigend sortiertes Feld.
   * @param suche Eine Zahl nach der im Feld gesucht werden soll.
   *
   * @return Wahr, wenn die Zahl im Feld gefunden wurde.
   */
  public static boolean sucheBinär(int[] feld, int suche) {
    int links = 0, rechts = feld.length - 1;
    while (links <= rechts) {
      int mitte = links + (rechts - links) / 2;
      if (feld[mitte] == suche)
        return true;
      if (feld[mitte] < suche)
        links = mitte + 1;
      else
        rechts = mitte - 1;
    }
    return false;
  }

  public static boolean sucheBinärUngeradeGerade(int[] feld, int suche) {
    int links = 0, rechts = feld.length - 1;
    boolean istGerade = suche % 2 == 0;
    while (links <= rechts) {
      int mitte = links + (rechts - links) / 2;
      if (feld[mitte] == suche)
        return true;
      if (
        // Verschiebe die linke Grenze nach rechts, wenn die gesuchte
        // Zahl gerade ist und die Zahl in der Mitte größer als die
        // gesuchte Zahl ist oder wenn die gesuchte Zahl ungerade ist
        // und die Zahl in der Mitte kleiner.
        (istGerade && feld[mitte] > suche)
        ||
        (!istGerade && feld[mitte] < suche)
      )
        // nach rechts verschieben
        links = mitte + 1;
      else
        // nach links verschieben
        rechts = mitte - 1;
    }
    return false;
  }

  /**
   * Suche die Index-Nummer der letzten ungeraden Zahl.
   *
   * @param feld
   * @return Die Index-Nummer der letzten ungeraden Zahl. Ist im Eingabe-Feld
   *         keine ungerade Zahl vorhanden, dann wird -1 ausgegeben.
   */
  public static int sucheBinärLetzteUngerade(int[] feld) {
    int links = 0, rechts = feld.length - 1;
    int mitte = 0;
    while (links <= rechts) {
      mitte = links + (rechts - links) / 2;
      // ist Ungerade
      if (feld[mitte] % 2 != 0)
        links = mitte + 1;
      else
        rechts = mitte - 1;
    }
    if (rechts < links)
      return rechts;
    return mitte;
  }

  public static void main(String[] args) {
    // System.out.println(binarySearch(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
    // 1));
    System.out.println(sucheBinärLetzteUngerade(new int[] { 1, 3, 5, 7, 9, 10, 8, 6, 4, 2 }));

  }
}
