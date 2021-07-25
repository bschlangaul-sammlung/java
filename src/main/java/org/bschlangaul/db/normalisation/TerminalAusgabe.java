
package org.bschlangaul.db.normalisation;

import org.bschlangaul.helfer.Farbe;
import org.bschlangaul.helfer.TextAusschnitt;

import java.util.List;
import java.util.Set;

/**
 * Diese Klassen bündelt die Algorithmen zur Datenbanknormalisation und bereitet
 * sie für die Kommandozeilen- bzw Terminalausgabe auf.
 *
 * @author Hermine Bschlangaul
 */
public class TerminalAusgabe {
  Set<Abhaengigkeit> abhängigkeiten;
  Set<Attribut> attribute;

  public TerminalAusgabe(String attribute, String abhaengigkeiten) {
    this.attribute = Attribut.getSet(attribute);
    this.abhängigkeiten = Abhaengigkeit.getSet(abhaengigkeiten);
  }

  public TerminalAusgabe(String abhaengigkeiten) {
    this.abhängigkeiten = Abhaengigkeit.getSet(abhaengigkeiten);
    this.attribute = Abhaengigkeit.extrahiereAttribute(this.abhängigkeiten);
  }

  /**
   * Zeige die Funktionalen Abhängigkeiten im Terminal.
   *
   * @return Die Menge an Funktionalen Abhängigkeiten.
   */
  public Set<Abhaengigkeit> zeigeAbhängigkeiten() {
    gibÜberschriftAus("Funktionale Abhängigkeiten");
    for (Abhaengigkeit abhängigkeit : abhängigkeiten) {
      System.out.println(abhängigkeit);
    }
    return abhängigkeiten;
  }

  /**
   * Zeige die Attribute im Terminal.
   *
   * @return Die Attributemenge.
   */
  public Set<Attribut> zeigeAttribute() {
    gibÜberschriftAus("Attribute");
    System.out.println(attribute);
    return attribute;
  }

  public static TerminalAusgabe sucheAbhängigkeiten(String pfad) {
    List<String> ausschnitte = TextAusschnitt.sucheInDatei(pfad, TextAusschnitt
        .gibMakroRegex("(FA|liFunktionaleAbhaengigkeiten)", "[a-zA-Z_0-9$]*", "(?<markup>[^\\}]+)"));
    if (ausschnitte.size() > 0) {
      return new TerminalAusgabe(ausschnitte.get(0));
    }
    return null;
  }

  public void findeSchlüssel() {
    Set<Set<Attribut>> superSchlüssel = AlgorithmenSammlung.findeSuperSchlüssel(attribute, abhängigkeiten);
    gibÜberschriftAus("Alle Superschlüssel");
    for (Set<Attribut> attributMenge : superSchlüssel) {
      System.out.println(attributMenge);
    }

    gibÜberschriftAus("Alle Schlüsselkandidaten");

    Set<Set<Attribut>> kandidaten = AlgorithmenSammlung.findeKandidatenSchlüssel(attribute, abhängigkeiten);

    for (Set<Attribut> attributMenge : kandidaten) {
      System.out.println(attributMenge);
    }
  }

  public void findeKanonischeÜberdeckung() {
    gibÜberschriftAus("Kanonische Überdeckung");
    Set<Abhaengigkeit> überdeckung = AlgorithmenSammlung.findeKanonischeÜberdeckung(abhängigkeiten);
    for (Abhaengigkeit abhängigkeit : überdeckung) {
      System.out.println(abhängigkeit);
    }
  }

  private void gibÜberschriftAus(String text) {
    Farbe.druckeGelb("\n" + text + ":");
  }

  private String gibFormatierenWahrheitsText(boolean istWahr) {
    return istWahr ? Farbe.grün("ja") : Farbe.rot("nein");
  }

  public boolean istIn3NF() {
    Set<Abhaengigkeit> verletzende = AlgorithmenSammlung.check3NF(attribute, abhängigkeiten);
    Boolean ist = verletzende.isEmpty();
    System.out.println("\n" + Farbe.gelb("3NF = ") + gibFormatierenWahrheitsText(ist));
    if (!ist) {
      printSet(verletzende);
    }
    return ist;
  }

  public boolean istInBCNF() {
    Set<Abhaengigkeit> verletzende = AlgorithmenSammlung.checkBCNF(attribute, abhängigkeiten);
    Boolean ist = verletzende.isEmpty();
    System.out.println("\n" + Farbe.gelb("BCNF = ") + gibFormatierenWahrheitsText(ist));
    if (!ist) {
      printSet(verletzende);
    }
    return ist;
  }

  /**
   * Bündle alle Ausgaben unter einer Methode.
   */
  public void gibAllesAus() {
    zeigeAttribute();
    zeigeAbhängigkeiten();
    findeSchlüssel();
    findeKanonischeÜberdeckung();
    istIn3NF();
    istInBCNF();
  }

  private <T> void printSet(Set<T> s) {
    for (T t : s) {
      System.out.println(t);
    }
  }
}
