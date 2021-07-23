
package io.bretty.solver.normalization;

import java.util.Set;

/**
 * @author Hermine Bschlangaul
 */
public class CliOutput {
  Set<FuncDep> funcDeps;
  Set<Attribute> attributes;

  public CliOutput(String attributes, String funcDeps) {
    this.attributes = Attribute.getSet(attributes);
    this.funcDeps = FuncDep.getSet(funcDeps);
  }

  public CliOutput(String funcDeps) {
    this.funcDeps = FuncDep.getSet(funcDeps);
    this.attributes = FuncDep.extractAttributes(this.funcDeps);
  }

  public void findKeys() {
    Set<Set<Attribute>> superkeys = Algos.superKeys(attributes, funcDeps);
    Set<Set<Attribute>> keys = Algos.keys(attributes, funcDeps);

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
    Set<FuncDep> mb = Algos.minimalBasis(funcDeps);
    for (FuncDep fd : mb) {
      System.out.println(fd);
    }
  }

  public void isIn3NF() {
    Set<FuncDep> violating = Algos.check3NF(attributes, funcDeps);
    System.out.println("3NF = " + violating.isEmpty());
    if (!violating.isEmpty()) {
      printSet(violating);
    }
  }

  public void isInBCNF() {
    Set<FuncDep> violating = Algos.checkBCNF(attributes, funcDeps);
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
