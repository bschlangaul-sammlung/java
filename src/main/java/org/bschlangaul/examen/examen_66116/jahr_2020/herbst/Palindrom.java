package org.bschlangaul.examen.examen_66116.jahr_2020.herbst;

public class Palindrom {

  @SuppressWarnings({"unused"})
  public static boolean istWortpalindrom(char[] wort) { // 1
    boolean resultat = false; // 2
    if (wort != null) { // 3
      int laenge = wort.length; // 4
      if (laenge >= 2) { // 5
        resultat = true; // 6
        for (int i = 0; i < laenge / 2; ++i) { // 7
          char c1 = wort[i]; // 8
          char c2 = wort[laenge - 1 - i]; // 9
          if (Character.toLowerCase(c1) != Character.toLowerCase(c2)) // 10
          { // 11
            resultat = false; // 12
            break; // 13
          } // 14
        } // 15
      } else { // 16
        if (laenge == 1) { // 17
          resultat = true; // 18
        } // 19
      } // 20
    } // 21
    return resultat; // 22
  } // 23

  public static void main(String[] args) {
    System.out.println(istWortpalindrom(new char[] { 'a', 'b', 'a' }));
    System.out.println(istWortpalindrom(new char[] { 'a', 'b', 'b', 'a' }));
    System.out.println(istWortpalindrom(new char[] { 'a', 'b', 'b' }));
  }
}
