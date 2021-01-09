package org.bschlangaul.examen.examen_66115.jahr_2007.fruehjahr;

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
