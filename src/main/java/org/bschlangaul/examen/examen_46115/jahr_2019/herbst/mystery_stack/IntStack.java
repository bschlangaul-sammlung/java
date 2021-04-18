package org.bschlangaul.examen.examen_46115.jahr_2019.herbst.mystery_stack;

import java.util.Stack;

/**
 * Um schnell einen lauffähigen Stack zu bekommen, verwenden wir den Stack aus
 * der Java Collection.
 */
public class IntStack {
  private Stack<Integer> stack = new Stack<Integer>();

  /**
   * Legt Element i auf den Stack.
   *
   * @param i Eine Zahl, die auf dem Stack gelegt werden soll.
   */
  public void push(int i) {
    stack.push(i);
  }

  /**
   * Gibt oberstes Element vom Stack.
   *
   * @return Das oberste Element auf dem Stapel.
   */
  public int pop() {
    return stack.pop();
  }

  /**
   * Fragt ab, ob Stack leer ist.
   *
   * @return Wahr, wenn der Stapel leer ist.
   */
  public boolean isEmpty() {
    return stack.empty();
  }
}
