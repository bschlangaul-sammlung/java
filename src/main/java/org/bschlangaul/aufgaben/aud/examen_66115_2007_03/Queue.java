package org.bschlangaul.aufgaben.aud.examen_66115_2007_03;

class Queue {
  QueueElement first;
  QueueElement last;

  public void append(Object contents) {
    QueueElement newElement = new QueueElement(contents);
    if (first == null) {
      first = newElement;
      last = newElement;
    } else {
      // neues Element hinten anhaengen
      last.setNext(newElement);
      // angehaengtes Element ist Letztes
      last = last.getNext();
    }
  }

  public Object remove() {
    Object temp = null;
    if (first != null) {
      // Inhalt des Ersten temporaer speichern
      temp = first.getContents();
      // Erstes aus der Schlange nehmen
      first = first.getNext();
    }
    return temp;
    // Inhalt des geloeschten ausgeben bzw . null
  }

  public boolean isEmpty() {
    return (first == null);
  }
}
