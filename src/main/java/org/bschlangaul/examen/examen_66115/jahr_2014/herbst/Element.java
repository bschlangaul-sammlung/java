package org.bschlangaul.examen.examen_66115.jahr_2014.herbst;

/**
 * https://www.studon.fau.de/file2860856_download.html
 */
public class Element {
  public int value;

  public Element next;

  public Element() {
    this.next = null;
  }

  public Element(int value, Element element) {
    this.value = value;
    this.next = element;
  }

  public Element(int value) {
    this.value = value;
    this.next = null;
  }

  public int getValue() {
    return value;
  }

  public Element getNext() {
    return next;
  }

  public void setNext(Element element) {
    next = element;
  }

  public int compareTo(Element element) {
    if (getValue() > element.getValue()) {
      return 1;
    } else if (element.getValue() == getValue()) {
      return 0;
    } else {
      return -1;
    }
  }

}
