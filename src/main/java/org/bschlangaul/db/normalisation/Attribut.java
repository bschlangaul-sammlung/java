/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package org.bschlangaul.db.normalisation;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code Attribut} class represents an attribute in a relation
 *
 * @author SUN XIMENG (Nathaniel)
 */
public final class Attribut {

  protected static final int AVERAGE_LENGTH = 10;

  /**
   * Get a set of attributes with one single method
   *
   * @param names a formatted {@code String} as in the following example:
   *              "username, password, favApp, lastLogin" (white spaces are
   *              optional)
   * @return a set of attributes
   */
  public static Set<Attribut> getSet(String names) {
    if (names.equals("")) {
      return new HashSet<>();
    }
    names = names.replaceAll("\\s+", "");
    return getSet(names.split(","));
  }

  /**
   * Get a set of attributes with one single method
   *
   * @param names a String array in which each element will be used to create an
   *              {@code Attribute}
   * @return a set of attributes
   */
  public static Set<Attribut> getSet(String[] names) {
    Set<Attribut> attrs = new HashSet<>();
    for (String s : names) {
      attrs.add(Attribut.of(s));
    }
    return attrs;
  }

  /**
   * A way to create an {@code Attribute} object without calling the constructor
   *
   * @param name the name of the attribute as a unique identifier
   * @return a new {@code Attribute} object
   */
  public static Attribut of(String name) {
    return new Attribut(name);
  }

  private final String name;

  /**
   * The default constructor
   *
   * @param name the name of the attribute, as a unique identifier
   */
  public Attribut(String name) {
    this.name = name;
  }

  /**
   * An {@code object} will equal to another if and only if they have the same
   * {@code name} (case sensitive)
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Attribut)) {
      return false;
    }
    Attribut a = (Attribut) o;
    return a.name.equals(this.name);
  }

  /**
   *
   * @return the name of the attribute
   */
  public String getName() {
    return this.name;
  }

  @Override
  public int hashCode() {
    return this.name.hashCode();
  }

  @Override
  public String toString() {
    return this.name;
  }

}
