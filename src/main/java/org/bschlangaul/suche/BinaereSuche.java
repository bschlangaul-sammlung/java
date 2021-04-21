package org.bschlangaul.suche;

/**
 * Nach Saake Seite 122-123
 */
public class BinaereSuche {
  public final static int KEIN_SCHLÜSSEL = -1;

  public static int suche(int[] zahlen, int schlüssel) {
    int links = 0, rechts = zahlen.length - 1;
    while (links <= rechts) {
      int mitte = (links + rechts) / 2;
      if (zahlen[mitte] == schlüssel) {
        return mitte;
      } else if (zahlen[mitte] > schlüssel) {
        rechts = mitte - 1;
      } else {
        links = mitte + 1;
      }
    }
    return KEIN_SCHLÜSSEL;
  }

  private static int sucheRekursiv(int[] zahlen, int links, int rechts, int schlüssel) {
    if (links > rechts) {
      return KEIN_SCHLÜSSEL;
    }
    int mitte = links + (rechts - links) / 2;
    if (schlüssel < zahlen[mitte]) {
      return sucheRekursiv(zahlen, links, mitte - 1, schlüssel);
    }
    if (schlüssel > zahlen[mitte]) {
      return sucheRekursiv(zahlen, mitte + 1, rechts, schlüssel);
    }
    return mitte;
  }

  public static int sucheRekursiv(int[] zahlen, int schlüssel) {
    return sucheRekursiv(zahlen, 0, zahlen.length - 1, schlüssel);
  }

  public static void main(String[] args) {
    int[] zahlen = { 2, 4, 5, 6, 7, 8, 9, 11 };
    System.out.println(suche(zahlen, 6));
    System.out.println(sucheRekursiv(zahlen, 6));
  }
}
