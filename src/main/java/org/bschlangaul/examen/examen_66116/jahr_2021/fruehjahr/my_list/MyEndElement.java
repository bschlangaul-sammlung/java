package org.bschlangaul.examen.examen_66116.jahr_2021.fruehjahr.my_list;

public class MyEndElement extends MyElement {

  public MyEndElement () {
    super(0);
  }

  MyElement add(int value) {
    MyElement element = new MyElement(value);
    element.next = this;
    return element;
  }

  int calculateSum() {
    return 0;
  }

}
