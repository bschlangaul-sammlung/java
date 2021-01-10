package org.bschlangaul.liste;

/**
 * Implementierung der doppelt verketteten Liste
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 332/337), heißt im
 * Original „DList“.
 */
public class DoppeltVerketteteListe {
  static class Node {
    Object obj; // Element
    Node prev, next; // Zeiger auf Vorgänger und Nachfolger

    public Node(Object o, Node p, Node n) {
      obj = o;
      prev = p;
      next = n;
    }

    public Node() {
      obj = null;
      prev = next = null;
    }

    // Vorgänger neu belegen
    public void setPrevious(Node p) {
      prev = p;
    }

    // Zugriff auf Vorgänger
    public Node getPrevious() {
      return prev;
    }

    // Vorgänger neu belegen
    public void setNext(Node p) {
      prev = p;
    }

    // Zugriff auf Vorgänger
    public Node getNext() {
      return prev;
    }

    // Zugriff auf Vorgänger
    public Object getElement() {
      return obj;
    }
  }

  private Node head = null; // Listenanfang
  private Node tail = null; // Listenende

  public DoppeltVerketteteListe() {
    head = new Node();
    tail = new Node();
    // Anfang und Ende "verknüpfen"
    head.setNext(tail);
    tail.setPrevious(head);
    tail.setNext(tail);
  }

  public void addFirst(Object o) {
    // Knoten zwischen head und dessen Nachfolger einfügen
    Node n = new Node(o, head, head.getNext());
    head.getNext().setPrevious(n);
    head.setNext(n);
  }

  public void addLast(Object o) {
    // Knoten zwischen tail und dessen Vorgänger einfügen
    Node l = tail.getPrevious();
    Node n = new Node(o, l, tail);
    l.setNext(n);
    tail.setPrevious(n);
  }

  public Object getFirst() throws LeereListeFehler {
    if (isEmpty())
      throw new LeereListeFehler();

    // Zugriff über Listenanfang
    return head.getNext().getElement();
  }

  public Object getLast() throws LeereListeFehler {
    if (isEmpty())
      throw new LeereListeFehler();
    // Zugriff über Listenende
    return tail.getPrevious().getElement();
  }

  public Object removeFirst() throws LeereListeFehler {
    if (isEmpty())
      throw new LeereListeFehler();
    // Zugriff über Listenanfang
    Object o = head.getNext().getElement();
    // Knoten zwischen head und Nachfolger entfernen
    head.setNext(head.getNext().getNext());
    head.getNext().setPrevious(head);
    return o;
  }

  public Object removeLast() throws LeereListeFehler {
    if (isEmpty())
      throw new LeereListeFehler();
    // Zugriff über Listenende
    Node n = tail.getPrevious();
    // Knoten zwischen tail und Vorgänger entfernen
    n.getPrevious().setNext(tail);
    tail.setPrevious(n.getPrevious());
    return n.getElement();
  }

  public int size() {
    int s = 0;
    Node n = head;
    // Knoten zählen
    while (n.getNext() != tail) {
      s++;
      n = n.getNext();
    }
    return s;
  }

  public boolean isEmpty() {
    // keine Knoten zwischen head und tail

    return head.getNext() == tail;
  }

  class ListIterator implements java.util.Iterator<Object> {
    private Node node = null;

    public ListIterator() {
      // mit Listenanfang initialisieren
      node = head.getNext();
    }

    public boolean hasNext() {
      return node != tail;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Object next() {
      if (!hasNext())
        throw new java.util.NoSuchElementException();
      Object o = node.getElement();
      node = node.getNext();
      return o;
    }
  }

  public java.util.Iterator<Object> iterator() {
    return new ListIterator();
  }
}
