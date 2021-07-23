/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package org.bschlangaul.db;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code Relation} class represent a relation consisting a set of
 * attributes and a set of FD's
 *
 * @author SUN XIMENG (Nathaniel)
 */
public final class Relation {

  private final Set<Attribut> attrs;
  private final Set<Abhaengigkeit> fds;

  /**
   * The default constructors
   *
   * @param attrs a set of attributes
   * @param fds   a set of FD's
   */
  public Relation(Set<Attribut> attrs, Set<Abhaengigkeit> fds) {
    this.attrs = new HashSet<>(attrs);
    this.fds = new HashSet<>(fds);
  }

  /**
   * Quickly construct a {@code Relation} object with two formatted strings, one
   * for attributes and another for FD's
   *
   * @param names a string formatted as the following example: "name, application,
   *              date, gender"
   * @param exprs a string formatted as the following example:
   *              "{@code a, b -> c; d -> e, f}"
   */
  public Relation(String names, String exprs) {
    this.attrs = Attribut.getSet(names);
    this.fds = Abhaengigkeit.getSet(exprs);
  }

  /**
   * Quickly construct a {@code Relation} object with two string arrays
   *
   * @param names each element will be used as the {@code name} of an
   *              {@code Attribute} object
   * @param exprs each element is formatted as the following example:
   *              "{@code a, b -> c, d}"
   */
  public Relation(String[] names, String[] exprs) {
    this.attrs = Attribut.getSet(names);
    this.fds = Abhaengigkeit.getSet(exprs);
  }

  /**
   * Decompose the current relation into a set of relations that satisfy 3NF, by
   * using the Lossless Join &amp; Dependency Preservation algorithm
   *
   * @return a set of decomposed relations
   */
  public Set<Relation> decomposeTo3NF() {
    Set<Relation> result = new HashSet<>();
    Set<Abhaengigkeit> mb = AlgorithmenSammlung.minimalBasis(this.fds);
    for (Abhaengigkeit fd : mb) {
      Set<Attribut> attrsNow = new HashSet<>(fd.getLeft());
      attrsNow.addAll(fd.getRight());
      Set<Abhaengigkeit> proj = AlgorithmenSammlung.projection(attrsNow, mb);
      result.add(new Relation(attrsNow, proj));
    }
    Set<Relation> toRemove = new HashSet<>();
    for (Relation a : result) {
      for (Relation b : result) {
        if (a != b && a.attrs.containsAll(b.attrs)) {
          toRemove.add(b);
        }
      }
    }
    result.removeAll(toRemove);
    Set<Set<Attribut>> keys = AlgorithmenSammlung.berechneKandidatenSchlüssel(this.attrs, mb);
    boolean contains = false;
    for (Relation r : result) {
      for (Set<Attribut> k : keys) {
        if (r.attrs.containsAll(k)) {
          contains = true;
          break;
        }
      }
      if (contains) {
        break;
      }
    }
    if (!contains) {
      Set<Attribut> key = null;
      for (Set<Attribut> k : keys) {
        key = k;
        break;
      }
      Set<Abhaengigkeit> proj = AlgorithmenSammlung.projection(key, mb);
      result.add(new Relation(key, proj));
    }
    return result;
  }

