package org.bschlangaul.aufgaben.aud.listen.woerterbuch;

/**
 * <a href=
 * "https://www.geeksforgeeks.org/given-a-linked-list-which-is-sorted-how-will-you-insert-in-sorted-way">geeksforgeeks: Einfügen in sortiere Listen</a>
 */
public class Woerterbuch extends WoerterbuchEintrag {

  public void einfügen(String deutsch, String englisch) {
    WortPaar wort = new WortPaar(deutsch, englisch);

    // Spezialbehandlung, wenn vor das erste Wortpaar des Wörterbuchs eingefügt
    // werden muss.
    WortPaar kopf = gibNächstes();
    if (kopf == null || kopf.gibDeutschesWort().compareTo(wort.gibDeutschesWort()) >= 0) {
      wort.setzeNächstes(kopf);
      setzeNächstes(wort);
      return;
    }
    WortPaar vergleichsWort = gibNächstes();
    while (vergleichsWort.gibNächstes() != null
        && vergleichsWort.gibNächstes().gibDeutschesWort().compareTo(wort.gibDeutschesWort()) < 0) {
      vergleichsWort = vergleichsWort.gibNächstes();
    }
    wort.setzeNächstes(vergleichsWort.gibNächstes());
    vergleichsWort.setzeNächstes(wort);
  }

  public String übersetze(String deutsch) {
    if (gibNächstes() == null) {
      return "Noch keine Wörter im Wörterbuch.";
    }
    WortPaar wort = gibNächstes();
    while (wort != null) {
      if (wort.gibDeutschesWort().equals(deutsch)) {
        return wort.gibEnglischesWort();
      }
      wort = wort.gibNächstes();
    }
    return "Es konnte keine passende Übersetzung gefunden werden";
  }
}
