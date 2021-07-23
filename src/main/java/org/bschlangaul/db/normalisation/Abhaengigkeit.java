/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package org.bschlangaul.db.normalisation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code FuncDep} represents a functional dependency (FD), in which all the
 * attributes on the left functionally determined all those on the right.
 *
 * @author SUN XIMENG (Nathaniel)
 */
public final class Abhaengigkeit {

  /**
   * A builder class to help construct a {@code FuncDep} object
   *
   * @author SUN XIMENG (Nathaniel)
   */
  public static class Builder {
    private Set<Attribut> left;
    private Set<Attribut> right;

    /**
     * The default constructor
     */
    public Builder() {
      this.left = new HashSet<>();
      this.right = new HashSet<>();
    }

    /**
     *
     * @return a new {@code FuncDep} object
     */
    public Abhaengigkeit build() {
      return new Abhaengigkeit(this.left, this.right);
    }

    /**
     * Add {@code Attribute} objects to the left of the FD
     *
     * @param as one or more {@code Attribute} objects
     * @return this builder
     */
    public Builder left(Attribut... as) {
      this.left.addAll(Arrays.asList(as));
      return this;
    }

    /**
     * Add {@code Attribute} objects to the left of the FD
     *
     * @param as a set of {@code Attribute} objects
     * @return this builder
     */
    public Builder left(Set<Attribut> as) {
      this.left.addAll(as);
      return this;
    }

    /**
     * Add {@code Attribute} objects to the right of the FD
     *
     * @param as one or more {@code Attribute} objects
     * @return this builder
     */
    public Builder right(Attribut... as) {
      this.right.addAll(Arrays.asList(as));
      return this;
    }

    /**
     * Add {@code Attribute} objects to the right of the FD
     *
     * @param as a set of {@code Attribute} objects
     * @return this builder
     */
    public Builder right(Set<Attribut> as) {
      this.right.addAll(as);
      return this;
    }
  }

  /**
   * Get a set of {@code FuncDep} objects with only one string
   *
   * @param exprs a formatted string as the following example: "a, b --&gt; c; d
   *              --&gt; e,f" (white spaces are optional)
   * @return a set of {@code FuncDep} objects
   */
  public static Set<Abhaengigkeit> getSet(String exprs) {
    if (exprs.equals("")) {
      return new HashSet<>();
    }
    exprs = exprs.replaceAll("\\s+", "");
    return getSet(exprs.split(";"));
  }

  /**
   * Get a set of {@code FuncDep} objects with only one string array
   *
   * @param exprs each element in this array is formatted as the following
   *              example: "a, b --&gt; c, d"
   * @return a set of {@code FuncDep} objects
   */
  public static Set<Abhaengigkeit> getSet(String[] exprs) {
    Set<Abhaengigkeit> fds = new HashSet<>();
    for (String s : exprs) {
      fds.add(Abhaengigkeit.of(s));
    }
    return fds;
  }

  /**
   * Quickly construct a {@code FuncDep} object with a formatted string
   *
   * @param expr a formatted string as the following example: "a, b --&gt; c, d"
   *             (white spaces are optional)
   * @return a {@code FuncDep} object
   */
  public static Abhaengigkeit of(String expr) {
    String[] halves = expr.split("->");
    return of(halves[0], halves[1]);
  }

  /**
   * Quickly construct a {@code FuncDep} object with two formatted string, for the
   * left and right parts respectively
   *
   * @param left  a formatted string as the following example: "a, b"
   * @param right a formatted string as the following example: "c, d"
   * @return a {@code FuncDep} object
   */
  public static Abhaengigkeit of(String left, String right) {
    left = left.replaceAll("\\s+", "");
    right = right.replaceAll("\\s+", "");
    String[] lefts = left.split(",");
    String[] rights = right.split(",");
    Builder bd = new Builder();
    for (String s : lefts) {
      bd.left(Attribut.of(s));
    }
    for (String s : rights) {
      bd.right(Attribut.of(s));
    }
    return bd.build();
  }

  /**
   * @author Hermine Bschlangaul
   */
  public static Set<Attribut> extractAttributes(Set<Abhaengigkeit> funcDeps) {
    Set<Attribut> attributes = new HashSet<>();
    for (Abhaengigkeit funcDep : funcDeps) {
      for (Attribut attribute : funcDep.getAllAttributes()) {
        attributes.add(attribute);
      }
    }
    return attributes;
  }

  protected final Set<Attribut> left;

  protected final Set<Attribut> right;

  /**
   * The default constructor
   *
   * @param left  a set of {@code Attribute} objects to be placed on the left of
   *              the FD
   * @param right a set of {@code Attribute} objects to be placed on the right of
   *              the FD
   */
  public Abhaengigkeit(Set<Attribut> left, Set<Attribut> right) {
    this.left = new HashSet<>(left);
    this.right = new HashSet<>(right);
  }

  /**
   * A {@code FuncDep} object will equal to another if and only if they have
   * exactly the left AND right attribute sets
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Abhaengigkeit)) {
      return false;
    }
    Abhaengigkeit fd = (Abhaengigkeit) o;
    return this.left.equals(fd.left) && this.right.equals(fd.right);
  }

  /**
   * @return the set of {@code Attribute} objects that appear in the left part of
   *         this FD
   */
  public Set<Attribut> getLeft() {
    return new HashSet<>(this.left);
  }

  /**
   * @return the set of {@code Attribute} objects that appear in the right part of
   *         this FD
   */
  public Set<Attribut> getRight() {
    return new HashSet<>(this.right);
  }

  /**
   * @author Hermine Bschlangaul
   */
  public Set<Attribut> getAllAttributes() {
    HashSet<Attribut> all = new HashSet<>();
    all.addAll(this.left);
    all.addAll(this.right);
    return all;
  }

  @Override
  public int hashCode() {
    int result = 17;
    for (Attribut at : this.left) {
      result = 31 * result + at.hashCode();
    }
    for (Attribut at : this.right) {
      result = 31 * result + at.hashCode();
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder((this.left.size() + this.right.size()) * Attribut.AVERAGE_LENGTH);
    for (Attribut at : this.left) {
      sb.append(at.toString());
      sb.append(", ");
    }
    sb.delete(sb.length() - 2, sb.length());
    sb.append(" -> ");
    for (Attribut at : this.right) {
      sb.append(at.toString());
      sb.append(", ");
    }
    sb.delete(sb.length() - 2, sb.length());
    return sb.toString();
  }

}
