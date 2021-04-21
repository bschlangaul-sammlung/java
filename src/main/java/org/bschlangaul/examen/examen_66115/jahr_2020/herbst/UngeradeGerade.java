package org.bschlangaul.examen.examen_66115.jahr_2020.herbst;

public class UngeradeGerade {

  public static boolean suche(int[] feld, int suche) {
    int links = 0, rechts = feld.length - 1;
    boolean istGerade = suche % 2 == 0;
    int mitte = 0;
    while (links <= rechts) {
      mitte = links + (rechts - links) / 2;
      if (feld[mitte] == suche) {
        return true;
      }
      // Verschiebe die linke Grenze nach rechts, wenn die gesuchte
      // Zahl gerade ist und die Zahl in der Mitte größer als die
      // gesuchte Zahl ist oder wenn die gesuchte Zahl ungerade ist
      // und die Zahl in der Mitte kleiner.
      if ((istGerade && feld[mitte] > suche) || (!istGerade && feld[mitte] < suche)) {
        // nach rechts verschieben
        links = mitte + 1;
      } else {
        // nach links verschieben
        rechts = mitte - 1;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println(suche(new int[] { 1, 5, 11, 8, 4, 2 }, 4));
  }
}
