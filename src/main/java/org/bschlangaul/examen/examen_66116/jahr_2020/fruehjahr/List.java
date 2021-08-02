package org.bschlangaul.examen.examen_66116.jahr_2020.fruehjahr;

public class List {

  Element head;

  class Element {
    String value;
    Element next;

    public Element(String value) {
      this.value = value;
    }
  }

  public List() {
    head = null;
  }

  void addFirst(String element) {
    Element newElement = new Element(element);
    if (head != null) {
      newElement.next = head;
    }
    head = newElement;
  }

  void addLast(String element) {
    Element nextElement = head;
    Element lastElement = head;
    while (nextElement != null) {
      lastElement = nextElement;
      nextElement = nextElement.next;
    }
    if (lastElement != null) {
      lastElement.next = new Element(element);
    } else {
      head = new Element(element);
    }
  }

  boolean exists(String element) {
    Element nextElement = head;
    while (nextElement != null) {
      if (nextElement.value.equals(element)) {
        return true;
      }
      nextElement = nextElement.next;
    }
    return false;
  }

  public static void main(String[] args) {
    List list = new List();
    list.addLast("two");
    list.addFirst("one");
    list.addLast("three");

    System.out.println(list.exists("one"));
    System.out.println(list.exists("four"));
    Element nextElement = list.head;
    while (nextElement != null) {
      System.out.println(nextElement.value);
      nextElement = nextElement.next;
    }
  }
}
