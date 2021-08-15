package org.bschlangaul.examen.examen_66115.jahr_2015.fruehjahr.schlange;

public class Stapel {
  public Element top;

  public Stapel() {
    top = null;
  }

  public int top() {
    return top.getValue();
  }

  /**
   * @param value Der Wert, der zum Stapel hinzugefügt werden soll.
   */
  public void push(int value) {
    Element element = new Element(value);
    element.setNext(top);
    top = element;
  }

  /**
   * @return Das Element oder null, wenn der Stapel leer ist.
   */
  public int pop() {
    Element element = top;
    top = top.getNext();
    return element.getValue();
  }

  /**
   * @return Wahr wenn der Stapel leer ist.
   */
  public boolean isEmpty() {
    return top == null;
  }

}
