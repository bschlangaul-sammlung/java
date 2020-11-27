package org.bschlangaul.aufgaben.aud.pu_1;

import java.util.Scanner;

public class Rater {
  public static void main(String[] args) {
    System.out.println("Ihre Zahl ist " + rateIterativ(1, 500));
  }

  public static int rateRekursiv(int anfang, int ende) {
    int mitte;
    int antwort;
    int ergebnis;
    if (anfang == ende) {
      ergebnis = anfang; // Abbruchfall
    } else {
      mitte = (anfang + ende) / 2; // halbiere Intervall
      System.out.println("Ist Ihre Zahl groesser als " + mitte + "? 0: ja, 1: nein");
      Scanner scanner = new Scanner(System.in); // Antwort einlesen
      antwort = scanner.nextInt();
      scanner.close();
      if (antwort == 0) { // suche rechts
        ergebnis = rateRekursiv(mitte + 1, ende);
      } else {
        ergebnis = rateRekursiv(anfang, mitte); // suche links
      }
    }
    return ergebnis;
  }

  public static int rateIterativ(int anfang, int ende) {
    int mitte;
    int antwort;
    while (anfang != ende) {
      mitte = (anfang + ende) / 2; // halbiere das Intervall
      System.out.println("Ist Ihre Zahl groesser als " + mitte + "? 0:ja, 1: nein");
      Scanner scanner = new Scanner(System.in); // Antwort einlesen
      antwort = scanner.nextInt();
      scanner.close();
      if (antwort == 0) {
        anfang = mitte + 1; // suche rechts
      } else {
        ende = mitte; // suche links
      }
    }
    return anfang;
  }

}
