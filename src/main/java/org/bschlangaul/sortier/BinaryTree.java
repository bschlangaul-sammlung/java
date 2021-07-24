package org.bschlangaul.sortier;

/**
 * <a href=
 * "https://knpcode.com/java-programs/tree-sort-java-program/">knpcode.com</a>
 */
class Node {
  int value;
  Node left;
  Node right;

  Node(int value) {
    this.value = value;
    left = null;
    right = null;
  }
}

class BST {
  Node node;

  int zähler;

  BST(int value) {
    node = new Node(value);
    zähler = 0;
  }

  public Node insert(Node node, int value) {
    if (node == null) {
      return new Node(value);
    }
    // Move to left for value less than parent node
    if (value < node.value) {
      node.left = insert(node.left, value);
    }
    // Move to right for value greater than parent node
    else if (value > node.value) {
      node.right = insert(node.right, value);
    }
    return node;
  }

  // For traversing in order
  public void inOrder(Node node, int[] zahlen) {
    if (node != null) {

      // recursively traverse left subtree
      inOrder(node.left, zahlen);

      zahlen[zähler] = node.value;
      zähler++;
      // recursively traverse right subtree
      inOrder(node.right, zahlen);
    }
  }
}

public class BinaryTree extends Sortieralgorithmus {
  public int[] sortiere() {
    if (zahlen.length == 0) {
      return zahlen;
    }
    // start creating tree with element at index 0 as root node
    BST bst = new BST(zahlen[0]);
    for (int num : zahlen) {
      bst.insert(bst.node, num);
    }
    int[] sortiereZahlen = new int[zahlen.length];

    bst.inOrder(bst.node, sortiereZahlen);
    zahlen = sortiereZahlen;

    return zahlen;
  }

  public static void main(String[] args) {
    new BinaryTree().teste();
  }
}
