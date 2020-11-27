package org.bschlangaul.aufgaben.sosy.pu_5;

public class Aufgabe2 {

  boolean isPalindrom(String s) {
    boolean yesItIs = true;
    if (s != null && s.length() > 1) {
      do {
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
          yesItIs = false;
        }
        s = s.substring(1, s.length() - 1);

      } while (yesItIs && s.length() > 1);
    }
    return yesItIs;
  }
}
