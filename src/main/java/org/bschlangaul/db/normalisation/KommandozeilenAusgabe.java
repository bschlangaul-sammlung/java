
package org.bschlangaul.db.normalisation;

import org.bschlangaul.helfer.Farbe;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hermine Bschlangaul
 */
public class KommandozeilenAusgabe {
  Set<Abhaengigkeit> abhängigkeiten;
  Set<Attribut> attribute;

  public KommandozeilenAusgabe(String attribute, String abhaengigkeiten) {
    this.attribute = Attribut.getSet(attribute);
    this.abhängigkeiten = Abhaengigkeit.getSet(abhaengigkeiten);
  }

  public KommandozeilenAusgabe(String abhaengigkeiten) {
    this.abhängigkeiten = Abhaengigkeit.getSet(abhaengigkeiten);
    this.attribute = Abhaengigkeit.extrahiereAttribute(this.abhängigkeiten);
  }

  public void zeigeAbhängigkeiten() {
    gibÜberschriftAus("Funktionale Abhängigkeiten");
    for (Abhaengigkeit abhängigkeit : abhängigkeiten) {
      System.out.println(abhängigkeit);
    }
  }

  public void zeigeAttribute() {
    gibÜberschriftAus("Attribute");
    System.out.println(attribute);
  }

  public static KommandozeilenAusgabe sucheAbhängigkeitenInText(String inhalt) {
    KommandozeilenAusgabe ausgabe = null;
    Pattern pattern = Pattern.compile("\\\\(FA|liFunktionaleAbhaengigkeiten)(\\[[a-zA-Z_0-9$]*\\])?\\{(?<abhaengigkeiten>[^\\}]+)\\}", Pattern.DOTALL);
    Matcher ergebnis = pattern.matcher(inhalt);
    if (ergebnis.find()) {
      ausgabe = new KommandozeilenAusgabe(ergebnis.group("abhaengigkeiten"));
    }
    return ausgabe;
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

  private String gibFormatierenWahrheitsText(boolean istWahr){
    return istWahr ? Farbe.grün("ja") : Farbe.rot("nein");
  }

  public void istIn3NF() {
    Set<Abhaengigkeit> verletzende = AlgorithmenSammlung.check3NF(attribute, abhängigkeiten);
    System.out.println("\n" + Farbe.gelb("3NF = ") + gibFormatierenWahrheitsText(verletzende.isEmpty()));
    if (!verletzende.isEmpty()) {
      printSet(verletzende);
    }
  }

  public void istInBCNF() {
    Set<Abhaengigkeit> verletzende = AlgorithmenSammlung.checkBCNF(attribute, abhängigkeiten);
    System.out.println("\n" + Farbe.gelb("BCNF = ") + gibFormatierenWahrheitsText(verletzende.isEmpty()));
    if (!verletzende.isEmpty()) {
      printSet(verletzende);
    }
  }

  public  void gibAllesAus() {
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
