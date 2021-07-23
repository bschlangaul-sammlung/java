/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package io.bretty.solver.normalization;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Demo {

  public static void basic() {
    Attribute time = new Attribute("Time");
    Attribute classroom = new Attribute("Classroom");
    Attribute course = new Attribute("Course");

    FunctionalDependency fd = new FunctionalDependency.Builder().left(time, classroom).right(course).build();

    System.out.println("time = " + time);
    System.out.println("classroom = " + classroom);
    System.out.println("course = " + course);
    System.out.println("FuncDep = " + fd);
  }

  public static void check3NF() {
    Set<Attribute> attrs = Attribute.getSet("A, B, C");
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("A,B->C; C->B");
    System.out.println("BCNF = " + AlgorithmCollection.checkBCNF(attrs, fds).isEmpty());
    System.out.println("3NF = " + AlgorithmCollection.check3NF(attrs, fds).isEmpty());
  }

  public static void checkBCNF() {
    Set<Attribute> attrs = Attribute.getSet("name, location, favAppl, application, provider");
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("name->location; name->favAppl; application->provider");
    System.out.println(AlgorithmCollection.checkBCNF(attrs, fds));
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
    Set<Attribute> attrs = Attribute.getSet("C,S");
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("C->T;H,R->C;H,T->R;C,S->G;H,S->R");

    System.out.println(AlgorithmCollection.closure(attrs, fds));
  }

  public static void combineRight() {
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("" + "A->B;" + "A,B->B,C;" + "A->C;" + "B,C->D;" + "B,C->C,E");
    AlgorithmCollection.combineRight(fds);
    AlgorithmCollection.removeTrivial(fds);
    printSet(fds);
  }

  public static void decompose() {
    Set<Attribute> attrs = Attribute.getSet("name, location, favAppl, application, provider");
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("name->location; name->favAppl; application->provider");
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
    Set<Attribute> attrs = Attribute.getSet("C, T, H, R, S, G");
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("C->T;H,R->C;H,T->R;C,S->G;H,S->R");
    Relation original = new Relation(attrs, fds);

    Set<Relation> decomposed = original.decomposeTo3NF();

    for (Relation r : decomposed) {
      System.out.println(r);
      System.out.println();
    }
  }

  public static void equivalent() {
    Set<FunctionalDependency> a = FunctionalDependency.getSet("A->C; A,C->D; E->A,D; E->H");
    Set<FunctionalDependency> b = FunctionalDependency.getSet("A->C,D; E->A,H");
    System.out.println(AlgorithmCollection.equivalent(a, b));
  }

  public static void findKeys() {

    Set<FunctionalDependency> fds = FunctionalDependency.getSet("A, B -> C; C, D -> E; C -> A; C -> D; D -> B");
    Set<Attribute> atts = Attribute.getSet("A, B, C, D, E");

    Set<Set<Attribute>> keys = AlgorithmCollection.computeCandidateKeys(atts, fds);
    for (Set<Attribute> sa : keys) {
      System.out.println(sa);
    }
  }

  public static void findSuperKeys() {
    String[] exprs = { "A, B -> C", "C, D -> E", "C -> A", "C -> D", "D -> B" };

    Set<FunctionalDependency> fds = FunctionalDependency.getSet(exprs);
    Set<Attribute> atts = Attribute.getSet("A, B, C, D, E");

    Set<Set<Attribute>> keys = AlgorithmCollection.computeSuperKeys(atts, fds);
    for (Set<Attribute> sa : keys) {
      System.out.println(sa);
    }

  }

  public static void keys() {
    String[] exprs = { "A, B -> C", "C, D -> E", "C -> A", "C -> D", "D -> B" };

    Set<FunctionalDependency> fds = FunctionalDependency.getSet(exprs);
    Set<Attribute> atts = Attribute.getSet("A, B, C, D, E");

    Set<Set<Attribute>> superkeys = AlgorithmCollection.computeSuperKeys(atts, fds);
    Set<Set<Attribute>> keys = AlgorithmCollection.computeCandidateKeys(atts, fds);

    System.out.println("All the super keys: ");
    for (Set<Attribute> sa : superkeys) {
      System.out.println(sa);
    }
    System.out.println();
    System.out.println("All the candidate keys: ");
    for (Set<Attribute> sa : keys) {
      System.out.println(sa);
    }
  }

  public static void minimalBasis() {
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("name -> location;name -> favAppl;appl, name -> favAppl");
    Set<FunctionalDependency> mb = AlgorithmCollection.minimalBasis(fds);
    for (FunctionalDependency fd : mb) {
      System.out.println(fd);
    }
  }

  public static void powerset() {
    Set<Attribute> attrs = Attribute.getSet("A,B,C");
    Set<Attribute> notin = Attribute.getSet("D,E");
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("A->B,C;C,D->E;E->A;B->D");
    Set<Set<Attribute>> powerset = AlgorithmCollection.powerSet(attrs);
    Map<Set<Attribute>, Set<Attribute>> map = new HashMap<Set<Attribute>, Set<Attribute>>();
    for (Set<Attribute> sa : powerset) {
      map.put(sa, AlgorithmCollection.closure(sa, fds));
    }
    for (Set<Attribute> k : map.keySet()) {
      Set<Attribute> v = map.get(k);
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
    Set<Attribute> attrs = Attribute.getSet("name, location, favAppl, appl");
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("name->location,favAppl; appl->provider");
    Set<FunctionalDependency> result = AlgorithmCollection.projection(attrs, fds);
    // System.out.println(result);
    for (FunctionalDependency fd : result) {
      System.out.println(fd);
    }
  }

  public static void relation() {
    Relation r = new Relation("A, B, C", "A,B->C; C->B");
    System.out.println(r.getSuperkeys());
  }

  public static void removeFD() {
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("A->B,C;B->C;A->B;A,B->C");
    AlgorithmCollection.splitRight(fds);
    int count = AlgorithmCollection.removeUnnecessaryEntireFD(fds);
    System.out.println("Removed = " + count);
    printSet(fds);
  }

  public static void removeTrivial() {
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("A->B;" + "A,B->B;" + "A,B->A;" + "C->C;" + "C,D,E,F->C,D,F");
    AlgorithmCollection.removeTrivial(fds);
    printSet(fds);
  }

  public static void main(String[] args) {
    keys();
  }

}
