
package io.bretty.solver.normalization;

import java.util.Set;

/**
 * @author Hermine Bschlangaul
 */
public class CliOutput {
  Set<FunctionalDependency> funcDeps;
  Set<Attribute> attributes;

  public CliOutput(String attributes, String funcDeps) {
    this.attributes = Attribute.getSet(attributes);
    this.funcDeps = FunctionalDependency.getSet(funcDeps);
  }

  public CliOutput(String funcDeps) {
    this.funcDeps = FunctionalDependency.getSet(funcDeps);
    this.attributes = FunctionalDependency.extractAttributes(this.funcDeps);
  }

  public void findKeys() {
    Set<Set<Attribute>> superkeys = AlgorithmCollection.computeSuperKeys(attributes, funcDeps);
    Set<Set<Attribute>> keys = AlgorithmCollection.computeCandidateKeys(attributes, funcDeps);

    System.out.println("Alle Superschlüssel: ");
    for (Set<Attribute> sa : superkeys) {
      System.out.println(sa);
    }
    System.out.println();
    System.out.println("Alle Schlüsselkandidaten: ");
    for (Set<Attribute> sa : keys) {
      System.out.println(sa);
    }
  }

  public void findMinimalCover() {
    System.out.println("Kanonische Überdeckung: ");
    Set<FunctionalDependency> mb = AlgorithmCollection.minimalBasis(funcDeps);
    for (FunctionalDependency fd : mb) {
      System.out.println(fd);
    }
  }

  public void isIn3NF() {
    Set<FunctionalDependency> violating = AlgorithmCollection.check3NF(attributes, funcDeps);
    System.out.println("3NF = " + violating.isEmpty());
    if (!violating.isEmpty()) {
      printSet(violating);
    }
  }

  public void isInBCNF() {
    Set<FunctionalDependency> violating = AlgorithmCollection.checkBCNF(attributes, funcDeps);
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
