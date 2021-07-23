/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package org.bschlangaul.db.normalisation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Demo {

  public static void basic() {
    Attribut time = new Attribut("Time");
    Attribut classroom = new Attribut("Classroom");
    Attribut course = new Attribut("Course");

    Abhaengigkeit fd = new Abhaengigkeit.Builder().left(time, classroom).right(course).build();

    System.out.println("time = " + time);
    System.out.println("classroom = " + classroom);
    System.out.println("course = " + course);
    System.out.println("FuncDep = " + fd);
  }

  public static void check3NF() {
    Set<Attribut> attrs = Attribut.getSet("A, B, C");
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("A,B->C; C->B");
    System.out.println("BCNF = " + AlgorithmenSammlung.checkBCNF(attrs, fds).isEmpty());
    System.out.println("3NF = " + AlgorithmenSammlung.check3NF(attrs, fds).isEmpty());
  }

  public static void checkBCNF() {
    Set<Attribut> attrs = Attribut.getSet("name, location, favAppl, application, provider");
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("name->location; name->favAppl; application->provider");
    System.out.println(AlgorithmenSammlung.checkBCNF(attrs, fds));
  }

  // public static void checkLossy() {
  // Set<Attribute> attrs = Attribute.getSet("A,B,C,D,E");
  // Set<Attribute> sub1 = Attribute.getSet("A,B,C");
  // Set<Attribute> sub2 = Attribute.getSet("A,D,E");
  // Set<FuncDep> fds = FuncDep.getSet("A->B,C;C,D->E;E->A;B->D");
  // Set<FuncDep> lost = Algos.checkLossyDecomposition(attrs, fds, sub1, sub2);
  // printSet(lost);
  // }

  public static void closure() {
    Set<Attribut> attrs = Attribut.getSet("C,S");
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("C->T;H,R->C;H,T->R;C,S->G;H,S->R");

    System.out.println(AlgorithmenSammlung.berechneAttributHülle(attrs, fds));
  }

  public static void combineRight() {
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("" + "A->B;" + "A,B->B,C;" + "A->C;" + "B,C->D;" + "B,C->C,E");
    AlgorithmenSammlung.combineRight(fds);
    AlgorithmenSammlung.removeTrivial(fds);
    printSet(fds);
  }

  public static void decompose() {
    Set<Attribut> attrs = Attribut.getSet("name, location, favAppl, application, provider");
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("name->location; name->favAppl; application->provider");
    Relation original = new Relation(attrs, fds);

    Set<Relation> decomposed = original.decomposeToBCNF();

    for (Relation r : decomposed) {
      System.out.println(r);
      System.out.println();
    }
  }

  public static void decomposeFailed() {
    Relation original = new Relation("A, B, C", "A,B->C; C->B");

    Set<Relation> decomposed = original.decomposeToBCNF();

    for (Relation r : decomposed) {
      System.out.println(r);
      System.out.println();
    }
  }

  public static void decomposeTo3NF() {
    Set<Attribut> attrs = Attribut.getSet("C, T, H, R, S, G");
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("C->T;H,R->C;H,T->R;C,S->G;H,S->R");
    Relation original = new Relation(attrs, fds);

    Set<Relation> decomposed = original.decomposeTo3NF();

    for (Relation r : decomposed) {
      System.out.println(r);
      System.out.println();
    }
  }

  public static void equivalent() {
    Set<Abhaengigkeit> a = Abhaengigkeit.getSet("A->C; A,C->D; E->A,D; E->H");
    Set<Abhaengigkeit> b = Abhaengigkeit.getSet("A->C,D; E->A,H");
    System.out.println(AlgorithmenSammlung.equivalent(a, b));
  }

  public static void findKeys() {

    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("A, B -> C; C, D -> E; C -> A; C -> D; D -> B");
    Set<Attribut> atts = Attribut.getSet("A, B, C, D, E");

    Set<Set<Attribut>> keys = AlgorithmenSammlung.berechneKandidatenSchlüssel(atts, fds);
    for (Set<Attribut> sa : keys) {
      System.out.println(sa);
    }
  }

  public static void findSuperKeys() {
    String[] exprs = { "A, B -> C", "C, D -> E", "C -> A", "C -> D", "D -> B" };

    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet(exprs);
    Set<Attribut> atts = Attribut.getSet("A, B, C, D, E");

    Set<Set<Attribut>> keys = AlgorithmenSammlung.berechneSuperSchlüssel(atts, fds);
    for (Set<Attribut> sa : keys) {
      System.out.println(sa);
    }

  }

  public static void keys() {
    String[] exprs = { "A, B -> C", "C, D -> E", "C -> A", "C -> D", "D -> B" };

    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet(exprs);
    Set<Attribut> atts = Attribut.getSet("A, B, C, D, E");

    Set<Set<Attribut>> superkeys = AlgorithmenSammlung.berechneSuperSchlüssel(atts, fds);
    Set<Set<Attribut>> keys = AlgorithmenSammlung.berechneKandidatenSchlüssel(atts, fds);

    System.out.println("All the super keys: ");
    for (Set<Attribut> sa : superkeys) {
      System.out.println(sa);
    }
    System.out.println();
    System.out.println("All the candidate keys: ");
    for (Set<Attribut> sa : keys) {
      System.out.println(sa);
    }
  }

  public static void minimalBasis() {
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("name -> location;name -> favAppl;appl, name -> favAppl");
    Set<Abhaengigkeit> mb = AlgorithmenSammlung.minimalBasis(fds);
    for (Abhaengigkeit fd : mb) {
      System.out.println(fd);
    }
  }

  public static void powerset() {
    Set<Attribut> attrs = Attribut.getSet("A,B,C");
    Set<Attribut> notin = Attribut.getSet("D,E");
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("A->B,C;C,D->E;E->A;B->D");
    Set<Set<Attribut>> powerset = AlgorithmenSammlung.erzeugePotenzMenge(attrs);
    Map<Set<Attribut>, Set<Attribut>> map = new HashMap<Set<Attribut>, Set<Attribut>>();
    for (Set<Attribut> sa : powerset) {
      map.put(sa, AlgorithmenSammlung.berechneAttributHülle(sa, fds));
    }
    for (Set<Attribut> k : map.keySet()) {
      Set<Attribut> v = map.get(k);
      v.removeAll(notin);
      // v.removeAll(k);
      System.out.println(k + " : " + v);
    }

  }

  public static <T> void printSet(Set<T> s) {
    for (T t : s) {
      System.out.println(t);
    }
  }

  public static void projection() {
    Set<Attribut> attrs = Attribut.getSet("name, location, favAppl, appl");
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("name->location,favAppl; appl->provider");
    Set<Abhaengigkeit> result = AlgorithmenSammlung.projection(attrs, fds);
    // System.out.println(result);
    for (Abhaengigkeit fd : result) {
      System.out.println(fd);
    }
  }

  public static void relation() {
    Relation r = new Relation("A, B, C", "A,B->C; C->B");
    System.out.println(r.getSuperkeys());
  }

  public static void removeFD() {
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("A->B,C;B->C;A->B;A,B->C");
    AlgorithmenSammlung.splitRight(fds);
    int count = AlgorithmenSammlung.removeUnnecessaryEntireFD(fds);
    System.out.println("Removed = " + count);
    printSet(fds);
  }

  public static void removeTrivial() {
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("A->B;" + "A,B->B;" + "A,B->A;" + "C->C;" + "C,D,E,F->C,D,F");
    AlgorithmenSammlung.removeTrivial(fds);
    printSet(fds);
  }

  public static void main(String[] args) {
    keys();
  }

}
