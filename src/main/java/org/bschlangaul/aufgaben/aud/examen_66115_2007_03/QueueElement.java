package org.bschlangaul.aufgaben.aud.examen_66115_2007_03;

class QueueElement {

  private QueueElement next;
  private Object contents;

  QueueElement(Object contents) {
    this.contents = contents;
  }

  Object getContents() {
    return contents;
  }

  QueueElement getNext() {
    return next;
  }

  void setNext(QueueElement next) {
    this.next = next;
  }
}