  /**
   * Decompose the current relation into a set of relations that satisfies BCNF,
   * regardless the possible loss of FD's
   *
   * @return a set of decomposed relations
   */
  public Set<Relation> decomposeToBCNF() {
    Set<Relation> result = new HashSet<>();

    // check if it's already in BCNF
    Set<Abhaengigkeit> violating = this.getFdsViolatingBCNF();
    if (violating.isEmpty()) {
      result.add(this);
      return result;
    }

    // if not, pick a violating FD to decompose
    Abhaengigkeit pick = null;
    for (Abhaengigkeit fd : violating) {
      pick = fd;
      break;
    }
    Set<Attribut> lefts = pick.getLeft();
    Set<Attribut> attrs1 = AlgorithmenSammlung.berechneAttributHülle(lefts, this.fds);
    Set<Attribut> attrs2 = new HashSet<>(this.attrs);
    attrs2.removeAll(attrs1);
    attrs2.addAll(lefts);
    Set<Abhaengigkeit> fds1 = AlgorithmenSammlung.projection(attrs1, this.fds);
    Set<Abhaengigkeit> fds2 = AlgorithmenSammlung.projection(attrs2, this.fds);

    // check if FDs are preserved
    /*
     * Set<FuncDep> fdsSep = new HashSet<FuncDep>(); fdsSep.addAll(fds1);
     * fdsSep.addAll(fds2); for(FuncDep fd : this.fds){
     * if(!Algos.closure(fd.getLeft(), fdsSep).containsAll(fd.getRight())){
     * result.add(this); return result; } }
     */

    // create two new relations
    Relation r1 = new Relation(attrs1, fds1);
    Relation r2 = new Relation(attrs2, fds2);

    // recursively decompose
    result.addAll(r1.decomposeToBCNF());
    result.addAll(r2.decomposeToBCNF());

    return result;

  }

  /**
   * A {@code Relation} object will equal to another if and only if: 1) they have
   * exactly the same set of attributes; 2) they have exactly the same set of FD's
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Relation)) {
      return false;
    }
    Relation r = (Relation) o;
    return r.attrs.equals(this.attrs) && r.fds.equals(this.fds);
  }

  /**
   *
   * @return a set of {@code Attribute} objects that appear in this relations
   */
  public Set<Attribut> getAttributes() {
    return new HashSet<>(this.attrs);
  }

  /**
   *
   * @return all FD's that violate the 3NF; an empty set if it's already in 3NF
   */
  public Set<Abhaengigkeit> getFdsViolating3NF() {
    return AlgorithmenSammlung.check3NF(this.attrs, this.fds);
  }

  /**
   *
   * @return all FD's that violate the BCNF; an empty set if it's already in BCNF
   */
  public Set<Abhaengigkeit> getFdsViolatingBCNF() {
    return AlgorithmenSammlung.checkBCNF(this.attrs, this.fds);
  }

  /**
   *
   * @return a set of {@code FuncDep} objects that involved in this relation
   */
  public Set<Abhaengigkeit> getFuncDeps() {
    return new HashSet<>(this.fds);
  }

  /**
   * Compute all the candidate keys in this relation
   *
   * @return a set of candidate keys, and each itself is a set of attributes
   */
  public Set<Set<Attribut>> getKeys() {
    return AlgorithmenSammlung.berechneKandidatenSchlüssel(this.attrs, this.fds);
  }

  /**
   * Compute all the superkeys (including candidate keys) in this relation
   *
   * @return a set of superkeys, and each itself is a set of attributes
   */
  public Set<Set<Attribut>> getSuperkeys() {
    return AlgorithmenSammlung.berechneSuperSchlüssel(this.attrs, this.fds);
  }

  @Override
  public int hashCode() {
    int hash = 17;
    for (Attribut a : this.attrs) {
      hash = 31 * hash + a.hashCode();
    }
    for (Abhaengigkeit fd : this.fds) {
      hash = 31 * hash + fd.hashCode();
    }
    return hash;
  }

  /**
   *
   * @return {@code true} if this relation is already in the third normal form
   *         (3NF)
   */
  public boolean is3NF() {
    return AlgorithmenSammlung.check3NF(this.attrs, this.fds).isEmpty();
  }

  /**
   *
   * @return {@code true} if this relation is already in Boyce-Codd normal form
   *         (BCNF)
   */
  public boolean isBCNF() {
    return AlgorithmenSammlung.checkBCNF(this.attrs, this.fds).isEmpty();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(Attribut.AVERAGE_LENGTH * 50);
    sb.append("Attributes:\n");
    for (Attribut a : this.attrs) {
      sb.append(a);
      sb.append(", ");
    }
    sb.delete(sb.length() - 2, sb.length() - 1);
    sb.append("\nFunctional Dependencies: \n");
    for (Abhaengigkeit fd : this.fds) {
      sb.append(fd);
      sb.append('\n');
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

}
