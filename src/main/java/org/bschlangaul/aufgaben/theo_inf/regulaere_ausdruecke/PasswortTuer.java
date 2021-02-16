package org.bschlangaul.aufgaben.theo_inf.regulaere_ausdruecke;

public class PasswortTuer {

  public static String b = "[a-z]";
  public static String Z = "\\d";
  public static String S = "[!\"#$%&\'\\(\\)\\*\\+,-\\./:;<=>\\?@\\[\\\\]\\^`\\{\\|\\}~]";

  public static void matches(String passwort) {
    System.out.print("Das Passwort " + passwort + " ");
    // "(b(S|b)Z)|(bZ(S|Z|b))|(Zb(S|Z|b))|(Z(S|Z)b)"
    if (passwort.matches(String.format("%s|%s|%s|%s",
        String.format("(%s(%s|%s)%s)", b, S, b, Z),
        String.format("(%s%s(%s|%s|%s))", b, Z, S, Z, b),
        String.format("(%s%s(%s|%s|%s))", Z, b, S, Z, b),
        String.format("(%s(%s|%s)%s)", Z, S, Z, b)))) {
      System.out.print("ist ein");
    } else {
      System.out.print("ist kein");
    }
    System.out.println(" valides Passwort.");
  }

  public static void main(String[] args) {
    matches("ab1");
  }
}
