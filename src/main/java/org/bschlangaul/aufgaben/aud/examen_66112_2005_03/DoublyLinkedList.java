package org.bschlangaul.aufgaben.aud.examen_66112_2005_03;

public class DoublyLinkedList {
  private ListElem head;

  public DoublyLinkedList() {
  }

  public void insert(int i) {
    if (head != null) {
      // Immer einen neue Zahl einfügen, nicht nur wenn die Zahl kleiner ist als head.
      head.insert(i);
      // Es muss kleiner gleich heißen, sonst können mehrer gleiche Zahlen am Anfang
      // nicht eingefügt werden.
      if (i <= head.getData()) {
        head = head.getPrevious();
      }
    } else {
      head = new ListElem(i);
    }
  }

  public boolean check() {
    ListElem current = head;
    while (current.getNext() != null) {
      if (current.getNext().getPrevious() != current) {
        return false;
      } else {
        current = current.getNext();
      }
    }
    return true;
  }

  public ListElem getHead() {
    return head;
  }

  public static void main(String[] args) {
    DoublyLinkedList list = new DoublyLinkedList();
    // int[] numbers = new int[] { 1 };
    // int[] numbers = new int[] { 1, 1, 1, 1, };
    // int[] numbers = new int[] { 1, 1, 1, 2, };
    // int[] numbers = new int[] { 2, 1, 1, 1, };
    // int[] numbers = new int[] { 2, 1 };
    int[] numbers = new int[] { 0, 2, 2, 6, 8, 4 };
    for (int number : numbers) {
      list.insert(number);
    }
    list.insert(3);

    ListElem current = list.getHead();
    while (current.getNext() != null) {
      System.out.println(current.getData());
      current = current.getNext();
    }
  }
}
