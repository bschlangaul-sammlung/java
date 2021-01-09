package org.bschlangaul.examen.examen_66115.jahr_2019.herbst;

class Node {
  Node left, right;
  int value;

  public Node(int value) {
    this.value = value;
  }

  int foo(Node node) {
    int b = node.value;
    if (b < 0) {
      b = -1 * b;
    }
    if (node.left != null) {
      b = b + foo(node.left);
    }
    if (node.right != null) {
      b = b + foo(node.right);
    }
    return b;
  }

  void magic(Node node) {
    Node m = max(node);
    if (m.value > node.value) {
      // Werte von m und node vertauschen
      int tmp = m.value;
      m.value = node.value;
      node.value = tmp;
    }
    if (node.left != null)
      magic(node.left);
    if (node.right != null)
      magic(node.right);
  }

  Node max(Node node) {
    Node max = node;
    if (node.left != null) {
      Node tmp = max(node.left);
      if (tmp.value > max.value)
        max = tmp;
    }
    if (node.right != null) {
      Node tmp = max(node.right);
      if (tmp.value > max.value)
        max = tmp;
    }
    return max;
  }

  public static void main(String[] args) {
    Node eins = new Node(1);
    Node zwei = new Node(2);
    Node drei = new Node(3);
    Node vier = new Node(4);
    Node fünf = new Node(5);

    eins.left = zwei;
    eins.right = drei;

    drei.left = vier;
    drei.right = fünf;

    System.out.println("Die Methode foo() berechnet die Summe aller Schlüsselwerte: 1 + 2 + 3 + 4 + 5 = " + eins.foo(eins));

    System.out.println("Die Methode magic() wird ausgeführt");
    eins.magic(eins);

    System.out.println("Der Knoten eins hat jetzt den Wert: " + eins.value);
    System.out.println("Der Knoten zwei hat jetzt den Wert: " + zwei.value);
    System.out.println("Der Knoten drei hat jetzt den Wert: " + drei.value);
    System.out.println("Der Knoten vier hat jetzt den Wert: " + vier.value);
    System.out.println("Der Knoten fünf hat jetzt den Wert: " + fünf.value);

    System.out.println("Die Summe aller Schlüsselwerte bleibt gleich: " + eins.foo(eins));
  }
}
