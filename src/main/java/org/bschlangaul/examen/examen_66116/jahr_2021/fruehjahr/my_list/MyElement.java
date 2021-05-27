package org.bschlangaul.examen.examen_66116.jahr_2021.fruehjahr.my_list;

public class MyElement {

  MyElement next;

  int value;

  public MyElement(int value) {
    this.value = value;
  }

  MyElement add(int value) {
    next = this.next.add(value);
    return this;
  }

  int calculateSum() {
    return value + next.calculateSum();
  }
}
