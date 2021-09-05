package org.bschlangaul.examen.examen_46115.jahr_2021.fruehjahr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class StackTest {

  private Stack makeStack() {
    Stack stack = new Stack();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    return stack;
  }

  @Test
  public void methodPop() {
    Stack stack = makeStack();
    assertEquals(3, stack.pop());
    assertEquals(2, stack.pop());
    assertEquals(1, stack.pop());
    assertThrows(IndexOutOfBoundsException.class, () -> {
      stack.pop();
    });
  }

  @Test
  public void methodSize() {
    Stack stack = makeStack();
    assertEquals(stack.size(), 3);
  }

  @Test
  public void methodMin() {
    Stack stack = makeStack();
    assertEquals(stack.min(), 1);
  }
}
