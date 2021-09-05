package org.bschlangaul.examen.examen_46115.jahr_2021.fruehjahr;

class Stack {
  private Item head;

  private int size;
  private int min = Integer.MAX_VALUE;

  public Stack() {
    head = null;
  }

  public void push(int val) {
    if (head == null) {
      head = new Item(val, null);
    } else {
      head = new Item(val, head);
    }
    if (min > val) {
      min = val;
    }
    size++;
  }

  public int pop() {
    if (head != null) {
      int val = head.val;
      size--;
      head = head.next;
      return val;
    } else {
      throw new IndexOutOfBoundsException("The stack is empty");
    }
  }

  public int size() {
    return size;
  }

  public int min() {
    return min;
  }

  class Item {
    private int val;
    private Item next;

    public Item(int val, Item next) {
      this.val = val;
      this.next = next;
    }
  }
}
