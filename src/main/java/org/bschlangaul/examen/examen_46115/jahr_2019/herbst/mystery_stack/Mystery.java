package org.bschlangaul.examen.examen_46115.jahr_2019.herbst.mystery_stack;

public class Mystery {
  private IntStack a = new IntStack();
  private IntStack b = new IntStack();

  public void foo(int item) {
    a.push(item);
  }

  public int bar() {
    if (b.isEmpty()) {
      while (!a.isEmpty()) {
        b.push(a.pop());
      }
    }
    return b.pop();
  }

  public static void main(String[] args) {
    Mystery m = new Mystery();
    m.foo(3); // a: 3; b: -;
    m.foo(5); // a: 5, 3; b: -;
    m.foo(4); // a: 4, 5, 3; b: -;
    System.out.println(m.bar()); // a: -; b: 5, 4; -> 3
    m.foo(7); // a: 7; b: 5, 4;
    System.out.println(m.bar()); // a: 7; b: 4; -> 5
    m.foo(2); // a: 2, 7; b: 4;
    System.out.println(m.bar()); // a: 2, 7; b: -; -> 4
    System.out.println(m.bar()); // a: -; b: 7, 2; -> 7
  }
}
