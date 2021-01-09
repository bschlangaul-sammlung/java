package org.bschlangaul.examen.examen_66115.jahr_2015.fruehjahr;

public class Schlange {

  public Element head;

  public Schlange() {
    head = null;
  }

  public int head() {
    if (head.getNext() == null) {
      return head.getValue();
    }
    Element element = head;
    Element previous = head;
    while (element.getNext() != null) {
      previous = element;
      element = element.getNext();
    }
    element = previous.getNext();
    return element.getValue();
  }

  /**
   * @param value Eine Zahl, die zur Schlange hinzugefügt werden soll.
   */
  public void enqueue(int value) {
    Element element = new Element(value);
    element.setNext(head);
    head = element;
  }

  /**
   * @return Das Element oder null, wenn der Schlange leer ist.
   */
  public int dequeue() {
    if (head.getNext() == null) {
      int result = head.getValue();
      head = null;
      return result;
    }
    Element element = head;
    Element previous = null;
    while (element.getNext() != null) {
      previous = element;
      element = element.getNext();
    }
    element = previous.getNext();
    previous.setNext(null);
    return element.getValue();
  }

  /**
   * @return Wahr wenn der Schlange leer ist.
   */
  public boolean isEmpty() {
    return head == null;
  }

  public static void main(String[] args) {
    Schlange s = new Schlange();
    s.enqueue(1);
    s.enqueue(2);
    s.enqueue(3);
    System.out.println(s.head());
    System.out.println(s.dequeue());
    System.out.println(s.head());
    System.out.println(s.dequeue());
    System.out.println(s.head());
    System.out.println(s.dequeue());
  }

}
