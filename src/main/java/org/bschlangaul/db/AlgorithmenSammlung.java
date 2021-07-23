/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package org.bschlangaul.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is a utility class to perform operations involving functional
 * dependencies (FD's)
 *
 * @author SUN XIMENG (Nathaniel)
 */
public class AlgorithmenSammlung {

  /**
   * Check if a relation is in the third normal form (3NF)
   *
   * @param attrs the set of attributes in a relation
   * @param fds   the set of FD's on this relation
   * @return {@code true} if this relation is in 3NF
   */
  public static Set<Abhaengigkeit> check3NF(Set<Attribut> attrs, Set<Abhaengigkeit> fds) {
    Set<Set<Attribut>> keys = berechneKandidatenSchlüssel(attrs, fds);
    Set<Attribut> primes = new HashSet<>();
    for (Set<Attribut> k : keys) {
      primes.addAll(k);
    }

    Set<Abhaengigkeit> violating = new HashSet<>();
    for (Abhaengigkeit fd : fds) {
      if (!primes.containsAll(fd.getRight())) {
        boolean contains = false;
        for (Set<Attribut> k : keys) {
          if (fd.getLeft().containsAll(k)) {
            contains = true;
            break;
          }
        }
        if (!contains) {
          violating.add(fd);
        }
      }
    }
    return violating;
  }

  /**
   * Check if a relation is in Boyce-Codd normal form (BCNF)
   *
   * @param attrs the set of attributes in a relation
   * @param fds   the set of FD's on this relation
   * @return {@code true} if this relation is in BCNF
   */
  public static Set<Abhaengigkeit> checkBCNF(Set<Attribut> attrs, Set<Abhaengigkeit> fds) {
    Set<Set<Attribut>> keys = berechneKandidatenSchlüssel(attrs, fds);
    Set<Abhaengigkeit> violating = new HashSet<>();
    for (Abhaengigkeit fd : fds) {
      boolean contains = false;
      for (Set<Attribut> k : keys) {
        if (fd.getLeft().containsAll(k)) {
          contains = true;
          break;
        }
      }
      if (!contains) {
        violating.add(fd);
      }
    }
    return violating;
  }

  /**
   * Check if a certain decomposition of a relation will cause FD loss
   *
   * @param attrs    attributes in the original relation
   * @param fds      all the FD's in the original relation
   * @param subattrs each {@code Set<Attribute>} represents a decomposed relation
   *                 from the original relation
   * @return the set of FD's that will be lost if this decomposition is performed
   */
  public static Set<Abhaengigkeit> checkLossyDecomposition(Set<Attribut> attrs, Set<Abhaengigkeit> fds,
      Set<Set<Attribut>> subattrs) {
    Set<Abhaengigkeit> lost = new HashSet<>();
    Set<Abhaengigkeit> decomposed = new HashSet<>();
    for (Set<Attribut> sa : subattrs) {
      decomposed.addAll(projection(sa, fds));
    }
    for (Abhaengigkeit fd : fds) {
      Set<Attribut> left = fd.getLeft();
      Set<Attribut> closure = berechneAttributHülle(left, decomposed);
      if (!closure.containsAll(fd.getRight())) {
        lost.add(fd);
      }
    }
    return lost;
  }

  /**
   * Compute the closure of a set of attributes under a set of FD's
   *
   * @param attrs a set of attributes
   * @param fds   a set of FD's
   * @return a set of attributes, i.e. the closure
   */
  public static Set<Attribut> berechneAttributHülle(Set<Attribut> attrs, Set<Abhaengigkeit> fds) {
    Set<Attribut> result = new HashSet<>(attrs);

    boolean found = true;
    while (found) {
      found = false;
      for (Abhaengigkeit fd : fds) {
        if (result.containsAll(fd.left) && !result.containsAll(fd.right)) {
          result.addAll(fd.right);
          found = true;
        }
      }
    }

    return result;
  }

  /**
   * In-place combine the right side of the FD's that have the same left side
   *
   * @param fds a set of FD's
   */
  public static void combineRight(Set<Abhaengigkeit> fds) {
    Map<Set<Attribut>, Set<Attribut>> map = new HashMap<>();
    for (Abhaengigkeit fd : fds) {
      // Set<Attribute> left = fd.getLeft();
      if (map.containsKey(fd.left)) {
        map.get(fd.left).addAll(fd.right);
      } else {
        map.put(fd.left, fd.getRight());
      }
    }
    fds.clear();
    for (Set<Attribut> left : map.keySet()) {
      fds.add(new Abhaengigkeit.Builder().left(left).right(map.get(left)).build());
    }
  }

  /**
   * Check if two sets of FD's are equivalent to each other (i.e. each can be
   * deduced from another)
   *
   * @param a a set of FD's
   * @param b another set of FD's
   * @return {@code true} if they are equivalent
   */
  public static boolean equivalent(Set<Abhaengigkeit> a, Set<Abhaengigkeit> b) {
    Set<Attribut> names = new HashSet<>();
    for (Abhaengigkeit fd : a) {
      names.addAll(fd.getLeft());
      names.addAll(fd.getRight());
    }
    for (Abhaengigkeit fd : b) {
      names.addAll(fd.getLeft());
      names.addAll(fd.getRight());
    }

    Set<Set<Attribut>> powerset = erzeugeReduziertePotenzMenge(names);
    for (Set<Attribut> sa : powerset) {
      Set<Attribut> closureInA = berechneAttributHülle(sa, a);
      Set<Attribut> closureInB = berechneAttributHülle(sa, b);
      if (!closureInA.equals(closureInB)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Berechne alle Superschlüssel (einschließlich der Kandidatenschlüssel).
   *
   * @param attribute       Eine Menge aus Attributen.
   * @param abhaengigkeiten Eine Menge aus Funktionalen Abhängigkeiten.
   *
   * @return Eine Menge aus Superschlüsseln. Jeder Superschlüssel ist wiederum
   *         eine Menge aus Attributen.
   */
  public static Set<Set<Attribut>> berechneSuperSchlüssel(Set<Attribut> attribute, Set<Abhaengigkeit> abhaengigkeiten) {
    Set<Set<Attribut>> schlüssel = new HashSet<>();
    if (attribute.isEmpty()) {
      for (Abhaengigkeit abhaengigkeit : abhaengigkeiten) {
        attribute.addAll(abhaengigkeit.left);
        attribute.addAll(abhaengigkeit.right);
      }
    }
    Set<Set<Attribut>> potenzMenge = erzeugeReduziertePotenzMenge(attribute);
    for (Set<Attribut> attributMenge : potenzMenge) {
      if (berechneAttributHülle(attributMenge, abhaengigkeiten).equals(attribute)) {
        schlüssel.add(attributMenge);
      }
    }
    return schlüssel;
  }

  /**
   * Berechne alle Schlüsselkandidaten (bzw. Kandidatenschlüssel).
   *
   * @param attribute       Eine Menge aus Attributen.
   * @param abhaengigkeiten Eine Menge aus Funktionalen Abhängigkeiten.
   *
   * @return Eine Menge aus Schlüsselkandidaten. Jeder Schlüsselkandidate besteht
   *         wiederum aus einer Menge von Attributen.
   */
  public static Set<Set<Attribut>> berechneKandidatenSchlüssel(Set<Attribut> attribute,
      Set<Abhaengigkeit> abhaengigkeiten) {
    Set<Set<Attribut>> superSchlüssel = berechneSuperSchlüssel(attribute, abhaengigkeiten);
    Set<Set<Attribut>> zuEntfernen = new HashSet<>();
    for (Set<Attribut> schlüssel : superSchlüssel) {
      for (Attribut attribut : schlüssel) {
        Set<Attribut> verbleibende = new HashSet<>(schlüssel);
        verbleibende.remove(attribut);
        if (superSchlüssel.contains(verbleibende)) {
          zuEntfernen.add(schlüssel);
          break;
        }
      }
    }
    superSchlüssel.removeAll(zuEntfernen);
    return superSchlüssel;
  }

  /**
   * Compute the minimal basis (aka. minimal cover). Notice this method only
   * returns ONE of the possible solutions, depending on the order in which the
   * attributes and FD's are stored in the memory.
   *
   * @param fds a set of FD's
   * @return a set of FD's as the minimal basis
   */
  public static Set<Abhaengigkeit> minimalBasis(Set<Abhaengigkeit> fds) {
    Set<Abhaengigkeit> result = new HashSet<>(fds);

    // Step 1: split right sides
    splitRight(result);

    // Step 2: remove trivial FDs
    removeTrivial(result);

    // Step 3: remove unnecessary FD's and left side attributes
    int count = 1;
    while (count > 0) {
      count = removeUnnecessaryLeftSide(result) + removeUnnecessaryEntireFD(result);
    }

    return result;
  }

  /**
   * Generate the power set, with the empty set.
   *
   * @param originalSet the original set
   * @param <T>         any class
   * @return the power set
   */
  public static <T> Set<Set<T>> erzeugePotenzMenge(Set<T> originalSet) {
    Set<Set<T>> sets = new HashSet<>();
    if (originalSet.isEmpty()) {
      sets.add(new HashSet<T>());
      return sets;
    }
    List<T> list = new ArrayList<>(originalSet);
    T head = list.get(0);
    Set<T> rest = new HashSet<>(list.subList(1, list.size()));
    for (Set<T> set : erzeugePotenzMenge(rest)) {
      Set<T> newSet = new HashSet<>();
      newSet.add(head);
      newSet.addAll(set);
      sets.add(newSet);
      sets.add(set);
    }
    // sets.remove(new HashSet<T>());
    return sets;
  }

  /**
   * Project a set of FD's on a subset of attributes
   *
   * @param attrs a set of attributes
   * @param fds   a set of FD's
   * @return a set of FD's as the projection
   */
  public static Set<Abhaengigkeit> projection(Set<Attribut> attrs, Set<Abhaengigkeit> fds) {
    Set<Attribut> appeared = new HashSet<>();
    for (Abhaengigkeit fd : fds) {
      appeared.addAll(fd.getLeft());
      appeared.addAll(fd.getRight());
    }
    if (attrs.containsAll(appeared)) {
      return new HashSet<>(fds);
    }
    Set<Attribut> notin = new HashSet<>(appeared);
    notin.removeAll(attrs);
    Set<Set<Attribut>> powerset = erzeugeReduziertePotenzMenge(attrs);
    Set<Abhaengigkeit> result = new HashSet<>();
    for (Set<Attribut> sa : powerset) {
      Set<Attribut> closure = berechneAttributHülle(sa, fds);
      closure.removeAll(notin);
      // closure.removeAll(sa);
      result.add(new Abhaengigkeit.Builder().left(sa).right(closure).build());
    }
    // return result;
    return minimalBasis(result);
  }

  /**
   * Generate the power set (i.e. set of all the subsets) of a generic set, but
   * without the empty set.
   *
   * @param originalSet the original set
   * @param <T>         any class
   * @return the reduced power set
   */
  public static <T> Set<Set<T>> erzeugeReduziertePotenzMenge(Set<T> originalSet) {
    Set<Set<T>> result = erzeugePotenzMenge(originalSet);
    result.remove(new HashSet<T>());
    return result;
  }

  /**
   * Remove all trivial FD's from a set of FD's
   *
   * @param fds a set of FD's
   */
  public static void removeTrivial(Set<Abhaengigkeit> fds) {
    Set<Abhaengigkeit> toRemove = new HashSet<>();
    Set<Abhaengigkeit> toAdd = new HashSet<>();
    for (Abhaengigkeit fd : fds) {
      if (fd.left.containsAll(fd.right)) {
        toRemove.add(fd);
      } else {
        Set<Attribut> toRemoveFromRight = new HashSet<>();
        for (Attribut a : fd.right) {
          if (fd.left.contains(a)) {
            toRemoveFromRight.add(a);
          }
        }
        if (!toRemoveFromRight.isEmpty()) {
          Set<Attribut> right = fd.getRight();
          right.removeAll(toRemoveFromRight);
          toRemove.add(fd);
          toAdd.add(new Abhaengigkeit.Builder().left(fd.left).right(right).build());
        }
      }
    }
    fds.addAll(toAdd);
    fds.removeAll(toRemove);
  }

  /**
   * In-place remove all the unnecessary FD's, and the result is still equivalent
   * to the original set of FD's
   *
   * @param fds a set of FD's
   * @return the total number of FD's removed
   */
  public static int removeUnnecessaryEntireFD(Set<Abhaengigkeit> fds) {
    int count = 0;
    while (true) {
      Abhaengigkeit toRemove = null;
      boolean found = false;
      for (Abhaengigkeit fd : fds) {
        Set<Abhaengigkeit> remaining = new HashSet<>(fds);
        remaining.remove(fd);
        if (equivalent(remaining, fds)) {
          ++count;
          found = true;
          toRemove = fd;
          break;
        }
      }
      if (!found) {
        break;
      } else {
        fds.remove(toRemove);
      }
    }

    return count;
  }

  /**
   * In-place remove all the unnecessary attributes on the left side of each FD,
   * and the result is still equivalent to the original set of FD's
   *
   * @param fds a set of FD's
   * @return the total number of attributes removed
   */
  public static int removeUnnecessaryLeftSide(Set<Abhaengigkeit> fds) {
    int count = 0;
    while (true) {
      boolean found = false;
      Abhaengigkeit toRemove = null;
      Abhaengigkeit toAdd = null;
      int loop = 0;
      for (Abhaengigkeit fd : fds) {
        Set<Attribut> left = fd.getLeft();
        Set<Attribut> right = fd.getRight();
        if (left.size() > 1) {
          for (Attribut a : left) {
            Set<Attribut> remaining = new HashSet<>(left);
            remaining.remove(a);
            Set<Abhaengigkeit> alternative = new HashSet<>(fds);
            alternative.remove(fd);
            toAdd = new Abhaengigkeit.Builder().left(remaining).right(right).build();
            alternative.add(toAdd);
            if (equivalent(alternative, fds)) {
              found = true;
              toRemove = fd;
              ++count;
              break;
            }
          }
        }

        if (found) {
          break;
        }
        ++loop;
      }
      if (found) {
        fds.remove(toRemove);
        fds.add(toAdd);
      }
      if (loop == fds.size()) {
        break;
      }
    }
    return count;
  }

  /**
   * In-place split the right side of each FD in this set
   *
   * @param fds a set of FD's
   */
  public static void splitRight(Set<Abhaengigkeit> fds) {
    Set<Abhaengigkeit> toRemove = new HashSet<>();
    Set<Abhaengigkeit> toAdd = new HashSet<>();
    for (Abhaengigkeit fd : fds) {
      // Set<Attribute> right = fd.getRight();
      if (fd.right.size() > 1) {
        // Set<Attribute> left = fd.getLeft();
        for (Attribut a : fd.right) {
          toAdd.add(new Abhaengigkeit.Builder().left(fd.left).right(a).build());
        }
        toRemove.add(fd);
      }
    }
    fds.addAll(toAdd);
    fds.removeAll(toRemove);
  }

  private AlgorithmenSammlung() {

  }

}
