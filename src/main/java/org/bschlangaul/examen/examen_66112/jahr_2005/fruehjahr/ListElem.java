package org.bschlangaul.examen.examen_66112.jahr_2005.fruehjahr;

public class ListElem {
  private int data;
  private ListElem previous;
  private ListElem next;

  public ListElem(int i) {
    data = i;
  }

  public ListElem() {
  }

  public void insert(int i) {
    ListElem newElement = new ListElem(i);
    if (i <= data) {
      if (previous != null) {
        newElement.next = this;
        newElement.previous = previous;
        previous.next = newElement;
        previous = newElement;
      } else {
        newElement.next = this;
        previous = newElement;
      }
    } else {
      if (next != null) {
        next.insert(i);
      } else {
        newElement.previous = this;
        next = newElement;
      }
    }
  }

  public ListElem getPrevious() {
    return previous;
  }

  public ListElem getNext() {
    return next;
  }

  public int getData() {
    return data;
  }
}
