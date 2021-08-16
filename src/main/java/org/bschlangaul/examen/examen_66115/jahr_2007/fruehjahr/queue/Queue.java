package org.bschlangaul.examen.examen_66115.jahr_2007.fruehjahr.queue;

class Queue {
  QueueElement first;
  QueueElement last;

  public void append(Object contents) {
    QueueElement newElement = new QueueElement(contents);
    if (first == null) {
      first = newElement;
      last = newElement;
    } else {
      // neues Element hinten anhängen
      last.setNext(newElement);
      // angehängtes Element ist Letztes
      last = last.getNext();
    }
  }

  public Object remove() {
    Object tmp = null;
    if (first != null) {
      // Dein Inhalt des ersten Elements temporär speichern
      tmp = first.getContents();
      // Das erste Element aus der Schlange nehmen
      first = first.getNext();
    }
    // Den Inhalt des gelöschten Elements ausgeben bzw . null
    return tmp;
  }

  public boolean isEmpty() {
    return (first == null);
  }
}
