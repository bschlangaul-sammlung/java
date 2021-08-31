package org.bschlangaul.examen.examen_66116.jahr_2019.herbst;

public class Dezimalzahl {
  public static boolean istDezimalzahl(char[] zeichen) {
    boolean resultat;
    int laenge = zeichen.length;
    if (laenge == 0)
      resultat = false;
    else {
      int i = 0;
      if (zeichen[i] == '+' || zeichen[i] == '-')
        i++;
      int j = i;

      while (i < laenge && '0' <= zeichen[i] && zeichen[i] <= '9')
        i++;
      if (i == j)
        resultat = false;
      else {
        if (i < laenge && zeichen[i] == '.')
          do
            i++;
          while (i < laenge && '0' <= zeichen[i] && zeichen[i] <= '9');
        resultat = i == laenge && '0' <= zeichen[i - 1] && zeichen[i - 1] <= '9';
      }
    }
    return resultat;
  }
}
