
package org.bschlangaul.db.normalisation;

import java.util.Set;

/**
 * @author Hermine Bschlangaul
 */
public class CliOutput {
  Set<Abhaengigkeit> funcDeps;
  Set<Attribut> attributes;

  public CliOutput(String attributes, String funcDeps) {
    this.attributes = Attribut.getSet(attributes);
    this.funcDeps = Abhaengigkeit.getSet(funcDeps);
  }

  public CliOutput(String funcDeps) {
    this.funcDeps = Abhaengigkeit.getSet(funcDeps);
    this.attributes = Abhaengigkeit.extractAttributes(this.funcDeps);
  }

  public void findKeys() {
    Set<Set<Attribut>> superkeys = AlgorithmenSammlung.berechneSuperSchlüssel(attributes, funcDeps);
    Set<Set<Attribut>> keys = AlgorithmenSammlung.berechneKandidatenSchlüssel(attributes, funcDeps);

    System.out.println("Alle Superschlüssel: ");
    for (Set<Attribut> sa : superkeys) {
      System.out.println(sa);
    }
    System.out.println();
    System.out.println("Alle Schlüsselkandidaten: ");
    for (Set<Attribut> sa : keys) {
      System.out.println(sa);
    }
  }

  public void findMinimalCover() {
    System.out.println("Kanonische Überdeckung: ");
    Set<Abhaengigkeit> mb = AlgorithmenSammlung.minimalBasis(funcDeps);
    for (Abhaengigkeit fd : mb) {
      System.out.println(fd);
    }
  }

  public void isIn3NF() {
    Set<Abhaengigkeit> violating = AlgorithmenSammlung.check3NF(attributes, funcDeps);
    System.out.println("3NF = " + violating.isEmpty());
    if (!violating.isEmpty()) {
      printSet(violating);
    }
  }

  public void isInBCNF() {
    Set<Abhaengigkeit> violating = AlgorithmenSammlung.checkBCNF(attributes, funcDeps);
    System.out.println("BCNF = " + violating.isEmpty());
    if (!violating.isEmpty()) {
      printSet(violating);
    }
  }

  private <T> void printSet(Set<T> s) {
    for (T t : s) {
      System.out.println(t);
    }
  }
}
