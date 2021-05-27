package org.bschlangaul.examen.examen_66116.jahr_2021.fruehjahr.my_list;

public class MyList {
  private MyElement head;

  public MyList() {
    this.head = new MyEndElement();
  }

  public int getSum() {
    return this.head.calculateSum();
  }

  public void add(int value) {
    head = head.add(value);
  }

  public static void main(String[] args) {
    MyList myList = new MyList();
    myList.add(1);
    myList.add(2);
    myList.add(3);
    System.out.println(myList.getSum());
  }
}
