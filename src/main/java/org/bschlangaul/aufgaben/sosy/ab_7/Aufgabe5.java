package org.bschlangaul.aufgaben.sosy.ab_7;

public class Aufgabe5 {
  int binToInt(String bin) {
    if (bin.isEmpty())
      return -1;
    int place = 1, value = 0;
    int length = bin.length() - 1;
    for (int i = length; i >= 0; --i) {
      char ch = bin.charAt(i);
      if (ch == '1') {
        value += place;
      } else if (ch == '0') {
        // do nothing
      } else {
        return -1;
      }
      place *= 2;
    }
    return value;
  }

  public static final String RESET = "\u001B[0m";
  public static final String ROT = "\u001B[31m";
  public static final String GRÜN = "\u001B[32m";

  static int binToIntLog(String bin) {
    System.out.println("\nInput: " + bin);
    System.out.print("S");
    System.out.print(1);
    if (bin.isEmpty()) {
      System.out.print("E");
      System.out.println("\nOutput: " + -1);
      return -1;
    }
    System.out.print(2);
    int place = 1, value = 0;
    int length = bin.length() - 1;
    System.out.print(3);
    for (int i = length; i >= 0; --i) {
      char ch = bin.charAt(i);
      System.out.print(5);
      if (ch == '1') {
        System.out.print(ROT + 7 + RESET);
        value += place;
      } else {
        System.out.print(GRÜN + 6 + RESET);
        if (ch == '0') {
          // do nothing
        } else {
          System.out.print("E");
          System.out.println("\nOutput: " + -1);
          return -1;
        }
      }
      System.out.print(8);
      place *= 2;
      System.out.print(3);
    }
    System.out.print(4);
    System.out.print("E");
    System.out.println("\nOutput: " + value);
    return value;
  }

  public static void main(String[] args) {
    binToIntLog(""); // p1
    binToIntLog("??"); // p2 not feasible
    binToIntLog("x01"); // p3
    binToIntLog("x"); // p4

    binToIntLog("11"); // p5
    binToIntLog("01"); // p6
    binToIntLog("00"); // p7
    binToIntLog("10"); // p8

    binToIntLog("x11"); // p9
    binToIntLog("x01"); // p10
    binToIntLog("x00"); // p11
    binToIntLog("x10"); // p12
  }
}
