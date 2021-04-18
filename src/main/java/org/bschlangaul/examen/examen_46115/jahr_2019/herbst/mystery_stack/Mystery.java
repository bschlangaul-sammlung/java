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
    m.foo(3);
    m.foo(5);
    m.foo(4);
    m.bar();
    m.foo(7);
    m.bar();
    m.foo(2);
    m.bar();
    m.bar();
  }
}
