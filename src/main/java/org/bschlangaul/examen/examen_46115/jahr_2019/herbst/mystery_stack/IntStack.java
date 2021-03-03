package org.bschlangaul.examen.examen_46115.jahr_2019.herbst.mystery_stack;

import java.util.Stack;

/**
 * Um schnell einen lauffähigen Stack zu bekommen, verwenden wir den Stack aus
 * der Java Collection.
 */
public class IntStack {
  private Stack<Integer> stack = new Stack<Integer>();

  // legt Element i auf den Stack
  public void push(int i) {
    stack.push(i);

  }

  // gibt oberstes Element vom Stack
  public int pop() {
    return stack.pop();
  }

  // fragt ab ob Stack leer ist
  public boolean isEmpty() {
    return stack.empty();
  }
}
