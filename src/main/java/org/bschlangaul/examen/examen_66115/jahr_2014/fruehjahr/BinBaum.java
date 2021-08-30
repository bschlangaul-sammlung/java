package org.bschlangaul.examen.examen_66115.jahr_2014.fruehjahr;

import java.util.ArrayList;
import java.util.List;

public class BinBaum {

  Knoten head;

  public BinBaum(Knoten head) {
    this.head = head;
  }

  class Knoten {
    int value;

    Knoten left;
    Knoten right;

    public Knoten(int value) {
      this.value = value;
    }

    public void setLeft(Knoten left) {
      this.left = left;
    }

    public void setRight(Knoten right) {
      this.right = right;
    }

    public Knoten getLeft() {
      return left;
    }

    public Knoten getRight() {
      return right;
    }

    public int getValue() {
      return value;
    }
  }

  void preOrder(Knoten knoten, List<Integer> list) {
    if (knoten != null) {
      list.add(knoten.getValue());
      preOrder(knoten.getLeft(), list);
      preOrder(knoten.getRight(), list);
    }
  }

  List<Integer> preOrder() {
    List<Integer> list = new ArrayList<>();
    preOrder(head, list);
    return list;
  }

  void postOrder(Knoten knoten, List<Integer> list) {
    if (knoten != null) {
      postOrder(knoten.getLeft(), list);
      postOrder(knoten.getRight(), list);
      list.add(knoten.getValue());
    }
  }

  List<Integer> postOrder() {
    List<Integer> list = new ArrayList<>();
    postOrder(head, list);
    return list;
  }

  boolean isSearchTree(Knoten knoten) {
    if (knoten == null) {
      return true;
    }

    if (knoten.getLeft() != null && knoten.getValue() < knoten.getLeft().getValue()) {
      return false;
    }

    if (knoten.getRight() != null && knoten.getValue() > knoten.getRight().getValue()) {
      return false;
    }

    return isSearchTree(knoten.getLeft()) && isSearchTree(knoten.getRight());
  }

  boolean isSearchTree() {
    return isSearchTree(head);
  }
}
