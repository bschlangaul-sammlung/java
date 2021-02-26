package org.bschlangaul.aufgaben.theo_inf.regulaere_ausdruecke;

public class TestRegularExpressions {
  // Hier bitte Lösungen der Aufgaben eintragen.
  static String regexABBA = "(a|b)*abba(a|b)*";
  static String regexGeradeA = "b*(ab*ab*)*"; // Epsilon aa aaaa abba bbaa aabb bbb
  static String regexUngeradeB = "a*ba*(ba*ba*)*"; // Epsilon b bbb abababa
  static String regexGeradzahligA = "((a|b)a)*(a*|b)"; // aa ba aab b
  static String regexEMAIL = "\\w+@\\w+\\.(de|com)";
  // Wenn die Lösungen stimmen, geben alle Tests true aus

  // Alternativen:
  // static String regexGeradzahligA = "((a|b)a)*((a|b)|)"; // aa ba aab b

  public static void main(String[] args) {
    testregexABBA();
    testregexGeradeA();
    testregexUngeradeB();
    testregexGeradzahligA();
    testregexEMAIL();
  }

  public static void testregexABBA() {
    boolean[] b = new boolean[7];
    b[0] = "abba".matches(regexABBA);
    b[1] = !"aba".matches(regexABBA);
    b[2] = "abbaabbaabba".matches(regexABBA);
    b[3] = "abababbaaabaaaabaaba".matches(regexABBA);
    b[4] = !"ab".matches(regexABBA);
    b[5] = !"bbb".matches(regexABBA);
    b[6] = !"".matches(regexABBA);
    if (b[0] & b[1] & b[2] & b[3] & b[4] & b[5] & b[6]) {
      System.out.println("Alle ABBA-Tests bestanden. Dein RegEx stimmt!");
    } else {
      for (int i = 0; i < b.length; i++) {
        if (!b[i]) {
          System.out.println("Test mit dem Index" + i + " leider nicht bestanden.");
        }
      }
    }
  }

  public static void testregexGeradeA() {
    boolean[] b = new boolean[7];
    b[0] = "aa".matches(regexGeradeA);
    b[1] = !"aaa".matches(regexGeradeA);
    b[2] = "abbaabbaabba".matches(regexGeradeA);
    b[3] = !"abababbaaabaaaabaaba".matches(regexGeradeA);
    b[4] = !"ab".matches(regexGeradeA);
    b[5] = "bbb".matches(regexGeradeA);
    b[6] = "".matches(regexGeradeA);
    if (b[0] & b[1] & b[2] & b[3] & b[4] & b[5] & b[6]) {
      System.out.println("Alle GeradeA-Tests bestanden. Dein RegEx stimmt!");
    } else {
      for (int i = 0; i < b.length; i++) {
        if (!b[i]) {
          System.out.println("Test mit dem Index" + i + " leider nicht bestanden.");
        }
      }
    }
  }

  public static void testregexUngeradeB() {
    boolean[] b = new boolean[7];
    b[0] = "b".matches(regexUngeradeB);
    b[1] = !"bb".matches(regexUngeradeB);
    b[2] = !"abbaabbaabba".matches(regexUngeradeB);
    b[3] = "abababbaaabaaaabaaba".matches(regexUngeradeB);
    b[4] = "ab".matches(regexUngeradeB);
    b[5] = "bbb".matches(regexUngeradeB);
    b[6] = !"".matches(regexUngeradeB);
    if (b[0] & b[1] & b[2] & b[3] & b[4] & b[5] & b[6]) {
      System.out.println("Alle UngeradeB-Tests bestanden. Dein RegEx stimmt!");
    } else {
      for (int i = 0; i < b.length; i++) {
        if (!b[i]) {
          System.out.println("Test mit dem Index" + i + " leider nicht bestanden.");
        }
      }
    }
  }

  public static void testregexGeradzahligA() {
    boolean[] b = new boolean[7];
    b[0] = !"ab".matches(regexGeradzahligA);
    b[1] = "b".matches(regexGeradzahligA);
    b[2] = "babab".matches(regexGeradzahligA);
    b[3] = !"bababaab".matches(regexGeradzahligA);
    b[4] = "ba".matches(regexGeradzahligA);
    b[5] = "aaa".matches(regexGeradzahligA);
    b[6] = "".matches(regexGeradzahligA);
    if (b[0] & b[1] & b[2] & b[3] & b[4] & b[5] & b[6]) {
      System.out.println("Alle GeradzahligeA-Tests bestanden. Dein RegEx stimmt!");
    } else {
      for (int i = 0; i < b.length; i++) {
        if (!b[i]) {
          System.out.println("Test mit dem Index" + i + " leider nicht bestanden.");
        }
      }
    }
  }

  public static void testregexEMAIL() {
    boolean[] b = new boolean[7];
    b[0] = "3@s.de".matches(regexEMAIL);
    b[1] = !"@0.de".matches(regexEMAIL);
    b[2] = "asdf@asdf.com".matches(regexEMAIL);
    b[3] = !"@.de".matches(regexEMAIL);
    b[4] = "s@1.com".matches(regexEMAIL);
    b[5] = !"a@a".matches(regexEMAIL);
    b[6] = !"".matches(regexEMAIL);
    if (b[0] & b[1] & b[2] & b[3] & b[4] & b[5] & b[6]) {
      System.out.println("Alle Email-Tests bestanden. Dein RegEx stimmt!");
    } else {
      for (int i = 0; i < b.length; i++) {
        if (!b[i]) {
          System.out.println("Test mit dem Index" + i + " leider nicht bestanden.");
        }
      }
    }
  }
}
