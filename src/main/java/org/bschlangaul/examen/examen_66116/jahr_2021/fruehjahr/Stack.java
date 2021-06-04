package org.bschlangaul.examen.examen_66116.jahr_2021.fruehjahr;

class Stack {

  int stck[] = new int[3];
  public int top;

  // Initialize top of stack
  Stack() {
    top = -1;
  }

  // Push an item on the stack
  void push(int item) {
    if (top == 2) {
      System.out.println("Stack is full.");
    } else {
      stck[++top] = item;
    }
  }

  // Pop an item from the stack
  int pop() {
    if (top < 0) {
      throw new IllegalStateException("Stack is empty.");
    } else {
      return stck[top--];
    }
  }
}
