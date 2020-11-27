package org.bschlangaul.aufgaben.aud.examen_66115_2015_03;

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

}
