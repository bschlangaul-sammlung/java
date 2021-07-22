
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
}
