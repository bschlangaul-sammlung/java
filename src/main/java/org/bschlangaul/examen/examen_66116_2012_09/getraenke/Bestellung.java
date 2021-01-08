package org.bschlangaul.examen.examen_66116_2012_09.getraenke;

import java.text.DecimalFormat;

/**
 * „Schreiben Sie eine Klasse Bestellung“
 */
public class Bestellung {
  /**
   * „Jeder Bestellung soll eine eindeutige Bestellnummer zugeordnet werden, die
   * über den Konstruktoraufruf erstellt wird.“
   */
  int bestellNummer;

  /**
   * „Außerdem soll zu jeder Bestellung der Name des Kunden gespeichert werden.“
   */
  String kundenName;

  /**
   * „sowie eine einfach verkettete Liste der bestellten Getränkekästen. “
   */
  Kasten kästen = null;

  /**
   * „Jeder Bestellung soll eine eindeutige Bestellnummer zugeordnet werden, die
   * über den Konstruktoraufruf erstellt wird. Außerdem soll zu jeder Bestellung
   * der Name des Kunden gespeichert werden.“
   *
   * @param bestellNummer Die Nummer der Getränkebestellung.
   * @param kundenName    Der Name des/der KundenIn.
   */
  public Bestellung(int bestellNummer, String kundenName) {
    this.bestellNummer = bestellNummer;
    this.kundenName = kundenName;
  }

  /**
   * „Die Klasse Bestellung soll weiterhin eine Methode beinhalten, die den
   * Gesamtpreis der Bestellung ermittelt.“
   *
   * @return Der Gesamtpreis der Getränkebestellung.
   */
  double berechneGesamtPreis() {
    double gesamtPreis = 0;
    Kasten kasten = kästen;
    while (kasten != null) {
      gesamtPreis += kasten.berechneGesamtPreis();
      kasten = kasten.nächsterKasten;
    }
    return gesamtPreis;
  }

  /**
   * Nicht verlangt. Könnte auch in die Test-Methode geschrieben werden.
   *
   * @param flaschen     Die Belegung des Kasten mit Flaschen als
   *                     zweidimensionales Feld der Flaschenpreise ohne
   *                     Flaschenpfand.
   * @param flaschenPfad Die Höhe des Flaschenpfads, dass für alle Flaschen in
   *                     diesem Kasten gleich ist.
   */
  void bestelleKasten(double[][] flaschen, double flaschenPfad) {
    Kasten bestellterKasten = new Kasten(flaschen, flaschenPfad);
    if (kästen == null) {
      kästen = bestellterKasten;
      return;
    }
    Kasten kasten = kästen;

    Kasten letzterKasten = null;
    while (kasten != null) {
      letzterKasten = kasten;
      kasten = kasten.nächsterKasten;
    }
    letzterKasten.nächsterKasten = bestellterKasten;
  }

  /**
   * Kleines Schmankerl. Nicht verlangt. Damit wir nicht 9.899999999999999 als
   * Aufgabe bekommen.
   *
   * @param preis Ein Preis als Gleitkommazahl.
   *
   * @return Der Preis als Text mit zwei Stellen nach dem Komma.
   */
  static String runde(double preis) {
    DecimalFormat df = new DecimalFormat("#.##");
    df.setMinimumFractionDigits(2);
    return df.format(preis);
  }

  /**
   * Die main-Methode soll hier als Testmethode verwendet werden:
   *
   * „Schreiben Sie ein kleines Testprogramm, das eine Bestellung erstellt, die
   * zwei Getränkekästen umfasst. Der erste Kasten soll ein 1 x 1 Getränkekasten
   * mit einer Flasche zu 0,75 Euro sein, der zweite Kasten soll - wie in
   * Abbildung 1 dargestellt - ein 3 x 3 Getränkekasten mit 3 Flaschen zu 0,7 Euro
   * auf der Diagonalen und 3 weiteren Flaschen zu je 1 Euro sein. Das
   * Flaschenpfand beider Kästen beträgt 0,15 Euro pro Flasche, das Kastenpfand
   * 1,50 Euro. Anschließend soll der Preis der Bestellung berechnet und auf der
   * Standardausgabe ausgegeben werden.“
   *
   * @param args Kommandozeilenargumente, die uns nicht zu interessieren brauchen.
   */
  public static void main(String[] args) {
    Bestellung bestellung = new Bestellung(1, "Hermine Bschlangaul");

    // Müsste eigentlich nicht mehr gesetzt werden, da wir es schon in der
    // Klassendefinition gesetzt haben.
    Kasten.kastenPfad = 1.50;

    bestellung.bestelleKasten(new double[][] { { 0.75 } }, 0.15);
    bestellung.bestelleKasten(new double[][] { { 1.0, 1.0, 0.7 }, { 1.0, 0.7, 0 }, { 0.7, 0, 0 } }, 0.15);

    // Oder kürzer
    // bestellung.bestelleKasten(new double[][] { { 1, 1, .7 }, { 1, .7 }, { .7 } }, .15);

    // Gegenrechnung:
    // 1 x 0.75 = 0.75
    // 3 x 1.00 = 3.00
    // 3 x 0.70 = 2.10
    // 7 x 0.15 = 1.05 (Flaschenpfad)
    // 3 x 1.50 = 3.00 (Kastenpfand)
    // ----
    // 9.90
    System.out.println("Der Gesamtpreis der Getränkebestellung beträgt: " + runde(bestellung.berechneGesamtPreis()) + " €");
  }
}
