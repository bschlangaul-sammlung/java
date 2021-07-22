
package io.bretty.solver.normalization;

import java.util.Set;

/**
 * Abgeleitet von Demo.java
 */
public class Ausgabe {

  public static void findeSchlüsselKandidaten(String attribute, String funcDep) {
    Set<FuncDep> fds = FuncDep.getSet(funcDep);
    Set<Attribute> atts = Attribute.getSet(attribute);

    Set<Set<Attribute>> superkeys = Algos.superKeys(atts, fds);
    Set<Set<Attribute>> keys = Algos.keys(atts, fds);

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

  public static void bestimmeKanonischeÜberdeckung(String funcDep) {
    System.out.println("Kanonische Überdeckung: ");

    Set<FuncDep> fds = FuncDep.getSet(funcDep);
    Set<FuncDep> mb = Algos.minimalBasis(fds);
    for (FuncDep fd : mb) {
      System.out.println(fd);
    }
  }

  public static void istIn3NF (String attribute, String funcDep) {
    Set<Attribute> attrs = Attribute.getSet(attribute);
    Set<FuncDep> fds = FuncDep.getSet(funcDep);
    Set<FuncDep> violating = Algos.check3NF(attrs, fds);
    System.out.println("3NF = " + violating.isEmpty());
    if (!violating.isEmpty()) {
      printSet(violating);
    }
  }

  public static void istInBCNF (String attribute, String funcDep) {
    Set<Attribute> attrs = Attribute.getSet(attribute);
    Set<FuncDep> fds = FuncDep.getSet(funcDep);
    Set<FuncDep> violating = Algos.checkBCNF(attrs, fds);
    System.out.println("BCNF = " + violating.isEmpty());
    if (!violating.isEmpty()) {
      printSet(violating);
    }
  }

  private static <T> void printSet(Set<T> s) {
    for (T t : s) {
      System.out.println(t);
    }
  }
}
