package org.bschlangaul.sortier;

import java.util.Stack;

/**
 * Quelle: nach <a href=
 * "https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/">geeksforgeeks.org</a>
 */
class StackSort {

  /**
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   *
   * @return Der sortierte Stapel.
   */
  public static Stack<Integer> sortiere(Stack<Integer> zahlen) {
    Stack<Integer> tmpStack = new Stack<Integer>();
    while (!zahlen.isEmpty()) {
      // pop out the first element
      int tmp = zahlen.pop();

      // while temporary stack is not empty and
      // top of stack is greater than temp
      while (!tmpStack.isEmpty() && tmpStack.peek() > tmp) {
        // pop from temporary stack and
        // push it to the input stack
        zahlen.push(tmpStack.pop());
      }

      // push temp in tempory of stack
      tmpStack.push(tmp);
    }
    return tmpStack;
  }

  // Driver Code
  public static void main(String args[]) {
    Stack<Integer> input = new Stack<Integer>();
    input.add(34);
    input.add(3);
    input.add(31);
    input.add(98);
    input.add(92);
    input.add(23);

    // This is the temporary stack
    Stack<Integer> tmpStack = sortiere(input);
    System.out.println("Sorted numbers are:");

    while (!tmpStack.empty()) {
      System.out.print(tmpStack.pop() + " ");
    }
  }
}
