package org.bschlangaul.aufgaben.aud.examen_46115_2014_03.suchbaum;

public class BinaererSuchbaum {
  public Knoten wurzel;

  BinaererSuchbaum(Knoten wurzel) {
    this.wurzel = wurzel;
  }

  BinaererSuchbaum() {
    this.wurzel = null;
  }

  public void einfügen(Knoten knoten) {
    if (wurzel != null) {
      einfügen(knoten, wurzel);
    } else {
      wurzel = knoten;
      knoten.elternKnoten = wurzel;
    }
  }

  public void einfügen(int wert) {
    einfügen(new Knoten(wert));
  }

  public void einfügen(Knoten knoten, Knoten elternKnoten) {
    if (knoten.wert <= elternKnoten.wert) {
      if (elternKnoten.links != null) {
        einfügen(knoten, elternKnoten.links);
      } else {
        elternKnoten.links = knoten;
        knoten.elternKnoten = elternKnoten;
      }
    } else {
      if (elternKnoten.rechts != null) {
        einfügen(knoten, elternKnoten.rechts);
      } else {
        elternKnoten.rechts = knoten;
        knoten.elternKnoten = elternKnoten;
      }
    }
  }

  public Knoten suchen(int wert) {
    if (wurzel == null || wurzel.wert == wert) {
      return wurzel;
    } else {
      return suchen(wert, wurzel);
    }
  }

  public Knoten suchen(int wert, Knoten knoten) {
    if (knoten.wert == wert) {
      return knoten;
    } else if (wert < knoten.wert && knoten.links != null) {
      return suchen(wert, knoten.links);
    } else if (wert > knoten.wert && knoten.rechts != null) {
      return suchen(wert, knoten.rechts);
    }
    return null;
  }

  public void loescheKnoten(int w) {
    Knoten knoten = suchen(w);
    if (knoten == null) return;
    // Der Knoten hat keine Teilbäume.
    if (knoten.links == null && knoten.rechts == null) {
      if (w < knoten.elternKnoten.wert) {
        knoten.elternKnoten.links = null;
      } else {
        knoten.elternKnoten.rechts = null;
      }
    }

    // Der Knoten besitzt einen Teilbaum.
    // links
    else if (knoten.links != null && knoten.rechts == null) {
      knoten.elternKnoten.anhängen(knoten.links);
    }
    // rechts
    else if (knoten.links == null) {
      knoten.elternKnoten.anhängen(knoten.rechts);
    }

    // Der Knoten besitzt zwei Teilbäume.
    else {
      Knoten minimumKnoten = knoten.findeMiniumRechterTeilbaum();
      minimumKnoten.links = knoten.links;
      minimumKnoten.rechts = knoten.rechts;
      knoten.elternKnoten.anhängen(minimumKnoten);
    }
  }

  // Der Baum aus dem Foliensatz
  public BinaererSuchbaum erzeugeTestBaum() {
    BinaererSuchbaum binärerSuchbaum = new BinaererSuchbaum();
    binärerSuchbaum.einfügen(new Knoten(7));
    binärerSuchbaum.einfügen(new Knoten(3));
    binärerSuchbaum.einfügen(new Knoten(11));
    binärerSuchbaum.einfügen(new Knoten(2));
    binärerSuchbaum.einfügen(new Knoten(6));
    binärerSuchbaum.einfügen(new Knoten(9));
    binärerSuchbaum.einfügen(new Knoten(1));
    binärerSuchbaum.einfügen(new Knoten(5));
    return binärerSuchbaum;
  }

  public void ausgebenInOrder() {

  }

  public static void main(String[] args) {
    BinaererSuchbaum binärerSuchbaum = new BinaererSuchbaum();
    BinaererSuchbaum testBaum = binärerSuchbaum.erzeugeTestBaum();

    // Teste das Einfügen

    System.out.println(testBaum.wurzel.wert); // 7
    System.out.println(testBaum.wurzel.links.wert); // 3
    System.out.println(testBaum.wurzel.links.links.wert); // 2
    System.out.println(testBaum.wurzel.links.rechts.wert); // 6
    System.out.println(testBaum.wurzel.rechts.wert); // 11

    // Teste das Suchen

    System.out.println("Gesucht nach 5 und gefunden: " + testBaum.suchen(5).wert);
    System.out.println("Gesucht nach 9 und gefunden: " + testBaum.suchen(9).wert);
    System.out.println("Gesucht nach 7 und gefunden: " + testBaum.suchen(7).wert);
    System.out.println("Gesucht nach 10 und gefunden: " + testBaum.suchen(10));

    // Teste das Löschen

    // Der Knoten hat keine Teilbäume.
    System.out.println("Noch nicht gelöschter Knoten 9: " + testBaum.suchen(9).wert);
    testBaum.loescheKnoten(9);
    System.out.println("Gelöschter Knoten 9: " + testBaum.suchen(9));

    // Der Knoten hat einen Teilbaum.
    // fristen Testbaum erzeugen.
    testBaum = binärerSuchbaum.erzeugeTestBaum();
    Knoten elternKnoten = testBaum.suchen(3);
    System.out.println("Rechts Kind von 3 vor dem Löschen: " + elternKnoten.rechts.wert);
    testBaum.loescheKnoten(6);
    System.out.println("Rechts Kind von 3 nach dem Löschen: " + elternKnoten.rechts.wert);

    // Der Knoten hat zwei Teilbäume.
    // fristen Testbaum erzeugen.
    testBaum = binärerSuchbaum.erzeugeTestBaum();
    Knoten wurzel = testBaum.wurzel;
    System.out.println("Linkes Kind der Wurzel vor dem Löschen: " + wurzel.links.wert); // 5
    testBaum.loescheKnoten(3);
    System.out.println("Linkes Kind der Wurzel nach dem Löschen: " + wurzel.links.wert); // 3
  }
}
