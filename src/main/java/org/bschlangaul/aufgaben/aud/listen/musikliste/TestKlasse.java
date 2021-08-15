package org.bschlangaul.aufgaben.aud.listen.musikliste;

public class TestKlasse {

  public static void main(String[] args) {
    MusikListe test = new MusikListe();

    MusikStueck stueck1 = new MusikStueck("Hangover");
    MusikStueck stueck2 = new MusikStueck("Roar");
    MusikStueck stueck3 = new MusikStueck("On the Floor");
    MusikStueck stueck4 = new MusikStueck("Whistle");

    Knoten platz1 = new Knoten(stueck1);
    Knoten platz2 = new Knoten(stueck2);
    Knoten platz3 = new Knoten(stueck3);
    Knoten platz4 = new Knoten(stueck4);

    test.setzeErsten(platz1);
    platz1.setzeNächsten(platz2);
    platz2.setzenVorherigen(platz1);
    platz2.setzeNächsten(platz3);
    platz3.setzenVorherigen(platz2);
    platz3.setzeNächsten(platz4);
    platz4.setzenVorherigen(platz3);
    test.aktualisiereAnzahl();
    System.out.println(test.gibMusikstückListe());
    System.out.println("Der erste Knoten der Liste wird entnommen: " + test.entnimmOben().gibMusikstück().gibTitel());
    System.out.println(test.gibMusikstückListe());
    System.out.println("Der zweite Knoten der Liste wird gegeben: " + test.gibKnoten(2).gibMusikstück().gibTitel());
    System.out.println("Die Anzahl der Listeneinträge wird rekursiv ermittelt: " + test.zähleEinträge());
  }
}
