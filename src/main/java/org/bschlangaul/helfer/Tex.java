package org.bschlangaul.helfer;

public class Tex {

  public static String makro(String name, String inhalt) {
    return String.format("\\%s{%s}", name, inhalt);
  }

  public static String umgebung(String name, String inhalt) {
    return String.format("%s\n%s\n%s", makro("begin", name), reinigeInhalt(inhalt), makro("end", name));
  }

  private static String reinigeInhalt(String inhalt) {
    return inhalt.replaceFirst("\\s+$", "");
  }

  public static String umgebung(String name, String inhalt, String option) {
    return String.format("%s[%s]\n%s\n%s", makro("begin", name), option, reinigeInhalt(inhalt), makro("end", name));
  }

  public static String tabellenZeile(String[] zeile, int maxTextBreite) {
    String ausgabe = "";
    String format = "%-" + maxTextBreite + "s";
    for (int i = 0; i < zeile.length - 1; i++) {
      ausgabe += String.format(format, zeile[i]) + " & ";
    }
    ausgabe += String.format(format, zeile[zeile.length - 1]) + " \\\\\n";
    return ausgabe;
  }

  public static String tabellenKörper(String[][] zeilen, int maxTextBreite) {
    String ausgabe = "";
    for (int i = 0; i < zeilen.length; i++) {
      ausgabe += tabellenZeile(zeilen[i], maxTextBreite);
    }
    return ausgabe;
  }

  public static String tabellenKopfzeile(String[] kopfzeile, int maxTextBreite) {
    for (int i = 0; i < kopfzeile.length; i++) {
      kopfzeile[i] = makro("bf", kopfzeile[i]);
    }
    return tabellenZeile(kopfzeile, maxTextBreite) + "\\hline\n";
  }

  public static String tabelle(String[] kopfzeile, String[][] zeilen) {
    int maxTextBreite = 0;
    for (String[] zellen : zeilen) {
      for (String zelle : zellen) {
        if (maxTextBreite < zelle.length()) {
          maxTextBreite = zelle.length();
        }
      }
    }

    int maxTextBreiteKopfzeile = 0;
    for (String kopfzelle : kopfzeile) {
      if (maxTextBreiteKopfzeile < kopfzelle.length()) {
        maxTextBreiteKopfzeile = kopfzelle.length();
      }
    }
    // \bf{} = 5
    maxTextBreiteKopfzeile = maxTextBreiteKopfzeile + 5;
    maxTextBreite = Math.max(maxTextBreiteKopfzeile, maxTextBreite);
    return String.format("\\begin{tabular}{%s}\n%s\\end{tabular}\n", "l".repeat(kopfzeile.length),
    tabellenKopfzeile(kopfzeile, maxTextBreite) + tabellenKörper(zeilen, maxTextBreite));
  }

}
